package com.gildedgames.aether.block;
import net.minecraft.item.ItemInstance;
import net.minecraft.stat.Stats;
import net.modificationstation.stationapi.api.block.HasMetaBlockItem;
import net.modificationstation.stationapi.api.block.MetaNamedBlockItemProvider;
import net.modificationstation.stationapi.api.registry.Identifier;
import net.modificationstation.stationapi.api.template.block.TemplateBlockBase;
import net.minecraft.entity.player.PlayerBase;
import net.minecraft.level.Level;
import net.minecraft.block.material.Material;
import java.util.Random;

import com.gildedgames.aether.event.listener.TextureListener;
import com.gildedgames.aether.registry.AetherItems;

import net.minecraft.block.BlockBase;
@HasMetaBlockItem
public class BlockAetherLog extends TemplateBlockBase implements MetaNamedBlockItemProvider{
    private static Random rand;
    
    public BlockAetherLog(final Identifier id) {
        super(id, TextureListener.sprSide, Material.WOOD);
    }
    
    @Override
    public int getTextureForSide(final int side, final int meta) {
        if (side <= 1 && meta <= 3) {
            return TextureListener.sprTop;
        }
        if (meta <= 1) {
            return TextureListener.sprSide;
        }
        return TextureListener.sprGoldenSide;
    }
    
    @Override
    public int getDropCount(final Random rand) {
        return 0;
    }
    
    @Override
    public void afterBreak(final Level level, final PlayerBase playerBase, final int x, final int y, final int z, final int meta) {
        playerBase.increaseStat(Stats.mineBlock[this.id], 1);
        ItemInstance stack = new ItemInstance(this.id, 1, 0);
        //if (mod_Aether.equippedSkyrootAxe() && meta != 1) {
        //    final ItemInstance itemInstance = stack;
        //    itemInstance.count *= 2;
        //}
        //SAPI.drop(level, new Loc(x, y, z), stack);
        final ItemInstance itemstack = playerBase.inventory.getHeldItem();
        //if (itemstack == null || (itemstack.itemId != AetherItems.AxeZanite.id && itemstack.itemId != AetherItems.AxeGravitite.id && meta >= 2)) {
        //    return;
        //}
        if (meta > 1 && BlockAetherLog.rand.nextBoolean()) {
            //stack = new ItemInstance(AetherItems.GoldenAmber.id, 2 + BlockAetherLog.rand.nextInt(2), 0);
            //SAPI.drop(level, new Loc(x, y, z), stack);
        }
    }
    
    static {
        BlockAetherLog.rand = new Random();
    }
}
