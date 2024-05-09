package com.example.homework5.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.homework5.model.ImageDocumentResponse
import com.example.homework5.databinding.ActivityMainBinding
import com.example.homework5.model.MultiSearchData
import com.google.android.material.tabs.TabLayoutMediator
import java.util.Date


val storageList = mutableListOf<MultiSearchData>()
val MultidataList = mutableListOf<MultiSearchData>()

class MainActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initView()

    }


    private fun initView(){
       binding.viewpager.adapter = ViewPagerAdapter(this)
        TabLayoutMediator(binding.tabs, binding.viewpager){tab, position ->
            when(position){
                0 -> {tab.text = "검색 결과"}
                else -> tab.text = "내 보관함"
            }
        }.attach()
    }
}