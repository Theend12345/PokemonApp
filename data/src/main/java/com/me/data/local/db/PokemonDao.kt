package com.me.data.local.db

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Upsert
import com.me.data.local.entity.ItemEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface PokemonDao {
    @Query("SELECT * FROM item")
    fun getItems(): PagingSource<Int, ItemEntity>
    @Query("SELECT * FROM item WHERE id=:id")
    fun getItemById(id: Int): Flow<ItemEntity>
    @Query("SELECT * FROM item WHERE name=:name")
    fun getItemByName(name: String): Flow<List<ItemEntity>>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addItem(itemEntity: ItemEntity)
    @Upsert
    fun addItems(itemEntities: List<ItemEntity>)
    @Query("DELETE FROM item")
    fun clear()
}