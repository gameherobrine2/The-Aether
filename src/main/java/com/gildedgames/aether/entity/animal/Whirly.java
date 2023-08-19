package com.gildedgames.aether.entity.animal;

import com.gildedgames.aether.AetherMod;
import com.gildedgames.aether.entity.base.EntityAetherAnimal;
import com.gildedgames.aether.mixin.access.EntityBaseAccessor;
import com.gildedgames.aether.mixin.access.LevelAccessor;
import com.gildedgames.aether.mixin.data.DimensionFileAccessor;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.block.BlockBase;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.ParticleManager;
import net.minecraft.client.render.particle.Explosion;
import net.minecraft.client.render.particle.Smoke;
import net.minecraft.entity.EntityBase;
import net.minecraft.entity.ParticleBase;
import net.minecraft.entity.monster.Creeper;
import net.minecraft.entity.player.PlayerBase;
import net.minecraft.item.ItemBase;
import net.minecraft.level.Level;
import net.minecraft.level.dimension.DimensionData;
import net.minecraft.level.dimension.DimensionFile;
import net.minecraft.util.io.CompoundTag;
import net.minecraft.util.maths.MathHelper;
import net.modificationstation.stationapi.api.registry.Identifier;
import net.modificationstation.stationapi.api.util.SideUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Whirly extends EntityAetherAnimal
{
    public int entcount;
    public int Life;
    public List fluffies;
    public ParticleManager Enty;
    public static final float pie = 3.141593f;
    public static final float pia = 0.01745329f;
    public float Angle;
    public float Speed;
    public float Curve;
    public boolean evil;

    public Whirly(final Level level)
    {
        super(level);
        System.out.println("whirly");
        this.entcount = 0;
        this.setSize(0.6f, 0.8f);
        this.setPosition(this.x, this.y, this.z);
        this.movementSpeed = 0.6f;
        this.Angle = this.rand.nextFloat() * 360.0f;
        this.Speed = this.rand.nextFloat() * 0.025f + 0.025f;
        this.Curve = (this.rand.nextFloat() - this.rand.nextFloat()) * 0.1f;
        this.Life = this.rand.nextInt(512) + 512;
        if (this.rand.nextInt(10) == 0 && !this.shouldStopEvil())
        {
            this.evil = true;
            this.Life /= 2;
        }
        this.fluffies = (List) new ArrayList();
        if (FabricLoader.getInstance().getGameInstance() instanceof Minecraft mc)
        {
            this.Enty = mc.particleManager;
        }
    }

    public boolean canClimb()
    {
        return false;
    }

    public boolean shouldStopEvil()
    {
        if (!(((LevelAccessor) this.level).getDimData() instanceof DimensionFile))
        {
            return false;
        }
        DimensionData dd = (((LevelAccessor) this.level).getDimData()); //i hate casts
        final File file = new File(((DimensionFileAccessor) dd).getSaveFolder(), "stopevil.txt");
        return file.exists();
    }

    public void tickHandSwing()
    {
        boolean flag = false;
        if (this.evil)
        {
            final PlayerBase entityplayer = (PlayerBase) this.getPlayer();
            if (entityplayer != null && entityplayer.onGround)
            {
                this.entity = entityplayer;
                flag = true;
            }
        }
        if (this.entity == null)
        {
            this.velocityX = Math.cos((double) (0.01745329f * this.Angle)) * this.Speed;
            this.velocityZ = -Math.sin((double) (0.01745329f * this.Angle)) * this.Speed;
            this.Angle += this.Curve;
        }
        else
        {
            super.tickHandSwing();
        }
        final List list = this.level.getEntities(this, this.boundingBox.expand(2.5, 2.5, 2.5));
        if (this.Life-- <= 0 || this.method_1393())
        {
            this.remove();
        }
        if (this.getPlayer() != null)
        {
            ++this.entcount;
        }
        if (this.entcount >= 128)
        {
            if (this.evil && this.entity != null)
            {
                final Creeper entitycreeper = new Creeper(this.level);
                entitycreeper.setPositionAndAngles(this.x, this.y - 0.75, this.z, this.rand.nextFloat() * 360.0f, 0.0f);
                entitycreeper.velocityX = (this.rand.nextFloat() - this.rand.nextFloat()) * 0.125;
                entitycreeper.velocityZ = (this.rand.nextFloat() - this.rand.nextFloat()) * 0.125;
                this.level.spawnEntity(entitycreeper);
                this.entcount = 0;
            }
            else
            {
                final int i = this.loot();
                if (i != 0)
                {
                    this.dropItem(i, 1);
                    this.entcount = 0;
                }
            }
        }
        if (!flag && this.entity == null)
        {
            for (int j = 0; j < 2; ++j)
            {
                final double d1 = (float) this.x + this.rand.nextFloat() * 0.25f;
                final double d2 = (float) this.y + this.height + 0.125f;
                final double d3 = (float) this.z + this.rand.nextFloat() * 0.25f;
                final float f = this.rand.nextFloat() * 360.0f;
                final Explosion entityexplodefx = new Explosion(this.level, -Math.sin((double) (0.01745329f * f)) * 0.75, d2 - 0.25, Math.cos((double) (0.01745329f * f)) * 0.75, d1, 0.125, d3);
                this.Enty.addParticle(entityexplodefx);
                this.fluffies.add(entityexplodefx);
                entityexplodefx.renderDistanceMultiplier = 10.0;
                entityexplodefx.field_1642 = true;
                ((EntityBaseAccessor) entityexplodefx).setWidth(0.25f);
                ((EntityBaseAccessor) entityexplodefx).setHeight(0.25f);
                entityexplodefx.setPosition(this.x, this.y, this.z);
                entityexplodefx.y = d2;
            }
        }
        else
        {
            for (int k = 0; k < 3; ++k)
            {
                final double d4 = (float) this.x + this.rand.nextFloat() * 0.25f;
                final double d5 = (float) this.y + this.height + 0.125f;
                final double d6 = (float) this.z + this.rand.nextFloat() * 0.25f;
                final float f2 = this.rand.nextFloat() * 360.0f;
                final Smoke entitysmokefx = new Smoke(this.level, -Math.sin((double) (0.01745329f * f2)) * 0.75, d5 - 0.25, Math.cos((double) (0.01745329f * f2)) * 0.75, d4, 0.125, d6, 3.5f);
                this.Enty.addParticle(entitysmokefx);
                this.fluffies.add(entitysmokefx);
                entitysmokefx.renderDistanceMultiplier = 10.0;
                entitysmokefx.field_1642 = true;
                ((EntityBaseAccessor) entitysmokefx).setWidth(0.25f);
                ((EntityBaseAccessor) entitysmokefx).setHeight(0.25f);
                entitysmokefx.setPosition(this.x, this.y, this.z);
                entitysmokefx.y = d5;
            }
        }
        final double d7 = (float) this.x;
        final double d8 = (float) this.y;
        final double d9 = (float) this.z;
        for (int l = 0; l < list.size(); ++l)
        {
            final EntityBase entity = (EntityBase) list.get(l);
            final double d10 = (float) entity.x;
            final double d11 = (float) entity.y - entity.standingEyeHeight * 0.6f;
            final double d12 = (float) entity.z;
            double d13 = this.distanceTo(entity);
            final double d14 = d11 - d8;
            if (d13 <= 1.5 + d14)
            {
                entity.velocityY = 0.15000000596046448;
                ((EntityBaseAccessor) entity).setFallDistance(0.0f);
                if (d14 > 1.5)
                {
                    entity.velocityY = -0.44999998807907104 + d14 * 0.3499999940395355;
                    d13 += d14 * 1.5;
                    if (entity == this.entity)
                    {
                        this.entity = null;
                        this.setTarget(null);
                    }
                }
                else
                {
                    entity.velocityY = 0.125;
                }
                double d15 = Math.atan2(d7 - d10, d9 - d12) / 0.01745329424738884;
                d15 += 160.0;
                entity.velocityX = -Math.cos(0.01745329424738884 * d15) * (d13 + 0.25) * 0.10000000149011612;
                entity.velocityZ = Math.sin(0.01745329424738884 * d15) * (d13 + 0.25) * 0.10000000149011612;
                if (entity instanceof Whirly)
                {
                    entity.remove();
                    if (!this.shouldStopEvil() && !this.evil)
                    {
                        this.evil = true;
                        this.Life /= 2;
                    }
                }
            }
            else
            {
                final double d16 = Math.atan2(d7 - d10, d9 - d12) / 0.01745329424738884;
                final EntityBase entityBase = entity;
                entityBase.velocityX += Math.sin(0.01745329424738884 * d16) * 0.009999999776482582;
                final EntityBase entityBase2 = entity;
                entityBase2.velocityZ += Math.cos(0.01745329424738884 * d16) * 0.009999999776482582;
            }
            final int j2 = MathHelper.floor(this.x);
            final int k2 = MathHelper.floor(this.y);
            final int l2 = MathHelper.floor(this.z);
            if (this.level.getTileId(j2, k2 + 1, l2) != 0)
            {
                this.Life -= 50;
            }
            final int i2 = j2 - 1 + this.rand.nextInt(3);
            final int j3 = k2 + this.rand.nextInt(5);
            final int k3 = l2 - 1 + this.rand.nextInt(3);
            if (this.level.getTileId(i2, j3, k3) == BlockBase.LEAVES.id)
            {
                this.level.setTile(i2, j3, k3, 0);
            }
        }
        if (!level.isServerSide)
            return;
        if (this.fluffies.size() > 0)
        {
            for (int i3 = 0; i3 < this.fluffies.size(); ++i3)
            {
                final ParticleBase entityfx = (ParticleBase) this.fluffies.get(i3);
                if (entityfx.removed)
                {
                    this.fluffies.remove(entityfx);
                }
                else
                {
                    final double d17 = (float) entityfx.x;
                    final double d18 = (float) entityfx.boundingBox.minY;
                    final double d19 = (float) entityfx.z;
                    final double d20 = this.distanceTo(entityfx);
                    final double d21 = d18 - d8;
                    entityfx.velocityY = 0.11500000208616257;
                    double d22 = Math.atan2(d7 - d17, d9 - d19) / 0.01745329424738884;
                    d22 += 160.0;
                    entityfx.velocityX = -Math.cos(0.01745329424738884 * d22) * (d20 * 2.5 - d21) * 0.10000000149011612;
                    entityfx.velocityZ = Math.sin(0.01745329424738884 * d22) * (d20 * 2.5 - d21) * 0.10000000149011612;
                }
            }
        }
    }

    public int loot()
    {
        final int i = this.rand.nextInt(100) + 1;
        if (i == 100)
        {
            return ItemBase.diamond.id;
        }
        if (i >= 96)
        {
            return ItemBase.ironIngot.id;
        }
        if (i >= 91)
        {
            return ItemBase.goldIngot.id;
        }
        if (i >= 82)
        {
            return ItemBase.coal.id;
        }
        if (i >= 75)
        {
            return BlockBase.GRAVEL.id;
        }
        if (i >= 64)
        {
            return BlockBase.CLAY.id;
        }
        if (i >= 52)
        {
            return ItemBase.stick.id;
        }
        if (i >= 38)
        {
            return ItemBase.flint.id;
        }
        if (i > 20)
        {
            return BlockBase.LOG.id;
        }
        return BlockBase.SAND.id;
    }

    @Override
    public void remove()
    {
        SideUtils.run(this::clientRemove, this::serverRemove);
        super.remove();
    }

    public void serverRemove()
    {
        if (!this.fluffies.isEmpty())
        {
            for (int i = 0; i < this.fluffies.size(); ++i)
            {
                this.fluffies.remove(i);
            }
        }
    }

    public void clientRemove()
    {
        if (!this.fluffies.isEmpty())
        {
            for (int i = 0; i < this.fluffies.size(); ++i)
            {
                final ParticleBase particleBase;
                final ParticleBase entityfx = particleBase = (ParticleBase) this.fluffies.get(i);
                particleBase.velocityX *= 0.5;
                final ParticleBase particleBase2 = entityfx;
                particleBase2.velocityY *= 0.75;
                final ParticleBase particleBase3 = entityfx;
                particleBase3.velocityZ *= 0.5;
                this.fluffies.remove(entityfx);
            }
        }
    }

    @Override
    public boolean canSpawn()
    {
        if (this.y < 64.0)
        {
            this.y += 64.0;
        }
        if (!this.level.canSpawnEntity(this.boundingBox) || this.level.method_190(this, this.boundingBox).size() != 0 || this.level.method_218(this.boundingBox))
        {
            return false;
        }
        final int i = MathHelper.floor(this.x);
        final int j = MathHelper.floor(this.boundingBox.minY);
        final int k = MathHelper.floor(this.z);
        boolean flag = true;
        for (int l = 1; l < 20; ++l)
        {
            if (l + j >= 125)
            {
                break;
            }
            if (this.level.placeTile(i, j + l, k) <= 12 || this.level.getTileId(i, j + l, k) != 0)
            {
                flag = false;
                break;
            }
        }
        return flag;
    }

    public EntityBase getPlayer()
    {
        final PlayerBase entityplayer = this.level.getClosestPlayerTo(this, 16.0);
        if (entityplayer != null && this.method_928(entityplayer))
        {
            return entityplayer;
        }
        return null;
    }

    @Override
    public void writeCustomDataToTag(final CompoundTag tag)
    {
        super.writeCustomDataToTag(tag);
        tag.put("Angle", this.Angle);
        tag.put("Speed", this.Speed);
        tag.put("Curve", this.Curve);
        tag.put("Life", (short) this.Life);
        tag.put("Counter", (short) this.entcount);
        tag.put("Evil", this.evil);
    }

    @Override
    public void readCustomDataFromTag(final CompoundTag tag)
    {
        super.readCustomDataFromTag(tag);
        this.Angle = tag.getFloat("Angle");
        this.Speed = tag.getFloat("Speed");
        this.Curve = tag.getFloat("Curve");
        this.Life = tag.getShort("Life");
        this.entcount = tag.getShort("Counter");
        this.evil = tag.getBoolean("Evil");
    }

    @Override
    public boolean damage(final EntityBase target, final int amount)
    {
        return false;
    }

    @Override
    public void method_1353(final EntityBase entity)
    {
    }

    @Override
    public int getLimitPerChunk()
    {
        return 1;
    }

    @Override
    public boolean method_932()
    {
        return this.field_1624;
    }

    @Override
    public Identifier getHandlerIdentifier()
    {
        return AetherMod.MODID.id("Whirly");
    }
}
