package com.gildedgames.aether.item.accessory;

import com.matthewperiut.accessoryapi.api.Accessory;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
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
    public String[] getAccessoryTypes(ItemInstance item)
    {
        return new String[]{"ring"};
    }

    @Environment(EnvType.CLIENT)
    public int getColourMultiplier(int i)
    {
        return colour;
    }
}
