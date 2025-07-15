package com.weaponized.core.items;

import com.weaponized.core.WeaponizedToolMaterials;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.world.World;

public class CarrionCleaverItem extends SwordItem implements ParticleDrippingItem {
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
            return 7.0F; // Custom attack damage for Carrion Cleaver - TEMPORARY WHILE WE ADD TOOLSET/ARMORSET BUFFS
        }
        return super.getAttackDamage();
    }
}
