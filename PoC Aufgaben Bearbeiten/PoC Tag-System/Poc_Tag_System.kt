////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// Dieser PoC testet das Filtern von Inhalten per Tag-System. Dazu werden Inhalte, welche hier nur Name und Tags als  //
// Eigenschaft haben, erstellt und können dann anschließend über Texteingabe abgefragt werden. Der Algorithmus gibt   //
// dann einen zufällig gewählten, passenden Inhalt aus. Content kann mit mehreren Tags versehen werden. Außerdem      //
// können mehrere Tags in einer Anfrage abgefragt werden. Für diesen PoC erstellte Tags heißen "abnehmen", "zunehmen",//
// "sport" und "inhalte". Hinzu kommt ein Universaltag, welches beispielhaft Inhalte kennzeichnet, welche für alle    //
// Kategorien passend sind. Der Code ist in Intellj getestet worden und erzielte gewünschtes Ergebnis.                //                                                                                           //
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


package com.example.poc

// Datenklasse für Tags //
data class Tag(val name: String)

// Datenklasse für Inhalte //
data class Content(val name: String, val tags: List<Tag>)

class ContentProvider {

    // Universaltag erstellen //

    private val universalTag = Tag("universal")

    // Erstellen von Inhalten für alle Kategorien //

    private val contents = mutableListOf(
        Content("abnehmen1", listOf(Tag("abnehmen"))),
        Content("abnehmen2", listOf(Tag("abnehmen"))),
        Content("zunehmen1", listOf(Tag("zunehmen"))),
        Content("zunehmen2", listOf(Tag("zunehmen"))),
        Content("sport1", listOf(Tag("sport"))),
        Content("sport2", listOf(Tag("sport"))),
        Content("inhalte1", listOf(Tag("inhalte"))),
        Content("inhalte2", listOf(Tag("inhalte"))),
        Content("universal1", listOf(Tag("universal"))),
        Content("universal2", listOf(Tag("universal")))
    )

    // Hauptfunktion der für den PoC erstellten Anwendung //

    fun run() {
        do {
            var inputTags: List<Tag>

            // Benutzer nach Tags fragen und sicherstellen, dass gültige Tags eingegeben werden //

            do {
                print("Geben Sie Tags ein (getrennt durch Kommas) (Für diesen PoC gültige Tags heißen \"abnehmen\", \"zunehmen\", \"sport\" und \"inhalte\".): ")
                val inputString = readLine()?.trim()?.lowercase()
                inputTags = inputString?.split(",")?.map { Tag(it.trim()) } ?: emptyList()

                if (inputTags.any { it.name !in listOf("abnehmen", "zunehmen", "sport", "inhalte") }) {
                    println("Ungültige Tags. Bitte verwenden Sie nur die gültigen Tags: \"abnehmen\", \"zunehmen\", \"sport\" oder \"inhalte\".")
                }
            } while (inputTags.isEmpty() || inputTags.any { it.name !in listOf("abnehmen", "zunehmen", "sport", "inhalte") })

            val selectedContents = getContentsByTags(inputTags)

            if (selectedContents.isNotEmpty()) {

                // Zufälligen Inhalt auswählen //

                val selectedContent = selectedContents.shuffled().firstOrNull()

                // Ausgabe des ausgewählten Inhalts //

                println("Ausgewählter Inhalt: ${selectedContent?.name ?: "Kein passender Inhalt gefunden."}")
            }

            // Benutzer:in fragen, ob mehr Inhalte generiert werden sollen //

            print("Möchten Sie mehr Inhalte generieren? (Ja/Nein): ")
        } while (readLine()?.equals("Ja", ignoreCase = true) == true)
    }

    // Diese Funktion dient dem Filtern von Inhalten nach Tags. Ausgegeben wird ein, den Tags entsprechender Inhalt    //
    // oder ein Universalinhalt //

    private fun getContentsByTags(tags: List<Tag>): List<Content> {

        val selectedContents = contents.filter { content ->
            tags.any { it == universalTag } || content.tags.any { it == universalTag || it in tags }
        }

        return selectedContents
    }
}

// Main-Funktion //

fun main() {
    val app = ContentProvider()
    app.run()
}

