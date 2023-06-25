package com.gildedgames.aether.entity.animal;
import net.minecraft.item.ItemInstance;
import net.minecraft.block.BlockBase;
import net.minecraft.entity.monster.MonsterBase;
import net.minecraft.util.io.CompoundTag;
import net.minecraft.entity.player.PlayerBase;
import net.minecraft.entity.Living;
import net.minecraft.util.maths.MathHelper;
import java.util.List;

import com.gildedgames.aether.Aether;
import com.gildedgames.aether.entity.base.EntityAetherAnimal;
import com.gildedgames.aether.mixin.access.EntityBaseAccessor;
import com.gildedgames.aether.mixin.access.LivingAccessor;
import com.gildedgames.aether.registry.AetherBlocks;

import net.minecraft.entity.EntityBase;
import net.minecraft.level.Level;

public class EntitySwet extends EntityAetherAnimal {
    public int ticker;
    public int flutter;
    public int hops;
    public int textureNum;
    public boolean textureSet;
    public boolean gotrider;
    public boolean kickoff;
    public boolean friendly;
    
    public EntitySwet(final Level level) {
        super(level);
        this.health = 25;
        if (!this.textureSet) {
            if (this.rand.nextInt(2) == 0) {
                this.textureNum = 2;
                this.textureSet = true;
            }
            else {
                this.textureNum = 1;
                this.textureSet = true;
            }
        }
        if (this.textureNum == 1) {
            this.texture = "aether:textures/entity/swets.png";
            this.movementSpeed = 1.5f;
        }
        else {
            this.texture = "aether:textures/entity/goldswets.png";
            this.movementSpeed = 3.0f;
        }
        this.setSize(0.8f, 0.8f);
        this.setPosition(this.x, this.y, this.z);
        this.hops = 0;
        this.gotrider = false;
        this.flutter = 0;
        this.ticker = 0;
    }
    
    @Override
    public void tickRiding() {
        super.tickRiding();
        if (this.passenger != null && this.kickoff) {
            this.passenger.startRiding(this);
            this.kickoff = false;
        }
    }
    
    @Override
    public void method_1382() {
        this.passenger.setPosition(this.x, this.boundingBox.minY - 0.30000001192092896 + this.passenger.standingEyeHeight, this.z);
    }
    
    @Override
    public void tick() {
        if (this.entity != null) {
            for (int i = 0; i < 3; ++i) {
                final float f = 0.01745278f;
                final double d = (float)this.x + (this.rand.nextFloat() - this.rand.nextFloat()) * 0.3f;
                final double d2 = (float)this.y + this.height;
                final double d3 = (float)this.z + (this.rand.nextFloat() - this.rand.nextFloat()) * 0.3f;
                this.level.addParticle("splash", d, d2 - 0.25, d3, 0.0, 0.0, 0.0);
            }
        }
        super.tick();
        if (this.gotrider) {
            if (this.passenger != null) {
                return;
            }
            final List list = this.level.getEntities(this, this.boundingBox.expand(0.5, 0.75, 0.5));
            final int j = 0;
            if (j < list.size()) {
                final EntityBase entity = (EntityBase)list.get(j);
                this.capturePrey(entity);
            }
            this.gotrider = false;
        }
        if (this.method_1393()) {
            this.dissolve();
        }
    }
    
    @Override
    protected boolean method_940() {
        return true;
    }
    
    public void handleFallDamage(final float height) {
        if (this.friendly) {
            return;
        }
        super.handleFallDamage(height);
        if (this.hops >= 3 && this.health > 0) {
            this.dissolve();
        }
    }
    
    @Override
    public void method_925(final EntityBase entity, final int i, final double d, final double d1) {
        if (this.passenger != null && entity == this.passenger) {
            return;
        }
        super.method_925(entity, i, d, d1);
    }
    
    public void dissolve() {
        for (int i = 0; i < 50; ++i) {
            final float f = this.rand.nextFloat() * 3.141593f * 2.0f;
            final float f2 = this.rand.nextFloat() * 0.5f + 0.25f;
            final float f3 = MathHelper.sin(f) * f2;
            final float f4 = MathHelper.cos(f) * f2;
            this.level.addParticle("splash", this.x + f3, this.boundingBox.minY + 1.25, this.z + f4, f3 * 1.5 + this.velocityX, 4.0, f4 * 1.5 + this.velocityZ);
        }
        if (this.passenger != null) {
            final EntityBase passenger = this.passenger;
            passenger.y += this.passenger.standingEyeHeight - 0.3f;
            this.passenger.startRiding(this);
        }
        this.remove();
    }
    
    public void capturePrey(final EntityBase entity) {
        this.splorch();
        final double x = entity.x;
        this.x = x;
        this.prevX = x;
        final double n = entity.y + 0.009999999776482582;
        this.y = n;
        this.prevY = n;
        final double z = entity.z;
        this.z = z;
        this.prevZ = z;
        final float yaw = entity.yaw;
        this.yaw = yaw;
        this.prevYaw = yaw;
        final float pitch = entity.pitch;
        this.pitch = pitch;
        this.prevPitch = pitch;
        this.velocityX = entity.velocityX;
        this.velocityY = entity.velocityY;
        this.velocityZ = entity.velocityZ;
        this.setSize(entity.width, entity.height);
        this.setPosition(this.x, this.y, this.z);
        entity.startRiding(this);
        this.yaw = this.rand.nextFloat() * 360.0f;
    }
    
    @Override
    public boolean damage(final EntityBase target, final int amount) {
        if (this.hops == 3 && target == null && this.health > 1) {
            this.health = 1;
        }
        final boolean flag = super.damage(target, amount);
        if (flag && this.passenger != null && this.passenger instanceof Living) {
            if (target != null && this.passenger == target) {
                if (this.rand.nextInt(3) == 0) {
                    this.kickoff = true;
                }
            }
            else {
                this.passenger.damage(null, amount);
                if (this.health <= 0) {
                    this.kickoff = true;
                }
            }
        }
        if (flag && this.health <= 0) {
            this.dissolve();
        }
        else if (flag && target instanceof Living) {
            final Living entityliving = (Living)target;
            if (entityliving.health > 0 && (this.passenger == null || entityliving != this.passenger)) {
                this.method_924(this.entity = target, 180.0f, 180.0f);
                this.kickoff = true;
            }
        }
        if (this.friendly && this.entity instanceof PlayerBase) {
            this.entity = null;
        }
        return flag;
    }
    
    public void d_2() {
        if (this.passenger != null && this.passenger instanceof Living) {
            this.field_1029 = 0.0f;
            this.field_1060 = 0.0f;
            this.jumping = false;
            ((EntityBaseAccessor)this.passenger).setFallDistance(0.0f);
            final float yaw = this.passenger.yaw;
            this.yaw = yaw;
            this.prevYaw = yaw;
            final float n = 0.0f;
            this.pitch = n;
            this.prevPitch = n;
            final Living entityliving = (Living)this.passenger;
            final float f = 3.141593f;
            final float f2 = f / 180.0f;
            final float f3 = entityliving.yaw * f2;
            if (this.onGround) {
                if (((LivingAccessor)entityliving).getJumping()) {
                    if (this.hops == 0) {
                        this.onGround = false;
                        this.velocityY = 0.8500000238418579;
                        this.hops = 1;
                        this.flutter = 5;
                    }
                    else if (this.hops == 1) {
                        this.onGround = false;
                        this.velocityY = 1.0499999523162842;
                        this.hops = 2;
                        this.flutter = 5;
                    }
                    else if (this.hops == 2) {
                        this.onGround = false;
                        this.velocityY = 1.25;
                        this.flutter = 5;
                    }
                }
                else if (((LivingAccessor)entityliving).get1029() > 0.125f || ((LivingAccessor)entityliving).get1029() < -0.125f || ((LivingAccessor)entityliving).get1060() > 0.125f || ((LivingAccessor)entityliving).get1060() < -0.125f) {
                    this.onGround = false;
                    this.velocityY = 0.3499999940395355;
                    this.hops = 0;
                    this.flutter = 0;
                }
                else if (this.hops > 0) {
                    this.hops = 0;
                }
                ((LivingAccessor)entityliving).set1029(0.0f);
                ((LivingAccessor)entityliving).set1060(0.0f);
            }
            else {
                if (((LivingAccessor)entityliving).get1029() > 0.1f) {
                    if (this.textureNum == 1) {
                        this.velocityX += ((LivingAccessor)entityliving).get1029() * -Math.sin((double)f3) * 0.125;
                        this.velocityZ += ((LivingAccessor)entityliving).get1029() * Math.cos((double)f3) * 0.125;
                    }
                    else {
                        this.velocityX += ((LivingAccessor)entityliving).get1029() * -Math.sin((double)f3) * 0.325;
                        this.velocityZ += ((LivingAccessor)entityliving).get1029() * Math.cos((double)f3) * 0.125;
                    }
                }
                else if (((LivingAccessor)entityliving).get1029() < -0.1f) {
                    if (this.textureNum == 1) {
                        this.velocityX += ((LivingAccessor)entityliving).get1029() * -Math.sin((double)f3) * 0.125;
                        this.velocityZ += ((LivingAccessor)entityliving).get1029() * Math.cos((double)f3) * 0.125;
                    }
                    else {
                        this.velocityX += ((LivingAccessor)entityliving).get1029() * -Math.sin((double)f3) * 0.325;
                        this.velocityZ += ((LivingAccessor)entityliving).get1029() * Math.cos((double)f3) * 0.125;
                    }
                }
                if (((LivingAccessor)entityliving).get1060() > 0.1f) {
                    if (this.textureNum == 1) {
                        this.velocityX += ((LivingAccessor)entityliving).get1060() * Math.cos((double)f3) * 0.125;
                        this.velocityZ += ((LivingAccessor)entityliving).get1060() * Math.sin((double)f3) * 0.125;
                    }
                    else {
                        this.velocityX += ((LivingAccessor)entityliving).get1060() * Math.cos((double)f3) * 0.325;
                        this.velocityZ += ((LivingAccessor)entityliving).get1060() * Math.sin((double)f3) * 0.125;
                    }
                }
                else if (((LivingAccessor)entityliving).get1060() < -0.1f) {
                    if (this.textureNum == 1) {
                        this.velocityX += ((LivingAccessor)entityliving).get1060() * Math.cos((double)f3) * 0.125;
                        this.velocityZ += ((LivingAccessor)entityliving).get1060() * Math.sin((double)f3) * 0.125;
                    }
                    else {
                        this.velocityX += ((LivingAccessor)entityliving).get1060() * Math.cos((double)f3) * 0.325;
                        this.velocityZ += ((LivingAccessor)entityliving).get1060() * Math.sin((double)f3) * 0.125;
                    }
                }
                if (this.velocityY < 0.05000000074505806 && this.flutter > 0 && ((LivingAccessor)entityliving).getJumping()) {
                    this.velocityY += 0.07000000029802322;
                    --this.flutter;
                }
            }
            final double d = Math.abs(Math.sqrt(this.velocityX * this.velocityX + this.velocityZ * this.velocityZ));
            if (d > 0.2750000059604645) {
                final double d2 = 0.275 / d;
                this.velocityX *= d2;
                this.velocityZ *= d2;
            }
        }
    }
    
    public void tickHandSwing() {
        ++this.despawnCounter;
        this.method_920();
        if (this.friendly && this.passenger != null) {
            this.d_2();
            return;
        }
        if (!this.onGround && this.jumping) {
            this.jumping = false;
        }
        else if (this.onGround) {
            if (this.field_1029 > 0.05f) {
                this.field_1029 *= 0.75f;
            }
            else {
                this.field_1029 = 0.0f;
            }
        }
        if (this.entity != null && this.passenger == null && this.health > 0) {
            this.method_924(this.entity, 10.0f, 10.0f);
        }
        if (this.entity != null && this.entity.removed) {
            this.entity = null;
        }
        if (!this.onGround && this.velocityY < 0.05000000074505806 && this.flutter > 0) {
            this.velocityY += 0.07000000029802322;
            --this.flutter;
        }
        if (this.ticker < 4) {
            ++this.ticker;
        }
        else {
            if (this.onGround && this.passenger == null && this.hops != 0 && this.hops != 3) {
                this.hops = 0;
            }
            if (this.entity == null && this.passenger == null) {
                final EntityBase entity = this.getPrey();
                if (entity != null) {
                    this.entity = entity;
                }
            }
            else if (this.entity != null && this.passenger == null) {
                if (this.distanceTo(this.entity) <= 9.0f) {
                    if (this.onGround && this.method_928(this.entity)) {
                        this.splotch();
                        this.flutter = 10;
                        this.jumping = true;
                        this.field_1029 = 1.0f;
                        this.yaw += 5.0f * (this.rand.nextFloat() - this.rand.nextFloat());
                    }
                }
                else {
                    this.entity = null;
                    this.jumping = false;
                    this.field_1029 = 0.0f;
                }
            }
            else if (this.passenger != null && this.onGround) {
                if (this.hops == 0) {
                    this.splotch();
                    this.onGround = false;
                    this.velocityY = 0.3499999940395355;
                    this.field_1029 = 0.8f;
                    this.hops = 1;
                    this.flutter = 5;
                    this.yaw += 20.0f * (this.rand.nextFloat() - this.rand.nextFloat());
                }
                else if (this.hops == 1) {
                    this.splotch();
                    this.onGround = false;
                    this.velocityY = 0.44999998807907104;
                    this.field_1029 = 0.9f;
                    this.hops = 2;
                    this.flutter = 5;
                    this.yaw += 20.0f * (this.rand.nextFloat() - this.rand.nextFloat());
                }
                else if (this.hops == 2) {
                    this.splotch();
                    this.onGround = false;
                    this.velocityY = 1.25;
                    this.field_1029 = 1.25f;
                    this.hops = 3;
                    this.flutter = 5;
                    this.yaw += 20.0f * (this.rand.nextFloat() - this.rand.nextFloat());
                }
            }
            this.ticker = 0;
        }
        if (this.onGround && this.hops >= 3) {
            this.dissolve();
        }
    }
    
    @Override
    public void writeCustomDataToTag(final CompoundTag tag) {
        super.writeCustomDataToTag(tag);
        tag.put("Hops", (short)this.hops);
        tag.put("Flutter", (short)this.flutter);
        if (this.passenger != null) {
            this.gotrider = true;
        }
        tag.put("GotRider", this.gotrider);
        tag.put("Friendly", this.friendly);
        tag.put("textureSet", this.textureSet);
        tag.put("textureNum", (short)this.textureNum);
    }
    
    @Override
    public void readCustomDataFromTag(final CompoundTag tag) {
        super.readCustomDataFromTag(tag);
        this.hops = tag.getShort("Hops");
        this.flutter = tag.getShort("Flutter");
        this.gotrider = tag.getBoolean("GotRider");
        this.friendly = tag.getBoolean("Friendly");
        this.textureSet = tag.getBoolean("textureSet");
        this.textureNum = tag.getShort("textureNum");
        if (this.textureNum == 1) {
            this.texture = "aether:textures/entity/swets.png";
            this.movementSpeed = 1.5f;
        }
        else {
            this.texture = "aether:textures/entity/goldswets.png";
            this.movementSpeed = 3.0f;
        }
    }
    
    public void splorch() {
        this.level.playSound((EntityBase)this, "mob.slimeattack", 1.0f, (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2f + 1.0f);
    }
    
    public void splotch() {
        this.level.playSound((EntityBase)this, "mob.slime", 0.5f, (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2f + 1.0f);
    }
    
    @Override
    protected String getHurtSound() {
        return "mob.slime";
    }
    
    @Override
    protected String getDeathSound() {
        return "mob.slime";
    }
    
    @Override
    public void method_1353(final EntityBase entity) {
        if (this.hops == 0 && this.passenger == null && this.entity != null && entity != null && entity == this.entity && (entity.vehicle == null || !(entity.vehicle instanceof EntitySwet))) {
            if (entity.passenger != null) {
                entity.passenger.startRiding(entity);
            }
            this.capturePrey(entity);
        }
        super.method_1353(entity);
    }
    
    @Override
    public boolean interact(final PlayerBase entityplayer) {
        if (!this.level.isServerSide) {
            if (!this.friendly) {
                this.friendly = true;
                this.entity = null;
                return true;
            }
            if ((this.friendly && this.passenger == null) || this.passenger == entityplayer) {
                this.capturePrey(entityplayer);
            }
        }
        return true;
    }
    
    protected EntityBase getPrey() {
        final List list = this.level.getEntities(this, this.boundingBox.expand(6.0, 6.0, 6.0));
        for (int i = 0; i < list.size(); ++i) {
            final EntityBase entity = (EntityBase)list.get(i);
            if (entity instanceof Living && !(entity instanceof EntitySwet)) {
                if (this.friendly) {
                    if (entity instanceof PlayerBase) {
                        continue;
                    }
                }
                else if (entity instanceof MonsterBase) {
                    continue;
                }
                return entity;
            }
        }
        return null;
    }
    
    @Override
    protected void getDrops() {
        final ItemInstance stack = new ItemInstance((this.textureNum == 1) ? AetherBlocks.AERCLOUD.id : BlockBase.GLOWSTONE.id, 3, (this.textureNum == 1) ? 1 : 0);
        if (Aether.equippedSkyrootSword()) {
            final ItemInstance itemInstance = stack;
            itemInstance.count *= 2;
        }
        this.dropItem(stack, 0.0f);
    }
}
