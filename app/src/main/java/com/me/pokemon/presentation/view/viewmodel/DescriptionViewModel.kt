package com.me.pokemon.presentation.view.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.me.domain.model.ItemModel
import com.me.domain.usecase.GetItemUsecase
import com.me.pokemon.presentation.view.state.ViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DescriptionViewModel @Inject constructor(
    private val getItemUsecase: GetItemUsecase
) : ViewModel() {
    private val _data: MutableStateFlow<ViewState<ItemModel>> =
        MutableStateFlow(ViewState.Loading)
    val data: StateFlow<ViewState<ItemModel>>
        get() = _data.asStateFlow()

    fun loadData(name: String) {
        viewModelScope.launch {
            try {
                getItemUsecase(name).collect {
                    _data.value = ViewState.Success(it)
                }
            } catch (e: Throwable) {
                _data.value = ViewState.Error(e)
            }
        }
    }
}