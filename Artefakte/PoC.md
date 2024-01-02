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
  
