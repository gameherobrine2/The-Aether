package com.gildedgames.aether.item.misc;

import org.jetbrains.annotations.NotNull;

import com.gildedgames.aether.Aether;

import net.minecraft.entity.player.PlayerBase;
import net.minecraft.level.Level;
import net.modificationstation.stationapi.api.registry.Identifier;
import net.modificationstation.stationapi.api.template.item.TemplateItemBase;
import net.minecraft.item.ItemInstance;

public class ItemLifeShard extends TemplateItemBase {
    public ItemLifeShard(final @NotNull Identifier identifier) {
        super(identifier);
        this.maxStackSize = 1;
    }
    
    @Override
    public ItemInstance use(final ItemInstance item, final Level level, final PlayerBase player) {
        --item.count;
        Aether.getPlayerHandler(player).increaseMaxHP(2);
        return item;
    }
}
