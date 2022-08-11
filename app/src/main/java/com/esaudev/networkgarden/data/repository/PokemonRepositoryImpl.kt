package com.esaudev.networkgarden.data.repository

import android.util.Log
import com.esaudev.networkgarden.data.local.PokemonDao
import com.esaudev.networkgarden.data.mapper.mapToDomain
import com.esaudev.networkgarden.data.mapper.mapToEntity
import com.esaudev.networkgarden.data.remote.PokemonApi
import com.esaudev.networkgarden.domain.model.Pokemon
import com.esaudev.networkgarden.domain.repository.PokemonRepository
import javax.inject.Inject

class PokemonRepositoryImpl @Inject constructor(
    private val pokemonDao: PokemonDao,
    private val pokemonApi: PokemonApi
): PokemonRepository {

    override suspend fun fetchPokemonList(): List<Pokemon> {
        return try {
            val request = pokemonApi.fetchFirstGeneration()

            if (request.isSuccessful) {
                request.body()?.results?.map { it.mapToDomain() }?: emptyList()
            } else {
                emptyList()
            }
        } catch (e: Exception) {
            Log.d("Test", e.toString())
            emptyList()
        }
    }

    override suspend fun insertPokemonList(pokemonList: List<Pokemon>) {
        pokemonDao.insertAllPokemon(*pokemonList.map { it.mapToEntity() }.toTypedArray())
    }

    override suspend fun getPokemonList(): List<Pokemon> {
        return pokemonDao.getAllPokemon().map { it.mapToDomain() }
    }
}