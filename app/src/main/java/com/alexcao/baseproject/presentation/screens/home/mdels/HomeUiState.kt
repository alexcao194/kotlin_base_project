package com.alexcao.baseproject.presentation.screens.home.mdels

import com.alexcao.baseproject.domain.entities.Hobby

data class HomeUiState (
    val hobbyState: List<Hobby> = emptyList(),
    val isWaitingData: Boolean = false,
    val dataError: String? = null,

    val isWaitingInsert: Boolean = false,
    val insertError: String? = null
)