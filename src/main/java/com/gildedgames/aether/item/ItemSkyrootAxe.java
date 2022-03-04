package com.gildedgames.aether.item;

import net.minecraft.item.tool.ToolMaterial;
import net.modificationstation.stationapi.api.registry.Identifier;
import net.modificationstation.stationapi.api.template.item.tool.TemplateToolBase;

import java.util.Random;

import org.jetbrains.annotations.NotNull;

import com.gildedgames.aether.registry.AetherBlocks;

import net.minecraft.block.BlockBase;
import net.minecraft.item.tool.ToolBase;

public class ItemSkyrootAxe extends TemplateToolBase {
    private static BlockBase[] blocksEffectiveAgainst;
    private static Random random;
    
    public ItemSkyrootAxe(final @NotNull Identifier identifier, final ToolMaterial enumtoolmaterial) {
        super(identifier, 3, enumtoolmaterial, ItemSkyrootAxe.blocksEffectiveAgainst);
    }
    
    @Override
    public boolean isEffectiveOn(final BlockBase tile) {
        for (int i = 0; i < ItemSkyrootAxe.blocksEffectiveAgainst.length; ++i) {
            if (ItemSkyrootAxe.blocksEffectiveAgainst[i].id == tile.id) {
                return true;
            }
        }
        return false;
    }
    
    static {
        ItemSkyrootAxe.blocksEffectiveAgainst = new BlockBase[] { AetherBlocks.SKYROOT_PLANKS, AetherBlocks.LOG };
        ItemSkyrootAxe.random = new Random();
    }
}
