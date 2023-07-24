package com.gildedgames.aether.entity.projectile;

import net.minecraft.entity.EntityBase;
import net.minecraft.entity.Lightning;
import net.minecraft.entity.player.PlayerBase;
import net.minecraft.level.Level;
import net.minecraft.util.maths.Box;

import java.util.List;

public class EntityAetherLightning extends Lightning
{
    public EntityAetherLightning(Level var1, double var2, double var4, double var6)
    {
        super(var1, var2, var4, var6);
    }

    private boolean strike = false;
    private int ticks = 0;

    @Override
    public void tick()
    {
        ticks++;
        if (!strike)
        {
            strike = true;
            this.level.playSound(this.x, this.y, this.z, "ambient.weather.thunder", 10000.0F, 0.8F + this.rand.nextFloat() * 0.2F);
            this.level.playSound(this.x, this.y, this.z, "random.explode", 2.0F, 0.5F + this.rand.nextFloat() * 0.2F);

            double var6 = 3.0D;
            List<EntityBase> entities = this.level.getEntities(this, Box.create(this.x - var6, this.y - var6, this.z - var6, this.x + var6, this.y + 6.0 + var6, this.z + var6));

            for (EntityBase entity : entities)
            {
                if (!(PlayerBase.class.isAssignableFrom(entity.getClass())))
                {
                    entity.onStruckByLightning(this);
                }
            }
        }
        if (ticks > 20)
        {
            level.removeEntity(this);
        }
    }
}
