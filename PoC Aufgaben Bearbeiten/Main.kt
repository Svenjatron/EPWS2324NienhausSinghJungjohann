// Benutzer:innen KLassen //
import java.util.Scanner


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
    val gegebene_Antworten: MutableList<Antwort>
    //val interessenListe: List<String>
) : Benutzer(email, passwort, name, vorname, schulklasse, isEmailVerified)

// Schulklassen Klassen //

abstract class Schulklasse(zahl: Int, buchstabe: Char)
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
    val teilaufgabenListe: MutableList<Teilaufgabe>,
    val suggestedGrade: Int
)
data class Schul_A(val bearbeitendeSchueler: List<Schueler>) : Aufgabe("", "", mutableListOf() , 0)

data class Privat_A(val aufgabe: Aufgabe, val bearbeitendeSchueler: List<Schueler>)

abstract class Teilaufgabe(
    val title: String,
    val description: String,
    val question: String,
    //val media: Medien,
    val answerList: MutableList<Antwort>    // given answers by Students
){
    open fun display_task(){
    }
    open fun answer_task():MutableList<Int>{
        val e = mutableListOf<Int>()
        return e
    }
}
//abstract class Medien(title = String)

open class Teilaufgabe_MC(
    title: String,
    description: String,
    question: String,
    answerList: MutableList<Antwort>,
    val possible_Answers: List<String>,
    val correct_Answer_index: Int?
): Teilaufgabe(title, description, question, answerList){

    override fun display_task(){
        var j = 1
        for (i in this.possible_Answers){
            println("$j. $i")
            j ++
        }
    }

    override fun answer_task():MutableList<Int>{
        val s = Scanner(System.`in`)
        val answer_list = mutableListOf<Int>()
        println("You can give multiple answers. If you're finished, enter 0.")
        println ("Please enter the number for your answer and hit enter. Then you can add more if you want.")
        while (true){
            val answer:Int = s.nextInt()
            if (answer != 0){
                answer_list.add(answer)
            }else break
        }
        return answer_list
    }


}

abstract class Antwort(
    val student: Schueler,
    val subtask: Teilaufgabe,
    val isCorrect: Boolean,
    val correctAnswer: String,
    val isPrivate: Boolean,
    open val student_answer: List<Any>
)

class Antwort_MC(
    student: Schueler,
    subtask: Teilaufgabe,
    isCorrect: Boolean,
    correctAnswer: String,
    isPrivate: Boolean,
    override val student_answer: MutableList<Int>
): Antwort(student, subtask, isCorrect, correctAnswer, isPrivate, student_answer)



class Antwort_FreeText(
    student: Schueler,
    subtask: Teilaufgabe,
    isCorrect: Boolean,
    correctAnswer: String,
    isPrivate: Boolean,
    override val student_answer: MutableList<String>

): Antwort(student, subtask, isCorrect, correctAnswer, isPrivate, student_answer)


fun main() {
    val klasse7a = object: Schulklasse(7, 'a'){}
    val given_answers_list = mutableListOf<Antwort>()
    val schüler1 = Schueler("schüler1@abc.de", "passwort", "Jungjohann", "Caro",
                    klasse7a , true, "Supermarkt", given_answers_list)
    val teilaufgaben_liste = mutableListOf<Teilaufgabe>()
    val antwort_liste = mutableListOf<Antwort>()
    val mc_antworten: List<String> = listOf("Supermarkt", "Discounter", "Wochenmarkt", "direkt beim Bauern","Bioladen", "über das Internet")

    val Teilaufgabe1_aufg1 = Teilaufgabe_MC("1.", "", "Wo kaufst du/ihr am häufigsten Lebensmittel ein?",
        answerList=antwort_liste, possible_Answers= mc_antworten, correct_Answer_index = null )
    teilaufgaben_liste.add(Teilaufgabe1_aufg1)

    val aufgabe1 = Aufgabe(name="Verkaufstricks im Supermarkt", tag="Supermarkt", teilaufgabenListe=teilaufgaben_liste, suggestedGrade = 7)

    aufgabeAusführen(aufgabe1, schüler1)

    println("Folgende Antworten hast du gegeben: ")
    var i = 0
    for (a in schüler1.gegebene_Antworten){
        a.student_answer.forEach{println(it)}
        i++
    }


}

fun aufgabeAusführen (aufgabe: Aufgabe, schüler: Schueler) {
    /**
     * Führt alle Teilaufgaben einer Aufgabe aus
     * */
    print("Aufgabe: ${aufgabe.name} \nAnzahl Teilaufgaben: ${aufgabe.teilaufgabenListe.size} \nEmpfohlener Jahrgang: ${aufgabe.suggestedGrade}\n")

    for (i in aufgabe.teilaufgabenListe) {
        i.display_task()                        // stellt Teilaufgabe dar
        val given_answer_list = i.answer_task()       // gibt Möglichkeit zum Antworten auf die Teilaufgabe
        val answer = Antwort_MC(schüler, i, true, "", true, given_answer_list)
        schüler.gegebene_Antworten.add(answer)
        i.answerList.add(answer)
    }
}


