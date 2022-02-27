package com.gildedgames.aether.mixin;

import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.gildedgames.aether.Aether;
import com.gildedgames.aether.entity.base.IAetherBoss;
import com.gildedgames.aether.registry.AetherItems;

import org.spongepowered.asm.mixin.injection.At;
import net.minecraft.client.Minecraft;
import net.minecraft.client.render.Tessellator;
import net.minecraft.client.util.ScreenScaler;
import net.minecraft.sortme.GameRenderer;

@Mixin(GameRenderer.class)
public class GameRendererMixin {
	@Shadow
	private Minecraft minecraft;
	
	@Inject(method = "method_1844", at = @At("TAIL"))
	private void betaloader_onRenderTick(float delta, CallbackInfo ci) { //skidded from BetaLoader
		if(minecraft.level != null) {
			AetherItems.tick(minecraft);
			renderBossHP();
		} //onTickInGame =P
	}
	
	private void renderBossHP()
    {
		if (Aether.currentBoss != null) {
			if(!(Aether.currentBoss instanceof IAetherBoss)) {
				return;
			}
            final Minecraft mc = MinecraftClientAccessor.getMCinstance();
            final ScreenScaler scaledresolution = new ScreenScaler(mc.options, mc.actualWidth, mc.actualHeight);
            final int width = scaledresolution.getScaledWidth();
            final int height = scaledresolution.getScaledHeight();
            final String s = ((IAetherBoss)Aether.currentBoss).getBossTitle();
            mc.textRenderer.drawTextWithShadow(s, width / 2 - mc.textRenderer.getTextWidth(s) / 2, 2, -1);
            GL11.glBindTexture(3553, mc.textureManager.getTextureId("/assets/aether/stationapi/textures/gui/bossHPBar.png"));
            GL11.glEnable(3042);
            GL11.glBlendFunc(775, 769);
            GL11.glColor3f(1.0f, 1.0f, 1.0f);
            GL11.glDisable(3042);
            this.drawTexturedModalRect(width / 2 - 128, 12, 0, 16, 256, 32);
            final int w = (int)(((IAetherBoss)Aether.currentBoss).getBossHP() / (float)((IAetherBoss)Aether.currentBoss).getBossMaxHP() * 256.0f);
            this.drawTexturedModalRect(width / 2 - 128, 12, 0, 0, w, 16);
        }
    }
	private float zLevel = -90;
	public void drawTexturedModalRect(final int i, final int j, final int k, final int l, final int i1, final int j1) {
        final float f = 0.00390625f;
        final float f2 = 0.00390625f;
        final Tessellator tessellator = Tessellator.INSTANCE;
        tessellator.start();
        tessellator.vertex(i + 0, j + j1, this.zLevel, (k + 0) * f, (l + j1) * f2);
        tessellator.vertex(i + i1, j + j1, this.zLevel, (k + i1) * f, (l + j1) * f2);
        tessellator.vertex(i + i1, j + 0, this.zLevel, (k + i1) * f, (l + 0) * f2);
        tessellator.vertex(i + 0, j + 0, this.zLevel, (k + 0) * f, (l + 0) * f2);
        tessellator.draw();
    }
    
}
