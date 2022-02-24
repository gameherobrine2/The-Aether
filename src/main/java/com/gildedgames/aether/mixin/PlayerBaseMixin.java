package com.gildedgames.aether.mixin;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import com.gildedgames.aether.mixin.DimesnionFileAccessor;
import java.io.OutputStream;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.At;

import com.gildedgames.aether.gui.container.ContainerAether;
import com.gildedgames.aether.inventory.InventoryAether;
import com.gildedgames.aether.registry.AetherItems;

import net.minecraft.client.gui.screen.ingame.Pause;
import net.minecraft.entity.player.AbstractClientPlayer;//.PlayerBase;
import net.minecraft.level.dimension.DimensionFile;
import net.minecraft.util.io.AbstractTag;
import net.minecraft.util.io.CompoundTag;
import net.minecraft.util.io.ListTag;
import net.minecraft.util.io.NBTIO;

@Mixin(AbstractClientPlayer.class)
public class PlayerBaseMixin {
    private InventoryAether inv;
    private int maxHealth = 20;
    
    @Inject(method = "writeCustomDataToTag", at = @At(value = "TAIL"))
    private void writeCustomDataToTag(CompoundTag tag, CallbackInfo ci) {
    	try {
    		AbstractClientPlayer player = AbstractClientPlayer.class.cast(this);
    		final CompoundTag customData = new CompoundTag();
    		customData.put("MaxHealth", (byte)this.maxHealth);
    		customData.put("Inventory", (AbstractTag)inv.writeToNBT(new ListTag()));
        
        	final File file = new File(((DimesnionFileAccessor)((DimensionFile)((LevelAccessor)player.level).getDimData())).getSaveFolder(), "aether.dat");
            NBTIO.writeGzipped(customData, (OutputStream)new FileOutputStream(file));
        }
        catch (Exception ioexception) {
            //throw new RuntimeException("Failed to create player data");
        }
    }
    
    //updateDespawnCounter
    @Inject(method = "readCustomDataFromTag", at = @At(value = "TAIL"))
    private void readCustomDataFromTag(CompoundTag tag, CallbackInfo ci) {
    	CompoundTag customData = new CompoundTag();
    	AbstractClientPlayer player = AbstractClientPlayer.class.cast(this);
        try {
        	final File file = new File(((DimesnionFileAccessor)((DimensionFile)((LevelAccessor)player.level).getDimData())).getSaveFolder(), "aether.dat");
            customData = NBTIO.readGzipped((InputStream)new FileInputStream(file));
            
            this.maxHealth = customData.getByte("MaxHealth");
            if (this.maxHealth < 20) {
                this.maxHealth = 20;
            }
            final ListTag nbttaglist = customData.getListTag("Inventory");
            this.inv.readFromNBT(nbttaglist);
        }
        catch (Exception ioexception) {
            System.out.println("Failed to read player data. Making new");
            this.maxHealth = 20;
        }
    }
    private void readCustomData() {
    	CompoundTag customData = new CompoundTag();
    	AbstractClientPlayer player = AbstractClientPlayer.class.cast(this);
        try {
        	final File file = new File(((DimesnionFileAccessor)((DimensionFile)((LevelAccessor)player.level).getDimData())).getSaveFolder(), "aether.dat");
            customData = NBTIO.readGzipped((InputStream)new FileInputStream(file));
            
            this.maxHealth = customData.getByte("MaxHealth");
            if (this.maxHealth < 20) {
                this.maxHealth = 20;
            }
            final ListTag nbttaglist = customData.getListTag("Inventory");
            this.inv.readFromNBT(nbttaglist);
        }
        catch (Exception ioexception) {
            System.out.println("Failed to read player data. Making new");
            this.maxHealth = 20;
        }
    }
    @Inject(method = "updateDespawnCounter", at = @At(value = "HEAD"))
	public void onLivingUpdate(CallbackInfo ci) {
		AbstractClientPlayer player = AbstractClientPlayer.class.cast(this);
    	if(inv == null) {
    		this.inv = new InventoryAether(player);
    		readCustomData();
    	}
    	if(!(player.playerContainer instanceof ContainerAether)) {
        	player.playerContainer = new ContainerAether(player.inventory, this.inv, true);
        	player.container = player.playerContainer;
    	}
        //if (MainMenu.mmactive) {
        //    player.setPosition(player.prevRenderX, player.prevRenderY, player.prevRenderZ);
        //}
        if (player.field_1645 % 400 == 0) {
            if (this.inv.slots[0] != null && this.inv.slots[0].itemId == AetherItems.ZanitePendant.id) {
                this.inv.slots[0].applyDamage(1, player);
                if (this.inv.slots[0].count < 1) {
                    this.inv.slots[0] = null;
                }
            }
            if (this.inv.slots[4] != null && this.inv.slots[4].itemId == AetherItems.ZaniteRing.id) {
                this.inv.slots[4].applyDamage(1, player);
                if (this.inv.slots[4].count < 1) {
                    this.inv.slots[4] = null;
                }
            }
            if (this.inv.slots[5] != null && this.inv.slots[5].itemId == AetherItems.ZaniteRing.id) {
                this.inv.slots[5].applyDamage(1, player);
                if (this.inv.slots[5].count < 1) {
                    this.inv.slots[5] = null;
                }
            }
            if (this.inv.slots[0] != null && this.inv.slots[0].itemId == AetherItems.IcePendant.id) {
                this.inv.slots[0].applyDamage(1, player);
                if (this.inv.slots[0].count < 1) {
                    this.inv.slots[0] = null;
                }
            }
            if (this.inv.slots[4] != null && this.inv.slots[4].itemId == AetherItems.IceRing.id) {
                this.inv.slots[4].applyDamage(1, player);
                if (this.inv.slots[4].count < 1) {
                    this.inv.slots[4] = null;
                }
            }
            if (this.inv.slots[5] != null && this.inv.slots[5].itemId == AetherItems.IceRing.id) {
                this.inv.slots[5].applyDamage(1, player);
                if (this.inv.slots[5].count < 1) {
                    this.inv.slots[5] = null;
                }
            }
        }
        if (player.level.difficulty == 0 && player.health >= 20 && player.health < this.maxHealth && player.field_1645 % 20 == 0) {
            player.addHealth(1);
        }
    }
	
}
