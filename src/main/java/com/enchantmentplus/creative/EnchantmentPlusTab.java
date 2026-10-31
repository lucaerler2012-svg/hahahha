package com.enchantmentplus.creative;

import com.enchantmentplus.EnchantmentPlusMod;
import java.util.Comparator;
import net.fabricmc.fabric.api.creativetab.v1.FabricCreativeModeTab;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;

/**
 * Eigener Kreativ-Tab "Enchantment Plus": Für JEDE Verzauberung ein Buch pro Level,
 * sortiert Verzauberung für Verzauberung und je von Level 1 bis zum (verdoppelten) Maximum.
 *
 * Neue Verzauberungen (auch aus anderen Mods/Datapacks) erscheinen automatisch,
 * weil die Liste aus der Enchantment-Registry gelesen wird.
 */
public final class EnchantmentPlusTab {
    private EnchantmentPlusTab() {}

    public static final ResourceKey<CreativeModeTab> TAB_KEY = ResourceKey.create(
            Registries.CREATIVE_MODE_TAB,
            Identifier.fromNamespaceAndPath(EnchantmentPlusMod.MOD_ID, "enchantments"));

    public static void register() {
        CreativeModeTab tab = FabricCreativeModeTab.builder()
                .title(Component.translatable("enchantmentplus.creative_tab"))
                .icon(() -> new ItemStack(Items.ENCHANTED_BOOK))
                .displayItems((parameters, output) -> {
                    parameters.holders()
                            .lookupOrThrow(Registries.ENCHANTMENT)
                            .listElements()
                            .sorted(Comparator.comparing((Holder.Reference<Enchantment> ref) -> ref.getRegisteredName()))
                            .forEach(holder -> {
                                int max = holder.value().getMaxLevel(); // bereits verdoppelt
                                for (int level = 1; level <= max; level++) {
                                    ItemStack book = new ItemStack(Items.ENCHANTED_BOOK);
                                    book.enchant(holder, level);
                                    output.accept(book);
                                }
                            });
                })
                .build();

        Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, TAB_KEY, tab);
    }
}
