package com.trung.applicationMoneyFW.ui.fragment.list

import androidx.databinding.ObservableField
import androidx.lifecycle.viewModelScope
import com.trung.applicationMoneyFW.ApplicationMoneyFWTest
import com.trung.applicationMoneyFW.ApplicationMoneyFWTest.Companion.context
import com.trung.applicationMoneyFW.R
import com.trung.applicationMoneyFW.core.BaseViewModel
import com.trung.applicationMoneyFW.data.enum.Status
import com.trung.applicationMoneyFW.data.remote.response.ListArticle
import com.trung.applicationMoneyFW.data.repository.ArticleRepository
import com.trung.applicationMoneyFW.util.ERROR_EVENT
import com.trung.applicationMoneyFW.util.UIEvent
import com.trung.applicationMoneyFW.util.extension.isNetworkConnected
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch

class ListArticleViewModel(
    private val defaultDispatcher: CoroutineDispatcher,
    private val articleRepository: ArticleRepository
) : BaseViewModel() {

    val articleList = ObservableField<ListArticle?>()

    /**
     * if it has internet, get all items for RecyclerView in fragment_list_article.xml from API
     * else if it has no internet, show dialog and exit App
     */
    fun getArticlesFromApi() {

        viewModelScope.launch(defaultDispatcher) {
            try {
                val unit = if (ApplicationMoneyFWTest.context.isNetworkConnected) {
                    articleRepository.getArticleList().let { baseApiResult ->
                        when (baseApiResult.status) {
                            Status.SUCCESS -> {
                                baseApiResult.data?.let { result ->
                                    articleList.set(result)
                                }
                            }

                            Status.ERROR -> {
                                _uiEvent.postValue(UIEvent(ERROR_EVENT, baseApiResult.message))
                            }

                            Status.LOADING -> {

                            }
                        }

                    }

                } else {
                    _uiEvent.postValue(
                        UIEvent(
                            ERROR_EVENT,
                            context.getString(R.string.has_no_internet)
                        )
                    )
                }

            } catch (ex: Exception) {
                _uiEvent.postValue(UIEvent(ERROR_EVENT, ex.localizedMessage))
            } finally {

            }
        }
    }
}