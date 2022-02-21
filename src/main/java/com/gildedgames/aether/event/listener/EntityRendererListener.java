package com.gildedgames.aether.event.listener;

import com.gildedgames.aether.client.render.entity.RenderFlyingCow;
import com.gildedgames.aether.client.render.model.ModelFlyingCow1;
import com.gildedgames.aether.client.render.model.ModelFlyingCow2;
import com.gildedgames.aether.entity.animal.EntityFlyingCow;

import net.mine_diver.unsafeevents.listener.EventListener;
import net.modificationstation.stationapi.api.client.event.render.entity.EntityRendererRegisterEvent;
public class EntityRendererListener {
    @EventListener
    private static void registerEntity(EntityRendererRegisterEvent event) {
        event.renderers.put(EntityFlyingCow.class, new RenderFlyingCow(new ModelFlyingCow1(), new ModelFlyingCow2(), 0.7f));
    }
}