package com.esaudev.networkgarden.di

import com.esaudev.networkgarden.data.remote.PokemonApi
import com.esaudev.networkgarden.data.remote.RetrofitConstants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .build()
    }

    @Provides
    @Singleton
    fun provideGsonConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }

    @Provides
    @Singleton
    fun providePokemonApi(
        client: OkHttpClient,
        converterFactory: GsonConverterFactory
    ): PokemonApi {
        return Retrofit.Builder()
            .baseUrl(RetrofitConstants.POKEMON_API_URL)
            .addConverterFactory(converterFactory)
            .client(client)
            .build()
            .create()
    }


}