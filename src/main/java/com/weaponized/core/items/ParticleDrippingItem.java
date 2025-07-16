package com.weaponized.core.items;

import com.weaponized.Weaponized;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.decoration.ArmorStandEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.world.World;

public interface ParticleDrippingItem {

    default void particleInventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        // Example offsets (customize as needed)
        
        double offsetX = 0.4;
        double offsetY = -0.9;
        double offsetZ = 1.0;

        // Get entity yaw in radian

        if ((entity instanceof PlayerEntity player &&
                (player.getMainHandStack() == stack || player.getOffHandStack() == stack)) ||
                (entity instanceof ArmorStandEntity armorStand &&
                        (armorStand.getMainHandStack() == stack || armorStand.getOffHandStack() == stack))) {
            float yaw = ((LivingEntity) entity).bodyYaw * ((float) Math.PI / 180F);

            // Rotate offset around Y axis by yaw
            double rotatedX = offsetX * Math.cos(-yaw) - offsetZ * Math.sin(-yaw);
            double rotatedZ = offsetX * Math.sin(-yaw) + offsetZ * Math.cos(-yaw);


            world.addParticle(
                    Weaponized.BLOOD_PARTICLE,
                    entity.getX() - rotatedX,
                    entity.getY() + entity.getStandingEyeHeight() + offsetY,
                    entity.getZ() + rotatedZ,
                    0.0, -0.1, 0.0
            );
            world.addParticle(
                    Weaponized.BLOOD_FLOOR_PARTICLE,
                    entity.getX() - rotatedX,
                    entity.getY() + entity.getStandingEyeHeight() + offsetY,
                    entity.getZ() + rotatedZ,
                    0.0, -0.1, 0.0
            );
        }
    }

}
