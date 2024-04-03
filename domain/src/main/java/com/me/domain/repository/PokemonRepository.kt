package com.me.domain.repository

import com.me.domain.model.ItemModel
import kotlinx.coroutines.flow.Flow
interface PokemonRepository {
    suspend fun getTestItems(offset: Int, limit: Int): Flow<List<ItemModel>>
}