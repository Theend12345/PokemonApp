package com.me.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "item")
data class ItemEntity(
    var babyTriggerFor: String? = null,
    var cost: Int? = null,
    var flingEffect: String? = null,
    var flingPower: String? = null,
    var name: String? = null,
    var sprites: String? = null
){
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}