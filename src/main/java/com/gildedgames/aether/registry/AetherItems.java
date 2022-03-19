package com.gildedgames.aether.registry;

import net.mine_diver.unsafeevents.listener.EventListener;
import net.minecraft.block.BlockBase;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import com.gildedgames.aether.mixin.EntityBaseAccessor;
import com.gildedgames.aether.mixin.LivingAccessor;

import net.minecraft.entity.player.PlayerBase;
import net.minecraft.item.ItemBase;
import net.minecraft.item.ItemInstance;
import net.minecraft.item.tool.ToolMaterial;
import net.minecraft.util.maths.MathHelper;
import net.modificationstation.stationapi.api.event.registry.ItemRegistryEvent;
import net.modificationstation.stationapi.api.mod.entrypoint.Entrypoint;
import net.modificationstation.stationapi.api.mod.entrypoint.EventBusPolicy;
import net.modificationstation.stationapi.api.registry.Identifier;
import net.modificationstation.stationapi.api.registry.ModID;

import com.gildedgames.aether.Aether;
import com.gildedgames.aether.inventory.InventoryAether;
import com.gildedgames.aether.item.ItemAether;
import com.gildedgames.aether.item.ItemAetherKey;
import com.gildedgames.aether.item.ItemAetherRecord;
import com.gildedgames.aether.item.ItemAmbrosium;
import com.gildedgames.aether.item.ItemColouredArmor;
import com.gildedgames.aether.item.ItemCloudParachute;
import com.gildedgames.aether.item.ItemDart;
import com.gildedgames.aether.item.ItemDartShooter;
import com.gildedgames.aether.item.ItemGravititeAxe;
import com.gildedgames.aether.item.ItemGravititePickaxe;
import com.gildedgames.aether.item.ItemGravititeSpade;
import com.gildedgames.aether.item.ItemHolystoneAxe;
import com.gildedgames.aether.item.ItemHolystonePickaxe;
import com.gildedgames.aether.item.ItemHolystoneSpade;
import com.gildedgames.aether.item.ItemLance;
import com.gildedgames.aether.item.ItemLifeShard;
import com.gildedgames.aether.item.ItemLoreBook;
import com.gildedgames.aether.item.ItemMoaEgg;
import com.gildedgames.aether.item.ItemMoreArmor;
import com.gildedgames.aether.item.ItemSkyrootAxe;
import com.gildedgames.aether.item.ItemSkyrootBucket;
import com.gildedgames.aether.item.ItemSkyrootPickaxe;
import com.gildedgames.aether.item.ItemSkyrootSpade;
import com.gildedgames.aether.item.ItemSkyrootSword;
import com.gildedgames.aether.item.ItemSwordGravitite;
import com.gildedgames.aether.item.ItemSwordHolystone;
import com.gildedgames.aether.item.ItemSwordZanite;
import com.gildedgames.aether.item.ItemValkyrieAxe;
import com.gildedgames.aether.item.ItemValkyriePickaxe;
import com.gildedgames.aether.item.ItemValkyrieSpade;
import com.gildedgames.aether.item.ItemZaniteAxe;
import com.gildedgames.aether.item.ItemZanitePickaxe;
import com.gildedgames.aether.item.ItemZaniteSpade;
@Entrypoint(eventBus = @EventBusPolicy(registerInstance = false))
public class AetherItems {
    @Entrypoint.ModID
    private static ModID MOD_ID;
    @EventListener
    private static void registerItems(ItemRegistryEvent event) {
    	AmbrosiumShard = new ItemAmbrosium(Identifier.of(MOD_ID, "ambrosium_shard"), 1).setTranslationKey("AmbrosiumShard");
    	Stick = new ItemAether(Identifier.of(MOD_ID, "stick")).setTranslationKey("SkyrootStick");
    	IronRing = new ItemMoreArmor(Identifier.of(MOD_ID, "iron_ring"), 0,"/assets/aether/stationapi/textures/armor/Accessories.png", 8, 16777215).setTranslationKey("IronRing");
    	GoldRing = new ItemMoreArmor(Identifier.of(MOD_ID, "gold_ring"), 0, "/assets/aether/stationapi/textures/armor/Accessories.png", 8, 16776994).setTranslationKey("GoldRing");
    	ZaniteRing = new ItemMoreArmor(Identifier.of(MOD_ID, "zanite_ring"), 0, "/assets/aether/stationapi/textures/armor/Accessories.png", 8, 7412456).setTranslationKey("ZaniteRing");
        IceRing = new ItemMoreArmor(Identifier.of(MOD_ID, "ice_ring"), 0, "/assets/aether/stationapi/textures/armor/Accessories.png", 8, 9823975).setTranslationKey("IceRing");
        AetherItems.Key = new ItemAetherKey(Identifier.of(MOD_ID, "key")).setTranslationKey("AetherKey");
        AetherItems.VictoryMedal = new ItemAether(Identifier.of(MOD_ID, "victory_medal")).setMaxStackSize(10).setTranslationKey("VictoryMedal");
        Bucket = new ItemSkyrootBucket(Identifier.of(MOD_ID, "skyroot_bucket")).setTranslationKey("SkyrootBucket");
        AetherItems.IronPendant = new ItemMoreArmor(Identifier.of(MOD_ID, "iron_pendant"), 0, "/assets/aether/stationapi/textures/armor/Accessories.png", 4, 16777215).setTranslationKey("IronPendant");
        AetherItems.GoldPendant = new ItemMoreArmor(Identifier.of(MOD_ID, "gold_endant"), 0, "/assets/aether/stationapi/textures/armor/Accessories.png", 4, 16776994).setTranslationKey("GoldPendant");
        AetherItems.ZanitePendant = new ItemMoreArmor(Identifier.of(MOD_ID, "zanite_pendant"), 0, "/assets/aether/stationapi/textures/armor/Accessories.png", 4, 7412456).setTranslationKey("ZanitePendant");
        AetherItems.IcePendant = new ItemMoreArmor(Identifier.of(MOD_ID, "ice_pendant"), 0, "/assets/aether/stationapi/textures/armor/Accessories.png", 4, 9823975).setTranslationKey("IcePendant");
        AetherItems.LoreBook = new ItemLoreBook(Identifier.of(MOD_ID, "lore_book")).setTexturePosition(59).setTranslationKey("LoreBook");
        AetherItems.MoaEgg = new ItemMoaEgg(Identifier.of(MOD_ID, "moa_egg")).setTranslationKey("MoaEgg");
        AetherItems.AechorPetal = new ItemAether(Identifier.of(MOD_ID, "aechor_petal")).setTranslationKey("AechorPetal");
        AetherItems.GoldenAmber = new ItemAether(Identifier.of(MOD_ID, "golden_amber")).setTranslationKey("GoldenAmber");
        AetherItems.Dart = new ItemDart(Identifier.of(MOD_ID, "item_dart")).setHasSubItems(true).setTranslationKey("Dart");
        AetherItems.DartShooter = new ItemDartShooter(Identifier.of(MOD_ID, "item_dart_shooter")).setTranslationKey("DartShooter");
        AetherItems.HealingStone = new ItemAmbrosium(Identifier.of(MOD_ID, "item_healing_stone"), 4).setTranslationKey("Healing_Stone");
        AetherItems.Zanite = new ItemAether(Identifier.of(MOD_ID, "item_zanite")).setTranslationKey("Zanite");
        AetherItems.BlueMusicDisk = new ItemAetherRecord(Identifier.of(MOD_ID, "item_blue_music_disk"), "AetherTune").setTranslationKey("BlueMusicDisk");
        ToolMaterial mat = ToolMaterial.WOOD;
        AetherItems.PickSkyroot = new ItemSkyrootPickaxe(Identifier.of(MOD_ID, "item_skyroot_pickaxe"), mat).setTranslationKey("PickSkyroot");
        AetherItems.ShovelSkyroot = new ItemSkyrootSpade(Identifier.of(MOD_ID, "item_skyroot_shovel"), mat).setTranslationKey("ShovelSkyroot");
        AetherItems.AxeSkyroot = new ItemSkyrootAxe(Identifier.of(MOD_ID, "item_skyroot_axe"), mat).setTranslationKey("AxeSkyroot");
        AetherItems.SwordSkyroot = new ItemSkyrootSword(Identifier.of(MOD_ID, "item_skyroot_sword"), mat).setTranslationKey("SwordSkyroot");
        mat = ToolMaterial.STONE;
        AetherItems.PickHolystone = new ItemHolystonePickaxe(Identifier.of(MOD_ID, "item_holystone_pickaxe"), mat).setTranslationKey("PickHolystone");
        AetherItems.ShovelHolystone = new ItemHolystoneSpade(Identifier.of(MOD_ID, "item_holystone_shovel"), mat).setTranslationKey("ShovelHolystone");
        AetherItems.AxeHolystone = new ItemHolystoneAxe(Identifier.of(MOD_ID, "item_holystone_axe"), mat).setTranslationKey("AxeHolystone");
        AetherItems.SwordHolystone = new ItemSwordHolystone(Identifier.of(MOD_ID, "item_holystone_sword"), mat).setTranslationKey("SwordHolystone");
        mat = ToolMaterial.IRON;
        AetherItems.PickZanite = new ItemZanitePickaxe(Identifier.of(MOD_ID, "item_zanite_pickaxe"), mat).setTranslationKey("PickZanite");
        AetherItems.ShovelZanite = new ItemZaniteSpade(Identifier.of(MOD_ID, "item_zanite_shovel"), mat).setTranslationKey("ShovelZanite");
        AetherItems.AxeZanite = new ItemZaniteAxe(Identifier.of(MOD_ID, "item_zanite_axe"), mat).setTranslationKey("AxeZanite");
        AetherItems.SwordZanite = new ItemSwordZanite(Identifier.of(MOD_ID, "item_zanite_sword"), mat).setTranslationKey("SwordZanite");
        AetherItems.LeatherGlove = new ItemMoreArmor(Identifier.of(MOD_ID, "item_leather_glove"), 0, "/assets/aether/stationapi/textures/armor/Accessories.png", 10, 12999733).setTranslationKey("LeatherGlove");
        AetherItems.IronGlove = new ItemMoreArmor(Identifier.of(MOD_ID, "item_iron_glove"), 2, "/assets/aether/stationapi/textures/armor/Accessories.png", 10, 14540253).setTranslationKey("IronGlove");
        AetherItems.GoldGlove = new ItemMoreArmor(Identifier.of(MOD_ID, "item_gold_glove"), 1, "/assets/aether/stationapi/textures/armor/Accessories.png", 10, 15396439).setTranslationKey("GoldGlove");
        AetherItems.DiamondGlove = new ItemMoreArmor(Identifier.of(MOD_ID, "item_diamond_glove"), 3, "/assets/aether/stationapi/textures/armor/Accessories.png", 10, 3402699).setTranslationKey("DiamondGlove");
        AetherItems.ZaniteGlove = new ItemMoreArmor(Identifier.of(MOD_ID, "item_zanite_glove"), 2, "/assets/aether/stationapi/textures/armor/Accessories.png", 10, 7412456).setTranslationKey("ZaniteGlove");
        AetherItems.GravititeGlove = new ItemMoreArmor(Identifier.of(MOD_ID, "item_gravitite_glove"), 3, "/assets/aether/stationapi/textures/armor/Accessories.png", 10, 15160027).setTranslationKey("GravititeGlove");
        AetherItems.PhoenixGlove = new ItemMoreArmor(Identifier.of(MOD_ID, "item_phoenix_glove"), 3, "/assets/aether/stationapi/textures/armor/Phoenix.png", 10, 16742144, false).setTranslationKey("PhoenixGlove");
        AetherItems.ObsidianGlove = new ItemMoreArmor(Identifier.of(MOD_ID, "item_obsidian_glove"), 4, "/assets/aether/stationapi/textures/armor/Accessories.png", 10, 1774663).setTranslationKey("ObsidianGlove");
        AetherItems.NeptuneGlove = new ItemMoreArmor(Identifier.of(MOD_ID, "item_neptune_glove"), 3, "/assets/aether/stationapi/textures/armor/Accessories.png", 10, 2512127).setTranslationKey("NeptuneGlove");
        mat = ToolMaterial.EMERALD;
        AetherItems.PickGravitite = new ItemGravititePickaxe(Identifier.of(MOD_ID, "item_gravitite_pickaxe"), mat).setTranslationKey("PickGravitite");
        AetherItems.ShovelGravitite = new ItemGravititeSpade(Identifier.of(MOD_ID, "item_gravitite_shovel"), mat).setTranslationKey("ShovelGravitite");
        AetherItems.AxeGravitite = new ItemGravititeAxe(Identifier.of(MOD_ID, "item_gravitite_axe"), mat).setTranslationKey("AxeGravitite");
        AetherItems.SwordGravitite = new ItemSwordGravitite(Identifier.of(MOD_ID, "item_gravitite_sword"), mat).setTranslationKey("SwordGravitite");
        AetherItems.PickValkyrie = new ItemValkyriePickaxe(Identifier.of(MOD_ID, "item_valkyre_pickaxe"), mat).setTranslationKey("PickValkyrie");
        AetherItems.ShovelValkyrie = new ItemValkyrieSpade(Identifier.of(MOD_ID, "item_valkyre_shovel"), mat).setTranslationKey("ShovelValkyrie");
        AetherItems.AxeValkyrie = new ItemValkyrieAxe(Identifier.of(MOD_ID, "item_valkyre_axe"), mat).setTranslationKey("AxeValkyrie");
        AetherItems.IronBubble = new ItemMoreArmor(Identifier.of(MOD_ID, "item_iron_bubble"), 0, 0, 7).setTranslationKey("IronBubble");
        AetherItems.AetherCape = new ItemMoreArmor(Identifier.of(MOD_ID, "item_aether_cape"), 0, "/assets/aether/stationapi/textures/capes/AetherCape.png", 5).setTranslationKey("AetherCape");
        AetherItems.RegenerationStone = new ItemMoreArmor(Identifier.of(MOD_ID, "item_regeneraation_stone"), 0, 0, 7).setTranslationKey("RegenerationStone");
        AetherItems.InvisibilityCloak = new ItemMoreArmor(Identifier.of(MOD_ID, "item_invisibility_cloack"), 0, 0, 5).setTranslationKey("InvisibilityCloak");
        AetherItems.AgilityCape = new ItemMoreArmor(Identifier.of(MOD_ID, "item_agility_cape"), 0, "/assets/aether/stationapi/textures/capes/AgilityCape.png", 5).setTranslationKey("AgilityCape");
        AetherItems.WhiteCape = new ItemMoreArmor(Identifier.of(MOD_ID, "item_white_cape"), 0, "/assets/aether/stationapi/textures/capes/WhiteCape.png", 5).setTranslationKey("WhiteCape");
        AetherItems.RedCape = new ItemMoreArmor(Identifier.of(MOD_ID, "item_red_cape"), 0, "/assets/aether/stationapi/textures/capes/RedCape.png", 5, 15208721).setTranslationKey("RedCape");
        AetherItems.YellowCape = new ItemMoreArmor(Identifier.of(MOD_ID, "item_yellow_cape"), 0, "/assets/aether/stationapi/textures/capes/YellowCape.png", 5, 13486862).setTranslationKey("YellowCape");
        AetherItems.BlueCape = new ItemMoreArmor(Identifier.of(MOD_ID, "item_blue_cape"), 0, "/assets/aether/stationapi/textures/capes/BlueCape.png", 5, 1277879).setTranslationKey("BlueCape");
        AetherItems.CloudParachute = new ItemCloudParachute(Identifier.of(MOD_ID, "item_cloud_parachute"),false).setTranslationKey("CloudParachute");
        AetherItems.CloudParachuteGold = new ItemCloudParachute(Identifier.of(MOD_ID, "item_gold_cloud_parachute"),true).setTranslationKey("CloudParachuteGold");
        AetherItems.PhoenixHelm = new ItemColouredArmor(Identifier.of(MOD_ID, "item_phoenix_helmet"), 3, "Phoenix_1", 0, 16742144).setTexturePosition(1).setTranslationKey("PhoenixHelm");
        AetherItems.PhoenixBody = new ItemColouredArmor(Identifier.of(MOD_ID, "item_phoenix_body"), 3, "Phoenix_1", 1, 16742144).setTexturePosition(17).setTranslationKey("PhoenixBody");
        AetherItems.PhoenixLegs = new ItemColouredArmor(Identifier.of(MOD_ID, "item_phoenix_legs"), 3, "Phoenix_2", 2, 16742144).setTexturePosition(33).setTranslationKey("PhoenixLegs");
        AetherItems.PhoenixBoots = new ItemColouredArmor(Identifier.of(MOD_ID, "item_phoenix_boots"), 3, "Phoenix_1", 3, 16742144).setTexturePosition(49).setTranslationKey("PhoenixBoots");
        AetherItems.ObsidianHelm = new ItemColouredArmor(Identifier.of(MOD_ID, "item_obsidian_helmet"), 4, "Obsidian_1", 0, 1774663).setTexturePosition(2).setTranslationKey("ObsidianHelm");
        AetherItems.ObsidianBody = new ItemColouredArmor(Identifier.of(MOD_ID, "item_obsidian_body"), 4, "Obsidian_1", 1, 1774663).setTexturePosition(18).setTranslationKey("ObsidianBody");
        AetherItems.ObsidianLegs = new ItemColouredArmor(Identifier.of(MOD_ID, "item_obsidian_legs"), 4, "Obsidian_2", 2, 1774663).setTexturePosition(34).setTranslationKey("ObsidianLegs");
        AetherItems.ObsidianBoots = new ItemColouredArmor(Identifier.of(MOD_ID, "item_obsidian_boots"), 4, "Obsidian_1", 3, 1774663).setTexturePosition(50).setTranslationKey("ObsidianBoots");
        AetherItems.GravititeHelmet = new ItemColouredArmor(Identifier.of(MOD_ID, "item_gravitite_helmet"), 3, "Gravitite_1", 0, 15160027).setTexturePosition(2).setTranslationKey("GravHelm");
        AetherItems.GravititeBodyplate = new ItemColouredArmor(Identifier.of(MOD_ID, "item_gravitite_body"), 3, "Gravitite_1", 1, 15160027).setTexturePosition(18).setTranslationKey("GravBody");
        AetherItems.GravititePlatelegs = new ItemColouredArmor(Identifier.of(MOD_ID, "item_gravitite_legs"), 3, "Gravitite_2", 2, 15160027).setTexturePosition(34).setTranslationKey("GravLegs");
        AetherItems.GravititeBoots = new ItemColouredArmor(Identifier.of(MOD_ID, "item_gravitite_boots"), 3, "Gravitite_1", 3, 15160027).setTexturePosition(50).setTranslationKey("GravBoots");
        AetherItems.ZaniteHelmet = new ItemColouredArmor(Identifier.of(MOD_ID, "item_zanite_helmet"), 2, "Zanite_1", 0, 7412456).setTexturePosition(2).setTranslationKey("ZaniteHelm");
        AetherItems.ZaniteChestplate = new ItemColouredArmor(Identifier.of(MOD_ID, "item_zanite_body"), 2, "Zanite_1", 1, 7412456).setTexturePosition(18).setTranslationKey("ZaniteBody");
        AetherItems.ZaniteLeggings = new ItemColouredArmor(Identifier.of(MOD_ID, "item_zanite_legs"), 2, "Zanite_2", 2, 7412456).setTexturePosition(34).setTranslationKey("ZaniteLegs");
        AetherItems.ZaniteBoots = new ItemColouredArmor(Identifier.of(MOD_ID, "item_zanite_boots"), 2,"Zanite_1", 3, 7412456).setTexturePosition(50).setTranslationKey("ZaniteBoots");
        AetherItems.NeptuneHelmet = new ItemColouredArmor(Identifier.of(MOD_ID, "item_neptune_helmet"), 3, "Neptune_1", 0, 2512127).setTexturePosition(1).setTranslationKey("NeptuneHelm");
        AetherItems.NeptuneChestplate = new ItemColouredArmor(Identifier.of(MOD_ID, "item_neptune_body"), 3, "Neptune_1", 1, 2512127).setTexturePosition(17).setTranslationKey("NeptuneBody");
        AetherItems.NeptuneLeggings = new ItemColouredArmor(Identifier.of(MOD_ID, "item_neptune_legs"), 3, "Neptune_2", 2, 2512127).setTexturePosition(33).setTranslationKey("NeptuneLegs");
        AetherItems.NeptuneBoots = new ItemColouredArmor(Identifier.of(MOD_ID, "item_neptune_boots"), 3, "Neptune_1", 3, 2512127).setTexturePosition(49).setTranslationKey("NeptuneBoots");
        /*AetherItems.PigSlayer = new ItemPigSlayer(mod_Aether.idItemPigSlayer).setTexturePosition(this.override("PigSlayer.png")).setTranslationKey("PigSlayer");*/
        AetherItems.LifeShard = new ItemLifeShard(Identifier.of(MOD_ID, "item_life_shard")).setTranslationKey("LifeShard");
        AetherItems.GoldenFeather = new ItemMoreArmor(Identifier.of(MOD_ID, "item_golden_feather"), 0, 0, 7).setTranslationKey("GoldenFeather");
        AetherItems.Lance = new ItemLance(Identifier.of(MOD_ID, "item_lance"), mat).setTranslationKey("Lance");
        AetherItems.RepShield = new ItemMoreArmor(Identifier.of(MOD_ID, "item_rep_shield"), 0, 0, 6, 16777215).setTranslationKey("RepShield").setDurability(512);
        
    }
    
    public static void tick(final Minecraft game) {
        if (true) {
            final PlayerBase player = game.player;
            final InventoryAether inv = Aether.getPlayerHandler(player).inv;
            if(inv == null || inv.slots == null) {
            	return;
            }
        	if ((inv.slots[0] != null && inv.slots[0].itemId == AetherItems.IcePendant.id) || (inv.slots[4] != null && inv.slots[4].itemId == AetherItems.IceRing.id) || (inv.slots[5] != null && inv.slots[5].itemId == AetherItems.IceRing.id)) {
                final int i = MathHelper.floor(player.x);
                final int j = MathHelper.floor(player.boundingBox.minY);
                final int k = MathHelper.floor(player.z);
                final double yoff = player.y - j;
                final Material mat0 = game.level.getMaterial(i, j, k);
                final Material mat2 = game.level.getMaterial(i, j - 1, k);
                for (int l = i - 1; l <= i + 1; ++l) {
                    for (int i2 = j - 1; i2 <= j + 1; ++i2) {
                        for (int j2 = k - 1; j2 <= k + 1; ++j2) {
                            if (game.level.getTileId(l, i2, j2) == 8) {
                                game.level.setTile(l, i2, j2, 79);
                            }
                            else if (game.level.getTileId(l, i2, j2) == 9) {
                                game.level.setTile(l, i2, j2, 79);
                            }
                            else if (game.level.getTileId(l, i2, j2) == 10) {
                                game.level.setTile(l, i2, j2, 49);
                            }
                            else if (game.level.getTileId(l, i2, j2) == 11) {
                                game.level.setTile(l, i2, j2, 49);
                            }
                        }
                    }
                }
            }
            if (player.inventory.armour[3] != null && player.inventory.armour[3].itemId == AetherItems.PhoenixHelm.id && player.inventory.armour[2] != null && player.inventory.armour[2].itemId == AetherItems.PhoenixBody.id && player.inventory.armour[1] != null && player.inventory.armour[1].itemId == AetherItems.PhoenixLegs.id && player.inventory.armour[0] != null && player.inventory.armour[0].itemId == AetherItems.PhoenixBoots.id && inv.slots[6] != null && inv.slots[6].itemId == AetherItems.PhoenixGlove.id) {
                ((EntityBaseAccessor)player).setImmunityToFire(true);
                player.fire = 0;
                //if (!MainMenu.mmactive) {
                game.level.addParticle("flame", player.x + ((EntityBaseAccessor)player).getRand().nextGaussian() / 5.0, player.y - 0.5 + ((EntityBaseAccessor)player).getRand().nextGaussian() / 5.0, player.z + ((EntityBaseAccessor)player).getRand().nextGaussian() / 3.0, 0.0, 0.0, 0.0);
                //}
            }
            else {
            	((EntityBaseAccessor)player).setImmunityToFire(false);
            }
            if (player.isTouchingWater()) {
                final int playerBlock = game.level.getTileId(MathHelper.floor(player.x), MathHelper.floor(player.y), MathHelper.floor(player.z));
                if (player.inventory.armour[0] != null && player.inventory.armour[0].itemId == AetherItems.PhoenixBoots.id) {
                    player.inventory.armour[0].applyDamage(1, player);
                    if (playerBlock == BlockBase.STILL_WATER.id) {
                        player.inventory.armour[0].applyDamage(4, player);
                        game.level.setTile(MathHelper.floor(player.x), MathHelper.floor(player.y), MathHelper.floor(player.z), 0);
                    }
                    if (player.inventory.armour[0] == null || player.inventory.armour[0].count < 1) {
                        player.inventory.armour[0] = new ItemInstance(AetherItems.ObsidianBoots, 1, 0);
                    }
                }
                if (player.inventory.armour[1] != null && player.inventory.armour[1].itemId == AetherItems.PhoenixLegs.id) {
                    player.inventory.armour[1].applyDamage(1, player);
                    if (playerBlock == BlockBase.STILL_WATER.id) {
                        player.inventory.armour[1].applyDamage(4, player);
                        game.level.setTile(MathHelper.floor(player.x), MathHelper.floor(player.y), MathHelper.floor(player.z), 0);
                    }
                    if (player.inventory.armour[1] == null || player.inventory.armour[1].count < 1) {
                        player.inventory.armour[1] = new ItemInstance(AetherItems.ObsidianLegs, 1, 0);
                    }
                }
                if (player.inventory.armour[2] != null && player.inventory.armour[2].itemId == AetherItems.PhoenixBody.id) {
                    player.inventory.armour[2].applyDamage(1, player);
                    if (playerBlock == BlockBase.STILL_WATER.id) {
                        player.inventory.armour[2].applyDamage(4, player);
                        game.level.setTile(MathHelper.floor(player.x), MathHelper.floor(player.y), MathHelper.floor(player.z), 0);
                    }
                    if (player.inventory.armour[2] == null || player.inventory.armour[2].count < 1) {
                        player.inventory.armour[2] = new ItemInstance(AetherItems.ObsidianBody, 1, 0);
                    }
                }
                if (player.inventory.armour[3] != null && player.inventory.armour[3].itemId == AetherItems.PhoenixHelm.id) {
                    player.inventory.armour[3].applyDamage(1, player);
                    if (playerBlock == BlockBase.STILL_WATER.id) {
                        player.inventory.armour[3].applyDamage(4, player);
                        game.level.setTile(MathHelper.floor(player.x), MathHelper.floor(player.y), MathHelper.floor(player.z), 0);
                    }
                    if (player.inventory.armour[3] == null || player.inventory.armour[3].count < 1) {
                        player.inventory.armour[3] = new ItemInstance(AetherItems.ObsidianHelm, 1, 0);
                    }
                }
                if (inv.slots[6] != null && inv.slots[6].itemId == AetherItems.PhoenixGlove.id) {
                    inv.slots[6].applyDamage(1, player);
                    if (playerBlock == BlockBase.STILL_WATER.id) {
                        inv.slots[6].applyDamage(4, player);
                        game.level.setTile(MathHelper.floor(player.x), MathHelper.floor(player.y), MathHelper.floor(player.z), 0);
                    }
                    if (inv.slots[6] == null || inv.slots[6].count < 1) {
                        inv.slots[6] = new ItemInstance(AetherItems.ObsidianGlove, 1, 0);
                    }
                }
            }
            if (player.inventory.armour[3] != null && player.inventory.armour[3].itemId == AetherItems.GravititeHelmet.id && player.inventory.armour[2] != null && player.inventory.armour[2].itemId == AetherItems.GravititeBodyplate.id && player.inventory.armour[1] != null && player.inventory.armour[1].itemId == AetherItems.GravititePlatelegs.id && player.inventory.armour[0] != null && player.inventory.armour[0].itemId == AetherItems.GravititeBoots.id && inv.slots[6] != null && inv.slots[6].itemId == AetherItems.GravititeGlove.id) {
                if (((LivingAccessor)player).getJumping() && !AetherItems.jumpBoosted) {
                    player.velocityY = 1.0;
                    AetherItems.jumpBoosted = true; //TODO: check/make it work
                }
                ((EntityBaseAccessor)player).setFallDistance(-1.0f);
            }
            if (!((LivingAccessor)player).getJumping() && player.onGround) {
                AetherItems.jumpBoosted = false;
            }
            if ((inv.slots[3] != null && inv.slots[3].itemId == AetherItems.IronBubble.id) || (inv.slots[7] != null && inv.slots[7].itemId == AetherItems.IronBubble.id)) {
                player.air = 20;
            }
            if ((inv.slots[0] != null && inv.slots[0].itemId == AetherItems.IcePendant.id) || (inv.slots[4] != null && inv.slots[4].itemId == AetherItems.IceRing.id) || (inv.slots[5] != null && inv.slots[5].itemId == AetherItems.IceRing.id)) {
                final int i = MathHelper.floor(player.x);
                final int j = MathHelper.floor(player.boundingBox.minY);
                final int k = MathHelper.floor(player.z);
                final double yoff = player.y - j;
                final Material mat0 = game.level.getMaterial(i, j, k);
                final Material mat2 = game.level.getMaterial(i, j - 1, k);
                for (int l = i - 1; l <= i + 1; ++l) {
                    for (int i2 = j - 1; i2 <= j + 1; ++i2) {
                        for (int j2 = k - 1; j2 <= k + 1; ++j2) {
                            if (game.level.getTileId(l, i2, j2) == 8) {
                                game.level.setTile(l, i2, j2, 79);
                            }
                            else if (game.level.getTileId(l, i2, j2) == 9) {
                                game.level.setTile(l, i2, j2, 79);
                            }
                            else if (game.level.getTileId(l, i2, j2) == 10) {
                                game.level.setTile(l, i2, j2, 49);
                            }
                            else if (game.level.getTileId(l, i2, j2) == 11) {
                                game.level.setTile(l, i2, j2, 49);
                            }
                        }
                    }
                }
            }
            if ((inv.slots[3] != null && inv.slots[3].itemId == AetherItems.GoldenFeather.id) || (inv.slots[7] != null && inv.slots[7].itemId == AetherItems.GoldenFeather.id)) {
                if (!player.onGround && player.velocityY < 0.0 && !((EntityBaseAccessor)player).get1612()) {
                    final PlayerBase playerBase = player;
                    playerBase.velocityY *= 0.6;
                }
                ((EntityBaseAccessor)player).setFallDistance(-1.0f);
            }
            if (inv.slots[1] != null && inv.slots[1].itemId == AetherItems.AgilityCape.id) {
                player.field_1641 = 1.0f;
            }
            else {
                player.field_1641 = 0.5f;
            }
            if (AetherItems.ticks % 200 == 0 && player.health < Aether.getPlayerHandler(player).maxHealth && ((inv.slots[3] != null && inv.slots[3].itemId == AetherItems.RegenerationStone.id) || (inv.slots[7] != null && inv.slots[7].itemId == AetherItems.RegenerationStone.id))) {
                player.addHealth(1);
            }
            ++AetherItems.ticks;
        }
    }
    
    
    public static double motionOffset;
    public static double ybuff;
    public static ItemBase VictoryMedal;
    public static ItemBase Key;
    public static ItemBase LoreBook;
    public static ItemBase MoaEgg;
    public static ItemBase AechorPetal;
    public static ItemBase GoldenAmber;
    public static ItemBase Stick;
    public static ItemBase Dart;
    public static ItemBase DartShooter;
    public static ItemBase AmbrosiumShard;
    public static ItemBase Zanite;
    public static ItemBase BlueMusicDisk;
    public static ItemBase Bucket;
    public static ItemBase PickSkyroot;
    public static ItemBase PickHolystone;
    public static ItemBase PickZanite;
    public static ItemBase PickGravitite;
    public static ItemBase ShovelSkyroot;
    public static ItemBase ShovelHolystone;
    public static ItemBase ShovelZanite;
    public static ItemBase ShovelGravitite;
    public static ItemBase AxeSkyroot;
    public static ItemBase AxeHolystone;
    public static ItemBase AxeZanite;
    public static ItemBase AxeGravitite;
    public static ItemBase SwordSkyroot;
    public static ItemBase SwordHolystone;
    public static ItemBase SwordZanite;
    public static ItemBase SwordGravitite;
    public static ItemBase IronBubble;
    public static ItemBase PigSlayer;
    public static ItemBase VampireBlade;
    public static ItemBase NatureStaff;
    public static ItemBase SwordFire;
    public static ItemBase SwordLightning;
    public static ItemBase SwordHoly;
    public static ItemBase LightningKnife;
    public static ItemBase GummieSwet;
    public static ItemBase HammerNotch;
    public static ItemBase PhoenixBow;
    public static ItemBase PhoenixHelm;
    public static ItemBase PhoenixBody;
    public static ItemBase PhoenixLegs;
    public static ItemBase PhoenixBoots;
    public static ItemBase ObsidianHelm;
    public static ItemBase ObsidianBody;
    public static ItemBase ObsidianLegs;
    public static ItemBase ObsidianBoots;
    public static ItemBase CloudStaff;
    public static ItemBase CloudParachute;
    public static ItemBase CloudParachuteGold;
    public static ItemBase GravititeHelmet;
    public static ItemBase GravititeBodyplate;
    public static ItemBase GravititePlatelegs;
    public static ItemBase GravititeBoots;
    public static ItemBase ZaniteHelmet;
    public static ItemBase ZaniteChestplate;
    public static ItemBase ZaniteLeggings;
    public static ItemBase ZaniteBoots;
    public static ItemBase LifeShard;
    public static ItemBase GoldenFeather;
    public static ItemBase Lance;
    public static ItemBase RepShield;
    public static ItemBase AetherCape;
    public static ItemBase IronRing;
    public static ItemBase GoldRing;
    public static ItemBase ZaniteRing;
    public static ItemBase IronPendant;
    public static ItemBase GoldPendant;
    public static ItemBase ZanitePendant;
    public static ItemBase LeatherGlove;
    public static ItemBase IronGlove;
    public static ItemBase GoldGlove;
    public static ItemBase DiamondGlove;
    public static ItemBase ZaniteGlove;
    public static ItemBase GravititeGlove;
    public static ItemBase PhoenixGlove;
    public static ItemBase ObsidianGlove;
    public static ItemBase NeptuneGlove;
    public static ItemBase NeptuneHelmet;
    public static ItemBase NeptuneChestplate;
    public static ItemBase NeptuneLeggings;
    public static ItemBase NeptuneBoots;
    public static ItemBase RegenerationStone;
    public static ItemBase InvisibilityCloak;
    public static ItemBase AxeValkyrie;
    public static ItemBase PickValkyrie;
    public static ItemBase ShovelValkyrie;
    public static ItemBase HealingStone;
    public static ItemBase AgilityCape;
    public static ItemBase WhiteCape;
    public static ItemBase RedCape;
    public static ItemBase YellowCape;
    public static ItemBase BlueCape;
    public static ItemBase IceRing;
    public static ItemBase IcePendant;
    private static int ticks;
    private static boolean jumpBoosted;
    public static int ElementalSwordIcon;
    public static int gravArmour;
    public static int zaniteArmour;
    public static int neptuneArmour;
    private static boolean debug;
    
}
