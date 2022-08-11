package com.esaudev.networkgarden.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.esaudev.networkgarden.databinding.ItemPokemonBinding
import com.esaudev.networkgarden.domain.extension.load
import com.esaudev.networkgarden.domain.model.Pokemon

class PokemonListAdapter(

): ListAdapter<Pokemon, BaseListViewHolder<*>>(DiffUtilCallback) {

    private object DiffUtilCallback : DiffUtil.ItemCallback<Pokemon>() {
        override fun areItemsTheSame(oldItem: Pokemon, newItem: Pokemon): Boolean = oldItem.name == newItem.name
        override fun areContentsTheSame(oldItem: Pokemon, newItem: Pokemon): Boolean = oldItem == newItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseListViewHolder<*> {
        val itemBinding = ItemPokemonBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BindViewHolderList(itemBinding)
    }

    override fun onBindViewHolder(holder: BaseListViewHolder<*>, position: Int) {
        when (holder) {
            is BindViewHolderList -> holder.bind(getItem(position), position)
        }
    }

    inner class BindViewHolderList(private val binding: ItemPokemonBinding) : BaseListViewHolder<Pokemon>(binding.root) {

        override fun bind(item: Pokemon, position: Int) = with(binding) {

            tvPokemonName.text = item.name
            ivPokemonImage.load(item.image)

            clPokemonItem.setOnClickListener {
                onPokemonClickListener?.let { click ->
                    click(item)
                }
            }
        }
    }

    private var onPokemonClickListener: ((Pokemon) -> Unit)? = null

    fun setPokemonClickListener(listener: (Pokemon) -> Unit) {
        onPokemonClickListener = listener
    }

}