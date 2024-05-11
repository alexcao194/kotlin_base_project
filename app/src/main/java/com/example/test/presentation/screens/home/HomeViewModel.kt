package com.example.test.presentation.screens.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.test.core.response.Response
import com.example.test.core.response.asResponse
import com.example.test.domain.entities.Data
import com.example.test.domain.use_cases.GetDataUseCase
import com.example.test.domain.use_cases.InsertDataUseCase
import com.example.test.presentation.screens.home.mdels.HomeUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getDataUseCase: GetDataUseCase,
    private val insertDataUseCase: InsertDataUseCase
) : ViewModel() {

    private val _homeUiState: MutableStateFlow<HomeUiState> = MutableStateFlow(HomeUiState())
    val homeUiState: StateFlow<HomeUiState> = _homeUiState.asStateFlow()

    var idInput by mutableStateOf("")
        private set

    fun onIdInputChanged (value: String) {
        idInput = value
    }

    var nameInput by mutableStateOf("")
        private set

    fun onNameInputChanged (value: String) {
        nameInput = value
    }

    init {
        getData()
    }

    private fun getData () {

    }

    fun insertRandomData () {

    }

    fun insertWithInvalidData() {
        _homeUiState.update { homeUiState ->
            homeUiState.copy(
                insertError = "This error is created by hand"
            )
        }
    }

    fun getDataSuspending () {

    }
}