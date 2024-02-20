package com.example.ep_2023_2024.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import com.example.ep_2023_2024.model.Antwort
import android.widget.Toast
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext

@Composable
fun QuizScreen() {
    val context = LocalContext.current
    // Annahme, dass wir eine Liste von Fragen und Antworten haben
    val questions = listOf(
        "Wie viele Kalorien hat ein Liter Wasser?",
        // ... weitere Fragen
    )
    val answers = listOf(
        "2", "500", "0", "10.000"
        // ... weitere Antworten
    )
    val currentQuestion = questions.first() // Erste Frage als Beispiel
    val userAnswer = remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "Allgemeine Ernährungsfragen", style = MaterialTheme.typography.headlineSmall)
        Text(text = "Teilaufgabe Wasser Trinken", Modifier.padding(bottom = 8.dp))
        Text(text = currentQuestion, Modifier.padding(bottom = 8.dp))
        LazyColumn {
            items(answers) { answer ->
                Button(onClick = {
                    // Hier wird die Toast-Nachricht ausgegeben, wenn der Button geklickt wird
                    Toast.makeText(context, "Antwort ausgewählt: $answer", Toast.LENGTH_SHORT).show()
                }) {
                    Text(text = answer)
                }
            }
        }
        BasicTextField(
            value = userAnswer.value,
            onValueChange = { userAnswer.value = it },
            modifier = Modifier.padding(top = 8.dp, bottom = 8.dp)
        )
        Button(onClick = { /* TODO: Antwort überprüfen und Feedback geben */ }) {
            Text(text = "Antwort überprüfen")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    QuizScreen()
}
