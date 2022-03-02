package com.gildedgames.aether.event.listener;

import com.gildedgames.aether.block.BlockAetherLeaves;
import com.gildedgames.aether.block.BlockAetherLog;
import com.gildedgames.aether.block.BlockAmbrosiumTorch;
import com.gildedgames.aether.block.BlockDungeon;
import com.gildedgames.aether.block.BlockPillar;
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
		//Sprites for blocks ^-^
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
        sprPillarTop = Atlases.getTerrain().addTexture(Identifier.of(MOD_ID, "block/PillarTop")).index;
        sprPillarSide = Atlases.getTerrain().addTexture(Identifier.of(MOD_ID, "block/PillarSide")).index;
        sprPillarTopSide = Atlases.getTerrain().addTexture(Identifier.of(MOD_ID, "block/PillarCarved")).index;
        sprHolystone = Atlases.getTerrain().addTexture(Identifier.of(MOD_ID, "block/HolyStone")).index;
        sprMossyHolystone = Atlases.getTerrain().addTexture(Identifier.of(MOD_ID, "block/MossyHolystone")).index;
        sprFreezerTop = Atlases.getTerrain().addTexture(Identifier.of(MOD_ID, "block/FreezerTop")).index;
        sprFreezerSide = Atlases.getTerrain().addTexture(Identifier.of(MOD_ID, "block/FreezerSide")).index;
        sprQuicksoilGlass = Atlases.getTerrain().addTexture(Identifier.of(MOD_ID, "block/QuicksoilGlass")).index;
        sprGravititeOre = Atlases.getTerrain().addTexture(Identifier.of(MOD_ID, "block/GravititeOre")).index;
        sprIncubatorTop = Atlases.getTerrain().addTexture(Identifier.of(MOD_ID, "block/IncubatorTop")).index;
        sprIncubatorSide = Atlases.getTerrain().addTexture(Identifier.of(MOD_ID, "block/IncubatorSide")).index;
        sprEnchanterTop = Atlases.getTerrain().addTexture(Identifier.of(MOD_ID, "block/EnchanterTop")).index;
        sprEnchanterSide = Atlases.getTerrain().addTexture(Identifier.of(MOD_ID, "block/EnchanterSide")).index;
        sprPortal = Atlases.getTerrain().addTexture(Identifier.of(MOD_ID, "block/Portal")).index;
        
        //Textures for some items
        sprShooterNormal = Atlases.getGuiItems().addTexture(Identifier.of(MOD_ID, "items/DartShooter")).index;
        sprShooterPoison = Atlases.getGuiItems().addTexture(Identifier.of(MOD_ID, "items/DartShooterPoison")).index;
        sprShooterEnchanted = Atlases.getGuiItems().addTexture(Identifier.of(MOD_ID, "items/DartShooterEnchanted")).index;
        sprDartGolden = Atlases.getGuiItems().addTexture(Identifier.of(MOD_ID, "items/DartGolden")).index;
        sprDartEnchanted = Atlases.getGuiItems().addTexture(Identifier.of(MOD_ID, "items/DartEnchanted")).index;
        sprDartPoison = Atlases.getGuiItems().addTexture(Identifier.of(MOD_ID, "items/DartPoison")).index;
        ItemSkyrootBucket.sprEmpty = Atlases.getGuiItems().addTexture(Identifier.of(MOD_ID, "items/Bucket")).index;
        ItemSkyrootBucket.sprWater = Atlases.getGuiItems().addTexture(Identifier.of(MOD_ID, "items/BucketWater")).index;
        ItemSkyrootBucket.sprMilk = Atlases.getGuiItems().addTexture(Identifier.of(MOD_ID, "items/BucketMilk")).index;
        ItemSkyrootBucket.sprPoison = Atlases.getGuiItems().addTexture(Identifier.of(MOD_ID, "items/BucketPoison")).index;
        ItemSkyrootBucket.sprRemedy = Atlases.getGuiItems().addTexture(Identifier.of(MOD_ID, "items/BucketRemedy")).index;
        
        //Some textures for blocks
        ((BlockAmbrosiumTorch)AetherBlocks.AMBROSIUM_TORCH).texture = sprAmbrosiumTorch;
        
        //Items
        
        ((ItemAmbrosium)AetherItems.AmbrosiumShard).setTexture(Identifier.of(MOD_ID, "items/AmbrosiumShard"));
        ((ItemAetherKey)AetherItems.Key).setTexture(Identifier.of(MOD_ID, "items/Key"));
        ((ItemAether)AetherItems.VictoryMedal).setTexture(Identifier.of(MOD_ID, "items/VictoryMedal"));
        ((ItemAether)AetherItems.Stick).setTexture(Identifier.of(MOD_ID, "items/Stick"));
        ((ItemMoreArmor)AetherItems.GoldPendant).setTexture(Identifier.of(MOD_ID, "items/Pendant"));
        ((ItemMoreArmor)AetherItems.IronPendant).setTexture(Identifier.of(MOD_ID, "items/Pendant"));
        ((ItemMoreArmor)AetherItems.IcePendant).setTexture(Identifier.of(MOD_ID, "items/Pendant"));
        ((ItemMoreArmor)AetherItems.ZanitePendant).setTexture(Identifier.of(MOD_ID, "items/Pendant"));
        ((ItemMoreArmor)AetherItems.IronRing).setTexture(Identifier.of(MOD_ID, "items/Ring"));
        ((ItemMoreArmor)AetherItems.GoldRing).setTexture(Identifier.of(MOD_ID, "items/Ring"));
        ((ItemMoreArmor)AetherItems.IceRing).setTexture(Identifier.of(MOD_ID, "items/Ring"));
        ((ItemMoreArmor)AetherItems.ZaniteRing).setTexture(Identifier.of(MOD_ID, "items/Ring"));
        ((ItemMoaEgg)AetherItems.MoaEgg).setTexture(Identifier.of(MOD_ID, "items/MoaEgg"));
        ((ItemAether)AetherItems.AechorPetal).setTexture(Identifier.of(MOD_ID, "items/AechorPetal"));
        ((ItemAether)AetherItems.GoldenAmber).setTexture(Identifier.of(MOD_ID, "items/GoldenAmber"));
	}
	//this is Alias code but i changed public static booleans to ints and renamed them
	public static int sprIncubatorSide, sprIncubatorTop,sprPortal;
	public static int sprEnchanterSide, sprEnchanterTop;
    public static int sprShooterNormal,sprQuicksoilGlass;
    public static int sprShooterPoison;
    public static int sprShooterEnchanted;
    public static int sprDartGolden;
    public static int sprDartEnchanted;
    public static int sprDartPoison;
	public static int sprFreezerTop;
	public static int sprFreezerSide;
    public static int sprPillarTop,sprHolystone,sprMossyHolystone;
    public static int sprPillarSide;
    public static int sprPillarTopSide;
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
	public static int sprGravititeOre;
    @Entrypoint.ModID
    public static final ModID MOD_ID = Null.get();
}
