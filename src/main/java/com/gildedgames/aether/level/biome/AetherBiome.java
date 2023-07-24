package com.gildedgames.aether.level.biome;

import com.gildedgames.aether.entity.animal.*;
import com.gildedgames.aether.entity.mobs.EntityAerwhale;
import com.gildedgames.aether.entity.mobs.EntityCockatrice;
import com.gildedgames.aether.entity.mobs.EntityZephyr;
import com.gildedgames.aether.generator.AetherGenGoldenOak;
import com.gildedgames.aether.generator.AetherGenSkyroot;
import net.minecraft.entity.EntityEntry;
import net.minecraft.level.biome.Biome;
import net.minecraft.level.structure.Structure;

import java.util.Random;

public class AetherBiome extends Biome
{

    public static int raritySwet;
    public static int rarityAechorPlant;
    public static int rarityCockatrice;
    public static int rarityAerwhale;
    public static int rarityZephyr;
    public static int raritySheepuff;
    public static int rarityPhyg;
    public static int rarityMoa;
    public static int rarityFlyingCow;
    public static int rarityWhirlwind;
    public static int rarityAerbunny;

    static
    {
        raritySwet = 8;
        rarityAechorPlant = 8;
        rarityCockatrice = 3;
        rarityAerwhale = 8;
        rarityZephyr = 5;
        raritySheepuff = 10;
        rarityPhyg = 12;
        rarityMoa = 10;
        rarityFlyingCow = 10;
        rarityWhirlwind = 8;
        rarityAerbunny = 11;
    }

    public AetherBiome()
    {
        monsters.clear();
        creatures.clear();
        waterCreatures.clear();
        if (raritySwet != 0)
        {
            this.creatures.add(new EntityEntry(EntitySwet.class, raritySwet));
        }
        if (rarityAechorPlant != 0)
        {
            this.creatures.add(new EntityEntry(EntityAechorPlant.class, rarityAechorPlant));
        }
        if (rarityCockatrice != 0)
        {
            this.monsters.add(new EntityEntry(EntityCockatrice.class, rarityCockatrice));
        }
        if (rarityAerwhale != 0)
        {
            this.monsters.add(new EntityEntry(EntityAerwhale.class, rarityAerwhale));
        }
        if (rarityZephyr != 0)
        {
            this.monsters.add(new EntityEntry(EntityZephyr.class, rarityZephyr));
        }
        if (raritySheepuff != 0)
        {
            this.creatures.add(new EntityEntry(EntitySheepuff.class, raritySheepuff));
        }
        if (rarityPhyg != 0)
        {
            this.creatures.add(new EntityEntry(EntityPhyg.class, rarityPhyg));
        }
        if (rarityMoa != 0)
        {
            this.creatures.add(new EntityEntry(EntityMoa.class, rarityMoa));
        }
        if (rarityFlyingCow != 0)
        {
            this.creatures.add(new EntityEntry(EntityFlyingCow.class, rarityFlyingCow));
        }
        /* todo: particles fix
        if (rarityWhirlwind != 0) {
            this.creatures.add(new EntityEntry(Whirly.class, rarityWhirlwind));
        }*/
        if (rarityAerbunny != 0)
        {
            this.creatures.add(new EntityEntry(EntityAerbunny.class, rarityAerbunny));
        }
    }

    @Override
    public Structure getTree(Random rand)
    {
        if (rand.nextInt(100) == 0)
        {
            return new AetherGenGoldenOak();
        }
        return new AetherGenSkyroot();
    }

    @Override
    public int getSkyColour(float temperature)
    {
        return 0xc0c0ff;
    }
}
