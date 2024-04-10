package com.me.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.me.data.local.entity.ItemEntity

@Database(entities = [ItemEntity::class], version = 1)
abstract class PokemonDatabase(): RoomDatabase() {
    abstract fun pokemonDao(): PokemonDao
}