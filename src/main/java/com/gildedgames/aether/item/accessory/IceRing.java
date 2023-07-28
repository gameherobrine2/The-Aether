package com.gildedgames.aether.item.accessory;

import net.minecraft.item.ItemInstance;
import net.modificationstation.stationapi.api.registry.Identifier;

public class IceRing extends IcePendant
{
    public IceRing(Identifier identifier, int colour)
    {
        super(identifier, "", colour);
    }

    @Override
    public String[] getAccessoryTypes(ItemInstance item)
    {
        return new String[]{"ring"};
    }
}
