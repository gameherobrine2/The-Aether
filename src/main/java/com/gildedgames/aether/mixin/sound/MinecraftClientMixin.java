package com.gildedgames.aether.mixin.sound;

import java.io.File;
import java.io.IOException;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.gildedgames.aether.utils.SkiddedUtils;

import org.spongepowered.asm.mixin.injection.At;
import net.minecraft.client.Minecraft;

@Mixin(Minecraft.class)
public class MinecraftClientMixin {
	private static boolean soundsLoaded = false;
	@Inject(method = "loadSoundFromDir",at = @At("TAIL"))
	public void loadSoundFromDir(String string, File file,CallbackInfo ci) {
		if(!soundsLoaded) {
			getClass().getResourceAsStream("assets/matmos/resources/default_database.xml");
			try {
				Minecraft.class.cast(this).soundHelper.addMusic("aether1.ogg", SkiddedUtils.getFile("assets/aether/stationapi/sounds/sound/aether/music/aether1.ogg"));
				Minecraft.class.cast(this).soundHelper.addMusic("aether2.ogg", SkiddedUtils.getFile("assets/aether/stationapi/sounds/sound/aether/music/aether2.ogg"));
				Minecraft.class.cast(this).soundHelper.addMusic("aether3.ogg", SkiddedUtils.getFile("assets/aether/stationapi/sounds/sound/aether/music/aether3.ogg"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			soundsLoaded = true;
		}
	}
}
