package com.gildedgames.aether.entity.tile;

import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.io.CompoundTag;

public class TileEntityTreasureChest extends TileEntityChest
{
    public int rarity = 0;

    public void setRarity(int r)
    {
        rarity = r;
    }

    public void readIdentifyingData(CompoundTag arg)
    {
        super.readIdentifyingData(arg);
        rarity = arg.getInt("rarity");
    }

    public void writeIdentifyingData(CompoundTag arg)
    {
        super.writeIdentifyingData(arg);
        arg.put("rarity", rarity);
    }
}
