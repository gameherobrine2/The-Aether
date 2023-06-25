package com.gildedgames.aether.generator;

import net.minecraft.item.ItemInstance;
import net.minecraft.entity.EntityBase;
import net.minecraft.tileentity.TileEntityChest;
import java.util.Random;

import com.gildedgames.aether.entity.boss.EntityFireMonster;
import com.gildedgames.aether.registry.AetherBlocks;
import com.gildedgames.aether.registry.AetherItems;

import net.minecraft.level.Level;
import net.minecraft.block.BlockBase;
import net.minecraft.level.structure.Structure;

public class AetherGenDungeon extends Structure {
	//public boolean generate(Level level, Random random, int i, int i2, int i3) {return false;}
	//public boolean generate(Level level, Random random, int i, int i2, int i3,int i4) {return false;}
    public int xoff;
    public int yoff;
    public int zoff;
    public int rad;
    public int truey;
    
    private int floors() {
        return BlockBase.DOUBLE_STONE_SLAB.id;
    }
    
    private int walls() {
        return AetherBlocks.LOCKED_DUNGEON_STONE.id;
    }
    
    private int ceilings() {
        return AetherBlocks.LOCKED_LIGHT_DUNGEON_STONE.id;
    }
    
    private int torches() {
        return 0;
    }
    
    private int doors() {
        return 0;
    }
    
    @Override
    public boolean generate(final Level level, final Random rand, final int x, final int y, final int z) {
        return this.generate(level, rand, x, y, z, 24);
    }
    
    public boolean generate(final Level world, final Random random, final int x, final int y, final int z, int r) {
        r = (int)Math.floor(r * 0.8);
        final int wid = (int)Math.sqrt((double)(r * r / 2));
        for (int j = 4; j > -5; --j) {
            int a = wid;
            if (j >= 3 || j <= -3) {
                --a;
            }
            if (j == 4 || j == -4) {
                --a;
            }
            for (int i = -a; i <= a; ++i) {
                for (int k = -a; k <= a; ++k) {
                    if (j == 4) {
                        this.setBlock(world, random, x + i, y + j, z + k, this.walls(), 2, this.ceilings(), 2, 10);
                    }
                    else if (j > -4) {
                        if (i == a || -i == a || k == a || -k == a) {
                            this.setBlock(world, random, x + i, y + j, z + k, this.walls(), 2, this.ceilings(), 2, 10);
                        }
                        else {
                            world.setTileInChunk(x + i, y + j, z + k, 0);
                            if (j == -2 && (i == a - 1 || -i == a - 1 || k == a - 1 || -k == a - 1) && (i % 3 == 0 || k % 3 == 0)) {
                                world.setTileInChunk(x + i, y + j + 2, z + k, this.torches());
                            }
                        }
                    }
                    else {
                        this.setBlock(world, random, x + i, y + j, z + k, this.walls(), 2, this.ceilings(), 2, 10);
                        if ((i == a - 2 || -i == a - 2) && (k == a - 2 || -k == a - 2)) {
                            world.setTileInChunk(x + i, y + j + 1, z + k, BlockBase.NETHERRACK.id);
                            world.setTileInChunk(x + i, y + j + 2, z + k, BlockBase.FIRE.id);
                        }
                    }
                }
            }
        }
        final int direction = random.nextInt(4);
        for (int l = wid; l < wid + 32; ++l) {
            boolean flag = false;
            for (int m = -3; m < 2; ++m) {
                for (int k2 = -3; k2 < 4; ++k2) {
                    int a2;
                    int b;
                    if (direction / 2 == 0) {
                        a2 = l;
                        b = k2;
                    }
                    else {
                        a2 = k2;
                        b = l;
                    }
                    if (direction % 2 == 0) {
                        a2 = -a2;
                        b = -b;
                    }
                    if (!AetherBlocks.isGood(world.getTileId(x + a2, y + m, z + b), world.getTileMeta(x + a2, y + m, z + b))) {
                        flag = true;
                        if (m == -3) {
                            this.setBlock(world, random, x + a2, y + m, z + b, AetherBlocks.HOLYSTONE.id, 0, AetherBlocks.MOSSY_HOLYSTONE.id, 0, 5);
                        }
                        else if (m < 1) {
                            if (l == wid) {
                                if (k2 < 2 && k2 > -2 && m < 0) {
                                    world.setTileInChunk(x + a2, y + m, z + b, this.doors());
                                }
                                else {
                                    this.setBlock(world, random, x + a2, y + m, z + b, this.walls(), 2, this.ceilings(), 2, 10);
                                }
                            }
                            else if (k2 == 3 || k2 == -3) {
                                this.setBlock(world, random, x + a2, y + m, z + b, AetherBlocks.HOLYSTONE.id, 0, AetherBlocks.MOSSY_HOLYSTONE.id, 0, 5);
                            }
                            else {
                                world.setTileInChunk(x + a2, y + m, z + b, 0);
                                if (m == -1 && (k2 == 2 || k2 == -2) && (l - wid - 2) % 3 == 0) {
                                    world.setTileInChunk(x + a2, y + m, z + b, this.torches());
                                }
                            }
                        }
                        else if (l == wid) {
                            this.setBlock(world, random, x + a2, y + m, z + b, this.walls(), 2, this.ceilings(), 2, 5);
                        }
                        else {
                            this.setBlock(world, random, x + a2, y + m, z + b, AetherBlocks.HOLYSTONE.id, 0, AetherBlocks.MOSSY_HOLYSTONE.id, 0, 5);
                        }
                    }
                    a2 = -a2;
                    b = -b;
                    if (l < wid + 6) {
                        if (m == -3) {
                            this.setBlock(world, random, x + a2, y + m, z + b, this.walls(), 2, this.ceilings(), 2, 10);
                        }
                        else if (m < 1) {
                            if (l == wid) {
                                if (k2 < 2 && k2 > -2 && m < 0) {
                                    this.setBlock(world, random, x + a2, y + m, z + b, this.walls(), 2, this.ceilings(), 2, 10);
                                }
                                else {
                                    this.setBlock(world, random, x + a2, y + m, z + b, this.walls(), 2, this.ceilings(), 2, 10);
                                }
                            }
                            else if (l == wid + 5) {
                                this.setBlock(world, random, x + a2, y + m, z + b, this.walls(), 2, this.ceilings(), 2, 10);
                            }
                            else if (l == wid + 4 && k2 == 0 && m == -2) {
                                world.setTileWithMetadata(x + a2, y + m, z + b, AetherBlocks.TREASURE_CHEST.id, 4);
                                final TileEntityChest chest = (TileEntityChest)world.getTileEntity(x + a2, y + m, z + b);
                                for (int p = 0; p < 3 + random.nextInt(3); ++p) {
                                    final ItemInstance item = this.getGoldLoot(random);
                                    chest.setInventoryItem(random.nextInt(chest.getInventorySize()), item);
                                }
                            }
                            else if (k2 == 3 || k2 == -3) {
                                this.setBlock(world, random, x + a2, y + m, z + b, this.walls(), 2, this.ceilings(), 2, 10);
                            }
                            else {
                                world.setTileInChunk(x + a2, y + m, z + b, 0);
                                if (m == -1 && (k2 == 2 || k2 == -2) && (l - wid - 2) % 3 == 0) {
                                    world.setTileInChunk(x + a2, y + m, z + b, this.torches());
                                }
                            }
                        }
                        else {
                            this.setBlock(world, random, x + a2, y + m, z + b, this.walls(), 2, this.ceilings(), 2, 10);
                        }
                    }
                }
            }
            if (!flag) {
                break;
            }
        }
        final EntityFireMonster boss = new EntityFireMonster(world, x, y - 1, z, wid, direction);
        world.spawnEntity(boss);
        System.out.println("Gold Dungeon: "+x+", "+y+", "+z);
        return true;
    }
    
    private void setBlock(final Level world, final Random random, final int i, final int j, final int k, final int block1, final int meta1, final int block2, final int meta2, final int chance) {
        if (random.nextInt(chance) == 0) {
            world.setTileWithMetadata(i, j, k, block2, meta2);
        }
        else {
            world.setTileWithMetadata(i, j, k, block1, meta1);
        }
    }
    
    private ItemInstance getGoldLoot(final Random random) {
        final int item = random.nextInt(8);
        switch (item) {
            case 0: {
                return new ItemInstance(AetherItems.IronBubble);
            }
            case 1: {
                return new ItemInstance(AetherItems.VampireBlade);
            }
            case 2: {
                return new ItemInstance(AetherItems.PigSlayer);
            }
            case 3: {
                if (random.nextBoolean()) {
                    return new ItemInstance(AetherItems.PhoenixHelm);
                }
                if (random.nextBoolean()) {
                    return new ItemInstance(AetherItems.PhoenixLegs);
                }
                if (random.nextBoolean()) {
                    return new ItemInstance(AetherItems.PhoenixBody);
                }
                break;
            }
            case 4: {
                if (random.nextBoolean()) {
                    return new ItemInstance(AetherItems.PhoenixBoots);
                }
                return new ItemInstance(AetherItems.PhoenixGlove);
            }
            case 5: {
                return new ItemInstance(AetherItems.LifeShard);
            }
            case 6: {
                if (random.nextBoolean()) {
                    return new ItemInstance(AetherItems.GravititeHelmet);
                }
                if (random.nextBoolean()) {
                    return new ItemInstance(AetherItems.GravititePlatelegs);
                }
                if (random.nextBoolean()) {
                    return new ItemInstance(AetherItems.GravititeBodyplate);
                }
                break;
            }
            case 7: {
                if (random.nextBoolean()) {
                    return new ItemInstance(AetherItems.GravititeBoots);
                }
                return new ItemInstance(AetherItems.GravititeGlove);
            }
        }
        //return null;
        return new ItemInstance(AetherItems.ObsidianBody);
    }
}
