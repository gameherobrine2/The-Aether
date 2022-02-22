package com.gildedgames.aether.generator;
import net.minecraft.block.BlockBase;
import java.util.Random;

import com.gildedgames.aether.registry.AetherBlocks;

import net.minecraft.level.Level;
import net.minecraft.level.structure.Structure;

public class AetherGenLiquids extends Structure {
    private int liquidBlockId;
    
    public AetherGenLiquids(final int i) {
        this.liquidBlockId = i;
    }
    
    @Override
    public boolean generate(final Level level, final Random rand, final int x, final int y, final int z) {
        if (level.getTileId(x, y + 1, z) != AetherBlocks.HOLYSTONE.id || level.getTileMeta(x, y + 1, z) >= 2) {
            return false;
        }
        if (level.getTileId(x, y - 1, z) != AetherBlocks.HOLYSTONE.id || level.getTileMeta(x, y - 1, z) >= 2) {
            return false;
        }
        if (level.getTileId(x, y, z) != 0 && (level.getTileId(x, y, z) != AetherBlocks.HOLYSTONE.id || level.getTileMeta(x, y, z) >= 2)) {
            return false;
        }
        int l = 0;
        if (level.getTileId(x - 1, y, z) == AetherBlocks.HOLYSTONE.id || level.getTileMeta(x - 1, y, z) >= 2) {
            ++l;
        }
        if (level.getTileId(x + 1, y, z) == AetherBlocks.HOLYSTONE.id || level.getTileMeta(x + 1, y, z) >= 2) {
            ++l;
        }
        if (level.getTileId(x, y, z - 1) == AetherBlocks.HOLYSTONE.id || level.getTileMeta(x, y, z - 1) >= 2) {
            ++l;
        }
        if (level.getTileId(x, y, z + 1) == AetherBlocks.HOLYSTONE.id || level.getTileMeta(x, y, z + 1) >= 2) {
            ++l;
        }
        int i1 = 0;
        if (level.isAir(x - 1, y, z)) {
            ++i1;
        }
        if (level.isAir(x + 1, y, z)) {
            ++i1;
        }
        if (level.isAir(x, y, z - 1)) {
            ++i1;
        }
        if (level.isAir(x, y, z + 1)) {
            ++i1;
        }
        if (l == 3 && i1 == 1) {
            level.setTile(x, y, z, this.liquidBlockId);
            level.field_197 = true;
            BlockBase.BY_ID[this.liquidBlockId].onScheduledTick(level, x, y, z, rand);
            level.field_197 = false;
        }
        return true;
    }
}
