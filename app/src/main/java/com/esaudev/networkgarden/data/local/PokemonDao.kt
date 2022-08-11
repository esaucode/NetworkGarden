package com.esaudev.networkgarden.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface PokemonDao {

    @Query("SELECT * FROM pokemonentity")
    fun getAllPokemon(): List<PokemonEntity>

    @Insert
    fun insertAllPokemon(vararg pokemon: PokemonEntity)

}