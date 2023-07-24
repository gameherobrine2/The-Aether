package com.gildedgames.aether.entity.base;

import net.minecraft.entity.EntityBase;
import net.minecraft.entity.WalkingBase;
import net.minecraft.entity.monster.MonsterEntityType;
import net.minecraft.entity.player.PlayerBase;
import net.minecraft.level.Level;
import net.minecraft.level.LightType;
import net.minecraft.util.io.CompoundTag;
import net.minecraft.util.maths.MathHelper;
import net.modificationstation.stationapi.api.server.entity.MobSpawnDataProvider;

public abstract class EntityDungeonMob extends WalkingBase implements MonsterEntityType, MobSpawnDataProvider
{
    protected int attackStrength;

    public EntityDungeonMob(final Level level)
    {
        super(level);
        this.attackStrength = 2;
        this.health = 20;
    }

    @Override
    public void updateDespawnCounter()
    {
        final float f = this.getBrightnessAtEyes(1.0f);
        if (f > 0.5f)
        {
            this.despawnCounter += 2;
        }
        super.updateDespawnCounter();
    }

    @Override
    protected EntityBase getAttackTarget()
    {
        final PlayerBase entityplayer = this.level.getClosestPlayerTo(this, 16.0);
        if (entityplayer != null && this.method_928(entityplayer))
        {
            return entityplayer;
        }
        return null;
    }

    @Override
    public boolean damage(final EntityBase target, final int amount)
    {
        if (!super.damage(target, amount))
        {
            return false;
        }
        if (this.passenger == target || this.vehicle == target)
        {
            return true;
        }
        if (target != this)
        {
            this.entity = target;
        }
        return true;
    }

    @Override
    protected void tryAttack(final EntityBase target, final float f)
    {
        if (this.attackTime <= 0 && f < 2.0f && target.boundingBox.maxY > this.boundingBox.minY && target.boundingBox.minY < this.boundingBox.maxY)
        {
            this.attackTime = 20;
            target.damage(this, this.attackStrength);
        }
    }

    @Override
    protected float getPathfindingFavour(final int x, final int y, final int z)
    {
        return 0.5f - this.level.getBrightness(x, y, z);
    }

    @Override
    public void writeCustomDataToTag(final CompoundTag tag)
    {
        super.writeCustomDataToTag(tag);
    }

    @Override
    public void readCustomDataFromTag(final CompoundTag tag)
    {
        super.readCustomDataFromTag(tag);
    }

    @Override
    public boolean canSpawn()
    {
        final int i = MathHelper.floor(this.x);
        final int j = MathHelper.floor(this.boundingBox.minY);
        final int k = MathHelper.floor(this.z);
        if (this.level.method_164(LightType.field_2757, i, j, k) > this.rand.nextInt(32))
        {
            return false;
        }
        int l = this.level.placeTile(i, j, k);
        if (this.level.isThundering())
        {
            final int i2 = this.level.field_202;
            this.level.field_202 = 10;
            l = this.level.placeTile(i, j, k);
            this.level.field_202 = i2;
        }
        return l <= this.rand.nextInt(8) && super.canSpawn();
    }
}
