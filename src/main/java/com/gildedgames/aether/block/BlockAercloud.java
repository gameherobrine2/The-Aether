package com.gildedgames.aether.block;

import net.minecraft.util.maths.Box;
import net.minecraft.level.BlockView;
import net.minecraft.entity.player.PlayerBase;
import net.minecraft.entity.EntityBase;
import net.minecraft.level.Level;
import net.minecraft.block.material.Material;
import net.minecraft.block.BlockBase;

public class BlockAercloud extends BlockBase {
    public static final int bouncingMeta = 1;
    
    protected BlockAercloud(final int blockID) {
        super(blockID, ModLoader.addOverride("/terrain.png", "/aether/blocks/Aercloud.png"), Material.ICE);
    }
    
    @Override
    public void onEntityCollision(final Level level, final int x, final int y, final int z, final EntityBase entityBase) {
        entityBase.fallDistance = 0.0f;
        if (level.getTileMeta(x, y, z) == 1) {
            entityBase.velocityY = 2.0;
            if (entityBase instanceof PlayerBase) {
                mod_Aether.giveAchievement(AetherAchievements.blueCloud, (PlayerBase)entityBase);
            }
        }
        else if (entityBase.velocityY < 0.0) {
            entityBase.velocityY *= 0.005;
        }
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
    protected int droppedMeta(final int meta) {
        return meta;
    }
    
    @Override
    public int getBaseColour(final int i) {
        if (i == 1) {
            return 3714284;
        }
        if (i == 2) {
            return 16777088;
        }
        return 16777215;
    }
    
    @Override
    public int getColourMultiplier(final BlockView tileView, final int x, final int y, final int z) {
        return this.getBaseColour(tileView.getTileMeta(x, y, z));
    }
    
    @Override
    public boolean isSideRendered(final BlockView tileView, final int x, final int y, final int z, final int side) {
        return super.isSideRendered(tileView, x, y, z, 1 - side);
    }
    
    @Override
    public Box getCollisionShape(final Level level, final int x, final int y, final int z) {
        if (level.getTileMeta(x, y, z) == 1) {
            return Box.createButWasteMemory(x, y, z, x, y, z);
        }
        return Box.createButWasteMemory(x, y, z, x + 1, y, z + 1);
    }
}
