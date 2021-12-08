package com.gildedgames.aether.client.texture;

import net.mine_diver.unsafeevents.listener.EventListener;
import net.modificationstation.stationapi.api.client.event.texture.TextureRegisterEvent;
import net.modificationstation.stationapi.api.client.model.Model;
import net.modificationstation.stationapi.api.mod.entrypoint.Entrypoint;
import net.modificationstation.stationapi.api.mod.entrypoint.EventBusPolicy;

import static com.gildedgames.aether.Aether.of;
import static net.modificationstation.stationapi.api.client.model.json.JsonModel.get;

@Entrypoint(eventBus = @EventBusPolicy(registerInstance = false))
public class AetherTextures {

    @EventListener
    private static void registerTextures(TextureRegisterEvent event) {
        AETHER_PORTAL = get(of("block/aether_portal_block"));
        AETHER_PORTAL_EW = get(of("block/aether_portal_ew"));
        AETHER_PORTAL_NS = get(of("block/aether_portal_ns"));
        AETHER_DIRT = get(of("block/aether_dirt"));
        AETHER_GRASS_BLOCK = get(of("block/aether_grass_block"));
        HOLYSTONE = get(of("block/holystone"));
        MOSSY_HOLYSTONE = get(of("block/mossy_holystone"));
    }

    public static Model
            AETHER_PORTAL,
            AETHER_PORTAL_EW,
            AETHER_PORTAL_NS,
            AETHER_DIRT,
            AETHER_GRASS_BLOCK,
            HOLYSTONE,
            MOSSY_HOLYSTONE;
}
