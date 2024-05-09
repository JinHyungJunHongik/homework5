package com.example.homework5.model

import android.os.Parcel
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parceler
import kotlinx.parcelize.Parcelize
import java.util.Date

data class SearchImageResponse(
    @SerializedName("meta") val meta : MetaResponse?,
    @SerializedName("documents") val documents : List<ImageDocumentResponse>?,
)

data class MetaResponse(
    @SerializedName("total_count") val totalCount: Int?,
    @SerializedName("pageable_count") val pageableCount: Int?,
    @SerializedName("is_end") val isEnd: Boolean?,
)

data class ImageDocumentResponse(
    @SerializedName("collection") val collection: Any?,
    @SerializedName("thumbnail_url") val thumbnailUrl: String?,
    @SerializedName("image_url") val imageUrl: String?,
    @SerializedName("width") val width: Int?,
    @SerializedName("height") val height: Int?,
    @SerializedName("display_sitename") val displaySitename: String?,
    @SerializedName("doc_url") val docUrl: String?,
    @SerializedName("datetime") val datetime: Date?,
)

data class SearchVideoResponse(
    @SerializedName("meta") val meta : MetaResponse?,
    @SerializedName("documents") val documents : List<VideoDocumentResponse>?,
)

data class VideoDocumentResponse(
    @SerializedName("title") val title: String?,
    @SerializedName("url") val url : String?,
    @SerializedName("datetime") val datetime : Date?,
    @SerializedName("play_time") val playTime : Int?,
    @SerializedName("thumbnail") val thumnail : String,
    @SerializedName("author") val author : String,
)



class MultiSearchData(
    val type: DataType,
    val imageDoc: ImageDocumentResponse?,
    val videoDoc: VideoDocumentResponse?,
)  {

    val textTypeDate : String = manufactureDateTime()

    fun manufactureDateTime() : String {
        var date : String = ""
        date = if(type == DataType.IMAGE){
            imageDoc!!.datetime.toString()
        } else {
            videoDoc!!.datetime.toString()
        }
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

