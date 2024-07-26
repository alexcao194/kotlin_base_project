package com.alexcao.baseproject.presentation.screens.home

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.alexcao.baseproject.configs.navigation.NavigationItem
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val homeUiState = viewModel.homeUiState.collectAsState()
    val dataState = homeUiState.value.hobbyState
    val isWaitingInsert = homeUiState.value.isWaitingInsert
    val idInput = viewModel.idInput
    val nameInput = viewModel.nameInput
    val insertError = homeUiState.value.insertError
    val scope = rememberCoroutineScope()
    val snackBarHostState = remember { SnackbarHostState() }
    Scaffold(
        snackbarHost = {
            SnackbarHost(
                hostState = snackBarHostState
            )
        }
    ) { innerPadding ->
        Box {
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .padding(16.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "Hobbies")
                if (homeUiState.value.hobbyState.isEmpty()) {
                    Text(text = "No hobbies found")
                } else {
                    Column {
                        dataState.forEach {
                            Text(text = it.name)
                        }
                    }
                }

                TextField(
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number
                    ),
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
                    viewModel.insertData()
                }) {
                    Text(text = "Insert Hobby")
                }

                Button(onClick = {
                    viewModel.getHobbies()
                }) {
                    Text(text = "Get Hobbies")
                }

                Button(onClick = {
                    openDetailScreen(navController = navController)
                }) {
                    Text(text = "Go to Detail Screen")
                }
            }

            if (isWaitingInsert) {
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    Dialog(onDismissRequest = { }) {
                        Text(text = "Loading...")
                    }
                }
            }
//
//            if (homeUiState.value.insertError != null) {
//                val dialogTitle = "Error"
//                val dialogText = homeUiState.value.insertError ?: ""
//                val onDismissRequest = {
//                    viewModel.clearError()
//                }
//                val onConfirmation = {
//                    viewModel.clearError()
//                }
//                Surface(
//                    modifier = Modifier,
//                ) {
//                    AlertDialog(
//                        title = {
//                            Text(text = dialogTitle)
//                        },
//                        text = {
//                            Text(text = dialogText)
//                        },
//                        onDismissRequest = {
//                            onDismissRequest()
//                        },
//                        confirmButton = {
//                            TextButton(
//                                onClick = {
//                                    onConfirmation()
//                                }
//                            ) {
//                                Text("Confirm")
//                            }
//                        },
//                        dismissButton = {
//                            TextButton(
//                                onClick = {
//                                    onDismissRequest()
//                                }
//                            ) {
//                                Text("Dismiss")
//                            }
//                        }
//                    )
//                }
//            }

            LaunchedEffect(insertError) {
                Log.d("HomeScreen", "Data - error: $insertError")
                if (insertError != null) {
                    scope.launch {
                        snackBarHostState.showSnackbar(
                            "Error: $insertError",
                        )
                    }
                    viewModel.clearError()
                }
            }
        }
    }
}

fun openDetailScreen(navController: NavController) {
    navController.navigate(NavigationItem.Detail.route)
}