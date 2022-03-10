package com.gildedgames.aether.item;

import net.minecraft.item.ItemInstance;
import net.minecraft.item.tool.ToolMaterial;
import net.modificationstation.stationapi.api.registry.Identifier;
import net.modificationstation.stationapi.api.template.item.tool.TemplateToolBase;

import org.jetbrains.annotations.NotNull;

import com.gildedgames.aether.registry.AetherBlocks;

import net.minecraft.block.BlockBase;
import net.minecraft.item.tool.ToolBase;

public class ItemZaniteAxe extends TemplateToolBase {
    private static BlockBase[] blocksEffectiveAgainst;
    
    public ItemZaniteAxe(final @NotNull Identifier identifier, final ToolMaterial enumtoolmaterial) {
        super(identifier, 3, enumtoolmaterial, ItemZaniteAxe.blocksEffectiveAgainst);
    }
    
    @Override
    public boolean isEffectiveOn(final BlockBase tile) {
        for (int i = 0; i < ItemZaniteAxe.blocksEffectiveAgainst.length; ++i) {
            if (ItemZaniteAxe.blocksEffectiveAgainst[i].id == tile.id) {
                return true;
            }
        }
        return false;
    }
    
    @Override
    public float getStrengthOnBlock(final ItemInstance item, final BlockBase tile) {
        return super.getStrengthOnBlock(item, tile) * (2.0f * item.getDamage() / item.getType().getDurability() + 0.5f);
    }
    
    static {
        ItemZaniteAxe.blocksEffectiveAgainst = new BlockBase[] { AetherBlocks.SKYROOT_PLANKS, AetherBlocks.LOG };
    }
}
