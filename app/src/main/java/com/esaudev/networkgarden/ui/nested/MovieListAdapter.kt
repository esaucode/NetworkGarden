package com.esaudev.networkgarden.ui.nested

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.esaudev.networkgarden.databinding.ItemMovieBinding
import com.esaudev.networkgarden.domain.model.Movie
import com.esaudev.networkgarden.ui.BaseListViewHolder

class MovieListAdapter(

): ListAdapter<Movie, BaseListViewHolder<*>>(DiffUtilCallback) {

    private object DiffUtilCallback : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean = oldItem.name == newItem.name
        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean = oldItem == newItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseListViewHolder<*> {
        val itemBinding = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BindViewHolderList(itemBinding)
    }

    override fun onBindViewHolder(holder: BaseListViewHolder<*>, position: Int) {
        when (holder) {
            is BindViewHolderList -> holder.bind(getItem(position), position)
        }
    }

    inner class BindViewHolderList(private val binding: ItemMovieBinding) : BaseListViewHolder<Movie>(binding.root) {

        override fun bind(item: Movie, position: Int) = with(binding) {

            movieImage.setImageResource(item.imageRes)

            movieImage.setOnClickListener {
                onMovieClickListener?.let { click ->
                    click(item)
                }
            }
        }
    }

    private var onMovieClickListener: ((Movie) -> Unit)? = null

    fun setMovieClickListener(listener: (Movie) -> Unit) {
        onMovieClickListener = listener
    }

}