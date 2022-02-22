package com.gildedgames.aether.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import net.minecraft.client.Minecraft;
import net.minecraft.client.render.Tessellator;
import net.minecraft.entity.Living;

@Mixin(Minecraft.class)
public interface MinecraftClientAccessor{
	 @Accessor("instance")
	 public static Minecraft getMCinstance() {
		 throw new AssertionError();
	 };
}
