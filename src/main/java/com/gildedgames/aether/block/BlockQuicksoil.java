package com.gildedgames.aether.block;
import net.minecraft.stat.Stats;
import net.modificationstation.stationapi.api.registry.Identifier;
import net.modificationstation.stationapi.api.template.block.TemplateBlockBase;
import net.minecraft.entity.player.PlayerBase;
import net.minecraft.level.Level;
import net.minecraft.block.material.Material;

import com.gildedgames.aether.event.listener.TextureListener;

import net.minecraft.block.BlockBase;

public class BlockQuicksoil extends TemplateBlockBase {
    public BlockQuicksoil(final Identifier id) {
        super(id, Material.SAND);
        this.slipperiness = 1.1f;
    }
    public int getTextureForSide(int side, int meta) {
    	return TextureListener.sprQuicksoil;
    }
    @Override
    public void afterBreak(final Level level, final PlayerBase playerBase, final int x, final int y, final int z, final int meta) {
        playerBase.increaseStat(Stats.mineBlock[this.id], 1);
        //if (meta == 0 && mod_Aether.equippedSkyrootShovel()) {
            this.drop(level, x, y, z, meta);
        //}
        this.drop(level, x, y, z, meta);
    }
}
