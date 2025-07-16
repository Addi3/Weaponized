package com.weaponized.core.items;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.CropBlock;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class CarrionScytheItem extends HoeItem implements ParticleDrippingItem, DifferingHandModelItem {
    public CarrionScytheItem(ToolMaterial material, int attackDamage, float attackSpeed, Settings settings) {
        super(material, attackDamage, attackSpeed, settings);
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (!target.getWorld().isClient() && !target.getStatusEffects().isEmpty()) {
            target.clearStatusEffects();
            target.addStatusEffect(new StatusEffectInstance(
                    StatusEffects.POISON, 100, 1));
        }
        // Apply reverse knockback
        if (!target.getWorld().isClient()) {
            double dx = attacker.getX() - target.getX();
            double dz = attacker.getZ() - target.getZ();
            double magnitude = Math.sqrt(dx * dx + dz * dz);
            if (magnitude > 0) {
                double strength = 2; // Adjust as needed
                target.addVelocity(dx / magnitude * strength, 0.1, dz / magnitude * strength);
                target.velocityModified = true;
            }
        }
        return super.postHit(stack, target, attacker);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        PlayerEntity player = context.getPlayer();
        World world = context.getWorld();
        if (!world.isClient() && player != null && player.isSneaking()) {
            int radius = 1;
            boolean hasBonemeal = false;
            ItemStack offhand = player.getOffHandStack();
            if (!offhand.isEmpty() && offhand.getItem() instanceof BoneMealItem) {
                hasBonemeal = true;
                radius = 2;
            }
            BlockPos center = context.getBlockPos();
            boolean didGrow = false;
            for (int dx = -radius; dx <= radius; dx++) {
                for (int dz = -radius; dz <= radius; dz++) {
                    BlockPos pos = center.add(dx, 0, dz);
                    BlockState state = world.getBlockState(pos);
                    Block block = state.getBlock();
                    if (block instanceof CropBlock crop) {
                        if (hasBonemeal && offhand.getCount() > 0 && crop.getAge(state) < crop.getMaxAge()) {
                            crop.grow((ServerWorld) world, world.getRandom(), pos, state);
                            offhand.decrement(1);
                            didGrow = true;
                            ((ServerWorld) world).spawnParticles(
                                    ParticleTypes.HAPPY_VILLAGER,
                                    pos.getX() + 0.5, pos.getY() + 1, pos.getZ() + 0.5,
                                    10, 0.3, 0.5, 0.3, 0.1
                            );
                        } else if (!hasBonemeal && crop.isMature(state)) {
                            world.breakBlock(pos, true, player);
                        }
                    }
                }
            }
            if (didGrow) {
                world.playSound(
                        null,
                        center,
                        SoundEvents.BLOCK_GROWING_PLANT_CROP,
                        SoundCategory.BLOCKS,
                        10F,
                        1.0F
                );
            }
            return ActionResult.SUCCESS;
        }
        return super.useOnBlock(context);
    }
}
