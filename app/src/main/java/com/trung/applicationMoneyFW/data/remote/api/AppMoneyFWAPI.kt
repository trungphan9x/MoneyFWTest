package com.trung.applicationMoneyFW.data.remote.api

import com.trung.applicationMoneyFW.data.remote.response.ListArticle
import retrofit2.http.GET

interface AppMoneyFWAPI {
    @GET(value = "/example-feed/feed.json")
    suspend fun getArticleList(): ListArticle?
}