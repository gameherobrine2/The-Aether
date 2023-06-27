package com.gildedgames.aether.block;
import com.gildedgames.aether.registry.AetherBlocks;
import net.minecraft.item.ItemInstance;
import net.minecraft.stat.Stats;
import net.modificationstation.stationapi.api.registry.Identifier;
import net.modificationstation.stationapi.api.template.block.TemplateBlockBase;
import net.minecraft.entity.player.PlayerBase;
import net.minecraft.level.Level;
import net.minecraft.block.material.Material;
import java.util.Random;

import com.gildedgames.aether.Aether;
import com.gildedgames.aether.event.listener.TextureListener;
import com.gildedgames.aether.registry.AetherItems;

public class BlockLog extends TemplateBlockBase {
    private static Random rand;

    public BlockLog(final Identifier id) {
        super(id, TextureListener.sprSkyrootLogSide, Material.WOOD);
    }

    @Override
    public void onBlockPlaced(Level level, int x, int y, int z) {
        level.setTileMeta(x, y, z, 1);
        super.onBlockPlaced(level, x, y, z);
    }

    @Override
    public int getTextureForSide(final int side) {

        if (side <= 1) {
            return TextureListener.sprSkyrootLogTop;
        }
        if (this.id == AetherBlocks.SKYROOT_LOG.id)
            return TextureListener.sprSkyrootLogSide;
        else
            return TextureListener.sprGoldenLogSide;
    }
    
    @Override
    public int getDropCount(final Random rand) {
        return 0;
    }
    
    @Override
    public void afterBreak(final Level level, final PlayerBase playerBase, final int x, final int y, final int z, final int meta) {

        playerBase.increaseStat(Stats.mineBlock[this.id], 1);
        ItemInstance stack = new ItemInstance(this.id, 1, 0);
        if (Aether.equippedSkyrootAxe() && meta != 1) {
            final ItemInstance itemInstance = stack;
            itemInstance.count *= 2;
        }

        final ItemInstance itemstack = playerBase.inventory.getHeldItem();
        if (itemstack == null || (itemstack.itemId != AetherItems.AxeZanite.id && itemstack.itemId != AetherItems.AxeGravitite.id)) {

        }
        else
        {
            ItemInstance amberStack;
            if (rand.nextBoolean()) {
                amberStack = new ItemInstance(AetherItems.GoldenAmber.id, 2 + rand.nextInt(2), 0);
                this.drop(level, x, y, z, amberStack);
                if (BlockLog.rand.nextBoolean()) {
                    amberStack = new ItemInstance(AetherItems.GoldenAmber.id, 2 + BlockLog.rand.nextInt(2), 0);
                    this.drop(level, x, y, z, amberStack);
                }
            }
            stack.itemId = AetherBlocks.SKYROOT_LOG.id;
        }

        this.drop(level, x, y, z, stack);
    }
    
    static {
        BlockLog.rand = new Random();
    }
}
