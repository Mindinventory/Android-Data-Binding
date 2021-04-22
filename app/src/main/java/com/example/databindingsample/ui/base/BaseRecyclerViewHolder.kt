package com.example.databindingsample.ui.base

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseRecyclerViewHolder<T>(
    private val containerView: View
) : RecyclerView.ViewHolder(containerView) {
    abstract fun bind(item: T)
}
