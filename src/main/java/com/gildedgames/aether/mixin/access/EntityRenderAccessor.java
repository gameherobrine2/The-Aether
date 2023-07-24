package com.gildedgames.aether.mixin.access;

import net.minecraft.client.render.entity.EntityRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(EntityRenderer.class)
public interface EntityRenderAccessor
{

    @Invoker("bindTexture")
    void invokeBindTexture(String string);
}
