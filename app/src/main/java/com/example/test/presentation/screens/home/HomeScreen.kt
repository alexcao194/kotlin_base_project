package com.example.test.presentation.screens.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.test.configs.navigation.NavigationItem

@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val dataState = viewModel.data.collectAsState()
    val isLoading = viewModel.loading.collectAsState()
    Scaffold { innerPadding ->
        Box {
            Column(
                modifier = Modifier.padding(innerPadding)
            ) {
                Text(text = "Data: ${dataState.value.name}")
                Text(text = "id: ${dataState.value.id}")
                Button(onClick = {
                    viewModel.getData()
                }) {
                    Text(text = "Get Data")
                }
                Button(onClick = {
                    viewModel.getDataSuspending()
                }) {
                    Text(text = "Get Data Suspending")
                }
                Button(onClick = {
                    openDetailScreen(navController = navController)
                }) {
                    Text(text = "Go to Detail Screen")
                }
            }

            if (isLoading.value) {
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    Dialog(onDismissRequest = { }) {
                        Text(text = "Loading...")
                    }
                }
            }
        }
    }
}

fun openDetailScreen(navController: NavController) {
    navController.navigate(NavigationItem.Detail.route)
}