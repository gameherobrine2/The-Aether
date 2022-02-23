/*RECREATED*/
package com.gildedgames.aether.slot;

import net.minecraft.block.BlockBase;
import net.minecraft.container.slot.Slot;
import net.minecraft.entity.player.PlayerContainer;
import net.minecraft.inventory.InventoryBase;
import net.minecraft.item.ItemInstance;
import net.minecraft.item.armour.Armour;
public class SlotArmor extends Slot
{

	public SlotArmor(PlayerContainer containerplayer, InventoryBase iinventory, int i, int j, int k, int l)
    {
        super(iinventory, i, j, k);
        inventory = containerplayer;
        armorType = l;
    }

    public int getSlotStackLimit()
    {
        return 1;
    }

    @Override // net.minecraft.container.slot.Slot
    public boolean canInsert(ItemInstance itemstack)
    {
        if(itemstack.getType() instanceof Armour)
        {
            return ((Armour)itemstack.getType()).armourSlot == armorType;
        }
        if(itemstack.getType().id == BlockBase.PUMPKIN.id)
        {
            return armorType == 0;
        } else
        {
            return false;
        }
    }

    final int armorType; /* synthetic field */
    final PlayerContainer inventory; /* synthetic field */
}
