package com.gildedgames.aether.block;

import net.minecraft.block.BlockBase;
import net.minecraft.level.BlockView;
import net.modificationstation.stationapi.api.registry.Identifier;

public class BlockEnchantedGravitite extends BlockFloating
{
    public BlockEnchantedGravitite(final Identifier i, final boolean bool)
    {
        super(i, bool);
        this.texture = BlockBase.IRON_BLOCK.texture;
    }

    @Override
    public int getBaseColour(final int i)
    {
        return 16755455;
    }

    @Override
    public int getColourMultiplier(final BlockView tileView, final int x, final int y, final int z)
    {
        return this.getBaseColour(tileView.getTileMeta(x, y, z));
    }
}
