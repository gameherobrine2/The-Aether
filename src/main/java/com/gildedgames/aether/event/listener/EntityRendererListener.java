package com.gildedgames.aether.event.listener;

import com.gildedgames.aether.client.render.entity.RenderDartEnchanted;
import com.gildedgames.aether.client.render.entity.RenderDartGolden;
import com.gildedgames.aether.client.render.entity.RenderDartPoison;
import com.gildedgames.aether.client.render.entity.RenderFlyingCow;
import com.gildedgames.aether.client.render.entity.RenderPoisonNeedle;

import static com.gildedgames.aether.Aether.MODID;
import static net.modificationstation.stationapi.api.registry.Identifier.of;
import com.gildedgames.aether.client.render.entity.RenderSlider;
import com.gildedgames.aether.client.render.entity.RenderValkyrie;
import com.gildedgames.aether.client.render.entity.RenderZephyr;
import com.gildedgames.aether.client.render.entity.RenderZephyrSnowball;
import com.gildedgames.aether.client.render.model.ModelFlyingCow1;
import com.gildedgames.aether.client.render.model.ModelFlyingCow2;
import com.gildedgames.aether.client.render.model.ModelSlider;
import com.gildedgames.aether.client.render.model.ModelValkyrie;
import com.gildedgames.aether.entity.animal.EntityFlyingCow;
import com.gildedgames.aether.entity.boss.EntitySlider;
import com.gildedgames.aether.entity.boss.EntityValkyrie;
import com.gildedgames.aether.entity.mobs.EntityZephyr;
import com.gildedgames.aether.entity.projectile.EntityDartEnchanted;
import com.gildedgames.aether.entity.projectile.EntityDartGolden;
import com.gildedgames.aether.entity.projectile.EntityDartPoison;
import com.gildedgames.aether.entity.projectile.EntityPoisonNeedle;
import com.gildedgames.aether.entity.projectile.EntityZephyrSnowball;
import com.gildedgames.aether.mixin.MinecraftClientAccessor;

import io.github.prospector.modmenu.mixin.MinecraftAccessor;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.modificationstation.stationapi.api.client.event.render.entity.EntityRendererRegisterEvent;
import net.modificationstation.stationapi.api.event.registry.MobHandlerRegistryEvent;
public class EntityRendererListener {
    @EventListener
    private static void registerEntity(EntityRendererRegisterEvent event) {
        event.renderers.put(EntityFlyingCow.class, new RenderFlyingCow(new ModelFlyingCow1(), new ModelFlyingCow2(), 0.7f));
        event.renderers.put(EntityZephyr.class, new RenderZephyr());
        event.renderers.put(EntityZephyrSnowball.class, new RenderZephyrSnowball());
        event.renderers.put(EntitySlider.class, new RenderSlider(new ModelSlider(0.0f, 12.0f), 1.5f));
        event.renderers.put(EntityValkyrie.class, new RenderValkyrie(new ModelValkyrie(), 0.3f));
        event.renderers.put(EntityPoisonNeedle.class, new RenderPoisonNeedle());
        event.renderers.put(EntityDartPoison.class, new RenderDartPoison());
        event.renderers.put(EntityDartGolden.class, new RenderDartGolden());
        event.renderers.put(EntityDartEnchanted.class, new RenderDartEnchanted());
    }
}