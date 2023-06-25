package com.gildedgames.aether.registry;

import com.gildedgames.aether.block.*;
import com.gildedgames.aether.event.listener.TextureListener;

import net.mine_diver.unsafeevents.listener.EventListener;
import net.minecraft.block.BlockBase;
import net.minecraft.block.material.Material;
import net.modificationstation.stationapi.api.event.registry.BlockRegistryEvent;
import net.modificationstation.stationapi.api.mod.entrypoint.Entrypoint;
import net.modificationstation.stationapi.api.mod.entrypoint.EventBusPolicy;

import static com.gildedgames.aether.Aether.MODID;
import static com.gildedgames.aether.Aether.of;

@Entrypoint(eventBus = @EventBusPolicy(registerInstance = false))
public class AetherBlocks {

    @EventListener
    private static void registerBlocks(BlockRegistryEvent event) {
        AETHER_PORTAL = new AetherPortal(of("aether_portal"), 0).setUnbreakable().setBlastResistance(6_000_000).setTranslationKey(MODID, "AetherPortal");
        AETHER_DIRT = new AetherDirt(of("aether_dirt")).setHardness(0.2F).setSounds(BlockBase.GRAVEL_SOUNDS).setTranslationKey(MODID, "AetherDirt");
        AETHER_GRASS_BLOCK = new AetherGrassBlock(of("aether_grass_block")).setHardness(0.2F).setSounds(BlockBase.GRASS_SOUNDS).setTranslationKey(MODID, "AetherGrass");
        HOLYSTONE = new BlockHolystone(of("holystone")).setHardness(0.5F).setSounds(BlockBase.STONE_SOUNDS).setTranslationKey(MODID, "Holystone");
        MOSSY_HOLYSTONE = new BlockMossyHolystone(of("mossy_holystone")).setHardness(0.5F).setSounds(BlockBase.STONE_SOUNDS).setTranslationKey(MODID, "MossyHolystone");
        SKYROOT_LOG = new BlockLog(of("skyroot_log")).setHardness(2.0f).setSounds(BlockBase.WOOD_SOUNDS).setTranslationKey(MODID, "SkyrootLog");
        GOLDEN_OAK_LOG = new BlockLog(of("golden_oak_log")).setHardness(2.0f).setSounds(BlockBase.WOOD_SOUNDS).setTranslationKey(MODID, "GoldenOakLog");
        SKYROOT_LEAVES = new BlockAetherLeaves(of("skyroot_leaves"),false).setHardness(0.2f).setLightOpacity(1).setSounds(BlockBase.GRASS_SOUNDS).setTranslationKey(MODID, "SkyrootLeaves");
        GOLDEN_OAK_LEAVES = new BlockAetherLeaves(of("golden_oak_leaves"),true).setHardness(0.2f).setLightOpacity(1).setSounds(BlockBase.GRASS_SOUNDS).setTranslationKey(MODID, "GoldenOakLeaves");
        AERCLOUD = new BlockAercloud(of("aercloud")).setHardness(0.2f).setLightOpacity(3).setSounds(BlockBase.WOOL_SOUNDS).setTranslationKey(MODID, "Aercloud");
        AEROGEL = new BlockAerogel(of("aerogel")).setHardness(1.0f).setBlastResistance(2000.0f).setLightOpacity(3).setSounds(BlockBase.PISTON_SOUNDS).setTranslationKey(MODID, "Aerogel");
        WHITE_FLOWER = new BlockAetherFlower(of("white_flower"), TextureListener.sprWhiteFlower).setHardness(0.0f).setSounds(BlockBase.GRASS_SOUNDS).setTranslationKey(MODID, "WhiteFlower");
        PURPLE_FLOWER = new BlockAetherFlower(of("purple_flower"), TextureListener.sprPurpleFlower).setHardness(0.0f).setSounds(BlockBase.GRASS_SOUNDS).setTranslationKey(MODID, "PurpleFlower");
        SKYROOT_SAPLING = new BlockAetherSapling(of("skyroot_sapling"),false).setTranslationKey(MODID, "SkyrootSapling").setHardness(0.0f).setSounds(BlockBase.GRASS_SOUNDS);
        GOLDEN_OAK_SAPLING = new BlockAetherSapling(of("golden_oak_sapling"),true).setTranslationKey(MODID, "GoldenOakSapling").setHardness(0.0f).setSounds(BlockBase.GRASS_SOUNDS);
        AMBROSIUM_ORE = new BlockAmbrosiumOre(of("ambrosium_ore")).setHardness(3.0f).setBlastResistance(5.0f).setSounds(BlockBase.PISTON_SOUNDS).setTranslationKey(MODID, "AmbrosiumOre");
        ICESTONE = new BlockIcestone(of("icestone")).setHardness(3.0f).setSounds(BlockBase.GLASS_SOUNDS).setTranslationKey(MODID, "Icestone");
        SKYROOT_PLANKS = new BlockAetherPlank(of("skyroot_planks"),Material.WOOD).setHardness(2.0f).setBlastResistance(5.0f).setSounds(BlockBase.WOOD_SOUNDS).setTranslationKey(MODID, "AetherPlank");
        AMBROSIUM_TORCH = new BlockAmbrosiumTorch(of("ambrosium_torch")).setLightEmittance(0.9375f).setSounds(BlockBase.WOOD_SOUNDS).setTranslationKey(MODID, "AmbrosiumTorch");
        ZANITE_ORE = new BlockZaniteOre(of("zanite_ore")).setHardness(3.0f).setSounds(BlockBase.PISTON_SOUNDS).setTranslationKey(MODID, "ZaniteOre");
        ZANITE_BLOCK = new BlockZanite(of("zanite_block")).setHardness(3.0f).setSounds(BlockBase.PISTON_SOUNDS).setTranslationKey(MODID, "ZaniteBlock");
        QUICKSOIL = new BlockQuicksoil(of("quicksoil")).setHardness(0.5f).setSounds(BlockBase.SAND_SOUNDS).setTranslationKey(MODID, "Quicksoil");
        DUNGEON_STONE = new BlockDungeon(of("dungeon_stone")).setHardness(0.5f).setSounds(BlockBase.PISTON_SOUNDS).setTranslationKey(MODID, "DungeonStone");
        LIGHT_DUNGEON_STONE = new BlockDungeon(of("light_dungeon_stone")).setHardness(0.5f).setSounds(BlockBase.PISTON_SOUNDS).setLightEmittance(0.75f).setTranslationKey(MODID, "LightDungeonStone");
        LOCKED_DUNGEON_STONE = new BlockDungeon(of("locked_dungeon_stone")).setHardness(-1.0f).setBlastResistance(1000000.0f).setSounds(BlockBase.PISTON_SOUNDS).setTranslationKey(MODID, "LockedDungeonStone");
        LOCKED_LIGHT_DUNGEON_STONE = new BlockDungeon(of("locked_light_dungeon_stone")).setHardness(-1.0f).setBlastResistance(1000000.0f).setSounds(BlockBase.PISTON_SOUNDS).setLightEmittance(0.5f).setTranslationKey(MODID, "LightLockedDungeonStone");
        TRAPPED_DUNGEON_STONE = new BlockTrap(of("trap")).setHardness(-1.0f).setBlastResistance(1000000.0f).setSounds(BlockBase.PISTON_SOUNDS).setTranslationKey(MODID, "Trap");
        TREASURE_CHEST = new BlockTreasureChest(of("treasure_chest")).setHardness(-1.0f).setSounds(BlockBase.PISTON_SOUNDS).setTranslationKey(MODID, "TreasureChest");
        CHEST_MIMIC = new BlockChestMimic(of("mimic_chest")).setHardness(2.0f).setSounds(BlockBase.WOOD_SOUNDS).setTranslationKey(MODID, "Mimic");
        PILLAR = new BlockPillar(of("pillar")).setHardness(0.5f).setSounds(BlockBase.PISTON_SOUNDS).setTranslationKey(MODID, "Pillar");
        FREEZER = new BlockFreezer(of("freezer")).setHardness(2.5f).setSounds(BlockBase.PISTON_SOUNDS).setTranslationKey(MODID, "Freezer");
        QUICKSOIL_GLASS = new BlockQuicksoilGlass(of("quicksoilglass")).setLightEmittance(0.7375f).setHardness(0.2f).setLightOpacity(0).setSounds(BlockBase.GLASS_SOUNDS).setTranslationKey(MODID, "QuicksoilGlass");
        GRAVITITE_ORE = new BlockFloating(of("gravitite_ore"), false).setHardness(5.0f).setSounds(BlockBase.PISTON_SOUNDS).setTranslationKey(MODID, "GravititeOre");
        ENCHANTED_GRAVITITE = new BlockEnchantedGravitite(of("enchanted_gravitite"), true).setHardness(5.0f).setSounds(BlockBase.PISTON_SOUNDS).setTranslationKey(MODID, "EnchantedGravitite");
        ENCHANTER = new BlockEnchanter(of("enchanter")).setTranslationKey(MODID, "Enchanter").setHardness(2.0f);
        INCUBATOR = new BlockIncubator(of("incubator")).setTranslationKey(MODID, "Incubator").setHardness(2.0f);
        BED = new BlockAetherBed(of("aether_bed")).setHardness(0.2f).setTranslationKey(MODID, "aether_bed").disableStat().disableNotifyOnMetaDataChange();
    }
    
    public static boolean isGood(final int id, final int meta) { //AETHER 1.02
        return id == 0 || id == AetherBlocks.AERCLOUD.id;
    }
    public static BlockBase
            AETHER_PORTAL,
            AETHER_DIRT,
            AETHER_GRASS_BLOCK,
            BED,
            QUICKSOIL,
            HOLYSTONE,
            MOSSY_HOLYSTONE,
            ICESTONE,
            AERCLOUD,
            AEROGEL,
            SKYROOT_LOG,
            GOLDEN_OAK_LOG,
            SKYROOT_PLANKS,
            SKYROOT_LEAVES,
            GOLDEN_OAK_LEAVES,
            SKYROOT_SAPLING,
            GOLDEN_OAK_SAPLING,
            AMBROSIUM_ORE,
            AMBROSIUM_TORCH,
            ZANITE_ORE,
            GRAVITITE_ORE,
            ENCHANTED_GRAVITITE,
            ENCHANTER,
            TRAPPED_DUNGEON_STONE,
            CHEST_MIMIC,
            TREASURE_CHEST,
            DUNGEON_STONE,
            LIGHT_DUNGEON_STONE,
            LOCKED_DUNGEON_STONE,
            LOCKED_LIGHT_DUNGEON_STONE,
            PILLAR,
            ZANITE_BLOCK,
            QUICKSOIL_GLASS,
            FREEZER,
            WHITE_FLOWER,
            PURPLE_FLOWER,
            INCUBATOR;
}
