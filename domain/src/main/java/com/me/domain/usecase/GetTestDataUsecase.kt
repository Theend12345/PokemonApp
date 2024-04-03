package com.me.domain.usecase

import com.me.domain.repository.PokemonRepository
import javax.inject.Inject

class GetTestDataUsecase @Inject constructor(private val repository: PokemonRepository) {
    suspend operator fun invoke() = repository.getTestItems(20,100)
}