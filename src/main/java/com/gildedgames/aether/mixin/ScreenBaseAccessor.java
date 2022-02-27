package com.gildedgames.aether.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.ScreenBase;

@Mixin(ScreenBase.class)
public interface ScreenBaseAccessor{
	
	@Accessor("minecraft")
	Minecraft getMinecraftInstance();
}
