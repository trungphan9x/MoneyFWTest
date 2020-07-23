package com.trung.applicationMoneyFW.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth
import com.trung.applicationMoneyFW.data.repository.ArticleRepository
import com.trung.applicationMoneyFW.di.apiModuleAndroidTest
import com.trung.applicationMoneyFW.di.networkModuleAndroidTest
import com.trung.applicationMoneyFW.di.repositoryModuleAndroidTest
import com.trung.applicationMoneyFW.di.viewModelModuleAndroidTest
import com.trung.applicationMoneyFW.ui.fragment.list.ListArticleViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
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
class ListArticleViewModelTest : KoinTest {
    private val testDispatcher = TestCoroutineDispatcher()
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
    fun checkIfGetArticlesFromApiSuccessfully() {
        val listViewModel = ListArticleViewModel(testDispatcher, articleRepository)
        runBlocking {
            listViewModel.getArticlesFromApi()
            delay(1000)
            Truth.assertThat(listViewModel.articleList.get()).isNotNull()
        }
    }
}