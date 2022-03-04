package com.gildedgames.aether.block;
import net.minecraft.stat.Stats;
import net.modificationstation.stationapi.api.registry.Identifier;
import net.modificationstation.stationapi.api.template.block.TemplateBlockBase;
import net.minecraft.entity.player.PlayerBase;
import net.minecraft.level.Level;
import net.minecraft.block.material.Material;

import com.gildedgames.aether.Aether;
import com.gildedgames.aether.event.listener.TextureListener;

import net.minecraft.block.BlockBase;

public class BlockIcestone extends TemplateBlockBase {
    public BlockIcestone(final Identifier id) {
        super(id, Material.STONE);
    }
    public int getTextureForSide(int side, int meta) {
    	return TextureListener.sprIceStone;
    }
    @Override
    public void onBlockPlaced(final Level level, final int x, final int y, final int z, final int facing) {
        level.setTile(x, y, z, this.id);
        level.setTileMeta(x, y, z, 1);
        for (int x2 = x - 3; x2 < x + 4; ++x2) {
            for (int y2 = y - 1; y2 < y + 2; ++y2) {
                for (int z2 = z - 3; z2 < z + 4; ++z2) {
                    if ((x2 - x) * (x2 - x) + (y2 - y) * (y2 - y) + (z2 - z) * (z2 - z) < 8 && level.getTileId(x2, y2, z2) == BlockBase.STILL_WATER.id) {
                        level.setTile(x2, y2, z2, BlockBase.ICE.id);
                    }
                    if ((x2 - x) * (x2 - x) + (y2 - y) * (y2 - y) + (z2 - z) * (z2 - z) < 8 && level.getTileId(x2, y2, z2) == BlockBase.STILL_LAVA.id) {
                        level.setTile(x2, y2, z2, BlockBase.OBSIDIAN.id);
                    }
                }
            }
        }
    }
    
    @Override
    public void afterBreak(final Level level, final PlayerBase playerBase, final int x, final int y, final int z, final int meta) {
        playerBase.increaseStat(Stats.mineBlock[this.id], 1);
        if (meta == 0 && Aether.equippedSkyrootPick()) {
            this.drop(level, x, y, z, meta);
        }
        this.drop(level, x, y, z, meta);
    }
}
