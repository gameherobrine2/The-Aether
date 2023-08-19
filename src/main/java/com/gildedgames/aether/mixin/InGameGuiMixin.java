package com.gildedgames.aether.mixin;

import com.gildedgames.aether.AetherMod;
import com.gildedgames.aether.entity.base.IAetherBoss;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.InGame;
import net.minecraft.client.util.ScreenScaler;
import net.minecraft.entity.EntityBase;
import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;
import java.util.Random;

@Mixin(InGame.class)
public class InGameGuiMixin
{
    @Shadow
    private Minecraft minecraft;
    @Shadow
    private Random rand;

    @Unique
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

    @Unique
    int ticksToBossCheck = 0;

    @Unique
    EntityBase boss = null;

    @Unique
    private static double distance(double x1, double y1, double z1, double x2, double y2, double z2) {
        return Math.sqrt(Math.pow(x2 - x1, 2) +
                Math.pow(y2 - y1, 2) +
                Math.pow(z2 - z1, 2));
    }

    @Unique
    private void renderBossHP(InGame instance)
    {
        ticksToBossCheck++;

        if (ticksToBossCheck == 100)
        {
            ticksToBossCheck = 0;
            boss = null;
            for (EntityBase entity : (List<EntityBase>) minecraft.level.entities)
            {
                if (entity instanceof IAetherBoss)
                {
                    if (distance(entity.x, entity.y, entity.z, minecraft.player.x, minecraft.player.y, minecraft.player.z) > 25)
                        continue;

                    boss = entity;
                    break;
                }
            }
        }

        if (boss != null) {
            final ScreenScaler scaledresolution = new ScreenScaler(minecraft.options, minecraft.actualWidth, minecraft.actualHeight);
            final int width = scaledresolution.getScaledWidth();
            final int height = scaledresolution.getScaledHeight();
            final String s = ((IAetherBoss) boss).getBossTitle();
            minecraft.textRenderer.drawTextWithShadow(s, width / 2 - minecraft.textRenderer.getTextWidth(s) / 2, 2, -1);
            GL11.glBindTexture(3553, minecraft.textureManager.getTextureId("aether:textures/gui/bossHPBar.png"));
            GL11.glEnable(3042);
            GL11.glBlendFunc(775, 769);
            GL11.glColor3f(1.0f, 1.0f, 1.0f);
            GL11.glDisable(3042);
            instance.blit(width / 2 - 128, 12, 0, 16, 256, 32);
            final int w = (int)(((IAetherBoss) boss).getBossHP() / (float)((IAetherBoss) boss).getBossMaxHP() * 256.0f);
            instance.blit(width / 2 - 128, 12, 0, 0, w, 16);
        }
    }

    @Unique
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
