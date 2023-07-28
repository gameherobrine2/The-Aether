package com.gildedgames.aether.item.accessory;

import com.matthewperiut.accessoryapi.api.Accessory;
import net.minecraft.entity.player.PlayerBase;
import net.minecraft.item.ItemInstance;
import net.modificationstation.stationapi.api.registry.Identifier;
import net.modificationstation.stationapi.api.template.item.TemplateItemBase;

public class IronBubble extends TemplateItemBase implements Accessory
{
    public IronBubble(Identifier identifier)
    {
        super(identifier);
        this.setMaxStackSize(1);
        this.setDurability(500);
    }

    @Override
    public String[] getAccessoryTypes(ItemInstance item)
    {
        return new String[]{"misc"};
    }

    @Override
    public ItemInstance tickWhileWorn(PlayerBase playerBase, ItemInstance itemInstance)
    {
        playerBase.air = 20;
        return itemInstance;
    }
}
