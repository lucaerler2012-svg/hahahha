# Von den Dateien zur fertigen JAR

## Variante A: GitHub baut die JAR für dich (empfohlen)

1. Auf github.com ein neues Repository anlegen (z. B. `enchantmentplus`), ohne README.
2. Den Inhalt dieses Ordners hochladen:
   - Im Browser: "uploading an existing file" anklicken und **alle Dateien samt Ordnern** hineinziehen. Der versteckte Ordner `.github` muss mit, sonst wird nicht gebaut.
   - Oder mit Git:
     ```bash
     git init
     git add .
     git commit -m "Erste Version"
     git branch -M main
     git remote add origin https://github.com/DEIN-NAME/enchantmentplus.git
     git push -u origin main
     ```
3. Im Repository auf den Reiter **Actions** gehen. Der Lauf "Build" startet automatisch.
4. Wenn der Lauf grün ist: Lauf anklicken, unten bei **Artifacts** `enchantmentplus-jar` herunterladen. Darin liegt `enchantmentplus-1.0.0.jar`.
5. Wird der Lauf rot: Auf den roten Schritt "Mod bauen" klicken und die Fehlermeldung kopieren. Fast immer ist es eine in 26.3 umbenannte Methode, die sich in einer Zeile beheben lässt.

## Variante B: Lokal bauen

Voraussetzung: Java 25 und Gradle 9.6.0. Der Wrapper fehlt noch, einmalig erzeugen:

```bash
gradle wrapper --gradle-version 9.6.0
./gradlew build        # Windows: gradlew.bat build
```

Die JAR liegt in `build/libs/enchantmentplus-1.0.0.jar` (die Datei **ohne** `-sources`).

## Änderungen veröffentlichen

```bash
git add .
git commit -m "Beschreibung der Änderung"
git push
```

Ein Tag wie `v1.0.0` erzeugt zusätzlich ein GitHub-Release mit der JAR:

```bash
git tag v1.0.0
git push origin v1.0.0
```

## Auf Modrinth hochladen

1. Auf modrinth.com einloggen, **Create a project** wählen, Name `Enchantment Plus`, Texte aus `MODRINTH.md` einfügen.
2. Unter **Versions** auf **Create a version** gehen und die JAR hochladen.
3. Version number `1.0.0`, Loader **Fabric**, Game version **26.3**.
4. Dependency hinzufügen: **Fabric API**, Typ *Required*.
5. Release channel *Release* (oder *Beta* zum Testen) und veröffentlichen.

Vorher in `src/main/resources/fabric.mod.json` den Platzhalter `DEIN-NAME` in der Repository-URL ersetzen.
