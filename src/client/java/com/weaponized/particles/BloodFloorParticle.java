package com.weaponized.particles;


import net.minecraft.client.particle.*;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.util.math.MathHelper;

public class BloodFloorParticle extends SpriteBillboardParticle {
    private final SpriteProvider spriteProvider;

    BloodFloorParticle(ClientWorld world, double x, double y, double z, double velocityX, double velocityY, double velocityZ, SpriteProvider spriteProvider) {
        super(world, x, y, z, (double)0.0F, (double)0.0F, (double)0.0F);
        this.velocityMultiplier = 0.96F;
        this.spriteProvider = spriteProvider;
        float f = 2.5F;
        this.velocityX *= (double)0.1F;
        this.velocityY *= (double)0.1F;
        this.velocityZ *= (double)0.1F;
        this.velocityX += velocityX;
        this.velocityY += velocityY;
        this.velocityZ += velocityZ;
        float g = 1.0F - (float)(Math.random() * (double)0.3F);
        this.red = g;
        this.green = g;
        this.blue = g;
        this.scale *= 1.0F;
        int i = (int)((double)8.0F / (Math.random() * 0.8 + 0.3));
        this.maxAge = (int)Math.max((float)i * 10F, 1.0F);
        this.collidesWithWorld = true;
        this.setSpriteForAge(spriteProvider);
    }

    public ParticleTextureSheet getType() {
        return ParticleTextureSheet.PARTICLE_SHEET_TRANSLUCENT;
    }

    public float getSize(float tickDelta) {
        return this.scale * MathHelper.clamp(((float)this.age + tickDelta) / (float)this.maxAge * 32.0F, 0.0F, 1.0F);
    }

    public void tick() {
        super.tick();
        if (!this.dead) {
            this.setSpriteForAge(this.spriteProvider);
            PlayerEntity playerEntity = this.world.getClosestPlayer(this.x, this.y, this.z, (double)2.0F, false);
            if (playerEntity != null) {
                double d = playerEntity.getY();
                if (this.y > d) {
                    this.y += (d - this.y) * 0.2;
                    this.velocityY += (playerEntity.getVelocity().y - this.velocityY) * 0.2;
                    this.setPos(this.x, this.y, this.z);
                }
            }
        }

    }


    public static class BloodFactory implements ParticleFactory<DefaultParticleType> {
        private final SpriteProvider spriteProvider;

        public BloodFactory(SpriteProvider spriteProvider) {
            this.spriteProvider = spriteProvider;
        }

        public Particle createParticle(DefaultParticleType defaultParticleType, ClientWorld clientWorld, double d, double e, double f, double g, double h, double i) {
            return new BloodFloorParticle(clientWorld, d, e, f, g, h, i, this.spriteProvider);
        }
    }
    }
