package com.gildedgames.aether.block;

import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerBase;
import net.minecraft.level.Level;
import net.minecraft.stat.Stats;
import net.modificationstation.stationapi.api.registry.Identifier;
import net.modificationstation.stationapi.api.template.block.TemplateBlockBase;

public class AetherDirt extends TemplateBlockBase {

    public AetherDirt(Identifier identifier) {
        super(identifier, Material.DIRT);
    }

    @Override
    public boolean isFullOpaque() {
        return true;
    }
    
    @Override
    public void onBlockPlaced(final Level level, final int x, final int y, final int z, final int facing) {
        level.setTile(x, y, z, this.id);
        level.setTileMeta(x, y, z, 1);
    }
    
    @Override
    public void afterBreak(final Level level, final PlayerBase playerBase, final int x, final int y, final int z, final int meta) {
        playerBase.increaseStat(Stats.mineBlock[this.id], 1);
        //if (meta == 0 && mod_Aether.equippedSkyrootShovel()) {
        //    this.drop(level, x, y, z, meta);
        //}
        this.drop(level, x, y, z, meta);
    }
}
