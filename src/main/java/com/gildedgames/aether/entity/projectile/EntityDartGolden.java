package com.gildedgames.aether.entity.projectile;

import com.gildedgames.aether.entity.base.EntityProjectileBase;
import com.gildedgames.aether.registry.AetherItems;

import net.minecraft.entity.EntityBase;
import net.minecraft.item.ItemInstance;
import net.minecraft.level.Level;
import net.minecraft.entity.Living;

public class EntityDartGolden extends EntityProjectileBase {
    public Living victim;
    public static int texfxindex;
    
    public EntityDartGolden(final Level level) {
        super(level);
    }
    
    public EntityDartGolden(final Level world, final double x, final double y, final double z) {
        super(world, x, y, z);
    }
    
    public EntityDartGolden(final Level world, final Living ent) {
        super(world, ent);
    }
    
    public void initDataTracker() {
        super.initDataTracker();
        this.item = new ItemInstance(AetherItems.Dart, 1, 0);
        this.curvature = 0.0f;
        this.dmg = 4;
        this.speed = 1.5f;
    }
    
    @Override
    public boolean method_1393() {
        return this.victim == null && super.method_1393();
    }
    
    @Override
    public void remove() {
        this.victim = null;
        super.remove();
    }
    
    @Override
    public boolean onHitBlock() {
        this.curvature = 0.03f;
        this.level.playSound((EntityBase)this, "random.drr", 1.0f, 1.2f / (this.rand.nextFloat() * 0.2f + 0.9f));
        return this.victim == null;
    }
    
    @Override
    public boolean canBeShot(final EntityBase ent) {
        return super.canBeShot(ent) && this.victim == null;
    }
    
    static {
        EntityDartGolden.texfxindex = 94;
    }
}
