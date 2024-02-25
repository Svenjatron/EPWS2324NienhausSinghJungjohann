package com.example.ep_2023_2024.model

import android.annotation.SuppressLint
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import java.util.Scanner
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import kotlinx.coroutines.delay
import android.util.Log



// Benutzer:innen Klassen //

abstract class Benutzer(
    var email: String = "",
    var passwort: String = "",
    var name: String = "",
    var vorname: String = "",
    var schulklasse: Schulklasse? = null,
    var isEmailVerified: Boolean = false
)

class Lehrer(
    email: String = "",
    passwort: String = "",
    name: String = "",
    vorname: String = "",
    schulklasse: Schulklasse? = null,
    isEmailVerified: Boolean = false,
    var unterrichtsfach: String = ""
) : Benutzer(email, passwort, name, vorname, schulklasse, isEmailVerified)

class Schueler(
    email: String = "",
    passwort: String = "",
    name: String = "",
    vorname: String = "",
    schulklasse: Schulklasse? = null,
    isEmailVerified: Boolean = false,
    var persönlichesInteresse: String = "",
    var interessenListe: List<String> = emptyList()
) : Benutzer(email, passwort, name, vorname, schulklasse, isEmailVerified)

// Schulklassen Klassen //

abstract class Schulklasse(zahl: Int, buchstabe: Char)
abstract class Chat

class Schulgruppe(
    var name: String = "",
    var course: String = "",
    var schoolclass: Schulklasse? = null,
    var admin: Lehrer? = null,
    var studentList: List<Schueler> = emptyList(),
    var aktiveAufgabeList: List<Schul_A> = emptyList(),
    var chat: Chat? = null
)

// Aufgaben Klassen //

open class Aufgabe(
    var name: String = "",
    var tag: String = "",
    var teilaufgabenListe: List<Teilaufgabe> = emptyList(),
    var suggestedGrade: Int = 0
)

class Schul_A(
    var bearbeitendeSchueler: List<Schueler> = emptyList()
) : Aufgabe()

class Privat_A(
    var aufgabe: Aufgabe? = null,
    var bearbeitendeSchueler: List<Schueler> = emptyList()
)


abstract class Antwort(
    var student: Schueler? = null,
    open val subtask: Teilaufgabe? = null,
    var isCorrect: Boolean = false,
    var isPrivate: Boolean = false,
    open val studentAnswer: Set<Any>
){
    open fun manualApproveAnswer(){}

    open fun approveAnswer(answerList: Set<String>, isPressed: Boolean, onFinishSubtask: (Boolean) -> Unit, isReset: Boolean) {}
    open fun evaluateAnswers(answerList: Set<String>, subtask: Teilaufgabe_MC): Boolean{return true}
}
class Antwort_MC(
    student: Schueler,
    override val subtask: Teilaufgabe_MC,
    isCorrect: Boolean,
    isPrivate: Boolean,
    override val studentAnswer: Set<String>
) : Antwort(student, subtask, isCorrect, isPrivate, studentAnswer) {

    override fun  approveAnswer(
        answerList: Set<String>,
        isPressed: Boolean,
        onFinishSubtask: (Boolean) -> Unit,
        isReset: Boolean
    ) {
        // Logik zur Überprüfung der Antwort
        val isCorrect = evaluateAnswers(answerList, subtask)
        onFinishSubtask(isCorrect)
    }

    override fun evaluateAnswers(answerList: Set<String>, subtask: Teilaufgabe_MC): Boolean {
        // Implementierung bleibt gleich, aber entfernen Sie @Composable und Logging
        val correctAnswersSet = subtask.correctAnswer.map { it.trim() }.toSet()
        val userAnswersSet = answerList.map { it.trim() }.toSet()
        return userAnswersSet == correctAnswersSet
    }
}

open class Teilaufgabe(
    var title: String = "",
    var description: String = "",
    var question: String = "",
    var answerList: MutableList<Antwort> = mutableListOf<Antwort>(),
    val context: String = "",
    open val correctAnswer: List<Any> = emptyList()
) {
    @Composable
    open fun displayTask() {}
    @Composable
    open fun answerTask(onAnswersSelected: (Set<String>) -> Unit, onButtonPressed: (Boolean) -> Unit, context:Context, isReset: Boolean) {
        val a : List<Any> = listOf<Any>() }

    open fun approveAnswer() {}
    open fun manualApproveAnswers() {
    }
}
open class Teilaufgabe_MC(
    title: String = "",
    description: String = "",
    question: String = "",
    answerList: MutableList<Antwort> = mutableListOf<Antwort>(),
    context: String = "",
    override val correctAnswer: MutableList<String> = mutableListOf<String>(),
    val possibleAnswers: List<String> = listOf<String>()
): Teilaufgabe(title, description, question, answerList, context, correctAnswer) {
    @Composable
    override fun displayTask() {
        /**
         * Zeigt die zu bearbeitende Teilaufgabe an.
         * */
        Text(text = this.title + this.description, Modifier.padding(bottom = 8.dp))
        Text(text = this.question, Modifier.padding(bottom = 8.dp))

    }

    @Composable
    override fun answerTask(onAnswersSelected: (Set<String>) -> Unit, onButtonPressed: (Boolean) -> Unit, context: Context, isReset: Boolean) {
        /**
         * Gibt eine Möglichkeit zur Bearbeitung der vorher angezeigten Aufgabe.
         * */
        var selectedAnswers by remember { mutableStateOf(emptySet<String>()) }
        var isPressed by remember { mutableStateOf(false) }
        if (isReset){
            selectedAnswers = emptySet<String>()
            isPressed = false
        }

        LazyColumn {
            items(possibleAnswers) { answer ->
                // Hier wird die Toast-Nachricht ausgegeben, wenn der Button geklickt wird
                Button(onClick = {
                    Toast.makeText(context, "Antwort ausgewählt: $answer", Toast.LENGTH_SHORT)
                        .show()
                    if (!selectedAnswers.contains(answer)) {
                        selectedAnswers = selectedAnswers + answer
                    }
                })
                { Text(text = answer); }
            }
        }
         BasicTextField(
            value = selectedAnswers.joinToString(","),
            onValueChange = {},
            modifier = Modifier.padding(top = 8.dp, bottom = 8.dp)
        )
        Button(onClick = { onAnswersSelected(selectedAnswers)
        isPressed = !isPressed
        onButtonPressed(isPressed)}) {
            Text(text = "Antwort überprüfen")
        }
    }


}


// Tag Klasse //

data class Tag(val name: String = "")



