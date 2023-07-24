package com.gildedgames.aether.item.accessory;

import com.matthewperiut.accessoryapi.api.AccessoryType;
import net.minecraft.item.ItemInstance;
import net.modificationstation.stationapi.api.registry.Identifier;

public class IceRing extends IcePendant
{
    public IceRing(Identifier identifier, int colour)
    {
        super(identifier, "", colour);
    }

    @Override
    public AccessoryType[] getAccessoryTypes(ItemInstance item)
    {
        return new AccessoryType[]{AccessoryType.ring};
    }
}
