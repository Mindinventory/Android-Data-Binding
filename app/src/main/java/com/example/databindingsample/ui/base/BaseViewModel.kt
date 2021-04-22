package com.example.databindingsample.ui.base

import androidx.annotation.CallSuper
import androidx.lifecycle.ViewModel

abstract class BaseViewModel : ViewModel() {
    private var isFirst = true

    /**
     * Only can call once per lifecycle
     * @param multipleTimes (OPTIONAL) set it to true to make multiple call capability
     */
    @CallSuper
    open fun loadPage(multipleTimes: Boolean = false): Boolean {
        if (isFirst) {
            isFirst = false
            return true
        }

        return isFirst || multipleTimes
    }

    override fun onCleared() {
        super.onCleared()
    }
}