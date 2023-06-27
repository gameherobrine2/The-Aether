package com.gildedgames.aether.item;
import org.jetbrains.annotations.NotNull;

import com.gildedgames.aether.entity.EntityCloudParachute;
import com.gildedgames.aether.registry.AetherItems;

import net.minecraft.entity.EntityBase;
import net.minecraft.entity.player.PlayerBase;
import net.minecraft.level.Level;
import net.modificationstation.stationapi.api.registry.Identifier;
import net.modificationstation.stationapi.api.template.item.TemplateItemBase;
import net.minecraft.item.ItemInstance;

public class ItemCloudParachute extends TemplateItemBase {
    
    public ItemCloudParachute(final @NotNull Identifier identifier,boolean isGolden) {
        super(identifier);
        this.maxStackSize = 1;
        this.setDurability(isGolden ? 20 : 0);
    }
    
    @Override
    public ItemInstance use(final ItemInstance item, final Level level, final PlayerBase player) {
        if (EntityCloudParachute.entityHasRoomForCloud(level, player)) {
            for (int i = 0; i < 32; ++i) {
                EntityCloudParachute.doCloudSmoke(level, player);
            }
            if (this.id == AetherItems.CloudParachuteGold.id) {
                item.applyDamage(1, player);
            }
            else {
                --item.count;
            }
            level.playSound((EntityBase)player, "cloud", 1.0f, 1.0f / (ItemCloudParachute.rand.nextFloat() * 0.1f + 0.95f));
            if (!level.isServerSide) {
                level.spawnEntity(new EntityCloudParachute(level, player, this.id == AetherItems.CloudParachuteGold.id));
            }
        }
        return item;
    }
    
    @Override
    public int getColourMultiplier(final int i) {
        if (this.id == AetherItems.CloudParachuteGold.id) {
            return 16777087;
        }
        return 16777215;
    }
    
    static {
    }
}
