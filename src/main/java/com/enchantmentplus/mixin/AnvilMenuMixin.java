package com.enchantmentplus.mixin;

import com.enchantmentplus.anvil.XpCostPlanner;
import net.minecraft.world.inventory.AnvilMenu;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Haken in den Amboss für spätere XP-Kosten.
 *
 * Das Kombinieren hoher Level funktioniert OHNE diesen Mixin (siehe EnchantmentMixin).
 * Dieser Mixin ist nur die vorbereitete Stelle, um nach jeder Berechnung die Kosten
 * anzupassen. Aktuell ruft er nur XpCostPlanner auf, der nichts verändert.
 *
 * TODO: Methode "createResult" gegen Minecraft 26.3 prüfen. Mit "require = 0" bleibt das
 * Spiel auch dann lauffähig, wenn sie anders heißt (dann ist nur der Haken inaktiv).
 */
@Mixin(AnvilMenu.class)
public abstract class AnvilMenuMixin {

    @Inject(method = "createResult", at = @At("RETURN"), require = 0)
    private void enchantmentplus$afterCreateResult(CallbackInfo ci) {
        XpCostPlanner.onAnvilResult((AnvilMenu) (Object) this);
    }
}
