package com.example.homework5.model

import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface GetSearchDataResource {

    @GET("v2/search/image")
    suspend fun getSearchImage(
        @Query("query") query : String,
        @Query("sort") sort: String,
        @Query("page") page: Int,
        @Query("size") size: Int,
    ) : SearchImageResponse

    @GET("v2/search/vclip")
    suspend fun getSearchVideo(
        @Query("query") query : String,
        @Query("sort") sort: String,
        @Query("page") page: Int,
        @Query("size") size: Int,
    ) : SearchVideoResponse

}



class GetSearchDataResourseCons(
    private val getSearchDataResource: GetSearchDataResource
) : GetSearchDataResource {
    override suspend fun getSearchImage(
        query: String,
        sort: String,
        page: Int,
        size: Int
    ) = getSearchDataResource.getSearchImage(query,sort,page,size)

    override suspend fun getSearchVideo(
        query: String,
        sort: String,
        page: Int,
        size: Int
    ) = getSearchDataResource.getSearchVideo(query, sort, page, size)


}