package com.gildedgames.aether.block;
import net.minecraft.level.BlockView;
import net.modificationstation.stationapi.api.registry.Identifier;
import net.modificationstation.stationapi.api.template.block.TemplateBlockBase;
import net.minecraft.block.material.Material;
import net.minecraft.block.BlockBase;

public class BlockZanite extends TemplateBlockBase {
    public BlockZanite(final Identifier id) {
        super(id, Material.STONE);
        this.texture = BlockBase.IRON_BLOCK.texture;
    }
    
    @Override
    public int getBaseColour(final int i) {
        return 10066431;
    }
    
    @Override
    public int getColourMultiplier(final BlockView tileView, final int x, final int y, final int z) {
        return this.getBaseColour(tileView.getTileMeta(x, y, z));
    }
}
