package com.gildedgames.aether.item.misc;

import com.gildedgames.aether.utils.MoaColour;
import net.minecraft.item.ItemInstance;
import net.modificationstation.stationapi.api.registry.Identifier;
import net.modificationstation.stationapi.api.template.item.TemplateItemBase;
import org.jetbrains.annotations.NotNull;

public class ItemMoaEgg extends TemplateItemBase
{
    public ItemMoaEgg(final @NotNull Identifier identifier)
    {
        super(identifier);
        this.setHasSubItems(true);
    }

    @Override
    public int getColourMultiplier(final int damage)
    {
        return MoaColour.getColour(damage).colour;
    }

    @Override
    public String getTranslationKey(final ItemInstance item)
    {
        int i = item.getDamage();
        if (i > MoaColour.colours.size() - 1)
        {
            i = MoaColour.colours.size() - 1;
        }
        return this.getTranslationKey() + i;
    }
}
