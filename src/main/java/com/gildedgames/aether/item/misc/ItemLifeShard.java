package com.gildedgames.aether.item.misc;

import com.gildedgames.aether.AetherMod;
import net.minecraft.entity.player.PlayerBase;
import net.minecraft.item.ItemInstance;
import net.minecraft.level.Level;
import net.modificationstation.stationapi.api.registry.Identifier;
import net.modificationstation.stationapi.api.template.item.TemplateItemBase;
import org.jetbrains.annotations.NotNull;

public class ItemLifeShard extends TemplateItemBase
{
    public ItemLifeShard(final @NotNull Identifier identifier)
    {
        super(identifier);
        this.maxStackSize = 1;
    }

    @Override
    public ItemInstance use(final ItemInstance item, final Level level, final PlayerBase player)
    {
        --item.count;
        AetherMod.getPlayerHandler(player).increaseMaxHP(2);
        return item;
    }
}
