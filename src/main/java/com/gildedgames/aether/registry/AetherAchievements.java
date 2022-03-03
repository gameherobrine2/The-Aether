package com.gildedgames.aether.registry;
import net.minecraft.item.ItemBase;
import net.minecraft.item.ItemInstance;
import net.modificationstation.stationapi.api.client.gui.screen.menu.AchievementPage;
import net.modificationstation.stationapi.api.event.achievement.AchievementRegisterEvent;
import net.minecraft.block.BlockBase;
import net.modificationstation.stationapi.mixin.achievement.client.MixinAchievements;
import com.gildedgames.aether.achievement.AetherACPage;
import com.gildedgames.aether.registry.AetherBlocks;
import com.gildedgames.aether.registry.AetherItems;

import net.mine_diver.unsafeevents.listener.EventListener;
import net.minecraft.achievement.Achievement;

public class AetherAchievements {
    public static final int acOff = 800;
    public static Achievement enterAether;
    public static Achievement defeatBronze;
    public static Achievement defeatSilver;
    public static Achievement defeatGold;
    public static Achievement enchanter;
    public static Achievement incubator;
    public static Achievement gravTools;
    public static Achievement blueCloud;
    public static Achievement flyingPig;
    public static Achievement lore;
    public static Achievement loreception;
    @EventListener
    private void registerAchievements(AchievementRegisterEvent event) {
    	AetherAchievements.enterAether = new Achievement(800, "enterAether", 0, 0, BlockBase.GLOWSTONE, (Achievement)null).register();
        AetherAchievements.defeatBronze = new Achievement(801, "defeatBronze", -2, 3, new ItemInstance(AetherItems.Key, 1, 0), AetherAchievements.enterAether).register();
        AetherAchievements.defeatSilver = new Achievement(802, "defeatSilver", 0, 4, new ItemInstance(AetherItems.Key, 1, 1), AetherAchievements.enterAether).register();
        AetherAchievements.defeatGold = new Achievement(803, "defeatGold", 2, 3, new ItemInstance(AetherItems.Key, 1, 2), AetherAchievements.enterAether).register();
        AetherAchievements.enchanter = new Achievement(804, "enchanter", 2, 1, AetherBlocks.ENCHANTER, AetherAchievements.enterAether).register();
        AetherAchievements.incubator = new Achievement(805, "incubator", 2, -1, AetherBlocks.INCUBATOR, AetherAchievements.enterAether).register();
        AetherAchievements.blueCloud = new Achievement(806, "blueCloud", -2, -1, new ItemInstance(AetherBlocks.AERCLOUD, 1, 1), AetherAchievements.enterAether).register();
        AetherAchievements.flyingPig = new Achievement(807, "flyingPig", -2, 1, ItemBase.saddle, AetherAchievements.enterAether).register();
        AetherAchievements.gravTools = new Achievement(808, "gravTools", -1, -3, /*TODO AetherItems.PickGravitite*/ AetherItems.IronRing, AetherAchievements.enterAether).register();
        AetherAchievements.lore = new Achievement(809, "lore", 1, -3, ItemBase.book, AetherAchievements.enterAether).register();
        AetherAchievements.loreception = new Achievement(810, "loreception", 1, -5, ItemBase.book, AetherAchievements.lore).register();
        event.achievements.add(AetherAchievements.enterAether);/*, "Hostile Paradise", "Ascend to the Aether");*/
        event.achievements.add(AetherAchievements.defeatBronze);/*, "Like a Bossaru!", "Defeat the bronze boss");*/
        event.achievements.add(AetherAchievements.defeatSilver);/*, "Dethroned", "Defeat the silver boss");*/
        event.achievements.add(AetherAchievements.defeatGold);/*, "Extinguished", "Defeat the gold boss");*/
        event.achievements.add(AetherAchievements.enchanter);/*, "Do you believe in magic?", "Craft an enchanter");*/
        event.achievements.add(AetherAchievements.incubator);/*, "Now you're family", "Incubate a moa");*/
        event.achievements.add(AetherAchievements.blueCloud);/*, "To infinity and beyond!", "Bounce on a blue cloud");*/
        event.achievements.add(AetherAchievements.flyingPig);/*, "When phygs fly!", "Fly on a phyg");*/
        event.achievements.add(AetherAchievements.gravTools);/*, "Pink is the new blue", "Craft a gravitite tool");*/
        event.achievements.add(AetherAchievements.lore);/*, "The more you know!", "Read a book of lore");*/
        event.achievements.add(AetherAchievements.loreception);/*, "Lore-ception", "It's a book in a book in a book in...");*/
        final AchievementPage page = new AetherACPage();
        page.addAchievements(new Achievement[] { AetherAchievements.enterAether, AetherAchievements.defeatBronze, AetherAchievements.defeatSilver, AetherAchievements.defeatGold, AetherAchievements.enchanter, AetherAchievements.incubator, AetherAchievements.gravTools, AetherAchievements.blueCloud, AetherAchievements.flyingPig, AetherAchievements.lore, AetherAchievements.loreception });
    }
    
}
