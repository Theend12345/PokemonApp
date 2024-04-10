package com.me.data.remote.model.description

import com.google.gson.annotations.SerializedName

data class ItemResponseModel(
    @SerializedName("baby_trigger_for") var babyTriggerFor: String? = null,
    @SerializedName("cost") var cost: Int? = null,
    @SerializedName("fling_power") var flingPower: String? = null,
    @SerializedName("id") var id: Int? = null,
    @SerializedName("name") var name: String? = null,
    @SerializedName("sprites") var sprites: SpritesResponseModel? = SpritesResponseModel()
)