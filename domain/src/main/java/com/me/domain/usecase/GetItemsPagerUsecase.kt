package com.me.domain.usecase

import com.me.domain.repository.PokemonRepository
import javax.inject.Inject

class GetItemsPagerUsecase @Inject constructor(private val repository: PokemonRepository) {
    operator fun invoke() = repository.getAllItemsPager()
}