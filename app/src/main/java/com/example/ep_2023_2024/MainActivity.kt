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

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Firebase initialisieren
        FirebaseApp.initializeApp(this)
        setContent {
            EP_2023_2024Theme {
                // Nutzen Sie Surface, um den Hintergrund der gesamten App festzulegen
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // Hier erstellen Sie eine Instanz von FirebaseHelper
                    val firebaseHelper = FirebaseHelper(this)

                    // Rufen Sie LoginScreen auf und übergeben die notwendigen Parameter
                    QuizScreen()
                }
            }
        }
    }
}


