package com.weaponized.datagen;

import com.weaponized.datagen.providers.WeaponizedEnglishLangProvider;
import com.weaponized.datagen.providers.WeaponizedModelProvider;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class WeaponizedDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
		pack.addProvider(WeaponizedEnglishLangProvider::new);
		pack.addProvider(WeaponizedModelProvider::new);
	}
}
