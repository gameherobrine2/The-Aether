package com.gildedgames.aether.mixin;

import com.gildedgames.aether.mixin.access.EntityRenderAccessor;
import com.gildedgames.aether.mixin.access.LivingAccessor;
import com.gildedgames.aether.mixin.access.LivingEntityRendererAccessor;
import com.gildedgames.aether.mixin.access.MinecraftClientAccessor;
import net.minecraft.entity.EntityBase;
import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.gildedgames.aether.Aether;
import com.gildedgames.aether.inventory.InventoryAether;
import com.gildedgames.aether.item.ItemMoreArmor;
import com.gildedgames.aether.registry.AetherItems;

import net.minecraft.client.render.entity.PlayerRenderer;
import net.minecraft.client.render.entity.model.Biped;
import net.minecraft.entity.Living;
import net.minecraft.entity.player.AbstractClientPlayer;
import net.minecraft.entity.player.PlayerBase;
import net.minecraft.item.ItemInstance;
import net.minecraft.util.maths.MathHelper;

@Mixin(PlayerRenderer.class)
public class PlayerRendererMixin {
	//TODO: render
	//@Inject(method = "render(Lnet/minecraft/entity/player/PlayerBase;DDDFF)V", at = @At(value = "HEAD"), locals = LocalCapture.CAPTURE_FAILSOFT, cancellable = true)
	@Inject(method = "render(Lnet/minecraft/entity/EntityBase;DDDFF)V", at = @At(value = "HEAD"))
    private void renderEntityCustomHEAD(EntityBase d, double e, double f, double g, float h, float par6, CallbackInfo ci) {
		if(Aether.invisible(MinecraftClientAccessor.getMCinstance().player)) ci.cancel();
	}
	@Inject(method = "render(Lnet/minecraft/entity/EntityBase;DDDFF)V", at = @At(value = "TAIL"))
	private void renderEntityCustom(EntityBase d, double e, double f, double g, float h, float par6, CallbackInfo ci) {
		try {
			this.renderEnergyShield((PlayerBase)d, e, f, g, h, par6);
			this.renderMisc((PlayerBase)d, e, f, g, h, par6);
		}catch(Exception ex) {ex.printStackTrace();}
	}
	@Inject(method = "method_827", at = @At(value = "TAIL"))
	private void method_827_handle(Living f, float par2, CallbackInfo ci) {
		renderCape((PlayerBase)f,par2);
	}
	public void renderEnergyShield(final PlayerBase entityplayer, final double d, final double d1, final double d2, final float f, final float f1) {
        final ItemInstance itemstack = entityplayer.inventory.getHeldItem();
        this.modelEnergyShield.field_629 = (itemstack != null);
        this.modelEnergyShield.field_630 = entityplayer.method_1373();
        double d3 = d1 - entityplayer.standingEyeHeight;
        if (entityplayer.method_1373() && !(entityplayer instanceof AbstractClientPlayer)) {
            d3 -= 0.125;
        }
        this.doRenderEnergyShield(entityplayer, d, d3, d2, f, f1);
        this.modelEnergyShield.field_630 = false;
        this.modelEnergyShield.field_629 = false;
    }
	@Inject(method = "method_345", at = @At(value = "TAIL"))
	private void method345_handle(CallbackInfo ci) {
		try {
		if(Aether.getPlayerHandler().inv == null) {return;}
        final PlayerBase player = MinecraftClientAccessor.getMCinstance().player;
        final InventoryAether inv = Aether.getPlayerHandler(player).inv;
        if (inv.slots[6] != null) {
            final float brightness = player.getBrightnessAtEyes(1.0f);
            this.modelMisc.handSwingProgress = 0.0f;
            this.modelMisc.setAngles(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0625f);
            this.modelMisc.field_622.method_1815(0.0625f);
            final ItemMoreArmor glove = (ItemMoreArmor)inv.slots[6].getType();
            ((EntityRenderAccessor)this).invokeBindTexture(glove.texture);
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
            this.modelMisc.field_622.method_1815(0.0625f);
        }
		}catch(Exception e) {e.printStackTrace();}
	}
	private void renderCape(final PlayerBase entityplayer, final float f) {
        final InventoryAether inv = Aether.getPlayerHandler(entityplayer).inv;
        if (inv.slots[1] != null) {
            final ItemInstance cape = inv.slots[1];
            //if (cape.itemId == AetherItems.RepShield.id) {
            //    return;
            //}
            ((EntityRenderAccessor)this).invokeBindTexture(((ItemMoreArmor)cape.getType()).texture);
            GL11.glPushMatrix();
            GL11.glTranslatef(0.0f, 0.0f, 0.125f);
            final double d = entityplayer.field_530 + (entityplayer.field_533 - entityplayer.field_530) * f - (entityplayer.prevX + (entityplayer.x - entityplayer.prevX) * f);
            final double d2 = entityplayer.field_531 + (entityplayer.field_534 - entityplayer.field_531) * f - (entityplayer.prevY + (entityplayer.y - entityplayer.prevY) * f);
            final double d3 = entityplayer.field_532 + (entityplayer.field_535 - entityplayer.field_532) * f - (entityplayer.prevZ + (entityplayer.z - entityplayer.prevZ) * f);
            final float f2 = entityplayer.field_1013 + (entityplayer.field_1012 - entityplayer.field_1013) * f;
            final double d4 = MathHelper.sin(f2 * 3.141593f / 180.0f);
            final double d5 = -MathHelper.cos(f2 * 3.141593f / 180.0f);
            float f3 = (float)d2 * 10.0f;
            if (f3 < -6.0f) {
                f3 = -6.0f;
            }
            if (f3 > 32.0f) {
                f3 = 32.0f;
            }
            float f4 = (float)(d * d4 + d3 * d5) * 100.0f;
            final float f5 = (float)(d * d5 - d3 * d4) * 100.0f;
            if (f4 < 0.0f) {
                f4 = 0.0f;
            }
            final float f6 = entityplayer.field_524 + (entityplayer.field_525 - entityplayer.field_524) * f;
            f3 += MathHelper.sin((entityplayer.field_1634 + (entityplayer.field_1635 - entityplayer.field_1634) * f) * 6.0f) * 32.0f * f6;
            if (entityplayer.method_1373()) {
                f3 += 25.0f;
            }
            GL11.glRotatef(6.0f + f4 / 2.0f + f3, 1.0f, 0.0f, 0.0f);
            GL11.glRotatef(f5 / 2.0f, 0.0f, 0.0f, 1.0f);
            GL11.glRotatef(-f5 / 2.0f, 0.0f, 1.0f, 0.0f);
            GL11.glRotatef(180.0f, 0.0f, 1.0f, 0.0f);
            this.modelCape.method_606(0.0625f);
            GL11.glPopMatrix();
        }
    }
	public void renderMisc(final PlayerBase entityplayer, final double d, final double d1, final double d2, final float f, final float f1) {
		if(Aether.getPlayerHandler(entityplayer).inv == null) {return;}
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
            final InventoryAether inv = Aether.getPlayerHandler(player).inv;
            if (inv.slots[0] != null) {
                final ItemMoreArmor pendant = (ItemMoreArmor)inv.slots[0].getType();
                ((EntityRenderAccessor)this).invokeBindTexture(pendant.texture);
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
                ((EntityRenderAccessor)this).invokeBindTexture(glove.texture);
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
	private void doRenderEnergyShield(final Living entityliving, final double d, final double d1, final double d2, final float f, final float f1) {
        GL11.glPushMatrix();
        GL11.glEnable(2884);
        this.modelEnergyShield.handSwingProgress = ((LivingEntityRendererAccessor)this).invoke820(entityliving, f1);
        this.modelEnergyShield.isRiding = entityliving.method_1360();
        try {
            final float f2 = entityliving.field_1013 + (entityliving.field_1012 - entityliving.field_1013) * f1;
            final float f3 = entityliving.prevYaw + (entityliving.yaw - entityliving.prevYaw) * f1;
            final float f4 = entityliving.prevPitch + (entityliving.pitch - entityliving.prevPitch) * f1;
            ((LivingEntityRendererAccessor)this).invoke826(entityliving, d, d1, d2);
            final float f5 = ((LivingEntityRendererAccessor)this).invoke828(entityliving, f1);
            ((LivingEntityRendererAccessor)this).invoke824(entityliving, f5, f2, f1);
            final float f6 = 0.0625f;
            GL11.glEnable(32826);
            GL11.glScalef(-1.0f, -1.0f, 1.0f);
            ((LivingEntityRendererAccessor)this).invoke823(entityliving, f1);
            GL11.glTranslatef(0.0f, -24.0f * f6 - 0.0078125f, 0.0f);
            float f7 = entityliving.field_1048 + (entityliving.limbDistance - entityliving.field_1048) * f1;
            final float f8 = entityliving.field_1050 - entityliving.limbDistance * (1.0f - f1);
            if (f7 > 1.0f) {
                f7 = 1.0f;
            }
            GL11.glEnable(3008);
            if (this.setEnergyShieldBrightness((PlayerBase)entityliving, 0, f1)) {
                this.modelEnergyShield.render(f8, f7, f5, f3 - f2, f4, f6);
                GL11.glDisable(3042);
                GL11.glEnable(3008);
            }
            GL11.glDisable(32826);
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
        GL11.glEnable(2884);
        GL11.glPopMatrix();
    }
	protected boolean setEnergyShieldBrightness(final PlayerBase player, final int i, final float f) {
        if (i != 0) {
            return false;
        }
        final InventoryAether inv = Aether.getPlayerHandler(player).inv;
        final boolean flag = inv != null && inv.slots[2] != null && inv.slots[2].itemId == AetherItems.RepShield.id;
        if (flag) {
            if ((player.onGround || (player.vehicle != null && player.vehicle.onGround)) && ((LivingAccessor)player).get1029() == 0.0f && ((LivingAccessor)player).get1060() == 0.0f) {
            	((EntityRenderAccessor)this).invokeBindTexture("aether:textures/entity/energyGlow.png");
                GL11.glEnable(2977);
                GL11.glEnable(3042);
                GL11.glBlendFunc(770, 771);
                GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
            }
            else {
                GL11.glEnable(2977);
                GL11.glEnable(3042);
                GL11.glBlendFunc(770, 771);
                ((EntityRenderAccessor)this).invokeBindTexture("aether:textures/entity/energyNotGlow.png");
            }
            return true;
        }
        return false;
    }
	
	private Biped modelEnergyShield = new Biped(1.25f);
    private Biped modelCape = new Biped(0.0f);
    private Biped modelMisc = new Biped(0.6f);
}
