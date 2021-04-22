package com.example.databindingsample.common.extensions

import android.app.Activity
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.databindingsample.ui.base.BaseViewModel

inline fun <reified VM : BaseViewModel> Fragment.viewModel(
    factory: ViewModelProvider.Factory,
): VM {
    return ViewModelProvider(this, factory)[VM::class.java]
}

fun Fragment.hideKeyboard() {
    val currentFocus = if (this is DialogFragment) dialog?.currentFocus else activity?.currentFocus
    val imm = requireContext().getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
}
