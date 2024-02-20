package com.example.ep_2023_2024.model

import android.content.Context
import android.widget.Toast
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class FirebaseHelper(private val context: Context) {

    fun speichernSchueler(schueler: Schueler, onResult: (String) -> Unit) {
        val databaseReference = FirebaseDatabase.getInstance().getReference("Schueler")
        val schuelerId = databaseReference.push().key
        if (schuelerId == null) {
            Toast.makeText(context, "Fehler beim Generieren der Schüler-ID.", Toast.LENGTH_SHORT).show()
            return
        }

        databaseReference.child(schuelerId).setValue(schueler)
            .addOnSuccessListener {
                Toast.makeText(context, "Schüler erfolgreich gespeichert.", Toast.LENGTH_SHORT).show()
                onResult(schuelerId) // Callback mit der ID des neu gespeicherten Schülers
            }
            .addOnFailureListener {
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
    fun findeSchuelerByName(name: String, onResult: (String?) -> Unit) {
        val databaseReference = FirebaseDatabase.getInstance().getReference("Schueler")
        databaseReference.orderByChild("name").equalTo(name).addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists() && snapshot.children.count() == 1) {
                    val schuelerId = snapshot.children.first().key
                    Toast.makeText(context, "Schüler gefunden.", Toast.LENGTH_SHORT).show()
                    onResult(schuelerId) // Callback mit der gefundenen Schüler-ID
                } else {
                    Toast.makeText(context, "Kein Schüler mit diesem Namen gefunden.", Toast.LENGTH_SHORT).show()
                    onResult(null)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(context, "Fehler beim Suchen des Schülers: ${error.message}", Toast.LENGTH_SHORT).show()
                onResult(null)
            }
        })
    }





}
