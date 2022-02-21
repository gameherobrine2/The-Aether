package com.gildedgames.aether.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import net.minecraft.entity.EntityBase;

@Mixin(EntityBase.class)
public interface EntityBaseAccessor{
	 @Accessor("fallDistance")
	 float getFallDistance();
	 void setFallDistance(float i); 
}
