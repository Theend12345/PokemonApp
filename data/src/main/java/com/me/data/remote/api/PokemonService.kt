package com.me.data.remote.api

import com.me.data.remote.model.description.ItemResponseModel
import com.me.data.remote.model.list.ItemListRemoteModel
import com.me.domain.model.ItemModel
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokemonService {
    @GET("item/")
    suspend fun getTestItems(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
    ): ItemListRemoteModel
    @GET("item/{name}")
    suspend fun getItemByName(@Path("name") name: String): ItemResponseModel
}