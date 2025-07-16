package com.weaponized.core;

import com.weaponized.Weaponized;
import com.weaponized.core.items.CarrionCleaverItem;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class WeaponizedItems {
    public static final Item CARRION_CLEAVER = registerItem("carrion_cleaver", new CarrionCleaverItem(WeaponizedToolMaterials.CARRION_CLEAVER, 7,
            1.8F, new Item.Settings()));

    private static void addItemsToIngredientItemGroup(FabricItemGroupEntries entries) {

    }

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Weaponized.id(name), item);
    }

    public static void registerModItems() {
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(WeaponizedItems::addItemsToIngredientItemGroup);
    }
}
