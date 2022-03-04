package com.gildedgames.aether.entity.animal;

import com.gildedgames.aether.Aether;
import com.gildedgames.aether.entity.base.EntityAetherAnimal;
import com.gildedgames.aether.mixin.LivingAccessor;
import com.gildedgames.aether.mixin.EntityBaseAccessor;
import net.minecraft.entity.Living;
import net.minecraft.entity.player.PlayerBase;
import net.minecraft.item.ItemBase;
import net.minecraft.level.Level;
import net.minecraft.util.io.CompoundTag;

public class EntityFlyingCow extends EntityAetherAnimal {
    public boolean getSaddled;
    public float wingFold;
    public float wingAngle;
    private float aimingForFold;
    public int jumps;
    public int jrem;
    private boolean jpress;
    private int ticks;
    
    public EntityFlyingCow(Level level) {
        super(level);
        this.getSaddled = false;
        this.texture = "/assets/aether/stationapi/textures/entity/Mob_FlyingCowBase.png";
        this.setSize(0.9f, 1.3f);
        this.jrem = 0;
        this.jumps = 1;
        this.ticks = 0;
        this.field_1641 = 1.0f;
        this.field_1622 = true;
    }
    
    @Override
    protected boolean method_940() {
        return !this.getSaddled;
    }
    
    @Override
    protected boolean canClimb() {
        return this.onGround;
    }
    
    @Override
    protected void initDataTracker() {
        this.dataTracker.startTracking(16, 0);
    }
    
    @Override
    public void writeCustomDataToTag(CompoundTag tag) {
        super.writeCustomDataToTag(tag);
        tag.put("Jumps", (short)this.jumps);
        tag.put("Remaining", (short)this.jrem);
        tag.put("getSaddled", this.getSaddled);
    }
    
    @Override
    public void readCustomDataFromTag(CompoundTag tag) {
        super.readCustomDataFromTag(tag);
        this.jumps = tag.getShort("Jumps");
        this.jrem = tag.getShort("Remaining");
        this.getSaddled = tag.getBoolean("getSaddled");
        if (this.getSaddled) {
            this.texture = "/assets/aether/stationapi/textures/entity/Mob_FlyingCowSaddle.png";
        }
    }
    
    @Override
    protected void jump() {
        this.velocityY = 0.6;
    }
    
    @Override
    public void tick() {
        super.tick();
        if (this.onGround) {
            this.wingAngle *= 0.8f;
            this.aimingForFold = 0.1f;
            this.jpress = false;
            this.jrem = this.jumps;
        }
        else {
            this.aimingForFold = 1.0f;
        }
        ++this.ticks;
        this.wingAngle = this.wingFold * (float)Math.sin(this.ticks / 31.830988f);
        this.wingFold += (this.aimingForFold - this.wingFold) / 5.0f;
        this.fallDistance = 0.0f;
        if (this.velocityY < -0.2) {
            this.velocityY = -0.2;
        }
    }
    
    public void tickHandSwing() {
        //if (this.level.isServerSide) {
         //   return;
        //}
        if (this.passenger != null && this.passenger instanceof Living) {
            this.field_1029 = 0.0f;
            this.field_1060 = 0.0f;
            this.jumping = false;
            ((EntityBaseAccessor)this.passenger).setFallDistance(0.0f);
            float field_1606 = this.passenger.yaw;
            this.yaw = field_1606;
            this.prevYaw = field_1606;
            float field_1607 = this.passenger.pitch;
            this.pitch = field_1607;
            this.prevPitch = field_1607;
            Living living2 = (Living)this.passenger;
            float float3 = 3.141593f;
            float float4 = float3 / 180.0f;
            if (((LivingAccessor)living2).get1029() > 0.1f) {
                float float5 = living2.yaw * float4;
                this.velocityX += ((LivingAccessor)living2).get1029() * -Math.sin(float5) * 0.17499999701976776;
                this.velocityZ += ((LivingAccessor)living2).get1029() * Math.cos(float5) * 0.17499999701976776;
            }
            else if (((LivingAccessor)living2).get1029() < -0.1f) {
                float float5 = living2.yaw * float4;
                this.velocityX += ((LivingAccessor)living2).get1029() * -Math.sin(float5) * 0.17499999701976776;
                this.velocityZ += ((LivingAccessor)living2).get1029() * Math.cos(float5) * 0.17499999701976776;
            }
            if (((LivingAccessor)living2).get1060() > 0.1f) {
                float float5 = living2.yaw * float4;
                this.velocityX += ((LivingAccessor)living2).get1060() * Math.cos(float5) * 0.17499999701976776;
                this.velocityZ += ((LivingAccessor)living2).get1060() * Math.sin(float5) * 0.17499999701976776;
            }
            else if (((LivingAccessor)living2).get1060() < -0.1f) {
                float float5 = living2.yaw * float4;
                this.velocityX += ((LivingAccessor)living2).get1060() * Math.cos(float5) * 0.17499999701976776;
                this.velocityZ += ((LivingAccessor)living2).get1060() * Math.sin(float5) * 0.17499999701976776;
            }
            if (this.onGround && ((LivingAccessor)living2).getJumping()) {
                this.onGround = false;
                this.velocityY = 1.4;
                this.jpress = true;
                --this.jrem;
            }
            else if (this.method_1393() && ((LivingAccessor)living2).getJumping()) {
                this.velocityY = 0.5;
                this.jpress = true;
                --this.jrem;
            }
            else if (this.jrem > 0 && !this.jpress && ((LivingAccessor)living2).getJumping()) {
                this.velocityY = 1.2;
                this.jpress = true;
                --this.jrem;
            }
            if (this.jpress && !((LivingAccessor)living2).getJumping()) {
                this.jpress = false;
            }
            double double5 = Math.abs(Math.sqrt(this.velocityX * this.velocityX + this.velocityZ * this.velocityZ));
            if (double5 > 0.375) {
                double double7 = 0.375 / double5;
                this.velocityX *= double7;
                this.velocityZ *= double7;
            }
            return;
        }
        super.tickHandSwing();
    }
    
    @Override
    protected String getAmbientSound() {
        return "mob.cow";
    }
    
    @Override
    protected String getHurtSound() {
        return "mob.cowhurt";
    }
    
    @Override
    protected String getDeathSound() {
        return "mob.cowhurt";
    }
    
    @Override
    protected float getSoundVolume() {
        return 0.4f;
    }
    
    @Override
    public boolean interact(PlayerBase playerBase) {
        if (!this.getSaddled && playerBase.inventory.getHeldItem() != null && playerBase.inventory.getHeldItem().itemId == ItemBase.saddle.id) {
            playerBase.inventory.setInventoryItem(playerBase.inventory.selectedHotbarSlot, null);
            this.getSaddled = true;
            this.texture = "/assets/aether/stationapi/textures/entity/Mob_FlyingCowSaddle.png";
            return true;
        }
        if (this.getSaddled /*&& !this.level.isServerSide*/ && (this.passenger == null || this.passenger == playerBase)) {
            playerBase.startRiding(this);
            return true;
        }
        return false;
    }
    
    @Override
    protected void getDrops() {
        if (Aether.equippedSkyrootSword()) {
            this.dropItem(ItemBase.leather.id, 4);
        }
        else {
            this.dropItem(ItemBase.leather.id, 2);
        }
    }
}
