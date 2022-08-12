package com.esaudev.networkgarden.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import com.esaudev.networkgarden.R
import com.esaudev.networkgarden.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlin.math.log

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
        initClickListeners()
        subscribeObservers()
        viewModel.getAllPokemon()
    }

    private fun initRecyclerView() {
        binding.pokemonList.apply {
            adapter = pokemonListAdapter
        }

        pokemonListAdapter.setPokemonClickListener {
            Toast.makeText(this, it.name, Toast.LENGTH_SHORT).show()
        }

        pokemonListAdapter.setPokemonLongClickListener {
            Toast.makeText(this, "Entrando a modo editar con ${it.name}", Toast.LENGTH_SHORT).show()
        }
    }

    private fun initClickListeners() {
        binding.backButton.setOnClickListener {
            pokemonListAdapter.disableActionMode()
        }

        binding.deleteButton.setOnClickListener {
            pokemonListAdapter.deleteSelection()
        }
    }

    private fun subscribeObservers() {
        viewModel.pokemonList.observe(this) {
            pokemonListAdapter.submitList(it)
        }

        pokemonListAdapter.actionModeEnabled.observe(this) { actionModeEnabled ->
            if (actionModeEnabled) {
                binding.actionModeToolbar.visibility = View.VISIBLE
            } else {
                binding.actionModeToolbar.visibility = View.GONE
            }
        }
    }
}