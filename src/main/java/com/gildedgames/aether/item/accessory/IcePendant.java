package com.gildedgames.aether.item.accessory;

import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerBase;
import net.minecraft.item.ItemInstance;
import net.minecraft.util.maths.MathHelper;
import net.modificationstation.stationapi.api.registry.Identifier;

public class IcePendant extends Pendant
{
    public IcePendant(Identifier identifier, String texture, int colour)
    {
        super(identifier, texture, colour);
    }

    @Override
    public ItemInstance tickWhileWorn(PlayerBase player, ItemInstance itemInstance)
    {
        super.tickWhileWorn(player, itemInstance);

        final int i = MathHelper.floor(player.x);
        final int j = MathHelper.floor(player.boundingBox.minY);
        final int k = MathHelper.floor(player.z);
        final double yoff = player.y - j;
        final Material mat0 = player.level.getMaterial(i, j, k);
        final Material mat2 = player.level.getMaterial(i, j - 1, k);
        for (int l = i - 1; l <= i + 1; ++l)
        {
            for (int i2 = j - 1; i2 <= j + 1; ++i2)
            {
                for (int j2 = k - 1; j2 <= k + 1; ++j2)
                {
                    if (player.level.getTileId(l, i2, j2) == 8)
                    {
                        player.level.setTile(l, i2, j2, 79);
                    }
                    else if (player.level.getTileId(l, i2, j2) == 9)
                    {
                        player.level.setTile(l, i2, j2, 79);
                    }
                    else if (player.level.getTileId(l, i2, j2) == 10)
                    {
                        player.level.setTile(l, i2, j2, 49);
                    }
                    else if (player.level.getTileId(l, i2, j2) == 11)
                    {
                        player.level.setTile(l, i2, j2, 49);
                    }
                }
            }
        }
        return itemInstance;
    }
}