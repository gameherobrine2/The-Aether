package com.gildedgames.aether.mixin.sound;

import net.minecraft.client.sound.SoundMap;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.List;

/*DEBUG-ONLY*/
@Mixin(SoundMap.class)
public interface SoundMapAccessor
{
    @Accessor("soundList")
    public List getSoundList();
}
