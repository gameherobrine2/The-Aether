package com.gildedgames.aether.mixin.data;

import net.minecraft.level.dimension.DimensionFile;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.io.File;

@Mixin(DimensionFile.class)
public interface DimensionFileAccessor
{
    @Accessor("parentFolder")
    public File getSaveFolder();
}
