package com.gildedgames.aether.item.accessory;

import com.gildedgames.aether.mixin.access.EntityBaseAccessor;
import com.matthewperiut.accessoryapi.api.Accessory;
import com.matthewperiut.accessoryapi.api.AccessoryType;
import net.minecraft.client.render.entity.PlayerRenderer;
import net.minecraft.client.render.entity.model.Biped;
import net.minecraft.entity.player.PlayerBase;
import net.minecraft.item.ItemInstance;
import net.modificationstation.stationapi.api.registry.Identifier;
import net.modificationstation.stationapi.api.template.item.TemplateItemBase;

public class GoldenFeather extends TemplateItemBase implements Accessory
{
    public GoldenFeather(Identifier identifier)
    {
        super(identifier);
        this.setMaxStackSize(1);
        this.setDurability(500);
    }

    @Override
    public AccessoryType[] getAccessoryTypes(ItemInstance item)
    {
        return new AccessoryType[]{AccessoryType.misc};
    }

    public static void slowFall(PlayerBase player)
    {
        if (player.velocityY < 0)
        {
            if (player.method_1373()) // isSneaking
                player.velocityY *= 0.9;
            else player.velocityY *= 0.6;
        }

        ((EntityBaseAccessor) (player)).setFallDistance(0.0f);

    }

    @Override
    public void tickWhileWorn(PlayerBase playerBase, ItemInstance itemInstance)
    {
        slowFall(playerBase);
    }

    @Override
    public void renderWhileWorn(PlayerBase playerBase, PlayerRenderer playerRenderer, ItemInstance itemInstance, Biped biped, Object[] objects)
    {

    }

    @Override
    public void onAccessoryAdded(PlayerBase playerBase, ItemInstance itemInstance)
    {

    }

    @Override
    public void onAccessoryRemoved(PlayerBase playerBase, ItemInstance itemInstance)
    {

    }
}
