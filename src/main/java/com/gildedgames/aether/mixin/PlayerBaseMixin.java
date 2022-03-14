package com.gildedgames.aether.mixin;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.gildedgames.aether.Aether;
import com.gildedgames.aether.inventory.InventoryAether;
import net.minecraft.entity.player.PlayerBase;
import net.minecraft.level.dimension.DimensionFile;
import net.minecraft.util.io.CompoundTag;
import net.minecraft.util.io.ListTag;
import net.minecraft.util.io.NBTIO;

@Mixin(PlayerBase.class)
public class PlayerBaseMixin {
	@Inject(method = "remove", at = @At(value = "TAIL"))
    private void setEntityDead(CallbackInfo ci) {
		Aether.inv.dropAllItems();
		this.writeCustomData(new InventoryAether((PlayerBase)(Object)this));
    }
	
	private void writeCustomData(final InventoryAether inv) {
        final CompoundTag customData = new CompoundTag();
        Aether.inv = inv;
        PlayerBase player = (PlayerBase)(Object)this;
        customData.put("MaxHealth", (byte)Aether.maxHealth);
        customData.put("Inventory",inv.writeToNBT(new ListTag()));
        try {
            final File file = new File(((DimesnionFileAccessor)((DimensionFile)((LevelAccessor)player.level).getDimData())).getSaveFolder(), "aether.dat");
            NBTIO.writeGzipped(customData, (OutputStream)new FileOutputStream(file));
        }
        catch (IOException ioexception) {
            ioexception.printStackTrace();
            throw new RuntimeException("Failed to create player data");
        }
    }
}
