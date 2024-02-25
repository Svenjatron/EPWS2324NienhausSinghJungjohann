package com.example.ep_2023_2024.model

fun getAufgabenByTags(tags: List<String>, aufgabenListe: List<Aufgabe>): List<Aufgabe> {
    val universalTagName = "universal"
    val filteredAufgaben = aufgabenListe.filter { aufgabe ->
        tags.contains(universalTagName) || aufgabe.tag in tags
    }

    // Debugging-Ausgabe
    println("Anzahl der Aufgaben vor der Filterung: ${aufgabenListe.size}")
    println("Anzahl der Tags: ${tags.size}")
    println("Anzahl der Aufgaben nach der Filterung: ${filteredAufgaben.size}")

    return filteredAufgaben
}
