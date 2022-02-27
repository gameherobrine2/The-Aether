package com.gildedgames.aether.item;
import net.minecraft.item.ItemInstance;
import net.modificationstation.stationapi.api.registry.Identifier;
import net.modificationstation.stationapi.api.template.item.TemplateItemBase;

import org.jetbrains.annotations.NotNull;

import net.minecraft.item.ItemBase;

public class ItemAetherKey extends TemplateItemBase {
    public ItemAetherKey(final @NotNull Identifier identifier) {
        super(identifier);
        //this.setTexturePosition(ModLoader.addOverride("/gui/items.png", "/aether/items/Key.png"));
        this.setTranslationKey("AetherKey");
        this.setHasSubItems(true);
        this.maxStackSize = 1;
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
    public int getColourMultiplier(final int damage) {
        if (damage == 1) {
            return -6710887;
        }
        if (damage == 2) {
            return -13312;
        }
        return -7638187;
    }
}
