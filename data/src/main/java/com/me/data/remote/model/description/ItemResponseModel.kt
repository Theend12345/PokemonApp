package com.me.data.remote.model.description

import com.google.gson.annotations.SerializedName

data class ItemResponseModel(
    @SerializedName("baby_trigger_for") var babyTriggerFor: String? = null,
    @SerializedName("cost") var cost: Int? = null,
    @SerializedName("effect_entries") var effectEntries: ArrayList<EffectEntriesResponseModel> = arrayListOf(),
    @SerializedName("flavor_text_entries") var flavorTextEntries: ArrayList<FlavorTextEntriesResponseModel> = arrayListOf(),
    @SerializedName("fling_effect") var flingEffect: String? = null,
    @SerializedName("fling_power") var flingPower: String? = null,
    @SerializedName("held_by_pokemon") var heldByPokemon: ArrayList<String> = arrayListOf(),
    @SerializedName("id") var id: Int? = null,
    @SerializedName("machines") var machines: ArrayList<String> = arrayListOf(),
    @SerializedName("name") var name: String? = null,
    @SerializedName("sprites") var sprites: SpritesResponseModel? = SpritesResponseModel()
)