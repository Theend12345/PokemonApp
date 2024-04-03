package com.me.data.util

import com.me.data.remote.model.list.ResultRemoteModel
import com.me.domain.model.ItemModel

fun ArrayList<ResultRemoteModel>.toListOfItemModel(): List<ItemModel> =
    this.map {
        it.toItemModel()
    }

fun ResultRemoteModel.toItemModel(): ItemModel = ItemModel(
    name = name, sprites = spriteUrl()
)
