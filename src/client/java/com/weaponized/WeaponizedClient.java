package com.weaponized;

import com.weaponized.particles.BloodFloorParticle;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.minecraft.client.particle.EndRodParticle;

import static com.weaponized.Weaponized.BLOOD_FLOOR_PARTICLE;
import static com.weaponized.Weaponized.BLOOD_PARTICLE;

public class WeaponizedClient implements ClientModInitializer {
	
	@Override
	public void onInitializeClient() {

		registerParticles();


}

	public void registerParticles() {
		ParticleFactoryRegistry.getInstance().register(BLOOD_PARTICLE, EndRodParticle.Factory::new);
		ParticleFactoryRegistry.getInstance().register(BLOOD_FLOOR_PARTICLE, BloodFloorParticle.BloodFactory::new);
	}
}