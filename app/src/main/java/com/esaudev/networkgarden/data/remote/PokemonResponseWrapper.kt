package com.esaudev.networkgarden.data.remote

data class PokemonResponseWrapper<out T>(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: T
)
