package com.gildedgames.aether;

import com.gildedgames.aether.entity.boss.EntitySlider;
import com.gildedgames.aether.inventory.InventoryAether;
import com.gildedgames.aether.mixin.MinecraftClientAccessor;
import com.gildedgames.aether.player.AetherPlayerHandler;
import com.gildedgames.aether.registry.AetherItems;

import net.minecraft.achievement.Achievement;
import net.minecraft.entity.EntityBase;
import net.minecraft.entity.player.PlayerBase;
import net.minecraft.item.ItemInstance;
import net.modificationstation.stationapi.api.mod.entrypoint.Entrypoint;
import net.modificationstation.stationapi.api.mod.entrypoint.EventBusPolicy;
import net.modificationstation.stationapi.api.registry.Identifier;
import net.modificationstation.stationapi.api.registry.ModID;
import net.modificationstation.stationapi.api.util.Null;
import net.modificationstation.stationapi.impl.entity.player.PlayerAPI;

@Entrypoint(eventBus = @EventBusPolicy(registerInstance = false, registerStatic = false))
public class Aether {
	
    @Entrypoint.ModID
    public static final ModID MODID = Null.get();
    public static Identifier of(String id) {
        return Identifier.of(MODID, id);
    }
    
    public static AetherPlayerHandler getPlayerHandler() {
    	return getPlayerHandler((PlayerBase)MinecraftClientAccessor.getMCinstance().player);
    }
    public static AetherPlayerHandler getPlayerHandler(PlayerBase player) {
    	return (AetherPlayerHandler) PlayerAPI.getPlayerHandler(player, com.gildedgames.aether.player.AetherPlayerHandler.class);
    }
	public static EntityBase currentBoss;
	public static void giveAchievement(final Achievement a) { 
        giveAchievement(a, MinecraftClientAccessor.getMCinstance().player);
    }
    public static void giveAchievement(final Achievement a, final PlayerBase p) {
        if (MinecraftClientAccessor.getMCinstance().statFileWriter.isAchievementUnlocked(a)) {
            return;
        }
        MinecraftClientAccessor.getMCinstance().soundHelper.playSound(Aether.MODID+":aether.sound.other.achievement.achievementgen", 1.0f, 1.0f); //TODO: sounds
        p.incrementStat(a);
    }
    public static boolean invisible(final PlayerBase player) {
        return (!player.handSwinging && getPlayerHandler(player).inv.slots[1] != null && getPlayerHandler(player).inv.slots[1].itemId == AetherItems.InvisibilityCloak.id);
    }
	public static int getCurrentDimension() {
        final PlayerBase player = MinecraftClientAccessor.getMCinstance().player;
        if (player == null) {
            return 0;
        }
        return player.dimensionId;
    }
	
	public static boolean equippedSkyrootSword() {
        final ItemInstance itemstack = MinecraftClientAccessor.getMCinstance().player.inventory.getHeldItem();
        return itemstack != null && itemstack.itemId == AetherItems.SwordSkyroot.id;
    }
    
    public static boolean equippedSkyrootPick() {
        final ItemInstance itemstack = MinecraftClientAccessor.getMCinstance().player.inventory.getHeldItem();
        return itemstack != null && itemstack.itemId == AetherItems.PickSkyroot.id;
    }
    
    public static boolean equippedSkyrootShovel() {
        final ItemInstance itemstack = MinecraftClientAccessor.getMCinstance().player.inventory.getHeldItem();
        return itemstack != null && itemstack.itemId == AetherItems.ShovelSkyroot.id;
    }
    
    public static boolean equippedSkyrootAxe() {
        final ItemInstance itemstack = MinecraftClientAccessor.getMCinstance().player.inventory.getHeldItem();
        return itemstack != null && itemstack.itemId == AetherItems.AxeSkyroot.id;
    }
    
	public static boolean mmactive;
    public static int musicId = -1;
    public static boolean loadingWorld;
    public static boolean renderOption;
    public static boolean themeOption;
    
    public static int raritySwet;
    public static int rarityAechorPlant;
    public static int rarityCockatrice;
    public static int rarityAerwhale;
    public static int rarityZephyr;
    public static int raritySheepuff;
    public static int rarityPhyg;
    public static int rarityMoa;
    public static int rarityFlyingCow;
    public static int rarityWhirlwind;
    public static int rarityAerbunny;
    static {
        Aether.raritySwet = 8;
        Aether.rarityAechorPlant = 8;
        Aether.rarityCockatrice = 3;
        Aether.rarityAerwhale = 8;
        Aether.rarityZephyr = 5;
        Aether.raritySheepuff = 10;
        Aether.rarityPhyg = 12;
        Aether.rarityMoa = 10;
        Aether.rarityFlyingCow = 10;
        Aether.rarityWhirlwind = 8;
        Aether.rarityAerbunny = 11;
   }
}
