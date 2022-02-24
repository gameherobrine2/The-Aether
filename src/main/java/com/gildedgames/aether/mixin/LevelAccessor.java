package com.gildedgames.aether.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import net.minecraft.level.Level;
import net.minecraft.level.dimension.DimensionData;

@Mixin(Level.class)
public interface LevelAccessor {
	
	@Accessor("dimensionData")
	DimensionData getDimData();
	
}
