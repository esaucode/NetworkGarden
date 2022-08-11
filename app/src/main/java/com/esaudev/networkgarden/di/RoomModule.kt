package com.esaudev.networkgarden.di

import android.content.Context
import androidx.room.Room
import com.esaudev.networkgarden.data.local.PokemonDatabase
import com.esaudev.networkgarden.data.local.RoomConstants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {


    @Provides
    @Singleton
    fun providePokemonDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(context, PokemonDatabase::class.java, RoomConstants.POKEMON_DATABASE).build()


    @Provides
    @Singleton
    fun providePokemonDao(db: PokemonDatabase) = db.pokemonDao()

}