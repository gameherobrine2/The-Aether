package com.gildedgames.aether.item;
import com.gildedgames.aether.mixin.access.EntityRenderAccessor;
import com.matthewperiut.accessoryapi.api.Accessory;
import net.minecraft.client.render.entity.PlayerRenderer;
import net.minecraft.client.render.entity.model.Biped;
import net.minecraft.entity.player.PlayerBase;
import net.minecraft.item.ItemInstance;
import net.minecraft.util.maths.MathHelper;
import org.jetbrains.annotations.NotNull;

import net.modificationstation.stationapi.api.registry.Identifier;
import net.modificationstation.stationapi.api.template.item.TemplateItemBase;
import org.lwjgl.opengl.GL11;

public class ItemMoreArmor extends TemplateItemBase implements Accessory
{
    private static final int[] damageReduceAmountArray;
    private static final int[] maxDamageArray;
    public final int armorLevel;
    public final Type armorType;
    //public final int damageReduceAmount;
    public final int renderIndex;
    private final int colour;
    public String texture;
    public boolean colouriseRender;
    
    public ItemMoreArmor(final @NotNull Identifier identifier, final int j, final int k, final Type l, final int col) {
        super(identifier);
        this.armorLevel = j;
        this.armorType = l;
        this.renderIndex = k;
        //this.damageReduceAmount = ItemMoreArmor.damageReduceAmountArray[l];
        //this.setDurability(ItemMoreArmor.maxDamageArray[l] * 3 << j);
        this.setDurability(500); //todo: make accurate
        this.maxStackSize = 1;
        this.colour = col;
        this.colouriseRender = true;
    }
    
    public ItemMoreArmor(final @NotNull Identifier identifier, final int j, final int k, final Type l) {
        this(identifier, j, k, l, 16777215);
    }
    
    public ItemMoreArmor(final @NotNull Identifier i, final int j, final String path, final Type l) {
        this(i, j, 0, l);
        this.texture = path;
    }
    
    public ItemMoreArmor(final @NotNull Identifier i, final int j, final String path, final Type l, final int m) {
        this(i, j, 0, l, m);
        this.texture = path;
    }
    
    public ItemMoreArmor(final @NotNull Identifier i, final int j, final String path, final Type l, final int m, final boolean flag) {
        this(i, j, path, l, m);
        this.colouriseRender = flag;
    }
    
    @Override
    public int getColourMultiplier(final int i) {
        return this.colour;
    }
    
    static {
        damageReduceAmountArray = new int[] { 3, 8, 6, 3, 0, 1, 0, 0, 0, 0, 2, 0 };
        maxDamageArray = new int[] { 11, 16, 15, 13, 10, 10, 8, 10, 10, 10, 10, 10 };
    }

    @Override
    public Type getType()
    {
        return armorType;
    }

    @Override
    public void tickWhileWorn(PlayerBase playerBase, ItemInstance itemInstance)
    {

    }

    @Override
    public void renderWhileWorn(PlayerBase player, PlayerRenderer renderer, ItemInstance itemInstance, Biped biped, Object[] objects)
    {

    }

    @Override
    public void onAccessoryAdded(PlayerBase playerBase, ItemInstance itemInstance)
    {

    }

    @Override
    public void onAccessoryRemoved(PlayerBase playerBase, ItemInstance itemInstance)
    {

    }
}
