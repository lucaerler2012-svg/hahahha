package com.enchantmentplus;

import com.enchantmentplus.creative.EnchantmentPlusTab;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Einstiegspunkt der Mod.
 *
 * Die eigentliche Logik steckt in den Mixins (siehe com.enchantmentplus.mixin):
 *  - EnchantmentMixin       -> verdoppelt das Maximal-Level jeder Verzauberung
 *  - EnchantmentHelperMixin -> skalierender Schärfe-Schaden
 *  - AnvilMenuMixin         -> vorbereiteter Haken für spätere XP-Kosten (TODO)
 *
 * Der Amboss erlaubt höhere Level automatisch, weil er das Maximum über
 * Enchantment#getMaxLevel() abfragt, und genau das verdoppeln wir.
 */
public class EnchantmentPlusMod implements ModInitializer {
    public static final String MOD_ID = "enchantmentplus";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        EnchantmentPlusTab.register();
        LOGGER.info("Enchantment Plus geladen: Maximal-Level x{}", com.enchantmentplus.config.EnchantmentPlusConfig.MAX_LEVEL_MULTIPLIER);
    }
}
