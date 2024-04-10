package com.me.pokemon.presentation.view.rv

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil

import com.me.domain.model.ItemModel
import com.me.pokemon.databinding.ListItemBinding


val ITEM_COMPARATOR = object : DiffUtil.ItemCallback<ItemModel>() {
    override fun areItemsTheSame(oldItem: ItemModel, newItem: ItemModel): Boolean =
        oldItem.name == newItem.name

    override fun areContentsTheSame(oldItem: ItemModel, newItem: ItemModel): Boolean =
        oldItem == newItem
}

class ItemPagingAdapter: PagingDataAdapter<ItemModel, ItemViewHolder>(ITEM_COMPARATOR) {
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = getItem(position)
        if (item != null) {
            holder.bind(item)
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(ListItemBinding.inflate(LayoutInflater.from(parent.context)))
    }
}