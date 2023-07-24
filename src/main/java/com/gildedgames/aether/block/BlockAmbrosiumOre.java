package com.gildedgames.aether.block;

import com.gildedgames.aether.event.listener.TextureListener;
import com.gildedgames.aether.registry.AetherItems;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerBase;
import net.minecraft.level.Level;
import net.modificationstation.stationapi.api.registry.Identifier;
import net.modificationstation.stationapi.api.template.block.TemplateBlockBase;

import java.util.Random;

public class BlockAmbrosiumOre extends TemplateBlockBase
{
    public BlockAmbrosiumOre(final Identifier id)
    {
        super(id, Material.STONE);
    }

    public int getTextureForSide(int side, int meta)
    {
        return TextureListener.sprAmbrosiumOre;
    }

    @Override
    public int getDropId(final int meta, final Random rand)
    {
        return AetherItems.AmbrosiumShard.id;
    }

    @Override
    public void onBlockPlaced(final Level level, final int x, final int y, final int z, final int facing)
    {
        level.setTile(x, y, z, this.id);
        level.setTileMeta(x, y, z, 1);
    }

    @Override
    public void afterBreak(final Level level, final PlayerBase playerBase, final int x, final int y, final int z, final int meta)
    {
        super.afterBreak(level, playerBase, x, y, z, meta);
        if (playerBase.getHeldItem() != null)
            if (meta == 0 && playerBase.getHeldItem().getType() == AetherItems.PickSkyroot)
            {
                this.drop(level, x, y, z, meta);
            }
    }
}
