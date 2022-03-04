package com.gildedgames.aether.entity.animal;
import net.minecraft.item.ItemBase;
import net.minecraft.entity.player.PlayerBase;
import net.minecraft.util.io.CompoundTag;
import net.minecraft.entity.Living;
import net.minecraft.item.ItemInstance;
import net.minecraft.entity.EntityBase;
import net.minecraft.level.Level;

import com.gildedgames.aether.Aether;
import com.gildedgames.aether.entity.base.EntityAetherAnimal;
import com.gildedgames.aether.mixin.EntityBaseAccessor;
import com.gildedgames.aether.mixin.LivingAccessor;
import com.gildedgames.aether.mixin.MinecraftClientAccessor;
import com.gildedgames.aether.registry.AetherItems;
import com.gildedgames.aether.utils.MoaColour;

import net.minecraft.client.Minecraft;

public class EntityMoa extends EntityAetherAnimal {
    public static Minecraft mc;
    public float field_752_b;
    public float destPos;
    public float field_757_d;
    public float field_756_e;
    public float field_755_h;
    public int timeUntilNextEgg;
    public int jrem;
    int petalsEaten;
    boolean wellFed;
    boolean followPlayer;
    public boolean jpress;
    public boolean baby;
    public boolean grown;
    public boolean saddled;
    public MoaColour colour;
    
    public EntityMoa(final Level level) {
        this(level, false, false, false);
    }
    
    public EntityMoa(final Level world, final boolean babyBool, final boolean grownBool, final boolean saddledBool) {
        this(world, babyBool, grownBool, saddledBool, MoaColour.pickRandomMoa());
    }
    
    public EntityMoa(final Level world, final boolean babyBool, final boolean grownBool, final boolean saddledBool, final MoaColour moaColour) {
        super(world);
        this.petalsEaten = 0;
        this.wellFed = false;
        this.followPlayer = false;
        this.baby = false;
        this.grown = false;
        this.saddled = false;
        this.destPos = 0.0f;
        this.field_755_h = 1.0f;
        this.field_1641 = 1.0f;
        this.jrem = 0;
        this.baby = babyBool;
        this.grown = grownBool;
        this.saddled = saddledBool;
        if (this.baby) {
            this.setSize(0.4f, 0.5f);
        }
        this.colour = moaColour;
        this.texture = this.colour.getTexture(this.saddled);
        this.setSize(1.0f, 2.0f);
        this.health = 40;
        this.timeUntilNextEgg = this.rand.nextInt(6000) + 6000;
    }
    
    @Override
    public void tick() {
        super.tick();
        this.field_1622 = (this.passenger == EntityMoa.mc.player);
    }
    
    @Override
    public void updateDespawnCounter() {
        super.updateDespawnCounter();
        this.field_756_e = this.field_752_b;
        this.field_757_d = this.destPos;
        this.destPos += (float)((this.onGround ? -1 : 4) * 0.05);
        if (this.destPos < 0.01f) {
            this.destPos = 0.01f;
        }
        if (this.destPos > 1.0f) {
            this.destPos = 1.0f;
        }
        if (this.onGround) {
            this.destPos = 0.0f;
            this.jpress = false;
            this.jrem = this.colour.jumps;
        }
        if (!this.onGround && this.field_755_h < 1.0f) {
            this.field_755_h = 1.0f;
        }
        this.field_755_h *= (float)0.9;
        if (!this.onGround && this.velocityY < 0.0) {
            if (this.passenger == null) {
                this.velocityY *= 0.6;
            }
            else {
                this.velocityY *= 0.6375;
            }
        }
        this.field_752_b += this.field_755_h * 2.0f;
        if (!this.level.isServerSide && !this.baby && --this.timeUntilNextEgg <= 0) {
            this.level.playSound((EntityBase)this, "mob.chickenplop", 1.0f, (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2f + 1.0f);
            this.dropItem(new ItemInstance(AetherItems.MoaEgg, 1, this.colour.ID), 0.0f);
            this.timeUntilNextEgg = this.rand.nextInt(6000) + 6000;
        }
        if (this.wellFed && this.rand.nextInt(2000) == 0) {
            this.wellFed = false;
        }
        if (this.saddled && this.passenger == null) {
            this.movementSpeed = 0.0f;
        }
        else {
            this.movementSpeed = 0.7f;
        }
    }
    
    @Override
    protected void handleFallDamage(final float height) {
    }
    
    @Override
    public boolean damage(final EntityBase target, final int amount) {
        final boolean flag = super.damage(target, amount);
        if (flag && this.passenger != null && (this.health <= 0 || this.rand.nextInt(3) == 0)) {
            this.passenger.startRiding(this);
        }
        return flag;
    }
    
    public void tickHandSwing() {
        if (this.level.isServerSide) {
            return;
        }
        if (this.passenger != null && this.passenger instanceof Living) {
            this.field_1029 = 0.0f;
            this.field_1060 = 0.0f;
            this.jumping = false;
            ((EntityBaseAccessor)this.passenger).setFallDistance(0.0f);
            final float yaw = this.passenger.yaw;
            this.yaw = yaw;
            this.prevYaw = yaw;
            final float pitch = this.passenger.pitch;
            this.pitch = pitch;
            this.prevPitch = pitch;
            final Living entityliving = (Living)this.passenger;
            final float f = 3.141593f;
            final float f2 = f / 180.0f;
            if (((LivingAccessor)entityliving).get1029() > 0.1f) {
                final float f3 = entityliving.yaw * f2;
                this.velocityX += ((LivingAccessor)entityliving).get1029() * -Math.sin((double)f3) * 0.17499999701976776;
                this.velocityZ += ((LivingAccessor)entityliving).get1029() * Math.cos((double)f3) * 0.17499999701976776;
            }
            else if (((LivingAccessor)entityliving).get1029() < -0.1f) {
                final float f4 = entityliving.yaw * f2;
                this.velocityX += ((LivingAccessor)entityliving).get1029() * -Math.sin((double)f4) * 0.17499999701976776;
                this.velocityZ += ((LivingAccessor)entityliving).get1029() * Math.cos((double)f4) * 0.17499999701976776;
            }
            if (((LivingAccessor)entityliving).get1060() > 0.1f) {
                final float f5 = entityliving.yaw * f2;
                this.velocityX += ((LivingAccessor)entityliving).get1060() * Math.cos((double)f5) * 0.17499999701976776;
                this.velocityZ += ((LivingAccessor)entityliving).get1060() * Math.sin((double)f5) * 0.17499999701976776;
            }
            else if (((LivingAccessor)entityliving).get1060() < -0.1f) {
                final float f6 = entityliving.yaw * f2;
                this.velocityX += ((LivingAccessor)entityliving).get1060() * Math.cos((double)f6) * 0.17499999701976776;
                this.velocityZ += ((LivingAccessor)entityliving).get1060() * Math.sin((double)f6) * 0.17499999701976776;
            }
            if (this.onGround && ((LivingAccessor)entityliving).getJumping()) {
                this.onGround = false;
                this.velocityY = 0.875;
                this.jpress = true;
                --this.jrem;
            }
            else if (this.method_1393() && ((LivingAccessor)entityliving).getJumping()) {
                this.velocityY = 0.5;
                this.jpress = true;
                --this.jrem;
            }
            else if (this.jrem > 0 && !this.jpress && ((LivingAccessor)entityliving).getJumping()) {
                this.velocityY = 0.75;
                this.jpress = true;
                --this.jrem;
            }
            if (this.jpress && !((LivingAccessor)entityliving).getJumping()) {
                this.jpress = false;
            }
            final double d = Math.abs(Math.sqrt(this.velocityX * this.velocityX + this.velocityZ * this.velocityZ));
            if (d > 0.375) {
                final double d2 = 0.375 / d;
                this.velocityX *= d2;
                this.velocityZ *= d2;
            }
            return;
        }
        super.tickHandSwing();
    }
    
    @Override
    public void writeCustomDataToTag(final CompoundTag tag) {
        super.writeCustomDataToTag(tag);
        tag.put("Remaining", (short)this.jrem);
        tag.put("ColourNumber", (short)this.colour.ID);
        tag.put("Baby", this.baby);
        tag.put("Grown", this.grown);
        tag.put("Saddled", this.saddled);
        tag.put("wellFed", this.wellFed);
        tag.put("petalsEaten", this.petalsEaten);
        tag.put("followPlayer", this.followPlayer);
    }
    
    @Override
    public void readCustomDataFromTag(final CompoundTag tag) {
        super.readCustomDataFromTag(tag);
        this.jrem = tag.getShort("Remaining");
        this.colour = MoaColour.getColour(tag.getShort("ColourNumber"));
        this.baby = tag.getBoolean("Baby");
        this.grown = tag.getBoolean("Grown");
        this.saddled = tag.getBoolean("Saddled");
        this.wellFed = tag.getBoolean("wellFed");
        this.petalsEaten = tag.getInt("petalsEaten");
        this.followPlayer = tag.getBoolean("followPlayer");
        if (this.baby) {
            this.grown = false;
            this.saddled = false;
        }
        if (this.grown) {
            this.baby = false;
            this.saddled = false;
        }
        if (this.saddled) {
            this.baby = false;
            this.grown = false;
        }
        this.texture = this.colour.getTexture(this.saddled);
    }
    
    @Override
    protected String getAmbientSound() {
        return "aether.sound.mobs.moa.idleCall";
    }
    
    @Override
    protected String getHurtSound() {
        return "aether.sound.mobs.moa.idleCall";
    }
    
    @Override
    protected String getDeathSound() {
        return "aether.sound.mobs.moa.idleCall";
    }
    
    @Override
    public boolean interact(final PlayerBase entityplayer) {
        if (!this.saddled && this.grown && !this.baby && entityplayer.inventory.getHeldItem() != null && entityplayer.inventory.getHeldItem().itemId == ItemBase.saddle.id) {
            entityplayer.inventory.setInventoryItem(entityplayer.inventory.selectedHotbarSlot, null);
            this.saddled = true;
            this.grown = false;
            this.texture = this.colour.getTexture(this.saddled);
            return true;
        }
        if (this.saddled && !this.level.isServerSide && (this.passenger == null || this.passenger == entityplayer)) {
            entityplayer.startRiding(this);
            final float yaw = this.yaw;
            entityplayer.yaw = yaw;
            entityplayer.prevYaw = yaw;
            return true;
        }
        if (!this.wellFed && !this.saddled && this.baby && !this.grown) {
            final ItemInstance itemstack = entityplayer.inventory.getHeldItem();
            if (itemstack != null && itemstack.itemId == AetherItems.AechorPetal.id) {
                ++this.petalsEaten;
                entityplayer.inventory.takeInventoryItem(entityplayer.inventory.selectedHotbarSlot, 1);
                if (this.petalsEaten > this.colour.jumps) {
                    this.grown = true;
                    this.baby = false;
                }
                this.wellFed = true;
            }
            return true;
        }
        if (!this.saddled && (this.baby || this.grown)) {
            if (!this.followPlayer) {
                this.followPlayer = true;
                this.entity = entityplayer;
            }
            else {
                this.followPlayer = false;
                this.entity = null;
            }
        }
        return true;
    }
    
    public boolean method_940() {
        return !this.baby && !this.grown && !this.saddled;
    }
    
    @Override
    protected boolean canClimb() {
        return this.onGround;
    }
    
    @Override
    protected void getDrops() {
        this.dropItem(ItemBase.feather.id, 3 * (Aether.equippedSkyrootSword() ? 2 : 1));
    }
    
    static {
        EntityMoa.mc = MinecraftClientAccessor.getMCinstance();
    }
}
