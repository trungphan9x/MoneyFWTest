package com.trung.applicationMoneyFW.ui.activity.detail

import android.app.Activity
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityOptionsCompat
import androidx.lifecycle.Observer
import com.trung.applicationMoneyFW.R
import com.trung.applicationMoneyFW.core.BaseActivity
import com.trung.applicationMoneyFW.databinding.ActivityDetailBinding
import com.trung.applicationMoneyFW.util.AppDialog
import com.trung.applicationMoneyFW.util.ERROR_EVENT
import com.trung.applicationMoneyFW.util.UIEvent

import org.koin.android.viewmodel.ext.android.viewModel

class DetailActivity : BaseActivity<ActivityDetailBinding>() {
    private val viewModel by viewModel(DetailViewModel::class)
    override fun getLayoutResId() = R.layout.activity_detail

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.allowEnterTransitionOverlap = false
        window.allowReturnTransitionOverlap = false

        binding.vm = viewModel
        viewModel.uiEvent.observe(this, onUiEvent())
        viewModel.detailView = this
        intent?.let {
            intent.getStringExtra(DETAIL_TEXT)?.let {
                binding.detailText = it
            }

            intent.getStringExtra(DETAIL_IMAGE)?.let {
                binding.detailImage = it
            }

            intent.getStringExtra(DETAIL_TITLE)?.let {
                binding.detailTitle = it
            }

            intent.getStringExtra(DETAIL_ANIMATION_NAME)?.let {
                binding.ivPhotoUrl.transitionName = it
            }
        }

    }

    /**
     * Function which observe the UI events sent from viewModel to process them
     * NO_INTERNET_AND_NO_DATA_IN_ROOM: is called when it has no internet to call API
     */
    override fun onUiEvent() = Observer<UIEvent<Int>> {
        it.getContentIfNotHandled()?.let {
            when (it.first) {
                ERROR_EVENT -> {
                    AppDialog.showDialog(this, it.second.toString()) { super.onBackPressed() }
                }
                else -> {

                }
            }
        }
    }


    companion object {
        private const val DETAIL_TEXT = "detail_text"
        private const val DETAIL_IMAGE = "detail_image"
        private const val DETAIL_TITLE = "detail_title"
        const val DETAIL_ANIMATION_NAME = "detail_animation_mame"

        // use for animation

        @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
        fun startActivity(
            activity: Activity?,
            detailText: String,
            detailImage: String,
            detailTitle: String,
            view: View
        ) {

            val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                activity!!,
                view,
                "hihi"
            )

            Intent(activity, DetailActivity::class.java).apply {
                this.putExtra(DETAIL_TEXT, detailText)
                this.putExtra(DETAIL_IMAGE, detailImage)
                this.putExtra(DETAIL_TITLE, detailTitle)
                this.putExtra(DETAIL_ANIMATION_NAME, "hihi")
            }.also {

                activity.startActivity(it, options.toBundle())

                //activity.overridePendingTransition(R.anim.bounce, R.anim.fade_in)
            }
        }
    }
}