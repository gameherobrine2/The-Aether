package com.gildedgames.aether.item.accessory;

import com.matthewperiut.accessoryapi.api.Accessory;
import com.matthewperiut.accessoryapi.api.AccessoryType;
import com.matthewperiut.accessoryapi.api.helper.AccessoryRenderHelper;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.PlayerRenderer;
import net.minecraft.client.render.entity.model.Biped;
import net.minecraft.entity.player.PlayerBase;
import net.minecraft.item.ItemInstance;
import net.modificationstation.stationapi.api.registry.Identifier;
import net.modificationstation.stationapi.api.template.item.TemplateItemBase;

public class Glove extends TemplateItemBase implements Accessory
{
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
    public AccessoryType[] getAccessoryTypes(ItemInstance item)
    {
        return new AccessoryType[]{AccessoryType.glove};
    }

    @Environment(EnvType.CLIENT)
    public int getColourMultiplier(int i)
    {
        return colour;
    }

    @Override
    public void tickWhileWorn(PlayerBase playerBase, ItemInstance itemInstance)
    {

    }

    @Override
    public void renderWhileWorn(PlayerBase player, PlayerRenderer playerRenderer, ItemInstance itemInstance, Biped modelMisc, Object[] objects)
    {
        final Glove glove = (Glove) itemInstance.getType();
        AccessoryRenderHelper.ArmOverlay(player, glove.texture, glove.colour, modelMisc, objects);
    }

    @Override
    public void onAccessoryAdded(PlayerBase playerBase, ItemInstance itemInstance)
    {
        final Glove glove = (Glove) itemInstance.getType();
        AccessoryRenderHelper.enableFirstPersonArmOverlayRender(glove.texture, glove.colour);
    }

    @Override
    public void onAccessoryRemoved(PlayerBase playerBase, ItemInstance itemInstance)
    {
        AccessoryRenderHelper.disableFirstPersonArmOverlay();
    }
}
