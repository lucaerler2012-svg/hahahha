package com.enchantmentplus.mixin;

import com.enchantmentplus.config.EnchantmentPlusConfig;
import net.minecraft.world.item.enchantment.Enchantment;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * Verdoppelt das Maximal-Level jeder Verzauberung (auch Verzauberungen anderer Mods
 * und Datapacks), weil alles auf Enchantment#getMaxLevel() aufbaut:
 *  - Amboss: Bücher/Items lassen sich bis zum neuen Maximum kombinieren (5+5 -> 6 ... bis 10).
 *  - Kreativ-Bücher: werden von 1 bis zum neuen Maximum erzeugt.
 *  - /enchant: erlaubt Level bis zum neuen Maximum.
 *
 * Bekannte Nebenwirkung: Auch Verzauberungstisch und Beute-Verzauberungen sehen das neue
 * Maximum. Hohe Level kosten dort so viel "Kraft", dass sie praktisch nie erscheinen.
 */
@Mixin(Enchantment.class)
public abstract class EnchantmentMixin {

    @Inject(method = "getMaxLevel", at = @At("RETURN"), cancellable = true)
    private void enchantmentplus$doubleMaxLevel(CallbackInfoReturnable<Integer> cir) {
        cir.setReturnValue(EnchantmentPlusConfig.scaleMaxLevel(cir.getReturnValueI()));
    }
}
