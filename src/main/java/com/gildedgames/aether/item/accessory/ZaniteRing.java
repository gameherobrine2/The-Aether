package com.gildedgames.aether.item.accessory;

import net.minecraft.entity.player.PlayerBase;
import net.minecraft.item.ItemInstance;
import net.modificationstation.stationapi.api.registry.Identifier;

public class ZaniteRing extends Ring
{
    public ZaniteRing(Identifier identifier, int colour)
    {
        super(identifier, colour);
    }

    @Override
    public void tickWhileWorn(PlayerBase playerBase, ItemInstance itemInstance)
    {
        // todo: mine faster
    }
}
