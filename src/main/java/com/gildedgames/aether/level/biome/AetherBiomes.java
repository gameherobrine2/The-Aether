package com.gildedgames.aether.level.biome;

import net.mine_diver.unsafeevents.listener.EventListener;
import net.minecraft.level.biome.Biome;
import net.modificationstation.stationapi.api.event.level.biome.BiomeRegisterEvent;
import net.modificationstation.stationapi.api.mod.entrypoint.Entrypoint;
import net.modificationstation.stationapi.api.mod.entrypoint.EventBusPolicy;

@Entrypoint(eventBus = @EventBusPolicy(registerInstance = false))
public class AetherBiomes {

    @EventListener
    private static void registerBiomes(BiomeRegisterEvent event) {
        AETHER = new Aether();
    }

    public static Biome AETHER;
}
