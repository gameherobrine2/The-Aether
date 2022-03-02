package com.gildedgames.aether;

import com.gildedgames.aether.entity.boss.EntitySlider;
import com.gildedgames.aether.inventory.InventoryAether;
import com.gildedgames.aether.mixin.MinecraftClientAccessor;

import net.minecraft.achievement.Achievement;
import net.minecraft.entity.EntityBase;
import net.minecraft.entity.player.PlayerBase;
import net.modificationstation.stationapi.api.mod.entrypoint.Entrypoint;
import net.modificationstation.stationapi.api.mod.entrypoint.EventBusPolicy;
import net.modificationstation.stationapi.api.registry.Identifier;
import net.modificationstation.stationapi.api.registry.ModID;
import net.modificationstation.stationapi.api.util.Null;

@Entrypoint(eventBus = @EventBusPolicy(registerInstance = false, registerStatic = false))
public class Aether {
	
    @Entrypoint.ModID
    public static final ModID MODID = Null.get();
    public static Identifier of(String id) {
        return Identifier.of(MODID, id);
    }
    
    public static InventoryAether inv;
    public static int maxHealth = 20;
	public static EntityBase currentBoss;
	public static void giveAchievement(final Achievement a) {
        giveAchievement(a, MinecraftClientAccessor.getMCinstance().player);
    }
    public static void giveAchievement(final Achievement a, final PlayerBase p) {
        if (MinecraftClientAccessor.getMCinstance().statFileWriter.isAchievementUnlocked(a)) {
            return;
        }
        MinecraftClientAccessor.getMCinstance().soundHelper.playSound("aether.sound.other.achievement.achievementGen", 1.0f, 1.0f); //TODO: sounds
        p.incrementStat(a);
    }
	public static int getCurrentDimension() {
        final PlayerBase player = MinecraftClientAccessor.getMCinstance().player;
        if (player == null) {
            return 0;
        }
        return player.dimensionId;
    }
}
