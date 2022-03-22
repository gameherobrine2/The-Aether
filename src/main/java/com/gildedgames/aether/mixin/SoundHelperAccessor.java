package com.gildedgames.aether.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import net.minecraft.client.sound.SoundHelper;
import paulscode.sound.SoundSystem;

@Mixin(SoundHelper.class)
public interface SoundHelperAccessor {
	
	@Accessor("soundUID")
	int getSoundUID();
	@Accessor("soundSystem")
	public static SoundSystem getSoundSystem() {
		throw new AssertionError();
	};
	
	@Accessor("musicCountdown")
	void setMusicCountdown(int i);
}
