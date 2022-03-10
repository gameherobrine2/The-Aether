package com.gildedgames.aether.item;

import net.minecraft.util.hit.HitResult;
import net.minecraft.entity.EntityBase;
import net.minecraft.util.hit.HitType;
import net.minecraft.util.maths.MathHelper;
import net.minecraft.util.maths.Vec3f;
import net.modificationstation.stationapi.api.registry.Identifier;
import net.modificationstation.stationapi.api.template.item.tool.TemplateToolBase;
import net.minecraft.entity.player.PlayerBase;
import net.minecraft.level.Level;
import net.minecraft.item.ItemInstance;
import net.minecraft.item.tool.ToolMaterial;
import java.util.Random;

import org.jetbrains.annotations.NotNull;

import com.gildedgames.aether.entity.projectile.EntityFloatingBlock;
import com.gildedgames.aether.registry.AetherBlocks;

import net.minecraft.block.BlockBase;
import net.minecraft.item.tool.ToolBase;

public class ItemGravititeSpade extends TemplateToolBase {
    private static BlockBase[] blocksEffectiveAgainst;
    private static Random random;
    
    public ItemGravititeSpade(final @NotNull Identifier identifier, final ToolMaterial enumtoolmaterial) {
        super(identifier, 1, enumtoolmaterial, ItemGravititeSpade.blocksEffectiveAgainst);
    }
    
    @Override
    public boolean isEffectiveOn(final BlockBase tile) {
        for (int i = 0; i < ItemGravititeSpade.blocksEffectiveAgainst.length; ++i) {
            if (ItemGravititeSpade.blocksEffectiveAgainst[i].id == tile.id) {
                return true;
            }
        }
        return false;
    }
    
    @Override
    public ItemInstance use(final ItemInstance item, final Level level, final PlayerBase player) {
        final float f1 = player.pitch;
        final float f2 = player.yaw;
        final double d = player.x;
        final double d2 = player.y + 1.62 - player.standingEyeHeight;
        final double d3 = player.z;
        final Vec3f vec3d = Vec3f.from(d, d2, d3);
        final float f3 = MathHelper.cos(-f2 * 0.01745329f - 3.141593f);
        final float f4 = MathHelper.sin(-f2 * 0.01745329f - 3.141593f);
        final float f5 = -MathHelper.cos(-f1 * 0.01745329f);
        final float f6 = MathHelper.sin(-f1 * 0.01745329f);
        final float f7 = f4 * f5;
        final float f8 = f6;
        final float f9 = f3 * f5;
        final double d4 = 5.0;
        final Vec3f vec3d2 = vec3d.method_1301(f7 * d4, f8 * d4, f9 * d4);
        final HitResult movingobjectposition = level.method_161(vec3d, vec3d2, false);
        if (movingobjectposition == null) {
            return item;
        }
        if (movingobjectposition.type == HitType.TILE) {
            final int i = movingobjectposition.x;
            final int j = movingobjectposition.y;
            final int k = movingobjectposition.z;
            if (!level.isServerSide) {
                int blockID = level.getTileId(i, j, k);
                final int metadata = level.getTileMeta(i, j, k);
                for (int n = 0; n < ItemGravititeSpade.blocksEffectiveAgainst.length; ++n) {
                    if (blockID == ItemGravititeSpade.blocksEffectiveAgainst[n].id) {
                        if (blockID == AetherBlocks.AETHER_GRASS_BLOCK.id) {
                            blockID = AetherBlocks.AETHER_DIRT.id;
                        }
                        final EntityFloatingBlock floating = new EntityFloatingBlock(level, i + 0.5f, j + 0.5f, k + 0.5f, blockID, metadata);
                        level.spawnEntity(floating);
                    }
                }
            }
            item.applyDamage(4, player);
        }
        return item;
    }
    
    static {
        ItemGravititeSpade.blocksEffectiveAgainst = new BlockBase[] { AetherBlocks.QUICKSOIL, AetherBlocks.AETHER_DIRT, AetherBlocks.AETHER_GRASS_BLOCK, AetherBlocks.AERCLOUD };
        ItemGravititeSpade.random = new Random();
    }
}
