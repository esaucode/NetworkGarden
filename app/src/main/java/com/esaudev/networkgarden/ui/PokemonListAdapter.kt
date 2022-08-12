package com.esaudev.networkgarden.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.esaudev.networkgarden.databinding.ItemPokemonBinding
import com.esaudev.networkgarden.domain.extension.load
import com.esaudev.networkgarden.domain.model.Pokemon
import java.util.function.Predicate

class PokemonListAdapter(

): ListAdapter<Pokemon, BaseListViewHolder<*>>(DiffUtilCallback) {

    private object DiffUtilCallback : DiffUtil.ItemCallback<Pokemon>() {
        override fun areItemsTheSame(oldItem: Pokemon, newItem: Pokemon): Boolean = oldItem.image == newItem.image
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

            ivSelected.visibility = if (item.isSelected) View.VISIBLE else View.GONE
            vSelectedBackground.visibility = if (item.isSelected) View.VISIBLE else View.GONE

            clPokemonItem.setOnClickListener {
                onPokemonClickListener?.let { action ->
                    if (_actionModeEnabled.value == true) {
                        currentList[position].isSelected = !item.isSelected
                        if (currentList.none { it.isSelected }) {
                            _actionModeEnabled.value = false
                        }
                        action(item)
                        notifyItemChanged(position)
                    } else {
                        action(item)
                    }
                }
            }

            clPokemonItem.setOnLongClickListener {
                onPokemonLongClickListener?.let { action ->
                    if (!_actionModeEnabled.value!!) {
                        _actionModeEnabled.value = true
                        currentList[position].isSelected = !item.isSelected
                        action(item)
                        notifyItemChanged(position)
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

    fun disableActionMode() {
        _actionModeEnabled.value = false
        currentList.onEach { it.isSelected = false }
        notifyItemRangeChanged(0, currentList.size)
    }

    fun deleteSelection() {
        val selection = currentList.filter { !it.isSelected }.toMutableList()
        submitList(selection) {
            disableActionMode()
        }
    }

}