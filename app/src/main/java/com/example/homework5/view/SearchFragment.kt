package com.example.homework5.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.homework5.model.ImageDocumentResponse
import com.example.homework5.databinding.FragmentSearchBinding
import com.example.homework5.model.MultiSearchData
import com.example.homework5.viewmodel.SearchViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class SearchFragment : Fragment() {
    private val binding by lazy{
        FragmentSearchBinding.inflate(layoutInflater)
    }
    private val viewModel by lazy{
        ViewModelProvider(requireActivity(), SearchViewModel.SearchViewModelFactory())[SearchViewModel::class.java]
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }



    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        settingSearchWord()
    }



    @SuppressLint("NotifyDataSetChanged")
    private fun initView() {

        binding.searchBtnEnter.setOnClickListener {
            MultidataList.clear()
            val query = binding.searchEditText.text.toString()
            saveSearchWord()
            viewModel.onSearch(query)
            viewModel.onVideoSearch(query)
        }
        viewModel.multiData.observe(viewLifecycleOwner) {
            binding.searchRecycler.adapter = SearchRecyclerAdapter(it, object : OnButtonClick {
                override fun onHeartClick(data: MultiSearchData) {
                    storageList.add(data)
                    viewModel.setStorageData()
                }
            })
        }

        binding.searchRecycler.layoutManager = GridLayoutManager(this.context, 2)
        val scrollListener = object : RecyclerView.OnScrollListener() {
            //리싸이클러 뷰를 담는 nestedscrollview의 높이가 0보다 커지면 버튼 활성화
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (binding.searchRecyclerContainer.scrollY > 0) {
                    binding.searchFloatingBtn.visibility = View.VISIBLE
                } else {
                    binding.searchFloatingBtn.visibility = View.GONE
                }
            }
        }
        //위로가기 버튼을 누를 시 nestedscrollview의 높이를 최상단으로 이동시키기
        binding.searchRecycler.addOnScrollListener(scrollListener)
        binding.searchFloatingBtn.setOnClickListener {
            binding.searchRecyclerContainer.scrollY = 0
        }

    }




    @SuppressLint("CommitPrefEdits")
    private fun saveSearchWord() {
        val pref = requireActivity().getSharedPreferences("pref", 0)
        val edit = pref.edit()
        edit.putString("SearchWord", binding.searchEditText.text.toString())
        edit.apply()
    }

    private fun settingSearchWord() {
        val pref = requireActivity().getSharedPreferences("pref", 0)
        binding.searchEditText.setText(pref.getString("SearchWord", ""))
    }

}