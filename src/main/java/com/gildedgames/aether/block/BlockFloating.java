package com.gildedgames.aether.block;

import com.gildedgames.aether.entity.projectile.EntityFloatingBlock;
import com.gildedgames.aether.event.listener.TextureListener;
import com.gildedgames.aether.registry.AetherBlocks;
import net.minecraft.block.BlockBase;
import net.minecraft.block.material.Material;
import net.minecraft.level.Level;
import net.modificationstation.stationapi.api.registry.Identifier;
import net.modificationstation.stationapi.api.template.block.TemplateBlockBase;

import java.util.Random;

public class BlockFloating extends TemplateBlockBase
{
    public static boolean fallInstantly;
    private boolean enchanted;

    public BlockFloating(final Identifier id, final boolean bool)
    {
        super(id, Material.STONE);
        this.enchanted = bool;
    }

    public int getTextureForSide(int side, int meta)
    {
        if (this.id == AetherBlocks.GRAVITITE_ORE.id)
        {
            return TextureListener.sprGravititeOre;
        }
        return super.getTextureForSide(side, meta);
    }

    @Override
    public void onBlockPlaced(final Level level, final int x, final int y, final int z)
    {
        level.method_216(x, y, z, this.id, this.getTickrate());
    }

    @Override
    public void onAdjacentBlockUpdate(final Level level, final int x, final int y, final int z, final int id)
    {
        level.method_216(x, y, z, this.id, this.getTickrate());
    }

    @Override
    public void onScheduledTick(final Level level, final int x, final int y, final int z, final Random rand)
    {
        if (!this.enchanted || (this.enchanted && level.method_263(x, y, z)))
        {
            this.tryToFall(level, x, y, z);
        }
    }

    private void tryToFall(final Level world, final int i, int j, final int k)
    {
        final int l = i;
        final int i2 = j;
        final int j2 = k;
        if (canFallAbove(world, l, i2 + 1, j2) && i2 < 128)
        {
            final byte byte0 = 32;
            if (BlockFloating.fallInstantly || !world.method_155(i - byte0, j - byte0, k - byte0, i + byte0, j + byte0, k + byte0))
            {
                world.setTile(i, j, k, 0);
                while (canFallAbove(world, i, j + 1, k) && j < 128)
                {
                    ++j;
                }
                if (j > 0)
                {
                    world.setTile(i, j, k, this.id);
                }
            }
            else
            {
                final EntityFloatingBlock floating = new EntityFloatingBlock(world, i + 0.5f, j + 0.5f, k + 0.5f, this.id);
                world.spawnEntity(floating);
            }
        }
    }

    @Override
    public int getTickrate()
    {
        return 3;
    }

    public static boolean canFallAbove(final Level world, final int i, final int j, final int k)
    {
        final int l = world.getTileId(i, j, k);
        if (l == 0)
        {
            return true;
        }
        if (l == BlockBase.FIRE.id)
        {
            return true;
        }
        final Material material = BlockBase.BY_ID[l].material;
        return material == Material.WATER || material == Material.LAVA;
    }

    static
    {
        BlockFloating.fallInstantly = false;
    }
}
