import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.ep_2023_2024.model.*
import android.util.Log
import android.content.Context
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import android.widget.Toast
import androidx.compose.runtime.*
import com.example.ep_2023_2024.model.Schueler
import com.example.ep_2023_2024.model.Teilaufgabe_MC
import com.example.ep_2023_2024.model.Aufgabe
import com.example.ep_2023_2024.model.Antwort_MC
import com.example.ep_2023_2024.view.HomeButton
@Composable
fun QuizScreen(firebaseHelper: FirebaseHelper, schuelerId: String) {
    val context = LocalContext.current
    val schuelerTags = remember { mutableStateOf<List<String>>(listOf()) }
    val aufgabenListe = remember { mutableStateOf<List<Aufgabe>>(listOf()) }
    val fehlerNachricht = remember { mutableStateOf<String?>(null) }
    var displayTaskIndex = remember { mutableStateOf(0) }
    var selectedAnswers = remember { mutableStateOf<Set<String>>(emptySet()) }
    val isButtonPressed = remember { mutableStateOf(false) }
    val isTaskFinished = remember { mutableStateOf(false) }

    LaunchedEffect(schuelerId) {
        firebaseHelper.loadStudentTags(schuelerId, onSuccess = { tags ->
            schuelerTags.value = tags
            firebaseHelper.loadAllTasks(onSuccess = { tasks ->
                aufgabenListe.value = getAufgabenByTags(tags, tasks)
                // Setze den Index zurück und lade die Aufgaben neu
                displayTaskIndex.value = 0
                isTaskFinished.value = false  // <-- Hinzugefügt: Setzt isTaskFinished zurück
            }, onError = { exception ->
                fehlerNachricht.value = "Fehler beim Laden aller Aufgaben: ${exception.message}"
            })
        }, onError = { exception ->
            fehlerNachricht.value = "Fehler beim Laden der Schüler-Tags: ${exception.message}"
        })
    }

    AufgabenListeUI(
        aufgabenListe = aufgabenListe.value,
        context = context,
        schueler = Schueler(),
        displayTaskIndex = displayTaskIndex,
        selectedAnswers = selectedAnswers,
        isButtonPressed = isButtonPressed,
        isTaskFinished = isTaskFinished,
        onNextQuestion = {
            Log.d("QuizScreen", "onNextQuestion - displayTaskIndex: ${displayTaskIndex.value}, aufgabenListe.size: ${aufgabenListe.value.size}")
            if (displayTaskIndex.value < aufgabenListe.value.size - 1) {
                displayTaskIndex.value += 1
                selectedAnswers.value = emptySet()
                isButtonPressed.value = false
            } else {
                firebaseHelper.loadAllTasks(onSuccess = { tasks ->
                    aufgabenListe.value = getAufgabenByTags(schuelerTags.value, tasks)
                    displayTaskIndex.value = 0
                    isTaskFinished.value = false  // <-- Hinzugefügt: Setzt isTaskFinished zurück
                }, onError = { exception ->
                    fehlerNachricht.value = "Fehler beim Laden aller Aufgaben: ${exception.message}"
                })
            }
        }
    )
}

@Composable
fun AufgabenListeUI(
    aufgabenListe: List<Aufgabe>,
    context: Context,
    schueler: Schueler,
    displayTaskIndex: MutableState<Int>,
    selectedAnswers: MutableState<Set<String>>,
    isButtonPressed: MutableState<Boolean>,
    isTaskFinished: MutableState<Boolean>,
    onNextQuestion: () -> Unit,
) {
    Column(modifier = Modifier.padding(16.dp)) {
        if (displayTaskIndex.value < aufgabenListe.size) {
            val aufgabe = aufgabenListe[displayTaskIndex.value]
            Text(text = aufgabe.name, style = MaterialTheme.typography.headlineMedium, modifier = Modifier.padding(bottom = 8.dp))

            aufgabe.teilaufgabenListe.firstOrNull()?.let { teilaufgabe ->
                if (teilaufgabe is Teilaufgabe_MC) {
                    TeilaufgabeMCUI(
                        teilaufgabe = teilaufgabe,
                        context = context,
                        initialSelectedAnswers = selectedAnswers,
                        onNextQuestion = {
                            // Aktionen bei onNextQuestion
                            selectedAnswers.value = emptySet()
                            isButtonPressed.value = false
                            displayTaskIndex.value += 1
                            onNextQuestion()
                        },
                    )
                }
            } ?: run {
                Text("Keine Teilaufgaben verfügbar.", style = MaterialTheme.typography.bodyLarge)
            }
        } else {
            isTaskFinished.value = true
            Text("Keine Aufgaben verfügbar.", style = MaterialTheme.typography.bodyLarge)
        }
    }
}

@Composable
fun TeilaufgabeMCUI(
    teilaufgabe: Teilaufgabe_MC,
    context: Context,
    initialSelectedAnswers: MutableState<Set<String>>,
    onNextQuestion: () -> Unit,
) {
    val showDialog = remember { mutableStateOf(false) }
    val answerCheckResult = remember { mutableStateOf<Boolean?>(null) }

    // Erstelle eine Instanz von Antwort_MC
    val antwortMC = Antwort_MC(
        student = Schueler(),
        subtask = teilaufgabe,
        isCorrect = false,
        isPrivate = false,
        studentAnswer = initialSelectedAnswers.value
    )

    // Zeige den Titel und die Frage der Teilaufgabe an
    Text(text = teilaufgabe.title, style = MaterialTheme.typography.headlineSmall)
    Text(text = teilaufgabe.question, style = MaterialTheme.typography.bodyMedium)
    Column {
        // Liste der möglichen Antworten
        LazyColumn {
            items(teilaufgabe.possibleAnswers) { answer ->
                Button(onClick = {
                    val newSelectedAnswers = initialSelectedAnswers.value.toMutableSet()
                    if (newSelectedAnswers.contains(answer)) {
                        newSelectedAnswers.remove(answer)
                    } else {
                        newSelectedAnswers.add(answer)
                    }
                    initialSelectedAnswers.value = newSelectedAnswers
                }) {
                    Text(text = answer)
                }
            }
        }

        // Button zum Überprüfen der Antworten
        Button(onClick = {
            answerCheckResult.value = antwortMC.evaluateAnswers(initialSelectedAnswers.value, teilaufgabe)
            showDialog.value = true
        }) {
            Text("Antwort überprüfen")
        }

    }

    // Zeige ein Dialogfenster mit dem Ergebnis der Überprüfung
    if (showDialog.value) {
        AlertDialog(
            onDismissRequest = { showDialog.value = false },
            title = { Text("Antwort Überprüfung") },
            text = { Text(if (answerCheckResult.value == true) "Richtig!" else "Leider falsch.") },
            confirmButton = {
                Button(onClick = {
                    showDialog.value = false
                    initialSelectedAnswers.value = emptySet()
                    onNextQuestion()
                }) { Text("OK") }
            }
        )
    }
}


