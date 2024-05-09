package com.example.homework5.view

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.homework5.model.ImageDocumentResponse
import com.example.homework5.databinding.ItemDataBinding
import com.example.homework5.model.DataType
import com.example.homework5.model.MultiSearchData
import java.util.Date

interface OnButtonClick{
    fun onHeartClick(data : MultiSearchData)
}

class SearchRecyclerAdapter(private val data : MutableList<MultiSearchData>, val onButtonClick: OnButtonClick) : RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    inner class ImageViewHolder(private val binding: ItemDataBinding) : RecyclerView.ViewHolder(binding.root){
        @SuppressLint("SetTextI18n")
        fun bind(data : MultiSearchData){
            Glide.with(itemView).load(data.imageDoc!!.thumbnailUrl).into(binding.itemImage)
            binding.itemTitle.text = "[IMAGE] ${data.imageDoc.displaySitename}"
            binding.itemDatatime.text = data.manufactureDateTime()
            binding.itemContainer.setOnClickListener {
                binding.imgLike.visibility = View.VISIBLE
                onButtonClick.onHeartClick(data)
            }
        }
    }
    inner class VideoViewHolder(private val binding: ItemDataBinding) : RecyclerView.ViewHolder(binding.root){
        @SuppressLint("SetTextI18n")
        fun bind(data : MultiSearchData){
            Glide.with(itemView).load(data.videoDoc!!.thumnail).into(binding.itemImage)
            binding.itemTitle.text = "[VIDEO] ${data.videoDoc.title}"
            binding.itemDatatime.text = data.manufactureDateTime()
            binding.itemContainer.setOnClickListener {
                binding.imgLike.visibility = View.VISIBLE
                onButtonClick.onHeartClick(data)
            }
        }
    }
    override fun getItemViewType(position: Int): Int {
        return data[position].type.viewType
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val muiltiViewType = DataType.entries.find { it.viewType == viewType }
        return when(muiltiViewType){
            DataType.IMAGE ->{val binding = ItemDataBinding.inflate(LayoutInflater.from(parent.context),parent,false)
                                ImageViewHolder(binding)}
            else ->{val binding = ItemDataBinding.inflate(LayoutInflater.from(parent.context),parent,false)
                        VideoViewHolder(binding)}
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
       if(data[position].type == DataType.IMAGE){
           (holder as ImageViewHolder).bind(data[position])
       }
        else {
           (holder as VideoViewHolder).bind(data[position])
        }
    }


    override fun getItemCount(): Int {
       return data.size
    }

    private fun manufactureDateTime(date: String) : String {
       val year = date.substring(date.lastIndex-3, date.lastIndex+1)
       val str1 = date.substring(4, 19)
       val str2 = date.substring(7, 19)
       val month = str1.substring(0,2)
       var numOfMonth : String = ""
        numOfMonth = when(month){
            "Jan" -> {
                "01"
            }

            "Feb" -> {
                "02"
            }

            "Mar" -> {
                "03"
            }

            "Apr" -> {
                "04"
            }

            "May" -> {
                "05"
            }

            "Jun" -> {
                "06"
            }

            "Jul" -> {
                "07"
            }

            "Aug" -> {
                "08"
            }

            "Sep" -> {
                "09"
            }

            "Oct" -> {
                "10"
            }

            "Nov" -> {
                "11"
            }

            else -> {
                "12"
            }
        }
       val result = "$year-$numOfMonth$str2"
       return result.replace(" ",  "-")
    }
}