package com.me.pokemon.presentation.view.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.me.domain.model.ItemModel
import com.me.domain.usecase.GetItemsPagerUsecase
import com.me.pokemon.presentation.view.state.ViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getItemsPagerUsecase: GetItemsPagerUsecase
) : ViewModel() {

    private val _data: MutableStateFlow<ViewState<PagingData<ItemModel>>> =
        MutableStateFlow(ViewState.Loading)
    val data: StateFlow<ViewState<PagingData<ItemModel>>>
        get() = _data.asStateFlow()

    fun loadData() {
        viewModelScope.launch {
            try {
                val itemPagingData = getItemsPagerUsecase().cachedIn(viewModelScope)
                itemPagingData.collect {
                    _data.value = ViewState.Success(it)
                }
            } catch (e: Throwable) {
                loadError(e)
            }
        }
    }

    fun loadError(e: Throwable){
        _data.value = ViewState.Error(e)
    }

}