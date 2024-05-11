package com.example.test.presentation.screens.home

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.test.configs.navigation.NavigationItem
import com.example.test.presentation.screens.home.mdels.HomeUiState

@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val homeUiState = viewModel.homeUiState.collectAsState()
    val dataState = homeUiState.value.dataState
    val isWaitingInsert = homeUiState.value.isWaitingInsert
    val isWaitingData = homeUiState.value.isWaitingData
    val idInput = viewModel.idInput
    val nameInput = viewModel.nameInput


    Scaffold { innerPadding ->
        Box {
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .padding(16.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "Data")
                if (homeUiState.value.dataState.isEmpty()) {
                    Text(text = "No data")
                } else {
                    Column {
                        dataState.forEach {
                            Text(text = it.name)
                        }
                    }
                }

                TextField(
                    modifier = Modifier.padding(16.dp),
                    value = idInput,
                    label = {
                            Text(text = "ID")
                    },
                    onValueChange = {
                        viewModel.onIdInputChanged(it)
                    }
                )

                TextField(
                    modifier = Modifier.padding(16.dp),
                    value = nameInput,
                    label = {
                            Text(text = "Name")
                    },
                    onValueChange = {
                        viewModel.onNameInputChanged(it)
                    }
                )

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

            if (isWaitingData || isWaitingInsert) {
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