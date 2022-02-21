package com.gildedgames.aether.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import net.minecraft.client.render.Tessellator;
import net.minecraft.entity.Living;

@Mixin(Living.class)
public interface LivingAccessor{
	 @Accessor("field_1029")
	 float get1029();
	 @Accessor("field_1060")
	 float get1060();
	 @Accessor("jumping")
	 boolean getJumping();
}
