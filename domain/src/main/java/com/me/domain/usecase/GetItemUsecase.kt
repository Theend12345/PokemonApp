package com.me.domain.usecase

import com.me.domain.repository.PokemonRepository
import javax.inject.Inject

class GetItemUsecase @Inject constructor(private val repository: PokemonRepository) {
    suspend operator fun invoke(name: String) = repository.getItemByName(name)
}