package com.example.test.presentation.screens.detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController

@Composable
fun DetailScreen(
    navController: NavController
) {
    Scaffold {scaffoldPadding ->
        Box(modifier = Modifier.padding(scaffoldPadding))
    }
}