package com.gildedgames.aether.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.gildedgames.aether.registry.AetherItems;

import org.spongepowered.asm.mixin.injection.At;
import net.minecraft.client.Minecraft;
import net.minecraft.sortme.GameRenderer;

@Mixin(GameRenderer.class)
public class GameRendererMixin {
	@Shadow
	private Minecraft minecraft;
	
	@Inject(method = "method_1844", at = @At("TAIL"))
	private void betaloader_onRenderTick(float delta, CallbackInfo ci) { //skidded from BetaLoader
		if(minecraft.level != null) {AetherItems.tick(minecraft);} //onTickInGame =P
	}
}
