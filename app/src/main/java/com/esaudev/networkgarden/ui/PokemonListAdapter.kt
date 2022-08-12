package com.esaudev.networkgarden.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.esaudev.networkgarden.R
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

            selectedBackground.visibility = if (item.isSelected) View.VISIBLE else View.GONE

            clPokemonItem.setOnClickListener {
                onPokemonClickListener?.let { action ->
                    if (_actionModeEnabled.value == true) {
                        currentList[position].isSelected = !currentList[position].isSelected
                        if (currentList.none { it.isSelected }) {
                            toggleActionMode(isEnabled = false)
                        }
                        notifyItemChanged(position)
                    } else {
                        action(item)
                    }
                }
            }

            clPokemonItem.setOnLongClickListener {

                onPokemonLongClickListener?.let { action ->
                    if (_actionModeEnabled.value == false) {
                        toggleActionMode(isEnabled = true)
                        currentList[position].isSelected = !currentList[position].isSelected
                        notifyItemChanged(position)
                        action(item)
                    }
                }

                true
            }
        }
    }

    private var onPokemonClickListener: ((Pokemon) -> Unit)? = null
    private var onPokemonLongClickListener: ((Pokemon) -> Unit)? = null

    private val _actionModeEnabled : MutableLiveData<Boolean> = MutableLiveData(false)
    val actionModeEnabled : LiveData<Boolean>
        get() = _actionModeEnabled

    fun setPokemonClickListener(listener: (Pokemon) -> Unit) {
        onPokemonClickListener = listener
    }

    fun setPokemonLongClickListener(listener: (Pokemon) -> Unit) {
        onPokemonLongClickListener = listener
    }

    fun toggleActionMode(isEnabled: Boolean) {
        _actionModeEnabled.value = isEnabled
    }

    fun disableActionMode() {
        toggleActionMode(isEnabled = false)
        currentList.onEach { it.isSelected = false }
        notifyDataSetChanged()
    }

    fun deleteSelection() {
        val listWithoutSelection = currentList.filter { !it.isSelected }.toMutableList()
        submitList(listWithoutSelection) {
            notifyItemRangeChanged(0, currentList.size)
        }
        toggleActionMode(isEnabled = false)
    }

}