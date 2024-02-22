
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
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.ui.Alignment



@Composable
fun LoginScreen(
    firebaseHelper: FirebaseHelper,
    onLoginComplete: (String) -> Unit
) {
    // State für den Namen und den Ladezustand
    var name by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Name") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Button zum Einloggen eines bestehenden Schülers
        Button(
            onClick = {
                isLoading = true
                firebaseHelper.findeSchuelerByName(name) { schuelerId ->
                    isLoading = false
                    schuelerId?.let {
                        onLoginComplete(it) // Fortfahren mit der Schüler-ID
                    } ?: Toast.makeText(context, "Schüler nicht gefunden.", Toast.LENGTH_LONG).show()
                }
            },
            enabled = !isLoading && name.isNotEmpty()
        ) {
            Text("Einloggen")
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Button zum Anlegen eines neuen Schülers
        Button(
            onClick = {
                isLoading = true
                val newSchueler = Schueler(name = name)
                firebaseHelper.speichernSchueler(newSchueler) { schuelerId ->
                    isLoading = false
                    onLoginComplete(schuelerId) // Fortfahren mit der neuen Schüler-ID
                }
            },
            enabled = !isLoading && name.isNotEmpty()
        ) {
            Text("Neuen Schüler anlegen")
        }
    }

    if (isLoading) {
        // Ladebalken
        CircularProgressIndicator()
    }
}

