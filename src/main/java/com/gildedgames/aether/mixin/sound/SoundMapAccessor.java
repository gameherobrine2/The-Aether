package com.gildedgames.aether.mixin.sound;

import java.util.List;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import net.minecraft.client.sound.SoundMap;
/*DEBUG-ONLY*/
@Mixin(SoundMap.class)
public interface SoundMapAccessor {
	@Accessor("soundList")
	public List getSoundList();
}
