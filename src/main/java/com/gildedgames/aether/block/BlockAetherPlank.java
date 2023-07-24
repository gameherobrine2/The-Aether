package com.gildedgames.aether.block;

import com.gildedgames.aether.event.listener.TextureListener;
import net.minecraft.block.material.Material;
import net.minecraft.level.BlockView;
import net.modificationstation.stationapi.api.registry.Identifier;
import net.modificationstation.stationapi.api.template.block.TemplateBlockBase;

public class BlockAetherPlank extends TemplateBlockBase
{

    public BlockAetherPlank(Identifier identifier, Material material)
    {
        super(identifier, material);
    }

    @Override
    public int getTextureForSide(int side, int meta)
    {
        return TextureListener.sprPlank;
    }

    @Override
    public boolean isFullOpaque()
    {
        return false;
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

}
