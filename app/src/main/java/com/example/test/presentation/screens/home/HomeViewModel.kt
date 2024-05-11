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

    fun insertData () {
        _homeUiState.update {
            it.copy(
                insertError = null,
                isWaitingInsert = true
            )
        }

        if (idInput.isEmpty() || nameInput.isEmpty()) {
            _homeUiState.update {
                it.copy(
                    insertError = "Please fill all fields",
                    isWaitingInsert = false
                )
            }
            return
        }

        val id = idInput.toIntOrNull()

        if (id == null) {
            _homeUiState.update {
                it.copy(
                    insertError = "Id must be a number",
                    isWaitingInsert = false
                )
            }
            return
        }

        viewModelScope.launch {
            insertDataUseCase.execute(
                Data(
                    id = idInput.toInt(),
                    name = nameInput
                )
            )

            _homeUiState.update {
                it.copy(
                    isWaitingInsert = false
                )
            }
        }
    }

    fun getDataSuspending () {

    }
}