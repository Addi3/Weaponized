package com.weaponized;

import com.weaponized.core.WeaponizedItemGroups;
import com.weaponized.core.WeaponizedItems;
import net.fabricmc.api.ModInitializer;

import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Weaponized implements ModInitializer {
	public static final String MOD_ID = "weaponized";

	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public static Identifier id(String path) {
		return new Identifier(MOD_ID, path);
	}

	@Override
	public void onInitialize() {

		WeaponizedItemGroups.initialize();
		WeaponizedItems.registerModItems();
	}
}