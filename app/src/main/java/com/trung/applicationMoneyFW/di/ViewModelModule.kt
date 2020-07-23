package com.trung.applicationMoneyFW.di

import com.trung.applicationMoneyFW.ui.activity.detail.DetailViewModel
import com.trung.applicationMoneyFW.ui.activity.main.MainViewModel
import com.trung.applicationMoneyFW.ui.fragment.list.ListArticleViewModel
import kotlinx.coroutines.Dispatchers
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    factory { Dispatchers.IO }
    viewModel { MainViewModel() }
    viewModel { DetailViewModel() }
    viewModel { ListArticleViewModel(get(), get()) }
}