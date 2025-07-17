package com.weaponized.core.items;

import com.weaponized.Weaponized;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.decoration.ArmorStandEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * @author Loqor
 * @license GNU General Public License v3.0
 */
public interface ParticleDrippingItem {

    default void particleInventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {

        double offsetX = 0.4;
        double offsetY = -0.9;
        double offsetZ = 1.0;

        double bloodFloorOffsetY = -1.5;


        if ((entity instanceof PlayerEntity player &&
                (player.getMainHandStack() == stack || player.getOffHandStack() == stack)) ||
                (entity instanceof ArmorStandEntity armorStand &&
                        (armorStand.getMainHandStack() == stack || armorStand.getOffHandStack() == stack))) {


            if (entity instanceof PlayerEntity) {
                PlayerEntity player = (PlayerEntity) entity;


                if (player.getMainHandStack() == stack) {
                    offsetX = 0.4;
                    offsetY = -1.0;
                    offsetZ = 0.5;
                }

                else if (player.getOffHandStack() == stack) {
                    offsetX = -0.3;
                    offsetY = -1.0;
                    offsetZ = 0.5;
                }
            }


            float yaw = ((LivingEntity) entity).bodyYaw * ((float) Math.PI / 180F);


            double rotatedX = offsetX * Math.cos(-yaw) - offsetZ * Math.sin(-yaw);
            double rotatedZ = offsetX * Math.sin(-yaw) + offsetZ * Math.cos(-yaw);


            for (int i = 0; i < 2; i++) {
                if (world.random.nextFloat() < 0.1f) { // 70% chance per particle
                    world.addParticle(
                            Weaponized.BLOOD_PARTICLE,
                            entity.getX() - rotatedX + (world.random.nextDouble() - 0.5) * 0.1,
                            entity.getY() + entity.getStandingEyeHeight() + offsetY,
                            entity.getZ() + rotatedZ + (world.random.nextDouble() - 0.5) * 0.1,
                            0.0, -0.1, 0.0
                    );
                }
            }


            for (int i = 0; i < 0.5f; i++) {
                if (world.getTime() % 20 == 0 && world.random.nextFloat() < 0.1f) { // once per second, 10% chance
                    world.addParticle(
                            Weaponized.BLOOD_FLOOR_PARTICLE,
                            entity.getX() - rotatedX + (world.random.nextDouble() - 0.5) * 0.2,
                            entity.getY() + entity.getStandingEyeHeight() + bloodFloorOffsetY,
                            entity.getZ() + rotatedZ + (world.random.nextDouble() - 0.5) * 0.2,
                            0.0, -0.1, 0.0
                    );
                }
            }
        }
    }
}