package com.gildedgames.aether.entity.animal;

import com.gildedgames.aether.AetherMod;
import com.gildedgames.aether.entity.base.EntityAetherAnimal;
import com.gildedgames.aether.mixin.access.EntityBaseAccessor;
import com.gildedgames.aether.mixin.access.LivingAccessor;
import com.gildedgames.aether.registry.AetherAchievements;
import com.gildedgames.aether.registry.AetherItems;
import net.minecraft.entity.Living;
import net.minecraft.entity.player.PlayerBase;
import net.minecraft.item.ItemBase;
import net.minecraft.level.Level;
import net.minecraft.util.io.CompoundTag;
import net.modificationstation.stationapi.api.registry.Identifier;

public class EntityPhyg extends EntityAetherAnimal
{
    public boolean getSaddled;
    public float wingFold;
    public float wingAngle;
    private float aimingForFold;
    public int jumps;
    public int jrem;
    private boolean jpress;
    private int ticks;

    public EntityPhyg(final Level level)
    {
        super(level);
        this.getSaddled = false;
        this.texture = "aether:textures/entity/FlyingPigBase.png";
        this.setSize(0.9f, 1.3f);
        this.jrem = 0;
        this.jumps = 1;
        this.ticks = 0;
        this.field_1641 = 1.0f;
        this.field_1622 = true;
    }

    @Override
    protected boolean method_940()
    {
        return !this.getSaddled;
    }

    @Override
    protected boolean canClimb()
    {
        return this.onGround;
    }

    @Override
    protected void initDataTracker()
    {
        this.dataTracker.startTracking(16, 0);
    }

    @Override
    public void writeCustomDataToTag(final CompoundTag tag)
    {
        super.writeCustomDataToTag(tag);
        tag.put("Jumps", (short) this.jumps);
        tag.put("Remaining", (short) this.jrem);
        tag.put("getSaddled", this.getSaddled);
    }

    @Override
    public void readCustomDataFromTag(final CompoundTag tag)
    {
        super.readCustomDataFromTag(tag);
        this.jumps = tag.getShort("Jumps");
        this.jrem = tag.getShort("Remaining");
        this.getSaddled = tag.getBoolean("getSaddled");
        if (this.getSaddled)
        {
            this.texture = "aether:textures/entity/FlyingPigSaddle.png";
        }
    }

    @Override
    protected void jump()
    {
        this.velocityY = 0.6;
    }

    @Override
    public void tick()
    {
        super.tick();
        if (this.onGround)
        {
            this.wingAngle *= 0.8f;
            this.aimingForFold = 0.1f;
            this.jpress = false;
            this.jrem = this.jumps;
        }
        else
        {
            this.aimingForFold = 1.0f;
        }
        ++this.ticks;
        this.wingAngle = this.wingFold * (float) Math.sin((double) (this.ticks / 31.830988f));
        this.wingFold += (this.aimingForFold - this.wingFold) / 5.0f;
        this.fallDistance = 0.0f;
        if (this.velocityY < -0.2)
        {
            this.velocityY = -0.2;
        }
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
                this.velocityY = 1.4;
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
                this.velocityY = 1.2;
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
    protected String getAmbientSound()
    {
        return "mob.pig";
    }

    @Override
    protected String getHurtSound()
    {
        return "mob.pig";
    }

    @Override
    protected String getDeathSound()
    {
        return "mob.pigdeath";
    }

    @Override
    public boolean interact(final PlayerBase entityplayer)
    {
        if (!this.getSaddled && entityplayer.inventory.getHeldItem() != null && entityplayer.inventory.getHeldItem().itemId == ItemBase.saddle.id)
        {
            entityplayer.inventory.setInventoryItem(entityplayer.inventory.selectedHotbarSlot, null);
            this.getSaddled = true;
            this.texture = "aether:textures/entity/FlyingPigSaddle.png";
            return true;
        }
        if (this.getSaddled && !this.level.isServerSide && (this.passenger == null || this.passenger == entityplayer))
        {
            entityplayer.startRiding(this);
            AetherMod.giveAchievement(AetherAchievements.flyingPig, entityplayer);
            return true;
        }
        return false;
    }

    @Override
    protected void getDrops()
    {
        PlayerBase player = this.level.getClosestPlayerTo(this, 10);
        int count = 1;
        if (player != null)
            if (player.getHeldItem() != null)
                if (player.getHeldItem().getType() == AetherItems.SwordSkyroot)
                    count *= 2;
        this.dropItem(this.rand.nextBoolean() ? ItemBase.feather.id : ItemBase.rawPorkchop.id, count);
    }

    @Override
    public Identifier getHandlerIdentifier()
    {
        return AetherMod.MODID.id("Phyg");
    }
}
