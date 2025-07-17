package com.weaponized.datagen;

import com.joyeuxlib.datagenproviders.JoyeuxLibEnglishLangProvider;
import com.joyeuxlib.datagenproviders.JoyeuxLibSoundProvider;
import com.weaponized.datagen.providers.WeaponizedAchievementProvider;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class WeaponizedDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
		pack.addProvider(WeaponizedAchievementProvider::new);
		pack.addProvider(this::englishTranslationsProvider);
		pack.addProvider(this::addSounds);
	}

	public JoyeuxLibEnglishLangProvider englishTranslationsProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
		JoyeuxLibEnglishLangProvider joyENUSLang = new JoyeuxLibEnglishLangProvider(output);
		joyENUSLang.addTranslation("itemgroup.main","Weaponized");
		joyENUSLang.addTranslation("item.weaponized.carrion_cleaver","Carrion Cleaver");
		joyENUSLang.addTranslation("item.weaponized.carrion_cleaver.tooltip", "An old reliable cleaver, made of flesh and bones.");
		return joyENUSLang;
	}

	public JoyeuxLibSoundProvider addSounds(FabricDataOutput output) {
		JoyeuxLibSoundProvider soundProvider = new JoyeuxLibSoundProvider(output);
		//soundProvider.addSound("", WeaponizedSounds.CLEAVER_THROW);
		//soundProvider.addSound("", WeaponizedSounds.CLEAVER_HIT);
		return soundProvider;
	}
}
