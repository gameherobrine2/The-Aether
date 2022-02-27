package com.gildedgames.aether.entity.projectile;

import net.minecraft.util.maths.Vec3f;
import net.minecraft.entity.Living;
import net.minecraft.util.io.ListTag;
import net.minecraft.util.io.DoubleTag;
import net.minecraft.util.io.AbstractTag;
import net.minecraft.util.io.CompoundTag;
import net.minecraft.util.maths.MathHelper;

import com.gildedgames.aether.entity.boss.EntityFireMonster;
import com.gildedgames.aether.entity.mobs.EntityFireMinion;

import net.minecraft.entity.EntityBase;
import net.minecraft.level.Level;
import net.minecraft.entity.living.FlyingBase;

public class EntityFiroBall extends FlyingBase {
    public float[] sinage;
    public double smotionX;
    public double smotionY;
    public double smotionZ;
    public int life;
    public int lifeSpan;
    public boolean frosty;
    public boolean smacked;
    public boolean fromCloud;
    private static final double topSpeed = 0.125;
    private static final float sponge = 57.295773f;
    
    public EntityFiroBall(final Level level) {
        super(level);
        this.texture = "/assets/aether/stationapi/textures/entity/firoball.png";
        this.lifeSpan = 300;
        this.life = this.lifeSpan;
        this.setSize(0.9f, 0.9f);
        this.sinage = new float[3];
        this.immuneToFire = true;
        for (int i = 0; i < 3; ++i) {
            this.sinage[i] = this.rand.nextFloat() * 6.0f;
        }
    }
    
    public EntityFiroBall(final Level world, final double x, final double y, final double z, final boolean flag) {
        super(world);
        this.texture = "/assets/aether/stationapi/textures/entity/firoball.png";
        this.lifeSpan = 300;
        this.life = this.lifeSpan;
        this.setSize(0.9f, 0.9f);
        this.method_1338(x, y, z, this.yaw, this.pitch);
        this.sinage = new float[3];
        this.immuneToFire = true;
        for (int i = 0; i < 3; ++i) {
            this.sinage[i] = this.rand.nextFloat() * 6.0f;
        }
        this.smotionX = (0.2 + this.rand.nextFloat() * 0.15) * ((this.rand.nextInt(2) == 0) ? 1.0 : -1.0);
        this.smotionY = (0.2 + this.rand.nextFloat() * 0.15) * ((this.rand.nextInt(2) == 0) ? 1.0 : -1.0);
        this.smotionZ = (0.2 + this.rand.nextFloat() * 0.15) * ((this.rand.nextInt(2) == 0) ? 1.0 : -1.0);
        if (flag) {
            this.frosty = true;
            this.texture = "/assets/aether/stationapi/textures/entity/iceyball.png";
            this.smotionX /= 3.0;
            this.smotionY = 0.0;
            this.smotionZ /= 3.0;
        }
    }
    
    public EntityFiroBall(final Level world, final double x, final double y, final double z, final boolean flag, final boolean flag2) {
        super(world);
        this.texture = "/assets/aether/stationapi/textures/entity/firoball.png";
        this.lifeSpan = 300;
        this.life = this.lifeSpan;
        this.setSize(0.9f, 0.9f);
        this.method_1338(x, y, z, this.yaw, this.pitch);
        this.sinage = new float[3];
        this.immuneToFire = true;
        for (int i = 0; i < 3; ++i) {
            this.sinage[i] = this.rand.nextFloat() * 6.0f;
        }
        this.smotionX = (0.2 + this.rand.nextFloat() * 0.15) * ((this.rand.nextInt(2) == 0) ? 1.0 : -1.0);
        this.smotionY = (0.2 + this.rand.nextFloat() * 0.15) * ((this.rand.nextInt(2) == 0) ? 1.0 : -1.0);
        this.smotionZ = (0.2 + this.rand.nextFloat() * 0.15) * ((this.rand.nextInt(2) == 0) ? 1.0 : -1.0);
        if (flag) {
            this.frosty = true;
            this.texture = "/assets/aether/stationapi/textures/entity/iceyball.png";
            this.smotionX /= 3.0;
            this.smotionY = 0.0;
            this.smotionZ /= 3.0;
        }
        this.fromCloud = flag2;
    }
    
    @Override
    public void tick() {
        super.tick();
        --this.life;
        if (this.life <= 0) {
            this.fizzle();
            this.removed = true;
        }
    }
    
    public void fizzle() {
        if (this.frosty) {
            this.level.playSound((EntityBase)this, "random.glass", 2.0f, (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2f + 1.2f);
        }
        else {
            this.level.playSound((EntityBase)this, "random.fizz", 2.0f, (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2f + 1.2f);
        }
        for (int j = 0; j < 16; ++j) {
            final double a = this.x + (this.rand.nextFloat() - this.rand.nextFloat()) * 0.25;
            final double b = this.y + (this.rand.nextFloat() - this.rand.nextFloat()) * 0.25;
            final double c = this.z + (this.rand.nextFloat() - this.rand.nextFloat()) * 0.25;
            if (!this.frosty) {
                this.level.addParticle("largesmoke", a, b, c, 0.0, 0.0, 0.0);
            }
        }
    }
    
    public void splode() {
        if (this.frosty) {
            this.level.playSound((EntityBase)this, "random.glass", 2.0f, (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2f + 1.2f);
        }
        else {
            this.level.playSound((EntityBase)this, "random.explode", 2.0f, (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2f + 1.2f);
        }
        for (int j = 0; j < 40; ++j) {
            double a = (this.rand.nextFloat() - 0.5f) * 0.5f;
            double b = (this.rand.nextFloat() - 0.5f) * 0.5f;
            double c = (this.rand.nextFloat() - 0.5f) * 0.5f;
            if (this.frosty) {
                a *= 0.5;
                b *= 0.5;
                c *= 0.5;
                this.level.addParticle("snowshovel", this.x, this.y, this.z, a, b + 0.125, c);
            }
            else {
                this.level.addParticle("flame", this.x, this.y, this.z, a, b, c);
            }
        }
    }
    
    public void updateAnims() {
        if (!this.frosty) {
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
    }
    
    public void tickHandSwing() {
        this.velocityX = this.smotionX;
        this.velocityY = this.smotionY;
        this.velocityZ = this.smotionZ;
        if (this.field_1626) {
            if (this.frosty && this.smacked) {
                this.splode();
                this.fizzle();
                this.removed = true;
            }
            else {
                final int i = MathHelper.floor(this.x);
                final int j = MathHelper.floor(this.boundingBox.minY);
                final int k = MathHelper.floor(this.z);
                if (this.smotionX > 0.0 && this.level.getTileId(i + 1, j, k) != 0) {
                    final double n = -this.smotionX;
                    this.smotionX = n;
                    this.velocityX = n;
                }
                else if (this.smotionX < 0.0 && this.level.getTileId(i - 1, j, k) != 0) {
                    final double n2 = -this.smotionX;
                    this.smotionX = n2;
                    this.velocityX = n2;
                }
                if (this.smotionY > 0.0 && this.level.getTileId(i, j + 1, k) != 0) {
                    final double n3 = -this.smotionY;
                    this.smotionY = n3;
                    this.velocityY = n3;
                }
                else if (this.smotionY < 0.0 && this.level.getTileId(i, j - 1, k) != 0) {
                    final double n4 = -this.smotionY;
                    this.smotionY = n4;
                    this.velocityY = n4;
                }
                if (this.smotionZ > 0.0 && this.level.getTileId(i, j, k + 1) != 0) {
                    final double n5 = -this.smotionZ;
                    this.smotionZ = n5;
                    this.velocityZ = n5;
                }
                else if (this.smotionZ < 0.0 && this.level.getTileId(i, j, k - 1) != 0) {
                    final double n6 = -this.smotionZ;
                    this.smotionZ = n6;
                    this.velocityZ = n6;
                }
            }
        }
        this.updateAnims();
    }
    
    @Override
    public void writeCustomDataToTag(final CompoundTag tag) {
        super.writeCustomDataToTag(tag);
        tag.put("LifeLeft", (short)this.life);
        tag.put("SeriousKingVampire", (AbstractTag)this.method_1329(this.smotionX, this.smotionY, this.smotionZ));
        tag.put("Frosty", this.frosty);
        tag.put("FromCloud", this.fromCloud);
        tag.put("Smacked", this.smacked);
    }
    
    @Override
    public void readCustomDataFromTag(final CompoundTag tag) {
        super.readCustomDataFromTag(tag);
        this.life = tag.getShort("LifeLeft");
        this.frosty = tag.getBoolean("Frosty");
        this.fromCloud = tag.getBoolean("FromCloud");
        if (this.frosty) {
            this.texture = "/assets/aether/stationapi/textures/entity/iceyball.png";
        }
        this.smacked = tag.getBoolean("Smacked");
        final ListTag nbttaglist = tag.getListTag("SeriousKingVampire");
        this.smotionX = (float)((DoubleTag)nbttaglist.get(0)).data;
        this.smotionY = (float)((DoubleTag)nbttaglist.get(1)).data;
        this.smotionZ = (float)((DoubleTag)nbttaglist.get(2)).data;
    }
    
    @Override
    public void method_1353(final EntityBase entity) {
        super.method_1353(entity);
        boolean flag = false;
        if (entity != null && entity instanceof Living && !(entity instanceof EntityFiroBall)) {
            if (this.frosty && (!(entity instanceof EntityFireMonster) || (this.smacked && !this.fromCloud)) && !(entity instanceof EntityFireMinion)) {
                flag = entity.damage(this, 5);
            }
            else if (!this.frosty && !(entity instanceof EntityFireMonster) && !(entity instanceof EntityFireMinion)) {
                flag = entity.damage(this, 5);
                if (flag) {
                    entity.fire = 100;
                }
            }
        }
        if (flag) {
            this.splode();
            this.fizzle();
            this.removed = true;
        }
    }
    
    @Override
    public boolean damage(final EntityBase target, final int amount) {
        if (target != null) {
            final Vec3f vec3d = target.method_1320();
            if (vec3d != null) {
                this.smotionX = vec3d.x;
                this.smotionY = vec3d.y;
                this.smotionZ = vec3d.z;
            }
            return this.smacked = true;
        }
        return false;
    }
}
