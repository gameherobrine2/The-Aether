package com.gildedgames.aether.effect;

import com.gildedgames.aether.entity.animal.EntityAechorPlant;
import com.gildedgames.aether.entity.boss.EntityFireMonster;
import com.gildedgames.aether.entity.boss.EntitySlider;
import com.gildedgames.aether.entity.mobs.EntityCockatrice;
import com.gildedgames.aether.entity.mobs.EntityHomeShot;
import com.gildedgames.aether.entity.mobs.EntityMiniCloud;
import com.gildedgames.aether.entity.mobs.EntitySentry;
import com.gildedgames.aether.entity.projectile.EntityFiroBall;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Minecraft;
import net.minecraft.client.render.Tessellator;
import net.minecraft.client.util.ScreenScaler;
import net.minecraft.entity.EntityBase;
import net.minecraft.level.Level;
import org.lwjgl.opengl.GL11;

public class AetherPoison {
    public static long clock;
    public static final float poisonRed = 1.0f;
    public static final float poisonBlue = 1.0f;
    public static final float poisonGreen = 0.0f;
    public static final float cureRed = 0.0f;
    public static final float cureBlue = 0.1f;
    public static final float cureGreen = 1.0f;
    public static int poisonTime;
    public static final int poisonInterval = 50;
    public static final int poisonDmg = 1;
    public static final int poisonHurts = 10;
    public static final int maxPoisonTime = 500;
    public static Level world;
    public static Minecraft mc;
    public static double rotDFac;
    public static double rotD;
    public static double rotTaper;
    public static double motTaper;
    public static double motD;
    public static double motDFac;
    
    public AetherPoison() {

    }
    
    public static boolean canPoison(final EntityBase entity) {
        return !(entity instanceof EntitySlider) && !(entity instanceof EntitySentry) 
        		&& !(entity instanceof EntityMiniCloud) && !(entity instanceof EntityFireMonster) && !(entity instanceof EntityAechorPlant) 
        		&& !(entity instanceof EntityFiroBall) && !(entity instanceof EntityCockatrice) && !(entity instanceof EntityHomeShot);
    }
    
    public static void distractEntity(final EntityBase ent) {
        final double gauss = AetherPoison.mc.level.rand.nextGaussian();
        final double newMotD = AetherPoison.motDFac * gauss;
        AetherPoison.motD = AetherPoison.motTaper * newMotD + (1.0 - AetherPoison.motTaper) * AetherPoison.motD;
        ent.velocityX += AetherPoison.motD;
        ent.velocityZ += AetherPoison.motD;
        final double newRotD = AetherPoison.rotDFac * gauss;
        AetherPoison.rotD = AetherPoison.rotTaper * newRotD + (1.0 - AetherPoison.rotTaper) * AetherPoison.rotD;
        ent.yaw += (float)AetherPoison.rotD;
        ent.pitch += (float)AetherPoison.rotD;
    }

    @Environment(EnvType.CLIENT)
    public static void tickRender(final Minecraft game) {
        if (AetherPoison.world != game.level || (game.player != null && (game.player.removed || game.player.health <= 0))) {
            AetherPoison.world = game.level;
            AetherPoison.poisonTime = 0;
            return;
        }
        if (AetherPoison.world == null) {
            return;
        }
        if (AetherPoison.poisonTime < 0) {
            ++AetherPoison.poisonTime;
            displayCureEffect();
            return;
        }
        if (AetherPoison.poisonTime == 0) {
            return;
        }
        final long time = AetherPoison.mc.level.getLevelTime();
        final int mod = AetherPoison.poisonTime % 50;
        if (AetherPoison.clock != time) {
            distractEntity(game.player);
            if (mod == 0) {
                game.player.damage(null, 1);
            }
            --AetherPoison.poisonTime;
            AetherPoison.clock = time;
        }
        displayPoisonEffect(mod);
    }
    
    public static boolean afflictPoison() {
        if (AetherPoison.poisonTime < 0) {
            return false;
        }
        AetherPoison.poisonTime = 500;
        AetherPoison.world = AetherPoison.mc.level;
        return true;
    }
    
    public static boolean curePoison() {
        if (AetherPoison.poisonTime == -500) {
            return false;
        }
        AetherPoison.poisonTime = -500;
        AetherPoison.world = AetherPoison.mc.level;
        return true;
    }
    
    public static float getPoisonAlpha(final float f) {
        return f * f / 5.0f + 0.4f;
    }
    
    public static float getCureAlpha(final float f) {
        return f * f / 10.0f + 0.4f;
    }
    
    public static void displayCureEffect() {
        if (AetherPoison.mc.currentScreen != null) {
            return;
        }
        flashColor("%blur%assets/aether/textures/poison/curevignette.png", getCureAlpha(-(float)AetherPoison.poisonTime / 500.0f));
    }
    
    public static void displayPoisonEffect(final int mod) {
        if (AetherPoison.mc.currentScreen != null) {
            return;
        }
        flashColor("%blur%assets/aether/textures/poison/poisonvignette.png", getPoisonAlpha(mod / 50.0f));
    }
    
    public static void flashColor(final String file, final float a) {
        final ScreenScaler scaledresolution = new ScreenScaler(AetherPoison.mc.options, AetherPoison.mc.actualWidth, AetherPoison.mc.actualHeight);
        final int width = scaledresolution.getScaledWidth();
        final int height = scaledresolution.getScaledHeight();
        GL11.glEnable(3042);
        GL11.glDisable(2929);
        GL11.glDepthMask(false);
        GL11.glBlendFunc(770, 771);
        GL11.glColor4f(1.0f, 1.0f, 1.0f, a);
        GL11.glDisable(3008);
        GL11.glBindTexture(3553, AetherPoison.mc.textureManager.getTextureId(file));
        final Tessellator tessellator = Tessellator.INSTANCE;
        tessellator.start();
        tessellator.vertex(0.0, height, -90.0, 0.0, 1.0);
        tessellator.vertex(width, height, -90.0, 1.0, 1.0);
        tessellator.vertex(width, 0.0, -90.0, 1.0, 0.0);
        tessellator.vertex(0.0, 0.0, -90.0, 0.0, 0.0);
        tessellator.draw();
        GL11.glDepthMask(true);
        GL11.glEnable(2929);
        GL11.glEnable(3008);
        GL11.glColor4f(1.0f, 1.0f, 1.0f, a);
    }
    
    static {
        AetherPoison.rotDFac = 0.7853981633974483;
        AetherPoison.rotTaper = 0.125;
        AetherPoison.motTaper = 0.2;
        AetherPoison.motDFac = 0.1;
    }
}
