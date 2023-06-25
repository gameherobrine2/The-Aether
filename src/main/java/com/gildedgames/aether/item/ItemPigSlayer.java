package com.gildedgames.aether.item;

import org.jetbrains.annotations.NotNull;

import com.gildedgames.aether.mixin.access.EntityBaseAccessor;
import com.gildedgames.aether.mixin.access.LivingAccessor;
import net.minecraft.entity.EntityRegistry;
import net.minecraft.entity.Living;
import net.minecraft.item.ItemInstance;
import net.minecraft.item.tool.ToolMaterial;
import net.modificationstation.stationapi.api.registry.Identifier;
import net.modificationstation.stationapi.api.template.item.tool.TemplateSword;

public class ItemPigSlayer extends TemplateSword {
    public ItemPigSlayer(final @NotNull Identifier identifier) {
        // field_1690 is IRON
        super(identifier, ToolMaterial.field_1690);
        this.setDurability(0);
    }
    
    @Override
    public boolean postHit(final ItemInstance itemstack, final Living damageSource, final Living damageTarget) {
        if (damageSource == null || damageTarget == null) {
            return false;
        }
        final String s = EntityRegistry.getStringId(damageSource);
        if (!s.equals("") && s.toLowerCase().contains("pig")) {
            if (damageSource.health > 0) {
                damageSource.health = 1;
                damageSource.hurtTime = 0;
                damageSource.damage(damageTarget, 9999);
            }
            for (int j = 0; j < 20; ++j) {
                final double d = ((EntityBaseAccessor)damageTarget).getRand().nextGaussian() * 0.02;
                final double d2 = ((EntityBaseAccessor)damageTarget).getRand().nextGaussian() * 0.02;
                final double d3 = ((EntityBaseAccessor)damageTarget).getRand().nextGaussian() * 0.02;
                final double d4 = 5.0;
                damageSource.level.addParticle("flame", damageSource.x + ((EntityBaseAccessor)damageSource).getRand().nextFloat() * damageSource.width * 2.0f - damageSource.width - d * d4, damageSource.y + ((EntityBaseAccessor)damageSource).getRand().nextFloat() * damageSource.height - d2 * d4, damageSource.z + ((EntityBaseAccessor)damageSource).getRand().nextFloat() * damageSource.width * 2.0f - damageSource.width - d3 * d4, d, d2, d3);
            }
            ((LivingAccessor)damageSource).invokeGetDrops();
            damageSource.removed = true;
        }
        return true;
    }
    
    public boolean func_25008_a(final ItemInstance itemstack, final int i, final int j, final int k, final int l, final Living entityliving) {
        return true;
    }
}
