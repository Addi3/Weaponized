package com.weaponized.datagen.providers;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class WeaponizedEnglishLangProvider extends FabricLanguageProvider {
    public WeaponizedEnglishLangProvider(FabricDataOutput dataGenerator, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {

        super(dataGenerator);
    }

    @Override
    public void generateTranslations(TranslationBuilder translationBuilder) {
        translationBuilder.add("itemgroup.main","Weaponized");
        translationBuilder.add("item.weaponized.carrion_cleaver","Carrion Cleaver");
        translationBuilder.add("item.weaponized.carrion_cleaver.tooltip", "An old reliable cleaver, made of flesh and bones.");
    }
}
