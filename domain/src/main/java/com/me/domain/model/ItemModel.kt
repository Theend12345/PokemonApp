package com.me.domain.model

data class ItemModel(
    var babyTriggerFor: String? = null,
    var cost: Int? = null,
    var effectEntries: ArrayList<EffectEntriesModel> = arrayListOf(),
    var flavorTextEntries: ArrayList<String> = arrayListOf(),
    var flingEffect: String? = null,
    var flingPower: String? = null,
    var heldByPokemon: ArrayList<String> = arrayListOf(),
    var id: Int? = null,
    var machines: ArrayList<String> = arrayListOf(),
    var name: String? = null,
    var sprites: String? = null
)
