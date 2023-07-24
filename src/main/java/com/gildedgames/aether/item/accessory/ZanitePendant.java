package com.gildedgames.aether.item.accessory;

import net.minecraft.entity.player.PlayerBase;
import net.minecraft.item.ItemInstance;
import net.modificationstation.stationapi.api.registry.Identifier;

public class ZanitePendant extends Pendant
{
    public ZanitePendant(Identifier identifier, String texture, int colour)
    {
        super(identifier, texture, colour);
    }

    @Override
    public void tickWhileWorn(PlayerBase playerBase, ItemInstance itemInstance)
    {
        // todo: mine faster as durability decreases
    }
}