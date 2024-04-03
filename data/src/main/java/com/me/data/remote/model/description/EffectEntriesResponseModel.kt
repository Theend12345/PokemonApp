package com.me.data.remote.model.description

import com.google.gson.annotations.SerializedName

data class EffectEntriesResponseModel(
    @SerializedName("effect") var effect: String? = null,
    @SerializedName("short_effect") var shortEffect: String? = null
)