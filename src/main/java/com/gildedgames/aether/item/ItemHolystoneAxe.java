package com.gildedgames.aether.item;

import net.minecraft.entity.Living;
import net.minecraft.item.ItemInstance;
import net.minecraft.item.tool.ToolMaterial;
import net.modificationstation.stationapi.api.registry.Identifier;
import net.modificationstation.stationapi.api.template.item.tool.TemplateToolBase;

import java.util.Random;

import org.jetbrains.annotations.NotNull;

import com.gildedgames.aether.registry.AetherBlocks;
import com.gildedgames.aether.registry.AetherItems;

import net.minecraft.block.BlockBase;
import net.minecraft.item.tool.ToolBase;

public class ItemHolystoneAxe extends TemplateToolBase {
    private static BlockBase[] blocksEffectiveAgainst;
    private static Random random;
    
    public ItemHolystoneAxe(final @NotNull Identifier identifier, final ToolMaterial enumtoolmaterial) {
        super(identifier, 3, enumtoolmaterial, ItemHolystoneAxe.blocksEffectiveAgainst);
    }
    
    @Override
    public boolean postMine(final ItemInstance itemstack, final int i, final int j, final int k, final int l, final Living damageTarget) {
        if (ItemHolystoneAxe.random.nextInt(50) == 0) {
            damageTarget.dropItem(AetherItems.AmbrosiumShard.id, 1, 0.0f);
        }
        return super.postMine(itemstack, i, j, k, l, damageTarget);
    }
    
    @Override
    public boolean isEffectiveOn(final BlockBase tile) {
        for (int i = 0; i < ItemHolystoneAxe.blocksEffectiveAgainst.length; ++i) {
            if (ItemHolystoneAxe.blocksEffectiveAgainst[i].id == tile.id) {
                return true;
            }
        }
        return false;
    }
    
    static {
        ItemHolystoneAxe.blocksEffectiveAgainst = new BlockBase[] { AetherBlocks.SKYROOT_PLANKS, AetherBlocks.LOG };
        ItemHolystoneAxe.random = new Random();
    }
}
