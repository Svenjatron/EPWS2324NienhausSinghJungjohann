package com.example.ep_2023_2024.model


// Benutzer:innen KLassen //

abstract class Benutzer(
    val email: String,
    val passwort: String,
    val name: String,
    val vorname: String,
    val schulklasse: Schulklasse,
    var isEmailVerified: Boolean
)

class Lehrer(
    email: String,
    passwort: String,
    name: String,
    vorname: String,
    schulklasse: Schulklasse,
    isEmailVerified: Boolean,
    val unterrichtsfach: String
) : Benutzer(email, passwort, name, vorname, schulklasse, isEmailVerified)

class Schueler(
    email: String,
    passwort: String,
    name: String,
    vorname: String,
    schulklasse: Schulklasse,
    isEmailVerified: Boolean,
    val persönlichesInteresse: String,
    val interessenListe: List<String>
) : Benutzer(email, passwort, name, vorname, schulklasse, isEmailVerified)

// Schulklassen Klassen //

abstract class Schulklasse

abstract class Chat


class Schulgruppe(
    val name: String,
    val course: String,
    val schoolclass: Schulklasse,
    val admin: Lehrer,
    val studentList: List<Schueler>,
    val aktiveAufgabeList: List<Schul_A>,
    val chat: Chat
)


// Aufgaben Klassen //

open class Aufgabe(
    val name: String,
    val tag: String,
    val teilaufgabenListe: List<Teilaufgabe>,
    val suggestedGrade: Int
)
data class Schul_A(val bearbeitendeSchueler: List<Schueler>) : Aufgabe("", "", listOf(), 0)

data class Privat_A(val aufgabe: Aufgabe, val bearbeitendeSchueler: List<Schueler>)

class Teilaufgabe(
    val title: String,
    val description: String,
    val question: String,
    val media: Medien,
    val answerList: List<Antwort>
)
abstract class Medien

data class Antwort(
    val answerText: String,
    val answerMedia: Medien,
    val student: Schueler,
    val subtask: Teilaufgabe,
    val isCorrect: Boolean,
    val correctAnswer: String,
    val isPrivate: Boolean
)



