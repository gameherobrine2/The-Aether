package com.gildedgames.aether.block;

import com.gildedgames.aether.event.listener.TextureListener;
import com.gildedgames.aether.registry.AetherBlocks;
import com.gildedgames.aether.registry.AetherItems;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerBase;
import net.minecraft.level.Level;
import net.modificationstation.stationapi.api.registry.Identifier;
import net.modificationstation.stationapi.api.template.block.TemplateBlockBase;

import java.util.Random;

public class AetherGrassBlock extends TemplateBlockBase
{

    public AetherGrassBlock(Identifier identifier)
    {
        super(identifier, Material.DIRT);
        setTicksRandomly(true);
    }

    public int getTextureForSide(int side, int meta)
    {
        switch (side)
        {
            case 0:
                return TextureListener.sprDirt;
            case 1:
                return TextureListener.sprGrassTop;
            default:
                return TextureListener.sprGrassSide;
        }
    }

    @Override
    public int getDropId(final int meta, final Random rand)
    {
        return AetherBlocks.AETHER_DIRT.getDropId(0, rand);
    }

    @Override
    public void afterBreak(final Level level, final PlayerBase playerBase, final int x, final int y, final int z, final int meta)
    {
        super.afterBreak(level, playerBase, x, y, z, meta);
        if (playerBase.getHeldItem() != null)
            if (meta == 0 && playerBase.getHeldItem().getType() == AetherItems.ShovelSkyroot)
            {
                this.drop(level, x, y, z, meta);
            }
    }

    @Override
    public void onScheduledTick(Level level, int x, int y, int z, Random rand)
    {
        if (!level.isServerSide)
        {
            if (level.getLightLevel(x, y + 1, z) < 4 && level.getMaterial(x, y + 1, z).canBlockGrass())
            {
                if (rand.nextInt(4) == 0)
                {
                    level.setTile(x, y, z, AetherBlocks.AETHER_DIRT.id);
                }
            }
            else if (level.getLightLevel(x, y + 1, z) >= 9)
            {
                int l = (x + rand.nextInt(3)) - 1;
                int i1 = (y + rand.nextInt(5)) - 3;
                int j1 = (z + rand.nextInt(3)) - 1;
                if (level.getTileId(l, i1, j1) == AetherBlocks.AETHER_DIRT.id && level.getLightLevel(l, i1 + 1, j1) >= 4 && !level.getMaterial(l, i1 + 1, j1).canBlockGrass())
                {
                    level.setTile(l, i1, j1, AetherBlocks.AETHER_GRASS_BLOCK.id);
                }
            }
        }
    }
}
