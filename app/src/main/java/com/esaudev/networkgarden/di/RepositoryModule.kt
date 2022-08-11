package com.esaudev.networkgarden.di

import com.esaudev.networkgarden.data.local.PokemonDao
import com.esaudev.networkgarden.data.remote.PokemonApi
import com.esaudev.networkgarden.data.repository.PokemonRepositoryImpl
import com.esaudev.networkgarden.domain.repository.PokemonRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun providePokemonRepository(
        pokemonDao: PokemonDao,
        pokemonApi: PokemonApi
    ): PokemonRepository = PokemonRepositoryImpl(pokemonDao, pokemonApi)

}