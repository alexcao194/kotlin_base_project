package com.example.test.presentation.screens.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.test.configs.navigation.NavigationItem

@Composable
fun HomeScreen(
    navController: NavController
) {
    Scaffold { innerPadding ->
        Column {
            Text(
                text = "Hello from HomeScreen!",
                modifier = Modifier.padding(innerPadding),
            )
            Button(onClick = {
                openDetailScreen(navController = navController)
            }) {
                Text(text = "Go to DetailScreen")
            }
        }
    }
}

fun openDetailScreen(navController: NavController) {
    navController.navigate(NavigationItem.Detail.route)
}