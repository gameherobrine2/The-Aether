package com.gildedgames.aether.item.accessory;

import com.gildedgames.aether.mixin.access.EntityBaseAccessor;
import com.gildedgames.aether.registry.AetherItems;
import net.minecraft.entity.player.PlayerBase;
import net.minecraft.item.ItemInstance;
import net.modificationstation.stationapi.api.registry.Identifier;

public class PhoenixGlove extends Glove
{
    public PhoenixGlove(Identifier identifier, int strength, String texture, int color)
    {
        super(identifier, strength, texture, color);
    }

    @Override
    public void tickWhileWorn(PlayerBase player, ItemInstance itemInstance)
    {
        if (player.inventory.armour[3] != null && player.inventory.armour[3].itemId == AetherItems.PhoenixHelm.id && player.inventory.armour[2] != null && player.inventory.armour[2].itemId == AetherItems.PhoenixBody.id && player.inventory.armour[1] != null && player.inventory.armour[1].itemId == AetherItems.PhoenixLegs.id && player.inventory.armour[0] != null && player.inventory.armour[0].itemId == AetherItems.PhoenixBoots.id)
        {
            ((EntityBaseAccessor) player).setImmunityToFire(true);
            player.fire = 0;
            player.level.addParticle("flame", player.x + ((EntityBaseAccessor) player).getRand().nextGaussian() / 5.0, player.y - 0.5 + ((EntityBaseAccessor) player).getRand().nextGaussian() / 5.0, player.z + ((EntityBaseAccessor) player).getRand().nextGaussian() / 3.0, 0.0, 0.0, 0.0);
        }
    }

    @Override
    public void onAccessoryRemoved(PlayerBase player, ItemInstance itemInstance)
    {
        ((EntityBaseAccessor) player).setImmunityToFire(false);
    }
}
