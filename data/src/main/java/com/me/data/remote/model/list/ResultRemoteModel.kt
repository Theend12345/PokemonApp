package com.me.data.remote.model.list

import com.google.gson.annotations.SerializedName

data class ResultRemoteModel(
    @SerializedName("name") var name: String? = null
){
    fun spriteUrl() = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/items/${name}.png"
}
