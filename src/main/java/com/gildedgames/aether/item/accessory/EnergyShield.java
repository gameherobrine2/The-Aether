package com.gildedgames.aether.item.accessory;

import com.gildedgames.aether.entity.base.EntityProjectileBase;
import com.gildedgames.aether.mixin.access.EntityRenderAccessor;
import com.gildedgames.aether.mixin.access.LivingAccessor;
import com.matthewperiut.accessoryapi.api.Accessory;
import com.matthewperiut.accessoryapi.api.render.AccessoryRenderer;
import com.matthewperiut.accessoryapi.api.render.HasCustomRenderer;
import com.matthewperiut.accessoryapi.impl.mixin.client.LivingEntityRendererAccessor;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.Minecraft;
import net.minecraft.client.render.Tessellator;
import net.minecraft.client.render.entity.PlayerRenderer;
import net.minecraft.client.render.entity.model.Biped;
import net.minecraft.client.util.ScreenScaler;
import net.minecraft.entity.EntityBase;
import net.minecraft.entity.Living;
import net.minecraft.entity.player.PlayerBase;
import net.minecraft.entity.projectile.Arrow;
import net.minecraft.item.ItemInstance;
import net.minecraft.level.Level;
import net.modificationstation.stationapi.api.registry.Identifier;
import net.modificationstation.stationapi.api.template.item.TemplateItemBase;
import org.lwjgl.opengl.GL11;

import java.util.List;
import java.util.Optional;

public class EnergyShield extends TemplateItemBase implements Accessory, HasCustomRenderer
{
    public EnergyShield(Identifier identifier)
    {
        super(identifier);
        this.setMaxStackSize(1);
        this.setDurability(500);
    }

    @Override
    public String[] getAccessoryTypes(ItemInstance item)
    {
        return new String[]{"shield"};
    }

    @Override
    public ItemInstance tickWhileWorn(PlayerBase playerBase, ItemInstance itemInstance)
    {
        final Level world = playerBase.level;
        if (world != null && !world.isServerSide)
        {
            final List list1 = world.players;
            if (list1 != null && list1.size() > 0)
            {
                for (int i = 0; i < list1.size(); ++i)
                {
                    final PlayerBase player = (PlayerBase) list1.get(i);
                    boolean flag = false;
                    final net.minecraft.entity.player.PlayerInventory inv = player.inventory;
                    ItemInstance shieldItem = null;
                    if (inv.armour.length > 4)
                    {
                        //flag = (inv != null && inv.armour[6] != null && inv.armour[6].itemId == AetherItems.RepShield.id);
                        shieldItem = inv.armour[6];
                    }
                    if (flag && (player.onGround || (player.vehicle != null && player.vehicle.onGround)) && ((LivingAccessor) player).get1029() == 0.0f && ((LivingAccessor) player).get1060() == 0.0f)
                    {
                        /*if (!game.options.thirdPerson && player == game.player) {
                            this.renderShieldEffect(game);
                        }*/
                        final List list2 = world.getEntities(player, player.boundingBox.expand(4.0, 4.0, 4.0));
                        for (int j = 0; j < list2.size() && shieldItem != null && shieldItem.getDamage() < 500; ++j)
                        {
                            final EntityBase entity = (EntityBase) list2.get(j);
                            boolean flag2 = false;
                            if (entity instanceof EntityProjectileBase && entity.distanceTo(player) < 2.5f && (entity.prevX != entity.x || entity.prevY != entity.y || entity.prevZ != entity.z))
                            {
                                final EntityProjectileBase proj = (EntityProjectileBase) entity;
                                if (proj.shooter == null || proj.shooter != player)
                                {
                                    final EntityBase dick = proj.shooter;
                                    proj.shooter = player;
                                    flag2 = true;
                                    double a;
                                    double b;
                                    double c;
                                    if (dick != null)
                                    {
                                        a = proj.x - dick.x;
                                        b = proj.boundingBox.minY - dick.boundingBox.minY;
                                        c = proj.z - dick.z;
                                    }
                                    else
                                    {
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
                                    world.playSound((EntityBase) proj, "note.snare", 1.0f, (rand.nextFloat() - rand.nextFloat() * 0.4f + 0.8f) * 1.1f);
                                    for (int k = 0; k < 12; ++k)
                                    {
                                        double d2 = -proj.velocityX * 0.15000000596046448 + (rand.nextFloat() - 0.5f) * 0.05f;
                                        double e1 = -proj.velocityY * 0.15000000596046448 + (rand.nextFloat() - 0.5f) * 0.05f;
                                        double f1 = -proj.velocityZ * 0.15000000596046448 + (rand.nextFloat() - 0.5f) * 0.05f;
                                        d2 *= 0.625;
                                        e1 *= 0.625;
                                        f1 *= 0.625;
                                        world.addParticle("flame", proj.x, proj.y, proj.z, d2, e1, f1);
                                    }
                                }
                            }
                            else if (entity instanceof Arrow && entity.distanceTo(player) < 2.5f && (entity.prevX != entity.x || entity.prevY != entity.y || entity.prevZ != entity.z))
                            {
                                final Arrow proj2 = (Arrow) entity;
                                if (proj2.owner == null || proj2.owner != player)
                                {
                                    final EntityBase dick = proj2.owner;
                                    proj2.owner = player;
                                    flag2 = true;
                                    double a;
                                    double b;
                                    double c;
                                    if (dick != null)
                                    {
                                        a = proj2.x - dick.x;
                                        b = proj2.boundingBox.minY - dick.boundingBox.minY;
                                        c = proj2.z - dick.z;
                                    }
                                    else
                                    {
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
                                    world.playSound((EntityBase) proj2, "note.snare", 1.0f, (rand.nextFloat() - rand.nextFloat() * 0.4f + 0.8f) * 1.1f);
                                    for (int k = 0; k < 12; ++k)
                                    {
                                        double d2 = -proj2.velocityX * 0.15000000596046448 + (rand.nextFloat() - 0.5f) * 0.05f;
                                        double e1 = -proj2.velocityY * 0.15000000596046448 + (rand.nextFloat() - 0.5f) * 0.05f;
                                        double f1 = -proj2.velocityZ * 0.15000000596046448 + (rand.nextFloat() - 0.5f) * 0.05f;
                                        d2 *= 0.625;
                                        e1 *= 0.625;
                                        f1 *= 0.625;
                                        world.addParticle("flame", proj2.x, proj2.y, proj2.z, d2, e1, f1);
                                    }
                                }
                            }
                            if (flag2 && shieldItem != null)
                            {
                                shieldItem.applyDamage(1, null);
                                if (shieldItem.getDamage() >= 500)
                                {
                                    player.inventory.armour[6] = null;
                                }
                            }
                        }
                    }
                }
            }
        }
        return itemInstance;
    }

    AccessoryRenderer renderer;

    @Override
    public Optional<AccessoryRenderer> getRenderer()
    {
        return Optional.ofNullable(renderer);
    }

    @Override
    public void constructRenderer()
    {
        renderer = new EnergyShieldRenderer();
    }

    private static class EnergyShieldRenderer implements AccessoryRenderer
    {
        Biped modelEnergyShield = new Biped(1.25F);

        public void renderThirdPerson(PlayerBase player, PlayerRenderer renderer, ItemInstance itemInstance, double x, double y, double z, float h, float v)
        {
            final ItemInstance itemstack = player.inventory.getHeldItem();
            modelEnergyShield.field_629 = (itemstack != null);
            modelEnergyShield.field_630 = player.method_1373();
            double d3 = y - player.standingEyeHeight;
            if (player.method_1373() && !(player instanceof PlayerBase))
            {
                d3 -= 0.125;
            }
            doRenderEnergyShield(player, renderer, modelEnergyShield, x, d3, z, h, v);
            modelEnergyShield.field_630 = false;
            modelEnergyShield.field_629 = false;
        }

        public void renderFirstPerson(PlayerBase player, PlayerRenderer renderer, ItemInstance itemInstance)
        {
            // todo: make this work

            Minecraft minecraft = (Minecraft) FabricLoader.getInstance().getGameInstance();

            if (!(player.onGround || (player.vehicle != null && player.vehicle.onGround)))
                return;
            if (!(((LivingAccessor) player).get1029() == 0.0f && ((LivingAccessor) player).get1060() == 0.0f))
                return;
            if (minecraft.options.thirdPerson)
                return;

            final ScreenScaler scaledresolution = new ScreenScaler(minecraft.options, minecraft.actualWidth, minecraft.actualHeight);
            final int i = scaledresolution.getScaledWidth();
            final int j = scaledresolution.getScaledHeight();
            GL11.glDisable(2929);
            GL11.glDepthMask(false);
            GL11.glBlendFunc(770, 771);
            GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
            GL11.glDisable(3008);
            GL11.glEnable(2977);
            GL11.glEnable(3042);
            System.out.println("step 3");
            GL11.glBindTexture(3553, minecraft.textureManager.getTextureId("aether:textures/capes/shieldEffect.png"));
            final Tessellator tessellator = Tessellator.INSTANCE;
            tessellator.start();
            tessellator.vertex(0.0, j, -90.0, 0.0, 1.0);
            tessellator.vertex(i, j, -90.0, 1.0, 1.0);
            tessellator.vertex(i, 0.0, -90.0, 1.0, 0.0);
            tessellator.vertex(0.0, 0.0, -90.0, 0.0, 0.0);
            tessellator.draw();
            GL11.glDepthMask(true);
            GL11.glEnable(2929);
            GL11.glEnable(3008);
            GL11.glDisable(2977);
            GL11.glDisable(3042);
            GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        }

        private void doRenderEnergyShield(final Living entityliving, PlayerRenderer playerRenderer, Biped modelEnergyShield, final double d, final double d1, final double d2, final float f, final float f1)
        {
            GL11.glPushMatrix();
            GL11.glEnable(2884);
            modelEnergyShield.handSwingProgress = ((LivingEntityRendererAccessor) playerRenderer).invoke820(entityliving, f1);
            modelEnergyShield.isRiding = entityliving.method_1360();
            try
            {
                final float f2 = entityliving.field_1013 + (entityliving.field_1012 - entityliving.field_1013) * f1;
                final float f3 = entityliving.prevYaw + (entityliving.yaw - entityliving.prevYaw) * f1;
                final float f4 = entityliving.prevPitch + (entityliving.pitch - entityliving.prevPitch) * f1;
                ((LivingEntityRendererAccessor) playerRenderer).invoke826(entityliving, d, d1, d2);
                final float f5 = ((LivingEntityRendererAccessor) playerRenderer).invoke828(entityliving, f1);
                ((LivingEntityRendererAccessor) playerRenderer).invoke824(entityliving, f5, f2, f1);
                final float f6 = 0.0625f;
                GL11.glEnable(32826);
                GL11.glScalef(-1.0f, -1.0f, 1.0f);
                ((LivingEntityRendererAccessor) playerRenderer).invoke823(entityliving, f1);
                GL11.glTranslatef(0.0f, -24.0f * f6 - 0.0078125f, 0.0f);
                float f7 = entityliving.field_1048 + (entityliving.limbDistance - entityliving.field_1048) * f1;
                final float f8 = entityliving.field_1050 - entityliving.limbDistance * (1.0f - f1);
                if (f7 > 1.0f)
                {
                    f7 = 1.0f;
                }
                GL11.glEnable(3008);
                if (setEnergyShieldBrightness((PlayerBase) entityliving, playerRenderer, 0, f1))
                {
                    modelEnergyShield.render(f8, f7, f5, f3 - f2, f4, f6);
                    GL11.glDisable(3042);
                    GL11.glEnable(3008);
                }
                GL11.glDisable(32826);
            }
            catch (Exception exception)
            {
                exception.printStackTrace();
            }
            GL11.glEnable(2884);
            GL11.glPopMatrix();
        }

        protected boolean setEnergyShieldBrightness(final PlayerBase player, PlayerRenderer playerRenderer, final int i, final float f)
        {
            if (i != 0) return false;
            if ((player.onGround || (player.vehicle != null && player.vehicle.onGround)) && ((LivingAccessor) player).get1029() == 0.0f && ((LivingAccessor) player).get1060() == 0.0f)
            {
                ((EntityRenderAccessor) playerRenderer).invokeBindTexture("aether:textures/entity/energyGlow.png");
                GL11.glEnable(2977);
                GL11.glEnable(3042);
                GL11.glBlendFunc(770, 771);
                GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
            }
            else
            {
                GL11.glEnable(2977);
                GL11.glEnable(3042);
                GL11.glBlendFunc(770, 771);
                ((EntityRenderAccessor) playerRenderer).invokeBindTexture("aether:textures/entity/energyNotGlow.png");
            }
            return true;
        }
    }
}
