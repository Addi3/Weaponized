package com.weaponized.core;

import com.weaponized.Weaponized;
import com.weaponized.core.items.CarrionCleaverItem;
import com.weaponized.core.items.CarrionScytheItem;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class WeaponizedItems {
    public static final Item CARRION_CLEAVER = registerItem("carrion_cleaver", new CarrionCleaverItem(WeaponizedToolMaterials.CARRION_CLEAVER, 5,
            1.8F, new Item.Settings()));
    public static final Item CARRION_SCYTHE = registerItem("carrion_scythe", new CarrionScytheItem(WeaponizedToolMaterials.CARRION_SCYTHE, 0,
            2.0F, new Item.Settings()));

    private static void addItemsToIngredientItemGroup(FabricItemGroupEntries entries) {

    }

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Weaponized.id(name), item);
    }

    public static void registerModItems() {
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(WeaponizedItems::addItemsToIngredientItemGroup);
    }
}
