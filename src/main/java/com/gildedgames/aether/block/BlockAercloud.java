package com.gildedgames.aether.block;

import net.minecraft.util.maths.Box;
import net.modificationstation.stationapi.api.block.HasMetaBlockItem;
import net.modificationstation.stationapi.api.block.MetaNamedBlockItemProvider;
import net.modificationstation.stationapi.api.registry.Identifier;
import net.modificationstation.stationapi.api.template.block.TemplateBlockBase;
import net.minecraft.level.BlockView;
import net.minecraft.entity.player.PlayerBase;
import net.minecraft.entity.EntityBase;
import net.minecraft.level.Level;
import net.minecraft.block.material.Material;
import com.gildedgames.aether.mixin.access.EntityBaseAccessor;
import com.gildedgames.aether.registry.AetherAchievements;
import com.gildedgames.aether.Aether;
import com.gildedgames.aether.event.listener.TextureListener;

@HasMetaBlockItem
public class BlockAercloud extends TemplateBlockBase implements MetaNamedBlockItemProvider {
    public static final int bouncingMeta = 1;
    
    public BlockAercloud(final Identifier id) {
        super(id, Material.ICE);
    }
    public int getTextureForSide(int side, int meta) {
    	return TextureListener.sprAercloud;
    }
    @Override
    public void onEntityCollision(final Level level, final int x, final int y, final int z, final EntityBase entityBase) {
        ((EntityBaseAccessor)entityBase).setFallDistance(0.0f);
        if (level.getTileMeta(x, y, z) == 1) {
            entityBase.velocityY = 2.0;
            if (entityBase instanceof PlayerBase) {
                Aether.giveAchievement(AetherAchievements.blueCloud, (PlayerBase)entityBase);
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