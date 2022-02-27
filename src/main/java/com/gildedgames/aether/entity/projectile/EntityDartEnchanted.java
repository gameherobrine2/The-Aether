package com.gildedgames.aether.entity.projectile;

import net.minecraft.item.ItemInstance;
import net.minecraft.level.Level;

import com.gildedgames.aether.registry.AetherItems;

import net.minecraft.entity.Living;

public class EntityDartEnchanted extends EntityDartGolden {
    public Living victim;
    public static int texfxindex;
    
    public EntityDartEnchanted(final Level level) {
        super(level);
    }
    
    public EntityDartEnchanted(final Level world, final double x, final double y, final double z) {
        super(world, x, y, z);
    }
    
    public EntityDartEnchanted(final Level world, final Living ent) {
        super(world, ent);
    }
    
    @Override
    public void initDataTracker() {
        super.initDataTracker();
        this.item = new ItemInstance(AetherItems.Dart, 1, 2);
        this.dmg = 6;
    }
    
    static {
        EntityDartEnchanted.texfxindex = 94;
    }
}
