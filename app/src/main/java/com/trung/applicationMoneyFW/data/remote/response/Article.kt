package com.trung.applicationMoneyFW.data.remote.response

import com.google.gson.annotations.SerializedName

data class Article(
    @SerializedName("title") val title: String,
    @SerializedName("pubDate") val pubDate: String,
    @SerializedName("dc:creator") val dcCreator: String,
    @SerializedName("category") val category: List<String>,
    @SerializedName("description") val description: String,
    @SerializedName("image") val image: String,
    @SerializedName("detail") val detail: String
)