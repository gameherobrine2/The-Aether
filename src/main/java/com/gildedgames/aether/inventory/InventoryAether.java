package com.gildedgames.aether.inventory;

import com.gildedgames.aether.item.ItemMoreArmor;

import net.minecraft.entity.EntityBase;
import net.minecraft.util.io.AbstractTag;
import net.minecraft.util.io.CompoundTag;
import net.minecraft.util.io.ListTag;
import net.minecraft.entity.player.PlayerBase;
import net.minecraft.item.ItemInstance;
import net.minecraft.inventory.InventoryBase;

public class InventoryAether implements InventoryBase {
    public ItemInstance[] slots;
    public PlayerBase player;
    
    public InventoryAether(final PlayerBase entityplayer) {
        this.player = entityplayer;
        this.slots = new ItemInstance[8];
    }
    
    public void readFromNBT(final ListTag nbttaglist) {
        this.slots = new ItemInstance[8];
        for (int i = 0; i < nbttaglist.size(); ++i) {
            final CompoundTag nbttagcompound = (CompoundTag)nbttaglist.get(i);
            final int j = nbttagcompound.getByte("Slot") & 0xFF;
            final ItemInstance itemstack = new ItemInstance(nbttagcompound);
            if (j > 8 || !(itemstack.getType() instanceof ItemMoreArmor)) {
                this.readOldFile(nbttaglist);
                return;
            }
            if (itemstack.getType() != null) {
                if (j < this.slots.length) {
                    this.slots[j] = itemstack;
                }
            }
        }
    }
    
    public void readOldFile(final ListTag nbttaglist) {
        for (int i = 0; i < nbttaglist.size(); ++i) {
            final CompoundTag nbttagcompound = (CompoundTag)nbttaglist.get(i);
            final int j = nbttagcompound.getByte("Slot") & 0xFF;
            final ItemInstance itemstack = new ItemInstance(nbttagcompound);
            if (itemstack.getType() != null) {
                if (j >= 104 && j < 112) {
                    this.slots[j - 104] = itemstack;
                }
            }
        }
    }
    
    public ListTag writeToNBT(final ListTag nbttaglist) {
        for (int j = 0; j < this.slots.length; ++j) {
            if (this.slots[j] != null) {
                final CompoundTag nbttagcompound1 = new CompoundTag();
                nbttagcompound1.put("Slot", (byte)j);
                this.slots[j].toTag(nbttagcompound1);
                nbttaglist.add(nbttagcompound1);
            }
        }
        return nbttaglist;
    }
    
    public ItemInstance getInventoryItem(final int index) {
        return this.slots[index];
    }
    
    public ItemInstance takeInventoryItem(final int index, final int count) {
        if (this.slots[index] == null) {
            return null;
        }
        if (this.slots[index].count <= count) {
            final ItemInstance itemstack = this.slots[index];
            this.slots[index] = null;
            this.markDirty();
            return itemstack;
        }
        final ItemInstance itemstack2 = this.slots[index].split(count);
        if (this.slots[index].count == 0) {
            this.slots[index] = null;
        }
        this.markDirty();
        return itemstack2;
    }
    
    public void setInventoryItem(final int slot, final ItemInstance stack) {
        this.slots[slot] = stack;
        if (stack != null && stack.count > this.getMaxItemCount()) {
            stack.count = this.getMaxItemCount();
        }
        this.markDirty();
    }
    
    public int getInventorySize() {
        return 8;
    }
    
    public String getContainerName() {
        return "Aether Slots";
    }
    
    public int getMaxItemCount() {
        return 1;
    }
    
    public void markDirty() {
    }
    
    public boolean canPlayerUse(final PlayerBase player) {
        return true;
    }
    
    public int getTotalArmorValue() {
        int i = 0;
        int j = 0;
        int k = 0;
        for (int l = 0; l < this.slots.length; ++l) {
            if (this.slots[l] != null && this.slots[l].getType() instanceof ItemMoreArmor) {
                final int i2 = this.slots[l].getDurability();
                final int j2 = this.slots[l].getDamage2();
                final int k2 = i2 - j2;
                j += k2;
                k += i2;
                final int l2 = ((ItemMoreArmor)this.slots[l].getType()).damageReduceAmount;
                i += l2;
            }
        }
        if (k == 0) {
            return 0;
        }
        return (i - 1) * j / k + 1;
    }
    
    public void damageArmor(final int i) {
        for (int j = 0; j < this.slots.length; ++j) {
            if (this.slots[j] != null) {
                if (this.slots[j].getType() instanceof ItemMoreArmor) {
                    this.slots[j].applyDamage(i, this.player);
                    if (this.slots[j].count == 0) {
                        this.slots[j].unusedEmptyMethod1(this.player);
                        this.slots[j] = null;
                    }
                }
            }
        }
    }
    
    public void dropAllItems() {
        for (int j = 0; j < this.slots.length; ++j) {
            if (this.slots[j] != null) {
                this.player.dropItem(this.slots[j], true);
                this.slots[j] = null;
            }
        }
    }
}
