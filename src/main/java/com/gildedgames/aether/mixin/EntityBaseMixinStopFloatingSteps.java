package com.gildedgames.aether.mixin;

import net.minecraft.entity.EntityBase;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@Mixin(EntityBase.class)
public class EntityBaseMixinStopFloatingSteps
{
    @Redirect(method = "move", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/EntityBase;canClimb()Z"))
    public boolean canClimbOrAir(EntityBase instance)
    {
        try
        {
            Method canClimbMethod = EntityBase.class.getDeclaredMethod("canClimb");
            canClimbMethod.setAccessible(true);
            boolean originalCanClimb = (boolean) canClimbMethod.invoke(instance);
            return instance.onGround && originalCanClimb;
        }
        catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e)
        {
            e.printStackTrace();
            return false;
        }
    }
}
