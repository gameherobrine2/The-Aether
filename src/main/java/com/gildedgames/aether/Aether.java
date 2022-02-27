package com.gildedgames.aether;

import com.gildedgames.aether.entity.boss.EntitySlider;
import com.gildedgames.aether.inventory.InventoryAether;

import net.minecraft.entity.EntityBase;
import net.modificationstation.stationapi.api.mod.entrypoint.Entrypoint;
import net.modificationstation.stationapi.api.mod.entrypoint.EventBusPolicy;
import net.modificationstation.stationapi.api.registry.Identifier;
import net.modificationstation.stationapi.api.registry.ModID;
import net.modificationstation.stationapi.api.util.Null;

@Entrypoint(eventBus = @EventBusPolicy(registerInstance = false, registerStatic = false))
public class Aether {

    @Entrypoint.ModID
    public static final ModID MODID = Null.get();
    
    public static Identifier of(String id) {
        return Identifier.of(MODID, id);
    }
    
    public static InventoryAether inv;
    public static int maxHealth = 20;
	public static EntityBase currentBoss;
}
