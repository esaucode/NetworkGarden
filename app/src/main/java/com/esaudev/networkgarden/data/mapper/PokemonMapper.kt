package com.esaudev.networkgarden.data.mapper

import com.esaudev.networkgarden.data.local.PokemonEntity
import com.esaudev.networkgarden.data.remote.PokemonDto
import com.esaudev.networkgarden.domain.model.Pokemon

fun PokemonDto.mapToDomain(): Pokemon {
    return Pokemon(
        name = name,
        image = getPokemonImage(url)
    )
}

fun PokemonDto.mapToEntity(): PokemonEntity {
    return PokemonEntity(
        name = name,
        image = getPokemonImage(url)
    )
}

fun PokemonEntity.mapToDomain(): Pokemon {
    return Pokemon(
        name = name,
        image = image
    )
}

fun Pokemon.mapToEntity(): PokemonEntity {
    return PokemonEntity(
        name = name,
        image = image
    )
}

private fun getPokemonImage(url: String): String {
    val pokemonId = url.replace("/", "").removePrefix("https:pokeapi.coapiv2pokemon")
    return "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/$pokemonId.png"
}