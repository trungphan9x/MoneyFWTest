package com.trung.applicationMoneyFW.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth
import com.trung.applicationMoneyFW.data.repository.ArticleRepository
import com.trung.applicationMoneyFW.di.apiModuleAndroidTest
import com.trung.applicationMoneyFW.di.networkModuleAndroidTest
import com.trung.applicationMoneyFW.di.repositoryModuleAndroidTest
import com.trung.applicationMoneyFW.di.viewModelModuleAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.inject
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class ArticleRepositoryTest : KoinTest {

    private val articleRepository: ArticleRepository by inject()

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @Before
    @Throws(Exception::class)
    fun before() {
        MockitoAnnotations.initMocks(this)
        stopKoin()

        startKoin {
            modules(
                viewModelModuleAndroidTest,
                repositoryModuleAndroidTest,
                apiModuleAndroidTest,
                networkModuleAndroidTest
            )
        }
    }

    @After
    @Throws(Exception::class)
    fun after() {
        stopKoin()
    }

    @Test
    fun getListArticles() {
        runBlocking {
            articleRepository.getArticleList().let {
                Truth.assertThat(it.data?.articles).isNotEmpty()
            }
        }
    }

}