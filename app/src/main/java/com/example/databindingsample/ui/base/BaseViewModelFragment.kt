package com.example.databindingsample.ui.base

import android.os.Bundle
import androidx.annotation.CallSuper
import androidx.annotation.LayoutRes
import androidx.databinding.ViewDataBinding

abstract class BaseViewModelFragment<VM : BaseViewModel, T : ViewDataBinding>(
    @LayoutRes layoutRes: Int
) : BaseFragment<T>(layoutRes) {

    protected val viewModel by lazy { buildViewModel() }

    protected abstract fun buildViewModel(): VM

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initLiveDataObservers()
        viewModel.loadPage(isMultipleLoad())
    }

    @CallSuper
    protected open fun initLiveDataObservers() {
    }

    protected open fun isMultipleLoad(): Boolean = false
}