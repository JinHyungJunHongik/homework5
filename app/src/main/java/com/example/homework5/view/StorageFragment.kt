package com.example.homework5.view

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.homework5.model.ImageDocumentResponse
import com.example.homework5.databinding.FragmentStorageBinding
import com.example.homework5.model.MultiSearchData
import com.example.homework5.viewmodel.SearchViewModel


class StorageFragment : Fragment() {
    private val binding by lazy {
        FragmentStorageBinding.inflate(layoutInflater)
    }
    private val viewModel by lazy {
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

    private fun initView(){
        viewModel.storageData.observe(viewLifecycleOwner){
            binding.storageRecycler.adapter = SearchRecyclerAdapter(it, object : OnButtonClick{
                override fun onHeartClick(data: MultiSearchData) {
                }
            })
        }
        binding.storageRecycler.layoutManager = GridLayoutManager(this.context, 2)
    }

//    @SuppressLint("CommitPrefEdits")
//    private fun saveSearchWord() {
//        val pref = requireActivity().getSharedPreferences("pref", 0)
//        val edit = pref.edit()
//        edit.put
//        edit.apply()
//    }
//
//    private fun settingSearchWord() {
//        val pref = requireActivity().getSharedPreferences("pref", 0)
//        binding.searchEditText.setText(pref.getString("SearchWord", ""))
//    }
}