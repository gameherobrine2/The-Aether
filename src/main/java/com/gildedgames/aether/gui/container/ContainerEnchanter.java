package com.gildedgames.aether.gui.container;
import net.minecraft.entity.player.PlayerBase;
import net.minecraft.container.ContainerListener;
import net.minecraft.item.ItemInstance;
import net.minecraft.container.slot.FurnaceOutput;
import net.minecraft.container.slot.Slot;
import net.minecraft.entity.player.PlayerInventory;

import com.gildedgames.aether.entity.tile.TileEntityEnchanter;

import net.minecraft.container.ContainerBase;

public class ContainerEnchanter extends ContainerBase {
    private TileEntityEnchanter enchanter;
    private int cookTime;
    private int burnTime;
    private int itemBurnTime;
    
    public ContainerEnchanter(final PlayerInventory inventoryplayer, final TileEntityEnchanter tileentityenchanter) {
        this.cookTime = 0;
        this.burnTime = 0;
        this.itemBurnTime = 0;
        this.enchanter = tileentityenchanter;
        this.addSlot(new Slot(tileentityenchanter, 0, 56, 17));
        this.addSlot(new Slot(tileentityenchanter, 1, 56, 53));
        this.addSlot(new FurnaceOutput(inventoryplayer.player, tileentityenchanter, 2, 116, 35));
        for (int i = 0; i < 3; ++i) {
            for (int k = 0; k < 9; ++k) {
                this.addSlot(new Slot(inventoryplayer, k + i * 9 + 9, 8 + k * 18, 84 + i * 18));
            }
        }
        for (int j = 0; j < 9; ++j) {
            this.addSlot(new Slot(inventoryplayer, j, 8 + j * 18, 142));
        }
    }
    
    @Override
    protected void insertItem(final ItemInstance itemstack, final int i, final int j, final boolean flag) {
    }
    
    @Override
    public void tick() {
        super.tick();
        for (int i = 0; i < this.listeners.size(); ++i) {
            final ContainerListener icrafting = (ContainerListener)this.listeners.get(i);
            if (this.cookTime != this.enchanter.enchantTimeForItem) {
                icrafting.updateProperty(this, 0, this.enchanter.enchantTimeForItem);
            }
            if (this.burnTime != this.enchanter.enchantProgress) {
                icrafting.updateProperty(this, 1, this.enchanter.enchantProgress);
            }
            if (this.itemBurnTime != this.enchanter.enchantPowerRemaining) {
                icrafting.updateProperty(this, 2, this.enchanter.enchantPowerRemaining);
            }
        }
        this.cookTime = this.enchanter.enchantTimeForItem;
        this.burnTime = this.enchanter.enchantProgress;
        this.itemBurnTime = this.enchanter.enchantPowerRemaining;
    }
    
    @Override
    public void setProperty(final int index, final int value) {
        if (index == 0) {
            this.enchanter.enchantTimeForItem = value;
        }
        if (index == 1) {
            this.enchanter.enchantProgress = value;
        }
        if (index == 2) {
            this.enchanter.enchantPowerRemaining = value;
        }
    }
    
    @Override
    public boolean canUse(final PlayerBase player) {
        return this.enchanter.canPlayerUse(player);
    }
    
    @Override
    public ItemInstance transferSlot(final int slotIndex) {
        ItemInstance itemstack = null;
        final Slot slot = (Slot)this.slots.get(slotIndex);
        if (slot != null && slot.hasItem()) {
            final ItemInstance itemstack2 = slot.getItem();
            itemstack = itemstack2.copy();
            if (slotIndex == 2) {
                this.insertItem(itemstack2, 3, 39, true);
            }
            else if (slotIndex >= 3 && slotIndex < 30) {
                this.insertItem(itemstack2, 30, 39, false);
            }
            else if (slotIndex >= 30 && slotIndex < 39) {
                this.insertItem(itemstack2, 3, 30, false);
            }
            else {
                this.insertItem(itemstack2, 3, 39, false);
            }
            if (itemstack2.count == 0) {
                slot.setStack(null);
            }
            else {
                slot.markDirty();
            }
            if (itemstack2.count == itemstack.count) {
                return null;
            }
            slot.onCrafted(itemstack2);
        }
        return itemstack;
    }
}
