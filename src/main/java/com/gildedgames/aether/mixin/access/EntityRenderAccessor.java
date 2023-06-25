package com.gildedgames.aether.mixin.access;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

import net.minecraft.client.render.entity.EntityRenderer;

@Mixin(EntityRenderer.class)
public interface EntityRenderAccessor {
	
	@Invoker("bindTexture")
	void invokeBindTexture(String string);
}
