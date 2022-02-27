package com.gildedgames.aether.entity.projectile;

import net.minecraft.entity.ParticleBase;
import net.minecraft.client.render.particle.Poof;
import net.minecraft.item.ItemBase;
import java.util.List;

import com.gildedgames.aether.effect.AetherPoison;
import com.gildedgames.aether.entity.base.EntityProjectileBase;
import com.gildedgames.aether.mixin.ParticleBaseAccessor;

import net.minecraft.entity.player.AbstractClientPlayer;
import net.minecraft.entity.EntityBase;
import net.minecraft.level.Level;
import net.minecraft.entity.Living;

public class EntityPoisonNeedle extends EntityProjectileBase {
    public Living victim;
    public int poisonTime;
    public static int texfxindex;
    
    public EntityPoisonNeedle(final Level level) {
        super(level);
    }
    
    public EntityPoisonNeedle(final Level world, final double x, final double y, final double z) {
        super(world, x, y, z);
    }
    
    public EntityPoisonNeedle(final Level world, final Living ent) {
        super(world, ent);
    }
    
    public void initDataTracker() {
        super.initDataTracker();
        this.dmg = 0;
        this.speed = 1.5f;
    }
    
    @Override
    public boolean method_1393() {
        return this.victim == null && super.method_1393();
    }
    
    @Override
    public boolean onHitTarget(final EntityBase entity) {
        if (!(entity instanceof Living) || !AetherPoison.canPoison(entity)) {
            return super.onHitTarget(entity);
        }
        final Living ent = (Living)entity;
        if (ent instanceof AbstractClientPlayer) {
            AetherPoison.afflictPoison();
            return super.onHitTarget(entity);
        }
        final List list = this.level.getEntities(this, ent.boundingBox.expand(2.0, 2.0, 2.0));
        for (int i = 0; i < list.size(); ++i) {
            final EntityBase lr2 = (EntityBase)list.get(i);
            if (lr2 instanceof EntityPoisonNeedle) {
                final EntityPoisonNeedle arr = (EntityPoisonNeedle)lr2;
                if (arr.victim == ent) {
                    arr.poisonTime = 500;
                    arr.removed = false;
                    this.remove();
                    return false;
                }
            }
        }
        (this.victim = ent).damage(this.shooter, this.dmg);
        this.poisonTime = 500;
        return false;
    }
    
    @Override
    public void remove() {
        this.victim = null;
        super.remove();
    }
    
    @Override
    public boolean onHitBlock() {
        return this.victim == null;
    }
    
    @Override
    public boolean canBeShot(final EntityBase ent) {
        return super.canBeShot(ent) && this.victim == null;
    }
    
    @Override
    public void tick() {
        super.tick();
        if (this.removed) {
            return;
        }
        if (this.victim != null) {
            if (this.victim.removed || this.poisonTime == 0) {
                this.remove();
                return;
            }
            final ParticleBase fx = new Poof(this.level, this.x, this.y, this.z, ItemBase.slimeball);
            fx.renderDistanceMultiplier = 10.0;
            ((ParticleBaseAccessor)fx).set2635(EntityPoisonNeedle.texfxindex);
            AetherPoison.mc.particleManager.addParticle(fx);
            this.removed = false;
            this.inGround = false;
            this.x = this.victim.x;
            this.y = this.victim.boundingBox.minY + this.victim.height * 0.8;
            this.z = this.victim.z;
            AetherPoison.distractEntity(this.victim);
            --this.poisonTime;
        }
    }
    
    static {
        EntityPoisonNeedle.texfxindex = 94;
    }
}
