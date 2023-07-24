package com.gildedgames.aether.event.listener;

import com.gildedgames.aether.entity.EntityCloudParachute;
import com.gildedgames.aether.entity.animal.*;
import com.gildedgames.aether.entity.boss.EntityFireMonster;
import com.gildedgames.aether.entity.boss.EntitySlider;
import com.gildedgames.aether.entity.boss.EntityValkyrie;
import com.gildedgames.aether.entity.mobs.*;
import com.gildedgames.aether.entity.projectile.*;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.modificationstation.stationapi.api.event.entity.EntityRegister;
import net.modificationstation.stationapi.api.event.registry.MobHandlerRegistryEvent;
import net.modificationstation.stationapi.api.registry.Registry;

import static com.gildedgames.aether.AetherMod.MODID;
import static net.modificationstation.stationapi.api.registry.Identifier.of;

public class EntityRegistry
{
    @EventListener
    public void registerMobHandlers(MobHandlerRegistryEvent event)
    {
        Registry.register(event.registry, of(MODID, "flying_cow"), EntityFlyingCow::new);
        Registry.register(event.registry, of(MODID, "awful_monster"), EntityZephyr::new);
        Registry.register(event.registry, of(MODID, "boss_slider"), EntitySlider::new);
        Registry.register(event.registry, of(MODID, "entity_sentry"), EntitySentry::new);
        Registry.register(event.registry, of(MODID, "entity_mimic"), EntityMimic::new);
        Registry.register(event.registry, of(MODID, "entity_valkyrie"), EntityValkyrie::new);
        Registry.register(event.registry, of(MODID, "entity_homeshot"), EntityHomeShot::new);
        Registry.register(event.registry, of(MODID, "boss_sunspirit"), EntityFireMonster::new);
        Registry.register(event.registry, of(MODID, "entity_fireminion"), EntityFireMinion::new);
        Registry.register(event.registry, of(MODID, "entity_moa"), EntityMoa::new);
        Registry.register(event.registry, of(MODID, "entity_aerwhale"), EntityAerwhale::new);
        Registry.register(event.registry, of(MODID, "entity_aechor_plant"), EntityAechorPlant::new);
        Registry.register(event.registry, of(MODID, "entity_aerbunny"), EntityAerbunny::new);
        Registry.register(event.registry, of(MODID, "entity_cockatrice"), EntityCockatrice::new);
        Registry.register(event.registry, of(MODID, "entity_sheepuff"), EntitySheepuff::new);
        Registry.register(event.registry, of(MODID, "entity_phyg"), EntityPhyg::new);
        Registry.register(event.registry, of(MODID, "entity_swet"), EntitySwet::new);
        Registry.register(event.registry, of(MODID, "entity_cloud"), EntityMiniCloud::new);
        Registry.register(event.registry, of(MODID, "entity_whirly"), Whirly::new);
    }

    @EventListener
    public void registerEntities(EntityRegister event)
    {
        event.register(EntityCockatrice.class, "entity_cockatrice");
        event.register(EntitySheepuff.class, "entity_sheepuff");
        event.register(EntityPhyg.class, "entity_phyg");
        event.register(EntitySwet.class, "entity_swet");
        event.register(EntityFlyingCow.class, "flying_cow");
        event.register(EntityZephyr.class, "awful_monster");
        event.register(EntityZephyrSnowball.class, "dangerous_weapon_zephyrsnowball");
        event.register(EntitySentry.class, "entity_sentry");
        event.register(EntityMimic.class, "entity_mimic");
        event.register(EntityMoa.class, "entity_moa");
        event.register(EntitySlider.class, "boss_slider");
        event.register(EntityValkyrie.class, "entity_valkyre");
        event.register(EntityHomeShot.class, "entity_homeshot");
        event.register(EntityPoisonNeedle.class, "PoisonNeedle");
        event.register(EntityDartPoison.class, "PoisonDart");
        event.register(EntityDartGolden.class, "GoldenDart");
        event.register(EntityDartEnchanted.class, "EnchantedDart");
        event.register(EntityFireMonster.class, "entity_sunspirit");
        event.register(EntityFireMinion.class, "entity_fireminion");
        event.register(EntityFiroBall.class, "entity_fireball");
        event.register(EntityFloatingBlock.class, "entity_floatingblock");
        event.register(EntityAerwhale.class, "entity_aerwhale");
        event.register(EntityAechorPlant.class, "entity_aechor_plant");
        event.register(EntityAerbunny.class, "entity_aerbunny");
        event.register(EntityCloudParachute.class, "entity_parachute");
        event.register(EntityMiniCloud.class, "entity_cloud");
        event.register(EntityFlamingArrow.class, "entity_arrow");
        event.register(Whirly.class, "entity_whirly");
        event.register(EntityHomeShot.class, "entity_homeshot");
    }
}
