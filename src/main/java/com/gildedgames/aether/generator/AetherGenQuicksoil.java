package com.gildedgames.aether.generator;

import java.util.Random;
import net.minecraft.level.Level;
import net.minecraft.level.structure.Structure;

public class AetherGenQuicksoil extends Structure {
    private int minableBlockId;
    
    public AetherGenQuicksoil(final int i) {
        this.minableBlockId = i;
    }
    
    @Override
    public boolean generate(final Level level, final Random rand, final int x, final int y, final int z) {
        for (int x2 = x - 3; x2 < x + 4; ++x2) {
            for (int z2 = z - 3; z2 < z + 4; ++z2) {
                if (level.getTileId(x2, y, z2) == 0 && (x2 - x) * (x2 - x) + (z2 - z) * (z2 - z) < 12) {
                    level.setTileInChunk(x2, y, z2, this.minableBlockId);
                }
            }
        }
        return true;
    }
}
