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

public class ItemValkyrieSpade extends TemplateToolBase implements IReach {
    private static BlockBase[] blocksEffectiveAgainst;
    private static Random random;
    
    public ItemValkyrieSpade(final @NotNull Identifier identifier, final ToolMaterial enumtoolmaterial) {
        super(identifier, 1, enumtoolmaterial, ItemValkyrieSpade.blocksEffectiveAgainst);
        //SAPI.reachAdd((IReach)this);
    }
    
    public boolean isEffectiveOn(final BlockBase tile) {
        for (int i = 0; i < ItemValkyrieSpade.blocksEffectiveAgainst.length; ++i) {
            if (ItemValkyrieSpade.blocksEffectiveAgainst[i].id == tile.id) {
                return true;
            }
        }
        return false;
    }
    
    public boolean reachItemMatches(final ItemInstance itemstack) {
        return itemstack != null && itemstack.itemId == AetherItems.ShovelValkyrie.id;
    }
    
    public float getReach(final ItemInstance itemstack) {
        return 10.0f;
    }
    
    static {
        ItemValkyrieSpade.blocksEffectiveAgainst = new BlockBase[] { AetherBlocks.QUICKSOIL, AetherBlocks.AETHER_DIRT, AetherBlocks.AETHER_GRASS_BLOCK, AetherBlocks.AERCLOUD };
        ItemValkyrieSpade.random = new Random();
    }
}
