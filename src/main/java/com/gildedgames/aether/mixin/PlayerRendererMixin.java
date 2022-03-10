package com.gildedgames.aether.mixin;

import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.gildedgames.aether.Aether;
import com.gildedgames.aether.inventory.InventoryAether;
import com.gildedgames.aether.item.ItemMoreArmor;
import com.gildedgames.aether.mixin.LivingEntityRendererAccessor;
import net.minecraft.client.render.entity.PlayerRenderer;
import net.minecraft.client.render.entity.model.Biped;
import net.minecraft.entity.Living;
import net.minecraft.entity.player.AbstractClientPlayer;
import net.minecraft.entity.player.PlayerBase;
import net.minecraft.item.ItemInstance;
import net.modificationstation.stationapi.mixin.render.client.EntityRendererAccessor;

@Mixin(PlayerRenderer.class)
public class PlayerRendererMixin {
	//TODO: render
	@Inject(method = "render(Lnet/minecraft/entity/player/PlayerBase;DDDFF)V", at = @At(value = "TAIL"))
	public void renderEntityCustom(PlayerBase entity, double x, double y, double z, float f, float f1,CallbackInfo ci) {
		//this.renderEnergyShield((PlayerBase)entity, x, y, z, f, f1);
        this.renderMisc((PlayerBase)entity, x, y, z, f, f1);
	}
	
	public void renderMisc(final PlayerBase entityplayer, final double d, final double d1, final double d2, final float f, final float f1) {
		final ItemInstance itemstack = entityplayer.inventory.getHeldItem();
        this.modelMisc.field_629 = (itemstack != null);
        this.modelMisc.field_630 = entityplayer.method_1373();
        double d3 = d1 - entityplayer.standingEyeHeight;
        if (entityplayer.method_1373() && !(entityplayer instanceof AbstractClientPlayer)) {
            d3 -= 0.125;
        }
        this.doRenderMisc(entityplayer, d, d3, d2, f, f1);
        this.modelMisc.field_630 = false;
        this.modelMisc.field_629 = false;
    }
	
	public void doRenderMisc(final PlayerBase player, final double d, final double d1, final double d2, final float f, final float f1) {
        GL11.glPushMatrix();
        GL11.glEnable(2884);
        this.modelMisc.handSwingProgress = ((LivingEntityRendererAccessor)this).invoke820(player, f1);
        this.modelMisc.isRiding = player.method_1360();
        try {
            final float f2 = player.field_1013 + (player.field_1012 - player.field_1013) * f1;
            final float f3 = player.prevYaw + (player.yaw - player.prevYaw) * f1;
            final float f4 = player.prevPitch + (player.pitch - player.prevPitch) * f1;
            ((LivingEntityRendererAccessor)this).invoke826((Living)player, d, d1, d2);
            final float f5 = ((LivingEntityRendererAccessor)this).invoke828(player, f1);
            ((LivingEntityRendererAccessor)this).invoke824((Living)player, f5, f2, f1);
            final float f6 = 0.0625f;
            GL11.glEnable(32826);
            GL11.glScalef(-1.0f, -1.0f, 1.0f);
            ((LivingEntityRendererAccessor)this).invoke823((Living)player, f1);
            GL11.glTranslatef(0.0f, -24.0f * f6 - 0.0078125f, 0.0f);
            float f7 = player.field_1048 + (player.limbDistance - player.field_1048) * f1;
            final float f8 = player.field_1050 - player.limbDistance * (1.0f - f1);
            if (f7 > 1.0f) {
                f7 = 1.0f;
            }
            GL11.glEnable(3008);
            this.modelMisc.setAngles(f8, f7, f5, f3 - f2, f4, f6);
            final float brightness = player.getBrightnessAtEyes(f);
            final InventoryAether inv = Aether.inv;
            if (inv.slots[0] != null) {
                final ItemMoreArmor pendant = (ItemMoreArmor)inv.slots[0].getType();
                ((EntityRendererAccessor)this).invokeBindTexture(pendant.texture);
                final int colour = pendant.getColourMultiplier(0);
                final float red = (colour >> 16 & 0xFF) / 255.0f;
                final float green = (colour >> 8 & 0xFF) / 255.0f;
                final float blue = (colour & 0xFF) / 255.0f;
                if (pendant.colouriseRender) {
                    GL11.glColor3f(red * brightness, green * brightness, blue * brightness);
                }
                else {
                    GL11.glColor3f(brightness, brightness, brightness);
                }
                this.modelMisc.field_621.method_1815(f6);
            }
            if (inv.slots[6] != null) {
                final ItemMoreArmor glove = (ItemMoreArmor)inv.slots[6].getType();
                ((EntityRendererAccessor)this).invokeBindTexture(glove.texture);
                final int colour = glove.getColourMultiplier(0);
                final float red = (colour >> 16 & 0xFF) / 255.0f;
                final float green = (colour >> 8 & 0xFF) / 255.0f;
                final float blue = (colour & 0xFF) / 255.0f;
                if (glove.colouriseRender) {
                    GL11.glColor3f(red * brightness, green * brightness, blue * brightness);
                }
                else {
                    GL11.glColor3f(brightness, brightness, brightness);
                }
                this.modelMisc.field_623.method_1815(f6);
                this.modelMisc.field_622.method_1815(f6);
            }
            GL11.glDisable(3042);
            GL11.glDisable(32826);
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
        GL11.glPopMatrix();
    }
	
	private Biped modelEnergyShield = new Biped(1.25f);
    private Biped modelCape = new Biped(0.0f);
    private Biped modelMisc = new Biped(0.6f);
}
