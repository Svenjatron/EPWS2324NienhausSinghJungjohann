
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
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import com.example.ep_2023_2024.model.FirebaseHelper
import com.example.ep_2023_2024.model.Schueler
import androidx.compose.material3.OutlinedTextField
@Composable
fun SchuelerScreen() {
    val context = LocalContext.current
    var name by remember { mutableStateOf("") }
    val firebaseHelper = FirebaseHelper(context)

    Column {
        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Name") },
            singleLine = true,
            keyboardActions = KeyboardActions.Default
        )
        Button(onClick = {
            firebaseHelper.speichernSchueler(Schueler(name = name))
        }) {
            Text("Schüler anlegen")
        }
        Button(onClick = {
            // Implementieren Sie die Logik, um einen Schüler zu finden
            // Beispiel: firebaseHelper.findeSchuelerByName(name)
        }) {
            Text("Mit Namen einloggen")
        }
    }
}
