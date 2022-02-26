package com.gildedgames.aether.item;
import org.jetbrains.annotations.NotNull;

import net.minecraft.item.ItemBase;
import net.modificationstation.stationapi.api.registry.Identifier;
import net.modificationstation.stationapi.api.template.item.TemplateItemBase;

public class ItemMoreArmor extends TemplateItemBase {
    private static final int[] damageReduceAmountArray;
    private static final int[] maxDamageArray;
    public final int armorLevel;
    public final int armorType;
    public final int damageReduceAmount;
    public final int renderIndex;
    private final int colour;
    public String texture;
    public boolean colouriseRender;
    
    public ItemMoreArmor(final @NotNull Identifier identifier, final int j, final int k, final int l, final int col) {
        super(identifier);
        this.armorLevel = j;
        this.armorType = l;
        this.renderIndex = k;
        this.damageReduceAmount = ItemMoreArmor.damageReduceAmountArray[l];
        this.setDurability(ItemMoreArmor.maxDamageArray[l] * 3 << j);
        this.maxStackSize = 1;
        this.colour = col;
        this.colouriseRender = true;
    }
    
    public ItemMoreArmor(final @NotNull Identifier identifier, final int j, final int k, final int l) {
        this(identifier, j, k, l, 16777215);
    }
    
    public ItemMoreArmor(final @NotNull Identifier i, final int j, final String path, final int l) {
        this(i, j, 0, l);
        this.texture = path;
    }
    
    public ItemMoreArmor(final @NotNull Identifier i, final int j, final String path, final int l, final int m) {
        this(i, j, 0, l, m);
        this.texture = path;
    }
    
    public ItemMoreArmor(final @NotNull Identifier i, final int j, final String path, final int l, final int m, final boolean flag) {
        this(i, j, path, l, m);
        this.colouriseRender = flag;
    }
    
    public boolean isTypeValid(final int i) {
        return i == this.armorType || ((i == 8 || i == 9) && (this.armorType == 8 || this.armorType == 9)) || ((i == 7 || i == 11) && (this.armorType == 7 || this.armorType == 11));
    }
    
    public boolean damageType() {
        return this.damageType(this.armorType);
    }
    
    public boolean damageType(final int i) {
        return i < 4 || i == 6 || i == 10;
    }
    
    @Override
    public int getColourMultiplier(final int i) {
        return this.colour;
    }
    
    static {
        damageReduceAmountArray = new int[] { 3, 8, 6, 3, 0, 1, 0, 0, 0, 0, 2, 0 };
        maxDamageArray = new int[] { 11, 16, 15, 13, 10, 10, 8, 10, 10, 10, 10, 10 };
    }
}
