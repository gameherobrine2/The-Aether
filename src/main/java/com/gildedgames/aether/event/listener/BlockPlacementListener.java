package com.gildedgames.aether.event.listener;

import com.gildedgames.aether.block.AetherPortal;
import com.gildedgames.aether.registry.AetherBlocks;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.minecraft.block.BlockBase;
import net.modificationstation.stationapi.api.event.level.BlockSetEvent;
import net.modificationstation.stationapi.api.mod.entrypoint.Entrypoint;
import net.modificationstation.stationapi.api.mod.entrypoint.EventBusPolicy;

@Entrypoint(eventBus = @EventBusPolicy(registerInstance = false))
public class BlockPlacementListener
{

    @EventListener
    private static void blockSet(BlockSetEvent event)
    {
        if (
                (event.blockState.getBlock().id == BlockBase.STILL_WATER.id || event.blockState.getBlock().id == BlockBase.FLOWING_WATER.id) &&
                        event.level.getTileId(event.x, event.y - 1, event.z) == BlockBase.GLOWSTONE.id &&
                        ((AetherPortal) AetherBlocks.AETHER_PORTAL).method_736(event.level, event.x, event.y, event.z)
        )
        {
            event.cancel();
            event.level.setTile(event.x, event.y, event.z, AetherBlocks.AETHER_PORTAL.id);
        }
    }
}
