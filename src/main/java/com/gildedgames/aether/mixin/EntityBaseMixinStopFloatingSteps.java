package com.gildedgames.aether.mixin;

import net.minecraft.entity.EntityBase;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(EntityBase.class)
public abstract class EntityBaseMixinStopFloatingSteps
{
    @Shadow
    protected abstract boolean canClimb();

    @Redirect(method = "move", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/EntityBase;canClimb()Z"), require = 0)
    public boolean canClimbOrAir(EntityBase instance) {
        boolean originalCanClimb = canClimb();
        return instance.onGround && originalCanClimb;
    }
}
