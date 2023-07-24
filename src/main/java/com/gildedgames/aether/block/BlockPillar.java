package com.gildedgames.aether.block;

import com.gildedgames.aether.event.listener.TextureListener;
import net.minecraft.block.material.Material;
import net.modificationstation.stationapi.api.registry.Identifier;
import net.modificationstation.stationapi.api.template.block.TemplateBlockBase;

public class BlockPillar extends TemplateBlockBase
{

    public BlockPillar(final Identifier id)
    {
        super(id, Material.STONE);
    }

    @Override
    public int getTextureForSide(final int side, final int meta)
    {
        if (side == 0 || side == 1)
        {
            return TextureListener.sprPillarTop;
        }
        if (meta == 0)
        {
            return TextureListener.sprPillarSide;
        }
        return TextureListener.sprPillarTopSide;
    }

    @Override
    protected int droppedMeta(final int meta)
    {
        return meta;
    }

    static
    {

    }
}
