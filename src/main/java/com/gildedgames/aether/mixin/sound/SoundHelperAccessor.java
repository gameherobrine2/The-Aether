package com.gildedgames.aether.mixin.sound;

import net.minecraft.client.sound.SoundHelper;
import net.minecraft.client.sound.SoundMap;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import paulscode.sound.SoundSystem;

@Mixin(SoundHelper.class)
public interface SoundHelperAccessor
{

    @Accessor("soundUID")
    int getSoundUID();

    @Accessor("music")
    public SoundMap getBgMusicMap();

    @Accessor("soundSystem")
    public static SoundSystem getSoundSystem()
    {
        throw new AssertionError();
    }

    ;

    @Accessor("musicCountdown")
    void setMusicCountdown(int i);
}
