package com.gildedgames.aether.event.listener;

import org.jetbrains.annotations.NotNull;

import com.gildedgames.aether.entity.tile.TileEntityFreezer;

import net.mine_diver.unsafeevents.listener.EventListener;
import net.modificationstation.stationapi.api.event.tileentity.TileEntityRegisterEvent;
import net.modificationstation.stationapi.api.mod.entrypoint.Entrypoint;
import net.modificationstation.stationapi.api.registry.Identifier;
import net.modificationstation.stationapi.api.util.Null;

public class TileEntityListener {
	@Entrypoint.ModID
    public static final net.modificationstation.stationapi.api.registry.@NotNull ModID MOD_ID = Null.get();

    @EventListener
    public void registerTileEntities(TileEntityRegisterEvent event) {
        event.register(TileEntityFreezer.class, Identifier.of(MOD_ID, "freezer_entity").toString());
    }
}
