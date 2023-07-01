package com.gildedgames.aether.entity.projectile;

import com.gildedgames.aether.effect.AetherPoison;
import com.gildedgames.aether.mixin.access.ParticleBaseAccessor;
import com.gildedgames.aether.registry.AetherItems;
import net.minecraft.client.render.particle.Poof;
import net.minecraft.entity.EntityBase;
import net.minecraft.entity.Living;
import net.minecraft.entity.ParticleBase;
import net.minecraft.entity.player.PlayerBase;
import net.minecraft.item.ItemBase;
import net.minecraft.item.ItemInstance;
import net.minecraft.level.Level;

import java.util.List;

public class EntityDartPoison extends EntityDartGolden {
    public Living victim;
    public int poisonTime;
    public static int texfxindex;
    
    public EntityDartPoison(final Level level) {
        super(level);
    }
    
    public EntityDartPoison(final Level world, final double x, final double y, final double z) {
        super(world, x, y, z);
    }
    
    public EntityDartPoison(final Level world, final Living ent) {
        super(world, ent);
    }
    
    @Override
    public void initDataTracker() {
        super.initDataTracker();
        this.item = new ItemInstance(AetherItems.Dart, 1, 1);
        this.dmg = 2;
    }
    
    @Override
    public boolean onHitTarget(final EntityBase entity) {
        if (!(entity instanceof Living) || !AetherPoison.canPoison(entity)) {
            return super.onHitTarget(entity);
        }
        final Living ent = (Living)entity;
        if (ent instanceof PlayerBase) {
            AetherPoison.afflictPoison();
            return super.onHitTarget(entity);
        }
        final List list = this.level.getEntities(this, ent.boundingBox.expand(2.0, 2.0, 2.0));
        for (int i = 0; i < list.size(); ++i) {
            final EntityBase lr2 = (EntityBase)list.get(i);
            if (lr2 instanceof EntityDartPoison) {
                final EntityDartPoison arr = (EntityDartPoison)lr2;
                if (arr.victim == ent) {
                    arr.poisonTime = 500;
                    arr.removed = false;
                    ent.damage(this.shooter, this.dmg);
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
            ((ParticleBaseAccessor)fx).set2635(EntityDartPoison.texfxindex);
            AetherPoison.mc.particleManager.addParticle(fx);
            this.removed = false;
            this.inGround = false;
            this.x = this.victim.x;
            this.y = this.victim.boundingBox.minY + this.victim.height * 0.8;
            this.z = this.victim.z;
            AetherPoison.distractEntity(this.victim);
            --this.poisonTime;
            if (this.poisonTime % 50 == 0) {
                this.victim.damage(this.shooter, 1);
            }
        }
    }
    
    static {
        EntityDartPoison.texfxindex = 94;
    }
}
