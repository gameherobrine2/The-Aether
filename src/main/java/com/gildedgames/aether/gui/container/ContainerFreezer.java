package com.gildedgames.aether.gui.container;

import com.gildedgames.aether.entity.tile.TileEntityFreezer;
import net.minecraft.container.ContainerBase;
import net.minecraft.container.ContainerListener;
import net.minecraft.container.slot.FurnaceOutput;
import net.minecraft.container.slot.Slot;
import net.minecraft.entity.player.PlayerBase;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemInstance;

public class ContainerFreezer extends ContainerBase
{
    private TileEntityFreezer freezer;
    private int cookTime;
    private int burnTime;
    private int itemBurnTime;

    public ContainerFreezer(final PlayerInventory inventoryplayer, final TileEntityFreezer tileentityfreezer)
    {
        this.cookTime = 0;
        this.burnTime = 0;
        this.itemBurnTime = 0;
        this.freezer = tileentityfreezer;
        this.addSlot(new Slot(tileentityfreezer, 0, 56, 17));
        this.addSlot(new Slot(tileentityfreezer, 1, 56, 53));
        this.addSlot(new FurnaceOutput(inventoryplayer.player, tileentityfreezer, 2, 116, 35));
        for (int i = 0; i < 3; ++i)
        {
            for (int k = 0; k < 9; ++k)
            {
                this.addSlot(new Slot(inventoryplayer, k + i * 9 + 9, 8 + k * 18, 84 + i * 18));
            }
        }
        for (int j = 0; j < 9; ++j)
        {
            this.addSlot(new Slot(inventoryplayer, j, 8 + j * 18, 142));
        }
    }

    @Override
    protected void insertItem(final ItemInstance itemstack, final int i, final int j, final boolean flag)
    {
    }

    @Override
    public void tick()
    {
        super.tick();
        for (int i = 0; i < this.listeners.size(); ++i)
        {
            final ContainerListener icrafting = (ContainerListener) this.listeners.get(i);
            if (this.cookTime != this.freezer.frozenTimeForItem)
            {
                icrafting.updateProperty(this, 0, this.freezer.frozenTimeForItem);
            }
            if (this.burnTime != this.freezer.frozenProgress)
            {
                icrafting.updateProperty(this, 1, this.freezer.frozenProgress);
            }
            if (this.itemBurnTime != this.freezer.frozenPowerRemaining)
            {
                icrafting.updateProperty(this, 2, this.freezer.frozenPowerRemaining);
            }
        }
        this.cookTime = this.freezer.frozenTimeForItem;
        this.burnTime = this.freezer.frozenProgress;
        this.itemBurnTime = this.freezer.frozenPowerRemaining;
    }

    @Override
    public void setProperty(final int index, final int value)
    {
        if (index == 0)
        {
            this.freezer.frozenTimeForItem = value;
        }
        if (index == 1)
        {
            this.freezer.frozenProgress = value;
        }
        if (index == 2)
        {
            this.freezer.frozenPowerRemaining = value;
        }
    }

    @Override
    public boolean canUse(final PlayerBase player)
    {
        return this.freezer.canPlayerUse(player);
    }

    @Override
    public ItemInstance transferSlot(final int slotIndex)
    {
        ItemInstance itemstack = null;
        final Slot slot = (Slot) this.slots.get(slotIndex);
        if (slot != null && slot.hasItem())
        {
            final ItemInstance itemstack2 = slot.getItem();
            itemstack = itemstack2.copy();
            if (slotIndex == 2)
            {
                this.insertItem(itemstack2, 3, 39, true);
            }
            else if (slotIndex >= 3 && slotIndex < 30)
            {
                this.insertItem(itemstack2, 30, 39, false);
            }
            else if (slotIndex >= 30 && slotIndex < 39)
            {
                this.insertItem(itemstack2, 3, 30, false);
            }
            else
            {
                this.insertItem(itemstack2, 3, 39, false);
            }
            if (itemstack2.count == 0)
            {
                slot.setStack(null);
            }
            else
            {
                slot.markDirty();
            }
            if (itemstack2.count == itemstack.count)
            {
                return null;
            }
            slot.onCrafted(itemstack2);
        }
        return itemstack;
    }
}
