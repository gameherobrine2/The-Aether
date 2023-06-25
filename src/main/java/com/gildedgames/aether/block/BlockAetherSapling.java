package com.gildedgames.aether.block;
import net.minecraft.block.BlockBase;
import net.minecraft.level.structure.Structure;
import net.modificationstation.stationapi.api.registry.Identifier;
import net.modificationstation.stationapi.api.template.block.BlockTemplate;
import net.modificationstation.stationapi.api.template.block.TemplatePlant;
import net.minecraft.item.ItemInstance;
import net.minecraft.item.ItemBase;
import net.minecraft.entity.player.PlayerBase;
import java.util.Random;

import com.gildedgames.aether.event.listener.TextureListener;
import com.gildedgames.aether.generator.AetherGenGoldenOak;
import com.gildedgames.aether.generator.AetherGenSkyroot;
import com.gildedgames.aether.registry.AetherBlocks;

import net.minecraft.level.Level;
import net.minecraft.block.Plant;
import net.minecraft.block.material.Material;

public class BlockAetherSapling extends TemplatePlant {
    public BlockAetherSapling(final Identifier id,boolean isGold) {
        super(id, isGold ? TextureListener.sprGoldenOak : TextureListener.sprSkyroot);
        final float f = 0.4f;
        this.setBoundingBox(0.5f - f, 0.0f, 0.5f - f, 0.5f + f, f * 2.0f, 0.5f + f);
    }
	@Override
    public void onScheduledTick(final Level level, final int x, final int y, final int z, final Random rand) {
        super.onScheduledTick(level, x, y, z, rand);
        if (level.placeTile(x, y + 1, z) >= 9 && rand.nextInt(30) == 0) {
            this.growTree(level, x, y, z, rand);
        }
    }
    
    @Override
    public int getTextureForSide(final int side, final int meta) {
        if (this.id == AetherBlocks.GOLDEN_OAK_SAPLING.id) {
        	if(this.texture != TextureListener.sprGoldenSapling) {
        		this.texture = TextureListener.sprGoldenSapling;
        	}
            return TextureListener.sprGoldenSapling;
        }
    	if(this.texture != TextureListener.sprOakSapling) {
    		this.texture = TextureListener.sprOakSapling;
    	}
        return TextureListener.sprOakSapling;
    }
    
    @Override
    public boolean canPlaceAt(final Level level, final int x, final int y, final int z) {
        return super.canPlaceAt(level, x, y, z) && this.canPlantOnTopOf(level.getTileId(x, y - 1, z));
    }
    
    @Override
    protected boolean canPlantOnTopOf(final int id) {
        return id == AetherBlocks.AETHER_GRASS_BLOCK.id || id == AetherBlocks.AETHER_DIRT.id || id == BlockBase.DIRT.id || id == BlockBase.GRASS.id;
    }
    
    @Override
    public boolean canUse(final Level level, final int x, final int y, final int z, final PlayerBase player) {
        if (player == null) {
            return false;
        }
        final ItemInstance itemStack = player.getHeldItem();
        if (itemStack == null) {
            return false;
        }
        if (itemStack.itemId != ItemBase.dyePowder.id) {
            return false;
        }
        if (itemStack.getDamage() != 15) {
            return false;
        }
        this.growTree(level, x, y, z, level.rand);
        final ItemInstance itemInstance = itemStack;
        --itemInstance.count;
        return true;
    }
    
    public void growTree(final Level world, final int i, final int j, final int k, final Random random) {
        world.setTileInChunk(i, j, k, 0);
        Object obj = null;
        if (this.id == AetherBlocks.GOLDEN_OAK_SAPLING.id) {
            obj = new AetherGenGoldenOak();
        }
        else {
            obj = new AetherGenSkyroot();
        }
        if (!((Structure)obj).generate(world, random, i, j, k)) {
            world.setTileInChunk(i, j, k, this.id);
        }
    }
    
    static {
        //BlockAetherSapling.sprSkyroot = ModLoader.addOverride("/terrain.png", "/aether/blocks/SkyrootSapling.png");
        //BlockAetherSapling.sprGoldenOak = ModLoader.addOverride("/terrain.png", "/aether/blocks/GoldenOakSapling.png");
    }
}
