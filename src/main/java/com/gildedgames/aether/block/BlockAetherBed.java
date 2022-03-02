package com.gildedgames.aether.block;

import net.minecraft.item.ItemBase;
import java.util.Random;

import com.gildedgames.aether.mixin.PlayerBaseAccessor;
import com.gildedgames.aether.mixin.LivingAccessor;
import net.minecraft.level.BlockView;
import net.minecraft.sortme.MagicBedNumbers;
import net.minecraft.block.Bed;
import net.minecraft.util.Vec3i;
import net.modificationstation.stationapi.api.registry.Identifier;
import net.modificationstation.stationapi.api.template.block.TemplateBlockBase;

import java.util.Iterator;
import net.minecraft.util.SleepStatus;
import net.minecraft.entity.player.PlayerBase;
import net.minecraft.level.Level;
import net.minecraft.block.material.Material;
import net.minecraft.block.BlockBase;

public class BlockAetherBed extends TemplateBlockBase {
    public static final int[][] headBlockToFootBlockMap;
    
    public BlockAetherBed(final Identifier id) {
        super(id, 134, Material.WOOL);
        this.setBounds();
    }
    
    @Override
    public boolean canUse(final Level level, int x, final int y, int z, final PlayerBase player) {
        //if (level.isServerSide) {
        //    return true;
        //}
        int l = level.getTileMeta(x, y, z);
        if (!isBlockFootOfBed(l)) {
            final int i1 = getDirectionFromMetadata(l);
            x += BlockAetherBed.headBlockToFootBlockMap[i1][0];
            z += BlockAetherBed.headBlockToFootBlockMap[i1][1];
            if (level.getTileId(x, y, z) != this.id) {
                return true;
            }
            l = level.getTileMeta(x, y, z);
        }
        if (isBedOccupied(l)) {
            PlayerBase entityplayer1 = null;
            for (final Object objpl : level.players) {
            	PlayerBase entityplayer2 = (PlayerBase)objpl;
                if (entityplayer2.isSleeping()) {
                    final Vec3i chunkcoordinates = entityplayer2.bedPosition;
                    if (chunkcoordinates.x != x || chunkcoordinates.y != y || chunkcoordinates.z != z) {
                        continue;
                    }
                    entityplayer1 = entityplayer2;
                }
            }
            if (entityplayer1 != null) {
                player.sendMessage("tile.bed.occupied");
                return true;
            }
            setBedOccupied(level, x, y, z, false);
        }
        final SleepStatus enumstatus = this.sleepInBedAt(player, x, y, z);
        if (enumstatus == SleepStatus.OK) {
            setBedOccupied(level, x, y, z, true);
            return true;
        }
        if (enumstatus == SleepStatus.DAY_TIME) {
            player.sendMessage("tile.bed.noSleep");
        }
        return true;
    }
    
    public SleepStatus sleepInBedAt(final PlayerBase player, final int i, final int j, final int k) {
        final Level worldObj = player.level;
        //if (!worldObj.isServerSide) {
        if (player.isSleeping() || !player.isAlive()) {
        	return SleepStatus.YOU_SLEEPING_OR_DEAD;
        }
        if (worldObj.isDaylight()) {
            return SleepStatus.DAY_TIME;
        }
        if (Math.abs(player.x - i) > 3.0 || Math.abs(player.y - j) > 2.0 || Math.abs(player.z - k) > 3.0) {
            return SleepStatus.TOO_FAR_AWAY;
        }
        //}
        player.width = 0.2f;
        player.height = 0.2f;
        player.standingEyeHeight = 0.2f;
        if (worldObj.isTileLoaded(i, j, k)) {
            final int l = worldObj.getTileMeta(i, j, k);
            final int i2 = Bed.orientationOnly(l);
            float f = 0.5f;
            float f2 = 0.5f;
            switch (i2) {
                case 0: {
                    f2 = 0.9f;
                    break;
                }
                case 2: {
                    f2 = 0.1f;
                    break;
                }
                case 1: {
                    f = 0.1f;
                    break;
                }
                case 3: {
                    f = 0.9f;
                    break;
                }
            }
            this.func_22052_e(player, i2);
            player.setPosition(i + f, j + 0.9375f, k + f2);
        }
        else {
            player.setPosition(i + 0.5f, j + 0.9375f, k + 0.5f);
        }
        ((PlayerBaseAccessor)player).setSleeping(true);
        final double velocityX = 0.0;
        player.velocityY = velocityX;
        player.velocityZ = velocityX;
        player.velocityX = velocityX;
        //if (!worldObj.isServerSide) {
            worldObj.onPlayerDisconnect();
        //}
        return SleepStatus.OK;
    }
    
    private void func_22052_e(final PlayerBase player, final int i) {
        player.field_509 = 0.0f;
        player.field_510 = 0.0f;
        switch (i) {
            case 0: {
                player.field_510 = -1.8f;
                break;
            }
            case 2: {
                player.field_510 = 1.8f;
                break;
            }
            case 1: {
                player.field_509 = 1.8f;
                break;
            }
            case 3: {
                player.field_509 = -1.8f;
                break;
            }
        }
    }
    
    @Override
    public int getTextureForSide(final int side, final int meta) {
        if (side == 0) {
            return BlockBase.WOOD.texture;
        }
        final int k = getDirectionFromMetadata(meta);
        final int l = MagicBedNumbers.field_794[k][side];
        if (isBlockFootOfBed(meta)) {
            if (l == 2) {
                return this.texture + 2 + 16;
            }
            if (l == 5 || l == 4) {
                return this.texture + 1 + 16;
            }
            return this.texture + 1;
        }
        else {
            if (l == 3) {
                return this.texture - 1 + 16;
            }
            if (l == 5 || l == 4) {
                return this.texture + 16;
            }
            return this.texture;
        }
    }
    
    @Override
    public int getRenderType() {
        return 14;
    }
    
    @Override
    public boolean isFullCube() {
        return false;
    }
    
    @Override
    public boolean isFullOpaque() {
        return false;
    }
    
    @Override
    public void updateBoundingBox(final BlockView tileView, final int x, final int y, final int z) {
        this.setBounds();
    }
    
    @Override
    public void onAdjacentBlockUpdate(final Level level, final int x, final int y, final int z, final int id) {
        final int i1 = level.getTileMeta(x, y, z);
        final int j1 = getDirectionFromMetadata(i1);
        if (isBlockFootOfBed(i1)) {
            if (level.getTileId(x - BlockAetherBed.headBlockToFootBlockMap[j1][0], y, z - BlockAetherBed.headBlockToFootBlockMap[j1][1]) != this.id) {
                level.setTile(x, y, z, 0);
            }
        }
        else if (level.getTileId(x + BlockAetherBed.headBlockToFootBlockMap[j1][0], y, z + BlockAetherBed.headBlockToFootBlockMap[j1][1]) != this.id) {
            level.setTile(x, y, z, 0);
            //if (!level.isServerSide) {
            this.drop(level, x, y, z, i1);
            //}
        }
    }
    
    @Override
    public int getDropId(final int meta, final Random rand) {
        if (isBlockFootOfBed(meta)) {
            return 0;
        }
        return ItemBase.bed.id;
    }
    
    private void setBounds() {
        this.setBoundingBox(0.0f, 0.0f, 0.0f, 1.0f, 0.5625f, 1.0f);
    }
    
    public static int getDirectionFromMetadata(final int i) {
        return i & 0x3;
    }
    
    public static boolean isBlockFootOfBed(final int i) {
        return (i & 0x8) != 0x0;
    }
    
    public static boolean isBedOccupied(final int i) {
        return (i & 0x4) != 0x0;
    }
    
    public static void setBedOccupied(final Level world, final int i, final int j, final int k, final boolean flag) {
        int l = world.getTileMeta(i, j, k);
        if (flag) {
            l |= 0x4;
        }
        else {
            l &= 0xFFFFFFFB;
        }
        world.setTileMeta(i, j, k, l);
    }
    
    public static Vec3i getNearestEmptyChunkCoordinates(final Level world, final int i, final int j, final int k, int l) {
        final int i2 = world.getTileMeta(i, j, k);
        final int j2 = getDirectionFromMetadata(i2);
        for (int k2 = 0; k2 <= 1; ++k2) {
            final int l2 = i - BlockAetherBed.headBlockToFootBlockMap[j2][0] * k2 - 1;
            final int i3 = k - BlockAetherBed.headBlockToFootBlockMap[j2][1] * k2 - 1;
            final int j3 = l2 + 2;
            final int k3 = i3 + 2;
            for (int l3 = l2; l3 <= j3; ++l3) {
                for (int i4 = i3; i4 <= k3; ++i4) {
                    if (world.canSuffocate(l3, j - 1, i4) && world.isAir(l3, j, i4)) {
                        if (world.isAir(l3, j + 1, i4)) {
                            if (l <= 0) {
                                return new Vec3i(l3, j, i4);
                            }
                            --l;
                        }
                    }
                }
            }
        }
        return null;
    }
    
    @Override
    public void beforeDestroyedByExplosion(final Level level, final int x, final int y, final int z, final int meta, final float dropChance) {
        if (!isBlockFootOfBed(meta)) {
            super.beforeDestroyedByExplosion(level, x, y, z, meta, dropChance);
        }
    }
    
    @Override
    public int getPistonPushMode() {
        return 1;
    }
    
    static {
        headBlockToFootBlockMap = new int[][] { { 0, 1 }, { -1, 0 }, { 0, -1 }, { 1, 0 } };
    }
}
