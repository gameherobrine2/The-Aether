package com.gildedgames.aether.event.listener;

import com.gildedgames.aether.block.BlockAetherLeaves;
import com.gildedgames.aether.block.BlockAetherLog;
import com.gildedgames.aether.block.BlockDungeon;
import com.gildedgames.aether.block.BlockTrap;
import com.gildedgames.aether.item.*;
import com.gildedgames.aether.registry.AetherBlocks;
import com.gildedgames.aether.registry.AetherItems;

import net.mine_diver.unsafeevents.listener.EventListener;
import net.modificationstation.stationapi.api.client.event.texture.TextureRegisterEvent;
import net.modificationstation.stationapi.api.client.texture.atlas.Atlases;
import net.modificationstation.stationapi.api.mod.entrypoint.Entrypoint;
import net.modificationstation.stationapi.api.registry.Identifier;
import net.modificationstation.stationapi.api.registry.ModID;
import net.modificationstation.stationapi.api.util.Null;

public class TextureListener {
	@EventListener
    public void registerTextures(TextureRegisterEvent event) {
		sprTop = Atlases.getTerrain().addTexture(Identifier.of(MOD_ID, "block/SkyrootLogTop")).index;
		sprSide = Atlases.getTerrain().addTexture(Identifier.of(MOD_ID, "block/SkyrootLogSide")).index;
		sprGoldenSide = Atlases.getTerrain().addTexture(Identifier.of(MOD_ID, "block/GoldenOak")).index;
		sprSkyroot = Atlases.getTerrain().addTexture(Identifier.of(MOD_ID, "block/SkyrootLeaves")).index;
        sprGoldenOak = Atlases.getTerrain().addTexture(Identifier.of(MOD_ID, "block/GoldenOakLeaves")).index;
        sprAercloud = Atlases.getTerrain().addTexture(Identifier.of(MOD_ID, "block/Aercloud")).index;
        sprAerogel = Atlases.getTerrain().addTexture(Identifier.of(MOD_ID, "block/Aerogel")).index;
        sprWhiteFlower =  Atlases.getTerrain().addTexture(Identifier.of(MOD_ID, "block/WhiteFlower")).index;
        sprPurpleFlower = Atlases.getTerrain().addTexture(Identifier.of(MOD_ID, "block/PurpleFlower")).index;
        sprGoldenSapling = Atlases.getTerrain().addTexture(Identifier.of(MOD_ID, "block/GoldenOakSapling")).index;
        sprOakSapling = Atlases.getTerrain().addTexture(Identifier.of(MOD_ID, "block/SkyrootSapling")).index;
        sprAmbrosiumOre = Atlases.getTerrain().addTexture(Identifier.of(MOD_ID, "block/AmbrosiumOre")).index;
        sprIceStone = Atlases.getTerrain().addTexture(Identifier.of(MOD_ID, "block/Icestone")).index;
        sprPlank = Atlases.getTerrain().addTexture(Identifier.of(MOD_ID, "block/Plank")).index;
        sprDirt = Atlases.getTerrain().addTexture(Identifier.of(MOD_ID, "block/Dirt")).index;
        sprGrassTop = Atlases.getTerrain().addTexture(Identifier.of(MOD_ID, "block/GrassTop")).index;
        sprGrassSide = Atlases.getTerrain().addTexture(Identifier.of(MOD_ID, "block/GrassSide")).index;
        sprAmbrosiumTorch = Atlases.getTerrain().addTexture(Identifier.of(MOD_ID, "block/AmbrosiumTorch")).index;
        sprZaniteOre = Atlases.getTerrain().addTexture(Identifier.of(MOD_ID, "block/ZaniteOre")).index;
        sprQuicksoil = Atlases.getTerrain().addTexture(Identifier.of(MOD_ID, "block/Quicksoil")).index;
        sprBronze = Atlases.getTerrain().addTexture(Identifier.of(MOD_ID, "block/CarvedStone")).index;
        sprSilver = Atlases.getTerrain().addTexture(Identifier.of(MOD_ID, "block/AngelicStone")).index;
        sprGold = Atlases.getTerrain().addTexture(Identifier.of(MOD_ID, "block/HellfireStone")).index;
        sprBronzeLit = Atlases.getTerrain().addTexture(Identifier.of(MOD_ID, "block/LightCarvedStone")).index;
        sprSilverLit = Atlases.getTerrain().addTexture(Identifier.of(MOD_ID, "block/LightAngelicStone")).index;
        sprGoldLit = Atlases.getTerrain().addTexture(Identifier.of(MOD_ID, "block/LightHellfireStone")).index;
        sprChestFront = Atlases.getTerrain().addTexture(Identifier.of(MOD_ID, "block/LockedChestFront")).index;
        sprChestSide = Atlases.getTerrain().addTexture(Identifier.of(MOD_ID, "block/LockedChestSide")).index;
        ((ItemAmbrosium)AetherItems.AmbrosiumShard).setTexture(Identifier.of(MOD_ID, "items/AmbrosiumShard"));
        
        ((ItemAether)AetherItems.Stick).setTexture(Identifier.of(MOD_ID, "items/Stick"));
        ((ItemMoreArmor)AetherItems.IronRing).setTexture(Identifier.of(MOD_ID, "items/Ring"));
        ((ItemMoreArmor)AetherItems.GoldRing).setTexture(Identifier.of(MOD_ID, "items/Ring"));
        ((ItemMoreArmor)AetherItems.IceRing).setTexture(Identifier.of(MOD_ID, "items/Ring"));
        ((ItemMoreArmor)AetherItems.ZaniteRing).setTexture(Identifier.of(MOD_ID, "items/Ring"));
	}
	
    public static int sprBronze,sprChestFront,sprChestSide;
    public static int sprSilver;
    public static int sprGold;
    public static int sprBronzeLit;
    public static int sprSilverLit;
    public static int sprGoldLit;
	public static int sprAmbrosiumOre,sprIceStone,sprPlank,sprDirt,sprGrassTop,sprGrassSide,sprAmbrosiumTorch,sprQuicksoil;
	public static int sprGoldenSapling, sprOakSapling;
	public static int sprWhiteFlower, sprPurpleFlower;
	public static int sprAerogel;
	public static int sprAercloud;
    public static int sprSkyroot;
    public static int sprGoldenOak;
    public static int sprTop;
    public static int sprSide;
    public static int sprGoldenSide;
	public static int sprZaniteOre;
    @Entrypoint.ModID
    public static final ModID MOD_ID = Null.get();
}
