#Durchgeführte Proof of Concepts

## Personalisierte Inhalteauswahl basierend auf Tags

### Beschreibung des Vorhabens­:

Um den Nutzer:innen des Systems Inhalte entsprechend ihren Interessen zu präsentieren, ist geplant, das System mit Tags arbeiten zu lassen. Dadurch sollen Inhalte in verschiedene Interessensgruppen gefiltert und bei Bedarf ausgegeben werden können. Der Proof of Concept (PoC) hat das Ziel, sicherzustellen, dass die Filterung mithilfe eines Tagging-Systems die gewünschten Ergebnisse erzielt. Zu diesem Zweck wird im PoC ein Algorithmus zur Filterung von Inhalten konzipiert und getestet.
### Code-Erläuterung:
Der bereitgestellte Code dient als Proof of Concept (PoC) für die personalisierte Inhalteauswahl basierend auf Tags. Hier sind einige Schlüsselaspekte des Codes:
#### Datenstrukturen:
Es gibt zwei Hauptdatenklassen: Tag repräsentiert die Tags, während Content die Inhalte mit ihren Namen und Tags speichert.
#### Tagging-System:
Es wird ein Tagging-System verwendet, um Inhalte mit relevanten Tags zu versehen. Tags wie "abnehmen", "zunehmen", "sport" und "inhalte" werden für verschiedene Interessengebiete erstellt. Es gibt auch ein Universaltag für Inhalte, die für alle Kategorien relevant sind.
#### Inhalte erstellen:
Es werden Inhalte für verschiedene Kategorien erstellt und mit den entsprechenden Tags versehen. Dies geschieht in der ContentProvider-Klasse.
#### Inhalte filtern:
Die Funktion getContentsByTags filtert Inhalte basierend auf den eingegebenen Tags. 
#### Benutzereingabe:
Die run-Funktion interagiert mit dem Benutzer, indem sie nach Tags fragt und dann Inhalte basierend auf den eingegebenen Tags auswählt. Die Benutzereingabe wird validiert, um sicherzustellen, dass nur gültige Tags verwendet werden.
#### Zufällige Inhaltsauswahl:
Nach dem Filtern der Inhalte werden diese zufällig sortiert, und ein passender Inhalt wird ausgewählt und ausgegeben.
### Exit-Kriterien:
- Erfolgreiche manuelle Setzung von Tags durch das Entwicklerteam.
- Erfolgreiche Auswahl von Inhalten durch den Algorithmus basierend auf den manuell gesetzten Tags.
- Präsentation der vordefinierten Inhalte entsprechend den ausgewählten Tags.
### Fail-Kriterien:
- Probleme bei der manuellen Setzung von Tags durch das Entwicklerteam.
- Fehlerhafte Auswahl von Inhalten durch den Algorithmus.
- Probleme bei der Präsentation der vordefinierten Inhalte.
### Fallbacks:
- Bei Schwierigkeiten mit der manuellen Setzung von Tags: Überprüfung der Implementierung und Fehlerbehebung.
- Bei fehlerhafter Auswahl von Inhalten durch den Algorithmus: Überprüfung der Logik und Anpassung bei Bedarf.
- Bei Schwierigkeiten bei der Präsentation der vordefinierten Inhalte: Überprüfung der Implementierung und Fehlerbehebung.
