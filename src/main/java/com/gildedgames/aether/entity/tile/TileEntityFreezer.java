package com.gildedgames.aether.entity.tile;

import com.gildedgames.aether.registry.AetherBlocks;
import com.gildedgames.aether.registry.AetherItems;
import com.gildedgames.aether.utils.Frozen;
import net.minecraft.block.BlockBase;
import net.minecraft.entity.player.PlayerBase;
import net.minecraft.inventory.InventoryBase;
import net.minecraft.item.ItemBase;
import net.minecraft.item.ItemInstance;
import net.minecraft.tileentity.TileEntityBase;
import net.minecraft.util.io.AbstractTag;
import net.minecraft.util.io.CompoundTag;
import net.minecraft.util.io.ListTag;

import java.util.ArrayList;
import java.util.List;

public class TileEntityFreezer extends TileEntityBase implements InventoryBase
{
    private static List<Frozen> frozen;
    private ItemInstance[] frozenItemStacks;
    public int frozenProgress;
    public int frozenPowerRemaining;
    public int frozenTimeForItem;
    private Frozen currentFrozen;

    public TileEntityFreezer()
    {
        this.frozenItemStacks = new ItemInstance[3];
        this.frozenProgress = 0;
        this.frozenPowerRemaining = 0;
        this.frozenTimeForItem = 0;
    }

    @Override
    public int getInventorySize()
    {
        return this.frozenItemStacks.length;
    }

    @Override
    public ItemInstance getInventoryItem(final int index)
    {
        return this.frozenItemStacks[index];
    }

    @Override
    public ItemInstance takeInventoryItem(final int index, final int count)
    {
        if (this.frozenItemStacks[index] == null)
        {
            return null;
        }
        if (this.frozenItemStacks[index].count <= count)
        {
            final ItemInstance itemstack = this.frozenItemStacks[index];
            this.frozenItemStacks[index] = null;
            return itemstack;
        }
        final ItemInstance itemstack2 = this.frozenItemStacks[index].split(count);
        if (this.frozenItemStacks[index].count == 0)
        {
            this.frozenItemStacks[index] = null;
        }
        return itemstack2;
    }

    @Override
    public void setInventoryItem(final int slot, final ItemInstance stack)
    {
        this.frozenItemStacks[slot] = stack;
        if (stack != null && stack.count > this.getMaxItemCount())
        {
            stack.count = this.getMaxItemCount();
        }
    }

    @Override
    public String getContainerName()
    {
        return "Freezer";
    }

    @Override
    public void readIdentifyingData(final CompoundTag tag)
    {
        super.readIdentifyingData(tag);
        final ListTag nbttaglist = tag.getListTag("Items");
        this.frozenItemStacks = new ItemInstance[this.getInventorySize()];
        for (int i = 0; i < nbttaglist.size(); ++i)
        {
            final CompoundTag nbttagcompound1 = (CompoundTag) nbttaglist.get(i);
            final byte byte0 = nbttagcompound1.getByte("Slot");
            if (byte0 >= 0 && byte0 < this.frozenItemStacks.length)
            {
                this.frozenItemStacks[byte0] = new ItemInstance(nbttagcompound1);
            }
        }
        this.frozenProgress = tag.getShort("BurnTime");
        this.frozenTimeForItem = tag.getShort("CookTime");
    }

    @Override
    public void writeIdentifyingData(final CompoundTag tag)
    {
        super.writeIdentifyingData(tag);
        tag.put("BurnTime", (short) this.frozenProgress);
        tag.put("CookTime", (short) this.frozenTimeForItem);
        final ListTag nbttaglist = new ListTag();
        for (int i = 0; i < this.frozenItemStacks.length; ++i)
        {
            if (this.frozenItemStacks[i] != null)
            {
                final CompoundTag nbttagcompound1 = new CompoundTag();
                nbttagcompound1.put("Slot", (byte) i);
                this.frozenItemStacks[i].toTag(nbttagcompound1);
                nbttaglist.add(nbttagcompound1);
            }
        }
        tag.put("Items", (AbstractTag) nbttaglist);
    }

    @Override
    public int getMaxItemCount()
    {
        return 64;
    }

    public int getCookProgressScaled(final int i)
    {
        if (this.frozenTimeForItem == 0)
        {
            return 0;
        }
        return this.frozenProgress * i / this.frozenTimeForItem;
    }

    public int getBurnTimeRemainingScaled(final int i)
    {
        return this.frozenPowerRemaining * i / 500;
    }

    public boolean isBurning()
    {
        return this.frozenPowerRemaining > 0;
    }

    @Override
    public void tick()
    {
        if (this.frozenPowerRemaining > 0)
        {
            --this.frozenPowerRemaining;
            if (this.currentFrozen != null)
            {
                ++this.frozenProgress;
            }
        }
        if (this.currentFrozen != null && (this.frozenItemStacks[0] == null || this.frozenItemStacks[0].itemId != this.currentFrozen.frozenFrom.itemId))
        {
            this.currentFrozen = null;
            this.frozenProgress = 0;
        }
        if (this.currentFrozen != null && this.frozenProgress >= this.currentFrozen.frozenPowerNeeded)
        {
            if (this.frozenItemStacks[2] == null)
            {
                this.setInventoryItem(2, new ItemInstance(this.currentFrozen.frozenTo.getType(), 1, this.currentFrozen.frozenTo.getDamage()));
            }
            else
            {
                this.setInventoryItem(2, new ItemInstance(this.currentFrozen.frozenTo.getType(), this.getInventoryItem(2).count + 1, this.currentFrozen.frozenTo.getDamage()));
            }
            if (this.getInventoryItem(0).itemId == ItemBase.waterBucket.id || this.getInventoryItem(0).itemId == ItemBase.lavaBucket.id)
            {
                this.setInventoryItem(0, new ItemInstance(ItemBase.bucket));
            }
            else if (this.getInventoryItem(0).itemId == AetherItems.Bucket.id)
            {
                this.setInventoryItem(0, new ItemInstance(AetherItems.Bucket));
            }
            else
            {
                this.takeInventoryItem(0, 1);
            }
            this.frozenProgress = 0;
            this.currentFrozen = null;
            this.frozenTimeForItem = 0;
        }
        if (this.frozenPowerRemaining <= 0 && this.currentFrozen != null && this.getInventoryItem(1) != null && this.getInventoryItem(1).itemId == AetherBlocks.ICESTONE.id)
        {
            this.frozenPowerRemaining += 500;
            this.takeInventoryItem(1, 1);
        }
        if (this.currentFrozen == null)
        {
            final ItemInstance itemstack = this.getInventoryItem(0);
            for (int i = 0; i < TileEntityFreezer.frozen.size(); ++i)
            {
                if (itemstack != null && TileEntityFreezer.frozen.get(i) != null && itemstack.itemId == ((Frozen) TileEntityFreezer.frozen.get(i)).frozenFrom.itemId && itemstack.getDamage() == ((Frozen) TileEntityFreezer.frozen.get(i)).frozenFrom.getDamage())
                {
                    if (this.frozenItemStacks[2] == null)
                    {
                        this.currentFrozen = (Frozen) TileEntityFreezer.frozen.get(i);
                        this.frozenTimeForItem = this.currentFrozen.frozenPowerNeeded;
                    }
                    else if (this.frozenItemStacks[2].itemId == ((Frozen) TileEntityFreezer.frozen.get(i)).frozenTo.itemId && ((Frozen) TileEntityFreezer.frozen.get(i)).frozenTo.getType().getMaxStackSize() > this.frozenItemStacks[2].count)
                    {
                        this.currentFrozen = (Frozen) TileEntityFreezer.frozen.get(i);
                        this.frozenTimeForItem = this.currentFrozen.frozenPowerNeeded;
                    }
                }
            }
        }
    }

    @Override
    public boolean canPlayerUse(final PlayerBase player)
    {
        return this.level.getTileEntity(this.x, this.y, this.z) == this && player.squaredDistanceTo(this.x + 0.5, this.y + 0.5, this.z + 0.5) <= 64.0;
    }

    public static void addFrozen(final ItemInstance from, final ItemInstance to, final int i)
    {
        TileEntityFreezer.frozen.add(new Frozen(from, to, i));
    }

    static
    {
        TileEntityFreezer.frozen = (List<Frozen>) new ArrayList();
        addFrozen(new ItemInstance(ItemBase.waterBucket, 1), new ItemInstance(BlockBase.ICE, 5), 500);
        addFrozen(new ItemInstance(AetherItems.Bucket, 1, 8), new ItemInstance(BlockBase.ICE, 5), 500);
        addFrozen(new ItemInstance(ItemBase.lavaBucket, 1), new ItemInstance(BlockBase.OBSIDIAN, 2), 500);
        addFrozen(new ItemInstance(AetherBlocks.AERCLOUD, 1, 0), new ItemInstance(AetherBlocks.AERCLOUD, 1, 1), 50);
        addFrozen(new ItemInstance(AetherItems.GoldPendant, 1), new ItemInstance(AetherItems.IcePendant, 1), 2500);
        addFrozen(new ItemInstance(AetherItems.GoldRing, 1), new ItemInstance(AetherItems.IceRing, 1), 1500);
        addFrozen(new ItemInstance(AetherItems.IronRing, 1), new ItemInstance(AetherItems.IceRing, 1), 1500);
        addFrozen(new ItemInstance(AetherItems.IronPendant, 1), new ItemInstance(AetherItems.IcePendant, 1), 2500);
    }
}
