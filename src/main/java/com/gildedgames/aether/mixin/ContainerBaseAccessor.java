package com.gildedgames.aether.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import net.minecraft.client.gui.screen.container.ContainerBase;
@Mixin(ContainerBase.class)
public interface ContainerBaseAccessor {
	
	@Accessor("containerWidth")
	int getContainerWidth();
	
	@Accessor("containerHeight")
	int getContainerHeight();
	
}
