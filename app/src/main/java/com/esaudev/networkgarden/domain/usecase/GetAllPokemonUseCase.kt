package com.esaudev.networkgarden.domain.usecase

import com.esaudev.networkgarden.domain.model.Pokemon
import com.esaudev.networkgarden.domain.repository.PokemonRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetAllPokemonUseCase @Inject constructor(
    private val repository: PokemonRepository
) {

    suspend operator fun invoke(): List<Pokemon> {
        return withContext(Dispatchers.IO) {

            if (repository.getPokemonList().isEmpty()) {
                val networkPokemonList = repository.fetchPokemonList()
                repository.insertPokemonList(networkPokemonList)
            }

            return@withContext repository.getPokemonList()
        }

    }

}