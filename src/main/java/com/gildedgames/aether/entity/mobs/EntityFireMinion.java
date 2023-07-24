package com.gildedgames.aether.entity.mobs;

import com.gildedgames.aether.AetherMod;
import net.minecraft.entity.monster.MonsterBase;
import net.minecraft.level.Level;
import net.modificationstation.stationapi.api.registry.Identifier;
import net.modificationstation.stationapi.api.server.entity.MobSpawnDataProvider;

public class EntityFireMinion extends MonsterBase implements MobSpawnDataProvider
{
    public EntityFireMinion(final Level level)
    {
        super(level);
        this.texture = "aether:textures/entity/firemonster.png";
        this.movementSpeed = 1.5f;
        this.attackDamage = 5;
        this.health = 40;
        this.immuneToFire = true;
    }

    @Override
    public void tick()
    {
        super.tick();
        if (this.health > 0)
        {
            for (int j = 0; j < 4; ++j)
            {
                final double a = this.rand.nextFloat() - 0.5f;
                final double b = this.rand.nextFloat();
                final double c = this.rand.nextFloat() - 0.5f;
                final double d = this.x + a * b;
                final double e = this.boundingBox.minY + b - 0.5;
                final double f = this.z + c * b;
                this.level.addParticle("flame", d, e, f, 0.0, -0.07500000298023224, 0.0);
            }
        }
    }

    @Override
    public Identifier getHandlerIdentifier()
    {
        return AetherMod.MODID.id("entity_fireminion");
    }
}
