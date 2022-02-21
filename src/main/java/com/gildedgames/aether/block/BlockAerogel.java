package com.gildedgames.aether.block;

import net.minecraft.level.BlockView;
import net.minecraft.block.material.Material;
import net.minecraft.block.BlockBase;

public class BlockAerogel extends BlockBase {
    public BlockAerogel(final int blockID) {
        super(blockID, /*ModLoader.addOverride("/terrain.png", "/aether/blocks/Aerogel.png"),*/ Material.STONE);
    }
    
    @Override
    public boolean isFullOpaque() {
        return false;
    }
    
    @Override
    public int getRenderPass() {
        return 1;
    }
    
    @Override
    public boolean isSideRendered(final BlockView tileView, final int x, final int y, final int z, final int side) {
        return super.isSideRendered(tileView, x, y, z, 1 - side);
    }
}
