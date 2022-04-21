package com.gildedgames.aether.level.biome;

import net.minecraft.entity.EntityEntry;
import net.minecraft.level.biome.Biome;
import net.minecraft.level.structure.Structure;

import java.util.*;

import com.gildedgames.aether.entity.animal.EntityAechorPlant;
import com.gildedgames.aether.entity.animal.EntityAerbunny;
import com.gildedgames.aether.entity.animal.EntityFlyingCow;
import com.gildedgames.aether.entity.animal.EntityMoa;
import com.gildedgames.aether.entity.animal.EntityPhyg;
import com.gildedgames.aether.entity.animal.EntitySheepuff;
import com.gildedgames.aether.entity.animal.EntitySwet;
import com.gildedgames.aether.entity.animal.Whirly;
import com.gildedgames.aether.entity.mobs.EntityAerwhale;
import com.gildedgames.aether.entity.mobs.EntityCockatrice;
import com.gildedgames.aether.entity.mobs.EntityZephyr;
import com.gildedgames.aether.generator.AetherGenGoldenOak;
import com.gildedgames.aether.generator.AetherGenSkyroot;
import com.gildedgames.aether.mixin.MinecraftClientAccessor;

public class Aether extends Biome {

    public Aether() {
        monsters.clear();
        creatures.clear();
        waterCreatures.clear();
        if (com.gildedgames.aether.Aether.raritySwet != 0) {
           this.creatures.add(new EntityEntry(EntitySwet.class, com.gildedgames.aether.Aether.raritySwet));
        }
        if (com.gildedgames.aether.Aether.rarityAechorPlant != 0) {
            this.creatures.add(new EntityEntry(EntityAechorPlant.class, com.gildedgames.aether.Aether.rarityAechorPlant));
        }
        if (com.gildedgames.aether.Aether.rarityCockatrice != 0) {
            this.monsters.add(new EntityEntry(EntityCockatrice.class, com.gildedgames.aether.Aether.rarityCockatrice));
        }
        if (com.gildedgames.aether.Aether.rarityAerwhale != 0) {
            this.monsters.add(new EntityEntry(EntityAerwhale.class, com.gildedgames.aether.Aether.rarityAerwhale));
        }
        if (com.gildedgames.aether.Aether.rarityZephyr != 0) {
            this.monsters.add(new EntityEntry(EntityZephyr.class, com.gildedgames.aether.Aether.rarityZephyr));
        }
        if (com.gildedgames.aether.Aether.raritySheepuff != 0) {
            this.creatures.add(new EntityEntry(EntitySheepuff.class, com.gildedgames.aether.Aether.raritySheepuff));
        }
        if (com.gildedgames.aether.Aether.rarityPhyg != 0) {
            this.creatures.add(new EntityEntry(EntityPhyg.class, com.gildedgames.aether.Aether.rarityPhyg));
        }
        if (com.gildedgames.aether.Aether.rarityMoa != 0) {
            this.creatures.add(new EntityEntry(EntityMoa.class, com.gildedgames.aether.Aether.rarityMoa));
        }
        if (com.gildedgames.aether.Aether.rarityFlyingCow != 0) {
            this.creatures.add(new EntityEntry(EntityFlyingCow.class, com.gildedgames.aether.Aether.rarityFlyingCow));
        }
        if (com.gildedgames.aether.Aether.rarityWhirlwind != 0) {
            this.creatures.add(new EntityEntry(Whirly.class, com.gildedgames.aether.Aether.rarityWhirlwind));
        }
        if (com.gildedgames.aether.Aether.rarityAerbunny != 0) {
            this.creatures.add(new EntityEntry(EntityAerbunny.class, com.gildedgames.aether.Aether.rarityAerbunny));
        }
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
