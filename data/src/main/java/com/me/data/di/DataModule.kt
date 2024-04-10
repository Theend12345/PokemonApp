package com.me.data.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.me.data.local.db.PokemonDatabase
import com.me.data.remote.api.PokemonService
import com.me.data.repository.PokemonRepositoryImp
import com.me.domain.repository.PokemonRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

private const val BASE_URL = "https://pokeapi.co/api/v2/"
private const val DB_NAME = "pokemon-db"
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
    fun providePokemonDatabase(@ApplicationContext context: Context): PokemonDatabase =
        Room.databaseBuilder(context,PokemonDatabase::class.java,DB_NAME).build()

    @Provides
    @Singleton
    fun provideRepository(pokemonService: PokemonService, database: PokemonDatabase): PokemonRepository =
        PokemonRepositoryImp(pokemonService, database)
}