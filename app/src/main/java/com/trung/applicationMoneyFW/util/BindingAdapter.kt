package com.trung.applicationMoneyFW.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.trung.applicationMoneyFW.R
import com.trung.applicationMoneyFW.data.remote.response.ListArticle
import com.trung.applicationMoneyFW.di.GlideApp
import com.trung.applicationMoneyFW.ui.fragment.list.ListArticleAdapter


@BindingAdapter("setUrlPhoto")
fun ImageView.setUrlImage(url: String?) {
    GlideApp.with(this.context)
        .load(url)
        .apply {
            RequestOptions()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .skipMemoryCache(false)
        }
        .placeholder(R.drawable.ic_default)
        .centerCrop()
        .fitCenter()
        .into(this)
}

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: ListArticle?) {
    val adapter = recyclerView.adapter as ListArticleAdapter
    //adapter.submitList(data)

    adapter.setIetms(data?.articles)
}

