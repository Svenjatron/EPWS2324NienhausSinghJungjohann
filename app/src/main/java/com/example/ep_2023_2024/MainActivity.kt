package com.example.ep_2023_2024

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.ep_2023_2024.ui.theme.EP_2023_2024Theme
import LoginScreen
import com.example.ep_2023_2024.model.FirebaseHelper
import com.google.firebase.FirebaseApp
import QuizScreen
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import InteressenFrageScreen
import com.example.ep_2023_2024.view.SchuelerTagsScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FirebaseApp.initializeApp(this)
        setContent {
            EP_2023_2024Theme {
                val navController = rememberNavController()
                val firebaseHelper = FirebaseHelper(this)

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavHost(navController = navController, startDestination = "login") {
                        composable("login") {
                            LoginScreen(firebaseHelper = firebaseHelper) { schuelerId ->

                                navController.navigate("interessenFrage/$schuelerId")
                            }
                        }
                        composable("interessenFrage/{schuelerId}") { backStackEntry ->
                            val schuelerId = backStackEntry.arguments?.getString("schuelerId") ?: return@composable
                            InteressenFrageScreen(firebaseHelper = firebaseHelper, schuelerId = schuelerId, navController = navController)
                        }
                        composable("SchuelerTagsScreen/{schuelerId}") { backStackEntry ->

                            val schuelerId = backStackEntry.arguments?.getString("schuelerId") ?: return@composable
                            SchuelerTagsScreen(firebaseHelper, schuelerId)
                        }

                    }
                }
            }
        }
    }
}
