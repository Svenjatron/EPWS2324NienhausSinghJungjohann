package com.example.ep_2023_2024.model

import android.content.Context
import android.widget.Toast
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class FirebaseHelper(private val context: Context) {

    fun speichernSchueler(schueler: Schueler) {
        val databaseReference = FirebaseDatabase.getInstance().getReference("Schueler")
        val schuelerId = databaseReference.push().key ?: return // Generiert eine eindeutige ID für jeden Schüler
        databaseReference.child(schuelerId).setValue(schueler)
            .addOnSuccessListener {
                // Erfolgreich gespeichert
                Toast.makeText(context, "Schüler erfolgreich gespeichert.", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener {
                // Fehler beim Speichern
                Toast.makeText(context, "Fehler beim Speichern des Schülers.", Toast.LENGTH_SHORT).show()
            }
    }

    fun abrufenSchueler() {
        val databaseReference = FirebaseDatabase.getInstance().getReference("Schueler")
        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val schuelerListe = mutableListOf<Schueler>()
                snapshot.children.forEach {
                    val schueler = it.getValue(Schueler::class.java)
                    schueler?.let { schuelerListe.add(it) }
                }
                // Verwenden Sie schuelerListe wie benötigt
                Toast.makeText(context, "Schüler erfolgreich abgerufen.", Toast.LENGTH_SHORT).show()
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Fehler beim Abrufen der Daten
                Toast.makeText(context, "Fehler beim Abrufen der Schülerdaten.", Toast.LENGTH_SHORT).show()
            }
        })
    }
    fun aktualisiereSchueler(schuelerId: String, neueDaten: Map<String, Any>) {
        val databaseReference = FirebaseDatabase.getInstance().getReference("Schueler")
        databaseReference.child(schuelerId).updateChildren(neueDaten)
            .addOnSuccessListener {
                // Aktualisierung erfolgreich
                Toast.makeText(context, "Schülerdaten erfolgreich aktualisiert.", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener {
                // Fehler beim Aktualisieren
                Toast.makeText(context, "Fehler beim Aktualisieren der Schülerdaten.", Toast.LENGTH_SHORT).show()
            }
    }
    fun findeSchuelerByName(name: String) {
        val databaseReference = FirebaseDatabase.getInstance().getReference("Schueler")
        databaseReference.orderByChild("name").equalTo(name).addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    // Schüler mit dem gegebenen Namen gefunden
                    val schuelerListe = mutableListOf<Schueler>()
                    snapshot.children.forEach {
                        val schueler = it.getValue(Schueler::class.java)
                        schueler?.let { schuelerListe.add(it) }
                    }
                    // Verarbeiten Sie die gefundene Schülerliste wie benötigt
                    Toast.makeText(context, "Schüler gefunden: ${schuelerListe.size}", Toast.LENGTH_SHORT).show()
                } else {
                    // Kein Schüler mit diesem Namen gefunden
                    Toast.makeText(context, "Kein Schüler mit diesem Namen gefunden.", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onCancelled(error: DatabaseError) {
                // Fehler beim Abrufen der Daten
                Toast.makeText(context, "Fehler beim Suchen des Schülers: ${error.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

}
