package com.gildedgames.aether.block;

import com.gildedgames.aether.event.listener.TextureListener;
import net.modificationstation.stationapi.api.registry.Identifier;

public class BlockMossyHolystone extends BlockHolystone
{
    public BlockMossyHolystone(Identifier identifier)
    {
        super(identifier);
    }

    @Override
    public int getTextureForSide(int side, int meta)
    {
        return TextureListener.sprMossyHolystone;
    }

}
