package com.gildedgames.aether.player;

import com.gildedgames.aether.AetherMod;
import com.gildedgames.aether.effect.AetherPoison;
import com.gildedgames.aether.entity.animal.EntityAerbunny;
import com.gildedgames.aether.item.accessory.GoldenFeather;
import com.gildedgames.aether.level.dimension.BareAetherTravelAgent;
import com.gildedgames.aether.registry.AetherAchievements;
import com.gildedgames.aether.registry.AetherItems;
import net.minecraft.entity.EntityBase;
import net.minecraft.entity.player.PlayerBase;
import net.minecraft.item.ItemInstance;
import net.minecraft.util.io.CompoundTag;
import net.modificationstation.stationapi.api.entity.player.PlayerHandler;
import net.modificationstation.stationapi.api.world.dimension.DimensionHelper;
import net.modificationstation.stationapi.api.world.dimension.VanillaDimensions;
import net.modificationstation.stationapi.impl.entity.player.PlayerAPI;

public class AetherPlayerHandler implements PlayerHandler
{
    private PlayerBase player;

    public AetherPlayerHandler(PlayerBase playerBase)
    {
        player = playerBase;
    }

    public int maxHealth = 20;
    public boolean visible = true;
    public boolean visited_aether = false;
    public AetherPoison poison = new AetherPoison();

    public boolean increaseMaxHP(final int i)
    {
        if (this.maxHealth <= 40 - i)
        {
            this.maxHealth += i;
            final PlayerBase player = (PlayerBase) this.player;
            player.health += i;
            return true;
        }
        else
        {
            return false;
        }
    }

    @Override
    public boolean writeEntityBaseToNBT(CompoundTag tag)
    {
        tag.put("MaxHealth", (byte) this.maxHealth);
        tag.put("visited_aether", visited_aether);
        tag.put("visible", visible);
        return false;
    }

    @Override
    public boolean heal(final int i)
    {
        if (this.player.health <= 0)
        {
            return false;
        }
        final PlayerBase player = (PlayerBase) this.player;
        player.health += i;
        if (this.player.health > this.maxHealth)
        {
            this.player.health = this.maxHealth;
        }
        this.player.field_1613 = this.player.field_1009 / 2;
        return true;
    }

    @Override
    public boolean onLivingUpdate()
    {
        if (player.dimensionId == 2) //todo: maybe not hardcode 2?
        {
            if (player.y < 0)
            {
                player.setPosition(player.x, 150, player.z);
                DimensionHelper.switchDimension(player, VanillaDimensions.OVERWORLD, 1, new BareAetherTravelAgent());
            }
        }

        // todo: move this into aerbunny somehow
        if (player.passenger instanceof EntityAerbunny && player.velocityY < 0.f) GoldenFeather.slowFall(player);

        if (player.dimensionId == 2)
        {
            if (!visited_aether)
            {
                visited_aether = true;
                AetherMod.giveAchievement(AetherAchievements.enterAether, player);
                player.inventory.addStack(new ItemInstance(AetherItems.LoreBook, 1, 2));
                player.inventory.addStack(new ItemInstance(AetherItems.CloudParachute, 1));
                player.level.playSound((EntityBase) player, "random.pop", 0.2f, 1.0f);
            }
        }

        return PlayerHandler.super.onLivingUpdate();

        // todo: reimplement
        // AetherItems.tick(minecraft);
        //AetherPoison.tickRender(minecraft);
    }

    @Override
    public boolean readEntityBaseFromNBT(CompoundTag tag)
    {
        maxHealth = tag.getByte("MaxHealth");
        visible = tag.getBoolean("visible");
        visited_aether = tag.getBoolean("visited_aether");
        return false;
    }

    public static AetherPlayerHandler get(PlayerBase player)
    {
        return (AetherPlayerHandler) PlayerAPI.getPlayerHandler(player, AetherPlayerHandler.class);
    }
}
