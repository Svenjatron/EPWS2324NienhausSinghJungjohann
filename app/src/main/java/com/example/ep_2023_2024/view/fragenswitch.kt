


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
import android.widget.Toast
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.example.ep_2023_2024.view.HomeButton




@Composable
fun InteressenFrageScreen(firebaseHelper: FirebaseHelper, schuelerId: String, navController: NavController) {
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
        FrageMitSwitch("Möchtest Du gerne ein bisschen abnehmen?", abnehmenChecked) { abnehmenChecked = it }
        FrageMitSwitch("Würdest Du gerne ein bisschen zunehmen?", zunehmenChecked) { zunehmenChecked = it }
        FrageMitSwitch("Willst Du was zu Sportlerernährung erfahren?", sportChecked) { sportChecked = it }
        FrageMitSwitch("Willst Du gerne generell etwas über gesunde Ernährung erfahren? ", inhalteChecked) { inhalteChecked = it }
        FrageMitSwitch("Möchtest Du vielfältige Themen rund um Wohlbefinden und Lifestyle erkunden?", universalChecked) { universalChecked = it }

        Button(
            onClick = {

                val selectedTags = mutableListOf<String>()
                if (abnehmenChecked) selectedTags.add("abnehmen")
                if (zunehmenChecked) selectedTags.add("zunehmen")
                if (sportChecked) selectedTags.add("sport")
                if (inhalteChecked) selectedTags.add("inhalte")
                if (universalChecked) selectedTags.add("universal")

                firebaseHelper.addTagsToSchueler(schuelerId, selectedTags) { success ->
                    if (success) {
                        navController.navigate("QuizScreen/$schuelerId")

                    } else {
                        // Fehlerbehandlung

                    }
                }
            },
            modifier = Modifier.align(Alignment.End)
        ) {
            Text("Weiter")
        }

    }
    HomeButton(navController)

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
