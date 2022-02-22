package com.gildedgames.aether.item.block;
import net.minecraft.item.ItemInstance;
import net.modificationstation.stationapi.api.template.item.MetaNamedBlock;
import net.minecraft.item.Block;

public class ItemBlockAercloud extends MetaNamedBlock {
    public ItemBlockAercloud(final int id) {
        super(id);
        this.setTranslationKey("BlockAercloud");
        this.setHasSubItems(true);
    }
    
    @Override
    public String getTranslationKey(final ItemInstance item) {
        int i = item.getDamage();
        if (i > 2) {
            i = 2;
        }
        return this.getTranslationKey() + i;
    }
    
    public int getColourMultiplier(final int damage) {
        if (damage == 1) {
            return -7829249;
        }
        if (damage == 2) {
            return -120;
        }
        return -1;
    }
    
    @Override
    public int getMetaData(final int damage) {
        return damage;
    }
}
