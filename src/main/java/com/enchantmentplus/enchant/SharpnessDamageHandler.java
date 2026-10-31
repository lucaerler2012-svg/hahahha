package com.enchantmentplus.enchant;

import com.enchantmentplus.config.EnchantmentPlusConfig;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import net.minecraft.core.Holder;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;

/**
 * Berechnet den zusätzlichen Schärfe-Schaden für Level über dem Referenz-Level (5).
 *
 * Vanilla: Bonus(L) = 0.5 * L + 0.5   -> L5 = 3.0, L10 = 5.5
 * Wunsch:  Bonus(L) ist proportional zu L, mit Bonus(5) = 3.0 -> Bonus(L) = 0.6 * L
 *          -> L5 = 3.0, L10 = 6.0 (genau doppelt)
 *
 * Wir ersetzen die Vanilla-Berechnung nicht, sondern addieren nur die Differenz
 * (Bonus_neu - Bonus_vanilla). Bis Level 5 ist diese 0, Vanilla bleibt unverändert.
 */
public final class SharpnessDamageHandler {
    private SharpnessDamageHandler() {}

    /** Vanilla-Schärfe-Bonus für ein Level. */
    public static float vanillaBonus(int level) {
        return EnchantmentPlusConfig.VANILLA_SHARPNESS_PER_LEVEL * level
                + EnchantmentPlusConfig.VANILLA_SHARPNESS_BASE;
    }

    /** Zusätzlicher Schaden, der auf das Vanilla-Ergebnis aufgerechnet wird. */
    public static float extraDamage(ItemStack stack) {
        int level = getSharpnessLevel(stack);
        int reference = EnchantmentPlusConfig.SHARPNESS_REFERENCE_LEVEL;
        if (level <= reference) {
            return 0.0f;
        }
        float bonusPerLevel = vanillaBonus(reference) / reference; // 3.0 / 5 = 0.6
        float wanted = bonusPerLevel * level;
        return Math.max(0.0f, wanted - vanillaBonus(level));
    }

    /** Liest das Schärfe-Level vom Item (0, wenn nicht verzaubert). */
    public static int getSharpnessLevel(ItemStack stack) {
        if (stack.isEmpty()) {
            return 0;
        }
        for (Object2IntMap.Entry<Holder<Enchantment>> entry : stack.getEnchantments().entrySet()) {
            if (entry.getKey().is(Enchantments.SHARPNESS)) {
                return entry.getIntValue();
            }
        }
        return 0;
    }
}
