package com.gildedgames.aether.entity.mobs;

import com.gildedgames.aether.AetherMod;
import com.gildedgames.aether.entity.base.EntityDungeonMob;
import com.gildedgames.aether.registry.AetherBlocks;
import net.minecraft.entity.EntityBase;
import net.minecraft.entity.Living;
import net.minecraft.entity.player.PlayerBase;
import net.minecraft.level.Level;
import net.minecraft.util.io.CompoundTag;
import net.modificationstation.stationapi.api.registry.Identifier;

public class EntitySentry extends EntityDungeonMob
{
    public float field_100021_a;
    public float field_100020_b;
    private int jcount;
    public int size;
    public int counter;
    public int lostyou;
    public boolean active;

    public EntitySentry(final Level level)
    {
        super(level);
        this.texture = "aether:textures/entity/Sentry.png";
        this.size = 2;
        this.standingEyeHeight = 0.0f;
        this.movementSpeed = 1.0f;
        this.field_100021_a = 1.0f;
        this.field_100020_b = 1.0f;
        this.jcount = this.rand.nextInt(20) + 10;
        this.func_100019_e(this.size);
    }

    public EntitySentry(final Level world, final double x, final double y, final double z)
    {
        super(world);
        this.texture = "aether:textures/entity/Sentry.png";
        this.size = 2;
        this.standingEyeHeight = 0.0f;
        this.movementSpeed = 1.0f;
        this.field_100021_a = 1.0f;
        this.field_100020_b = 1.0f;
        this.jcount = this.rand.nextInt(20) + 10;
        this.func_100019_e(this.size);
        this.yaw = this.rand.nextInt(4) * 1.5707965f;
        this.setPosition(x, y, z);
    }

    public void func_100019_e(final int i)
    {
        this.health = 10;
        this.width = 0.85f;
        this.height = 0.85f;
        this.setPosition(this.x, this.y, this.z);
    }

    @Override
    public void writeCustomDataToTag(final CompoundTag tag)
    {
        super.writeCustomDataToTag(tag);
        tag.put("Size", this.size - 1);
        tag.put("LostYou", this.lostyou);
        tag.put("Counter", this.counter);
        tag.put("Active", this.active);
    }

    @Override
    public void readCustomDataFromTag(final CompoundTag tag)
    {
        super.readCustomDataFromTag(tag);
        this.size = tag.getInt("Size") + 1;
        this.lostyou = tag.getInt("LostYou");
        this.counter = tag.getInt("Counter");
        this.active = tag.getBoolean("Active");
    }

    @Override
    public void tick()
    {
        final boolean flag = this.onGround;
        super.tick();
        if (this.onGround && !flag)
        {
            this.level.playSound((EntityBase) this, "mob.slime", this.getSoundVolume(), ((this.rand.nextFloat() - this.rand.nextFloat()) * 0.2f + 1.0f) / 0.8f);
        }
        else if (!this.onGround && flag && this.entity != null)
        {
            this.velocityX *= 3.0;
            this.velocityZ *= 3.0;
        }
        if (this.entity != null && this.entity.removed)
        {
            this.entity = null;
        }
    }

    public void remove()
    {
        if (this.health <= 0 || this.removed)
        {
            super.remove();
        }
    }

    @Override
    public boolean damage(final EntityBase target, final int amount)
    {
        final boolean flag = super.damage(target, amount);
        if (flag && target instanceof Living)
        {
            this.active = true;
            this.lostyou = 0;
            this.entity = target;
            this.texture = "aether:textures/entity/SentryLit.png";
        }
        return flag;
    }

    public void shutdown()
    {
        this.counter = -64;
        this.active = false;
        this.entity = null;
        this.texture = "aether:textures/entity/Sentry.png";
        this.setTarget(null);
        this.field_1060 = 0.0f;
        this.field_1029 = 0.0f;
        this.jumping = false;
        this.velocityX = 0.0;
        this.velocityZ = 0.0;
    }

    public void method_1353(final EntityBase entity)
    {
        if (!this.removed && this.entity != null && entity != null && this.entity == entity)
        {
            this.level.createExplosion(this, this.x, this.y, this.z, 0.1f);
            entity.damage(null, 2);
            if (entity instanceof Living entityliving)
            {
                double d;
                double d2;
                for (d = entityliving.x - this.x, d2 = entityliving.z - this.z; d * d + d2 * d2 < 1.0E-4; d = (Math.random() - Math.random()) * 0.01, d2 = (Math.random() - Math.random()) * 0.01)
                {
                }
                entityliving.method_925(this, 5, -d, -d2);
                entityliving.velocityX *= 4.0;
                entityliving.velocityY *= 4.0;
                entityliving.velocityZ *= 4.0;
            }
            final float f = 0.01745329f;
            for (int i = 0; i < 40; ++i)
            {
                final double d3 = (float) this.x + this.rand.nextFloat() * 0.25f;
                final double d4 = (float) this.y + 0.5f;
                final double d5 = (float) this.z + this.rand.nextFloat() * 0.25f;
                final float f2 = this.rand.nextFloat() * 360.0f;
                this.level.addParticle("explode", d3, d4, d5, -Math.sin(f * f2) * 0.75, 0.125, Math.cos(f * f2) * 0.75);
            }
            this.health = 0;
            this.remove();
        }
    }

    @Override
    protected void tickHandSwing()
    {
        final PlayerBase entityplayer = this.level.getClosestPlayerTo(this, 8.0);
        if (!this.active && this.counter >= 8)
        {
            if (entityplayer != null && this.method_928(entityplayer))
            {
                this.method_924(entityplayer, 10.0f, 10.0f);
                this.entity = entityplayer;
                this.active = true;
                this.lostyou = 0;
                this.texture = "aether:textures/entity/SentryLit.png";
            }
            this.counter = 0;
        }
        else if (this.active && this.counter >= 8)
        {
            if (this.entity == null)
            {
                if (entityplayer != null && this.method_928(entityplayer))
                {
                    this.entity = entityplayer;
                    this.active = true;
                    this.lostyou = 0;
                }
                else
                {
                    ++this.lostyou;
                    if (this.lostyou >= 4)
                    {
                        this.shutdown();
                    }
                }
            }
            else if (!this.method_928(this.entity) || this.distanceTo(this.entity) >= 16.0f)
            {
                ++this.lostyou;
                if (this.lostyou >= 4)
                {
                    this.shutdown();
                }
            }
            else
            {
                this.lostyou = 0;
            }
            this.counter = 0;
        }
        else
        {
            ++this.counter;
        }
        if (!this.active)
        {
            return;
        }
        if (this.entity != null)
        {
            this.method_924(this.entity, 10.0f, 10.0f);
        }
        if (this.onGround && this.jcount-- <= 0)
        {
            this.jcount = this.rand.nextInt(20) + 10;
            this.jumping = true;
            this.field_1060 = 0.5f - this.rand.nextFloat();
            this.field_1029 = 1.0f;
            this.level.playSound(this, "mob.slime", this.getSoundVolume(), ((this.rand.nextFloat() - this.rand.nextFloat()) * 0.2f + 1.0f) * 0.8f);
            if (this.entity != null)
            {
                this.jcount /= 2;
                this.field_1029 = 1.0f;
            }
        }
        else
        {
            this.jumping = false;
            if (this.onGround)
            {
                final float n = 0.0f;
                this.field_1029 = n;
                this.field_1060 = n;
            }
        }
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
    public boolean canSpawn()
    {
        return super.canSpawn();
    }

    @Override
    protected float getSoundVolume()
    {
        return 0.6f;
    }

    @Override
    protected int getMobDrops()
    {
        if (this.rand.nextInt(5) == 0)
        {
            return AetherBlocks.LIGHT_DUNGEON_STONE.id;
        }
        return AetherBlocks.DUNGEON_STONE.id;
    }

    @Override
    public Identifier getHandlerIdentifier()
    {
        return AetherMod.MODID.id("Sentry");
    }
}
