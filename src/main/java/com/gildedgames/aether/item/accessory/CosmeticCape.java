package com.gildedgames.aether.item.accessory;

import com.gildedgames.aether.mixin.access.EntityRenderAccessor;
import com.matthewperiut.accessoryapi.api.Accessory;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.PlayerRenderer;
import net.minecraft.client.render.entity.model.Biped;
import net.minecraft.entity.player.PlayerBase;
import net.minecraft.item.ItemInstance;
import net.minecraft.util.maths.MathHelper;
import net.modificationstation.stationapi.api.registry.Identifier;
import net.modificationstation.stationapi.api.template.item.TemplateItemBase;
import org.lwjgl.opengl.GL11;

public class CosmeticCape extends TemplateItemBase implements Accessory
{
    public String texture;
    public int colour;
    public CosmeticCape(Identifier identifier, String texture, int color)
    {
        super(identifier);
        this.texture = texture;
        this.colour = color;
        this.setMaxStackSize(1);
        this.setDurability(500);
    }

    public CosmeticCape(Identifier identifier, String texture)
    {
        this(identifier, texture, 16777215);
    }

    @Environment(EnvType.CLIENT)
    public int getColourMultiplier(int i) {
        return colour;
    }

    @Override
    public Type getType()
    {
        return Type.cape;
    }

    @Override
    public void tickWhileWorn(PlayerBase playerBase, ItemInstance itemInstance)
    {

    }

    @Override
    public void renderWhileWorn(PlayerBase player, PlayerRenderer renderer, ItemInstance itemInstance, Biped biped, Object[] objects)
    {
        float f = (float) objects[0];

        ((EntityRenderAccessor)renderer).invokeBindTexture(((CosmeticCape)itemInstance.getType()).texture);
        GL11.glPushMatrix();
        GL11.glTranslatef(0.0f, 0.0f, 0.125f);
        final double d = player.field_530 + (player.field_533 - player.field_530) * f - (player.prevX + (player.x - player.prevX) * f);
        final double d2 = player.field_531 + (player.field_534 - player.field_531) * f - (player.prevY + (player.y - player.prevY) * f);
        final double d3 = player.field_532 + (player.field_535 - player.field_532) * f - (player.prevZ + (player.z - player.prevZ) * f);
        final float f2 = player.field_1013 + (player.field_1012 - player.field_1013) * f;
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
        final float f6 = player.field_524 + (player.field_525 - player.field_524) * f;
        f3 += MathHelper.sin((player.field_1634 + (player.field_1635 - player.field_1634) * f) * 6.0f) * 32.0f * f6;
        if (player.method_1373()) {
            f3 += 25.0f;
        }
        GL11.glRotatef(6.0f + f4 / 2.0f + f3, 1.0f, 0.0f, 0.0f);
        GL11.glRotatef(f5 / 2.0f, 0.0f, 0.0f, 1.0f);
        GL11.glRotatef(-f5 / 2.0f, 0.0f, 1.0f, 0.0f);
        GL11.glRotatef(180.0f, 0.0f, 1.0f, 0.0f);
        biped.method_606(0.0625f);
        GL11.glPopMatrix();
    }

    @Override
    public void onAccessoryAdded(PlayerBase playerBase, ItemInstance itemInstance)
    {

    }

    @Override
    public void onAccessoryRemoved(PlayerBase playerBase, ItemInstance itemInstance)
    {

    }
}
