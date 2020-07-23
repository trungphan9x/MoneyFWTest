package com.trung.applicationMoneyFW.ui.fragment.list

import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import androidx.lifecycle.Observer
import com.trung.applicationMoneyFW.R
import com.trung.applicationMoneyFW.core.BaseFragment
import com.trung.applicationMoneyFW.databinding.FragmentListArticleBinding
import com.trung.applicationMoneyFW.ui.activity.detail.DetailActivity
import com.trung.applicationMoneyFW.ui.activity.main.MainViewModel
import com.trung.applicationMoneyFW.util.AppDialog
import com.trung.applicationMoneyFW.util.ERROR_EVENT
import com.trung.applicationMoneyFW.util.UIEvent
import org.koin.android.viewmodel.ext.android.sharedViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class ListArticleFragment : BaseFragment<FragmentListArticleBinding>() {
    private val viewModel: ListArticleViewModel by viewModel()
    private val mainViewModel: MainViewModel by sharedViewModel()
    private val adapter = ListArticleAdapter()
    override fun getLayoutResId() = R.layout.fragment_list_article


    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.vm = viewModel
        binding.mainVM = mainViewModel

        viewModel.uiEvent.observe(viewLifecycleOwner, onUiEvent())

        //get list of article from API
        viewModel.getArticlesFromApi()

        //Init Adapter Of recyclerview
        binding.rvListArticle.adapter = adapter

        adapter.setOnItemClickListener { position, article, view ->
            DetailActivity.startActivity(
                requireActivity(),
                article.detail,
                article.image,
                article.title,
                view
            )
        }

    }

    /**
     * Function which observe the UI events sent from viewModel to process them
     * ERROR_MESSAGE: dialog showing error message
     */
    override fun onUiEvent() = Observer<UIEvent<Int>> {
        it.getContentIfNotHandled()?.let {
            when (it.first) {
                ERROR_EVENT -> {
                    AppDialog.showDialog(
                        requireContext(),
                        it.second.toString(),
                        { requireActivity().onBackPressed() })
                }
                else -> {

                }
            }
        }
    }

}