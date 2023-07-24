package com.gildedgames.aether.item.tool;

import com.gildedgames.aether.effect.AetherPoison;
import com.gildedgames.aether.registry.AetherItems;
import net.minecraft.block.BlockBase;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerBase;
import net.minecraft.item.ItemInstance;
import net.minecraft.level.Level;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.hit.HitType;
import net.minecraft.util.maths.MathHelper;
import net.minecraft.util.maths.Vec3f;
import net.modificationstation.stationapi.api.registry.Identifier;
import net.modificationstation.stationapi.api.template.item.TemplateItemBase;
import org.jetbrains.annotations.NotNull;

public class ItemSkyrootBucket extends TemplateItemBase
{
    public static int sprEmpty;
    public static int sprWater;
    public static int sprMilk;
    public static int sprPoison;
    public static int sprRemedy;

    public ItemSkyrootBucket(final @NotNull Identifier identifier)
    {
        super(identifier);
        this.setHasSubItems(true);
        this.maxStackSize = 1;
    }

    @Override
    public int getTexturePosition(final int damage)
    {
        if (damage == 3)
        {
            return ItemSkyrootBucket.sprRemedy;
        }
        if (damage == 2)
        {
            return ItemSkyrootBucket.sprPoison;
        }
        if (damage == 1)
        {
            return ItemSkyrootBucket.sprMilk;
        }
        if (damage == BlockBase.FLOWING_WATER.id)
        {
            return ItemSkyrootBucket.sprWater;
        }
        return ItemSkyrootBucket.sprEmpty;
    }

    @Override
    public String getTranslationKey(final ItemInstance item)
    {
        int i = item.getDamage();
        if (i > 3 && i != BlockBase.FLOWING_WATER.id)
        {
            i = 0;
        }
        return this.getTranslationKey() + i;
    }

    @Override
    public ItemInstance use(final ItemInstance item, final Level level, final PlayerBase player)
    {
        final float f = 1.0f;
        final float f2 = player.prevPitch + (player.pitch - player.prevPitch) * f;
        final float f3 = player.prevYaw + (player.yaw - player.prevYaw) * f;
        final double d = player.prevX + (player.x - player.prevX) * f;
        final double d2 = player.prevY + (player.y - player.prevY) * f + 1.62 - player.standingEyeHeight;
        final double d3 = player.prevZ + (player.z - player.prevZ) * f;
        final Vec3f vec3d = Vec3f.from(d, d2, d3);
        final float f4 = MathHelper.cos(-f3 * 0.01745329f - 3.141593f);
        final float f5 = MathHelper.sin(-f3 * 0.01745329f - 3.141593f);
        final float f6 = -MathHelper.cos(-f2 * 0.01745329f);
        final float f7 = MathHelper.sin(-f2 * 0.01745329f);
        final float f8 = f5 * f6;
        final float f9 = f7;
        final float f10 = f4 * f6;
        final double d4 = 5.0;
        final Vec3f vec3d2 = vec3d.method_1301(f8 * d4, f9 * d4, f10 * d4);
        final HitResult movingobjectposition = level.method_161(vec3d, vec3d2, item.getDamage() == 0);
        /* todo: inherit bucket?
        if (item.getDamage() == 2 && (MinecraftClientAccessor.getMCinstance().hitResult == null || MinecraftClientAccessor.getMCinstance().hitResult.field_1989 == null ||
        		!(MinecraftClientAccessor.getMCinstance().hitResult.field_1989 instanceof EntityAechorPlant))) {
            if (AetherPoison.afflictPoison()) {
                item.setDamage(0);
                return item;
            }
        }*/
        if (item.getDamage() == 3 && AetherPoison.curePoison())
        {
            item.setDamage(0);
            return item;
        }
        // (HitType) field_789 = TILE, field_790 = ENTITY
        if (movingobjectposition != null && movingobjectposition.type == HitType.field_789 && (item.getDamage() == 0 || item.getDamage() == BlockBase.FLOWING_WATER.id))
        {
            int i = movingobjectposition.x;
            int j = movingobjectposition.y;
            int k = movingobjectposition.z;
            if (!level.method_171(player, i, j, k))
            {
                return item;
            }
            if (item.getDamage() == 0)
            {
                if (level.getMaterial(i, j, k) == Material.WATER && level.getTileMeta(i, j, k) == 0)
                {
                    level.setTile(i, j, k, 0);
                    item.setDamage(BlockBase.FLOWING_WATER.id);
                    return item;
                }
            }
            else
            {
                if (item.getDamage() <= 3 && item.getDamage() != 0)
                {
                    return new ItemInstance(AetherItems.Bucket);
                }
                if (movingobjectposition.field_1987 == 0)
                {
                    --j;
                }
                if (movingobjectposition.field_1987 == 1)
                {
                    ++j;
                }
                if (movingobjectposition.field_1987 == 2)
                {
                    --k;
                }
                if (movingobjectposition.field_1987 == 3)
                {
                    ++k;
                }
                if (movingobjectposition.field_1987 == 4)
                {
                    --i;
                }
                if (movingobjectposition.field_1987 == 5)
                {
                    ++i;
                }
                if (level.isAir(i, j, k) || !level.getMaterial(i, j, k).isSolid())
                {
                    if (level.dimension.evaporatesWater && item.getDamage() == BlockBase.FLOWING_WATER.id)
                    {
                        level.playSound(d + 0.5, d2 + 0.5, d3 + 0.5, "random.fizz", 0.5f, 2.6f + (level.rand.nextFloat() - level.rand.nextFloat()) * 0.8f);
                        for (int l = 0; l < 8; ++l)
                        {
                            level.addParticle("largesmoke", i + Math.random(), j + Math.random(), k + Math.random(), 0.0, 0.0, 0.0);
                        }
                    }
                    else
                    {
                        level.placeBlockWithMetaData(i, j, k, item.getDamage(), 0);
                    }
                    return new ItemInstance(AetherItems.Bucket);
                }
            }
        }
        /* todo: more inherit bucket?
        else if (item.getDamage() == 0 && MinecraftClientAccessor.getMCinstance().hitResult != null && MinecraftClientAccessor.getMCinstance().hitResult.field_1989 != null && (MinecraftClientAccessor.getMCinstance().hitResult.field_1989 instanceof Cow || MinecraftClientAccessor.getMCinstance().hitResult.field_1989 instanceof EntityFlyingCow)) {
            item.setDamage(1);
            return item;
        }*/
        return item;
    }
}
