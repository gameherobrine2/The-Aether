package com.gildedgames.aether.mixin;

import java.util.Random;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;

import net.minecraft.entity.EntityBase;

@Mixin(EntityBase.class)
public interface EntityBaseAccessor{
	 @Accessor("field_1612")
	 boolean get1612();
	 @Accessor("fallDistance")
	 float getFallDistance();
	 @Accessor("fallDistance")
	 void setFallDistance(float i);
     @Accessor("rand")
	 public Random getRand();
 	 @Accessor("immuneToFire")
 	 public void setImmunityToFire(boolean b); //did i spell it correctly?

}
