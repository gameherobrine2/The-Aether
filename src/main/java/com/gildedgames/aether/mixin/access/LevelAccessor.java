package com.gildedgames.aether.mixin.access;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import net.minecraft.level.Level;
import net.minecraft.level.dimension.DimensionData;

@Mixin(Level.class)
public interface LevelAccessor {
	
	@Accessor("dimensionData")
	DimensionData getDimData();
	@Accessor("field_212")
	void set212(int i);
}
