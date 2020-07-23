package com.trung.applicationMoneyFW

import android.app.Application
import android.content.Context
import com.trung.applicationMoneyFW.di.apiModule
import com.trung.applicationMoneyFW.di.networkModule
import com.trung.applicationMoneyFW.di.repositoryModule
import com.trung.applicationMoneyFW.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class ApplicationMoneyFWTest : Application() {
    override fun onCreate() {
        super.onCreate()
        ApplicationMoneyFWTest.context = applicationContext

        startKoin {
            androidLogger()
            androidContext(this@ApplicationMoneyFWTest)
            // modules
            modules(
                listOf(viewModelModule, repositoryModule, apiModule, networkModule)
            )
        }
    }

    companion object {
        lateinit var context: Context
    }
}