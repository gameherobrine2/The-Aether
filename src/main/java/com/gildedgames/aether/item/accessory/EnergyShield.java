package com.gildedgames.aether.item.accessory;

import com.gildedgames.aether.mixin.access.EntityRenderAccessor;
import com.gildedgames.aether.mixin.access.LivingAccessor;
import com.gildedgames.aether.mixin.access.LivingEntityRendererAccessor;
import com.matthewperiut.accessoryapi.api.Accessory;
import net.minecraft.client.render.entity.PlayerRenderer;
import net.minecraft.client.render.entity.model.Biped;
import net.minecraft.entity.Living;
import net.minecraft.entity.player.AbstractClientPlayer;
import net.minecraft.entity.player.PlayerBase;
import net.minecraft.item.ItemBase;
import net.minecraft.item.ItemInstance;
import net.modificationstation.stationapi.api.registry.Identifier;
import net.modificationstation.stationapi.api.template.item.TemplateItemBase;
import org.lwjgl.opengl.GL11;

public class EnergyShield extends TemplateItemBase implements Accessory
{

    public EnergyShield(Identifier identifier)
    {
        super(identifier);
    }

    @Override
    public Type getType()
    {
        return Type.shield;
    }

    @Override
    public void tickWhileWorn(PlayerBase playerBase, ItemInstance itemInstance)
    {
        // todo: repulse
    }

    @Override
    public void renderWhileWorn(PlayerBase entityplayer, PlayerRenderer playerRenderer, ItemInstance itemInstance, Biped modelEnergyShield, Object[] objects)
    {
        double d = (double) objects[0];
        double d1 = (double) objects[1];
        double d2 = (double) objects[2];
        float f = (float) objects[3];
        float f1 = (float) objects[4];

        final ItemInstance itemstack = entityplayer.inventory.getHeldItem();
        modelEnergyShield.field_629 = (itemstack != null);
        modelEnergyShield.field_630 = entityplayer.method_1373();
        double d3 = d1 - entityplayer.standingEyeHeight;
        if (entityplayer.method_1373() && !(entityplayer instanceof AbstractClientPlayer)) {
            d3 -= 0.125;
        }
        doRenderEnergyShield(entityplayer, playerRenderer, modelEnergyShield, d, d3, d2, f, f1);
        modelEnergyShield.field_630 = false;
        modelEnergyShield.field_629 = false;

    }


    @Override
    public void onAccessoryAdded(PlayerBase playerBase, ItemInstance itemInstance)
    {

    }

    @Override
    public void onAccessoryRemoved(PlayerBase playerBase, ItemInstance itemInstance)
    {

    }

    private void doRenderEnergyShield(final Living entityliving, PlayerRenderer playerRenderer, Biped modelEnergyShield, final double d, final double d1, final double d2, final float f, final float f1) {
        GL11.glPushMatrix();
        GL11.glEnable(2884);
        modelEnergyShield.handSwingProgress = ((LivingEntityRendererAccessor)playerRenderer).invoke820(entityliving, f1);
        modelEnergyShield.isRiding = entityliving.method_1360();
        try {
            final float f2 = entityliving.field_1013 + (entityliving.field_1012 - entityliving.field_1013) * f1;
            final float f3 = entityliving.prevYaw + (entityliving.yaw - entityliving.prevYaw) * f1;
            final float f4 = entityliving.prevPitch + (entityliving.pitch - entityliving.prevPitch) * f1;
            ((LivingEntityRendererAccessor)playerRenderer).invoke826(entityliving, d, d1, d2);
            final float f5 = ((LivingEntityRendererAccessor)playerRenderer).invoke828(entityliving, f1);
            ((LivingEntityRendererAccessor)playerRenderer).invoke824(entityliving, f5, f2, f1);
            final float f6 = 0.0625f;
            GL11.glEnable(32826);
            GL11.glScalef(-1.0f, -1.0f, 1.0f);
            ((LivingEntityRendererAccessor)playerRenderer).invoke823(entityliving, f1);
            GL11.glTranslatef(0.0f, -24.0f * f6 - 0.0078125f, 0.0f);
            float f7 = entityliving.field_1048 + (entityliving.limbDistance - entityliving.field_1048) * f1;
            final float f8 = entityliving.field_1050 - entityliving.limbDistance * (1.0f - f1);
            if (f7 > 1.0f) {
                f7 = 1.0f;
            }
            GL11.glEnable(3008);
            if (setEnergyShieldBrightness((PlayerBase)entityliving, playerRenderer, 0, f1)) {
                modelEnergyShield.render(f8, f7, f5, f3 - f2, f4, f6);
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

    protected boolean setEnergyShieldBrightness(final PlayerBase player, PlayerRenderer playerRenderer, final int i, final float f) {
        if (i != 0)
            return false;
        if ((player.onGround || (player.vehicle != null && player.vehicle.onGround)) && ((LivingAccessor)player).get1029() == 0.0f && ((LivingAccessor)player).get1060() == 0.0f) {
            ((EntityRenderAccessor)playerRenderer).invokeBindTexture("aether:textures/entity/energyGlow.png");
            GL11.glEnable(2977);
            GL11.glEnable(3042);
            GL11.glBlendFunc(770, 771);
            GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        }
        else {
            GL11.glEnable(2977);
            GL11.glEnable(3042);
            GL11.glBlendFunc(770, 771);
            ((EntityRenderAccessor)playerRenderer).invokeBindTexture("aether:textures/entity/energyNotGlow.png");
        }
        return true;
    }
}
