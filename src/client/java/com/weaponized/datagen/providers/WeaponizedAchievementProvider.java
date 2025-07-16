package com.weaponized.datagen.providers;

import com.weaponized.Weaponized;
import com.weaponized.core.WeaponizedItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricAdvancementProvider;
import net.minecraft.advancement.Advancement;
import net.minecraft.advancement.AdvancementFrame;
import net.minecraft.advancement.criterion.InventoryChangedCriterion;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.function.Consumer;

public class WeaponizedAchievementProvider extends FabricAdvancementProvider {
    public WeaponizedAchievementProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateAdvancement(Consumer<Advancement> consumer) {
        Advancement root = Advancement.Builder.create()
                .display(WeaponizedItems.CARRION_CLEAVER,
                        Text.translatable("achievement.weaponized.title.root"),
                        Text.translatable("achievement.weaponized.description.root"),
                        new Identifier("textures/block/anvil.png"),
                        AdvancementFrame.TASK, true, true, false)
                .criterion("root", InventoryChangedCriterion.Conditions.items(WeaponizedItems.CARRION_CLEAVER))
                .build(consumer, Weaponized.MOD_ID + "/root");

       
    }
}
