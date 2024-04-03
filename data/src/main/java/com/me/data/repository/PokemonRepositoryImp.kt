package com.me.data.repository

import com.me.data.remote.api.PokemonService
import com.me.data.util.toListOfItemModel
import com.me.domain.model.ItemModel
import com.me.domain.repository.PokemonRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext

class PokemonRepositoryImp(private val pokemonService: PokemonService) : PokemonRepository {
    override suspend fun getTestItems(offset: Int, limit: Int): Flow<List<ItemModel>> =
        withContext(Dispatchers.IO) {
            flow {
                emit(pokemonService.getTestItems(offset, limit).results.toListOfItemModel())
            }
        }

}