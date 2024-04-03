package com.me.data.remote.model.list

import com.google.gson.annotations.SerializedName

data class ItemListRemoteModel(
    @SerializedName("count") var count: Int? = null,
    @SerializedName("next") var next: String? = null,
    @SerializedName("previous") var previous: String? = null,
    @SerializedName("results") var results: ArrayList<ResultRemoteModel> = arrayListOf()
)