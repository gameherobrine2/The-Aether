package com.gildedgames.aether.mixin.gui;

import java.util.List;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.ScreenBase;

@Mixin(ScreenBase.class)
public interface ScreenBaseAccessor{
	
	@Accessor("minecraft")
	Minecraft getMinecraftInstance();
	@Accessor("buttons")
	public List getButtons();
	@Invoker("render")
	public void invokeRender(int mouseX, int mouseY, float delta);
}
