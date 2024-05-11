package com.example.test.presentation.screens.home.mdels

import com.example.test.domain.entities.Hobby

data class HomeUiState (
    val hobbyState: List<Hobby> = emptyList(),
    val isWaitingData: Boolean = false,
    val dataError: String? = null,

    val isWaitingInsert: Boolean = false,
    val insertError: String? = null
)