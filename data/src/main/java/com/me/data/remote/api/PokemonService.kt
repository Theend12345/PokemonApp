package com.me.data.remote.api

import com.me.data.remote.model.list.ItemListRemoteModel
import retrofit2.http.GET
import retrofit2.http.Query

interface PokemonService {
    @GET("item/")
    suspend fun getTestItems(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
    ): ItemListRemoteModel
}