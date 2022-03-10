package com.gildedgames.aether.item;

import net.minecraft.item.ItemInstance;
import net.minecraft.item.tool.ToolMaterial;
import net.modificationstation.stationapi.api.registry.Identifier;
import net.modificationstation.stationapi.api.template.item.tool.TemplateToolBase;

import org.jetbrains.annotations.NotNull;

import com.gildedgames.aether.registry.AetherBlocks;

import net.minecraft.block.BlockBase;
import net.minecraft.item.tool.ToolBase;

public class ItemZanitePickaxe extends TemplateToolBase {
    private static BlockBase[] blocksEffectiveAgainst;
    
    public ItemZanitePickaxe(final @NotNull Identifier identifier, final ToolMaterial enumtoolmaterial) {
        super(identifier, 2, enumtoolmaterial, ItemZanitePickaxe.blocksEffectiveAgainst);
    }
    
    @Override
    public float getStrengthOnBlock(final ItemInstance item, final BlockBase tile) {
        return super.getStrengthOnBlock(item, tile) * (2.0f * item.getDamage() / item.getType().getDurability() + 0.5f);
    }
    
    @Override
    public boolean isEffectiveOn(final BlockBase tile) {
        for (int i = 0; i < ItemZanitePickaxe.blocksEffectiveAgainst.length; ++i) {
            if (ItemZanitePickaxe.blocksEffectiveAgainst[i].id == tile.id) {
                return true;
            }
        }
        return false;
    }
    
    static {
        ItemZanitePickaxe.blocksEffectiveAgainst = new BlockBase[] { AetherBlocks.HOLYSTONE, AetherBlocks.ICESTONE, AetherBlocks.ENCHANTED_GRAVITITE, AetherBlocks.GRAVITITE_ORE, 
        		AetherBlocks.ZANITE_ORE, AetherBlocks.AMBROSIUM_ORE, AetherBlocks.LIGHT_DUNGEON_STONE, AetherBlocks.DUNGEON_STONE, AetherBlocks.PILLAR, AetherBlocks.ENCHANTER, 
        		AetherBlocks.INCUBATOR, AetherBlocks.ZANITE_BLOCK, AetherBlocks.FREEZER, AetherBlocks.QUICKSOIL_GLASS };
    }
}
