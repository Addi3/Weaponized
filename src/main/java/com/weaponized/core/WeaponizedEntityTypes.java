package com.weaponized.core;

import com.weaponized.Weaponized;
import com.weaponized.core.entities.CleaverThrownEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class WeaponizedEntityTypes {

    public static final EntityType<CleaverThrownEntity> CARRION_CLEAVER = register(Registries.ENTITY_TYPE, "carrion_cleaver",
            EntityType.Builder.<CleaverThrownEntity>create(CleaverThrownEntity::new, SpawnGroup.MISC)
                    .setDimensions(0.5f, 0.5f)
                    .build("carrion_cleaver"));

    public static <V, T extends V> T register(Registry<V> registry, String name, T entry) {
        return Registry.register(registry, new Identifier(Weaponized.MOD_ID, name), entry);
    }

    public static void initialize() {}
}
