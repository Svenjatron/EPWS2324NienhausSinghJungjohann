package com.example.ep_2023_2024.model

fun getAufgabenByTags(tags: List<String>, aufgabenListe: List<Aufgabe>): List<Aufgabe> {
    val universalTagName = "universal"
    return aufgabenListe.filter { aufgabe ->
        tags.contains(universalTagName) || aufgabe.tag in tags || aufgabe.tag == universalTagName
    }
}