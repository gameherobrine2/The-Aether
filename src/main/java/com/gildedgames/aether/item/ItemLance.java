package com.gildedgames.aether.item;

import net.minecraft.entity.EntityBase;
import net.minecraft.entity.Living;

import net.minecraft.entity.player.PlayerBase;
import net.minecraft.util.hit.HitType;
import net.modificationstation.stationapi.api.item.CustomReachProvider;
import org.jetbrains.annotations.NotNull;

import com.gildedgames.aether.registry.AetherItems;

import net.minecraft.block.BlockBase;
import net.minecraft.item.ItemInstance;
import net.minecraft.item.tool.ToolMaterial;
import net.modificationstation.stationapi.api.registry.Identifier;
import net.modificationstation.stationapi.api.template.item.TemplateItemBase;
import net.minecraft.item.ItemBase;

public class ItemLance extends TemplateItemBase implements CustomReachProvider {
    private int weaponDamage;
    
    public ItemLance(final @NotNull Identifier identifier, final ToolMaterial enumtoolmaterial) {
        super(identifier);
        this.maxStackSize = 1;
        this.setDurability(enumtoolmaterial.getDurability());
        this.weaponDamage = 4 + enumtoolmaterial.getAttackDamage() * 2;
    }
    
    @Override
    public float getStrengthOnBlock(final ItemInstance item, final BlockBase tile) {
        return (tile.id != BlockBase.COBWEB.id) ? 1.5f : 15.0f;
    }
    
    @Override
    public boolean postHit(final ItemInstance itemstack, final Living damageSource, final Living damageTarget) {
        itemstack.applyDamage(1, damageTarget);
        return true;
    }
    
    @Override
    public boolean postMine(final ItemInstance itemstack, final int i, final int j, final int k, final int l, final Living damageTarget) {
        itemstack.applyDamage(2, damageTarget);
        return true;
    }
    
    @Override
    public int getAttack(final EntityBase entity) {
        return this.weaponDamage;
    }
    
    @Override
    public boolean isRendered3d() {
        return true;
    }
    
    @Override
    public boolean isEffectiveOn(final BlockBase tile) {
        return tile.id == BlockBase.COBWEB.id;
    }
    
    public boolean reachItemMatches(final ItemInstance itemstack) {
        return itemstack != null && itemstack.itemId == AetherItems.Lance.id;
    }

    @Override
    public double getReach(ItemInstance itemInstance, PlayerBase player, HitType type, double currentReach) {
        return 10.f;
    }
}
