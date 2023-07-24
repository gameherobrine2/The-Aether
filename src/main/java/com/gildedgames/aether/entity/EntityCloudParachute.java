package com.gildedgames.aether.entity;

import com.gildedgames.aether.AetherMod;
import com.gildedgames.aether.mixin.access.EntityBaseAccessor;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityBase;
import net.minecraft.entity.Living;
import net.minecraft.entity.player.PlayerBase;
import net.minecraft.level.Level;
import net.minecraft.util.io.CompoundTag;
import net.minecraft.util.maths.Box;
import net.modificationstation.stationapi.api.registry.Identifier;
import net.modificationstation.stationapi.api.server.entity.MobSpawnDataProvider;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EntityCloudParachute extends EntityBase implements MobSpawnDataProvider
{
    private Living entityUsing;
    private boolean justServerSpawned;
    private static Map<Living, EntityCloudParachute> cloudMap;
    private static final float MAX_FALL_SPEED = 0.25f;
    private static final double ANIM_RADIUS = 0.75;
    public boolean gold;

    public EntityCloudParachute(final Level level)
    {
        super(level);
        this.justServerSpawned = false;
        this.setSize(1.0f, 1.0f);
    }

    public EntityCloudParachute(final Level world, final double d, final double d1, final double d2)
    {
        this(world);
        this.method_1338(d, d1, d2, this.yaw, this.pitch);
        this.justServerSpawned = true;
    }

    public EntityCloudParachute(final Level world, final Living entityliving, final boolean bool)
    {
        this(world);
        if (entityliving == null)
        {
            throw new IllegalArgumentException("entityliving cannot be null.");
        }
        this.entityUsing = entityliving;
        EntityCloudParachute.cloudMap.put(entityliving, this);
        this.prevX = this.x;
        this.prevY = this.y;
        this.prevZ = this.z;
        this.moveToEntityUsing();
        this.gold = bool;
    }

    public static EntityCloudParachute getCloudBelongingToEntity(final Living entity)
    {
        return (EntityCloudParachute) EntityCloudParachute.cloudMap.get(entity);
    }

    public Level getWorld()
    {
        return this.level;
    }

    public void die()
    {
        if (this.entityUsing != null)
        {
            if (EntityCloudParachute.cloudMap.containsKey(this.entityUsing))
            {
                EntityCloudParachute.cloudMap.remove(this.entityUsing);
            }
            for (int i = 0; i < 32; ++i)
            {
                doCloudSmoke(this.level, this.entityUsing);
            }
            this.level.playSound((EntityBase) this.entityUsing, "cloud", 1.0f, 1.0f / (this.rand.nextFloat() * 0.1f + 0.95f));
        }
        this.entityUsing = null;
        this.removed = true;
    }

    public static void doCloudSmoke(final Level world, final Living entityliving)
    {
        final double x = entityliving.x + ((EntityBaseAccessor) entityliving).getRand().nextDouble() * 0.75 * 2.0 - 0.75;
        final double y = entityliving.boundingBox.minY - 0.5 + ((EntityBaseAccessor) entityliving).getRand().nextDouble() * 0.75 * 2.0 - 0.75;
        final double z = entityliving.z + ((EntityBaseAccessor) entityliving).getRand().nextDouble() * 0.75 * 2.0 - 0.75;
        // todo: particle MinecraftClientAccessor.getMCinstance().particleManager.addParticle(new EntityCloudSmokeFX(world, x, y, z, 0.0, 0.0, 0.0, 2.5f, 1.0f, 1.0f, 1.0f));
    }

    public static boolean entityHasRoomForCloud(final Level world, final Living entityliving)
    {
        final Box boundingBox = Box.create(entityliving.x - 0.5, entityliving.boundingBox.minY - 1.0, entityliving.z - 0.5, entityliving.x + 0.5, entityliving.boundingBox.minY, entityliving.z + 0.5);
        return world.method_190(entityliving, boundingBox).size() == 0 && !world.method_207(boundingBox, Material.WATER);
    }

    @Override
    protected void initDataTracker()
    {
    }

    @Override
    public boolean shouldRenderAtDistance(final double d)
    {
        if (this.entityUsing != null)
        {
            return this.entityUsing.shouldRenderAtDistance(d);
        }
        return super.shouldRenderAtDistance(d);
    }

    @Override
    public boolean method_1356()
    {
        return true;
    }

    @Override
    public Box method_1381()
    {
        return this.boundingBox;
    }

    @Override
    public void tick()
    {
        if (this.removed)
        {
            return;
        }
        if (this.entityUsing == null)
        {
            if (this.level.isServerSide && !this.justServerSpawned)
            {
                this.die();
                return;
            }
            this.justServerSpawned = false;
            this.entityUsing = this.findUser();
            if (this.entityUsing == null)
            {
                this.die();
                return;
            }
            EntityCloudParachute.cloudMap.put(this.entityUsing, this);
        }
        if (this.entityUsing.velocityY < -0.25)
        {
            this.entityUsing.velocityY = -0.25;
        }
        ((EntityBaseAccessor) this.entityUsing).setFallDistance(0.0f);
        doCloudSmoke(this.level, this.entityUsing);
        this.moveToEntityUsing();
    }

    private Living findUser()
    {
        final List entities = this.level.getEntities(Living.class, this.boundingBox.method_92().method_102(0.0, 1.0, 0.0));
        double minDeltaSquared = -1.0;
        Living entityliving = null;
        for (int i = 0; i < entities.size(); ++i)
        {
            final Living entitylivingtemp = (Living) entities.get(i);
            if (entitylivingtemp.isAlive())
            {
                final double xDelta = this.x - entitylivingtemp.x;
                final double yDelta = this.boundingBox.maxY - entitylivingtemp.boundingBox.minY;
                final double zDelta = this.z - entitylivingtemp.z;
                final double deltaSquared = xDelta * xDelta + yDelta * yDelta + zDelta * zDelta;
                if (minDeltaSquared == -1.0 || deltaSquared < minDeltaSquared)
                {
                    minDeltaSquared = deltaSquared;
                    entityliving = entitylivingtemp;
                }
            }
        }
        return entityliving;
    }

    private void moveToEntityUsing()
    {
        this.method_1338(this.entityUsing.x, this.entityUsing.boundingBox.minY - this.height / 2.0f, this.entityUsing.z, this.entityUsing.yaw, this.entityUsing.pitch);
        this.velocityX = this.entityUsing.velocityX;
        this.velocityY = this.entityUsing.velocityY;
        this.velocityZ = this.entityUsing.velocityZ;
        this.yaw = this.entityUsing.yaw;
        if (this.isCollided())
        {
            this.die();
        }
    }

    private boolean isCollided()
    {
        return this.level.method_190(this, this.boundingBox).size() > 0 || this.level.method_207(this.boundingBox, Material.WATER);
    }

    @Override
    public void onPlayerCollision(final PlayerBase entityplayer)
    {
    }

    @Override
    protected void readCustomDataFromTag(final CompoundTag tag)
    {
    }

    @Override
    protected void writeCustomDataToTag(final CompoundTag tag)
    {
    }

    static
    {
        EntityCloudParachute.cloudMap = new HashMap<Living, EntityCloudParachute>();
    }

    @Override
    public Identifier getHandlerIdentifier()
    {
        return AetherMod.MODID.id("entity_parachute");
    }
}
