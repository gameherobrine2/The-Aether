package com.gildedgames.aether.item.tool;

import net.minecraft.entity.player.PlayerBase;
import java.util.Random;

import org.jetbrains.annotations.NotNull;

import com.gildedgames.aether.registry.AetherItems;

import net.minecraft.entity.Living;
import net.minecraft.item.ItemInstance;
import net.minecraft.item.tool.ToolMaterial;
import net.modificationstation.stationapi.api.registry.Identifier;
import net.modificationstation.stationapi.api.template.item.tool.TemplateSword;

public class ItemSwordHolystone extends TemplateSword {
    public ItemSwordHolystone(final @NotNull Identifier identifier, final ToolMaterial mat) {
        super(identifier, mat);
    }
    
    @Override
    public boolean postHit(final ItemInstance itemstack, final Living damageSource, final Living damageTarget) {
        if (new Random().nextInt(25) == 0 && damageTarget != null && damageTarget instanceof PlayerBase && (damageSource.hurtTime > 0 || damageSource.deathTime > 0)) {
            damageSource.dropItem(AetherItems.AmbrosiumShard.id, 1, 0.0f);
            itemstack.applyDamage(1, damageTarget);
        }
        itemstack.applyDamage(1, damageTarget);
        return true;
    }
}
