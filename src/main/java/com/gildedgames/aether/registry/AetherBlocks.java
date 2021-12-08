package com.gildedgames.aether.registry;

import com.gildedgames.aether.block.AetherDirt;
import com.gildedgames.aether.block.AetherGrassBlock;
import com.gildedgames.aether.block.AetherPortal;
import com.gildedgames.aether.block.Holystone;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.minecraft.block.BlockBase;
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
    }

    private static <T extends BlockBase & BlockTemplate<T>> T register(String id, Function<Identifier, T> initializer) {
        return initializer.apply(of(id)).setTranslationKey(MODID, id);
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
