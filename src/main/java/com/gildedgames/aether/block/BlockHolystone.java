package com.gildedgames.aether.block;

import com.gildedgames.aether.Aether;
import com.gildedgames.aether.event.listener.TextureListener;

import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerBase;
import net.minecraft.item.ItemInstance;
import net.minecraft.level.Level;
import net.minecraft.stat.Stats;
import net.modificationstation.stationapi.api.registry.Identifier;
import net.modificationstation.stationapi.api.template.block.TemplateBlockBase;

public class BlockHolystone extends TemplateBlockBase {

    public BlockHolystone(Identifier identifier) {
        super(identifier, Material.STONE);
    }
    @Override
    public void afterBreak(Level level, PlayerBase playerBase, int x, int y, int z, int meta) {
        int i;
        playerBase.increaseStat(Stats.mineBlock[this.id], 1);
        int i2 = this.id;
        if (meta <= 1) {
            i = 0;
        } else {
            i = 2;
        }
        ItemInstance stack = new ItemInstance(i2, 1, i);
        if (Aether.equippedSkyrootPick() && (meta == 0 || meta == 2)) {
            stack.count *= 2;
        }
        this.drop(level, x, y, z, stack);
    }

    @Override
    public int getTextureForSide(int side, int meta) {
        return TextureListener.sprHolystone;
    }
}
