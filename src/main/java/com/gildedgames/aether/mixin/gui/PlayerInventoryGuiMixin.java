package com.gildedgames.aether.mixin.gui;

import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.container.PlayerInventory;
import net.minecraft.client.render.RenderHelper;
import net.minecraft.client.render.entity.EntityRenderDispatcher;

@Mixin(PlayerInventory.class)
public class PlayerInventoryGuiMixin {
    private float xSize_lo;
    private float ySize_lo;
    

    @Inject(method = "render", at = @At(value = "HEAD"))
    public void render(final int mouseX, final int mouseY, final float delta,CallbackInfo ci) {
        this.xSize_lo = (float)mouseX;
        this.ySize_lo = (float)mouseY;
    }
    @Overwrite // net.minecraft.client.gui.screen.container.ContainerBase
    public void renderForeground() {
    	
    }
	/**
     * @author
     * @reason
     */
    @Overwrite
    public void renderContainerBackground(float tickDelta)
    {
		Minecraft minecraft = ((ScreenBaseAccessor)this.getClass().cast(this)).getMinecraftInstance();
		int width = PlayerInventory.class.cast(this).width;
		int height = PlayerInventory.class.cast(this).height;
        final int i = minecraft.textureManager.getTextureId("aether:textures/gui/inventory.png");
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        minecraft.textureManager.bindTexture(i);
        final int j = (width - ((ContainerBaseAccessor)PlayerInventory.class.cast(this)).getContainerWidth()) / 2;
        final int k = (height - ((ContainerBaseAccessor)PlayerInventory.class.cast(this)).getContainerHeight()) / 2;
        PlayerInventory.class.cast(this).blit(j, k, 0, 0, ((ContainerBaseAccessor)PlayerInventory.class.cast(this)).getContainerWidth(), ((ContainerBaseAccessor)PlayerInventory.class.cast(this)).getContainerHeight());
        GL11.glEnable(32826);
        GL11.glEnable(2903);
        GL11.glPushMatrix();
        GL11.glTranslatef((float)(j + 33), (float)(k + 75), 50.0f);
        final float f1 = 30.0f;
        GL11.glScalef(-f1, f1, f1);
        GL11.glRotatef(180.0f, 0.0f, 0.0f, 1.0f);
        final float f2 = minecraft.player.field_1012;
        final float f3 = minecraft.player.yaw;
        final float f4 = minecraft.player.pitch;
        final float f5 = j + 33 - this.xSize_lo;
        final float f6 = k + 75 - 50 - this.ySize_lo;
        GL11.glRotatef(135.0f, 0.0f, 1.0f, 0.0f);
        RenderHelper.enableLighting();
        GL11.glRotatef(-135.0f, 0.0f, 1.0f, 0.0f);
        GL11.glRotatef(-(float)Math.atan((double)(f6 / 40.0f)) * 20.0f, 1.0f, 0.0f, 0.0f);
        minecraft.player.field_1012 = (float)Math.atan((double)(f5 / 40.0f)) * 20.0f;
        minecraft.player.yaw = (float)Math.atan((double)(f5 / 40.0f)) * 40.0f;
        minecraft.player.pitch = -(float)Math.atan((double)(f6 / 40.0f)) * 20.0f;
        minecraft.player.field_1617 = 1.0f;
        GL11.glTranslatef(0.0f, minecraft.player.standingEyeHeight, 0.0f);
        EntityRenderDispatcher.INSTANCE.field_2497 = 180.0f;
        EntityRenderDispatcher.INSTANCE.method_1920(minecraft.player, 0.0, 0.0, 0.0, 0.0f, 1.0f);
        minecraft.player.field_1617 = 0.0f;
        minecraft.player.field_1012 = f2;
        minecraft.player.yaw = f3;
        minecraft.player.pitch = f4;
        GL11.glPopMatrix();
        RenderHelper.disableLighting();
        GL11.glDisable(32826);
    }
	
	
}
