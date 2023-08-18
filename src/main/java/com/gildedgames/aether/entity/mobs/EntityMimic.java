package com.gildedgames.aether.entity.mobs;

import com.gildedgames.aether.AetherMod;
import com.gildedgames.aether.entity.base.EntityDungeonMob;
import net.minecraft.block.BlockBase;
import net.minecraft.entity.EntityBase;
import net.minecraft.entity.player.PlayerBase;
import net.minecraft.level.Level;
import net.modificationstation.stationapi.api.registry.Identifier;

public class EntityMimic extends EntityDungeonMob
{
    public float mouth;
    public float legs;
    private float legsDirection;

    public EntityMimic(final Level level)
    {
        super(level);
        this.legsDirection = 1.0f;
        this.texture = "aether:textures/entity/Mimic.png";
        this.standingEyeHeight = 0.0f;
        this.setSize(1.0f, 2.0f);
        this.health = 40;
        this.attackStrength = 5;
        this.entity = level.getClosestPlayerTo(this, 64.0);
    }

    @Override
    public void tick()
    {
        super.tick();
        this.mouth = (float) (Math.cos((double) (this.field_1645 / 10.0f * 3.1415927f)) + 1.0) * 0.6f;
        this.legs *= 0.9f;
        if (this.velocityX > 0.001 || this.velocityX < -0.001 || this.velocityZ > 0.001 || this.velocityZ < -0.001)
        {
            this.legs += this.legsDirection * 0.2f;
            if (this.legs > 1.0f)
            {
                this.legsDirection = -1.0f;
            }
            if (this.legs < -1.0f)
            {
                this.legsDirection = 1.0f;
            }
        }
    }

    public void method_1353(final EntityBase entity)
    {
        if (!this.removed && entity != null)
        {
            entity.damage(this, 4);
        }
    }

    @Override
    public boolean damage(final EntityBase target, final int amount)
    {
        if (target instanceof PlayerBase)
        {
            this.method_924(target, 10.0f, 10.0f);
            this.entity = target;
        }
        return super.damage(target, amount);
    }

    @Override
    protected String getHurtSound()
    {
        return "mob.slime";
    }

    @Override
    protected String getDeathSound()
    {
        return "mob.slime";
    }

    @Override
    protected float getSoundVolume()
    {
        return 0.6f;
    }

    @Override
    protected int getMobDrops()
    {
        return BlockBase.CHEST.id;
    }

    @Override
    public Identifier getHandlerIdentifier()
    {
        return AetherMod.MODID.id("Mimic");
    }
}
