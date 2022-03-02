package com.gildedgames.aether.slot;
import net.minecraft.inventory.InventoryBase;
import net.minecraft.container.slot.Slot;

public class TileEntityIncubatorSlot extends Slot {
    public TileEntityIncubatorSlot(final InventoryBase inventory, final int index, final int x, final int y) {
        super(inventory, index, x, y);
    }
    
    @Override
    public int getMaxStackCount() {
        return 1;
    }
}
