package com.gildedgames.aether.mixin;

import com.gildedgames.aether.AetherMod;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.InGame;
import net.minecraft.client.util.ScreenScaler;
import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Random;

@Mixin(InGame.class)
public class InGameGuiMixin
{
    @Shadow
    private Minecraft minecraft;
    @Shadow
    private Random rand;

    public void customBlit(InGame instance, int a, int b, int c, int d, int e, int f)
    {
        if (AetherMod.getPlayerHandler(minecraft.player).maxHealth > 20)
        {
            instance.blit(a, b - 11, c, d, e, f);
        }
        else
        {
            instance.blit(a, b - 1, c, d, e, f);
        }
    }

    @Redirect(method = "renderHud", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/InGame;blit(IIIIII)V", ordinal = 11))
    public void blit1(InGame instance, int i, int j, int k, int l, int m, int n)
    {
        customBlit(instance, i, j, k, l, m, n);
    }

    @Redirect(method = "renderHud", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/InGame;blit(IIIIII)V", ordinal = 12))
    public void blit2(InGame instance, int i, int j, int k, int l, int m, int n)
    {
        customBlit(instance, i, j, k, l, m, n);
    }

    @Inject(method = "renderHud", at = @At(value = "TAIL"))
    public void renderExtraHud(float bl, boolean i, int j, int par4, CallbackInfo ci)
    {
        InGame instance = (InGame) (Object) this;
        renderHearts(instance);
        renderBossHP(instance);
    }

    private void renderBossHP(InGame instance)
    {
        /* todo: find a boss within a 50x50x50 block area, render their bar.
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
            GL11.glBindTexture(3553, mc.textureManager.getTextureId("aether:textures/gui/bossHPBar.png"));
            GL11.glEnable(3042);
            GL11.glBlendFunc(775, 769);
            GL11.glColor3f(1.0f, 1.0f, 1.0f);
            GL11.glDisable(3042);
            instance.blit(width / 2 - 128, 12, 0, 16, 256, 32);
            final int w = (int)(((IAetherBoss)Aether.currentBoss).getBossHP() / (float)((IAetherBoss)Aether.currentBoss).getBossMaxHP() * 256.0f);
            instance.blit(width / 2 - 128, 12, 0, 0, w, 16);
        }*/
    }

    private void renderHearts(InGame instance)
    {
        int maxHealth = AetherMod.getPlayerHandler(minecraft.player).maxHealth;
        if (!(maxHealth > 20))
            return;
        final ScreenScaler scaledresolution = new ScreenScaler(minecraft.options, minecraft.actualWidth, minecraft.actualHeight);
        final int width = scaledresolution.getScaledWidth();
        final int height = scaledresolution.getScaledHeight();
        GL11.glBindTexture(3553, minecraft.textureManager.getTextureId("/gui/icons.png"));
        GL11.glEnable(3042);
        GL11.glBlendFunc(775, 769);
        GL11.glColor3f(1.0f, 1.0f, 1.0f);
        GL11.glDisable(3042);
        boolean flag1 = minecraft.player.field_1613 / 3 % 2 == 1;
        if (minecraft.player.field_1613 < 10)
        {
            flag1 = false;
        }
        final int halfHearts = minecraft.player.health - 20;
        final int prevHalfHearts = minecraft.player.field_1037 - 20;
        if (minecraft.interactionManager.method_1722())
        {
            for (int heart = 0; heart < maxHealth / 2 - 10; ++heart)
            {
                int yPos = height - 42;
                int k5 = 0;
                if (flag1)
                {
                    k5 = 1;
                }
                final int xPos = width / 2 - 91 + heart * 8;
                if (minecraft.player.health <= 4)
                {
                    yPos += this.rand.nextInt(2);
                }
                instance.blit(xPos, yPos, 16 + k5 * 9, 0, 9, 9);
                if (flag1)
                {
                    if (heart * 2 + 1 < prevHalfHearts)
                    {
                        instance.blit(xPos, yPos, 70, 0, 9, 9);
                    }
                    if (heart * 2 + 1 == prevHalfHearts)
                    {
                        instance.blit(xPos, yPos, 79, 0, 9, 9);
                    }
                }
                if (heart * 2 + 1 < halfHearts)
                {
                    instance.blit(xPos, yPos, 52, 0, 9, 9);
                }
                if (heart * 2 + 1 == halfHearts)
                {
                    instance.blit(xPos, yPos, 61, 0, 9, 9);
                }
            }
        }
        GL11.glDisable(3042);
    }
}
