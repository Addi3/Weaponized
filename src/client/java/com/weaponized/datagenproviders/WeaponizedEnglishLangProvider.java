package com.weaponized.datagenproviders;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.item.ItemGroup;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

import static com.weaponized.core.WeaponizedItemGroups.WEAPONIZED_ITEM_GROUP;

public class WeaponizedEnglishLangProvider extends FabricLanguageProvider {
    public WeaponizedEnglishLangProvider(FabricDataOutput dataGenerator, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {

        super(dataGenerator);
    }

    @Override
    public void generateTranslations(TranslationBuilder translationBuilder) {
translationBuilder.add("itemgroup.main","Weaponized");
translationBuilder.add("item.weaponized.carrion_cleaver","Carrion Cleaver");
    }
}
