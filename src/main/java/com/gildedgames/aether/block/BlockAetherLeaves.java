package com.gildedgames.aether.block;

import net.minecraft.item.ItemInstance;

import net.minecraft.stat.Stats;
import net.modificationstation.stationapi.api.registry.Identifier;
import net.modificationstation.stationapi.api.template.block.TemplateBlockBase;
import net.minecraft.entity.player.PlayerBase;
import net.minecraft.level.BlockView;
import net.minecraft.level.Level;
import net.minecraft.item.ItemBase;
import java.util.Random;

import com.gildedgames.aether.event.listener.TextureListener;
import com.gildedgames.aether.registry.AetherBlocks;

import net.minecraft.block.material.Material;

public class BlockAetherLeaves extends TemplateBlockBase {

    private boolean isGold;
    public BlockAetherLeaves(final Identifier id,boolean isGold) {
        super(id, Material.LEAVES);
        this.isGold = isGold;
        this.setTicksRandomly(true);
    }
    @Override
    public int getTextureForSide(int side, int meta) {
    	return (isGold) ? TextureListener.sprGoldenOak : TextureListener.sprSkyroot;
    }
    @Override
    public int getDropCount(final Random rand) {
        if (this.id == AetherBlocks.GOLDEN_OAK_LEAVES.id) {
            return (rand.nextInt(60) == 0) ? 1 : 0;
        }
        return (rand.nextInt(40) == 0) ? 1 : 0;
    }
    
    @Override
    public int getDropId(final int meta, final Random rand) {
        if (this.id == AetherBlocks.SKYROOT_LEAVES.id) {
            return AetherBlocks.SKYROOT_SAPLING.id;
        }
        if (rand.nextInt(10) == 0) {
            return ItemBase.goldenApple.id;
        }
        return AetherBlocks.GOLDEN_OAK_SAPLING.id;
    }
    
    @Override
    public void onBlockRemoved(final Level level, final int x, final int y, final int z) {
        final int l = 1;
        final int i1 = l + 1;
        if (level.method_155(x - i1, y - i1, z - i1, x + i1, y + i1, z + i1)) {
            for (int j1 = -l; j1 <= l; ++j1) {
                for (int k1 = -l; k1 <= l; ++k1) {
                    for (int l2 = -l; l2 <= l; ++l2) {
                        final int i2 = level.getTileId(x + j1, y + k1, z + l2);
                        if (i2 == this.id) {
                            final int j2 = level.getTileMeta(x + j1, y + k1, z + l2);
                            level.method_223(x + j1, y + k1, z + l2, j2 | 0x8);
                        }
                    }
                }
            }
        }
    }
    
    @Override
    public void onScheduledTick(final Level level, final int x, final int y, final int z, final Random rand) {
        //if (level.isServerSide) {
        //    return;
        //}
        if (!this.nearTrunk(level, x, y, z)) {
            this.removeLeaves(level, x, y, z);
        }
    }
    
    private void removeLeaves(final Level world, final int px, final int py, final int pz) {
        this.drop(world, px, py, pz, world.getTileMeta(px, py, pz));
        world.setTile(px, py, pz, 0);
    }
    
    private boolean nearTrunk(final Level world, final int px, final int py, final int pz) {
		return true;
        /*final Loc startLoc = new Loc(px, py, pz);
        final LinkedList<Loc> toCheck = (LinkedList<Loc>)new LinkedList();
        final ArrayList<Loc> checked = (ArrayList<Loc>)new ArrayList();
        toCheck.offer(new Loc(px, py, pz));
        final int bLeaves = this.id;
        while (!toCheck.isEmpty()) {
            final Loc curLoc = (Loc)toCheck.poll();
            if (checked.contains(curLoc)) {
                continue;
            }
            if (curLoc.distSimple(startLoc) <= 4) {
                final int block = curLoc.getBlock((BlockView)world);
                final int meta = curLoc.getMeta((BlockView)world);
                if (block == AetherBlocks.LOG.id && this.isMyTrunkMeta(meta)) {
                    return true;
                }
                if (block == bLeaves) {
                    toCheck.addAll((Collection)Arrays.asList((Object[])curLoc.adjacent()));
                }
            }
            System.out.println("nya");
            checked.add(curLoc);
        }
        return true;*/
    }
    
    private boolean isMyTrunkMeta(final int meta) {
        if (this.id == AetherBlocks.SKYROOT_LEAVES.id) {
            return meta <= 1;
        }
        return meta >= 2;
    }
    
    @Override
    protected int droppedMeta(final int meta) {
        return meta & 0x3;
    }
    
    @Override
    public boolean isFullOpaque() {
        return false;
    }
    
    @Override
    public void afterBreak(final Level level, final PlayerBase playerBase, final int x, final int y, final int z, final int meta) {
        if (playerBase.getHeldItem() != null && playerBase.getHeldItem().itemId == ItemBase.shears.id) {
            playerBase.increaseStat(Stats.mineBlock[this.id], 1);
            this.drop(level, x, y, z, new ItemInstance(this.id, 1, meta & 0x3));
        }
        else {
            super.afterBreak(level, playerBase, x, y, z, meta);
        }
    }
    
    @Override
    public boolean isSideRendered(final BlockView tileView, final int x, final int y, final int z, final int side) {
        final int i1 = tileView.getTileId(x, y, z);
        return true;
    }
}
