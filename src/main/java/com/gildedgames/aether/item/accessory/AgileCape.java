package com.gildedgames.aether.item.accessory;

import net.minecraft.entity.player.PlayerBase;
import net.minecraft.item.ItemInstance;
import net.modificationstation.stationapi.api.registry.Identifier;

public class AgileCape extends CosmeticCape
{
    public AgileCape(Identifier identifier, String texture)
    {
        super(identifier, texture);
    }

    @Override
    public void onAccessoryAdded(PlayerBase playerBase, ItemInstance itemInstance)
    {
        playerBase.field_1641 = 1.0f;
    }

    @Override
    public void onAccessoryRemoved(PlayerBase playerBase, ItemInstance itemInstance)
    {
        playerBase.field_1641 = 0.5f;
    }
}
