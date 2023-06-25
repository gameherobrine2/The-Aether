package com.gildedgames.aether.block;
import net.minecraft.util.maths.MathHelper;
import net.modificationstation.stationapi.api.registry.Identifier;
import net.modificationstation.stationapi.api.template.block.TemplateTranslucentBlock;
import net.minecraft.entity.player.PlayerBase;
import net.minecraft.entity.EntityBase;
import net.minecraft.level.Level;
import java.util.Random;

import com.gildedgames.aether.entity.boss.EntityValkyrie;
import com.gildedgames.aether.entity.mobs.EntitySentry;
import com.gildedgames.aether.event.listener.TextureListener;
import com.gildedgames.aether.registry.AetherBlocks;

import net.minecraft.block.material.Material;
import net.minecraft.block.TranslucentBlock;

public class BlockTrap extends TemplateTranslucentBlock {
    
    public BlockTrap(final Identifier id) {
        super(id, TextureListener.sprBronze, Material.STONE, false);
        this.setTicksRandomly(true);
    }
    
    @Override
    public boolean isFullOpaque() {
        return true;
    }
    
    @Override
    public int getRenderPass() {
        return 1;
    }
    
    @Override
    public int getTextureForSide(final int side, final int meta) {
        if (meta == 2) {
            return TextureListener.sprGold;
        }
        if (meta == 1) {
            return TextureListener.sprSilver;
        }
        return TextureListener.sprBronze;
    }
    
    @Override
    public int getDropCount(final Random rand) {
        return 1;
    }
    
    @Override
    public void onSteppedOn(final Level level, final int x, final int y, final int z, final EntityBase entityBase) {
        if (entityBase instanceof PlayerBase) {
            level.playSound((double)(x + 0.5f), (double)(y + 0.5f), (double)(z + 0.5f), "assets/aether/stationapi/aether.sound.other.dungeontrap.activatetrap", 1.0f, 1.0f);
            final int x2 = MathHelper.floor((double)x);
            final int y2 = MathHelper.floor((double)y);
            final int z2 = MathHelper.floor((double)z);
            switch (level.getTileMeta(x, y, z)) {
                case 0: {
                    final EntitySentry entitysentry = new EntitySentry(level);
                    entitysentry.setPosition(x2 + 0.5, y2 + 1.5, z2 + 0.5);
                    level.spawnEntity(entitysentry);
                    break;
                }
                case 1: {
                    final EntityValkyrie entityvalk = new EntityValkyrie(level);
                    entityvalk.setPosition(x2 + 0.5, y2 + 1.5, z2 + 0.5);
                    level.spawnEntity(entityvalk);
                    break;
                }
            }
            level.placeBlockWithMetaData(x, y, z, AetherBlocks.LOCKED_DUNGEON_STONE.id, level.getTileMeta(x, y, z));
        }
    }
    
    @Override
    protected int droppedMeta(final int meta) {
        return meta;
    }
    
    static {
        
    }
}
