package com.enchantmentplus.mixin;

import com.enchantmentplus.enchant.SharpnessDamageHandler;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * Hängt sich an die Schadensberechnung der Verzauberungen und addiert den Zusatzschaden
 * für Schärfe über Level 5 (siehe SharpnessDamageHandler).
 *
 * TODO: Signatur von EnchantmentHelper#modifyDamage gegen Minecraft 26.3 prüfen.
 * "require = 0": Passt die Methode nicht, startet das Spiel trotzdem und Schärfe verhält
 * sich dann wie in Vanilla (nur der Zusatzschaden fehlt). Prüfe das Log auf Mixin-Warnungen.
 */
@Mixin(EnchantmentHelper.class)
public abstract class EnchantmentHelperMixin {

    @Inject(method = "modifyDamage", at = @At("RETURN"), cancellable = true, require = 0)
    private static void enchantmentplus$scaleSharpness(
            ServerLevel level,
            ItemStack tool,
            Entity target,
            DamageSource source,
            float damage,
            CallbackInfoReturnable<Float> cir) {
        float extra = SharpnessDamageHandler.extraDamage(tool);
        if (extra > 0.0f) {
            cir.setReturnValue(cir.getReturnValueF() + extra);
        }
    }
}
