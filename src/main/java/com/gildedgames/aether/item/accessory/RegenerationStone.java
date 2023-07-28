package com.gildedgames.aether.item.accessory;

import com.gildedgames.aether.player.AetherPlayerHandler;
import com.matthewperiut.accessoryapi.api.Accessory;
import net.minecraft.entity.player.PlayerBase;
import net.minecraft.item.ItemInstance;
import net.modificationstation.stationapi.api.registry.Identifier;
import net.modificationstation.stationapi.api.template.item.TemplateItemBase;

public class RegenerationStone extends TemplateItemBase implements Accessory
{
    public RegenerationStone(Identifier identifier)
    {
        super(identifier);
        this.setMaxStackSize(1);
        this.setDurability(250);
    }

    @Override
    public String[] getAccessoryTypes(ItemInstance item)
    {
        return new String[]{"misc"};
    }

    @Override
    public ItemInstance tickWhileWorn(PlayerBase playerBase, ItemInstance itemInstance)
    {
        if (playerBase.field_1645 % 200 == 0)
        {
            int maxHealth = AetherPlayerHandler.get(playerBase).maxHealth;
            if (playerBase.health < maxHealth)
            {
                playerBase.health += 1;
                itemInstance.applyDamage(1, playerBase);
            }
        }
        return itemInstance;
    }
}
