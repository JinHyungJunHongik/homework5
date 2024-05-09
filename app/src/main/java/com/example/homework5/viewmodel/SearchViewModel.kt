package com.example.homework5.viewmodel

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.homework5.model.GetSearchDataResource
import com.example.homework5.model.GetSearchDataResourseCons
import com.example.homework5.model.ImageDocumentResponse
import com.example.homework5.model.SearchImageResponse
import com.example.homework5.model.SearchVideoResponse
import com.example.homework5.TestRetrofit
import com.example.homework5.model.DataType
import com.example.homework5.model.MultiSearchData
import com.example.homework5.model.VideoDocumentResponse
import com.example.homework5.view.MultidataList
import com.example.homework5.view.storageList
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class SearchViewModel(private val getSearchDataResource: GetSearchDataResource) : ViewModel() {


    private val getMultiData : MutableLiveData<MutableList<MultiSearchData>> = MutableLiveData()
    private val getStorageData : MutableLiveData<MutableList<MultiSearchData>> = MutableLiveData()
    val multiData : LiveData<MutableList<MultiSearchData>> get() = getMultiData
    val storageData : LiveData<MutableList<MultiSearchData>> get() = getStorageData


    fun onSearch(query: String){
        CoroutineScope(Dispatchers.IO).launch {
            val imageResponse = getSearchDataResource.getSearchImage( query, "accuracy", 1, 30)
            imageResponse.documents?.forEach {
                val data = MultiSearchData(DataType.IMAGE, it, null)
                MultidataList.add(data)
            }
            sortData()
            getMultiData.postValue(MultidataList)
        }
    }
     @SuppressLint("SuspiciousIndentation")
     fun onVideoSearch(query: String){
        CoroutineScope(Dispatchers.IO).launch {
          val videoResponse = getSearchDataResource.getSearchVideo( query, "accuracy", 1, 10)
          videoResponse.documents?.forEach {
              val data = MultiSearchData(DataType.VIDEO,null,it)
              MultidataList.add(data)
          }
            sortData()
            getMultiData.postValue(MultidataList)
        }
    }

    fun setStorageData() {
        getStorageData.value = storageList
    }

    private suspend fun sortData(){
        MultidataList.sortBy {
            it.textTypeDate
        }
        MultidataList.reverse()
    }

    class SearchViewModelFactory : ViewModelProvider.Factory {

        override fun <T : ViewModel> create(
            modelClass: Class<T>,
            extras: CreationExtras
        ): T {

            return SearchViewModel(
                GetSearchDataResourseCons(TestRetrofit.search)
            ) as T
        }
    }

}