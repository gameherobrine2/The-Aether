package com.gildedgames.aether.item.accessory;

import com.gildedgames.aether.mixin.access.EntityBaseAccessor;
import com.matthewperiut.accessoryapi.api.Accessory;
import net.minecraft.client.render.entity.PlayerRenderer;
import net.minecraft.client.render.entity.model.Biped;
import net.minecraft.entity.player.PlayerBase;
import net.minecraft.item.ItemInstance;
import net.modificationstation.stationapi.api.registry.Identifier;
import net.modificationstation.stationapi.api.template.item.TemplateItemBase;

public class GoldenFeather extends TemplateItemBase implements Accessory {
    public GoldenFeather(Identifier identifier) {
        super(identifier);
        this.setMaxStackSize(1);
        this.setDurability(500);
    }

    @Override
    public Type getType() {
        return Type.misc;
    }

    @Override
    public void tickWhileWorn(PlayerBase playerBase, ItemInstance itemInstance) {
        if (playerBase.velocityY < 0)
        {
            if (playerBase.method_1373()) // isSneaking
                playerBase.velocityY *= 0.9;
            else
                playerBase.velocityY *= 0.6;
        }

        ((EntityBaseAccessor)(playerBase)).setFallDistance(0.0f);
    }

    @Override
    public void renderWhileWorn(PlayerBase playerBase, PlayerRenderer playerRenderer, ItemInstance itemInstance, Biped biped, Object[] objects) {

    }

    @Override
    public void onAccessoryAdded(PlayerBase playerBase, ItemInstance itemInstance) {

    }

    @Override
    public void onAccessoryRemoved(PlayerBase playerBase, ItemInstance itemInstance) {

    }
}
