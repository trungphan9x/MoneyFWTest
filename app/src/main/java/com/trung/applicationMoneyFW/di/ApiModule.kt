package com.trung.applicationMoneyFW.di

import com.trung.applicationMoneyFW.data.remote.api.AppMoneyFWAPI
import org.koin.dsl.module
import retrofit2.Retrofit

val apiModule = module {
    single(createdAtStart = false) { get<Retrofit>().create(AppMoneyFWAPI::class.java) }
}