package com.gildedgames.aether.entity.mobs;

import com.gildedgames.aether.AetherMod;
import com.gildedgames.aether.entity.projectile.EntityPoisonNeedle;
import com.gildedgames.aether.mixin.access.EntityBaseAccessor;
import com.gildedgames.aether.mixin.access.LivingAccessor;
import com.gildedgames.aether.registry.AetherBlocks;
import com.gildedgames.aether.registry.AetherItems;
import net.minecraft.entity.EntityBase;
import net.minecraft.entity.Living;
import net.minecraft.entity.monster.MonsterBase;
import net.minecraft.entity.player.PlayerBase;
import net.minecraft.item.ItemBase;
import net.minecraft.level.Level;
import net.minecraft.util.io.CompoundTag;
import net.minecraft.util.maths.MathHelper;
import net.modificationstation.stationapi.api.registry.Identifier;
import net.modificationstation.stationapi.api.server.entity.MobSpawnDataProvider;

import java.util.List;

public class EntityCockatrice extends MonsterBase implements MobSpawnDataProvider
{
    public float field_752_b;
    public float destPos;
    public float field_757_d;
    public float field_756_e;
    public float field_755_h;
    public int timeUntilNextEgg;
    public int jumps;
    public int jrem;
    public boolean jpress;
    public boolean gotrider;

    public EntityCockatrice(final Level level)
    {
        super(level);
        this.destPos = 0.0f;
        this.field_755_h = 1.0f;
        this.field_1641 = 1.0f;
        this.jrem = 0;
        this.jumps = 3;
        this.texture = "aether:textures/entity/Cockatrice.png";
        this.setSize(1.0f, 2.0f);
        this.health = 20;
        this.timeUntilNextEgg = this.rand.nextInt(6000) + 6000;
    }

    @Override
    public boolean canSpawn()
    {
        final int i = MathHelper.floor(this.x);
        final int j = MathHelper.floor(this.boundingBox.minY);
        final int k = MathHelper.floor(this.z);
        return this.rand.nextInt(25) == 0 && this.getPathfindingFavour(i, j, k) >= 0.0f && this.level.canSpawnEntity(this.boundingBox) && this.level.method_190(this, this.boundingBox).size() == 0 && !this.level.method_218(this.boundingBox) && this.level.getTileId(i, j - 1, k) != AetherBlocks.DUNGEON_STONE.id && this.level.getTileId(i, j - 1, k) != AetherBlocks.LIGHT_DUNGEON_STONE.id
                && this.level.getTileId(i, j - 1, k) != AetherBlocks.LOCKED_DUNGEON_STONE.id && this.level.getTileId(i, j - 1, k) != AetherBlocks.LOCKED_LIGHT_DUNGEON_STONE.id && this.level.getTileId(i, j - 1, k) != AetherBlocks.HOLYSTONE.id && this.level.difficulty > 0;
    }

    @Override
    public void tick()
    {
        super.tick();
        this.field_1622 = (this.passenger instanceof PlayerBase); // todo: make local player
        if (!this.level.isServerSide && this.gotrider)
        {
            if (this.passenger != null)
            {
                return;
            }
            final List list = this.level.getEntities(this, this.boundingBox.expand(0.5, 0.75, 0.5));
            final int i = 0;
            if (i < list.size())
            {
                final EntityBase entity = (EntityBase) list.get(i);
                entity.startRiding(this);
            }
            this.gotrider = false;
        }
        if (!this.level.isServerSide && this.level.difficulty == 0)
        {
            this.remove();
        }
    }

    @Override
    protected void tryAttack(final EntityBase target, final float f)
    {
        if (f < 10.0f)
        {
            final double d = target.x - this.x;
            final double d2 = target.z - this.z;
            if (this.attackTime == 0)
            {
                final EntityPoisonNeedle entityPoisonNeedle;
                final EntityPoisonNeedle entityarrow = entityPoisonNeedle = new EntityPoisonNeedle(this.level, this);
                entityPoisonNeedle.y += 1.399999976158142;
                final double d3 = target.y + target.getStandingEyeHeight() - 0.20000000298023224 - entityarrow.y;
                final float f2 = MathHelper.sqrt(d * d + d2 * d2) * 0.2f;
                this.level.playSound((EntityBase) this, "aether:aether.sound.other.dartshooter.shootdart", 1.0f, 1.0f / (this.rand.nextFloat() * 0.4f + 0.8f));
                this.level.spawnEntity(entityarrow);
                entityarrow.setArrowHeading(d, d3 + f2, d2, 0.6f, 12.0f);
                this.attackTime = 30;
            }
            this.yaw = (float) (Math.atan2(d2, d) * 180.0 / 3.1415927410125732) - 90.0f;
            this.field_663 = true;
        }
    }

    @Override
    public void updateDespawnCounter()
    {
        super.updateDespawnCounter();
        this.field_756_e = this.field_752_b;
        this.field_757_d = this.destPos;
        this.destPos += (float) ((this.onGround ? -1 : 4) * 0.05);
        if (this.destPos < 0.01f)
        {
            this.destPos = 0.01f;
        }
        if (this.destPos > 1.0f)
        {
            this.destPos = 1.0f;
        }
        if (this.onGround)
        {
            this.destPos = 0.0f;
            this.jpress = false;
            this.jrem = this.jumps;
        }
        if (!this.onGround && this.field_755_h < 1.0f)
        {
            this.field_755_h = 1.0f;
        }
        this.field_755_h *= (float) 0.9;
        if (!this.onGround && this.velocityY < 0.0)
        {
            if (this.passenger == null)
            {
                this.velocityY *= 0.6;
            }
            else
            {
                this.velocityY *= 0.6375;
            }
        }
        this.field_752_b += this.field_755_h * 2.0f;
        if (!this.level.isServerSide && --this.timeUntilNextEgg <= 0)
        {
            this.timeUntilNextEgg = this.rand.nextInt(6000) + 6000;
        }
    }

    @Override
    protected void handleFallDamage(final float height)
    {
    }

    @Override
    public boolean damage(final EntityBase target, final int amount)
    {
        if (target != null && this.passenger != null && target == this.passenger)
        {
            return false;
        }
        final boolean flag = super.damage(target, amount);
        if (flag && this.passenger != null && (this.health <= 0 || this.rand.nextInt(3) == 0))
        {
            this.passenger.startRiding(this);
        }
        return flag;
    }

    public void tickHandSwing()
    {
        if (this.level.isServerSide)
        {
            return;
        }
        if (this.passenger != null && this.passenger instanceof Living)
        {
            this.field_1029 = 0.0f;
            this.field_1060 = 0.0f;
            this.jumping = false;
            ((EntityBaseAccessor) this.passenger).setFallDistance(0.0f);
            final float yaw = this.passenger.yaw;
            this.yaw = yaw;
            this.prevYaw = yaw;
            final float pitch = this.passenger.pitch;
            this.pitch = pitch;
            this.prevPitch = pitch;
            final Living entityliving = (Living) this.passenger;
            final float f = 3.141593f;
            final float f2 = f / 180.0f;
            if (((LivingAccessor) entityliving).get1029() > 0.1f)
            {
                final float f3 = entityliving.yaw * f2;
                this.velocityX += ((LivingAccessor) entityliving).get1029() * -Math.sin((double) f3) * 0.17499999701976776;
                this.velocityZ += ((LivingAccessor) entityliving).get1029() * Math.cos((double) f3) * 0.17499999701976776;
            }
            else if (((LivingAccessor) entityliving).get1029() < -0.1f)
            {
                final float f4 = entityliving.yaw * f2;
                this.velocityX += ((LivingAccessor) entityliving).get1029() * -Math.sin((double) f4) * 0.17499999701976776;
                this.velocityZ += ((LivingAccessor) entityliving).get1029() * Math.cos((double) f4) * 0.17499999701976776;
            }
            if (((LivingAccessor) entityliving).get1060() > 0.1f)
            {
                final float f5 = entityliving.yaw * f2;
                this.velocityX += ((LivingAccessor) entityliving).get1060() * Math.cos((double) f5) * 0.17499999701976776;
                this.velocityZ += ((LivingAccessor) entityliving).get1060() * Math.sin((double) f5) * 0.17499999701976776;
            }
            else if (((LivingAccessor) entityliving).get1060() < -0.1f)
            {
                final float f6 = entityliving.yaw * f2;
                this.velocityX += ((LivingAccessor) entityliving).get1060() * Math.cos((double) f6) * 0.17499999701976776;
                this.velocityZ += ((LivingAccessor) entityliving).get1060() * Math.sin((double) f6) * 0.17499999701976776;
            }
            if (this.onGround && ((LivingAccessor) entityliving).getJumping())
            {
                this.onGround = false;
                this.velocityY = 0.875;
                this.jpress = true;
                --this.jrem;
            }
            else if (this.method_1393() && ((LivingAccessor) entityliving).getJumping())
            {
                this.velocityY = 0.5;
                this.jpress = true;
                --this.jrem;
            }
            else if (this.jrem > 0 && !this.jpress && ((LivingAccessor) entityliving).getJumping())
            {
                this.velocityY = 0.75;
                this.jpress = true;
                --this.jrem;
            }
            if (this.jpress && !((LivingAccessor) entityliving).getJumping())
            {
                this.jpress = false;
            }
            final double d = Math.abs(Math.sqrt(this.velocityX * this.velocityX + this.velocityZ * this.velocityZ));
            if (d > 0.375)
            {
                final double d2 = 0.375 / d;
                this.velocityX *= d2;
                this.velocityZ *= d2;
            }
            return;
        }
        super.tickHandSwing();
    }

    @Override
    public void writeCustomDataToTag(final CompoundTag tag)
    {
        super.writeCustomDataToTag(tag);
        tag.put("Jumps", (short) this.jumps);
        tag.put("Remaining", (short) this.jrem);
        if (this.passenger != null)
        {
            this.gotrider = true;
        }
        tag.put("GotRider", this.gotrider);
    }

    @Override
    public void readCustomDataFromTag(final CompoundTag tag)
    {
        super.readCustomDataFromTag(tag);
        this.jumps = tag.getShort("Jumps");
        this.jrem = tag.getShort("Remaining");
        this.gotrider = tag.getBoolean("GotRider");
    }

    @Override
    protected String getAmbientSound()
    {
        return "aether:aether.sound.mobs.moa.idlecall";
    }

    @Override
    protected String getHurtSound()
    {
        return "aether:aether.sound.mobs.moa.idlecall";
    }

    @Override
    protected String getDeathSound()
    {
        return "aether:aether.sound.mobs.moa.idlecall";
    }

    public boolean interact(final PlayerBase entityplayer)
    {
        return true;
    }

    @Override
    protected void getDrops()
    {
        PlayerBase player = this.level.getClosestPlayerTo(this, 10);
        int count = 3;
        if (player != null)
            if (player.getHeldItem() != null)
                if (player.getHeldItem().getType() == AetherItems.SwordSkyroot)
                    count *= 2;
        this.dropItem(ItemBase.feather.id, count);
    }

    @Override
    public Identifier getHandlerIdentifier()
    {
        return AetherMod.MODID.id("Cockatrice");
    }
}
