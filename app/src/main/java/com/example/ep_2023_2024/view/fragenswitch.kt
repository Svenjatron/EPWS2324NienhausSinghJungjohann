


import androidx.compose.foundation.layout.*
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.ep_2023_2024.model.FirebaseHelper

@Composable
fun InteressenFrageScreen() {
    val context = LocalContext.current
    val firebaseHelper = FirebaseHelper(context)
    var abnehmenChecked by remember { mutableStateOf(false) }
    var zunehmenChecked by remember { mutableStateOf(false) }
    var sportChecked by remember { mutableStateOf(false) }
    var inhalteChecked by remember { mutableStateOf(false) }
    var universalChecked by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        FrageMitSwitch("Möchten Sie Informationen zum Thema Gewichtsverlust erhalten?", abnehmenChecked) { abnehmenChecked = it }
        FrageMitSwitch("Sind Sie daran interessiert, Tipps zum gesunden Zunehmen zu bekommen?", zunehmenChecked) { zunehmenChecked = it }
        FrageMitSwitch("Interessieren Sie sich für Sport und körperliche Aktivitäten?", sportChecked) { sportChecked = it }
        FrageMitSwitch("Sind Sie an exklusiven Inhalten zu Ernährung und Gesundheit interessiert?", inhalteChecked) { inhalteChecked = it }
        FrageMitSwitch("Möchten Sie vielfältige Themen rund um Wohlbefinden und Lifestyle erkunden?", universalChecked) { universalChecked = it }

        Button(
            onClick = {
                // Hier könnten Sie die Logik implementieren, um die Tags in Firebase zu speichern
               // if (abnehmenChecked) firebaseHelper.addTagToSchueler(schueler.id, Tag("abnehmen"))
              //  if (zunehmenChecked) firebaseHelper.addTagToSchueler(schueler.id, Tag("zunehmen"))
              //  if (sportChecked) firebaseHelper.addTagToSchueler(schueler.id, Tag("sport"))
              //  if (inhalteChecked) firebaseHelper.addTagToSchueler(schueler.id, Tag("inhalte"))
              //  if (universalChecked) firebaseHelper.addTagToSchueler(schueler.id, Tag("universal"))
            },
            modifier = Modifier.align(Alignment.End)
        ) {
            Text("Weiter")

        }
    }
}

@Composable
fun FrageMitSwitch(frage: String, checked: Boolean, onCheckedChange: (Boolean) -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(frage, modifier = Modifier.weight(1f))
        Switch(
            checked = checked,
            onCheckedChange = onCheckedChange
        )
    }
}
