package com.gildedgames.aether.block;

import com.gildedgames.aether.event.listener.TextureListener;
import net.minecraft.block.material.Material;
import net.minecraft.level.BlockView;
import net.modificationstation.stationapi.api.registry.Identifier;
import net.modificationstation.stationapi.api.template.block.TemplateTranslucentBlock;

import java.util.Random;

public class BlockQuicksoilGlass extends TemplateTranslucentBlock
{
    public BlockQuicksoilGlass(final Identifier id)
    {
        super(id, 0, Material.GLASS, false);
        this.slipperiness = 1.05f;
    }

    @Override
    public int getTextureForSide(int side, int meta)
    {
        return TextureListener.sprQuicksoilGlass;
    }

    @Override
    public int getDropCount(final Random rand)
    {
        return 0;
    }

    @Override
    public int getRenderPass()
    {
        return 1;
    }

    @Override
    public boolean isSideRendered(final BlockView tileView, final int x, final int y, final int z, final int side)
    {
        return super.isSideRendered(tileView, x, y, z, 1 - side);
    }

    @Override
    public int getBaseColour(final int i)
    {
        return 16776960;
    }

    @Override
    public int getColourMultiplier(final BlockView tileView, final int x, final int y, final int z)
    {
        return this.getBaseColour(tileView.getTileMeta(x, y, z));
    }
}
