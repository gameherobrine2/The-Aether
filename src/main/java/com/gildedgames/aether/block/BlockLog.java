package com.gildedgames.aether.block;

import com.gildedgames.aether.event.listener.TextureListener;
import com.gildedgames.aether.registry.AetherBlocks;
import com.gildedgames.aether.registry.AetherItems;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerBase;
import net.minecraft.item.ItemInstance;
import net.minecraft.level.Level;
import net.minecraft.stat.Stats;
import net.modificationstation.stationapi.api.registry.Identifier;
import net.modificationstation.stationapi.api.template.block.TemplateBlockBase;

import java.util.Random;

public class BlockLog extends TemplateBlockBase
{
    private static Random rand;

    public BlockLog(final Identifier id)
    {
        super(id, TextureListener.sprSkyrootLogSide, Material.WOOD);
    }

    @Override
    public void onBlockPlaced(Level level, int x, int y, int z)
    {
        level.setTileMeta(x, y, z, 1);
        super.onBlockPlaced(level, x, y, z);
    }

    @Override
    public int getTextureForSide(final int side)
    {

        if (side <= 1)
        {
            return TextureListener.sprSkyrootLogTop;
        }
        if (this.id == AetherBlocks.SKYROOT_LOG.id) return TextureListener.sprSkyrootLogSide;
        else return TextureListener.sprGoldenLogSide;
    }

    @Override
    public int getDropCount(final Random rand)
    {
        return 0;
    }

    @Override
    public void afterBreak(final Level level, final PlayerBase playerBase, final int x, final int y, final int z, final int meta)
    {
        ItemInstance stack = new ItemInstance(this.id, 1, 0);
        playerBase.increaseStat(Stats.mineBlock[this.id], 1);
        if (this.id == AetherBlocks.SKYROOT_LOG.id)
        {
            // regular log
            super.afterBreak(level, playerBase, x, y, z, meta);
            if (playerBase.getHeldItem() != null)
                if (meta != 0 && playerBase.getHeldItem().getType() == AetherItems.AxeSkyroot)
                {
                    stack.count = 2;
                }
        }
        else
        {
            final ItemInstance itemstack = playerBase.inventory.getHeldItem();
            if (itemstack != null)
            {
                if ((itemstack.itemId == AetherItems.AxeZanite.id || itemstack.itemId == AetherItems.AxeGravitite.id))
                {
                    ItemInstance amberStack;
                    if (rand.nextBoolean())
                    {
                        amberStack = new ItemInstance(AetherItems.GoldenAmber.id, 2 + rand.nextInt(2), 0);
                        this.drop(level, x, y, z, amberStack);
                    }
                    stack.count = 1;
                    stack.itemId = AetherBlocks.SKYROOT_LOG.id;
                }
            }
        }
        this.drop(level, x, y, z, stack);
    }

    static
    {
        BlockLog.rand = new Random();
    }
}
