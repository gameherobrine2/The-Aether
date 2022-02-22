package com.gildedgames.aether.generator;
import java.util.Random;

import com.gildedgames.aether.registry.AetherBlocks;

import net.minecraft.level.Level;
import net.minecraft.level.structure.Structure;

public class AetherGenGoldenOak extends Structure {
    public boolean branch(final Level world, final Random random, int i, int j, int k, final int slant) {
        final int directionX = random.nextInt(3) - 1;
        final int directionY = slant;
        final int directionZ = random.nextInt(3) - 1;
        for (int n = 0; n < random.nextInt(2) + 1; ++n) {
            i += directionX;
            j += directionY;
            k += directionZ;
            if (world.getTileId(i, j, k) == AetherBlocks.GOLDEN_OAK_LEAVES.id) {
                world.setTileWithMetadata(i, j, k, AetherBlocks.LOG.id, 2);
            }
        }
        return true;
    }
    
    @Override
    public boolean generate(final Level level, final Random rand, final int x, final int y, final int z) {
        if (level.getTileId(x, y - 1, z) != AetherBlocks.AETHER_GRASS_BLOCK.id && level.getTileId(x, y - 1, z) != AetherBlocks.AETHER_DIRT.id) {
            return false;
        }
        final int height = rand.nextInt(5) + 6;
        for (int x2 = x - 3; x2 < x + 4; ++x2) {
            for (int y2 = y + 5; y2 < y + 12; ++y2) {
                for (int z2 = z - 3; z2 < z + 4; ++z2) {
                    if ((x2 - x) * (x2 - x) + (y2 - y - 8) * (y2 - y - 8) + (z2 - z) * (z2 - z) < 12 + rand.nextInt(5) && level.getTileId(x2, y2, z2) == 0) {
                        level.setTileInChunk(x2, y2, z2, AetherBlocks.GOLDEN_OAK_LEAVES.id);
                    }
                }
            }
        }
        for (int n = 0; n < height; ++n) {
            if (n > 4 && rand.nextInt(3) > 0) {
                this.branch(level, rand, x, y + n, z, n / 4 - 1);
            }
            level.setTileWithMetadata(x, y + n, z, AetherBlocks.LOG.id, 2);
        }
        return true;
    }
}
