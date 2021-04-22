package com.example.databindingsample.util

import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.databindingsample.R

object GlideUtils {

    @JvmStatic
    @BindingAdapter("profileImageUrl")
    fun profileImageUrl(imageView: AppCompatImageView, url: String?) {
        if (url?.isNotEmpty() == true) {
            Glide.with(imageView.context)
                .load(url)
                .placeholder(R.drawable.avatar)
                .into(imageView)
        }
    }
}