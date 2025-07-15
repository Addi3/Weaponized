package com.weaponized.core;


import com.weaponized.Weaponized;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class WeaponizedItemGroups {
    public static final ItemGroup WEAPONIZED_ITEM_GROUP = Registry.register(Registries.ITEM_GROUP,
            new Identifier(Weaponized.MOD_ID, "main"),
            FabricItemGroup.builder().displayName(Text.translatable("itemgroup.main"))
                    .icon(() -> new ItemStack(Items.ACACIA_BOAT)).entries((displayContext, entries) -> {

                        entries.add(Items.DIAMOND);


                    }).build());


    public static void registerItemGroups() {
        Weaponized.LOGGER.info("Registering Item Groups for " + Weaponized.MOD_ID);
    }

    public static void initialize() {

    }
}
