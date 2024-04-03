package com.me.pokemon.presentation.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.me.domain.model.ItemModel
import com.me.domain.usecase.GetTestDataUsecase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getTestDataUsecase: GetTestDataUsecase
) : ViewModel() {

    private val _data: MutableStateFlow<List<ItemModel>> = MutableStateFlow<List<ItemModel>>(emptyList())
    val data: StateFlow<List<ItemModel>>
        get() = _data.asStateFlow()

    fun fetchData(){
        viewModelScope.launch{
            getTestDataUsecase().collectLatest {
                _data.value = it
            }
        }
    }

}