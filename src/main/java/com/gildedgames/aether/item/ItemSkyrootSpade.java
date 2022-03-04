package com.gildedgames.aether.item;

import net.minecraft.item.tool.ToolMaterial;
import net.modificationstation.stationapi.api.registry.Identifier;
import net.modificationstation.stationapi.api.template.item.tool.TemplateToolBase;

import java.util.Random;

import org.jetbrains.annotations.NotNull;

import com.gildedgames.aether.registry.AetherBlocks;

import net.minecraft.block.BlockBase;
import net.minecraft.item.tool.ToolBase;

public class ItemSkyrootSpade extends TemplateToolBase {
    private static BlockBase[] blocksEffectiveAgainst;
    private static Random random;
    
    public ItemSkyrootSpade(final @NotNull Identifier identifier, final ToolMaterial enumtoolmaterial) {
        super(identifier, 1, enumtoolmaterial, ItemSkyrootSpade.blocksEffectiveAgainst);
    }
    
    @Override
    public boolean isEffectiveOn(final BlockBase tile) {
        for (int i = 0; i < ItemSkyrootSpade.blocksEffectiveAgainst.length; ++i) {
            if (ItemSkyrootSpade.blocksEffectiveAgainst[i].id == tile.id) {
                return true;
            }
        }
        return false;
    }
    
    static {
        ItemSkyrootSpade.blocksEffectiveAgainst = new BlockBase[] { AetherBlocks.QUICKSOIL, AetherBlocks.AETHER_DIRT, AetherBlocks.AETHER_GRASS_BLOCK, AetherBlocks.AERCLOUD };
        ItemSkyrootSpade.random = new Random();
    }
}
