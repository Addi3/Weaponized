package com.weaponized;

import com.weaponized.core.WeaponizedEntityTypes;
import com.weaponized.particles.BloodFloorParticle;
import com.weaponized.render.CleaverThrownItemRenderer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.particle.EndRodParticle;
import net.minecraft.client.render.entity.EntityRenderer;

import static com.weaponized.Weaponized.BLOOD_FLOOR_PARTICLE;
import static com.weaponized.Weaponized.BLOOD_PARTICLE;

public class WeaponizedClient implements ClientModInitializer {
	
	@Override
	public void onInitializeClient() {
		registerParticles();
		registerEntityRenderers();
	}

	public void registerParticles() {
		ParticleFactoryRegistry.getInstance().register(BLOOD_PARTICLE, EndRodParticle.Factory::new);
		ParticleFactoryRegistry.getInstance().register(BLOOD_FLOOR_PARTICLE, BloodFloorParticle.BloodFactory::new);
	}

	public void registerEntityRenderers() {
		EntityRendererRegistry.register(WeaponizedEntityTypes.CARRION_CLEAVER, CleaverThrownItemRenderer::new);
	}
}