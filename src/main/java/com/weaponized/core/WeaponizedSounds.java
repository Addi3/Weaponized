package com.weaponized.core;

import com.weaponized.Weaponized;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public class WeaponizedSounds {

//cleaver sounds
    public static final SoundEvent CLEAVER_THROW = register("cleaver/throw");
    public static final SoundEvent CLEAVER_HIT = register("cleaver/hit");
    public static final SoundEvent CLEAVER_HIT_GROUND = register("cleaver/hit_ground");




    private static SoundEvent register(String name) {
        Identifier id = Weaponized.id(name);
        return Registry.register(Registries.SOUND_EVENT, id, SoundEvent.of(id));
    }


    public static void init() {
    }
}
