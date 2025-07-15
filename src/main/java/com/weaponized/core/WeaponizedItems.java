package com.weaponized.core;

import com.weaponized.Weaponized;
import com.weaponized.core.Items.CarrionCleaver;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class WeaponizedItems {
    public static final Item CARRION_CLEAVER = registerItem("carrion_cleaver", new CarrionCleaver());

    private static void addItemsToIngredientItemGroup(FabricItemGroupEntries entries) {

    }

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(Weaponized.MOD_ID, name), item);
    }

    public static void registerModItems() {
        Weaponized.LOGGER.info("Registering Mod Items for " + Weaponized.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(WeaponizedItems::addItemsToIngredientItemGroup);
    }
}
