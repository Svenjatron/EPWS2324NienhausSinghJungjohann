package com.example.ep_2023_2024.view
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.material3.Button
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.runtime.Composable
import androidx.navigation.NavController




@Composable
fun HomeButton(navController: NavController) {
    Button(
        onClick = {
            navController.navigate("login") {
                popUpTo("login") { inclusive = true }
            }
        },
        modifier = Modifier
            .wrapContentSize(Alignment.Center)
    ) {
        Text("Home")
    }
}
