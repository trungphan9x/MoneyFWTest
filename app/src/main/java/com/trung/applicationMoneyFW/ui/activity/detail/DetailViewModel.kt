package com.trung.applicationMoneyFW.ui.activity.detail

import android.view.View
import com.trung.applicationMoneyFW.core.BaseViewModel

class DetailViewModel : BaseViewModel() {
    var detailView: DetailActivity? = null


    /**
     * Event click on back button
     */
    fun onClickBack(view: View) {
        detailView?.onBackPressed()
    }

}