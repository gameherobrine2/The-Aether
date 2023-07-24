package com.gildedgames.aether.mixin.gui;

import net.minecraft.client.gui.screen.container.ContainerBase;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(ContainerBase.class)
public interface ContainerBaseAccessor
{

    @Accessor("containerWidth")
    int getContainerWidth();

    @Accessor("containerHeight")
    int getContainerHeight();

}
