package com.gildedgames.aether;

import com.gildedgames.aether.player.AetherPlayerHandler;
import net.minecraft.achievement.Achievement;
import net.minecraft.entity.EntityBase;
import net.minecraft.entity.player.PlayerBase;
import net.modificationstation.stationapi.api.mod.entrypoint.Entrypoint;
import net.modificationstation.stationapi.api.mod.entrypoint.EventBusPolicy;
import net.modificationstation.stationapi.api.registry.Identifier;
import net.modificationstation.stationapi.api.registry.ModID;
import net.modificationstation.stationapi.api.util.Null;
import net.modificationstation.stationapi.impl.entity.player.PlayerAPI;

@Entrypoint(eventBus = @EventBusPolicy(registerInstance = false, registerStatic = false))
public class AetherMod
{

    @Entrypoint.ModID
    public static final ModID MODID = Null.get();

    public static Identifier of(String id)
    {
        return Identifier.of(MODID, id);
    }

    public static AetherPlayerHandler getPlayerHandler(PlayerBase player)
    {
        return (AetherPlayerHandler) PlayerAPI.getPlayerHandler(player, com.gildedgames.aether.player.AetherPlayerHandler.class);
    }

    public static EntityBase currentBoss;

    public static void giveAchievement(final Achievement a)
    {
        //giveAchievement(a, MinecraftClientAccessor.getMCinstance().player);
    }

    public static void giveAchievement(final Achievement a, final PlayerBase p)
    {
        /* todo: play sound
        if (MinecraftClientAccessor.getMCinstance().statFileWriter.isAchievementUnlocked(a)) {
            return;
        }
        MinecraftClientAccessor.getMCinstance().soundHelper.playSound(Aether.MODID+":aether.sound.other.achievement.achievementgen", 1.0f, 1.0f);
        */
        p.incrementStat(a);
    }
}
