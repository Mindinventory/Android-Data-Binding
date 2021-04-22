package com.example.databindingsample.ui.randomcharacter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.databindingsample.data.randomcharacter.entity.CharacterItem
import com.example.databindingsample.databinding.ItemCharacterBinding
import com.example.databindingsample.ui.base.BaseRecyclerViewAdapter
import com.example.databindingsample.ui.base.BaseRecyclerViewHolder

class CharacterAdapter :
    BaseRecyclerViewAdapter<CharacterItem, CharacterAdapter.CharacterViewHolder>() {

    override fun createItemViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CharacterAdapter.CharacterViewHolder {
        return CharacterViewHolder(
            ItemCharacterBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun bindItemViewHolder(holder: CharacterAdapter.CharacterViewHolder, position: Int) {
        holder.bind(items[position])
    }

    inner class CharacterViewHolder(
        private val itemCharacterBinding: ItemCharacterBinding
    ) : BaseRecyclerViewHolder<CharacterItem>(itemCharacterBinding.root) {

        override fun bind(item: CharacterItem) {
            with(itemCharacterBinding) {
                character = item
            }
        }

    }
}