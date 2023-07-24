package com.gildedgames.aether.mixin.data;

import com.gildedgames.aether.AetherMod;
import com.gildedgames.aether.mixin.access.LevelAccessor;
import net.minecraft.entity.player.PlayerBase;
import net.minecraft.level.dimension.DimensionFile;
import net.minecraft.util.io.CompoundTag;
import net.minecraft.util.io.NBTIO;
import org.spongepowered.asm.mixin.Mixin;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

@Mixin(PlayerBase.class)
public class PlayerBaseMixin
{

    private void writeCustomData()
    {
        final CompoundTag customData = new CompoundTag();
        PlayerBase player = (PlayerBase) (Object) this;
        customData.put("MaxHealth", (byte) AetherMod.getPlayerHandler(PlayerBase.class.cast(this)).maxHealth);
        try
        {
            final File file = new File(((DimensionFileAccessor) ((DimensionFile) ((LevelAccessor) player.level).getDimData())).getSaveFolder(), "aether.dat");
            NBTIO.writeGzipped(customData, (OutputStream) new FileOutputStream(file));
        }
        catch (IOException ioexception)
        {
            ioexception.printStackTrace();
            throw new RuntimeException("Failed to create player data");
        }
    }
}
