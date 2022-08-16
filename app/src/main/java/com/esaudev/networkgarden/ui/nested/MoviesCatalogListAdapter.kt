package com.esaudev.networkgarden.ui.nested

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.esaudev.networkgarden.databinding.ItemMovieCatalogBinding
import com.esaudev.networkgarden.domain.extension.load
import com.esaudev.networkgarden.domain.model.Movie
import com.esaudev.networkgarden.domain.model.MovieCatalog
import com.esaudev.networkgarden.ui.BaseListViewHolder

class MoviesCatalogListAdapter(

): ListAdapter<MovieCatalog, BaseListViewHolder<*>>(DiffUtilCallback) {

    private object DiffUtilCallback : DiffUtil.ItemCallback<MovieCatalog>() {
        override fun areItemsTheSame(oldItem: MovieCatalog, newItem: MovieCatalog): Boolean = oldItem.catalog == newItem.catalog
        override fun areContentsTheSame(oldItem: MovieCatalog, newItem: MovieCatalog): Boolean = oldItem == newItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseListViewHolder<*> {
        val itemBinding = ItemMovieCatalogBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BindViewHolderList(itemBinding)
    }

    override fun onBindViewHolder(holder: BaseListViewHolder<*>, position: Int) {
        when (holder) {
            is BindViewHolderList -> holder.bind(getItem(position), position)
        }
    }

    inner class BindViewHolderList(private val binding: ItemMovieCatalogBinding) : BaseListViewHolder<MovieCatalog>(binding.root) {

        override fun bind(item: MovieCatalog, position: Int) = with(binding) {

            catalogName.text = item.catalog

            val movieListAdapter = MovieListAdapter()

            catalogMovies.apply {
                adapter = movieListAdapter
            }

            movieListAdapter.setMovieClickListener {
                onMovieClickListener?.let { click ->
                    click(it)
                }
            }

            movieListAdapter.submitList(item.movies)
        }
    }

    private var onMovieClickListener: ((Movie) -> Unit)? = null

    fun setMovieClickListener(listener: (Movie) -> Unit) {
        onMovieClickListener = listener
    }

}