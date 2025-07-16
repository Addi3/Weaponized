package com.weaponized.core.items;

import com.weaponized.core.entities.CleaverThrownEntity;
import net.minecraft.entity.player.ItemCooldownManager;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

/**
 * @author Loqor
 * @license GNU General Public License v3.0
 */
public abstract class BaseThrownWeaponItem extends SwordItem {
    public BaseThrownWeaponItem(ToolMaterial toolMaterial, int attackDamage, float attackSpeed, Settings settings) {
        super(toolMaterial, attackDamage, attackSpeed, settings);
    }

    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        world.playSound(null, user.getX(), user.getY(), user.getZ(), this.getDefaultSound(), SoundCategory.NEUTRAL, 0.5F, 1F / (world.getRandom().nextFloat() * 0.4F + 0.8F));
        if (!world.isClient) {
            //for (int i = 0; i < 3; i++) {
            CleaverThrownEntity cleaverEntity = new CleaverThrownEntity(world, user, this.getDefaultItem().getDefaultStack());
            // Slightly offset each projectile's angle for a burst effect
            float yawOffset = user.getYaw() /*+ (i - 1) * 5.0F*/; // -5, 0, +5 degrees
            cleaverEntity.setVelocity(user, user.getPitch(), yawOffset, 0.0F, 4F, 0.1f);
            world.spawnEntity(cleaverEntity);
            //}
        }

        user.incrementStat(Stats.USED.getOrCreateStat(this));
        if (!user.getAbilities().creativeMode) {
            itemStack.decrement(1);
        }

        ItemCooldownManager cooldownManager = user.getItemCooldownManager();
        cooldownManager.set(this, 15);

        return TypedActionResult.success(itemStack, world.isClient());
    }

    public Item getDefaultItem() {
        return Items.SNOWBALL;
    }

    public SoundEvent getDefaultSound() {
        return SoundEvents.ITEM_TRIDENT_THROW;
    }
}
