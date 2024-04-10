package com.me.pokemon.presentation.view.rv

import android.os.Bundle
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.me.domain.model.ItemModel
import com.me.pokemon.R
import com.me.pokemon.databinding.ListItemBinding
import com.me.pokemon.presentation.view.fragments.ITEM_NAME_KEY

class ItemViewHolder(private val binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(itemModel: ItemModel) {
        with(binding) {
            name.text = itemModel.name
            Glide.with(binding.root.context).load(itemModel.sprites).into(icon)

            itemView.setOnClickListener {
                navigate(itemModel)
            }
        }
    }

    private fun navigate(itemModel: ItemModel) {
        val navigationController = Navigation.findNavController(binding.root)
        val nameArg = Bundle()
        nameArg.putString(ITEM_NAME_KEY, itemModel.name)
        navigationController.navigate(R.id.action_mainFragment_to_descriptionFragment, nameArg)
    }
}