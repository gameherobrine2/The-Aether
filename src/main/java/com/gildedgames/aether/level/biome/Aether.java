package com.gildedgames.aether.level.biome;

import net.minecraft.level.biome.Biome;
import net.minecraft.level.structure.Structure;

import java.util.*;

public class Aether extends Biome {

    public Aether() {
        monsters.clear();
        creatures.clear();
        waterCreatures.clear();
    }

    @Override
    public Structure getTree(Random rand) {
        return super.getTree(rand);
    }

    @Override
    public int getSkyColour(float temperature) {
        return 0xc0c0ff;
    }
}
