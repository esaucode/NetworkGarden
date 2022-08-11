package com.esaudev.networkgarden.data.remote

import retrofit2.Response
import retrofit2.http.GET

interface PokemonApi {

    @GET("/api/v2/pokemon/?limit=151&offset=0")
    suspend fun fetchFirstGeneration(): Response<PokemonResponseWrapper<List<PokemonDto>>>

}