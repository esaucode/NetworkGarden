package com.esaudev.networkgarden.domain.repository

import com.esaudev.networkgarden.domain.model.Pokemon

interface PokemonRepository {

    suspend fun fetchPokemonList(): List<Pokemon>

    suspend fun insertPokemonList(pokemonList: List<Pokemon>)

    suspend fun getPokemonList(): List<Pokemon>

}