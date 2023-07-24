package com.gildedgames.aether.item.accessory;

import com.matthewperiut.accessoryapi.api.Accessory;
import com.matthewperiut.accessoryapi.api.AccessoryType;
import com.matthewperiut.accessoryapi.api.helper.AccessoryRenderHelper;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.PlayerRenderer;
import net.minecraft.client.render.entity.model.Biped;
import net.minecraft.entity.player.PlayerBase;
import net.minecraft.item.ItemInstance;
import net.modificationstation.stationapi.api.registry.Identifier;
import net.modificationstation.stationapi.api.template.item.TemplateItemBase;

public class CosmeticCape extends TemplateItemBase implements Accessory
{
    public String texture;
    public int colour;

    public CosmeticCape(Identifier identifier, String texture, int color)
    {
        super(identifier);
        this.texture = texture;
        this.colour = color;
        this.setMaxStackSize(1);
        this.setDurability(500);
    }

    public CosmeticCape(Identifier identifier, String texture)
    {
        this(identifier, texture, 16777215);
    }

    @Environment(EnvType.CLIENT)
    public int getColourMultiplier(int i)
    {
        return colour;
    }

    @Override
    public AccessoryType[] getAccessoryTypes(ItemInstance item)
    {
        return new AccessoryType[]{AccessoryType.cape};
    }

    @Override
    public void tickWhileWorn(PlayerBase playerBase, ItemInstance itemInstance)
    {

    }

    @Override
    public void renderWhileWorn(PlayerBase player, PlayerRenderer renderer, ItemInstance itemInstance, Biped model, Object[] objects)
    {
        AccessoryRenderHelper.Cape(player, ((CosmeticCape) itemInstance.getType()).texture, model, objects);
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
