package com.esaudev.networkgarden.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.esaudev.networkgarden.databinding.ActivityMainBinding
import com.esaudev.networkgarden.ui.nested.MoviesCatalogListAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val pokemonListAdapter = PokemonListAdapter()
    private val moviesCatalogListAdapter = MoviesCatalogListAdapter()

    private val viewModel: MainViewModel by viewModels()

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        initRecyclerView()
        subscribeObservers()
        viewModel.getMoviesCatalog()
    }

    private fun initRecyclerView() {
        binding.moviesCatalog.apply {
            adapter = moviesCatalogListAdapter
        }

        moviesCatalogListAdapter.setMovieClickListener {
            Toast.makeText(this, it.name, Toast.LENGTH_SHORT).show()
        }
    }

    private fun subscribeObservers() {
        viewModel.catalogList.observe(this) {
            moviesCatalogListAdapter.submitList(it)
        }
    }
}