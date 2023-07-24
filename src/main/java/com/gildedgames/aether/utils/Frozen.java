package com.gildedgames.aether.utils;

import net.minecraft.item.ItemInstance;

public class Frozen
{
    public ItemInstance frozenFrom;
    public ItemInstance frozenTo;
    public int frozenPowerNeeded;

    public Frozen(final ItemInstance from, final ItemInstance to, final int i)
    {
        this.frozenFrom = from;
        this.frozenTo = to;
        this.frozenPowerNeeded = i;
    }
}
