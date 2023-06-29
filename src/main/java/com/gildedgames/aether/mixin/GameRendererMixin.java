package com.gildedgames.aether.mixin;

import java.util.List;
import java.util.Random;

import com.gildedgames.aether.mixin.access.EntityBaseAccessor;
import com.gildedgames.aether.mixin.access.LivingAccessor;
import com.gildedgames.aether.mixin.access.MinecraftClientAccessor;
import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.gildedgames.aether.Aether;
import com.gildedgames.aether.effect.AetherPoison;
import com.gildedgames.aether.entity.base.EntityProjectileBase;
import com.gildedgames.aether.entity.base.IAetherBoss;
import com.gildedgames.aether.registry.AetherAchievements;
import com.gildedgames.aether.registry.AetherItems;

import org.spongepowered.asm.mixin.injection.At;

import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.render.Tessellator;
import net.minecraft.client.util.ScreenScaler;
import net.minecraft.entity.EntityBase;
import net.minecraft.entity.player.PlayerBase;
import net.minecraft.entity.projectile.Arrow;
import net.minecraft.item.ItemInstance;
import net.minecraft.level.Level;
import net.minecraft.sortme.GameRenderer;

@Mixin(GameRenderer.class)
public class GameRendererMixin {
	@Shadow
	private Minecraft minecraft;
	private static Random rand = new Random();
	private int updateCounter;
	@Inject(method = "method_1844", at = @At("TAIL"))
	private void onRenderTick(float delta, CallbackInfo ci) { //skidded from BetaLoader
		if(minecraft.level != null) {
			final PlayerBase player = minecraft.player;
			if (Aether.getCurrentDimension() == 2) {
                final boolean enteredAether = minecraft.statFileWriter.isAchievementUnlocked(AetherAchievements.enterAether);
                if (!enteredAether) {
                    Aether.giveAchievement(AetherAchievements.enterAether, player);
                    player.inventory.addStack(new ItemInstance(AetherItems.LoreBook, 1, 2));
                    player.inventory.addStack(new ItemInstance(AetherItems.CloudParachute, 1));
                    minecraft.level.playSound((EntityBase)player, "random.pop", 0.2f, 1.0f);
                }
            }
			AetherItems.tick(minecraft);
			renderBossHP();
			renderHearts();
			// this.repShieldUpdate(minecraft); todo: move to EnergyShield.class
			AetherPoison.tickRender(minecraft);
			++this.updateCounter;
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
            GL11.glBindTexture(3553, mc.textureManager.getTextureId("aether:textures/gui/bossHPBar.png"));
            GL11.glEnable(3042);
            GL11.glBlendFunc(775, 769);
            GL11.glColor3f(1.0f, 1.0f, 1.0f);
            GL11.glDisable(3042);
            this.drawTexturedModalRect(width / 2 - 128, 12, 0, 16, 256, 32);
            final int w = (int)(((IAetherBoss)Aether.currentBoss).getBossHP() / (float)((IAetherBoss)Aether.currentBoss).getBossMaxHP() * 256.0f);
            this.drawTexturedModalRect(width / 2 - 128, 12, 0, 0, w, 16);
        }
    }
	private void renderHearts() {
        final Minecraft mc = MinecraftClientAccessor.getMCinstance();
        final ScreenScaler scaledresolution = new ScreenScaler(mc.options, mc.actualWidth, mc.actualHeight);
        final int width = scaledresolution.getScaledWidth();
        final int height = scaledresolution.getScaledHeight();
        GL11.glBindTexture(3553, mc.textureManager.getTextureId("/gui/icons.png"));
        GL11.glEnable(3042);
        GL11.glBlendFunc(775, 769);
        GL11.glColor3f(1.0f, 1.0f, 1.0f);
        GL11.glDisable(3042);
        boolean flag1 = mc.player.field_1613 / 3 % 2 == 1;
        if (mc.player.field_1613 < 10) {
            flag1 = false;
        }
        final int halfHearts = mc.player.health - 20;
        final int prevHalfHearts = mc.player.field_1037 - 20;
        this.rand.setSeed((long)(this.updateCounter * 312871));
        if (mc.interactionManager.method_1722()) {
            for (int heart = 0; heart < Aether.getPlayerHandler(mc.player).maxHealth / 2 - 10; ++heart) {
                int yPos = height - 42;
                if (mc.player.isInFluid(Material.WATER)) {
                    yPos -= 9;
                }
                int k5 = 0;
                if (flag1) {
                    k5 = 1;
                }
                final int xPos = width / 2 - 91 + heart * 8;
                if (mc.player.health <= 4) {
                    yPos += this.rand.nextInt(2);
                }
                this.drawTexturedModalRect(xPos, yPos, 16 + k5 * 9, 0, 9, 9);
                if (flag1) {
                    if (heart * 2 + 1 < prevHalfHearts) {
                        this.drawTexturedModalRect(xPos, yPos, 70, 0, 9, 9);
                    }
                    if (heart * 2 + 1 == prevHalfHearts) {
                        this.drawTexturedModalRect(xPos, yPos, 79, 0, 9, 9);
                    }
                }
                if (heart * 2 + 1 < halfHearts) {
                    this.drawTexturedModalRect(xPos, yPos, 52, 0, 9, 9);
                }
                if (heart * 2 + 1 == halfHearts) {
                    this.drawTexturedModalRect(xPos, yPos, 61, 0, 9, 9);
                }
            }
        }
        GL11.glDisable(3042);
    }
    /*
	public void repShieldUpdate(final Minecraft game) {
        final Level world = game.level;
        if (world != null && !world.isServerSide) {
            final List list1 = world.players;
            if (list1 != null && list1.size() > 0) {
                for (int i = 0; i < list1.size(); ++i) {
                    final PlayerBase player = (PlayerBase)list1.get(i);
                    boolean flag = false;
                    final net.minecraft.entity.player.PlayerInventory inv = player.inventory;
                    ItemInstance shieldItem = null;
                    if (inv.armour.length > 4) {
                        flag = (inv != null && inv.armour[6] != null && inv.armour[6].itemId == AetherItems.RepShield.id);
                        shieldItem = inv.armour[6];
                    }
                    if (flag && (player.onGround || (player.vehicle != null && player.vehicle.onGround)) && ((LivingAccessor)player).get1029() == 0.0f && ((LivingAccessor)player).get1060() == 0.0f) {
                        if (!game.options.thirdPerson && player == game.player) {
                            this.renderShieldEffect(game);
                        }
                        final List list2 = world.getEntities(player, player.boundingBox.expand(4.0, 4.0, 4.0));
                        for (int j = 0; j < list2.size() && shieldItem != null && shieldItem.getDamage() < 500; ++j) {
                            final EntityBase entity = (EntityBase)list2.get(j);
                            boolean flag2 = false;
                            if (entity instanceof EntityProjectileBase && entity.distanceTo(player) < 2.5f && (entity.prevX != entity.x || entity.prevY != entity.y || entity.prevZ != entity.z)) {
                                final EntityProjectileBase proj = (EntityProjectileBase)entity;
                                if (proj.shooter == null || proj.shooter != player) {
                                    final EntityBase dick = proj.shooter;
                                    proj.shooter = player;
                                    flag2 = true;
                                    double a;
                                    double b;
                                    double c;
                                    if (dick != null) {
                                        a = proj.x - dick.x;
                                        b = proj.boundingBox.minY - dick.boundingBox.minY;
                                        c = proj.z - dick.z;
                                    }
                                    else {
                                        a = player.x - proj.x;
                                        b = player.y - proj.y;
                                        c = player.z - proj.z;
                                    }
                                    final double d = Math.sqrt(a * a + b * b + c * c);
                                    a /= -d;
                                    b /= -d;
                                    c /= -d;
                                    proj.velocityX = a * 0.75;
                                    proj.velocityY = b * 0.75 + 0.05;
                                    proj.velocityZ = c * 0.75;
                                    proj.setArrowHeading(proj.velocityX, proj.velocityY, proj.velocityZ, 0.8f, 0.5f);
                                    world.playSound((EntityBase)proj, "note.snare", 1.0f, ((((EntityBaseAccessor)player).getRand().nextFloat() - ((EntityBaseAccessor)player).getRand().nextFloat()) * 0.4f + 0.8f) * 1.1f);
                                    for (int k = 0; k < 12; ++k) {
                                        double d2 = -proj.velocityX * 0.15000000596046448 + (((EntityBaseAccessor)proj).getRand().nextFloat() - 0.5f) * 0.05f;
                                        double e1 = -proj.velocityY * 0.15000000596046448 + (((EntityBaseAccessor)proj).getRand().nextFloat() - 0.5f) * 0.05f;
                                        double f1 = -proj.velocityZ * 0.15000000596046448 + (((EntityBaseAccessor)proj).getRand().nextFloat() - 0.5f) * 0.05f;
                                        d2 *= 0.625;
                                        e1 *= 0.625;
                                        f1 *= 0.625;
                                        world.addParticle("flame", proj.x, proj.y, proj.z, d2, e1, f1);
                                    }
                                }
                            }
                            else if (entity instanceof Arrow && entity.distanceTo(player) < 2.5f && (entity.prevX != entity.x || entity.prevY != entity.y || entity.prevZ != entity.z)) {
                                final Arrow proj2 = (Arrow)entity;
                                if (proj2.owner == null || proj2.owner != player) {
                                    final EntityBase dick = proj2.owner;
                                    proj2.owner = player;
                                    flag2 = true;
                                    double a;
                                    double b;
                                    double c;
                                    if (dick != null) {
                                        a = proj2.x - dick.x;
                                        b = proj2.boundingBox.minY - dick.boundingBox.minY;
                                        c = proj2.z - dick.z;
                                    }
                                    else {
                                        a = player.x - proj2.x;
                                        b = player.y - proj2.y;
                                        c = player.z - proj2.z;
                                    }
                                    final double d = Math.sqrt(a * a + b * b + c * c);
                                    a /= -d;
                                    b /= -d;
                                    c /= -d;
                                    proj2.velocityX = a * 0.75;
                                    proj2.velocityY = b * 0.75 + 0.15;
                                    proj2.velocityZ = c * 0.75;
                                    proj2.method_1291(proj2.velocityX, proj2.velocityY, proj2.velocityZ, 0.8f, 0.5f);
                                    world.playSound((EntityBase)proj2, "note.snare", 1.0f, ((((EntityBaseAccessor)player).getRand().nextFloat() - ((EntityBaseAccessor)player).getRand().nextFloat()) * 0.4f + 0.8f) * 1.1f);
                                    for (int k = 0; k < 12; ++k) {
                                        double d2 = -proj2.velocityX * 0.15000000596046448 + (((EntityBaseAccessor)proj2).getRand().nextFloat() - 0.5f) * 0.05f;
                                        double e1 = -proj2.velocityY * 0.15000000596046448 + (((EntityBaseAccessor)proj2).getRand().nextFloat() - 0.5f) * 0.05f;
                                        double f1 = -proj2.velocityZ * 0.15000000596046448 + (((EntityBaseAccessor)proj2).getRand().nextFloat() - 0.5f) * 0.05f;
                                        d2 *= 0.625;
                                        e1 *= 0.625;
                                        f1 *= 0.625;
                                        world.addParticle("flame", proj2.x, proj2.y, proj2.z, d2, e1, f1);
                                    }
                                }
                            }
                            if (flag2 && shieldItem != null) {
                                shieldItem.applyDamage(1, null);
                                if (shieldItem.getDamage() >= 500) {
                                    player.inventory.armour[6] = null;
                                }
                            }
                        }
                    }
                }
            }
        }
    }*/
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
