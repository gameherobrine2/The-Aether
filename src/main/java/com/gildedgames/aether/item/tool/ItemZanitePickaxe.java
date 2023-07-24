package com.gildedgames.aether.item.tool;

import net.minecraft.block.BlockBase;
import net.minecraft.item.ItemInstance;
import net.minecraft.item.tool.ToolMaterial;
import net.modificationstation.stationapi.api.registry.Identifier;
import net.modificationstation.stationapi.api.template.item.tool.TemplatePickaxe;
import org.jetbrains.annotations.NotNull;

public class ItemZanitePickaxe extends TemplatePickaxe
{
    public ItemZanitePickaxe(final @NotNull Identifier identifier, final ToolMaterial enumtoolmaterial)
    {
        super(identifier, enumtoolmaterial);
    }

    @Override
    public float getStrengthOnBlock(final ItemInstance item, final BlockBase tile)
    {
        return super.getStrengthOnBlock(item, tile) * (2.0f * item.getDamage() / item.getType().getDurability() + 0.5f);
    }
}
