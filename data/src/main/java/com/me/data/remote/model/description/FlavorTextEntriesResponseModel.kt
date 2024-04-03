package com.me.data.remote.model.description

import com.google.gson.annotations.SerializedName

data class FlavorTextEntriesResponseModel(
    @SerializedName("text") var text: String? = null
)
