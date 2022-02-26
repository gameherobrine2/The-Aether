package com.gildedgames.aether.registry;

import com.gildedgames.aether.block.AetherDirt;
import com.gildedgames.aether.block.AetherGrassBlock;
import com.gildedgames.aether.block.AetherPortal;
import com.gildedgames.aether.block.BlockAercloud;
import com.gildedgames.aether.block.BlockAerogel;
import com.gildedgames.aether.block.BlockAetherFlower;
import com.gildedgames.aether.block.BlockAetherLeaves;
import com.gildedgames.aether.block.BlockAetherLog;
import com.gildedgames.aether.block.BlockAetherPlank;
import com.gildedgames.aether.block.BlockAetherSapling;
import com.gildedgames.aether.block.BlockAmbrosiumOre;
import com.gildedgames.aether.block.BlockAmbrosiumTorch;
import com.gildedgames.aether.block.BlockChestMimic;
import com.gildedgames.aether.block.BlockDungeon;
import com.gildedgames.aether.block.BlockIcestone;
import com.gildedgames.aether.block.BlockPillar;
import com.gildedgames.aether.block.BlockQuicksoil;
import com.gildedgames.aether.block.BlockTrap;
import com.gildedgames.aether.block.BlockTreasureChest;
import com.gildedgames.aether.block.BlockZanite;
import com.gildedgames.aether.block.BlockZaniteOre;
import com.gildedgames.aether.block.Holystone;
import com.gildedgames.aether.event.listener.TextureListener;

import net.mine_diver.unsafeevents.listener.EventListener;
import net.minecraft.block.BlockBase;
import net.minecraft.block.material.Material;
import net.modificationstation.stationapi.api.event.registry.BlockRegistryEvent;
import net.modificationstation.stationapi.api.mod.entrypoint.Entrypoint;
import net.modificationstation.stationapi.api.mod.entrypoint.EventBusPolicy;
import net.modificationstation.stationapi.api.registry.Identifier;
import net.modificationstation.stationapi.api.template.block.BlockTemplate;

import java.util.function.*;

import static com.gildedgames.aether.Aether.MODID;
import static com.gildedgames.aether.Aether.of;

@Entrypoint(eventBus = @EventBusPolicy(registerInstance = false))
public class AetherBlocks {

    @EventListener
    private static void registerBlocks(BlockRegistryEvent event) {
        AETHER_PORTAL = register("aether_portal", id -> new AetherPortal(id, 0).setUnbreakable().setBlastResistance(6_000_000));
        AETHER_DIRT = register("aether_dirt", id -> new AetherDirt(id).setHardness(0.2F).setSounds(BlockBase.GRAVEL_SOUNDS));
        AETHER_GRASS_BLOCK = register("aether_grass_block", id -> new AetherGrassBlock(id).setHardness(0.2F).setSounds(BlockBase.GRASS_SOUNDS));
        HOLYSTONE = register("holystone", id -> new Holystone(id).setHardness(0.5F).setSounds(BlockBase.STONE_SOUNDS));
        LOG = register("log", id -> new BlockAetherLog(id).setHardness(2.0f).setSounds(BlockBase.WOOD_SOUNDS));
        SKYROOT_LEAVES = register("skyroot_leaves", id -> new BlockAetherLeaves(id,false).setHardness(0.2f).setLightOpacity(1).setSounds(BlockBase.GRASS_SOUNDS));
        GOLDEN_OAK_LEAVES = register("golden_oak_leaves", id -> new BlockAetherLeaves(id,true).setHardness(0.2f).setLightOpacity(1).setSounds(BlockBase.GRASS_SOUNDS));
        AERCLOUD = register("aercloud",id -> new BlockAercloud(id).setHardness(0.2f).setLightOpacity(3).setSounds(BlockBase.WOOL_SOUNDS).setTranslationKey("Aercloud"));
        AEROGEL = register("aerogel", id -> new BlockAerogel(id).setHardness(1.0f).setBlastResistance(2000.0f).setLightOpacity(3).setSounds(BlockBase.PISTON_SOUNDS).setTranslationKey("Aerogel"));
        WHITE_FLOWER = register("white_flower", id -> new BlockAetherFlower(id, TextureListener.sprWhiteFlower).setHardness(0.0f).setSounds(BlockBase.GRASS_SOUNDS).setTranslationKey("White_Flower"));
        PURPLE_FLOWER = register("purple_flower", id -> new BlockAetherFlower(id, TextureListener.sprPurpleFlower).setHardness(0.0f).setSounds(BlockBase.GRASS_SOUNDS).setTranslationKey("Purple_Flower"));
        SKYROOT_SAPLING = register("skyroot_sapling", id -> new BlockAetherSapling(id,false).setTranslationKey("SkyrootSapling").setHardness(0.0f).setSounds(BlockBase.GRASS_SOUNDS));
        GOLDEN_OAK_SAPLING = register("golden_oak_sapling", id -> new BlockAetherSapling(id,true).setTranslationKey("GoldenOakSapling").setHardness(0.0f).setSounds(BlockBase.GRASS_SOUNDS));
        AMBROSIUM_ORE = register("ambrosium_ore", id -> new BlockAmbrosiumOre(id).setHardness(3.0f).setBlastResistance(5.0f).setSounds(BlockBase.PISTON_SOUNDS).setTranslationKey("AmbrosiumOre"));
        ICESTONE = register("icestone", id -> new BlockIcestone(id).setHardness(3.0f).setSounds(BlockBase.GLASS_SOUNDS).setTranslationKey("Icestone"));
        SKYROOT_PLANKS = register("skyroot_planks", id -> new BlockAetherPlank(id,Material.WOOD).setHardness(2.0f).setBlastResistance(5.0f).setSounds(BlockBase.WOOD_SOUNDS).setTranslationKey("AetherPlank"));
        AMBROSIUM_TORCH = register("ambrosium_torch", id -> new BlockAmbrosiumTorch(id).setLightEmittance(0.9375f).setSounds(BlockBase.WOOD_SOUNDS).setTranslationKey("AmbrosiumTorch"));
        ZANITE_ORE = register("zanite_ore", id -> new BlockZaniteOre(id).setHardness(3.0f).setSounds(BlockBase.PISTON_SOUNDS).setTranslationKey("ZaniteOre"));
        ZANITE_BLOCK = register("zanite_block", id -> new BlockZanite(id).setHardness(3.0f).setSounds(BlockBase.PISTON_SOUNDS).setTranslationKey("ZaniteBlock"));
        QUICKSOIL = register("quicksoil", id -> new BlockQuicksoil(id).setHardness(0.5f).setSounds(BlockBase.SAND_SOUNDS).setTranslationKey("Quicksoil"));
        DUNGEON_STONE =  register("dungeon_stone", id -> new BlockDungeon(id).setHardness(0.5f).setSounds(BlockBase.PISTON_SOUNDS).setTranslationKey("DungeonStone"));
        LIGHT_DUNGEON_STONE =  register("light_dungeon_stone", id -> new BlockDungeon(id).setHardness(0.5f).setSounds(BlockBase.PISTON_SOUNDS).setLightEmittance(0.75f).setTranslationKey("LightDungeonStone"));
        LOCKED_DUNGEON_STONE =  register("locked_dungeon_stone", id -> new BlockDungeon(id).setHardness(-1.0f).setBlastResistance(1000000.0f).setSounds(BlockBase.PISTON_SOUNDS).setTranslationKey("LockedDungeonStone"));
        LOCKED_LIGHT_DUNGEON_STONE =  register("locked_light_dungeon_stone", id -> new BlockDungeon(id).setHardness(-1.0f).setBlastResistance(1000000.0f).setSounds(BlockBase.PISTON_SOUNDS).setLightEmittance(0.5f).setTranslationKey("LightLockedDungeonStone"));
        TRAPPED_DUNGEON_STONE =  register("trap", id -> new BlockTrap(id).setHardness(-1.0f).setBlastResistance(1000000.0f).setSounds(BlockBase.PISTON_SOUNDS).setTranslationKey("Trap"));
        TREASURE_CHEST =  register("treasure_chest", id -> new BlockTreasureChest(id).setHardness(-1.0f).setSounds(BlockBase.PISTON_SOUNDS).setTranslationKey("TreasureChest"));
        CHEST_MIMIC = register("mimic_chest", id -> new BlockChestMimic(id).setHardness(2.0f).setSounds(BlockBase.WOOD_SOUNDS).setTranslationKey("Mimic"));
        PILLAR = register("pillar", id -> new BlockPillar(id).setHardness(0.5f).setSounds(BlockBase.PISTON_SOUNDS).setTranslationKey("Pillar"));
    }

    private static <T extends BlockBase & BlockTemplate<T>> T register(String id, Function<Identifier, T> initializer) {
        return initializer.apply(of(id)).setTranslationKey(MODID, id);
    }
    public static boolean isGood(final int id, final int meta) { //AETHER 1.02
        return id == 0 || id == AetherBlocks.AERCLOUD.id;
    }
    public static BlockBase
            AETHER_PORTAL,
            AETHER_DIRT,
            AETHER_GRASS_BLOCK,
            QUICKSOIL,
            HOLYSTONE,
            ICESTONE,
            AERCLOUD,
            AEROGEL,
            LOG,
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
