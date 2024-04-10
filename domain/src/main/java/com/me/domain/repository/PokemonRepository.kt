package com.me.domain.repository

import androidx.paging.PagingData
import com.me.domain.model.ItemModel
import kotlinx.coroutines.flow.Flow
interface PokemonRepository {

    fun getAllItemsPager(): Flow<PagingData<ItemModel>>
    suspend fun getTestItems(offset: Int, limit: Int): Flow<List<ItemModel>>
    suspend fun getItemByName(name: String): Flow<ItemModel>
}