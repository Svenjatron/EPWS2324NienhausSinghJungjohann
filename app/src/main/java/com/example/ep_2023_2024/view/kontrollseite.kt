package com.example.ep_2023_2024.view

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.ep_2023_2024.model.FirebaseHelper


@Composable
fun SchuelerTagsScreen(firebaseHelper: FirebaseHelper, schuelerId: String) {
    val context = LocalContext.current
    var schuelerTags by remember { mutableStateOf<List<String>?>(null) }
    var isLoading by remember { mutableStateOf(true) }

    // Tags vom Firebase abrufen
    LaunchedEffect(schuelerId) {
        firebaseHelper.loadStudentTags(schuelerId, onSuccess = { tags ->
            schuelerTags = tags
            isLoading = false
        }, onError = { exception ->
            Toast.makeText(context, "Fehler beim Laden der Tags: ${exception.localizedMessage}", Toast.LENGTH_LONG).show()
            isLoading = false
        })
    }

    if (isLoading) {
        CircularProgressIndicator()
    } else {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "Schüler ID: $schuelerId", style = MaterialTheme.typography.headlineMedium)
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "Tags:", style = MaterialTheme.typography.headlineSmall)
            schuelerTags?.let { tags ->
                for (tag in tags) {
                    Text(text = tag, style = MaterialTheme.typography.bodyLarge)
                }
            } ?: Text(text = "Keine Tags gefunden", style = MaterialTheme.typography.bodyLarge)
        }
    }
}
