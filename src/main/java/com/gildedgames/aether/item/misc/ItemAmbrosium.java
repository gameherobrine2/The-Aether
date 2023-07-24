package com.gildedgames.aether.item.misc;

import net.minecraft.entity.player.PlayerBase;
import net.minecraft.item.ItemInstance;
import net.minecraft.level.Level;
import net.modificationstation.stationapi.api.registry.Identifier;
import net.modificationstation.stationapi.api.template.item.TemplateItemBase;
import org.jetbrains.annotations.NotNull;

public class ItemAmbrosium extends TemplateItemBase
{
    private int healAmount;

    public ItemAmbrosium(final @NotNull Identifier identifier, final int j)
    {
        super(identifier);
        this.healAmount = j;
        this.maxStackSize = 64;
    }

    @Override
    public ItemInstance use(final ItemInstance item, final Level level, final PlayerBase player)
    {
        --item.count;
        player.addHealth(this.healAmount);
        return item;
    }

    public int getHealAmount()
    {
        return this.healAmount;
    }
}
