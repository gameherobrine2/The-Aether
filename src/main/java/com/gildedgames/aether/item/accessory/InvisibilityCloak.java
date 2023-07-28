package com.gildedgames.aether.item.accessory;

import com.gildedgames.aether.player.AetherPlayerHandler;
import com.matthewperiut.accessoryapi.api.Accessory;
import net.minecraft.entity.player.PlayerBase;
import net.minecraft.item.ItemInstance;
import net.modificationstation.stationapi.api.registry.Identifier;
import net.modificationstation.stationapi.api.template.item.TemplateItemBase;

public class InvisibilityCloak extends TemplateItemBase implements Accessory
{
    public InvisibilityCloak(Identifier identifier)
    {
        super(identifier);
        setMaxStackSize(1);
        setDurability(300);
    }

    @Override
    public String[] getAccessoryTypes(ItemInstance item)
    {
        return new String[]{"cape"};
    }

    @Override
    public void onAccessoryAdded(PlayerBase playerBase, ItemInstance itemInstance)
    {
        AetherPlayerHandler.get(playerBase).visible = false;
    }

    @Override
    public void onAccessoryRemoved(PlayerBase playerBase, ItemInstance itemInstance)
    {
        AetherPlayerHandler.get(playerBase).visible = true;
    }
}
