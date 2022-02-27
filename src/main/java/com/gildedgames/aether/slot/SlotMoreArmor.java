package com.gildedgames.aether.slot;

import net.minecraft.item.ItemInstance;
import net.minecraft.inventory.InventoryBase;

import com.gildedgames.aether.gui.container.ContainerAether;
import com.gildedgames.aether.item.ItemMoreArmor;

import net.minecraft.container.slot.Slot;

public class SlotMoreArmor extends Slot {
    final int armorType;
    final ContainerAether inventory;
    
    public SlotMoreArmor(final ContainerAether containerplayer, final InventoryBase iinventory, final int i, final int j, final int k, final int l) {
        super(iinventory, i, j, k);
        this.inventory = containerplayer;
        this.armorType = l;
    }
    
    @Override
    public int getMaxStackCount() {
        return 1;
    }
    
    @Override
    public boolean canInsert(final ItemInstance stack) {
        return stack.getType() instanceof ItemMoreArmor && ((ItemMoreArmor)stack.getType()).isTypeValid(this.armorType);
    }
}
