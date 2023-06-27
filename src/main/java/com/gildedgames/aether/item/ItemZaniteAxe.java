package com.gildedgames.aether.item;

import net.minecraft.item.ItemInstance;
import net.minecraft.item.tool.ToolMaterial;
import net.modificationstation.stationapi.api.registry.Identifier;
import net.modificationstation.stationapi.api.template.item.tool.TemplateHatchet;

import org.jetbrains.annotations.NotNull;

import net.minecraft.block.BlockBase;

public class ItemZaniteAxe extends TemplateHatchet {
    public ItemZaniteAxe(final @NotNull Identifier identifier, final ToolMaterial enumtoolmaterial) {
        super(identifier, enumtoolmaterial);
    }
    
    @Override
    public float getStrengthOnBlock(final ItemInstance item, final BlockBase tile) {
        return super.getStrengthOnBlock(item, tile) * (2.0f * item.getDamage() / item.getType().getDurability() + 0.5f);
    }
}
