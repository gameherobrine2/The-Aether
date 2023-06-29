package com.gildedgames.aether.client.color;

import com.gildedgames.aether.item.accessory.CosmeticCape;
import com.gildedgames.aether.registry.AetherItems;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.minecraft.item.ItemInstance;
import net.modificationstation.stationapi.api.client.color.item.ItemColorProvider;
import net.modificationstation.stationapi.api.client.event.color.item.ItemColorsRegisterEvent;
import net.modificationstation.stationapi.api.item.ItemConvertible;
import net.modificationstation.stationapi.api.mod.entrypoint.Entrypoint;
import net.modificationstation.stationapi.api.registry.Identifier;
import net.modificationstation.stationapi.api.registry.ItemRegistry;
import net.modificationstation.stationapi.api.registry.ModID;
import net.modificationstation.stationapi.api.util.Null;

public class ItemColorListener
{
    // todo: colors with stapi?
    @Entrypoint.ModID
    public static final ModID MOD_ID = Null.get();
    public static class provider implements ItemColorProvider
    {
        @Override
        public int getColor(ItemInstance item, int var2)
        {
            if (item.getType() instanceof CosmeticCape cape)
            {
                return cape.color;
            }
            return -1;
        }
    }
    @EventListener
    public void registerItemColors(ItemColorsRegisterEvent event)
    {
        System.out.println("registering colors");
        //ItemConvertible[] items = {AetherItems.RedCape}; was using this but wasn't working
        // tried the default fabric way of doing it
        ItemConvertible[] items = {ItemRegistry.INSTANCE.get(Identifier.of(MOD_ID, "item_red_cape"))};
        event.itemColors.register(new provider(), items);
    }
}
