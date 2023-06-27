package com.gildedgames.aether.item;

import net.minecraft.entity.player.PlayerBase;
import net.minecraft.item.ItemInstance;
import net.minecraft.level.Level;
import net.modificationstation.stationapi.api.registry.Identifier;
import org.jetbrains.annotations.NotNull;

public class ItemLightningKnife extends ItemAether {
    public ItemLightningKnife(final @NotNull Identifier identifier) {
        super(identifier);
        this.maxStackSize = 16;
    }
    
    @Override
    public ItemInstance use(final ItemInstance item, final Level level, final PlayerBase player) {
        --item.count;
        level.playSound(player, "aether:aether.sound.other.dartshooter.shootdart", 2.0f, 1.0f / (ItemLightningKnife.rand.nextFloat() * 0.4f + 0.8f));
        //if (!level.isServerSide) {
            //TODO knife level.spawnEntity(new EntityLightningKnife(level, player));
        //}
        return item;
    }
}
