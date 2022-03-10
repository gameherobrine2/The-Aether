package com.gildedgames.aether.item;

import net.minecraft.item.ItemInstance;
import net.minecraft.item.tool.ToolMaterial;
import net.modificationstation.stationapi.api.registry.Identifier;
import net.modificationstation.stationapi.api.template.item.tool.TemplateToolBase;

import org.jetbrains.annotations.NotNull;

import com.gildedgames.aether.registry.AetherBlocks;

import net.minecraft.block.BlockBase;
import net.minecraft.item.tool.ToolBase;

public class ItemZaniteSpade extends TemplateToolBase {
    private static BlockBase[] blocksEffectiveAgainst;
    
    public ItemZaniteSpade(final @NotNull Identifier identifier, final ToolMaterial enumtoolmaterial) {
        super(identifier, 1, enumtoolmaterial, ItemZaniteSpade.blocksEffectiveAgainst);
    }
    
    @Override
    public float getStrengthOnBlock(final ItemInstance item, final BlockBase tile) {
        return super.getStrengthOnBlock(item, tile) * (item.getDamage() / item.getType().getDurability() + 0.5f);
    }
    
    @Override
    public boolean isEffectiveOn(final BlockBase tile) {
        for (int i = 0; i < ItemZaniteSpade.blocksEffectiveAgainst.length; ++i) {
            if (ItemZaniteSpade.blocksEffectiveAgainst[i].id == tile.id) {
                return true;
            }
        }
        return false;
    }
    
    static {
        ItemZaniteSpade.blocksEffectiveAgainst = new BlockBase[] { AetherBlocks.QUICKSOIL, AetherBlocks.AETHER_DIRT, AetherBlocks.AETHER_GRASS_BLOCK, AetherBlocks.AERCLOUD };
    }
}
