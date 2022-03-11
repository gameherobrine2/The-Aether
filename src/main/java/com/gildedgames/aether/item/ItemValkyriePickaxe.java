package com.gildedgames.aether.item;

import net.minecraft.item.ItemInstance;
import net.minecraft.item.tool.ToolMaterial;
import net.modificationstation.stationapi.api.registry.Identifier;
import net.modificationstation.stationapi.api.template.item.tool.TemplateToolBase;

import java.util.Random;

import org.jetbrains.annotations.NotNull;

import com.gildedgames.aether.item.base.IReach;
import com.gildedgames.aether.registry.AetherBlocks;
import com.gildedgames.aether.registry.AetherItems;

import net.minecraft.block.BlockBase;
import net.minecraft.item.tool.ToolBase;

public class ItemValkyriePickaxe extends TemplateToolBase implements IReach {
    private static BlockBase[] blocksEffectiveAgainst;
    private static Random random;
    
    public ItemValkyriePickaxe(final @NotNull Identifier identifier, final ToolMaterial enumtoolmaterial) {
        super(identifier, 2, enumtoolmaterial, ItemValkyriePickaxe.blocksEffectiveAgainst);
        //SAPI.reachAdd((IReach)this);
    }
    
    public boolean isEffectiveOn(final BlockBase tile) {
        for (int i = 0; i < ItemValkyriePickaxe.blocksEffectiveAgainst.length; ++i) {
            if (ItemValkyriePickaxe.blocksEffectiveAgainst[i].id == tile.id) {
                return true;
            }
        }
        return false;
    }
    
    public boolean reachItemMatches(final ItemInstance itemstack) {
        return itemstack != null && itemstack.itemId == AetherItems.PickValkyrie.id;
    }
    
    public float getReach(final ItemInstance itemstack) {
        return 10.0f;
    }
    
    static {
        ItemValkyriePickaxe.blocksEffectiveAgainst = new BlockBase[] { AetherBlocks.HOLYSTONE, AetherBlocks.ICESTONE, AetherBlocks.ENCHANTED_GRAVITITE, AetherBlocks.GRAVITITE_ORE,
        		AetherBlocks.ZANITE_ORE, AetherBlocks.AMBROSIUM_ORE, AetherBlocks.LIGHT_DUNGEON_STONE, AetherBlocks.DUNGEON_STONE, AetherBlocks.PILLAR, AetherBlocks.AEROGEL, 
        		AetherBlocks.ENCHANTER, AetherBlocks.INCUBATOR, AetherBlocks.ZANITE_BLOCK, AetherBlocks.FREEZER, AetherBlocks.QUICKSOIL_GLASS };
        ItemValkyriePickaxe.random = new Random();
    }
}
