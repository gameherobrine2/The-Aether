package com.gildedgames.aether.entity.projectile;

import com.gildedgames.aether.AetherMod;
import com.gildedgames.aether.block.BlockFloating;
import com.gildedgames.aether.registry.AetherBlocks;
import net.minecraft.entity.EntityBase;
import net.minecraft.entity.FallingBlock;
import net.minecraft.level.Level;
import net.minecraft.util.io.CompoundTag;
import net.minecraft.util.maths.MathHelper;
import net.modificationstation.stationapi.api.registry.Identifier;
import net.modificationstation.stationapi.api.server.entity.MobSpawnDataProvider;

import java.util.List;

public class EntityFloatingBlock extends EntityBase implements MobSpawnDataProvider
{
    public int blockID;
    public int metadata;
    public int flytime;

    public EntityFloatingBlock(final Level level)
    {
        super(level);
        this.flytime = 0;
    }

    public EntityFloatingBlock(final Level world, final double d, final double d1, final double d2, final int i, final int j)
    {
        super(world);
        this.flytime = 0;
        this.blockID = i;
        this.metadata = j;
        this.field_1593 = true;
        this.setSize(0.98f, 0.98f);
        this.standingEyeHeight = this.height / 2.0f;
        this.setPosition(d, d1, d2);
        this.velocityX = 0.0;
        this.velocityY = 0.0;
        this.velocityZ = 0.0;
        this.prevX = d;
        this.prevY = d1;
        this.prevZ = d2;
    }

    public EntityFloatingBlock(final Level world, final double d, final double d1, final double d2, final int i)
    {
        this(world, d, d1, d2, i, 0);
    }

    @Override
    protected boolean canClimb()
    {
        return false;
    }

    @Override
    protected void initDataTracker()
    {
    }

    @Override
    public boolean method_1356()
    {
        return !this.removed;
    }

    @Override
    public void tick()
    {
        if (this.blockID == 0)
        {
            this.remove();
            return;
        }
        this.prevX = this.x;
        this.prevY = this.y;
        this.prevZ = this.z;
        ++this.flytime;
        this.velocityY += 0.04;
        this.move(this.velocityX, this.velocityY, this.velocityZ);
        this.velocityX *= 0.9800000190734863;
        this.velocityY *= 0.9800000190734863;
        this.velocityZ *= 0.9800000190734863;
        final int i = MathHelper.floor(this.x);
        final int j = MathHelper.floor(this.y);
        final int k = MathHelper.floor(this.z);
        if (this.level.getTileId(i, j, k) == this.blockID || (this.level.getTileId(i, j, k) == AetherBlocks.AETHER_GRASS_BLOCK.id && this.blockID == AetherBlocks.AETHER_DIRT.id))
        {
            this.level.setTile(i, j, k, 0);
        }
        final List list = this.level.getEntities(this, this.boundingBox.expand(0.0, 1.0, 0.0));
        for (int n = 0; n < list.size(); ++n)
        {
            if (list.get(n) instanceof FallingBlock && this.level.canPlaceTile(this.blockID, i, j, k, true, 1))
            {
                this.level.placeBlockWithMetaData(i, j, k, this.blockID, this.metadata);
                this.remove();
            }
        }
        if (this.field_1625 && !this.onGround)
        {
            this.velocityX *= 0.699999988079071;
            this.velocityZ *= 0.699999988079071;
            this.velocityY *= -0.5;
            this.remove();
            if ((!this.level.canPlaceTile(this.blockID, i, j, k, true, 1) || BlockFloating.canFallAbove(this.level, i, j + 1, k) || !this.level.placeBlockWithMetaData(i, j, k, this.blockID, this.metadata)) && !this.level.isServerSide)
            {
            }
        }
        else if (this.flytime > 100 && !this.level.isServerSide)
        {
            this.remove();
        }
    }

    @Override
    protected void writeCustomDataToTag(final CompoundTag tag)
    {
        tag.put("Tile", (byte) this.blockID);
    }

    @Override
    protected void readCustomDataFromTag(final CompoundTag tag)
    {
        this.blockID = (tag.getByte("Tile") & 0xFF);
    }

    @Override
    public float getEyeHeight()
    {
        return 0.0f;
    }

    public Level getWorld()
    {
        return this.level;
    }

    @Override
    public Identifier getHandlerIdentifier()
    {
        return AetherMod.MODID.id("entity_floatingblock");
    }
}
