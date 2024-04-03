package com.me.data.di

import com.me.data.remote.api.PokemonService
import com.me.data.repository.PokemonRepositoryImp
import com.me.domain.repository.PokemonRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

private const val BASE_URL = "https://pokeapi.co/api/v2/"

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    @Singleton
    fun providePokemonService(): PokemonService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PokemonService::class.java)
    }

    @Provides
    @Singleton
    fun provideRepository(pokemonService: PokemonService): PokemonRepository =
        PokemonRepositoryImp(pokemonService)
}