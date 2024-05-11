package com.example.test.presentation.screens.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.test.core.response.Response
import com.example.test.core.response.asResponse
import com.example.test.domain.entities.Hobby
import com.example.test.domain.use_cases.GetHobbyUseCase
import com.example.test.domain.use_cases.InsertHobbyUseCase
import com.example.test.presentation.screens.home.mdels.HomeUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getHobbyUseCase: GetHobbyUseCase,
    private val insertHobbyUseCase: InsertHobbyUseCase
) : ViewModel() {

    private val _homeUiState: MutableStateFlow<HomeUiState> = MutableStateFlow(HomeUiState())
    val homeUiState: StateFlow<HomeUiState> = combine(
        _homeUiState,
        getHobbyUseCase.execute(Unit).asResponse()
    ) { uiState, data ->
        when (data) {
            is Response.Success -> {
                uiState.copy(
                    hobbyState = data.data,
                    isWaitingData = false,
                    dataError = null
                )
            }
            is Response.Error -> {
                uiState.copy(
                    isWaitingData = false,
                    dataError = data.exception.message
                )
            }
            is Response.Loading -> {
                uiState.copy(
                    isWaitingData = true,
                    dataError = null
                )
            }
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = HomeUiState()
    )

    var idInput by mutableStateOf("")
        private set

    fun onIdInputChanged(value: String) {
        idInput = value
    }

    var nameInput by mutableStateOf("")
        private set

    fun onNameInputChanged(value: String) {
        nameInput = value
    }

    fun insertData() {
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

        viewModelScope.launch {
            insertHobbyUseCase.execute(
                Hobby(
                    id = idInput,
                    name = nameInput
                )
            )

            _homeUiState.update {
                it.copy(
                    isWaitingInsert = false
                )
            }

            idInput = ""
            nameInput = ""
        }
    }
}