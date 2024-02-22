package com.example.ep_2023_2024.model

fun getAufgabenByTags(tags: List<Tag>, aufgabenListe: List<Aufgabe>): List<Aufgabe> {
    val universalTagName = "universal"
    return aufgabenListe.filter { aufgabe ->
        tags.any { it.name == universalTagName } || aufgabe.tag in tags.map { it.name } || aufgabe.tag == universalTagName
    }
}