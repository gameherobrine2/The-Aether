package com.gildedgames.aether.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import com.gildedgames.aether.item.base.IReach;

import net.minecraft.client.SinglePlayerClientInteractionManager;
import net.minecraft.item.ItemBase;
import net.minecraft.item.ItemInstance;

@Mixin(SinglePlayerClientInteractionManager.class)
public class SinglePlayerClientInteractionManagerMixin { /*Who named original class like that*/
	
	@Inject(method = "getBlockReachDistance", at = @At(value = "HEAD"), locals = LocalCapture.CAPTURE_FAILSOFT, cancellable = true)
	public void getBlockReachDistance(CallbackInfoReturnable<Float> ci) {
		ItemInstance item = MinecraftClientAccessor.getMCinstance().player.inventory.getHeldItem();
		if(item == null) {
			return;
		}
		if(item.getType() instanceof IReach) {
			ci.setReturnValue(((IReach)item.getType()).getReach(MinecraftClientAccessor.getMCinstance().player.inventory.getHeldItem()));
		}
	}
	
}
