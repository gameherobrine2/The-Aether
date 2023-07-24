package com.gildedgames.aether.item.accessory;

import com.matthewperiut.accessoryapi.api.Accessory;
import com.matthewperiut.accessoryapi.api.AccessoryType;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.PlayerRenderer;
import net.minecraft.client.render.entity.model.Biped;
import net.minecraft.entity.player.PlayerBase;
import net.minecraft.item.ItemInstance;
import net.modificationstation.stationapi.api.registry.Identifier;
import net.modificationstation.stationapi.api.template.item.TemplateItemBase;

public class Ring extends TemplateItemBase implements Accessory
{
    int colour;

    public Ring(Identifier identifier, int colour)
    {
        super(identifier);
        this.colour = colour;
        this.setDurability(100);
        this.setMaxStackSize(1);
    }

    @Override
    public AccessoryType[] getAccessoryTypes(ItemInstance item)
    {
        return new AccessoryType[]{AccessoryType.ring};
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
    public void renderWhileWorn(PlayerBase playerBase, PlayerRenderer playerRenderer, ItemInstance itemInstance, Biped biped, Object[] objects)
    {

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
