package com.me.pokemon.presentation.view.state

sealed class ViewState<out T>() {
    data object Loading: ViewState<Nothing>()
    data class Success<T>(val data:T): ViewState<T>()
    data class Error(val e: Throwable): ViewState<Nothing>()
}