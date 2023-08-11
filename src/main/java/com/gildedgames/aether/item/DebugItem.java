package com.gildedgames.aether.item;

import com.gildedgames.aether.entity.boss.EntityValkyrie;
import net.minecraft.entity.EntityBase;
import net.minecraft.entity.player.PlayerBase;
import net.minecraft.item.ItemInstance;
import net.minecraft.level.Level;
import net.modificationstation.stationapi.api.registry.Identifier;
import net.modificationstation.stationapi.api.template.item.TemplateItemBase;

// todo: delete
public class DebugItem extends TemplateItemBase {
    public DebugItem(Identifier identifier) {
        super(identifier);
    }

    @Override
    public ItemInstance use(ItemInstance arg, Level arg2, PlayerBase arg3) {
        EntityBase e = new EntityValkyrie(arg2, arg3.x, arg3.y, arg3.z, true);
        e.setPosition(arg3.x, arg3.y, arg3.z);
        arg2.spawnEntity(e);


        return super.use(arg, arg2, arg3);
    }
}
