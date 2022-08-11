package com.esaudev.networkgarden.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.esaudev.networkgarden.R
import com.esaudev.networkgarden.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val pokemonListAdapter = PokemonListAdapter()

    private val viewModel: MainViewModel by viewModels()

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        initRecyclerView()
        subscribeObservers()
        viewModel.getAllPokemon()
    }

    private fun initRecyclerView() {
        binding.pokemonList.apply {
            adapter = pokemonListAdapter
        }
    }

    private fun subscribeObservers() {
        viewModel.pokemonList.observe(this) {
            pokemonListAdapter.submitList(it)
        }
    }
}