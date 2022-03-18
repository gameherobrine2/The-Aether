package com.gildedgames.aether.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import net.minecraft.client.sound.SoundHelper;

@Mixin(SoundHelper.class)
public interface SoundHelperAccessor {
	
	@Accessor("soundUID")
	int getSoundUID();
}
