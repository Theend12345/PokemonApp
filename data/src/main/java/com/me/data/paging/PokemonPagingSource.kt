package com.me.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.me.data.remote.api.PokemonService
import com.me.data.remote.model.list.ResultRemoteModel
import retrofit2.HttpException
import java.io.IOException

class PokemonPagingSource(private val pokemonService: PokemonService) :
    PagingSource<Int, ResultRemoteModel>() {
    override fun getRefreshKey(state: PagingState<Int, ResultRemoteModel>): Int? {
        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(it)?.nextKey?.minus(1)
        }
    }
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ResultRemoteModel> {
        return try {
            val pageNumber = params.key ?: 0
            val responseItems = pokemonService.getTestItems(20, 1000)
            val nextPage = if (responseItems.results.isNotEmpty()) pageNumber + 1 else null
            val prevPage = if (pageNumber > 0) pageNumber - 1 else null
            LoadResult.Page(
                data = responseItems.results,
                prevKey = prevPage,
                nextKey = nextPage
            )
        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        }
    }
}