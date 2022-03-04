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

public class ItemHolystonePickaxe extends TemplateToolBase {
    private static BlockBase[] blocksEffectiveAgainst;
    private static Random random;
    
    public ItemHolystonePickaxe(final @NotNull Identifier identifier, final ToolMaterial enumtoolmaterial) {
        super(identifier, 2, enumtoolmaterial, ItemHolystonePickaxe.blocksEffectiveAgainst);
    }
    
    @Override
    public boolean postMine(final ItemInstance itemstack, final int i, final int j, final int k, final int l, final Living damageTarget) {
        if (ItemHolystonePickaxe.random.nextInt(50) == 0) {
            damageTarget.dropItem(AetherItems.AmbrosiumShard.id, 1, 0.0f);
        }
        return super.postMine(itemstack, i, j, k, l, damageTarget);
    }
    

    @Override
    public boolean isEffectiveOn(final BlockBase tile) {
        for (int i = 0; i < ItemHolystonePickaxe.blocksEffectiveAgainst.length; ++i) {
            if (ItemHolystonePickaxe.blocksEffectiveAgainst[i].id == tile.id) {
                return true;
            }
        }
        return false;
    }
    
    static {
        ItemHolystonePickaxe.blocksEffectiveAgainst = new BlockBase[] { AetherBlocks.HOLYSTONE, AetherBlocks.ICESTONE, AetherBlocks.ZANITE_ORE, AetherBlocks.AMBROSIUM_ORE, AetherBlocks.LIGHT_DUNGEON_STONE, AetherBlocks.DUNGEON_STONE, AetherBlocks.PILLAR, AetherBlocks.ENCHANTER, AetherBlocks.INCUBATOR, AetherBlocks.ZANITE_BLOCK, AetherBlocks.FREEZER, AetherBlocks.QUICKSOIL_GLASS };
        ItemHolystonePickaxe.random = new Random();
    }
}
