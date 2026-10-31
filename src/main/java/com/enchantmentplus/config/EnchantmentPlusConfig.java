package com.enchantmentplus.config;

/**
 * Zentrale Einstellungen. Aktuell feste Konstanten.
 * TODO: Später in eine Konfigurationsdatei (config/enchantmentplus.json) auslagern.
 */
public final class EnchantmentPlusConfig {
    private EnchantmentPlusConfig() {}

    /** Faktor für das Maximal-Level: Vanilla-Maximum x 2. */
    public static final int MAX_LEVEL_MULTIPLIER = 2;

    /**
     * Bis zu diesem Schärfe-Level bleibt der Schaden exakt wie in Vanilla.
     * Darüber wächst der Zusatzschaden proportional zum Level, sodass
     * Schärfe 10 genau doppelt so viel Zusatzschaden macht wie Schärfe 5.
     */
    public static final int SHARPNESS_REFERENCE_LEVEL = 5;

    /** Vanilla-Formel für Schärfe-Zusatzschaden: 0.5 * Level + 0.5 (Level 1 = 1.0, Level 5 = 3.0). */
    public static final float VANILLA_SHARPNESS_PER_LEVEL = 0.5f;
    public static final float VANILLA_SHARPNESS_BASE = 0.5f;

    // ---- XP-Kosten (vorbereitet) ----------------------------------------------------

    /** TODO: Multiplikator für die Amboss-XP-Kosten bei erweiterten Leveln. 1.0 = Vanilla. */
    public static final float ANVIL_COST_MULTIPLIER = 1.0f;

    /** TODO: Ab welchen Kosten der Amboss "Zu teuer!" anzeigt (Vanilla: 40). Noch NICHT aktiv. */
    public static final int ANVIL_TOO_EXPENSIVE_LIMIT = 40;

    /** Verdoppelt das Vanilla-Maximum. */
    public static int scaleMaxLevel(int vanillaMax) {
        return vanillaMax * MAX_LEVEL_MULTIPLIER;
    }
}
