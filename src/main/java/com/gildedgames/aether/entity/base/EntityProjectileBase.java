package com.gildedgames.aether.entity.base;
import net.minecraft.util.io.CompoundTag;
import net.minecraft.util.maths.Box;
import java.util.List;

import com.gildedgames.aether.mixin.LivingAccessor;

import net.minecraft.util.hit.HitResult;
import net.minecraft.util.maths.Vec3f;
import net.minecraft.util.maths.MathHelper;
import net.minecraft.entity.player.PlayerBase;
import net.minecraft.level.Level;
import net.minecraft.entity.Living;
import net.minecraft.item.ItemInstance;
import net.minecraft.entity.EntityBase;

public abstract class EntityProjectileBase extends EntityBase {
    public float speed;
    public float slowdown;
    public float curvature;
    public float precision;
    public float hitBox;
    public int dmg;
    public ItemInstance item;
    public int ttlInGround;
    public int xTile;
    public int yTile;
    public int zTile;
    public int inTile;
    public int inData;
    public boolean inGround;
    public int arrowShake;
    public Living shooter;
    public int ticksInGround;
    public int ticksFlying;
    public boolean shotByPlayer;
    
    public EntityProjectileBase(final Level level) {
        super(level);
    }
    
    public EntityProjectileBase(final Level world, final double d, final double d1, final double d2) {
        this(world);
        this.method_1338(d, d1, d2, this.yaw, this.pitch);
    }
    
    public EntityProjectileBase(final Level world, final Living entityliving) {
        this(world);
        this.shooter = entityliving;
        this.shotByPlayer = (entityliving instanceof PlayerBase);
        this.setPositionAndAngles(entityliving.x, entityliving.y + entityliving.getStandingEyeHeight(), entityliving.z, entityliving.yaw, entityliving.pitch);
        this.x -= MathHelper.cos(this.yaw / 180.0f * 3.141593f) * 0.16f;
        this.y -= 0.10000000149011612;
        this.z -= MathHelper.sin(this.yaw / 180.0f * 3.141593f) * 0.16f;
        this.method_1338(this.x, this.y, this.z, this.yaw, this.pitch);
        this.velocityX = -MathHelper.sin(this.yaw / 180.0f * 3.141593f) * MathHelper.cos(this.pitch / 180.0f * 3.141593f);
        this.velocityZ = MathHelper.cos(this.yaw / 180.0f * 3.141593f) * MathHelper.cos(this.pitch / 180.0f * 3.141593f);
        this.velocityY = -MathHelper.sin(this.pitch / 180.0f * 3.141593f);
        this.setArrowHeading(this.velocityX, this.velocityY, this.velocityZ, this.speed, this.precision);
    }
    
    @Override
    protected void initDataTracker() {
        this.xTile = -1;
        this.yTile = -1;
        this.zTile = -1;
        this.inTile = 0;
        this.inGround = false;
        this.arrowShake = 0;
        this.ticksFlying = 0;
        this.setSize(0.5f, 0.5f);
        this.standingEyeHeight = 0.0f;
        this.hitBox = 0.3f;
        this.speed = 1.0f;
        this.slowdown = 0.99f;
        this.curvature = 0.03f;
        this.dmg = 4;
        this.precision = 1.0f;
        this.ttlInGround = 1200;
        this.item = null;
    }
    
    @Override
    public void remove() {
        this.shooter = null;
        super.remove();
    }
    
    public void setArrowHeading(double d, double d1, double d2, final float f, final float f1) {
        final float f2 = MathHelper.sqrt(d * d + d1 * d1 + d2 * d2);
        d /= f2;
        d1 /= f2;
        d2 /= f2;
        d += this.rand.nextGaussian() * 0.007499999832361937 * f1;
        d1 += this.rand.nextGaussian() * 0.007499999832361937 * f1;
        d2 += this.rand.nextGaussian() * 0.007499999832361937 * f1;
        d *= f;
        d1 *= f;
        d2 *= f;
        this.velocityX = d;
        this.velocityY = d1;
        this.velocityZ = d2;
        final float f3 = MathHelper.sqrt(d * d + d2 * d2);
        final float n = (float)(Math.atan2(d, d2) * 180.0 / 3.1415927410125732);
        this.yaw = n;
        this.prevYaw = n;
        final float n2 = (float)(Math.atan2(d1, (double)f3) * 180.0 / 3.1415927410125732);
        this.pitch = n2;
        this.prevPitch = n2;
        this.ticksInGround = 0;
    }
    
    @Override
    public void setVelocity(final double velocityX, final double velocityY, final double velocityZ) {
        this.velocityX = velocityX;
        this.velocityY = velocityY;
        this.velocityZ = velocityZ;
        if (this.prevPitch == 0.0f && this.prevYaw == 0.0f) {
            final float f = MathHelper.sqrt(velocityX * velocityX + velocityZ * velocityZ);
            final float n = (float)(Math.atan2(velocityX, velocityZ) * 180.0 / 3.1415927410125732);
            this.yaw = n;
            this.prevYaw = n;
            final float n2 = (float)(Math.atan2(velocityY, (double)f) * 180.0 / 3.1415927410125732);
            this.pitch = n2;
            this.prevPitch = n2;
        }
    }
    
    @Override
    public void tick() {
        super.tick();
        if (this.prevPitch == 0.0f && this.prevYaw == 0.0f) {
            final float f = MathHelper.sqrt(this.velocityX * this.velocityX + this.velocityZ * this.velocityZ);
            final float n = (float)(Math.atan2(this.velocityX, this.velocityZ) * 180.0 / 3.1415927410125732);
            this.yaw = n;
            this.prevYaw = n;
            final float n2 = (float)(Math.atan2(this.velocityY, (double)f) * 180.0 / 3.1415927410125732);
            this.pitch = n2;
            this.prevPitch = n2;
        }
        if (this.arrowShake > 0) {
            --this.arrowShake;
        }
        if (this.inGround) {
            final int i = this.level.getTileId(this.xTile, this.yTile, this.zTile);
            final int k = this.level.getTileMeta(this.xTile, this.yTile, this.zTile);
            if (i == this.inTile && k == this.inData) {
                ++this.ticksInGround;
                this.tickInGround();
                if (this.ticksInGround == this.ttlInGround) {
                    this.remove();
                }
                return;
            }
            this.inGround = false;
            this.velocityX *= this.rand.nextFloat() * 0.2f;
            this.velocityY *= this.rand.nextFloat() * 0.2f;
            this.velocityZ *= this.rand.nextFloat() * 0.2f;
            this.ticksInGround = 0;
            this.ticksFlying = 0;
        }
        else {
            ++this.ticksFlying;
        }
        this.tickFlying();
        Vec3f vec3d = Vec3f.from(this.x, this.y, this.z);
        Vec3f vec3d2 = Vec3f.from(this.x + this.velocityX, this.y + this.velocityY, this.z + this.velocityZ);
        HitResult movingobjectposition = this.level.method_160(vec3d, vec3d2);
        vec3d = Vec3f.from(this.x, this.y, this.z);
        vec3d2 = Vec3f.from(this.x + this.velocityX, this.y + this.velocityY, this.z + this.velocityZ);
        if (movingobjectposition != null) {
            vec3d2 = Vec3f.from(movingobjectposition.field_1988.x, movingobjectposition.field_1988.y, movingobjectposition.field_1988.z);
        }
        EntityBase entity = null;
        final List list = this.level.getEntities(this, this.boundingBox.method_86(this.velocityX, this.velocityY, this.velocityZ).expand(1.0, 1.0, 1.0));
        double d = 0.0;
        for (int j = 0; j < list.size(); ++j) {
            final EntityBase entity2 = (EntityBase)list.get(j);
            if (this.canBeShot(entity2)) {
                final float f2 = this.hitBox;
                final Box axisalignedbb = entity2.boundingBox.expand(f2, f2, f2);
                final HitResult movingobjectposition2 = axisalignedbb.method_89(vec3d, vec3d2);
                if (movingobjectposition2 != null) {
                    final double d2 = vec3d.method_1294(movingobjectposition2.field_1988);
                    if (d2 < d || d == 0.0) {
                        entity = entity2;
                        d = d2;
                    }
                }
            }
        }
        if (entity != null) {
            movingobjectposition = new HitResult(entity);
        }
        if (movingobjectposition != null && this.onHit()) {
            final EntityBase ent = movingobjectposition.field_1989;
            if (ent != null) {
                if (this.onHitTarget(ent)) {
                    if (ent instanceof Living && !(ent instanceof PlayerBase)) {
                        ((LivingAccessor)ent).set1058(0);
                    }
                    ent.damage(this.shooter, this.dmg);
                    this.remove();
                }
            }
            else {
                this.xTile = movingobjectposition.x;
                this.yTile = movingobjectposition.y;
                this.zTile = movingobjectposition.z;
                this.inTile = this.level.getTileId(this.xTile, this.yTile, this.zTile);
                this.inData = this.level.getTileMeta(this.xTile, this.yTile, this.zTile);
                if (this.onHitBlock(movingobjectposition)) {
                    this.velocityX = (float)(movingobjectposition.field_1988.x - this.x);
                    this.velocityY = (float)(movingobjectposition.field_1988.y - this.y);
                    this.velocityZ = (float)(movingobjectposition.field_1988.z - this.z);
                    final float f3 = MathHelper.sqrt(this.velocityX * this.velocityX + this.velocityY * this.velocityY + this.velocityZ * this.velocityZ);
                    this.x -= this.velocityX / f3 * 0.05000000074505806;
                    this.y -= this.velocityY / f3 * 0.05000000074505806;
                    this.z -= this.velocityZ / f3 * 0.05000000074505806;
                    this.inGround = true;
                    this.arrowShake = 7;
                }
                else {
                    this.inTile = 0;
                    this.inData = 0;
                }
            }
        }
        this.x += this.velocityX;
        this.y += this.velocityY;
        this.z += this.velocityZ;
        this.handleMotionUpdate();
        final float f4 = MathHelper.sqrt(this.velocityX * this.velocityX + this.velocityZ * this.velocityZ);
        this.yaw = (float)(Math.atan2(this.velocityX, this.velocityZ) * 180.0 / 3.1415927410125732);
        this.pitch = (float)(Math.atan2(this.velocityY, (double)f4) * 180.0 / 3.1415927410125732);
        while (this.pitch - this.prevPitch < -180.0f) {
            this.prevPitch -= 360.0f;
        }
        while (this.pitch - this.prevPitch >= 180.0f) {
            this.prevPitch += 360.0f;
        }
        while (this.yaw - this.prevYaw < -180.0f) {
            this.prevYaw -= 360.0f;
        }
        while (this.yaw - this.prevYaw >= 180.0f) {
            this.prevYaw += 360.0f;
        }
        this.pitch = this.prevPitch + (this.pitch - this.prevPitch) * 0.2f;
        this.yaw = this.prevYaw + (this.yaw - this.prevYaw) * 0.2f;
        this.method_1338(this.x, this.y, this.z, this.yaw, this.pitch);
    }
    
    public void handleMotionUpdate() {
        float slow = this.slowdown;
        if (this.method_1393()) {
            for (int k = 0; k < 4; ++k) {
                final float f6 = 0.25f;
                this.level.addParticle("bubble", this.x - this.velocityX * f6, this.y - this.velocityY * f6, this.z - this.velocityZ * f6, this.velocityX, this.velocityY, this.velocityZ);
            }
            slow *= 0.8f;
        }
        this.velocityX *= slow;
        this.velocityY *= slow;
        this.velocityZ *= slow;
        this.velocityY -= this.curvature;
    }
    
    public void writeCustomDataToTag(final CompoundTag tag) {
        tag.put("xTile", (short)this.xTile);
        tag.put("yTile", (short)this.yTile);
        tag.put("zTile", (short)this.zTile);
        tag.put("inTile", (byte)this.inTile);
        tag.put("inData", (byte)this.inData);
        tag.put("shake", (byte)this.arrowShake);
        tag.put("inGround", (byte)(byte)(this.inGround ? 1 : 0));
        tag.put("player", this.shotByPlayer);
    }
    
    public void readCustomDataFromTag(final CompoundTag tag) {
        this.xTile = tag.getShort("xTile");
        this.yTile = tag.getShort("yTile");
        this.zTile = tag.getShort("zTile");
        this.inTile = (tag.getByte("inTile") & 0xFF);
        this.inData = (tag.getByte("inData") & 0xFF);
        this.arrowShake = (tag.getByte("shake") & 0xFF);
        this.inGround = (tag.getByte("inGround") == 1);
        this.shotByPlayer = tag.getBoolean("player");
    }
    
    @Override
    public void onPlayerCollision(final PlayerBase entityplayer) {
        if (this.item == null) {
            return;
        }
        if (this.level.isServerSide) {
            return;
        }
        if (this.inGround && this.shotByPlayer && this.arrowShake <= 0 && entityplayer.inventory.addStack(this.item.copy())) {
            this.level.playSound((EntityBase)this, "random.pop", 0.2f, ((this.rand.nextFloat() - this.rand.nextFloat()) * 0.7f + 1.0f) * 2.0f);
            entityplayer.onItemPickup(this, 1);
            this.remove();
        }
    }
    
    public boolean canBeShot(final EntityBase ent) {
        return ent.method_1356() && (ent != this.shooter || this.ticksFlying >= 5) && (!(ent instanceof Living) || ((Living)ent).deathTime <= 0);
    }
    
    public boolean onHit() {
        return true;
    }
    
    public boolean onHitTarget(final EntityBase target) {
        this.level.playSound((EntityBase)this, "random.drr", 1.0f, 1.2f / (this.rand.nextFloat() * 0.2f + 0.9f));
        return true;
    }
    
    public void tickFlying() {
    }
    
    public void tickInGround() {
    }
    
    public boolean onHitBlock(final HitResult mop) {
        return this.onHitBlock();
    }
    
    public boolean onHitBlock() {
        this.level.playSound((EntityBase)this, "random.drr", 1.0f, 1.2f / (this.rand.nextFloat() * 0.2f + 0.9f));
        return true;
    }
    
    @Override
    public float getEyeHeight() {
        return 0.0f;
    }
}
