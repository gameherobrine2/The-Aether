package com.gildedgames.aether.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import net.minecraft.client.render.particle.ParticleBase;

@Mixin(ParticleBase.class)
public interface ParticleBaseAccessor {
	@Accessor("field_2635")
	void set2635(int i);
}
