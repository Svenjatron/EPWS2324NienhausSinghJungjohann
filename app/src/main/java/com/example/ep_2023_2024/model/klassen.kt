package com.example.ep_2023_2024.model

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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue


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
    @Composable
    open fun approveAnswer(context:Context, answerList: Set<String>, isPressed: Boolean) {}
}

class Antwort_MC(
    student: Schueler,
    override val subtask: Teilaufgabe_MC,
    isCorrect: Boolean,
    isPrivate: Boolean,
    override val studentAnswer: Set<String>
): Antwort(student, subtask, isCorrect, isPrivate, studentAnswer) {

    override fun manualApproveAnswer() {
        val s = Scanner(System.`in`)
        println("When you think the evaluation is wrong, please enter 1, else 0.")
        while (true) {
            val answer: Int = s.nextInt()
            if (answer == 1) {
                println("Deine Antwort wird nun als richtig dargestellt.")
                this.isCorrect = true
                break
            } else if (answer == 0) {
                println("Die Evaluation wird nicht geändert.")
                break
            } else break
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun approveAnswer(context: Context, answerList: Set<String>, isPressed: Boolean) {
        /** Check given answer from student and print if correct or not, as well as the correct answer.
         * */
        var p by remember { mutableStateOf(isPressed) }
        var isButtonChanged by remember { mutableStateOf(p) }

        if (subtask.correctAnswer.isEmpty()) {
            if (p) {
                AlertDialog(
                    onDismissRequest = { p = !p
                        },
                    title = { Text("Antworten gesendet") },
                    text = { Text("Die Antworten können weder richtig noch falsch sein. Sie wurden gesperichert.") },
                    confirmButton = {
                        Button(onClick = { p = false })
                        { Text("OK") }
                    }
                )
            }
        } else if (subtask.correctAnswer == studentAnswer.toMutableList()) {
            if (p) {
                AlertDialog(
                    onDismissRequest = { p = false },
                    title = { Text("Korrekt!") },
                    text = { Text("Deine Antworten sind korrekt! Möchtest du deine Antworten mauell überprüfen?") },
                    confirmButton = {
                        Button(onClick = { p = false })
                        { Text("Überprüfen") }
                    },
                    dismissButton = {
                        Button(onClick = {}) { Text("Speichern & schließen") }
                    }
                )
            }
        } else if (subtask.correctAnswer != studentAnswer.toMutableList()) {
            if (p) {
                AlertDialog(
                    onDismissRequest = { p = false },
                    title = { Text("Antwort nicht korrekt") },
                    text = { Text("Deine Antworten sind leider nicht korrekt. Möchtest du deine Antworten mauell überprüfen?") },
                    confirmButton = {
                        Button(onClick = { p = false })
                        { Text("Überprüfen") }
                    },
                    dismissButton = {
                        Button(onClick = {}) { Text("Speichern & schließen") }
                    }
                )
            }
        } else {
            p = true
            if (p) {

            }
        }
    }


    /*
    if (this.subtask.correctAnswer.isEmpty()) {
        print ("Answer cannot be correct or incorrect.")
    } else {
        if (this.subtask.correctAnswer == this.studentAnswer){
            print("Your answer/s ")
            for (x in this.studentAnswer)      print(" " + this.subtask.possibleAnswers[x])
            print(" is/are correct.")
            this.isCorrect = true
        }else{
            print("Your answer/s ")
            for (x in this.studentAnswer)      print(" " + this.subtask.possibleAnswers[x])
            print(" is/are wrong.")
            print("This are the correct answers: ")
            for (x in this.subtask.correctAnswer) print (" " + this.subtask.possibleAnswers[x] )
            this.isCorrect = false
            this.manualApproveAnswer()
        }
    }

     */
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
    open fun displayTask(context: Context) {}
    @Composable
    open fun answerTask(onAnswersSelected: (Set<String>) -> Unit, onButtonPressed: (Boolean) -> Unit, context:Context) {
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
    override fun displayTask(context: Context) {
        /**
         * Zeigt die zu bearbeitende Teilaufgabe an.
         * */
        Text(text = this.title + this.description, Modifier.padding(bottom = 8.dp))
        Text(text = this.question, Modifier.padding(bottom = 8.dp))

    }

    @Composable
    override fun answerTask(onAnswersSelected: (Set<String>) -> Unit, onButtonPressed: (Boolean) -> Unit, context: Context) {
        /**
         * Gibt eine Möglichkeit zur Bearbeitung der vorher angezeigten Aufgabe.
         * */
        var selectedAnswers by remember { mutableStateOf(emptySet<String>()) }
        var isPressed by remember { mutableStateOf(false) }

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


    /*
        val s = Scanner(System.`in`)
        val answer_list = mutableListOf<Int>()
        println("You can give multiple answers. If you're finished, enter 0. For more context, enter 9.")
        println ("Please enter the number for your answer and hit enter. Then you can add more if you want.")
        while (true){
            val answer: Int = s.nextInt()
            if (answer != 0 && answer != 9 ){
                answer_list.add(answer)
            }else if (answer == 9)  print(this.context)
            else break
        }
        return answer_list
 */


}


// Tag Klasse //

data class Tag(val name: String = "")



