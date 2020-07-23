package com.trung.applicationMoneyFW.data.repository

import com.trung.applicationMoneyFW.core.BaseApiResult
import com.trung.applicationMoneyFW.core.BaseRepository
import com.trung.applicationMoneyFW.data.remote.api.AppMoneyFWAPI
import com.trung.applicationMoneyFW.data.remote.response.ListArticle

class ArticleRepository(private val appMoneyFWAPI: AppMoneyFWAPI) : BaseRepository() {

    suspend fun getArticleList(): BaseApiResult<ListArticle?> {
        return safeApi {
            appMoneyFWAPI.getArticleList()
        }
    }
}