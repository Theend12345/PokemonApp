package com.me.data.util

import com.me.data.local.entity.ItemEntity
import com.me.data.remote.model.description.ItemResponseModel
import com.me.data.remote.model.list.ResultRemoteModel
import com.me.domain.model.ItemModel

fun ArrayList<ResultRemoteModel>.toListOfItemModel(): List<ItemModel> =
    this.map {
        it.toItemModel()
    }

fun ResultRemoteModel.toItemModel(): ItemModel = ItemModel(
    name = name, sprites = spriteUrl()
)

fun ResultRemoteModel.toItemEntity(): ItemEntity = ItemEntity(
    name = name, sprites = spriteUrl()
)

fun ItemEntity.toItemModel() = ItemModel(
    babyTriggerFor = babyTriggerFor, cost = cost,
    flingPower = flingPower,
    name = name,
    sprites = sprites,
    id = id
)

fun ItemResponseModel.toItemModel() = ItemModel(
    name = name,
    cost = cost,
    flingPower = flingPower,
    sprites = sprites?.default,
)

fun ArrayList<ResultRemoteModel>.toListOfItemEntity(): List<ItemEntity> =
    this.map {
        it.toItemEntity()
    }


