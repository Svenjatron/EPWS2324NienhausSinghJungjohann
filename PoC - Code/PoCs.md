# Durchgeführte Proof of Concepts

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

## Proof of Concept für das Bearbeiten von Aufgaben

### Beschreibung des Vorhabens:

Das geplante Aufgabensystem zielt darauf ab, die effiziente Bearbeitung von schulischen und außerschulischen  Aufgaben und Unter­aufgaben zu ermöglichen. Der Fokus liegt darauf, sicherzustellen, dass das System die Erstellung, Darstellung und Bearbeitung von Aufgaben erfolgreich umsetzt. Der Proof of Concept (PoC) hat das klare Ziel, die Funktionalität des konzipierten Aufgabensystems zu testen und sicherzustellen, dass es zuverlässig Aufgaben und Unter­aufgaben erstellen und bearbeiten kann.

### Code-Erläuterung:

Der vorliegende Code stellt eine Grundlage für die Bearbeitung von Aufgaben und Unter­aufgaben in einem schulischen Kontext dar. Hier sind die relevanten Aspekte des Codes:

#### Benutzerklassen:

- Klassen für Lehrer und Schüler sind vorhanden, wobei gemeinsame Eigenschaften wie E-Mail, Passwort und Schulklasse berücksichtigt werden.
  
#### Schulklassenklassen:

- Es gibt eine abstrakte Klasse für Schulklasse und eine konkrete Klasse für Schulgruppen, die Informationen wie Name, Kurs, Lehrer, Schülerliste, aktive Aufgaben und Chat beinhalten.
  
#### Aufgabenklassen:

- Klassen für Aufgaben und Teilaufgaben sind definiert und beinhalten wichtige Informationen wie Namen, Tags und Teilaufgabenlisten.
  
#### Teilaufgabenklassen:

- Abstrakte und konkrete Klassen für Teilaufgaben ermöglichen eine spezifische Umsetzung von verschiedenen Aufgabentypen.
  
#### Antwortenklassen:

Es gibt abstrakte Klassen für Antworten, wobei spezifische Klassen für verschiedene Antworttypen implementiert sind.

#### Main-Funktion:

Die main-Funktion repräsentiert eine Beispielumgebung, in der Aufgaben, Teilaufgaben und Antworten erstellt und bearbeitet werden. Die Funktion aufgabeAusführen demonstriert die Ausführung von Teilaufgaben einer Aufgabe.

## Exit-Kriterien:

- Erfolgreiche Erstellung von Aufgaben und Teilaufgaben.
- Erfolgreiche Bearbeitung von Aufgaben durch die aufgabeAusführen-Funktion.
- Zuverlässige Umsetzung der Beispielumgebung.
  
## Fail-Kriterien:

- Probleme bei der Erstellung von Aufgaben und Teilaufgaben.
- Fehler bei der Bearbeitung von Aufgaben durch die aufgabeAusführen-Funktion.
- Unzuverlässige Umsetzung der Beispielumgebung.

## Fallbacks:

- Bei Schwierigkeiten mit der Erstellung von Aufgaben: Überprüfung der Implementierung und Fehlerbehebung.
- Bei Fehlern bei der Bearbeitung von Aufgaben: Überprüfung der Logik und Anpassung bei Bedarf.
- Bei Problemen bei der Umsetzung der Beispielumgebung: Überprüfung der Implementierung und Fehlerbehebung.


