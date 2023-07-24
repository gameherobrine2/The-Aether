package com.gildedgames.aether.mixin.access;

import net.minecraft.entity.player.PlayerBase;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(PlayerBase.class)
public interface PlayerBaseAccessor
{
    @Accessor("sleeping")
    public void setSleeping(boolean b);
}
