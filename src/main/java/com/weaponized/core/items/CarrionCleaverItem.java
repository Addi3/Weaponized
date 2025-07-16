package com.weaponized.core.items;

import com.weaponized.core.WeaponizedItems;
import com.weaponized.core.WeaponizedSounds;
import com.weaponized.core.WeaponizedToolMaterials;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.sound.SoundEvent;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * @author Loqor
 * @license GNU General Public License v3.0
 */

// TODO: implement Joyeuxlib ItemHitSound

public class CarrionCleaverItem extends BaseThrownWeaponItem implements ParticleDrippingItem {
    public CarrionCleaverItem(ToolMaterial toolMaterial, int attackDamage, float attackSpeed, Settings settings) {
        super(toolMaterial, attackDamage, attackSpeed, settings);
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        ParticleDrippingItem.super.particleInventoryTick(stack, world, entity, slot, selected);
    }

    @Override
    public float getAttackDamage() {
        if (getMaterial() == WeaponizedToolMaterials.CARRION_CLEAVER) {
            return 7.0F;
        }
        return super.getAttackDamage();
    }

    @Override
    public Item getDefaultItem() {
        return WeaponizedItems.CARRION_CLEAVER;
    }

    @Override
    public SoundEvent getDefaultSound() {
        return WeaponizedSounds.CLEAVER_THROW;
    }

//    @Override
//    public void playHitSound(PlayerEntity player) {
//        player.playSound(WeaponizedSounds.CLEAVER_HIT, 1.0F, (float) (1.0F + player.getRandom().nextGaussian() / 10f));
//    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        super.appendTooltip(stack, world, tooltip, context);

        tooltip.add(Text.translatable("item.weaponized.carrion_cleaver.tooltip").formatted(Formatting.ITALIC, Formatting.LIGHT_PURPLE));
    }
}
