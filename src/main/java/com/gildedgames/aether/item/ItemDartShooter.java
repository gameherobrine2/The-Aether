package com.gildedgames.aether.item;
import net.minecraft.inventory.InventoryBase;
import net.minecraft.entity.Living;

import org.jetbrains.annotations.NotNull;

import com.gildedgames.aether.entity.projectile.EntityDartEnchanted;
import com.gildedgames.aether.entity.projectile.EntityDartGolden;
import com.gildedgames.aether.entity.projectile.EntityDartPoison;
import com.gildedgames.aether.event.listener.TextureListener;
import com.gildedgames.aether.registry.AetherItems;

import net.minecraft.entity.EntityBase;
import net.minecraft.entity.player.PlayerBase;
import net.minecraft.level.Level;
import net.modificationstation.stationapi.api.registry.Identifier;
import net.modificationstation.stationapi.api.template.item.TemplateItemBase;
import net.minecraft.item.ItemInstance;
import net.minecraft.item.ItemBase;

public class ItemDartShooter extends TemplateItemBase {

    
    public ItemDartShooter(final @NotNull Identifier identifier) {
        super(identifier);
        this.setHasSubItems(true);
        this.maxStackSize = 1;
    }
    
    @Override
    public int getTexturePosition(final int damage) {
        if (damage == 0) {
            return TextureListener.sprShooterNormal;
        }
        if (damage == 1) {
            return TextureListener.sprShooterPoison;
        }
        if (damage == 2) {
            return TextureListener.sprShooterEnchanted;
        }
        return TextureListener.sprShooterNormal;
    }
    
    @Override
    public String getTranslationKey(final ItemInstance item) {
        int i = item.getDamage();
        if (i > 2) {
            i = 2;
        }
        return this.getTranslationKey() + i;
    }
    
    @Override
    public ItemInstance use(final ItemInstance item, final Level level, final PlayerBase player) {
        final int consume = this.consumeItem(player, AetherItems.Dart.id, item.getDamage());
        if (consume != -1) {
            level.playSound((EntityBase)player, "aether.sound.other.dartshooter.shootDart", 2.0f, 1.0f / (ItemDartShooter.rand.nextFloat() * 0.4f + 0.8f));
            if (!level.isServerSide) {
                EntityDartGolden dart = null;
                switch(consume) {
                case 1:
                	dart = new EntityDartPoison(level, player);
                case 2:
                	dart = new EntityDartEnchanted(level, player);
                default:
                	dart = new EntityDartGolden(level, player);
                }
                level.spawnEntity(dart);
            }
        }
        return item;
    }
    
    private int consumeItem(final PlayerBase player, final int itemID, final int maxDamage) {
        final InventoryBase inv = player.inventory;
        for (int i = 0; i < inv.getInventorySize(); ++i) {
            ItemInstance stack = inv.getInventoryItem(i);
            if (stack != null) {
                final int damage = stack.getDamage();
                if (stack.itemId == itemID && stack.getDamage() == maxDamage) {
                    final ItemInstance itemInstance = stack;
                    --itemInstance.count;
                    if (stack.count == 0) {
                        stack = null;
                    }
                    inv.setInventoryItem(i, stack);
                    return damage;
                }
            }
        }
        return -1;
    }
    
    static {
    }
}
