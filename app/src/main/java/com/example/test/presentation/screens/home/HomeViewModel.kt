package com.example.test.presentation.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.test.core.response.Response
import com.example.test.core.response.asResponse
import com.example.test.domain.entities.Data
import com.example.test.domain.use_cases.GetDataUseCase
import com.example.test.domain.use_cases.InsertDataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getDataUseCase: GetDataUseCase,
    private val insertDataUseCase: InsertDataUseCase
) : ViewModel() {

    private val _dataState: MutableStateFlow<List<Data>> = MutableStateFlow(emptyList())
    val dataState: StateFlow<List<Data>> = _dataState

    private val _isWaitingData: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val isWaitingData: StateFlow<Boolean> = _isWaitingData

    private val _iswWaitingInsert: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val isWaitingInsert: StateFlow<Boolean> = _iswWaitingInsert

    private val _dataError: MutableStateFlow<String> = MutableStateFlow("")
    val dataError: StateFlow<String> = _dataError

    private val _insertError: MutableStateFlow<String> = MutableStateFlow("")
    val insertError: StateFlow<String> = _insertError

    init {
        getData()
    }

    private fun getData () {
        getDataUseCase.execute(Unit).stateIn(
            scope = viewModelScope,
            initialValue = emptyList(),
            started = SharingStarted.WhileSubscribed(5000)
        )
            .asResponse()
            .map { response ->
                _isWaitingData.value = false
                _dataError.value = ""
                when (response) {
                    is Response.Success -> {
                        _dataState.value = response.data
                    }
                    is Response.Error -> {
                        _dataError.value = response.exception.message ?: "An error occurred"
                    }
                    Response.Loading -> {
                        _isWaitingData.value = true
                    }
                }
            }
    }

    fun insertRandomData () {
        viewModelScope.launch {
            _iswWaitingInsert.value = true
            _insertError.value = ""
            try {
                insertDataUseCase.execute(Data(Random(100).nextInt(), "Data ${Random(100).nextInt()}"))
            } catch (e: Exception) {
                _insertError.value = e.message ?: "An error occurred"
            } finally {
                _iswWaitingInsert.value = false
            }
        }
    }

    fun insertWithInvalidData() {
        _insertError.value = "Invalid data"
    }

    fun getDataSuspending () {

    }
}