package com.gildedgames.aether.item.accessory;

import com.gildedgames.aether.mixin.access.EntityRenderAccessor;
import com.matthewperiut.accessoryapi.api.Accessory;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.PlayerRenderer;
import net.minecraft.client.render.entity.model.Biped;
import net.minecraft.entity.player.PlayerBase;
import net.minecraft.item.ItemInstance;
import net.modificationstation.stationapi.api.registry.Identifier;
import net.modificationstation.stationapi.api.template.item.TemplateItemBase;
import org.lwjgl.opengl.GL11;

public class Glove extends TemplateItemBase implements Accessory {
    public final int strength;
    private final int colour;
    public String texture;

    public Glove(Identifier identifier, int strength, String texture, int color)
    {
        super(identifier);
        this.texture = texture;
        this.colour = color;
        this.strength = strength;
        this.setMaxStackSize(1);
        this.setDurability(500);
    }

    public Glove(Identifier identifier, int strength, String texture)
    {
        this(identifier, strength, texture, 16777215);
    }

    @Override
    public Type getType() {
        return Type.glove;
    }

    @Environment(EnvType.CLIENT)
    public int getColourMultiplier(int i) {
        return colour;
    }

    @Override
    public void tickWhileWorn(PlayerBase playerBase, ItemInstance itemInstance) {

    }

    @Override
    public void renderWhileWorn(PlayerBase player, PlayerRenderer playerRenderer, ItemInstance itemInstance, Biped modelMisc, Object[] objects) {
        // glove //
        float f = (float) objects[3];
        final float brightness = player.getBrightnessAtEyes(f);
        final float f6 = 0.0625f;

        final Glove glove = (Glove) itemInstance.getType();
        ((EntityRenderAccessor)playerRenderer).invokeBindTexture(glove.texture);
        final int colour = glove.getColourMultiplier(0);
        final float red = (colour >> 16 & 0xFF) / 255.0f;
        final float green = (colour >> 8 & 0xFF) / 255.0f;
        final float blue = (colour & 0xFF) / 255.0f;
        GL11.glColor3f(red * brightness, green * brightness, blue * brightness);
        modelMisc.field_623.method_1815(f6);
        modelMisc.field_622.method_1815(f6);
        // end //
    }

            /* use on hand render
        final float brightness = player.getBrightnessAtEyes(1.0f);
        modelMisc.handSwingProgress = 0.0f;
        modelMisc.setAngles(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0625f);
        modelMisc.field_622.method_1815(0.0625f);
        final Glove glove = (Glove)itemInstance.getType();
        ((EntityRenderAccessor)playerRenderer).invokeBindTexture(glove.texture);
        final int colour = glove.getColourMultiplier(0);
        final float red = (colour >> 16 & 0xFF) / 255.0f;
        final float green = (colour >> 8 & 0xFF) / 255.0f;
        final float blue = (colour & 0xFF) / 255.0f;
        GL11.glColor3f(red * brightness, green * brightness, blue * brightness);
        modelMisc.field_622.method_1815(0.0625f);*/

    @Override
    public void onAccessoryAdded(PlayerBase playerBase, ItemInstance itemInstance) {

    }

    @Override
    public void onAccessoryRemoved(PlayerBase playerBase, ItemInstance itemInstance) {

    }
}
