package com.gildedgames.aether.mixin;

import com.gildedgames.aether.AetherMod;
import net.minecraft.client.render.entity.PlayerRenderer;
import net.minecraft.entity.EntityBase;
import net.minecraft.entity.player.PlayerBase;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerRenderer.class)
public class PlayerRendererMixin
{
    @Inject(method = "render(Lnet/minecraft/entity/EntityBase;DDDFF)V", at = @At(value = "HEAD"), cancellable = true)
    private void renderEntityCustomHEAD(EntityBase d, double e, double f, double g, float h, float par6, CallbackInfo ci)
    {
        if (d instanceof PlayerBase player)
        {
            if (!AetherMod.getPlayerHandler(player).visible)
                ci.cancel();
        }
    }
}
