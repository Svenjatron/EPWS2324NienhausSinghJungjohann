# Proof of concept

## Testung des Schulaufgaben-Systems

### Ziele 

- Funktionsfähigkeit vom Schulaufgaben-System sicherstellen
- Plattformkompatibilität testen => funktioniert Anwendung auf verschiedenen Geräten und Plattformen konsistent und effektiv
- Benutzerfreundlichkeit prüfen
- Prüfung der Tutorials bzw der Anleitungen

### Durchführung 

Zur Überprüfung der Anwendung als Proof of Concept wird eine interne Testgruppe von Studierenden eingebunden, die die Anwendung in einer simulierten schulischen Umgebung verwenden wird, insbesondere im Kontext des Schulaufgaben-Systems. Dabei sollen Teammitglieder oder Kommiliton:innen abwechselnd die Rollen von Lehrer:innen und Schüler:innen übernehmen, um vielfältige Perspektiven und Erfahrungen einzubeziehen. Ein besonderer Fokus liegt darauf, die Plattformkompatibilität durch die Verwendung verschiedener Geräte zu testen. Nach der Testphase wird das Feedback mittels eines standardisierten Fragebogens ausgewertet, um Erkenntnisse über die Benutzerfreundlichkeit, Funktionalität und mögliche Verbesserungsbereiche zu gewinnen. 

# Proof of concept (iteriert)

## Testung von Firebase Authentication / Cloud Firestore

### Beschreibung des Vorhabens:

- Umsetzung von Firebase Authentication und Cloud Firestore.
- Möglichkeit, Benutzer:innen anzulegen und ihre Daten in der Cloud-Datenbank zu speichern.
- Implementierung von Authentifizierungsmethoden für die Abfrage von Login-Informationen.

### Exit-Kriterien:

- Erfolgreiche Anlage von Benutzer:innen und Speicherung der Informationen in der Cloud-Datenbank.
- Erfolgreiche Abfrage von Login-Informationen mithilfe der implementierten Authentifizierungsmethoden.

### Fail-Kriterien:

- Unvollständige oder fehlerhafte Anlage von Benutzer:innen.
- Probleme bei der Speicherung von Informationen in der Cloud-Datenbank.
- Fehlschlagen der Abfrage von Login-Informationen durch Authentifizierungsmethoden.

### Fallbacks:

- Im Falle von unvollständiger Benutzer:innen-Anlage: Überprüfung der Implementierungsschritte und Fehlerbehebung.
- Bei Problemen mit der Speicherung in der Cloud-Datenbank: Überprüfung der Verbindung und Optimierung des Speicherprozesses.
- Bei Authentifizierungsproblemen: Überprüfung der Implementierung der Authentifizierungsmethoden und Anpassung bei Bedarf.
  
## Testung von Benutzeroberfläche mit Jackpack Compose und Android Studio

### Beschreibung des Vorhabens:

- Umsetzung einer gebrauchstauglichen Oberfläche für die Interaktion mit Firebase Authentication und Cloud Firestore unter Verwendung des Jetpack Compose Frameworks.
- Erfassung und Anzeige von Benutzerdaten.
- Klare Darstellung von Anmeldemöglichkeiten und Authentifizierungsmethoden.

### Exit-Kriterien:

- Erfolgreiche Darstellung der Benutzeroberfläche für die Benutzer:innen unter Einsatz des Jetpack Compose Frameworks.
- Erfolgreiche Erfassung und Anzeige von Benutzerdaten.
- Klare und intuitive Anzeige von Anmeldemöglichkeiten und Authentifizierungsmethoden.

### Fail-Kriterien:

- Unübersichtliche oder fehlerhafte Benutzeroberfläche trotz Verwendung des Jetpack Compose Frameworks.
- Probleme bei der Erfassung oder Anzeige von Benutzerdaten.
- Schwierigkeiten bei der Anzeige von Anmeldemöglichkeiten und Authentifizierungsmethoden.

### Fallbacks:

- Im Falle von Unübersichtlichkeit oder Fehlern in der Benutzeroberfläche trotz Jetpack Compose: Überprüfung der Designentscheidungen und Anpassung bei Bedarf.
- Bei Problemen mit der Erfassung oder Anzeige von Benutzerdaten: Überprüfung der Implementierung und Fehlerbehebung.
- Bei Schwierigkeiten mit Anmeldemöglichkeiten und Authentifizierung: Überprüfung der UI/UX-Entscheidungen und Optimierung.

## Individualisierte Aufgabenbearbeitung

### Beschreibung des Vorhabens:

- Das System weist der Schülerin bzw. dem Schüler automatisch eine vordefinierte Aufgabe zu.
- Die Schülerin bzw. der Schüler simuliert die Bearbeitung der zugewiesenen Aufgabe.
- Die Schülerin bzw. der Schüler setzt manuell den Status der Aufgabe auf 'erledigt'.
- Das System vermerkt die erledigte Aufgabe.
- 
### Exit-Kriterien:

- Erfolgreiche Simulation der Bearbeitung der automatisch zugewiesenen Aufgabe durch die Schülerin bzw. den Schüler.
- Manuelle Statusänderung einer Aufgabe durch die Schülerin bzw. den Schüler auf 'erledigt'.
- Erfassung und Vermerk des erledigten Status.
  
### Fail-Kriterien:

- Probleme bei der Simulation der Bearbeitung der zugewiesenen Aufgabe.
- Probleme bei der manuellen Statusänderung oder Markierung von erledigten Aufgaben.

### Fallbacks:

- Bei Problemen mit der Simulation der Bearbeitung: Überprüfung der Implementierung und Fehlerbehebung.
- Bei Schwierigkeiten mit der manuellen Statusänderung: Überprüfung der Implementierung und Optimierung.

## Aufgabenausgabe

## Personalisierte Lerninhalte

### Beschreibung des Vorhabens:
- Das Entwicklerteam setzt Tags manuell per Eingabe, um bestimmte Interessen und Kenntnisse zu repräsentieren.
- Der Algorithmus analysiert die manuell gesetzten Tags und wählt Lerninhalte aus, die den vordefinierten Interessen und Kenntnissen entsprechen.
  
### Exit-Kriterien:
- Erfolgreiche manuelle Setzung von Tags durch das Entwicklerteam.
- Erfolgreiche Auswahl von Lerninhalten durch den Algorithmus basierend auf den manuell gesetzten Tags.
- Präsentation der vordefinierten Lerninhalte.

### Fail-Kriterien:
- Probleme bei der manuellen Setzung von Tags durch das Entwicklerteam.
- Fehlerhafte Auswahl von Lerninhalten durch den Algorithmus.
- Probleme bei der Präsentation der vordefinierten Lerninhalte.
  
### Fallbacks:
- Bei Schwierigkeiten mit der manuellen Setzung von Tags: Überprüfung der Implementierung und Fehlerbehebung.
- Bei fehlerhafter Auswahl von Lerninhalten durch den Algorithmus: Überprüfung der Logik und Anpassung bei Bedarf.
- Bei Schwierigkeiten bei der Präsentation der vordefinierten Lerninhalte: Überprüfung der Implementierung und Fehlerbehebung.

## Proof of Concept für das Bearbeiten von Aufgaben

### Beschreibung des Vorhabens:

Das geplante Aufgabensystem zielt darauf ab, die effiziente Bearbeitung von schulischen und außerschulischen  Aufgaben und Unter­aufgaben zu ermöglichen. Der Fokus liegt darauf, sicherzustellen, dass das System die Erstellung, Darstellung und Bearbeitung von Aufgaben erfolgreich umsetzt. Der Proof of Concept (PoC) hat das klare Ziel, die Funktionalität des konzipierten Aufgabensystems zu testen und sicherzustellen, dass es zuverlässig Aufgaben und Unter­aufgaben erstellen und bearbeiten kann.

### Code-Erläuterung:

Der vorliegende Code stellt eine Grundlage für die Bearbeitung von Aufgaben und Unter­aufgaben in einem schulischen Kontext dar. Hier sind die relevanten Aspekte des Codes:

#### Benutzerklassen:

- Klassen für Lehrer und Schüler sind vorhanden, wobei gemeinsame Eigenschaften wie E-Mail, Passwort und Schulklasse berücksichtigt werden.
- 
#### Schulklassenklassen:

- Es gibt eine abstrakte Klasse für Schulklasse und eine konkrete Klasse für Schulgruppen, die Informationen wie Name, Kurs, Lehrer, Schülerliste, aktive Aufgaben und Chat beinhalten.
- 
#### Aufgabenklassen:

- Klassen für Aufgaben und Teilaufgaben sind definiert und beinhalten wichtige Informationen wie Namen, Tags und Teilaufgabenlisten.
- 
#### Teilaufgabenklassen:

- Abstrakte und konkrete Klassen für Teilaufgaben ermöglichen eine spezifische Umsetzung von verschiedenen Aufgabentypen.
- 
#### Antwortenklassen:

Es gibt abstrakte Klassen für Antworten, wobei spezifische Klassen für verschiedene Antworttypen implementiert sind.

#### Main-Funktion:

Die main-Funktion repräsentiert eine Beispielumgebung, in der Aufgaben, Teilaufgaben und Antworten erstellt und bearbeitet werden. Die Funktion aufgabeAusführen demonstriert die Ausführung von Teilaufgaben einer Aufgabe.

## Exit-Kriterien:

- Erfolgreiche Erstellung von Aufgaben und Teilaufgaben.
- Erfolgreiche Bearbeitung von Aufgaben durch die aufgabeAusführen-Funktion.
- Zuverlässige Umsetzung der Beispielumgebung.
- 
## Fail-Kriterien:

- Probleme bei der Erstellung von Aufgaben und Teilaufgaben.
- Fehler bei der Bearbeitung von Aufgaben durch die aufgabeAusführen-Funktion.
- Unzuverlässige Umsetzung der Beispielumgebung.

## Fallbacks:

- Bei Schwierigkeiten mit der Erstellung von Aufgaben: Überprüfung der Implementierung und Fehlerbehebung.
- Bei Fehlern bei der Bearbeitung von Aufgaben: Überprüfung der Logik und Anpassung bei Bedarf.
- Bei Problemen bei der Umsetzung der Beispielumgebung: Überprüfung der Implementierung und Fehlerbehebung.


