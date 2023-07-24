package com.gildedgames.aether.entity.base;

import com.gildedgames.aether.registry.AetherBlocks;
import net.minecraft.entity.animal.AnimalBase;
import net.minecraft.level.Level;
import net.minecraft.util.io.CompoundTag;
import net.minecraft.util.maths.MathHelper;
import net.modificationstation.stationapi.api.server.entity.MobSpawnDataProvider;

public abstract class EntityAetherAnimal extends AnimalBase implements MobSpawnDataProvider
{
    public EntityAetherAnimal(Level level)
    {
        super(level);
    }

    @Override
    protected float getPathfindingFavour(int x, int y, int z)
    {
        if (this.level.getTileId(x, y - 1, z) == AetherBlocks.AETHER_GRASS_BLOCK.id)
        {
            return 10.0f;
        }
        return this.level.getBrightness(x, y, z) - 0.5f;
    }

    @Override
    public void writeCustomDataToTag(CompoundTag tag)
    {
        super.writeCustomDataToTag(tag);
    }

    @Override
    public void readCustomDataFromTag(CompoundTag tag)
    {
        super.readCustomDataFromTag(tag);
    }

    @Override
    public boolean canSpawn()
    {
        int integer2 = MathHelper.floor(this.x);
        int integer3 = MathHelper.floor(this.boundingBox.minY);
        int integer4 = MathHelper.floor(this.z);
        return this.level.canSpawnEntity(this.boundingBox) && this.level.method_190(this, this.boundingBox).size() == 0 && !this.level.method_218(this.boundingBox) && this.level.getTileId(integer2, integer3 - 1, integer4) == AetherBlocks.AETHER_GRASS_BLOCK.id && this.level.getLightLevel(integer2, integer3, integer4) > 8 && this.getPathfindingFavour(integer2, integer3, integer4) >= 0.0f;
    }

    @Override
    public int method_936()
    {
        return 120;
    }
}
