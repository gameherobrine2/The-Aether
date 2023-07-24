package com.gildedgames.aether.item.misc;

import net.minecraft.entity.player.PlayerBase;
import net.minecraft.item.ItemInstance;
import net.minecraft.level.Level;
import net.modificationstation.stationapi.api.registry.Identifier;
import org.jetbrains.annotations.NotNull;

public class ItemGummieSwet extends ItemAether
{
    private int healAmount;
    private boolean damZero;
    private boolean damOne;

    public ItemGummieSwet(final @NotNull Identifier identifier)
    {
        super(identifier);
        this.maxStackSize = 64;
        this.damZero = false;
        this.damOne = false;
        this.healAmount = 20;
        this.setHasSubItems(true);
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

    @Override
    public int getColourMultiplier(final int damage)
    {
        if (damage == 1)
        {
            return 16777087;
        }
        return 8765927;
    }

    @Override
    public String getTranslationKey(final ItemInstance item)
    {
        int i = item.getDamage();
        if (i > 1)
        {
            i = 1;
        }
        return this.getTranslationKey() + i;
    }
}
