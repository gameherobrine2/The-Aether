package com.gildedgames.aether.block;

import com.gildedgames.aether.event.listener.TextureListener;
import com.gildedgames.aether.registry.AetherBlocks;
import net.minecraft.block.material.Material;
import net.minecraft.level.Level;
import net.minecraft.util.maths.Box;
import net.modificationstation.stationapi.api.registry.Identifier;
import net.modificationstation.stationapi.api.template.block.TemplateBlockBase;

import java.util.Random;

public class BlockAetherFlower extends TemplateBlockBase
{
    public BlockAetherFlower(final Identifier id, final int var2)
    {
        super(id, Material.PLANT);
        this.setTicksRandomly(true);
        final float var3 = 0.2f;
        this.setBoundingBox(0.5f - var3, 0.0f, 0.5f - var3, 0.5f + var3, var3 * 3.0f, 0.5f + var3);
    }

    @Override
    public int getTextureForSide(int side, int meta)
    {
        if (this.id == AetherBlocks.WHITE_FLOWER.id)
        {
            return TextureListener.sprWhiteFlower;
        }
        return TextureListener.sprPurpleFlower;
    }

    @Override
    public boolean canPlaceAt(final Level level, final int x, final int y, final int z)
    {
        return super.canPlaceAt(level, x, y, z) && this.canThisPlantGrowOnThisBlockID(level.getTileId(x, y - 1, z));
    }

    protected boolean canThisPlantGrowOnThisBlockID(final int var1)
    {
        return var1 == AetherBlocks.AETHER_GRASS_BLOCK.id || var1 == AetherBlocks.AETHER_DIRT.id;
    }

    @Override
    public void onAdjacentBlockUpdate(final Level level, final int x, final int y, final int z, final int id)
    {
        super.onAdjacentBlockUpdate(level, x, y, z, id);
        this.func_268_h(level, x, y, z);
    }

    @Override
    public void onScheduledTick(final Level level, final int x, final int y, final int z, final Random rand)
    {
        this.func_268_h(level, x, y, z);
    }

    protected final void func_268_h(final Level var1, final int var2, final int var3, final int var4)
    {
        if (!this.canGrow(var1, var2, var3, var4))
        {
            this.drop(var1, var2, var3, var4, var1.getTileMeta(var2, var3, var4));
            var1.setTile(var2, var3, var4, 0);
        }
    }

    @Override
    public boolean canGrow(final Level level, final int x, final int y, final int z)
    {
        return (level.getLightLevel(x, y, z) >= 8 || level.isAboveGroundCached(x, y, z)) && this.canThisPlantGrowOnThisBlockID(level.getTileId(x, y - 1, z));
    }

    @Override
    public Box getCollisionShape(final Level level, final int x, final int y, final int z)
    {
        return null;
    }

    @Override
    public boolean isFullOpaque()
    {
        return false;
    }

    @Override
    public boolean isFullCube()
    {
        return false;
    }

    @Override
    public int getRenderType()
    {
        return 1;
    }
}
