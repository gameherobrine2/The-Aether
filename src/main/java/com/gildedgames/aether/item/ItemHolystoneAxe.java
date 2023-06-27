package com.gildedgames.aether.item;

import net.minecraft.entity.Living;
import net.minecraft.item.ItemInstance;
import net.minecraft.item.tool.ToolMaterial;
import net.modificationstation.stationapi.api.registry.Identifier;
import net.modificationstation.stationapi.api.template.item.tool.TemplateHatchet;

import org.jetbrains.annotations.NotNull;

import com.gildedgames.aether.registry.AetherItems;

public class ItemHolystoneAxe extends TemplateHatchet {

    public ItemHolystoneAxe(final @NotNull Identifier identifier, final ToolMaterial enumtoolmaterial) {
        super(identifier, enumtoolmaterial);
    }

    @Override
    public boolean postMine(final ItemInstance itemstack, final int i, final int j, final int k, final int l, final Living damageTarget) {
        if (rand.nextInt(50) == 0) {
            damageTarget.dropItem(AetherItems.AmbrosiumShard.id, 1, 0.0f);
        }
        return super.postMine(itemstack, i, j, k, l, damageTarget);
    }
}
