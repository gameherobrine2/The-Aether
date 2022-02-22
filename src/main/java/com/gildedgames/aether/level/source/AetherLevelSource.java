package com.gildedgames.aether.level.source;

import com.gildedgames.aether.generator.AetherGenClouds;
import com.gildedgames.aether.generator.AetherGenFlowers;
import com.gildedgames.aether.generator.AetherGenLiquids;
import com.gildedgames.aether.registry.AetherBlocks;

import net.minecraft.block.BlockBase;
import net.minecraft.block.Sand;
import net.minecraft.level.Level;
import net.minecraft.level.biome.Biome;
import net.minecraft.level.chunk.Chunk;
import net.minecraft.level.gen.Cave;
import net.minecraft.level.gen.OverworldCave;
import net.minecraft.level.source.LevelSource;
import net.minecraft.level.structure.Lake;
import net.minecraft.level.structure.Structure;
import net.minecraft.util.ProgressListener;
import net.minecraft.util.noise.PerlinOctaveNoise;

import java.util.*;

public class AetherLevelSource implements LevelSource {

    private static int GUM_COUNT;

    private final Random random;
    private final PerlinOctaveNoise
            perlinOctaveNoise1,
            perlinOctaveNoise2,
            perlinOctaveNoise3,
            perlinOctaveNoise4,
            perlinOctaveNoise5,
            perlinOctaveNoise6,
            perlinOctaveNoise7,
            perlinOctaveNoise8;
    private final Level level;
    private double[]
            climateNoise,
            sandNoise = new double[256],
            gravelNoise = new double[256],
            stoneNoise = new double[256];
    private final Cave caveGen = new OverworldCave();
    private Biome[] biomes;
    private double[]
            noise3,
            noise1,
            noise2,
            noise6,
            noise7;
    private double[] temperatures;

    public AetherLevelSource(Level level, long l) {
        random = new Random(l);
        perlinOctaveNoise1 = new PerlinOctaveNoise(random, 16);
        perlinOctaveNoise2 = new PerlinOctaveNoise(random, 16);
        perlinOctaveNoise3 = new PerlinOctaveNoise(random, 8);
        perlinOctaveNoise4 = new PerlinOctaveNoise(random, 4);
        perlinOctaveNoise5 = new PerlinOctaveNoise(random, 4);
        perlinOctaveNoise6 = new PerlinOctaveNoise(random, 10);
        perlinOctaveNoise7 = new PerlinOctaveNoise(random, 16);
        perlinOctaveNoise8 = new PerlinOctaveNoise(random, 8);
        this.level = level;
    }

    private void shapeChunk(int chunkX, int chunkZ, byte[] tiles, Biome[] biomes, double[] temperatures) {
        byte byte0 = 2;
        int k = byte0 + 1;
        byte byte1 = 33;
        int l = byte0 + 1;
        climateNoise = calculateNoise(climateNoise, chunkX * byte0, 0, chunkZ * byte0, k, byte1, l);
        for(int i1 = 0; i1 < byte0; i1++)
            for (int j1 = 0; j1 < byte0; j1++)
                for (int k1 = 0; k1 < 32; k1++) {
                    double d = 0.25D;
                    double d1 = climateNoise[((i1) * l + (j1)) * byte1 + (k1)];
                    int a = ((i1) * l + (j1 + 1)) * byte1;
                    double d2 = climateNoise[a + (k1)];
                    int b = ((i1 + 1) * l + (j1)) * byte1;
                    double d3 = climateNoise[b + (k1)];
                    int e = ((i1 + 1) * l + (j1 + 1)) * byte1;
                    double d4 = climateNoise[e + (k1)];
                    double d5 = (climateNoise[((i1) * l + (j1)) * byte1 + (k1 + 1)] - d1) * d;
                    double d6 = (climateNoise[a + (k1 + 1)] - d2) * d;
                    double d7 = (climateNoise[b + (k1 + 1)] - d3) * d;
                    double d8 = (climateNoise[e + (k1 + 1)] - d4) * d;
                    for (int l1 = 0; l1 < 4; l1++) {
                        double d9 = 0.125D;
                        double d10 = d1;
                        double d11 = d2;
                        double d12 = (d3 - d1) * d9;
                        double d13 = (d4 - d2) * d9;
                        for (int i2 = 0; i2 < 8; i2++) {
                            int j2 = i2 + i1 * 8 << 11 | j1 * 8 << 7 | k1 * 4 + l1;
                            char c = '\200';
                            double d14 = 0.125D;
                            double d15 = d10;
                            double d16 = (d11 - d10) * d14;
                            for (int k2 = 0; k2 < 8; k2++) {
                                int l2 = 0;
                                if (d15 > 0.0D)
                                    l2 = AetherBlocks.HOLYSTONE.id;
                                tiles[j2] = (byte) l2;
                                j2 += c;
                                d15 += d16;
                            }
                            d10 += d12;
                            d11 += d13;
                        }
                        d1 += d5;
                        d2 += d6;
                        d3 += d7;
                        d4 += d8;
                    }
                }
    }

    public void buildSurface(int chunkX, int chunkZ, byte[] tiles, Biome[] biomes) {
        double d = 0.03125D;
        sandNoise = perlinOctaveNoise4.sample(sandNoise, chunkX * 16, chunkZ * 16, 0.0D, 16, 16, 1, d, d, 1.0D);
        gravelNoise = perlinOctaveNoise4.sample(gravelNoise, chunkX * 16, 109.0134D, chunkZ * 16, 16, 1, 16, d, 1.0D, d);
        stoneNoise = perlinOctaveNoise5.sample(stoneNoise, chunkX * 16, chunkZ * 16, 0.0D, 16, 16, 1, d * 2D, d * 2D, d * 2D);
        for(int k = 0; k < 16; k++)
            for (int l = 0; l < 16; l++) {
                int i1 = (int) (stoneNoise[k + l * 16] / 3D + 3D + random.nextDouble() * 0.25D);
                int j1 = -1;
                byte byte0 = (byte) AetherBlocks.AETHER_GRASS_BLOCK.id;
                byte byte1 = (byte) AetherBlocks.AETHER_DIRT.id;
                byte stone = (byte) AetherBlocks.HOLYSTONE.id;
                for (int k1 = 127; k1 >= 0; k1--) {
                    int l1 = (l * 16 + k) * 128 + k1;
                    byte byte2 = tiles[l1];
                    if (byte2 == 0) {
                        j1 = -1;
                        continue;
                    }
                    if (byte2 != stone)
                        continue;
                    if (j1 == -1) {
                        if (i1 <= 0) {
                            byte0 = 0;
                            byte1 = stone;
                        }
                        j1 = i1;
                        tiles[l1] = byte0;
                        continue;
                    }
                    if (j1 > 0) {
                        j1--;
                        tiles[l1] = byte1;
                    }
                }
            }
    }

    @Override
    public Chunk getChunk(int chunkX, int chunkZ) {
        return loadChunk(chunkX, chunkZ);
    }

    @Override
    public Chunk loadChunk(int chunkX, int chunkZ) {
        random.setSeed((long)chunkX * 0x4f9939f508L + (long)chunkZ * 0x1ef1565bd5L);
        byte[] tiles = new byte[32768];
        Chunk chunk = new Chunk(level, tiles, chunkX, chunkZ);
        biomes = level.getBiomeSource().getBiomes(biomes, chunkX * 16, chunkZ * 16, 16, 16);
        double[] temperatures = level.getBiomeSource().temperatureNoises;
        shapeChunk(chunkX, chunkZ, tiles, biomes, temperatures);
        buildSurface(chunkX, chunkZ, tiles, biomes);
        caveGen.generate(this, level, chunkX, chunkZ, tiles);
        chunk.generateHeightmap();
        return chunk;
    }

    private double[] calculateNoise(double[] noises, int chunkX, int chunkY, int chunkZ, int noiseResolutionX, int noiseResolutionY, int noiseResolutionZ) {
        if(noises == null)
            noises = new double[noiseResolutionX * noiseResolutionY * noiseResolutionZ];
        double d = 684.41200000000003D;
        double d1 = 684.41200000000003D;
        noise6 = perlinOctaveNoise6.sample(noise6, chunkX, chunkZ, noiseResolutionX, noiseResolutionZ, 1.121D, 1.121D, 0.5D);
        noise7 = perlinOctaveNoise7.sample(noise7, chunkX, chunkZ, noiseResolutionX, noiseResolutionZ, 200D, 200D, 0.5D);
        d *= 2D;
        noise3 = perlinOctaveNoise3.sample(noise3, chunkX, chunkY, chunkZ, noiseResolutionX, noiseResolutionY, noiseResolutionZ, d / 80D, d1 / 160D, d / 80D);
        noise1 = perlinOctaveNoise1.sample(noise1, chunkX, chunkY, chunkZ, noiseResolutionX, noiseResolutionY, noiseResolutionZ, d, d1, d);
        noise2 = perlinOctaveNoise2.sample(noise2, chunkX, chunkY, chunkZ, noiseResolutionX, noiseResolutionY, noiseResolutionZ, d, d1, d);
        int k1 = 0;
        for(int j2 = 0; j2 < noiseResolutionX; j2++)
            for (int l2 = 0; l2 < noiseResolutionZ; l2++)
                for (int j3 = 0; j3 < noiseResolutionY; j3++) {
                    double d8;
                    double d10 = noise1[k1] / 512D;
                    double d11 = noise2[k1] / 512D;
                    double d12 = (noise3[k1] / 10D + 1.0D) / 2D;
                    if (d12 < 0.0D)
                        d8 = d10;
                    else if (d12 > 1.0D)
                        d8 = d11;
                    else
                        d8 = d10 + (d11 - d10) * d12;
                    d8 -= 8D;
                    int k3 = 32;
                    if (j3 > noiseResolutionY - k3) {
                        double d13 = (float) (j3 - (noiseResolutionY - k3)) / ((float) k3 - 1.0F);
                        d8 = d8 * (1.0D - d13) + -30D * d13;
                    }
                    k3 = 8;
                    if (j3 < k3) {
                        double d14 = (float) (k3 - j3) / ((float) k3 - 1.0F);
                        d8 = d8 * (1.0D - d14) + -30D * d14;
                    }
                    noises[k1] = d8;
                    k1++;
                }
        return noises;
    }

    @Override
    public boolean isChunkLoaded(int chunkX, int chunkZ) {
        return true;
    }

    @Override
    public void decorate(LevelSource levelSource, int chunkX, int chunkZ) {
    	Sand.fallInstantly = true;
        final int k = chunkX * 16;
        final int l = chunkZ * 16;
        final Biome biomegenbase = this.level.getBiomeSource().getBiome(k + 16, l + 16);
        this.random.setSeed(this.level.getSeed());
        final long l2 = this.random.nextLong() / 2L * 2L + 1L;
        final long l3 = this.random.nextLong() / 2L * 2L + 1L;
        this.random.setSeed(chunkX * l2 + chunkZ * l3 ^ this.level.getSeed());
        double d = 0.25;
        //if (ChunkProviderAether.gumCount < 800) {
        //    ++ChunkProviderAether.gumCount;
        //}
        /*else if (this.random.nextInt(32) == 0) {
            boolean flag = false;
            final int x = k + this.random.nextInt(16) + 8;
            final int y = this.random.nextInt(64) + 32;
            final int z = l + this.random.nextInt(16) + 8;
            flag = new AetherGenGumdrop().generate(this.level, this.random, x, y, z);
            if (flag) {
                gumCount = 0;
            }
        }*/
        if (this.random.nextInt(3) == 0) {
            final int x2 = k + this.random.nextInt(16) + 8;
            final int y2 = this.random.nextInt(128);
            final int z2 = l + this.random.nextInt(16) + 8;
            new Lake(BlockBase.STILL_WATER.id).generate(this.level, this.random, x2, y2, z2);
        }
        /*for (int n = 0; n < 20; ++n) {
            final int x = k + this.random.nextInt(16);
            final int y = this.random.nextInt(128);
            final int z = l + this.random.nextInt(16);
            new AetherGenMinable(AetherBlocks.Dirt.id, 32).generate(this.level, this.random, x, y, z);
        }*/
        for (int n = 0; n < 2; ++n) {
            final int x = k + this.random.nextInt(16) + 8;
            final int y = this.random.nextInt(128);
            final int z = l + this.random.nextInt(16) + 8;
            new AetherGenFlowers(AetherBlocks.WHITE_FLOWER.id).generate(this.level, this.random, x, y, z);
        }
        for (int n = 0; n < 2; ++n) {
            if (this.random.nextInt(2) == 0) {
                final int x = k + this.random.nextInt(16) + 8;
                final int y = this.random.nextInt(128);
                final int z = l + this.random.nextInt(16) + 8;
                new AetherGenFlowers(AetherBlocks.PURPLE_FLOWER.id).generate(this.level, this.random, x, y, z);
            }
        }
        /*for (int n = 0; n < 10; ++n) {
            final int x = k + this.random.nextInt(16);
            final int y = this.random.nextInt(128);
            final int z = l + this.random.nextInt(16);
            new AetherGenMinable(AetherBlocks.Icestone.id, 32).generate(this.level, this.random, x, y, z);
        }
        for (int n = 0; n < 20; ++n) {
            final int x = k + this.random.nextInt(16);
            final int y = this.random.nextInt(128);
            final int z = l + this.random.nextInt(16);
            new AetherGenMinable(AetherBlocks.AmbrosiumOre.id, 16).generate(this.level, this.random, x, y, z);
        }
        for (int n = 0; n < 15; ++n) {
            final int x = k + this.random.nextInt(16);
            final int y = this.random.nextInt(64);
            final int z = l + this.random.nextInt(16);
            new AetherGenMinable(AetherBlocks.ZaniteOre.id, 8).generate(this.level, this.random, x, y, z);
        }
        for (int n = 0; n < 8; ++n) {
            final int x = k + this.random.nextInt(16);
            final int y = this.random.nextInt(32);
            final int z = l + this.random.nextInt(16);
            new AetherGenMinable(AetherBlocks.GravititeOre.id, 7).generate(this.level, this.random, x, y, z);
        }*/
        if (this.random.nextInt(50) == 0) {
            final int x2 = k + this.random.nextInt(16);
            final int y2 = this.random.nextInt(32) + 96;
            final int z2 = l + this.random.nextInt(16);
            new AetherGenClouds(AetherBlocks.AERCLOUD.id, 2, 4, false).generate(this.level, this.random, x2, y2, z2);
        }
        if (this.random.nextInt(13) == 0) {
            final int x2 = k + this.random.nextInt(16);
            final int y2 = this.random.nextInt(64) + 32;
            final int z2 = l + this.random.nextInt(16);
            new AetherGenClouds(AetherBlocks.AERCLOUD.id, 1, 8, false).generate(this.level, this.random, x2, y2, z2);
        }
        if (this.random.nextInt(7) == 0) {
            final int x2 = k + this.random.nextInt(16);
            final int y2 = this.random.nextInt(64) + 32;
            final int z2 = l + this.random.nextInt(16);
            new AetherGenClouds(AetherBlocks.AERCLOUD.id, 0, 16, false).generate(this.level, this.random, x2, y2, z2);
        }
        if (this.random.nextInt(50) == 0) {
            final int x2 = k + this.random.nextInt(16);
            final int y2 = this.random.nextInt(32);
            final int z2 = l + this.random.nextInt(16);
            new AetherGenClouds(AetherBlocks.AERCLOUD.id, 0, 64, true).generate(this.level, this.random, x2, y2, z2);
        }
        /*for (int qq = 0; qq < 2; ++qq) {
            final int x = k + this.random.nextInt(16);
            final int y = 32 + this.random.nextInt(64);
            final int z = l + this.random.nextInt(16);
            new AetherGenDungeonBronze(AetherBlocks.LockedDungeonStone.id, AetherBlocks.LockedLightDungeonStone.id, AetherBlocks.DungeonStone.id, AetherBlocks.LightDungeonStone.id, AetherBlocks.Holystone.id, 2, AetherBlocks.Holystone.id, 0, 16, true).generate(this.level, this.random, x, y, z);
        }
        if (this.random.nextInt(500) == 0) {
            final int x2 = k + this.random.nextInt(16);
            final int y2 = this.random.nextInt(32) + 64;
            final int z2 = l + this.random.nextInt(16);
            new AetherGenDungeonSilver(AetherBlocks.LockedDungeonStone.id, AetherBlocks.LockedLightDungeonStone.id, AetherBlocks.DungeonStone.id, AetherBlocks.LightDungeonStone.id, AetherBlocks.Holystone.id, 2, AetherBlocks.Holystone.id, 0, AetherBlocks.Pillar.id).generate(this.level, this.random, x2, y2, z2);
        }*/
        /*if (this.random.nextInt(5) == 0) {
            for (int x2 = k; x2 < k + 16; ++x2) {
                for (int z3 = l; z3 < l + 16; ++z3) {
                    for (int n2 = 0; n2 < 48; ++n2) {
                        if (this.level.getTileId(x2, n2, z3) == 0 && this.level.getTileId(x2, n2 + 1, z3) == AetherBlocks.Grass.id && this.level.getTileId(x2, n2 + 2, z3) == 0) {
                            new AetherGenQuicksoil(AetherBlocks.Quicksoil.id).generate(this.level, this.random, x2, n2, z3);
                            n2 = 128;
                        }
                    }
                }
            }
        }*/
        d = 0.5;
        final int k2 = (int)((this.perlinOctaveNoise8.sample(k * d, l * d) / 8.0 + this.random.nextDouble() * 4.0 + 4.0) / 3.0);
        int l4 = 0;
        if (this.random.nextInt(10) == 0) {
            ++l4;
        }
        if (biomegenbase == Biome.FOREST) {
            l4 += k2 + 5;
        }
        if (biomegenbase == Biome.RAINFOREST) {
            l4 += k2 + 5;
        }
        if (biomegenbase == Biome.SEASONAL_FOREST) {
            l4 += k2 + 2;
        }
        if (biomegenbase == Biome.TAIGA) {
            l4 += k2 + 5;
        }
        if (biomegenbase == Biome.DESERT) {
            l4 -= 20;
        }
        if (biomegenbase == Biome.TUNDRA) {
            l4 -= 20;
        }
        if (biomegenbase == Biome.PLAINS) {
            l4 -= 20;
        }
        l4 += k2;
        for (int i11 = 0; i11 < l4; ++i11) {
            final int k3 = k + this.random.nextInt(16) + 8;
            final int j18 = l + this.random.nextInt(16) + 8;
            final Structure worldgenerator = biomegenbase.getTree(this.random);
            worldgenerator.method_1143(1.0, 1.0, 1.0);
            worldgenerator.generate(this.level, this.random, k3, this.level.getHeight(k3, j18), j18);
        }
        for (int k4 = 0; k4 < 50; ++k4) {
            final int j19 = k + this.random.nextInt(16) + 8;
            final int l5 = this.random.nextInt(this.random.nextInt(120) + 8);
            final int l6 = l + this.random.nextInt(16) + 8;
            new AetherGenLiquids(BlockBase.FLOWING_WATER.id).generate(this.level, this.random, j19, l5, l6);
        }
        Sand.fallInstantly = false;
    }

    @Override
    public boolean deleteCacheCauseClientCantHandleThis(boolean iDontKnowWhy, ProgressListener listener) {
        return true;
    }

    @Override
    public boolean method_1801() {
        return false;
    }

    @Override
    public boolean method_1805() {
        return true;
    }

    @Override
    public String toString() {
        return "RandomLevelSource";
    }
}