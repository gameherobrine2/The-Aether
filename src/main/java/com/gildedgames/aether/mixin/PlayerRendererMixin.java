package com.gildedgames.aether.mixin;

import com.gildedgames.aether.Aether;
import com.gildedgames.aether.item.accessory.Glove;
import com.gildedgames.aether.mixin.access.EntityRenderAccessor;
import com.gildedgames.aether.mixin.access.MinecraftClientAccessor;
import com.matthewperiut.accessoryapi.api.Accessory;
import com.matthewperiut.accessoryapi.api.AccessoryAccess;
import net.minecraft.client.render.entity.PlayerRenderer;
import net.minecraft.client.render.entity.model.Biped;
import net.minecraft.entity.EntityBase;
import net.minecraft.entity.player.PlayerBase;
import net.minecraft.item.ItemInstance;
import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerRenderer.class)
public class PlayerRendererMixin {
	@Inject(method = "render(Lnet/minecraft/entity/EntityBase;DDDFF)V", at = @At(value = "HEAD"), cancellable = true)
    private void renderEntityCustomHEAD(EntityBase d, double e, double f, double g, float h, float par6, CallbackInfo ci) {
        if (d instanceof PlayerBase player)
        {
            if (!Aether.getPlayerHandler(player).visible)
                ci.cancel();
        }
	}

    private Biped gloveModel = new Biped(0.6f);
    @Inject(method = "method_345", at = @At(value = "TAIL"))
	private void firstPersonGloveRender(CallbackInfo ci) {
		try {
        final PlayerBase player = MinecraftClientAccessor.getMCinstance().player;
        ItemInstance item = AccessoryAccess.getAccessory(player, Accessory.Type.glove);
        if (item != null && item.getType() instanceof Glove glove) {
            final float brightness = player.getBrightnessAtEyes(1.0f);
            this.gloveModel.handSwingProgress = 0.0f;
            this.gloveModel.setAngles(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0625f);
            this.gloveModel.field_622.method_1815(0.0625f);
            ((EntityRenderAccessor)this).invokeBindTexture(glove.texture);
            final int colour = glove.getColourMultiplier(0);
            final float red = (colour >> 16 & 0xFF) / 255.0f;
            final float green = (colour >> 8 & 0xFF) / 255.0f;
            final float blue = (colour & 0xFF) / 255.0f;
            GL11.glColor3f(red * brightness, green * brightness, blue * brightness);
            this.gloveModel.field_622.method_1815(0.0625f);
        }
		}catch(Exception e) {e.printStackTrace();}
	}
}
