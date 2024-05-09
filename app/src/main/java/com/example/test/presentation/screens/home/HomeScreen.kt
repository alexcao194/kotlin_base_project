package com.example.test.presentation.screens.home

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.test.configs.navigation.NavigationItem

@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val dataState = viewModel.dataState.collectAsState()
    val isWaitingDataState = viewModel.isWaitingData.collectAsState()
    val isWaitingInsert = viewModel.isWaitingInsert.collectAsState()
    val errorData = viewModel.dataError.collectAsState()
    val errorInsert = viewModel.insertError.collectAsState()
    SideEffect {
        if (errorData.value.isNotEmpty()) {
            Log.d("HomeScreen", "Error: ${errorData.value}")
        }
        if (errorInsert.value.isNotEmpty()) {
            Log.d("HomeScreen", "Error: ${errorInsert.value}")
        }
    }
    Scaffold { innerPadding ->
        Box {
            Column(
                modifier = Modifier.padding(innerPadding)
            ) {
                Column {
                    dataState.value.forEach {
                        Text(text = it.name)
                    }
                }
                Button(onClick = {
                    viewModel.insertRandomData()
                }) {
                    Text(text = "Insert Data")
                }
                Button(onClick = {
                    viewModel.insertWithInvalidData()
                }) {
                    Text(text = "Insert Invalid Data")
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

            if (isWaitingDataState.value || isWaitingInsert.value) {
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