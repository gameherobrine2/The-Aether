package com.gildedgames.aether.level.biome;

import net.minecraft.entity.EntityEntry;
import net.minecraft.level.biome.Biome;
import net.minecraft.level.structure.Structure;

import java.util.*;

import com.gildedgames.aether.entity.animal.EntityFlyingCow;
import com.gildedgames.aether.entity.mobs.EntityZephyr;
import com.gildedgames.aether.generator.AetherGenGoldenOak;
import com.gildedgames.aether.generator.AetherGenSkyroot;
import com.gildedgames.aether.mixin.MinecraftClientAccessor;

public class Aether extends Biome {

    public Aether() {
        monsters.clear();
        creatures.clear();
        waterCreatures.clear();
        creatures.add(new EntityEntry(EntityFlyingCow.class,10));
        monsters.add(new EntityEntry(EntityZephyr.class, 5));
    }

    @Override
    public Structure getTree(Random rand) {
    	if (rand.nextInt(100) == 0) {
    		return new AetherGenGoldenOak();
        }
        return new AetherGenSkyroot();
    }

    @Override
    public int getSkyColour(float temperature) {
        return 0xc0c0ff;
    }
}
