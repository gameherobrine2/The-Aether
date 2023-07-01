package com.gildedgames.aether.mixin.access;

import net.minecraft.entity.EntityBase;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.Random;

@Mixin(EntityBase.class)
public interface EntityBaseAccessor{

	/*GETTERS*/
	@Accessor("field_1612")
	boolean get1612();
	@Accessor("fallDistance")
	float getFallDistance();
	@Accessor("field_1611")
	int getNextStepDistance();
	@Accessor("rand")
	public Random getRand();
	@Accessor("immuneToFire")
	public boolean getImmunityToFire();

	/*SETTERS*/
	@Accessor("height")
	void setHeight(float f);
	@Accessor("width")
	void setWidth(float f);
	@Accessor("fallDistance")
	void setFallDistance(float f);
	@Accessor("field_1611")
	void setNextStepDistance(int i);
	@Accessor("immuneToFire")
	public void setImmunityToFire(boolean b); //did i spell it correctly?
}
