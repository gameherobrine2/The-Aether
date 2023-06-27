package com.gildedgames.aether.item;

import java.util.List;

import org.jetbrains.annotations.NotNull;

import com.gildedgames.aether.entity.mobs.EntityMiniCloud;

import net.minecraft.entity.EntityBase;
import net.minecraft.entity.player.PlayerBase;
import net.minecraft.level.Level;
import net.modificationstation.stationapi.api.registry.Identifier;
import net.minecraft.item.ItemInstance;

public class ItemCloudStaff extends ItemAether {
    public ItemCloudStaff(final @NotNull Identifier identifier) {
        super(identifier);
        this.maxStackSize = 1;
        this.setDurability(60);
    }
    
    @Override
    public ItemInstance use(final ItemInstance item, final Level level, final PlayerBase player) {
        if (!this.cloudsExist(level, player)) {
            final EntityMiniCloud c1 = new EntityMiniCloud(level, player, false);
            final EntityMiniCloud c2 = new EntityMiniCloud(level, player, true);
            level.spawnEntity(c1);
            level.spawnEntity(c2);
            item.applyDamage(1, null);
        }
        return item;
    }
    
    private boolean cloudsExist(final Level world, final PlayerBase entityplayer) {
        final List<EntityBase> list = world.getEntities(entityplayer, entityplayer.boundingBox.expand(128.0, 128.0, 128.0));
        for (int j = 0; j < list.size(); ++j) {
            final EntityBase entity1 = (EntityBase)list.get(j);
            if (entity1 instanceof EntityMiniCloud) {
                return true;
            }
        }
        return false;
    }
}
