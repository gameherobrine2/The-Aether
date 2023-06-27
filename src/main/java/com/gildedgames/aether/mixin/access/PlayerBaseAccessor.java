package com.gildedgames.aether.mixin.access;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import net.minecraft.entity.player.PlayerBase;

@Mixin(PlayerBase.class)
public interface PlayerBaseAccessor {
	@Accessor("sleeping")
	public void setSleeping(boolean b);
}
