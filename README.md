# Enchantment Plus

Fabric-Mod für Minecraft **26.3**: höhere Verzauberungslevel, erweiterte Amboss-Kombinationen, skalierender Schärfe-Schaden und ein Kreativ-Tab mit allen Buch-Leveln.

## Funktionen

- **Doppeltes Maximal-Level:** Jede Verzauberung geht bis zum Doppelten ihres Vanilla-Maximums (Schärfe 10, Schutz 8, Effizienz 10 ...). Gilt automatisch auch für Verzauberungen anderer Mods und Datapacks.
- **Amboss-Kombination:** Gleiche Level lassen sich über das Vanilla-Maximum hinaus kombinieren (Schärfe V + V = VI ... bis X).
- **Übertragen auf Items:** Erweiterte Bücher lassen sich auf Schwerter, Werkzeuge und Rüstung anwenden.
- **Kreativ-Tab "Enchantment Plus":** Für jede Verzauberung ein Buch pro Level, von 1 bis zum neuen Maximum.
- **Schärfe skaliert:** Bis Level 5 wie Vanilla. Danach wächst der Zusatzschaden proportional (Schärfe V = 3,0 Zusatzschaden, Schärfe X = 6,0).
- **XP-Kosten:** vorbereitet, noch nicht aktiv (siehe TODO).

## Version und Anforderungen

| | |
|---|---|
| Minecraft | 26.3 |
| Mod-Loader | Fabric Loader 0.19.5 oder neuer |
| Abhängigkeit | Fabric API (0.165.0+26.3 oder neuer für 26.3) |
| Java | 25 |
| Gradle / Loom | 9.6.0 / 1.17 (`net.fabricmc.fabric-loom`, ohne Mappings) |

## Installation (Spieler)

1. Fabric Loader für Minecraft 26.3 installieren.
2. Fabric API für 26.3 in den `mods`-Ordner legen.
3. `enchantmentplus-1.0.0.jar` (die Datei **ohne** `-sources`) ebenfalls in den `mods`-Ordner legen.
4. Minecraft mit dem Fabric-Profil starten.

## Entwicklung

```bash
./gradlew build          # Linux / macOS
gradlew.bat build        # Windows
./gradlew runClient      # Testen im Entwicklungs-Client
```

Hinweis: Der Gradle-Wrapper (`gradlew`) ist in diesem Paket noch nicht enthalten. Einmalig erzeugen mit installiertem Gradle:
`gradle wrapper --gradle-version 9.6.0`. Die GitHub-Action braucht ihn nicht.

Die fertige JAR liegt danach in `build/libs/enchantmentplus-1.0.0.jar`.

Projektaufbau:

```
src/main/java/com/enchantmentplus/
├── EnchantmentPlusMod.java          Einstiegspunkt
├── config/EnchantmentPlusConfig.java Konstanten (Faktor, Schärfe, XP-Platzhalter)
├── enchant/SharpnessDamageHandler.java  Schärfe-Zusatzschaden
├── anvil/XpCostPlanner.java         vorbereiteter XP-Kosten-Haken
├── creative/EnchantmentPlusTab.java Kreativ-Tab
└── mixin/
    ├── EnchantmentMixin.java        getMaxLevel() x2
    ├── EnchantmentHelperMixin.java  Schärfe-Zusatzschaden
    └── AnvilMenuMixin.java          XP-Kosten-Haken
```

## Bekannte Einschränkungen

- **Nicht gegen 26.3 kompiliert getestet.** Das Projekt wurde ohne Zugriff auf die Minecraft-Quellen geschrieben. Falls `gradle build` Fehler meldet, sind das meist umbenannte Methoden (siehe TODO).
- `EnchantmentHelperMixin` und `AnvilMenuMixin` sind mit `require = 0` abgesichert. Passt die Zielmethode nicht, startet das Spiel trotzdem, aber Zusatzschaden bzw. XP-Haken sind dann inaktiv (Hinweis im Log).
- Das verdoppelte Maximum sieht auch der Verzauberungstisch. Hohe Level sind dort praktisch unerreichbar teuer, in seltenen Fällen kann aber z. B. Schutz V erscheinen.
- Verzauberungen mit Maximum 1 (z. B. Reparatur) haben nun Maximum 2. Level 2 bringt keinen Effekt.
- Der Amboss zeigt im Überlebensmodus ab 40 Level "Zu teuer!". Hohe Level erreichen das schnell.
- Der Zusatzschaden gilt nur für Schärfe, nicht für Nahkampf-Verzauberungen anderer Mods.

## TODO

- [ ] XP-Kosten für erweiterte Level festlegen (`XpCostPlanner`, `EnchantmentPlusConfig.ANVIL_*`)
- [ ] "Zu teuer!"-Grenze anpassen oder abschalten
- [ ] Konfigurationsdatei statt fester Konstanten
- [ ] Zielmethoden der Mixins gegen 26.3 prüfen
- [ ] Verzauberungstisch auf Vanilla-Maximum begrenzen (optional)
- [ ] Mod-Icon (`assets/enchantmentplus/icon.png`) ergänzen

## Lizenz

MIT, siehe `LICENSE`.
