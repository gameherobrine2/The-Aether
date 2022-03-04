package com.gildedgames.aether.entity.animal;

import net.minecraft.util.io.CompoundTag;
import net.minecraft.item.ItemInstance;
import java.util.List;

import com.gildedgames.aether.Aether;
import com.gildedgames.aether.entity.base.EntityAetherAnimal;
import com.gildedgames.aether.entity.projectile.EntityPoisonNeedle;
import com.gildedgames.aether.registry.AetherBlocks;
import com.gildedgames.aether.registry.AetherItems;

import net.minecraft.entity.player.PlayerBase;
import net.minecraft.entity.monster.Creeper;
import net.minecraft.entity.EntityBase;
import net.minecraft.util.maths.MathHelper;
import net.minecraft.level.Level;
import net.minecraft.entity.Living;

public class EntityAechorPlant extends EntityAetherAnimal {
    public Living target;
    public int size;
    public int attTime;
    public int smokeTime;
    public boolean seeprey;
    public boolean grounded;
    public boolean noDespawn;
    public float sinage;
    private int poisonLeft;
    
    public EntityAechorPlant(final Level level) {
        super(level);
        this.texture = "/assets/aether/stationapi/textures/entity/aechorplant.png";
        this.size = this.rand.nextInt(4) + 1;
        this.health = 10 + this.size * 2;
        this.sinage = this.rand.nextFloat() * 6.0f;
        final int n = 0;
        this.attTime = n;
        this.smokeTime = n;
        this.seeprey = false;
        this.setSize(0.75f + this.size * 0.125f, 0.5f + this.size * 0.075f);
        this.setPosition(this.x, this.y, this.z);
        this.poisonLeft = 2;
    }
    
    @Override
    public int getLimitPerChunk() {
        return 3;
    }
    
    @Override
    public boolean canSpawn() {
        final int i = MathHelper.floor(this.x);
        final int j = MathHelper.floor(this.boundingBox.minY);
        final int k = MathHelper.floor(this.z);
        return this.level.getTileId(i, j - 1, k) == AetherBlocks.AETHER_GRASS_BLOCK.id && this.level.placeTile(i, j, k) > 8 && super.canSpawn();
    }
    
    @Override
    public void updateDespawnCounter() {
        if (this.health <= 0 || !this.grounded) {
            super.updateDespawnCounter();
            if (this.health <= 0) {
                return;
            }
        }
        else {
            ++this.despawnCounter;
            this.method_920();
        }
        if (this.onGround) {
            this.grounded = true;
        }
        if (this.hurtTime > 0) {
            this.sinage += 0.9f;
        }
        else if (this.seeprey) {
            this.sinage += 0.3f;
        }
        else {
            this.sinage += 0.1f;
        }
        if (this.sinage > 6.283186f) {
            this.sinage -= 6.283186f;
        }
        if (this.target == null) {
            final List list = this.level.getEntities(this, this.boundingBox.expand(10.0, 10.0, 10.0));
            for (int j = 0; j < list.size(); ++j) {
                final EntityBase entity1 = (EntityBase)list.get(j);
                if (entity1 instanceof Living && !(entity1 instanceof EntityAechorPlant) && !(entity1 instanceof Creeper)) {
                    if (entity1 instanceof PlayerBase) {
                        final PlayerBase player1 = (PlayerBase)entity1;
                        final boolean flag = false;
                        if (flag) {
                            continue;
                        }
                    }
                    this.target = (Living)entity1;
                    break;
                }
            }
        }
        if (this.target != null) {
            if (this.target.removed || this.target.distanceTo(this) > 12.0) {
                this.target = null;
                this.attTime = 0;
            }
            else if (this.target instanceof PlayerBase) {
                final PlayerBase player2 = (PlayerBase)this.target;
                final boolean flag2 = false;
                if (flag2) {
                    this.target = null;
                    this.attTime = 0;
                }
            }
            if (this.target != null && this.attTime >= 20 && this.method_928(this.target) && this.target.distanceTo(this) < 5.5 + this.size / 2.0) {
                this.shootTarget();
                this.attTime = -10;
            }
            if (this.attTime < 20) {
                ++this.attTime;
            }
        }
        ++this.smokeTime;
        if (this.smokeTime >= (this.seeprey ? 3 : 8)) {
            this.smokeTime = 0;
            final int i = MathHelper.floor(this.x);
            final int j = MathHelper.floor(this.boundingBox.minY);
            final int k = MathHelper.floor(this.z);
            if (this.level.getTileId(i, j - 1, k) != AetherBlocks.AETHER_GRASS_BLOCK.id && this.grounded) {
                this.removed = true;
            }
        }
        this.seeprey = (this.target != null);
    }
    
    @Override
    public void remove() {
        if (!this.noDespawn || this.health <= 0) {
            super.remove();
        }
    }
    
    public void shootTarget() {
        if (this.level.difficulty == 0) {
            return;
        }
        double d1 = this.target.x - this.x;
        double d2 = this.target.z - this.z;
        final double d3 = 1.5 / Math.sqrt(d1 * d1 + d2 * d2 + 0.1);
        final double d4 = 0.1 + Math.sqrt(d1 * d1 + d2 * d2 + 0.1) * 0.5 + (this.y - this.target.y) * 0.25;
        d1 *= d3;
        d2 *= d3;
        final EntityPoisonNeedle entityarrow = new EntityPoisonNeedle(this.level, this);
        entityarrow.y = this.y + 0.5;
        this.level.playSound((EntityBase)this, "aether.sound.other.dartshooter.shootDart", 2.0f, 1.0f / (this.rand.nextFloat() * 0.4f + 0.8f));
        this.level.spawnEntity(entityarrow);
        entityarrow.setArrowHeading(d1, d4, d2, 0.285f + (float)d4 * 0.05f, 1.0f);
    }
    
    @Override
    protected String getHurtSound() {
        return "damage.hurtflesh";
    }
    
    @Override
    protected String getDeathSound() {
        return "damage.fallbig";
    }
    
    @Override
    public void method_925(final EntityBase entity, final int ii, final double dd, final double dd1) {
        for (int i = 0; i < 8; ++i) {
            final double d1 = this.x + (this.rand.nextFloat() - this.rand.nextFloat()) * 0.5;
            final double d2 = this.y + 0.25 + (this.rand.nextFloat() - this.rand.nextFloat()) * 0.5;
            final double d3 = this.z + (this.rand.nextFloat() - this.rand.nextFloat()) * 0.5;
            final double d4 = (this.rand.nextFloat() - this.rand.nextFloat()) * 0.5;
            final double d5 = (this.rand.nextFloat() - this.rand.nextFloat()) * 0.5;
            this.level.addParticle("portal", d1, d2, d3, d4, 0.25, d5);
        }
        if (this.health > 0) {
            return;
        }
        super.method_925(entity, ii, dd, dd1);
    }
    
    @Override
    public boolean interact(final PlayerBase entityplayer) {
        final boolean flag = false;
        final ItemInstance stack = entityplayer.inventory.getHeldItem();
        if (stack != null && stack.itemId == AetherItems.Bucket.id && this.poisonLeft > 0) {
            --this.poisonLeft;
            entityplayer.inventory.setInventoryItem(entityplayer.inventory.selectedHotbarSlot, null);
            entityplayer.inventory.setInventoryItem(entityplayer.inventory.selectedHotbarSlot, new ItemInstance(AetherItems.Bucket, 1, 2));
            return true;
        }
        if (flag) {
            this.noDespawn = true;
            final String s = "heart";
            for (int i = 0; i < 7; ++i) {
                final double d = this.rand.nextGaussian() * 0.02;
                final double d2 = this.rand.nextGaussian() * 0.02;
                final double d3 = this.rand.nextGaussian() * 0.02;
                this.level.addParticle(s, this.x + this.rand.nextFloat() * this.width * 2.0f - this.width, this.y + 0.5 + this.rand.nextFloat() * this.height, this.z + this.rand.nextFloat() * this.width * 2.0f - this.width, d, d2, d3);
            }
        }
        return false;
    }
    
    @Override
    public void writeCustomDataToTag(final CompoundTag tag) {
        super.writeCustomDataToTag(tag);
        tag.put("Grounded", this.grounded);
        tag.put("NoDespawn", this.noDespawn);
        tag.put("AttTime", (short)this.attTime);
        tag.put("Size", (short)this.size);
    }
    
    @Override
    public void readCustomDataFromTag(final CompoundTag tag) {
        super.readCustomDataFromTag(tag);
        this.grounded = tag.getBoolean("Grounded");
        this.noDespawn = tag.getBoolean("NoDespawn");
        this.attTime = tag.getShort("AttTime");
        this.size = tag.getShort("Size");
        this.setSize(0.75f + this.size * 0.125f, 0.5f + this.size * 0.075f);
        this.setPosition(this.x, this.y, this.z);
    }
    
    @Override
    protected void getDrops() {
        this.dropItem(AetherItems.AechorPetal.id, 2 * (Aether.equippedSkyrootSword() ? 2 : 1));
    }
}
