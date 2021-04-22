package com.example.databindingsample.common.extensions

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.databindingsample.domain.base.UiState
import timber.log.Timber

fun <T> LiveData<T>.safeObserve(owner: LifecycleOwner, observer: (T) -> Unit) {
    observe(owner, { it?.let(observer) ?: Timber.d("Live data value is null") })
}

fun <T> MutableLiveData<UiState<T>>.setSuccess(data: T) = postValue(UiState.Success(data))

fun <T> MutableLiveData<UiState<T>>.setLoading() = postValue(UiState.Loading())

fun <T> MutableLiveData<UiState<T>>.setError(throwable: Throwable) =
    postValue(UiState.Error(throwable))
