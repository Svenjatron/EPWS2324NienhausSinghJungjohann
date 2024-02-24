import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.ep_2023_2024.model.*
import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import android.content.Context


import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember

import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import com.example.ep_2023_2024.model.Antwort
import android.widget.Toast
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import com.example.ep_2023_2024.model.Schulklasse
import com.example.ep_2023_2024.model.Schueler
import com.example.ep_2023_2024.model.Teilaufgabe
import com.example.ep_2023_2024.model.Teilaufgabe_MC
import com.example.ep_2023_2024.model.Aufgabe
import com.example.ep_2023_2024.model.Antwort_MC

@Composable
fun QuizScreen(firebaseHelper: FirebaseHelper, schuelerId: String) {
    val context = LocalContext.current
    val schuelerTags = remember { mutableStateOf<List<String>>(listOf()) }
    val aufgabenListe = remember { mutableStateOf<List<Aufgabe>>(listOf()) }
    val fehlerNachricht = remember { mutableStateOf<String?>(null) }
    var displayTaskIndex by remember { mutableStateOf(0) }
    var selectedAnswers = remember { mutableStateOf<Set<String>>(emptySet()) }
    val isButtonPressed = remember { mutableStateOf(false) }
    val isTaskFinished = remember { mutableStateOf(false) }


    LaunchedEffect(schuelerId) {
        Log.d("QuizScreen", "Lade Schüler-Tags für Schüler-ID: $schuelerId")
        firebaseHelper.loadStudentTags(schuelerId, onSuccess = { tags ->
            Log.d("QuizScreen", "Schüler-Tags geladen: $tags")
            schuelerTags.value = tags
            // Aufruf von loadAllTasks
            firebaseHelper.loadAllTasks(onSuccess = { tasks ->
                val gefilterteAufgaben = getAufgabenByTags(tags, tasks)
                aufgabenListe.value = gefilterteAufgaben
                Log.d("QuizScreen", "Aufgaben geladen und gefiltert: ${gefilterteAufgaben.map { it.name }}")
            }, onError = { exception ->
                Log.e("QuizScreen", "Fehler beim Laden aller Aufgaben", exception)
                fehlerNachricht.value = "Fehler beim Laden aller Aufgaben: ${exception.message}"
            })
        }, onError = { exception ->
            Log.e("QuizScreen", "Fehler beim Laden der Schüler-Tags", exception)
            fehlerNachricht.value = "Fehler beim Laden der Schüler-Tags: ${exception.message}"
        })
    }

    AufgabenListeUI(aufgabenListe = aufgabenListe.value, context = context, schueler = Schueler(
        email = "",
        passwort = "",
        name = "",
        vorname = "",
        schulklasse = null,
        isEmailVerified = false,
        persönlichesInteresse = "",
        interessenListe = listOf()
    ), displayTaskIndex = displayTaskIndex, selectedAnswers = selectedAnswers, isButtonPressed = isButtonPressed, isTaskFinished = isTaskFinished)
}
@Composable
fun AufgabenListeUI(
    aufgabenListe: List<Aufgabe>,
    context: Context,
    schueler: Schueler,
    displayTaskIndex: Int,
    selectedAnswers: MutableState<Set<String>>,
    isButtonPressed: MutableState<Boolean>,
    isTaskFinished: MutableState<Boolean>
) {
    Column(modifier = Modifier.padding(16.dp)) {
        if (displayTaskIndex < aufgabenListe.size) {
            val aufgabe = aufgabenListe[displayTaskIndex]
            Text(text = aufgabe.name, style = MaterialTheme.typography.headlineMedium, modifier = Modifier.padding(bottom = 8.dp))
            // Überprüfen, ob es Teilaufgaben gibt und ob die displayTaskIndex innerhalb des Bereichs liegt
            if (aufgabe.teilaufgabenListe.isNotEmpty() && displayTaskIndex < aufgabe.teilaufgabenListe.size) {
                val currentSubtask = aufgabe.teilaufgabenListe[displayTaskIndex]
                if (currentSubtask is Teilaufgabe_MC) {
                    TeilaufgabeMCUI(
                        teilaufgabe = currentSubtask,
                        context = context,
                        initialSelectedAnswers = selectedAnswers, // Übergebe MutableState direkt
                        onAnswerSelected = { answers -> selectedAnswers.value = answers },
                        isPressed = isButtonPressed // Übergebe MutableState direkt
                    )
                }
            }
        }
    }
}

@Composable
fun TeilaufgabeMCUI(
    teilaufgabe: Teilaufgabe_MC,
    context: Context,
    initialSelectedAnswers: MutableState<Set<String>>,
    onAnswerSelected: (Set<String>) -> Unit,
    isPressed: MutableState<Boolean>
) {
    // Zeige den Titel und die Frage der Teilaufgabe an
    Text(text = teilaufgabe.title, style = MaterialTheme.typography.headlineSmall)
    Text(text = teilaufgabe.question, style = MaterialTheme.typography.bodyMedium)

    // Liste der möglichen Antworten
    LazyColumn {
        items(teilaufgabe.possibleAnswers) { answer ->
            Button(onClick = {
                Toast.makeText(context, "Antwort ausgewählt: $answer", Toast.LENGTH_SHORT).show()
                val newSelectedAnswers = initialSelectedAnswers.value.toMutableSet()
                if (newSelectedAnswers.contains(answer)) {
                    newSelectedAnswers.remove(answer)
                } else {
                    newSelectedAnswers.add(answer)
                }
                initialSelectedAnswers.value = newSelectedAnswers
                onAnswerSelected(initialSelectedAnswers.value)
            }) {
                Text(text = answer)
            }
        }
    }

    // Button zum Überprüfen der Antworten
    Button(onClick = { isPressed.value = true }) {
        Text("Antworten überprüfen")
    }

    // Wenn der Button gedrückt wurde, führe die Antwortprüfung durch
    if (isPressed.value) {
        val antwortMC = Antwort_MC(
            student = Schueler(), // Schueler ?? SchuelerID??
            subtask = teilaufgabe,
            isCorrect = false, // Der Anfangswert, wird innerhalb von approveAnswer aktualisiert
            isPrivate = false,
            studentAnswer = initialSelectedAnswers.value // Die ausgewählten Antworten
        )

        // Diese Funktion wird aufgerufen, wenn die Teilaufgabe abgeschlossen ist
        val onFinishSubtask: (Boolean) -> Unit = { finished ->
            if (finished) {
                isPressed.value = false
            }
        }

        // Zeige den Dialog basierend auf dem Ergebnis der Antwortüberprüfung
        antwortMC.approveAnswer(initialSelectedAnswers.value, isPressed.value, onFinishSubtask, isReset = false)
    }
}
