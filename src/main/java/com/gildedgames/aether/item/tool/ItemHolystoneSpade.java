package com.gildedgames.aether.item.tool;

import com.gildedgames.aether.registry.AetherItems;
import net.minecraft.entity.Living;
import net.minecraft.item.ItemInstance;
import net.minecraft.item.tool.ToolMaterial;
import net.modificationstation.stationapi.api.registry.Identifier;
import net.modificationstation.stationapi.api.template.item.tool.TemplateShovel;
import org.jetbrains.annotations.NotNull;

public class ItemHolystoneSpade extends TemplateShovel
{

    public ItemHolystoneSpade(final @NotNull Identifier identifier, final ToolMaterial enumtoolmaterial)
    {
        super(identifier, enumtoolmaterial);
    }

    @Override
    public boolean postMine(final ItemInstance itemstack, final int i, final int j, final int k, final int l, final Living damageTarget)
    {
        if (rand.nextInt(50) == 0)
        {
            damageTarget.dropItem(AetherItems.AmbrosiumShard.id, 1, 0.0f);
        }
        return super.postMine(itemstack, i, j, k, l, damageTarget);
    }
}
