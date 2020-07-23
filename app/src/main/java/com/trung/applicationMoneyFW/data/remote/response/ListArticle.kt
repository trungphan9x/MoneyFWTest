package com.trung.applicationMoneyFW.data.remote.response

import com.google.gson.annotations.SerializedName

data class ListArticle(
    @SerializedName("articles") val articles: List<Article>
)