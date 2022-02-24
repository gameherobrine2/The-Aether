package com.gildedgames.aether.event.listener;

import com.gildedgames.aether.client.render.entity.RenderFlyingCow;
import static com.gildedgames.aether.Aether.MODID;
import static net.modificationstation.stationapi.api.registry.Identifier.of;
import com.gildedgames.aether.client.render.entity.RenderSlider;
import com.gildedgames.aether.client.render.entity.RenderZephyr;
import com.gildedgames.aether.client.render.entity.RenderZephyrSnowball;
import com.gildedgames.aether.client.render.model.ModelFlyingCow1;
import com.gildedgames.aether.client.render.model.ModelFlyingCow2;
import com.gildedgames.aether.client.render.model.ModelSlider;
import com.gildedgames.aether.entity.animal.EntityFlyingCow;
import com.gildedgames.aether.entity.boss.EntitySlider;
import com.gildedgames.aether.entity.mobs.EntityZephyr;
import com.gildedgames.aether.entity.projectile.EntityZephyrSnowball;
import com.gildedgames.aether.mixin.MinecraftClientAccessor;

import net.mine_diver.unsafeevents.listener.EventListener;
import net.modificationstation.stationapi.api.event.entity.EntityRegister;
import net.modificationstation.stationapi.api.event.registry.MobHandlerRegistryEvent;

public class EntityRegistry {
    @EventListener
    public void registerMobHandlers(MobHandlerRegistryEvent event) {
    	 System.out.println("works");
    	 event.registry.register(of(MODID, "flying_cow"), EntityFlyingCow::new);
    	 event.registry.register(of(MODID, "awful_monster"), EntityZephyr::new);
    	 //event.registry.register(of(MODID, "dangerous_weapon_zephyrsnowball"), EntityZephyrSnowball::new);
    	 event.registry.register(of(MODID, "boss_slider"), EntitySlider::new);
    }
    
    @EventListener
    public void registerEntities(EntityRegister event) {
    	event.register(EntityFlyingCow.class,"flying_cow");
   	 	event.register(EntityZephyr.class,"awful_monster");
   	 //event.registry.register(of(MODID, "dangerous_weapon_zephyrsnowball"), EntityZephyrSnowball::new);
   	 	event.register(EntitySlider.class, "boss_slider");
    }
}
