package com.gildedgames.aether.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.gildedgames.aether.Aether;

import net.minecraft.client.Minecraft;
import net.minecraft.client.sound.SoundHelper;
import paulscode.sound.SoundSystem;

@Mixin(Minecraft.class)
public class MinecraftMixin {

	@Inject(method = "createOrLoadWorld", at = @At("HEAD"))
	private void createOrLoadWorld(String string, String string1, long l, CallbackInfo ci) {
		final SoundSystem sound = SoundHelperAccessor.getSoundSystem();
		sound.stop(new StringBuilder().append("sound_").append(Aether.musicId).toString());
        ((SoundHelperAccessor)MinecraftClientAccessor.getMCinstance().soundHelper).setMusicCountdown(6000);
	}
}
