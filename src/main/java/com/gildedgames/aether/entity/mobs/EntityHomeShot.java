package com.gildedgames.aether.entity.mobs;

import net.minecraft.entity.player.PlayerBase;
import net.minecraft.util.io.CompoundTag;
import net.minecraft.entity.EntityBase;
import net.minecraft.entity.Lightning;
import net.minecraft.level.Level;
import net.minecraft.entity.Living;
import net.minecraft.entity.living.FlyingBase;

public class EntityHomeShot extends FlyingBase {
    public float[] sinage;
    public Living target;
    public boolean firstRun;
    public int life;
    public int lifeSpan;
    private static final double topSpeed = 0.125;
    private static final float sponge = 57.295773f;
    
    public EntityHomeShot(final Level level) {
        super(level);
        this.texture = "/aether:textures/entity/electroball.png";
        this.lifeSpan = 200;
        this.life = this.lifeSpan;
        this.setSize(0.7f, 0.7f);
        this.firstRun = true;
        this.sinage = new float[3];
        this.immuneToFire = true;
        for (int i = 0; i < 3; ++i) {
            this.sinage[i] = this.rand.nextFloat() * 6.0f;
        }
    }
    
    public EntityHomeShot(final Level world, final double x, final double y, final double z, final Living ep) {
        super(world);
        this.texture = "/aether:textures/entity/electroball.png";
        this.lifeSpan = 200;
        this.life = this.lifeSpan;
        this.setSize(0.7f, 0.7f);
        this.setPosition(x, y, z);
        this.target = ep;
        this.sinage = new float[3];
        this.immuneToFire = true;
        for (int i = 0; i < 3; ++i) {
            this.sinage[i] = this.rand.nextFloat() * 6.0f;
        }
    }
    
    @Override
    public void tick() {
        super.tick();
        --this.life;
        if (this.firstRun && this.target == null) {
            this.target = (Living)this.findPlayerToAttack();
            this.firstRun = false;
        }
        if (this.target == null || this.target.removed || this.target.health <= 0) {
            this.removed = true;
        }
        else if (this.life <= 0) {
            final Lightning thunder = new Lightning(this.level, this.x, this.y, this.z);
            this.level.spawnEntity(thunder);
            this.removed = true;
        }
        else {
            this.updateAnims();
            this.faceIt();
            this.moveIt(this.target, 0.02);
        }
    }
    
    public void moveIt(final EntityBase e1, final double sped) {
        final double angle1 = this.yaw / 57.295773f;
        this.velocityX -= Math.sin(angle1) * sped;
        this.velocityZ += Math.cos(angle1) * sped;
        final double a = e1.y - 0.75;
        if (a < this.boundingBox.minY - 0.5) {
            this.velocityY -= sped / 2.0;
        }
        else if (a > this.boundingBox.minY + 0.5) {
            this.velocityY += sped / 2.0;
        }
        else {
            this.velocityY += (a - this.boundingBox.minY) * (sped / 2.0);
        }
        if (this.onGround) {
            this.onGround = false;
            this.velocityY = 0.10000000149011612;
        }
    }
    
    public void faceIt() {
        this.method_924(this.target, 10.0f, 10.0f);
    }
    
    public void updateAnims() {
        for (int i = 0; i < 3; ++i) {
            final float[] sinage = this.sinage;
            final int n = i;
            sinage[n] += 0.3f + i * 0.13f;
            if (this.sinage[i] > 6.283186f) {
                final float[] sinage2 = this.sinage;
                final int n2 = i;
                sinage2[n2] -= 6.283186f;
            }
        }
    }
    
    @Override
    public void writeCustomDataToTag(final CompoundTag tag) {
        super.writeCustomDataToTag(tag);
        tag.put("LifeLeft", (short)this.life);
    }
    
    @Override
    public void readCustomDataFromTag(final CompoundTag tag) {
        super.readCustomDataFromTag(tag);
        this.life = tag.getShort("LifeLeft");
    }
    
    public void checkOverLimit() {
        final double a = this.target.x - this.x;
        final double b = this.target.y - this.y;
        final double c = this.target.z - this.z;
        final double d = Math.sqrt(a * a + b * b + c * c);
        if (d > 0.125) {
            final double e = 0.125 / d;
            this.velocityX *= e;
            this.velocityY *= e;
            this.velocityZ *= e;
        }
    }
    
    public EntityBase findPlayerToAttack() {
        final PlayerBase entityplayer = this.level.getClosestPlayerTo(this, 16.0);
        if (entityplayer != null && this.method_928(entityplayer)) {
            return entityplayer;
        }
        return null;
    }
    
    @Override
    public void method_1353(final EntityBase entity) {
        super.method_1353(entity);
        if (entity != null && this.target != null && entity == this.target) {
            final boolean flag = entity.damage(this, 1);
            if (flag) {
                this.moveIt(entity, -0.1);
            }
        }
    }
    
    @Override
    public boolean damage(final EntityBase target, final int amount) {
        if (target != null) {
            this.moveIt(target, -0.15 - amount / 8.0);
            return true;
        }
        return false;
    }
}
