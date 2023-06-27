package com.gildedgames.aether.item;
import net.minecraft.item.ItemInstance;
import net.modificationstation.stationapi.api.registry.Identifier;
import net.modificationstation.stationapi.api.template.item.TemplateItemBase;

import org.jetbrains.annotations.NotNull;

import com.gildedgames.aether.event.listener.TextureListener;

public class ItemDart extends TemplateItemBase {
    
    public ItemDart(final @NotNull Identifier identifier) {
        super(identifier);
        this.setHasSubItems(true);
    }
    
    @Override
    public int getTexturePosition(final int damage) {
        if (damage == 0) {
            return TextureListener.sprDartGolden;
        }
        if (damage == 1) {
            return TextureListener.sprDartPoison;
        }
        if (damage == 2) {
            return TextureListener.sprDartEnchanted;
        }
        return TextureListener.sprDartGolden;
    }
    
    @Override
    public String getTranslationKey(final ItemInstance item) {
        int i = item.getDamage();
        if (i > 2) {
            i = 2;
        }
        return this.getTranslationKey() + i;
    }
    
    static {
    }
}
