package com.trung.applicationMoneyFW.ui.activity.main

import android.os.Bundle
import androidx.lifecycle.Observer
import com.trung.applicationMoneyFW.R
import com.trung.applicationMoneyFW.core.BaseActivity
import com.trung.applicationMoneyFW.databinding.ActivityMainBinding
import com.trung.applicationMoneyFW.ui.fragment.list.ListArticleFragment
import com.trung.applicationMoneyFW.util.AppDialog
import com.trung.applicationMoneyFW.util.ERROR_EVENT
import com.trung.applicationMoneyFW.util.UIEvent
import org.koin.android.viewmodel.ext.android.viewModel


class MainActivity : BaseActivity<ActivityMainBinding>() {

    private val viewModel by viewModel(MainViewModel::class)

    override fun getLayoutResId() = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.vm = viewModel
        viewModel.uiEvent.observe(this, onUiEvent())

        supportFragmentManager.beginTransaction()
            .replace(R.id.mainContainer, ListArticleFragment()).commit()
    }

    /**
     * Function which observe the UI events sent from viewModel to process them
     */
    override fun onUiEvent() = Observer<UIEvent<Int>> {
        it.getContentIfNotHandled()?.let {
            when (it.first) {
                ERROR_EVENT -> {
                    AppDialog.showDialog(this, it.second.toString())
                }
            }
        }
    }
}