package com.gildedgames.aether.block;
import java.util.Random;

import com.gildedgames.aether.entity.mobs.EntityMimic;

import net.minecraft.entity.player.PlayerBase;
import net.minecraft.level.Level;
import net.modificationstation.stationapi.api.registry.Identifier;
import net.modificationstation.stationapi.api.template.block.TemplateBlockBase;
import net.minecraft.level.BlockView;
import net.minecraft.block.material.Material;
import net.minecraft.block.BlockBase;

public class BlockChestMimic extends TemplateBlockBase {
    public BlockChestMimic(final Identifier id) {
        super(id, Material.WOOD);
        this.texture = 26;
    }
    
    @Override
    public int getTextureForSide(final BlockView tileView, final int x, final int y, final int z, final int meta) {
        if (meta == 1) {
            return this.texture - 1;
        }
        if (meta == 0) {
            return this.texture - 1;
        }
        final int i1 = tileView.getTileId(x, y, z - 1);
        final int j1 = tileView.getTileId(x, y, z + 1);
        final int k1 = tileView.getTileId(x - 1, y, z);
        final int l1 = tileView.getTileId(x + 1, y, z);
        if (i1 == this.id || j1 == this.id) {
            if (meta == 2 || meta == 3) {
                return this.texture;
            }
            int i2 = 0;
            if (i1 == this.id) {
                i2 = -1;
            }
            final int k2 = tileView.getTileId(x - 1, y, (i1 != this.id) ? (z + 1) : (z - 1));
            final int i3 = tileView.getTileId(x + 1, y, (i1 != this.id) ? (z + 1) : (z - 1));
            if (meta == 4) {
                i2 = -1 - i2;
            }
            byte byte1 = 5;
            if ((BlockBase.FULL_OPAQUE[k1] || BlockBase.FULL_OPAQUE[k2]) && !BlockBase.FULL_OPAQUE[l1] && !BlockBase.FULL_OPAQUE[i3]) {
                byte1 = 5;
            }
            if ((BlockBase.FULL_OPAQUE[l1] || BlockBase.FULL_OPAQUE[i3]) && !BlockBase.FULL_OPAQUE[k1] && !BlockBase.FULL_OPAQUE[k2]) {
                byte1 = 4;
            }
            return ((meta != byte1) ? (this.texture + 32) : (this.texture + 16)) + i2;
        }
        else {
            if (k1 != this.id && l1 != this.id) {
                byte byte2 = 3;
                if (BlockBase.FULL_OPAQUE[i1] && !BlockBase.FULL_OPAQUE[j1]) {
                    byte2 = 3;
                }
                if (BlockBase.FULL_OPAQUE[j1] && !BlockBase.FULL_OPAQUE[i1]) {
                    byte2 = 2;
                }
                if (BlockBase.FULL_OPAQUE[k1] && !BlockBase.FULL_OPAQUE[l1]) {
                    byte2 = 5;
                }
                if (BlockBase.FULL_OPAQUE[l1] && !BlockBase.FULL_OPAQUE[k1]) {
                    byte2 = 4;
                }
                return (meta != byte2) ? this.texture : (this.texture + 1);
            }
            if (meta == 4 || meta == 5) {
                return this.texture;
            }
            int j2 = 0;
            if (k1 == this.id) {
                j2 = -1;
            }
            final int l2 = tileView.getTileId((k1 != this.id) ? (x + 1) : (x - 1), y, z - 1);
            final int j3 = tileView.getTileId((k1 != this.id) ? (x + 1) : (x - 1), y, z + 1);
            if (meta == 3) {
                j2 = -1 - j2;
            }
            byte byte3 = 3;
            if ((BlockBase.FULL_OPAQUE[i1] || BlockBase.FULL_OPAQUE[l2]) && !BlockBase.FULL_OPAQUE[j1] && !BlockBase.FULL_OPAQUE[j3]) {
                byte3 = 3;
            }
            if ((BlockBase.FULL_OPAQUE[j1] || BlockBase.FULL_OPAQUE[j3]) && !BlockBase.FULL_OPAQUE[i1] && !BlockBase.FULL_OPAQUE[l2]) {
                byte3 = 2;
            }
            return ((meta != byte3) ? (this.texture + 32) : (this.texture + 16)) + j2;
        }
    }
    
    @Override
    public int getTextureForSide(final int side) {
        if (side == 1) {
            return this.texture - 1;
        }
        if (side == 0) {
            return this.texture - 1;
        }
        if (side == 3) {
            return this.texture + 1;
        }
        return this.texture;
    }
    
    @Override
    public boolean canPlaceAt(final Level level, final int x, final int y, final int z) {
        int l = 0;
        if (level.getTileId(x - 1, y, z) == this.id) {
            ++l;
        }
        if (level.getTileId(x + 1, y, z) == this.id) {
            ++l;
        }
        if (level.getTileId(x, y, z - 1) == this.id) {
            ++l;
        }
        if (level.getTileId(x, y, z + 1) == this.id) {
            ++l;
        }
        return l <= 1 && !this.isThereANeighborChest(level, x - 1, y, z) && !this.isThereANeighborChest(level, x + 1, y, z) && !this.isThereANeighborChest(level, x, y, z - 1) && !this.isThereANeighborChest(level, x, y, z + 1);
    }
    
    private boolean isThereANeighborChest(final Level world, final int i, final int j, final int k) {
        return world.getTileId(i, j, k) == this.id && (world.getTileId(i - 1, j, k) == this.id || world.getTileId(i + 1, j, k) == this.id || world.getTileId(i, j, k - 1) == this.id || world.getTileId(i, j, k + 1) == this.id);
    }
    
    @Override
    public void onBlockRemoved(final Level level, final int x, final int y, final int z) {
        //level.setTile(x, y, z, 0);
        final EntityMimic mimic = new EntityMimic(level);
        mimic.setPosition(x + 0.5, y + 1.5, z + 0.5);
        level.spawnEntity(mimic);
    }
    
    @Override
    public boolean canUse(final Level level, final int x, final int y, final int z, final PlayerBase player) {
        level.setTile(x, y, z, 0);
        return true;
    }
    
    @Override
    public int getDropCount(final Random rand) {
        return 0;
    }
}
