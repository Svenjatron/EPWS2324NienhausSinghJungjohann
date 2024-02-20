package com.example.ep_2023_2024.model

import java.util.Scanner

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


data class Antwort(
    var student: Schueler? = null,
    var subtask: Teilaufgabe? = null,
    var isCorrect: Boolean = false,
    var correctAnswer: String = "",
    var isPrivate: Boolean = false,
    val studentAnswer: List<Any>
)

open class Teilaufgabe(
    var title: String = "",
    var description: String = "",
    var question: String = "",
    var answerList: MutableList<Antwort> = mutableListOf<Antwort>(),
    val context: String = "",
    open val correctAnswer: List<Any> = emptyList()
) {
    open fun displayTask() {}
    open fun answerTask(): MutableList<Int> {
        val e = mutableListOf<Int>()
        return e
    }

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
    override val correctAnswer: MutableList<Int> = mutableListOf<Int>(),
    val possibleAnswers: List<String>
): Teilaufgabe(title, description, question, answerList, context, correctAnswer) {

    override fun displayTask() {
        /**
         * Zeigt die zu bearbeitende Teilaufgabe an.
         * */
        var j = 1
        println(this.description)
        println(this.question)
        for (i in this.possibleAnswers) {
            println("$j. $i")
            j++
        }
    }

    override fun answerTask():MutableList<Int>{
        /**
         * Gibt eine Möglichkeit zur Bearbeitung der vorher angezeigten Aufgabe.
         * */
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
    }
}




// Tag Klasse //

data class Tag(val name: String = "")



