package com.gildedgames.aether.item;

import org.jetbrains.annotations.NotNull;

import com.gildedgames.aether.entity.projectile.EntityFlamingArrow;

import net.minecraft.entity.EntityBase;
import net.minecraft.entity.player.PlayerBase;
import net.minecraft.level.Level;
import net.modificationstation.stationapi.api.registry.Identifier;
import net.minecraft.item.ItemInstance;
import net.minecraft.item.ItemBase;

public class ItemPhoenixBow extends ItemAether {
    public ItemPhoenixBow(final @NotNull Identifier identifier) {
        super(identifier);
        this.maxStackSize = 1;
    }
    
    @Override
    public ItemInstance use(final ItemInstance item, final Level level, final PlayerBase player) {
        if (player.inventory.decreaseAmountOfItem(ItemBase.arrow.id)) {
            level.playSound((EntityBase)player, "mob.ghast.fireball", 1.0f, 1.0f / (ItemPhoenixBow.rand.nextFloat() * 0.4f + 0.8f));
            if (!level.isServerSide) {
               level.spawnEntity(new EntityFlamingArrow(level, player));
            }
        }
        return item;
    }
}
