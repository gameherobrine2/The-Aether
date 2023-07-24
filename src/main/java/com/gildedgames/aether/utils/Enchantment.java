package com.gildedgames.aether.utils;

import net.minecraft.item.ItemInstance;

public class Enchantment
{
    public ItemInstance enchantFrom;
    public ItemInstance enchantTo;
    public int enchantPowerNeeded;

    public Enchantment(final ItemInstance from, final ItemInstance to, final int i)
    {
        this.enchantFrom = from;
        this.enchantTo = to;
        this.enchantPowerNeeded = i;
    }
}
