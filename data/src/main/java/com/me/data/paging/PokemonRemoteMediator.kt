package com.me.data.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.me.data.local.db.PokemonDatabase
import com.me.data.local.entity.ItemEntity
import com.me.data.remote.api.PokemonService
import com.me.data.util.toListOfItemEntity
import retrofit2.HttpException
import java.io.IOException

@OptIn(ExperimentalPagingApi::class)
class PokemonRemoteMediator(
    private val database: PokemonDatabase,
    private val pokemonService: PokemonService
) : RemoteMediator<Int, ItemEntity>() {

    private val dao = database.pokemonDao()

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, ItemEntity>
    ): MediatorResult {
        return try {
            val loadKey = when (loadType) {
                LoadType.REFRESH -> 1
                LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
                LoadType.APPEND -> {
                    val lastItem = state.lastItemOrNull()
                    lastItem?.id?.plus(1) ?: 1
                }
            }

            val response = pokemonService.getTestItems(loadKey, 10)

            database.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    dao.clear()
                }
                dao.addItems(response.results.toListOfItemEntity())
            }
            MediatorResult.Success(endOfPaginationReached = response.results.isEmpty())
        } catch (e: IOException) {
            MediatorResult.Error(e)
        } catch (e: HttpException) {
            MediatorResult.Error(e)
        }
    }

}