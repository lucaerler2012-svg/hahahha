package com.enchantmentplus.anvil;

import net.minecraft.world.inventory.AnvilMenu;

/**
 * Vorbereiteter Platz für eigene XP-Kosten am Amboss.
 *
 * Aktuell passiert hier absichtlich NICHTS: Es gelten die Vanilla-Kosten.
 * Wichtig zu wissen: Vanilla berechnet die Kosten aus den Leveln, hohe Level werden
 * also automatisch teurer, und ab 40 zeigt der Amboss im Überlebensmodus "Zu teuer!".
 *
 * TODO: XP-Kosten für erweiterte Level (Level über dem Vanilla-Maximum) festlegen.
 *       Ideen: Multiplikator (EnchantmentPlusConfig.ANVIL_COST_MULTIPLIER), Obergrenze
 *       (EnchantmentPlusConfig.ANVIL_TOO_EXPENSIVE_LIMIT) oder feste Kosten pro Zusatzlevel.
 * TODO: Dafür das Feld "cost" des AnvilMenu per @Shadow/@Accessor im AnvilMenuMixin
 *       zugänglich machen (Name und Typ gegen Minecraft 26.3 prüfen).
 */
public final class XpCostPlanner {
    private XpCostPlanner() {}

    /** Wird nach jeder Amboss-Berechnung aufgerufen (siehe AnvilMenuMixin). */
    public static void onAnvilResult(AnvilMenu menu) {
        // TODO: Kosten hier anpassen, sobald das Kostensystem festgelegt ist.
    }
}
