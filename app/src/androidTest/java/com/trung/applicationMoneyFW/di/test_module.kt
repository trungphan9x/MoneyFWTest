package com.trung.applicationMoneyFW.di

import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.trung.applicationMoneyFW.BuildConfig
import com.trung.applicationMoneyFW.data.Constant
import com.trung.applicationMoneyFW.data.remote.api.AppMoneyFWAPI
import com.trung.applicationMoneyFW.data.repository.ArticleRepository
import com.trung.applicationMoneyFW.module.network.ResponseHandler
import com.trung.applicationMoneyFW.ui.activity.detail.DetailViewModel
import com.trung.applicationMoneyFW.ui.activity.main.MainViewModel
import com.trung.applicationMoneyFW.ui.fragment.list.ListArticleViewModel
import kotlinx.coroutines.test.TestCoroutineDispatcher
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val testDispatcher = module {
    factory { TestCoroutineDispatcher() }
}

val viewModelModuleAndroidTest = module {
    viewModel { MainViewModel() }
    viewModel { DetailViewModel() }
    viewModel { ListArticleViewModel(get(), get()) }
}

val repositoryModuleAndroidTest = module {
    factory { ResponseHandler() }
    factory {
        ArticleRepository(
            get()
        )
    }
}


val networkModuleAndroidTest = module {
    factory { buildOkHttpClient() }
    single { buildRetrofit(get()) }

}


fun buildOkHttpClient() = OkHttpClient.Builder().apply {
    connectTimeout(Constant.OkHttp_TIMEOUT, TimeUnit.SECONDS)
    writeTimeout(Constant.OkHttp_TIMEOUT, TimeUnit.SECONDS)
    readTimeout(Constant.OkHttp_TIMEOUT, TimeUnit.SECONDS)
    addInterceptor(HttpLoggingInterceptor().setLevel(if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE))
}

fun buildRetrofit(okHttpClient: OkHttpClient.Builder): Retrofit {
    val gson = GsonBuilder()
        .setLenient()
        .create()

    return Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create(gson))
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .baseUrl(BuildConfig.API_URL)
        .client(okHttpClient.build())
        .build()
}


val apiModuleAndroidTest = module {
    single(createdAtStart = false) { get<Retrofit>().create(AppMoneyFWAPI::class.java) }
}
