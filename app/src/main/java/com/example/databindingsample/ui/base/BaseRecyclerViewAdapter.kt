package com.example.databindingsample.ui.base

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

abstract class BaseRecyclerViewAdapter<S, T : RecyclerView.ViewHolder> : RecyclerView.Adapter<T>() {

    // S = Model/Data class , T = RecyclerView.ViewHolder

    // list of all items
    var items = mutableListOf<S>()
        private set

    /**
     * create view holder of recycler view item
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): T {
        return createItemViewHolder(parent, viewType)
    }

    /**
     * bind recycler view item with data
     */
    override fun onBindViewHolder(holder: T, position: Int) {
        bindItemViewHolder(holder, position)
    }

    /**
     * get count for visible count
     */
    override fun getItemCount() = items.size

    /**
     * abstract method to create custom view holder
     */
    protected abstract fun createItemViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): T

    /**
     * abstract method to bind custom data
     */
    protected abstract fun bindItemViewHolder(holder: T, position: Int)

    /**
     * add all items to list
     */
    fun addAll(items: ArrayList<S>, clearPreviousItems: Boolean = false) {
        if (clearPreviousItems) {
            this.items.clear()
        }
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    /**
     * add item (at postion - optional)
     */
    fun addItem(item: S, position: Int = items.size, clearPreviousItems: Boolean = false) {
        var adapterPostion = position
        if (clearPreviousItems) {
            this.items.clear()
            adapterPostion = 0 // set position to 0 after items arrayList gets clear
        }
        this.items.add(adapterPostion, item)
        notifyDataSetChanged()
    }

    /**
     * remove item from position
     */
    fun removeItem(position: Int) {
        if (position != -1) {
            items.removeAt(position)
            notifyItemRemoved(position)
        }
    }

    /**
     * get item at position
     */
    fun getItemAt(position: Int): S? {
        return items.removeAt(position)
    }

    /**
     * update item at postion
     */
    fun updateItemAt(position: Int, item: S) {
        if (position != -1) {
            items[position] = item
            notifyItemChanged(position)
        }
    }

    /**
     * To clear the adapter
     */
    fun clearAdapter() {
        items.clear()
        notifyDataSetChanged()
    }
}
