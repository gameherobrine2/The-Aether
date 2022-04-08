package com.gildedgames.aether.item;

import net.minecraft.entity.Living;

import org.jetbrains.annotations.NotNull;

import net.minecraft.entity.EntityBase;
import net.minecraft.entity.player.PlayerBase;
import net.minecraft.level.Level;
import net.modificationstation.stationapi.api.registry.Identifier;
import net.minecraft.item.ItemInstance;
import net.minecraft.item.ItemBase;

public class ItemLightningKnife extends ItemAether {
    public ItemLightningKnife(final @NotNull Identifier identifier) {
        super(identifier);
        this.maxStackSize = 16;
    }
    
    @Override
    public ItemInstance use(final ItemInstance item, final Level level, final PlayerBase player) {
        --item.count;
        level.playSound((EntityBase)player, "mob.aether.dartshoot", 2.0f, 1.0f / (ItemLightningKnife.rand.nextFloat() * 0.4f + 0.8f));
        if (!level.isServerSide) {
            //TODO knife level.spawnEntity(new EntityLightningKnife(level, player));
        }
        return item;
    }
}
