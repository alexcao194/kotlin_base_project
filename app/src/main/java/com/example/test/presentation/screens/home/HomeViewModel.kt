package com.example.test.presentation.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.test.domain.entities.Data
import com.example.test.domain.use_cases.GetDataUseCase
import com.example.test.domain.use_cases.SuspendGetDataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getDataUseCase: GetDataUseCase,
    private val suspendGetDataUseCase: SuspendGetDataUseCase
) : ViewModel() {

    private val dataState: MutableStateFlow<Data> = MutableStateFlow(Data())
    val data: StateFlow<Data> = dataState

    private val isLoading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> = isLoading

    fun getData () {
        dataState.value = getDataUseCase.execute(Unit)
    }

    fun getDataSuspending () {
        viewModelScope.launch {
            isLoading.value = true
            dataState.value = suspendGetDataUseCase.execute(Unit)
            isLoading.value = false
        }
    }
}