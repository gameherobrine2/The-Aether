package com.gildedgames.aether.entity.tile;
import net.minecraft.entity.player.PlayerBase;

import com.gildedgames.aether.entity.animal.EntityMoa;
import com.gildedgames.aether.registry.AetherBlocks;
import com.gildedgames.aether.registry.AetherItems;
import com.gildedgames.aether.utils.MoaColour;

import net.minecraft.entity.EntityBase;
import net.minecraft.util.io.AbstractTag;
import net.minecraft.util.io.ListTag;
import net.minecraft.util.io.CompoundTag;
import net.minecraft.item.ItemInstance;
import net.minecraft.inventory.InventoryBase;
import net.minecraft.tileentity.TileEntityBase;

public class TileEntityIncubator extends TileEntityBase implements InventoryBase {
    private ItemInstance[] IncubatorItemStacks;
    public int torchPower;
    public int progress;
    
    public TileEntityIncubator() {
        this.IncubatorItemStacks = new ItemInstance[2];
        this.progress = 0;
    }
    
    @Override
    public int getInventorySize() {
        return this.IncubatorItemStacks.length;
    }
    
    @Override
    public ItemInstance getInventoryItem(final int index) {
        return this.IncubatorItemStacks[index];
    }
    
    @Override
    public ItemInstance takeInventoryItem(final int index, final int count) {
        if (this.IncubatorItemStacks[index] == null) {
            return null;
        }
        if (this.IncubatorItemStacks[index].count <= count) {
            final ItemInstance itemstack = this.IncubatorItemStacks[index];
            this.IncubatorItemStacks[index] = null;
            return itemstack;
        }
        final ItemInstance itemstack2 = this.IncubatorItemStacks[index].split(count);
        if (this.IncubatorItemStacks[index].count == 0) {
            this.IncubatorItemStacks[index] = null;
        }
        return itemstack2;
    }
    
    @Override
    public void setInventoryItem(final int slot, final ItemInstance stack) {
        this.IncubatorItemStacks[slot] = stack;
        if (stack != null && stack.count > this.getMaxItemCount()) {
            stack.count = this.getMaxItemCount();
        }
    }
    
    @Override
    public String getContainerName() {
        return "Incubator";
    }
    
    @Override
    public void readIdentifyingData(final CompoundTag tag) {
        super.readIdentifyingData(tag);
        final ListTag nbttaglist = tag.getListTag("Items");
        this.IncubatorItemStacks = new ItemInstance[this.getInventorySize()];
        for (int i = 0; i < nbttaglist.size(); ++i) {
            final CompoundTag nbttagcompound1 = (CompoundTag)nbttaglist.get(i);
            final byte byte0 = nbttagcompound1.getByte("Slot");
            if (byte0 >= 0 && byte0 < this.IncubatorItemStacks.length) {
                this.IncubatorItemStacks[byte0] = new ItemInstance(nbttagcompound1);
            }
        }
        this.progress = tag.getShort("BurnTime");
    }
    
    @Override
    public void writeIdentifyingData(final CompoundTag tag) {
        super.writeIdentifyingData(tag);
        tag.put("BurnTime", (short)this.progress);
        final ListTag nbttaglist = new ListTag();
        for (int i = 0; i < this.IncubatorItemStacks.length; ++i) {
            if (this.IncubatorItemStacks[i] != null) {
                final CompoundTag nbttagcompound1 = new CompoundTag();
                nbttagcompound1.put("Slot", (byte)i);
                this.IncubatorItemStacks[i].toTag(nbttagcompound1);
                nbttaglist.add(nbttagcompound1);
            }
        }
        tag.put("Items", (AbstractTag)nbttaglist);
    }
    
    @Override
    public int getMaxItemCount() {
        return 64;
    }
    
    public int getCookProgressScaled(final int i) {
        return this.progress * i / 6000;
    }
    
    public int getBurnTimeRemainingScaled(final int i) {
        return this.torchPower * i / 500;
    }
    
    public boolean isBurning() {
        return this.torchPower > 0;
    }
    
    @Override
    public void tick() {
        if (this.torchPower > 0) {
            --this.torchPower;
            if (this.getInventoryItem(1) != null) {
                ++this.progress;
            }
        }
        if (this.IncubatorItemStacks[1] == null || this.IncubatorItemStacks[1].itemId != AetherItems.MoaEgg.id) {
            this.progress = 0;
        }
        if (this.progress >= 6000) {
            if (this.IncubatorItemStacks[1] != null) {
                final EntityMoa moa = new EntityMoa(this.level, true, false, false, MoaColour.getColour(this.IncubatorItemStacks[1].getDamage()));
                moa.setPosition(this.x + 0.5, this.y + 1.5, this.z + 0.5);
                this.level.spawnEntity(moa);
            }
            //TODO: mod_Aether.giveAchievement(AetherAchievements.incubator);
            this.takeInventoryItem(1, 1);
            this.progress = 0;
        }
        if (this.torchPower <= 0 && this.IncubatorItemStacks[1] != null && this.IncubatorItemStacks[1].itemId == AetherItems.MoaEgg.id && this.getInventoryItem(0) != null && this.getInventoryItem(0).itemId == AetherBlocks.AMBROSIUM_TORCH.id) {
            this.torchPower += 1000;
            this.takeInventoryItem(0, 1);
        }
    }
    
    @Override
    public boolean canPlayerUse(final PlayerBase player) {
        return this.level.getTileEntity(this.x, this.y, this.z) == this && player.squaredDistanceTo(this.x + 0.5, this.y + 0.5, this.z + 0.5) <= 64.0;
    }
}
