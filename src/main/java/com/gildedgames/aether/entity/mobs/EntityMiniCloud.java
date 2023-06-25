package com.gildedgames.aether.entity.mobs;

import net.minecraft.util.maths.Vec3f;
import net.minecraft.util.io.CompoundTag;

import com.gildedgames.aether.Aether;
import com.gildedgames.aether.entity.projectile.EntityFiroBall;

import net.minecraft.client.gui.screen.menu.MainMenu;
import net.minecraft.entity.EntityBase;
import net.minecraft.entity.player.PlayerBase;
import net.minecraft.level.Level;
import net.minecraft.entity.Living;
import net.minecraft.entity.living.FlyingBase;

public class EntityMiniCloud extends FlyingBase {
    public int shotTimer;
    public int lifeSpan;
    public boolean gotPlayer;
    public boolean toLeft;
    public Living dude;
    public double targetX;
    public double targetY;
    public double targetZ;
    
    public EntityMiniCloud(final Level level) {
        super(level);
        this.texture = "aether:textures/entity/minicloud.png";
        this.setSize(0.0f, 0.0f);
        this.field_1642 = true;
        this.field_1643 = 1.75f;
    }
    
    public EntityMiniCloud(final Level world, final PlayerBase ep, final boolean flag) {
        super(world);
        this.texture = "aether:textures/entity/minicloud.png";
        this.setSize(0.5f, 0.45f);
        this.dude = ep;
        this.toLeft = flag;
        this.lifeSpan = 3600;
        this.getTargetPos();
        this.setPosition(this.targetX, this.targetY, this.targetZ);
        this.pitch = this.dude.pitch;
        this.yaw = this.dude.yaw;
        this.field_1643 = 1.75f;
        this.onSpawnedFromSpawner();
    }
    
    @Override
    public boolean shouldRenderAtDistance(final double d) {
        return true;
    }
    
    public void getTargetPos() {
        if (this.distanceTo(this.dude) > 2.0f) {
            this.targetX = this.dude.x;
            this.targetY = this.dude.y - 0.10000000149011612;
            this.targetZ = this.dude.z;
        }
        else {
            double angle = this.dude.yaw;
            if (this.toLeft) {
                angle -= 90.0;
            }
            else {
                angle += 90.0;
            }
            angle /= -57.29577319531843;
            this.targetX = this.dude.x + Math.sin(angle) * 1.05;
            this.targetY = this.dude.y - 0.10000000149011612;
            this.targetZ = this.dude.z + Math.cos(angle) * 1.05;
        }
    }
    
    public boolean atShoulder() {
        final double a = this.x - this.targetX;
        final double b = this.y - this.targetY;
        final double c = this.z - this.targetZ;
        return Math.sqrt(a * a + b * b + c * c) < 0.3;
    }
    
    public void approachTarget() {
        final double a = this.targetX - this.x;
        final double b = this.targetY - this.y;
        final double c = this.targetZ - this.z;
        final double d = Math.sqrt(a * a + b * b + c * c) * 3.25;
        this.velocityX = (this.velocityX + a / d) / 2.0;
        this.velocityY = (this.velocityY + b / d) / 2.0;
        this.velocityZ = (this.velocityZ + c / d) / 2.0;
        final double angle = Math.atan2(a, c);
    }
    
    protected EntityBase findPlayer() {
        final PlayerBase entityplayer = this.level.getClosestPlayerTo(this, 16.0);
        if (entityplayer != null && this.method_928(entityplayer) && !Aether.mmactive) { 
            return entityplayer;
        }
        return null;
    }
    
    @Override
    public void writeCustomDataToTag(final CompoundTag tag) {
        super.writeCustomDataToTag(tag);
        tag.put("LifeSpan", (short)this.lifeSpan);
        tag.put("ShotTimer", (short)this.shotTimer);
        tag.put("GotPlayer", this.gotPlayer = (this.dude != null));
        tag.put("ToLeft", this.toLeft);
    }
    
    @Override
    public void readCustomDataFromTag(final CompoundTag tag) {
        super.readCustomDataFromTag(tag);
        this.lifeSpan = tag.getShort("LifeSpan");
        this.shotTimer = tag.getShort("ShotTimer");
        this.gotPlayer = tag.getBoolean("GotPlayer");
        this.toLeft = tag.getBoolean("ToLeft");
    }
    
    public void tickHandSwing() {
        super.tickHandSwing();
        --this.lifeSpan;
        if (this.lifeSpan <= 0) {
            this.onSpawnedFromSpawner();
            this.removed = true;
            return;
        }
        if (this.shotTimer > 0) {
            --this.shotTimer;
        }
        if (this.gotPlayer && this.dude == null) {
            this.gotPlayer = false;
            this.dude = (Living)this.findPlayer();
        }
        if (this.dude == null || this.dude.removed) {
            this.onSpawnedFromSpawner();
            this.removed = true;
            return;
        }
        this.getTargetPos();
        if (this.atShoulder()) {
            this.velocityX *= 0.65;
            this.velocityY *= 0.65;
            this.velocityZ *= 0.65;
            this.yaw = this.dude.yaw + (this.toLeft ? 1.0f : -1.0f);
            this.pitch = this.dude.pitch;
            if (this.shotTimer <= 0 && this.dude instanceof PlayerBase && ((PlayerBase)this.dude).handSwinging) {
                final float spanish = this.yaw - (this.toLeft ? 1.0f : -1.0f);
                final double a = this.x + Math.sin(spanish / -57.29577319531843) * 1.6;
                final double b = this.y - 0.25;
                final double c = this.z + Math.cos(spanish / -57.29577319531843) * 1.6;
                final EntityFiroBall eh = new EntityFiroBall(this.level, a, b, c, true, true);
                this.level.spawnEntity(eh);
                final Vec3f vec3d = this.method_1320();
                if (vec3d != null) {
                    eh.smotionX = vec3d.x * 1.5;
                    eh.smotionY = vec3d.y * 1.5;
                    eh.smotionZ = vec3d.z * 1.5;
                }
                eh.smacked = true;
                this.level.playSound((EntityBase)this, "assets/aether/stationapi/aether.sound.mobs.zephyr.zephyrshoot", 0.75f, (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2f + 1.0f);
                this.shotTimer = 40;
            }
        }
        else {
            this.approachTarget();
        }
    }
    
    @Override
    public boolean damage(final EntityBase target, final int amount) {
        return (target == null || target != this.dude) && super.damage(target, amount);
    }
}
