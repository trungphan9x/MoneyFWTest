package com.trung.applicationMoneyFW.core

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.trung.applicationMoneyFW.util.UIEvent

open class BaseViewModel : ViewModel() {
    val _uiEvent: MutableLiveData<UIEvent<Int>> = MutableLiveData()
    val uiEvent: LiveData<UIEvent<Int>> get() = _uiEvent
}