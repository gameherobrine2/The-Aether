package com.gildedgames.aether.player;

import com.gildedgames.aether.mixin.access.LevelAccessor;
import com.gildedgames.aether.mixin.data.DimensionFileAccessor;
import net.minecraft.entity.player.PlayerBase;
import net.minecraft.level.dimension.DimensionFile;
import net.minecraft.util.io.CompoundTag;
import net.minecraft.util.io.NBTIO;

import java.io.*;

public class AetherPlayerHandler implements net.modificationstation.stationapi.api.entity.player.PlayerHandler{
	private PlayerBase player;

	public AetherPlayerHandler(PlayerBase playerBase) {
		player = playerBase;
	}
    public int maxHealth = 20;
    public boolean visible = true;
	public void increaseMaxHP(final int i) {
        if (this.maxHealth <= 40 - i) {
            this.maxHealth += i;
            final PlayerBase player = (PlayerBase) this.player;
            player.health += i;
        }
    }
	@Override
	public boolean writeEntityBaseToNBT(CompoundTag tag) {
		try {
    		final CompoundTag customData = new CompoundTag();
    		customData.put("MaxHealth", (byte)this.maxHealth);
            customData.put("visible", visible);
        
        	final File file = new File(((DimensionFileAccessor)((DimensionFile)((LevelAccessor)player.level).getDimData())).getSaveFolder(), "aether.dat");
            NBTIO.writeGzipped(customData, (OutputStream)new FileOutputStream(file));
        }
        catch (Exception ioexception) {
            //throw new RuntimeException("Failed to create player data");
        }
		return false;
		
    }
	@Override
	public boolean heal(final int i) {
        if (this.player.health <= 0) {
            return false;
        }
        final PlayerBase player = (PlayerBase) this.player;
        player.health += i;
        if (this.player.health > this.maxHealth) {
            this.player.health = this.maxHealth;
        }
        this.player.field_1613 = this.player.field_1009 / 2;
        return true;
    }
	@Override
	public boolean readEntityBaseFromNBT(CompoundTag tag) {
		CompoundTag customData = new CompoundTag();
        try {
        	final File file = new File(((DimensionFileAccessor)((DimensionFile)((LevelAccessor)player.level).getDimData())).getSaveFolder(), "aether.dat");
            customData = NBTIO.readGzipped((InputStream)new FileInputStream(file));
            
            this.maxHealth = customData.getByte("MaxHealth");
            this.visible = customData.getBoolean("visible");

            if (this.maxHealth < 20) {
                this.maxHealth = 20;
            }
        }
        catch (Exception ioexception) {
            System.out.println("Failed to read player data. Making new");
            this.maxHealth = 20;
        }
        return false;
    }
	private void readCustomData() {
    	CompoundTag customData = new CompoundTag();
        try {
        	final File file = new File(((DimensionFileAccessor)((DimensionFile)((LevelAccessor)player.level).getDimData())).getSaveFolder(), "aether.dat");
            customData = NBTIO.readGzipped((InputStream)new FileInputStream(file));
            
            this.maxHealth = customData.getByte("MaxHealth");
            this.visible = customData.getBoolean("visible");
            if (this.maxHealth < 20) {
                this.maxHealth = 20;
            }
        }
        catch (Exception ioexception) {
            System.out.println("Failed to read player data. Making new");
            this.maxHealth = 20;
        }
    }
	private void writeCustomData() {
        final CompoundTag customData = new CompoundTag();
        PlayerBase player = PlayerBase.class.cast(this);
        customData.put("MaxHealth", (byte)this.maxHealth);
        customData.put("visible", visible);

        try {
            final File file = new File(((DimensionFileAccessor)((DimensionFile)((LevelAccessor)player.level).getDimData())).getSaveFolder(), "aether.dat");
            NBTIO.writeGzipped(customData, (OutputStream)new FileOutputStream(file));
        }
        catch (IOException ioexception) {
            ioexception.printStackTrace();
            throw new RuntimeException("Failed to create player data");
        }
    }
	@Override
	public boolean onLivingUpdate() {
        //if (MainMenu.mmactive) {
        //    player.setPosition(player.prevRenderX, player.prevRenderY, player.prevRenderZ);
        //}
        /*
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
        }*/
		return false;
	}
}
