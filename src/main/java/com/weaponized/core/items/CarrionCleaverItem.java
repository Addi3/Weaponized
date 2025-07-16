package com.weaponized.core.items;

import com.weaponized.core.WeaponizedItems;
import com.weaponized.core.WeaponizedToolMaterials;
import net.minecraft.entity.Entity;
import net.minecraft.item.*;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.world.World;

/**
 * @author Loqor
 * @license GNU General Public License v3.0
 */
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
        return SoundEvents.ITEM_TRIDENT_THROW;
    }
}
