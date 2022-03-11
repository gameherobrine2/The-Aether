package com.gildedgames.aether.item;

import net.minecraft.item.ItemInstance;
import net.minecraft.item.tool.ToolMaterial;
import net.modificationstation.stationapi.api.registry.Identifier;
import net.modificationstation.stationapi.api.template.item.tool.TemplateToolBase;
import net.minecraft.client.gui.screen.menu.SelectWorld;
import java.util.Random;

import org.jetbrains.annotations.NotNull;

import com.gildedgames.aether.item.base.IReach;
import com.gildedgames.aether.registry.AetherBlocks;
import com.gildedgames.aether.registry.AetherItems;

import net.minecraft.block.BlockBase;
import net.minecraft.item.tool.ToolBase;

public class ItemValkyrieAxe extends TemplateToolBase implements IReach {
    private static BlockBase[] blocksEffectiveAgainst;
    private static Random random;
    
    public ItemValkyrieAxe(final @NotNull Identifier identifier, final ToolMaterial enumtoolmaterial) {
        super(identifier, 3, enumtoolmaterial, ItemValkyrieAxe.blocksEffectiveAgainst);
        //SAPI.reachAdd((IReach)this);
    }
    
    public boolean isEffectiveOn(final BlockBase tile) {
        for (int i = 0; i < ItemValkyrieAxe.blocksEffectiveAgainst.length; ++i) {
            if (ItemValkyrieAxe.blocksEffectiveAgainst[i].id == tile.id) {
                return true;
            }
        }
        return false;
    }
    
    public boolean reachItemMatches(final ItemInstance itemstack) {
        return itemstack != null && itemstack.itemId == AetherItems.AxeValkyrie.id;
    }
    
    @Override
    public float getReach(final ItemInstance itemstack) {
        return 10.0f;
    }
    
    static {
        ItemValkyrieAxe.blocksEffectiveAgainst = new BlockBase[] { AetherBlocks.SKYROOT_PLANKS, AetherBlocks.LOG };
        ItemValkyrieAxe.random = new Random();
    }
}
