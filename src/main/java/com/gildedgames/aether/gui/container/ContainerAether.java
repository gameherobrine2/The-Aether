package com.gildedgames.aether.gui.container;
import net.minecraft.container.slot.Slot;
import net.minecraft.container.slot.CraftingResult;
import net.minecraft.inventory.Chest;

import com.gildedgames.aether.inventory.InventoryAether;
import com.gildedgames.aether.slot.SlotMoreArmor;
import com.gildedgames.aether.slot.SlotArmor;
import net.minecraft.inventory.Crafting;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.PlayerContainer;

public class ContainerAether extends PlayerContainer {
    public ContainerAether(final PlayerInventory inventoryplayer, final InventoryAether inv) {
        this(inventoryplayer, inv, true);
    }
    Slot slot = null;
    public ContainerAether(final PlayerInventory inventoryplayer, final InventoryAether inv, final boolean flag) {
        super(inventoryplayer, flag);
        this.slots.clear();
        this.craftingInv = new Crafting(this, 2, 2);
        this.resultInv = new Chest();
        this.local = flag;
        this.addSlot(new CraftingResult(inventoryplayer.player, this.craftingInv, this.resultInv, 0, 134, 62));
        for (int i = 0; i < 2; ++i) {
            for (int i2 = 0; i2 < 2; ++i2) {
            	slot = new Slot(this.craftingInv, i2 + i * 2, 125 + i2 * 18, 8 + i * 18);
                this.addSlot(slot);
            }
        }
        for (int j = 0; j < 4; ++j) {
            final int j2 = j;
            this.addSlot(new SlotArmor(this, inventoryplayer, inventoryplayer.getInventorySize() - 1 - j, 62, 8 + j * 18, j2));
        }
        for (int k = 0; k < 3; ++k) {
            for (int k2 = 0; k2 < 9; ++k2) {
                this.addSlot(new Slot(inventoryplayer, k2 + (k + 1) * 9, 8 + k2 * 18, 84 + k * 18));
            }
        }
        for (int l = 0; l < 9; ++l) {
            this.addSlot(new Slot(inventoryplayer, l, 8 + l * 18, 142));
        }
        for (int i = 1; i < 3; ++i) {
            for (int m = 0; m < 4; ++m) {
                final int slotId;
                final int armorType = slotId = 4 * (i - 1) + m;
                this.addSlot(new SlotMoreArmor(this, inv, slotId, 62 + i * 18, 8 + m * 18, armorType + 4));
            }
        }
        this.onContentsChanged(this.craftingInv);
    }
}
