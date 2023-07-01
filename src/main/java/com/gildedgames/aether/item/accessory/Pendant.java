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

public class Pendant extends TemplateItemBase implements Accessory {
    String texture;
    int colour;
    public Pendant(Identifier identifier, String texture, int colour) {
        super(identifier);
        setMaxStackSize(1);
        setDurability(500);
        this.colour = colour;
        this.texture = texture;
    }

    public Pendant(Identifier identifier, String texture)
    {
        this(identifier, texture, 16777215);
    }

    @Environment(EnvType.CLIENT)
    public int getColourMultiplier(int i) {
        return colour;
    }

    @Override
    public Type getType() {
        return Type.pendant;
    }

    @Override
    public void tickWhileWorn(PlayerBase playerBase, ItemInstance itemInstance) {

    }

    @Override
    public void renderWhileWorn(PlayerBase playerBase, PlayerRenderer playerRenderer, ItemInstance itemInstance, Biped model, Object[] objects) {
        float f = (float) objects[3];
        final float brightness = playerBase.getBrightnessAtEyes(f);
        final float f6 = 0.0625f;

        final Pendant pendant = (Pendant) itemInstance.getType();
        ((EntityRenderAccessor) playerRenderer).invokeBindTexture(pendant.texture);
        final int colour = pendant.getColourMultiplier(0);
        final float red = (colour >> 16 & 0xFF) / 255.0f;
        final float green = (colour >> 8 & 0xFF) / 255.0f;
        final float blue = (colour & 0xFF) / 255.0f;
        GL11.glColor3f(red * brightness, green * brightness, blue * brightness);
        model.field_621.method_1815(f6);
    }

    @Override
    public void onAccessoryAdded(PlayerBase playerBase, ItemInstance itemInstance) {

    }

    @Override
    public void onAccessoryRemoved(PlayerBase playerBase, ItemInstance itemInstance) {

    }
}
