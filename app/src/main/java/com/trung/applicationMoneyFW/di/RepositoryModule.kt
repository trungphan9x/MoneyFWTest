package com.trung.applicationMoneyFW.di

import com.trung.applicationMoneyFW.data.repository.ArticleRepository
import org.koin.dsl.module

val repositoryModule = module {
    factory {
        ArticleRepository(
            get()
        )
    }
}