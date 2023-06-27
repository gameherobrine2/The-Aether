package com.gildedgames.aether.mixin.access;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import net.minecraft.client.Minecraft;

@Mixin(Minecraft.class)
public interface MinecraftClientAccessor{
	 @Accessor("instance")
	 public static Minecraft getMCinstance() {
		 throw new AssertionError();
	 };
}
