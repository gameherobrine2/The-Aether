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
import com.gildedgames.aether.entity.boss.EntityFireMonster;
import com.gildedgames.aether.entity.boss.EntitySlider;
import com.gildedgames.aether.entity.boss.EntityValkyrie;
import com.gildedgames.aether.entity.mobs.EntityFireMinion;
import com.gildedgames.aether.entity.mobs.EntityHomeShot;
import com.gildedgames.aether.entity.mobs.EntityMimic;
import com.gildedgames.aether.entity.mobs.EntitySentry;
import com.gildedgames.aether.entity.mobs.EntityZephyr;
import com.gildedgames.aether.entity.projectile.EntityDartEnchanted;
import com.gildedgames.aether.entity.projectile.EntityDartGolden;
import com.gildedgames.aether.entity.projectile.EntityDartPoison;
import com.gildedgames.aether.entity.projectile.EntityFiroBall;
import com.gildedgames.aether.entity.projectile.EntityPoisonNeedle;
import com.gildedgames.aether.entity.projectile.EntityZephyrSnowball;
import com.gildedgames.aether.mixin.MinecraftClientAccessor;

import net.mine_diver.unsafeevents.listener.EventListener;
import net.modificationstation.stationapi.api.event.entity.EntityRegister;
import net.modificationstation.stationapi.api.event.registry.MobHandlerRegistryEvent;

public class EntityRegistry {
    @EventListener
    public void registerMobHandlers(MobHandlerRegistryEvent event) {
    	 event.registry.register(of(MODID, "flying_cow"), EntityFlyingCow::new);
    	 event.registry.register(of(MODID, "awful_monster"), EntityZephyr::new);
    	 //event.registry.register(of(MODID, "dangerous_weapon_zephyrsnowball"), EntityZephyrSnowball::new);
    	 event.registry.register(of(MODID, "boss_slider"), EntitySlider::new);
    	 event.registry.register(of(MODID, "entity_sentry"), EntitySentry::new);
    	 event.registry.register(of(MODID, "entity_mimic"), EntityMimic::new);
    	 event.registry.register(of(MODID, "entity_valkyre"), EntityValkyrie::new);
    	 event.registry.register(of(MODID, "entity_homeshot"), EntityHomeShot::new);
    	 event.registry.register(of(MODID,"boss_sunspirit"), EntityFireMonster::new);
    	 event.registry.register(of(MODID,"entity_fireminion"), EntityFireMinion::new);
    	 
    	 
    }
    
    @EventListener
    public void registerEntities(EntityRegister event) {
    	event.register(EntityFlyingCow.class,"flying_cow");
    	event.register(EntityZephyr.class,"awful_monster");
    	event.register(EntityZephyrSnowball.class, "dangerous_weapon_zephyrsnowball");
    	event.register(EntitySentry.class, "entity_sentry");
    	event.register(EntityMimic.class, "entity_mimic");
    	event.register(EntitySlider.class, "boss_slider");
    	event.register(EntityValkyrie.class, "entity_valkyre");
    	event.register(EntityHomeShot.class, "entity_homeshot");
        event.register(EntityPoisonNeedle.class, "PoisonNeedle");
        event.register(EntityDartPoison.class, "PoisonDart");
        event.register(EntityDartGolden.class, "GoldenDart");
        event.register(EntityDartEnchanted.class, "EnchantedDart");
        event.register(EntityFireMonster.class,"entity_sunspirit");
        event.register(EntityFireMinion.class,"entity_fireminion");
        event.register(EntityFiroBall.class,"entity_fireball");
    }
}
