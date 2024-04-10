package com.me.data.repository

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.map
import com.me.data.local.db.PokemonDatabase
import com.me.data.paging.PokemonRemoteMediator
import com.me.data.remote.api.PokemonService
import com.me.data.util.toItemModel
import com.me.data.util.toListOfItemModel
import com.me.domain.model.ItemModel
import com.me.domain.repository.PokemonRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class PokemonRepositoryImp(
    private val pokemonService: PokemonService,
    private val database: PokemonDatabase
) : PokemonRepository {

    @OptIn(ExperimentalPagingApi::class)
    override fun getAllItemsPager() = Pager(
        config = PagingConfig(pageSize = 30),
        remoteMediator = PokemonRemoteMediator(database, pokemonService),
        pagingSourceFactory = { database.pokemonDao().getItems() }
    ).flow.map {
        it.map {
            it.toItemModel()
        }
    }
    override suspend fun getTestItems(offset: Int, limit: Int): Flow<List<ItemModel>> =
        withContext(Dispatchers.IO) {
            flow {
                emit(pokemonService.getTestItems(offset, limit).results.toListOfItemModel())
            }
        }

    override suspend fun getItemByName(name: String): Flow<ItemModel> = withContext(Dispatchers.IO){
        flow {
            emit(pokemonService.getItemByName(name).toItemModel())
        }
    }

}