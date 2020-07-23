package com.trung.applicationMoneyFW.core

import com.trung.applicationMoneyFW.module.network.ResponseHandler
import org.koin.core.KoinComponent
import org.koin.core.inject

abstract class BaseRepository : KoinComponent {
    val responseHandler: ResponseHandler by inject()

    suspend fun <T> safeApi(callApi: suspend () -> T): BaseApiResult<T> {
        try {
            callApi.invoke().let { response ->
                return responseHandler.handleSuccess(response)
            }
        } catch (ex: Exception) {
            return responseHandler.handleException(ex)
        }
    }
}