package com.gildedgames.aether.item.accessory;

import com.gildedgames.aether.Aether;
import com.matthewperiut.accessoryapi.api.Accessory;
import net.minecraft.client.render.entity.PlayerRenderer;
import net.minecraft.client.render.entity.model.Biped;
import net.minecraft.entity.player.PlayerBase;
import net.minecraft.item.ItemInstance;
import net.modificationstation.stationapi.api.registry.Identifier;
import net.modificationstation.stationapi.api.template.item.TemplateItemBase;

public class RegenerationStone extends TemplateItemBase implements Accessory {
    public RegenerationStone(Identifier identifier) {
        super(identifier);
        this.setMaxStackSize(1);
        this.setDurability(250);
    }

    @Override
    public Type getType() {
        return Type.misc;
    }

    @Override
    public void tickWhileWorn(PlayerBase playerBase, ItemInstance itemInstance) {
        if (playerBase.field_1645 % 200 == 0)
        {
            int maxHealth = Aether.getPlayerHandler(playerBase).maxHealth;
            if (playerBase.health < maxHealth)
            {
                playerBase.health += 1;
                itemInstance.applyDamage(1, playerBase);
            }
        }
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
