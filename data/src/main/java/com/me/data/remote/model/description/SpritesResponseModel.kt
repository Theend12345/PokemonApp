package com.me.data.remote.model.description

import com.google.gson.annotations.SerializedName

data class SpritesResponseModel(
    @SerializedName("default" ) var default : String? = null
)
