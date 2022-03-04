package com.gildedgames.aether.item;

import net.minecraft.item.tool.ToolMaterial;
import net.modificationstation.stationapi.api.registry.Identifier;
import net.modificationstation.stationapi.api.template.item.tool.TemplateToolBase;

import java.util.Random;

import org.jetbrains.annotations.NotNull;

import com.gildedgames.aether.registry.AetherBlocks;

import net.minecraft.block.BlockBase;
import net.minecraft.item.tool.ToolBase;

public class ItemSkyrootPickaxe extends TemplateToolBase {
    private static BlockBase[] blocksEffectiveAgainst;
    private static Random random;
    
    public ItemSkyrootPickaxe(final @NotNull Identifier identifier, final ToolMaterial enumtoolmaterial) {
        super(identifier, 2, enumtoolmaterial, ItemSkyrootPickaxe.blocksEffectiveAgainst);
    }
    
    @Override
    public boolean isEffectiveOn(final BlockBase tile) {
        for (int i = 0; i < ItemSkyrootPickaxe.blocksEffectiveAgainst.length; ++i) {
            if (ItemSkyrootPickaxe.blocksEffectiveAgainst[i].id == tile.id) {
                return true;
            }
        }
        return false;
    }
   
    
    static {
        ItemSkyrootPickaxe.blocksEffectiveAgainst = new BlockBase[] { AetherBlocks.HOLYSTONE, AetherBlocks.AMBROSIUM_ORE, AetherBlocks.FREEZER, AetherBlocks.QUICKSOIL_GLASS, AetherBlocks.INCUBATOR };
        ItemSkyrootPickaxe.random = new Random();
    }
}
