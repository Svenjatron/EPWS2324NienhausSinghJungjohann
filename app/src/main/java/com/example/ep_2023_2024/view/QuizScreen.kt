import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
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
fun QuizScreen() {
    /* Objekterstellung */
    val context = LocalContext.current
    val klasse7a = object : Schulklasse(7, 'a') {}    // Schulklasse Objekt
    val given_answers_list =mutableListOf<Antwort>()               // Liste mit allen jemals gegebenen Antworten des Schülers (Work in Progress!)
    val schüler1 = Schueler("schüler1@abc.de", "passwort", "Jungjohann", "Caro",    // Schüler erzeugen
        klasse7a, true, "Supermarkt")
    val teilaufgaben_liste =mutableListOf<Teilaufgabe>()       // Liste mit Teilaufgaben einer Aufgabe
    val antwortListe1 =mutableListOf<Antwort>()                // Liste mit allen jemals gegebenen Antworten der Schüler (Work in Progress!)
    val antwortListe2 = mutableListOf<Antwort>()                // Liste mit allen jemals gegebenen Antworten der Schüler (Work in Progress!)
    val mc1Antworten: List<String> = listOf("Supermarkt", "Discounter", "Wochenmarkt",          // mögliche Antworten im Multiple Choice
        "direkt beim Bauern", "Bioladen", "über das Internet")
    val mc2Antworten: List<String> = listOf("2-3 Mal pro Woche", "einmal pro Woche", "täglich", "alle 2 Wochen")
    val a1Ta1C1 =
        "Es gibt verschiedene Möglichkeiten, Lebensmittel einzukaufen. Der Supermarkt ist dabei wohl die gängigste, doch auch das Internet wird immer häufiger genutzt." +
                "Jedoch haben auch andere Optionen, wie zum Beispiel Bio- oder Wochenmärkte ihre Vorteile. Discounter haben im Vergleich zu Supermärkten ein kleineres Sortiment, " +
                "können dadurch günstigere Angebote liefern. Bio- und Wochenmärkte sowie Bauernmärkte liefern vor allem Obst und Gemüse am frischsten, haben jedoch eigene Preise." +
                "Weiterführende Links: https://de.wikipedia.org/wiki/Discounter https://de.wikipedia.org/wiki/Wochenmarkt https://de.wikipedia.org/wiki/Bioladen"
    val correctAnswerList1 = mutableListOf<String>("Supermarkt", "Wochenmarkt")
    val correctAnswerList2 = mutableListOf<String>()
    val teilaufgabe1_aufg1 = Teilaufgabe_MC("1.","","Wo kaufst du/ihr am häufigsten Lebensmittel ein?",
        answerList = antwortListe1, context = a1Ta1C1, possibleAnswers = mc1Antworten, correctAnswer = correctAnswerList1)           // Teilaufgabe
    val teilaufgabe2_aufg1 = Teilaufgabe_MC("2.", "", "Wie oft geht ihr einkaufen?", antwortListe2, context = "",
        possibleAnswers=mc2Antworten, correctAnswer=correctAnswerList2)
    teilaufgaben_liste.add(teilaufgabe1_aufg1)              // hinzufügen der Teilaufgabe in Teilaufgabenliste für die Aufgabe
    // teilaufgaben_liste.add(teilaufgabe2_aufg1)
    val aufgabe1 = Aufgabe(name = "Verkaufstricks im Supermarkt", tag = "Supermarkt", teilaufgabenListe = teilaufgaben_liste, suggestedGrade = 7) // Aufgabe erzeugen




    /* Darstellung*/
    @Composable
    fun aufgabeAusführen(aufgabe: Aufgabe, schueler: Schueler) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = aufgabe.name, style = MaterialTheme.typography.headlineSmall)
            for (i in aufgabe.teilaufgabenListe) {
                if (i is Teilaufgabe_MC)    {
                    i.displayTask(context)
                    val givenAnswers = i.answerTask(context)
                    val answer = Antwort_MC(schüler1, i, isCorrect = true, isPrivate = true, givenAnswers)
                    answer.approveAnswer(context)
                }

                Button(onClick = { }) {
                    Text(text = "Antwort überprüfen") }
            }
        }
    }

    aufgabeAusführen(aufgabe1, schüler1)


}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    QuizScreen()
}
