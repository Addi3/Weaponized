package com.weaponized;

import com.weaponized.core.WeaponizedEntityTypes;
import com.weaponized.core.WeaponizedItemGroups;
import com.weaponized.core.WeaponizedItems;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.ProbabilityConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Weaponized implements ModInitializer {
	public static final String MOD_ID = "weaponized";

	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public static Identifier id(String path) {
		return new Identifier(MOD_ID, path);
	}

	// This DefaultParticleType gets called when you want to use your particle in code.
	public static final DefaultParticleType BLOOD_PARTICLE = FabricParticleTypes.simple();
	public static final DefaultParticleType BLOOD_FLOOR_PARTICLE = FabricParticleTypes.simple();

	// Register our custom particle type in the mod initializer.


	public void registerParticles() {
		Registry.register(Registries.PARTICLE_TYPE, id("blood_particle"), BLOOD_PARTICLE);
		Registry.register(Registries.PARTICLE_TYPE, id("blood_floor_particle"), BLOOD_FLOOR_PARTICLE);
	}

	@Override
	public void onInitialize() {

		WeaponizedItemGroups.initialize();
		WeaponizedEntityTypes.initialize();
		WeaponizedItems.registerModItems();
		registerParticles();
		}
	}
