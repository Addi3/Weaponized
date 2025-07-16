package com.weaponized.core.entities;

import com.weaponized.Weaponized;
import com.weaponized.core.WeaponizedEntityTypes;
import com.weaponized.core.WeaponizedItems;
import com.weaponized.core.WeaponizedSounds;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.particle.ItemStackParticleEffect;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;

public class CleaverThrownEntity extends PersistentProjectileEntity {

    private static final TrackedData<Boolean> IN_GROUND = DataTracker.registerData(CleaverThrownEntity.class, TrackedDataHandlerRegistry.BOOLEAN);

    private ItemStack cleaverStack;
    private boolean dealtDamage;
    public int returnTimer;

    public CleaverThrownEntity(EntityType<? extends CleaverThrownEntity> entityType, World world) {
        super(entityType, world);
        this.cleaverStack =  new ItemStack(WeaponizedItems.CARRION_CLEAVER);
    }

    public CleaverThrownEntity(World world, LivingEntity owner) {
        super(WeaponizedEntityTypes.CARRION_CLEAVER, owner, world);
        this.cleaverStack =  new ItemStack(WeaponizedItems.CARRION_CLEAVER);
    }

    public CleaverThrownEntity(World world, double x, double y, double z) {
        super(WeaponizedEntityTypes.CARRION_CLEAVER, x, y, z, world);
        this.cleaverStack =  new ItemStack(WeaponizedItems.CARRION_CLEAVER);
    }

    public CleaverThrownEntity(World world, PlayerEntity player, ItemStack itemStack) {
        super(WeaponizedEntityTypes.CARRION_CLEAVER, player, world);
        this.cleaverStack = itemStack;
    }

    @Override
    protected void initDataTracker() {
        super.initDataTracker();
        this.dataTracker.startTracking(IN_GROUND, false);
    }

    private ParticleEffect getParticleParameters() {
        ItemStack itemStack = this.asItemStack();
        return itemStack.isEmpty() ? ParticleTypes.ELECTRIC_SPARK : new ItemStackParticleEffect(ParticleTypes.ITEM, itemStack);
    }

    public void setInGround(boolean value) {
        this.dataTracker.set(IN_GROUND, value);
    }

    public boolean isInGroundTracked() {
        return this.dataTracker.get(IN_GROUND);
    }

    public void handleStatus(byte status) {
        if (status == 3) {
            ParticleEffect particleEffect = this.getParticleParameters();

            for(int i = 0; i < 8; ++i) {
                this.getWorld().addParticle(particleEffect, this.getX(), this.getY(), this.getZ(), 0.0F, 0.0F, 0.0F);
            }
        }

    }

    @Override
    public boolean hasNoGravity() {
        return true;
    }

    public ItemStack asItemStack() {
        return WeaponizedItems.CARRION_CLEAVER.getDefaultStack();
    }

    protected void onEntityHit(EntityHitResult entityHitResult) {
        Entity entity = entityHitResult.getEntity();
        float f = 8.0F;
        if (entity instanceof LivingEntity livingEntity) {
            f += EnchantmentHelper.getAttackDamage(this.cleaverStack, livingEntity.getGroup());
        }

        Entity entity2 = this.getOwner();
        DamageSource damageSource = this.getDamageSources().thrown(this, entity2 == null ? this : entity2);
        this.dealtDamage = true;
        SoundEvent soundEvent = WeaponizedSounds.CLEAVER_HIT;
        if (entity.damage(damageSource, 7.0f)) {
            if (entity.getType() == EntityType.ENDERMAN) {
                return;
            }

            if (entity instanceof LivingEntity livingEntity2) {
                if (entity2 instanceof LivingEntity) {
                    EnchantmentHelper.onUserDamaged(livingEntity2, entity2);
                    EnchantmentHelper.onTargetDamaged((LivingEntity)entity2, livingEntity2);
                }

                this.onHit(livingEntity2);
            }
        }

        this.setVelocity(this.getVelocity().multiply(-0.01, -0.1, -0.01));
        float g = 1.0F;

        this.playSound(soundEvent, g, 1.0F);
    }

    @Override
    public void tick() {
        if (this.inGroundTime > 4) {
            this.dealtDamage = true;
        }

        Entity entity = this.getOwner();
        boolean shouldReturnQuickly = this.age > 30 && !this.inGround; // 6 seconds at 20 ticks/sec

        // Sync tracked inGround value
        this.setInGround(this.inGround);

        if ((this.dealtDamage || this.isNoClip() || shouldReturnQuickly) && entity != null && !this.inGround) {
            if (!this.isOwnerAlive()) {
                if (!this.getWorld().isClient && this.pickupType == PickupPermission.ALLOWED) {
                    this.dropStack(this.asItemStack(), 0.1F);
                }
                this.discard();
            } else {
                this.setNoClip(true);
                Vec3d vec3d = entity.getEyePos().subtract(this.getPos());
                this.setYaw((float)(Math.toDegrees(Math.atan2(vec3d.z, vec3d.x)) - 90.0));
                this.setPitch((float)(-Math.toDegrees(Math.atan2(vec3d.y, Math.sqrt(vec3d.x * vec3d.x + vec3d.z * vec3d.z)))));
                double speedMultiplier = shouldReturnQuickly ? 0.25 : 0.05 * 0.1d;
                this.setPos(this.getX(), this.getY() + vec3d.y * 0.015 * 0.1d, this.getZ());
                if (this.getWorld().isClient) {
                    this.lastRenderY = this.getY();
                }
                this.setVelocity(this.getVelocity().multiply(0.95).add(vec3d.normalize().multiply(speedMultiplier)));
                if (this.returnTimer == 0) {
                    this.playSound(SoundEvents.ITEM_TRIDENT_RETURN, 8.0F, 1.7F);
                }
                ++this.returnTimer;
            }
        }
        super.tick();
        Vec3d vec3d = this.getVelocity();
        double d = this.getX() + vec3d.x;
        double e = this.getY() + vec3d.y;
        double f = this.getZ() + vec3d.z;
        this.getWorld().addParticle(isInGroundTracked() ? Weaponized.BLOOD_FLOOR_PARTICLE : Weaponized.BLOOD_PARTICLE, d - vec3d.x * 0.25 +
                this.random.nextDouble() * 0.6 - 0.3, e - vec3d.y * 0.25 - 0.5, f - vec3d.z * 0.25 +
                this.random.nextDouble() * 0.6 - 0.3, vec3d.x, vec3d.y, vec3d.z);
    }

    private boolean isOwnerAlive() {
        Entity entity = this.getOwner();
        if (entity != null && entity.isAlive()) {
            return !(entity instanceof ServerPlayerEntity) || !entity.isSpectator();
        } else {
            return false;
        }
    }

    public boolean isInGround() {
        return this.inGround;
    }

    @Nullable
    protected EntityHitResult getEntityCollision(Vec3d currentPosition, Vec3d nextPosition) {
        return this.dealtDamage ? null : super.getEntityCollision(currentPosition, nextPosition);
    }

    protected boolean tryPickup(PlayerEntity player) {
        return this.inGround && super.tryPickup(player) || this.isNoClip() && this.isOwner(player) && player.getInventory().insertStack(this.asItemStack());
    }

    protected SoundEvent getHitSound() {
        return WeaponizedSounds.CLEAVER_HIT;
    }

    public void onPlayerCollision(PlayerEntity player) {
        if (this.isOwner(player) || this.getOwner() == null) {
            super.onPlayerCollision(player);
        }

    }

    public void readCustomDataFromNbt(NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);
        if (nbt.contains("Cleaver", 10)) {
            this.cleaverStack = ItemStack.fromNbt(nbt.getCompound("Trident"));
        }

        this.dealtDamage = nbt.getBoolean("DealtDamage");
        if (nbt.contains("InGround")) {
            this.setInGround(nbt.getBoolean("InGround"));
        }
    }

    public void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
        nbt.put("Cleaver", this.cleaverStack.writeNbt(new NbtCompound()));
        nbt.putBoolean("DealtDamage", this.dealtDamage);
        nbt.putBoolean("InGround", this.isInGroundTracked());
    }

    protected float getDragInWater() {
        return 0.99F;
    }

    public void age() {
        if (this.pickupType != PickupPermission.ALLOWED) {
            super.age();
        }

    }

    public boolean shouldRender(double cameraX, double cameraY, double cameraZ) {
        return true;
    }
}