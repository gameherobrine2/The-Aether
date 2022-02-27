package com.gildedgames.aether.client.level;

import com.gildedgames.aether.client.render.AetherWorldRenderer;
import net.fabricmc.loader.api.FabricLoader;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.minecraft.client.Minecraft;
import net.modificationstation.stationapi.api.event.level.LevelEvent;
import net.modificationstation.stationapi.api.mod.entrypoint.Entrypoint;
import net.modificationstation.stationapi.api.mod.entrypoint.EventBusPolicy;

@Entrypoint(eventBus = @EventBusPolicy(registerInstance = false))
public class AetherLevelListeners {

    @EventListener
    private static void registerLevelListeners(LevelEvent.Init event) {
        if (!init) {
            init = true;
            //noinspection deprecation
            AETHER_WORLD_RENDERER = new AetherWorldRenderer((Minecraft) FabricLoader.getInstance().getGameInstance());
        }
        AETHER_WORLD_RENDERER.updateLevel(event.level);
    }

    private static boolean init;

    private static AetherWorldRenderer AETHER_WORLD_RENDERER;
}
