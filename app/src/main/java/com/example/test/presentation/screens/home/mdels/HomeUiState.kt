package com.example.test.presentation.screens.home.mdels

import com.example.test.domain.entities.Data

data class HomeUiState (
    val dataState: List<Data> = emptyList(),
    val isWaitingData: Boolean = false,
    val dataError: String? = null,

    val isWaitingInsert: Boolean = false,
    val insertError: String? = null
)