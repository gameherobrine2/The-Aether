package com.gildedgames.aether.event.listener;

import com.gildedgames.aether.registry.AetherBlocks;
import com.gildedgames.aether.registry.AetherItems;

import net.mine_diver.unsafeevents.listener.EventListener;
import net.minecraft.block.BlockBase;
import net.minecraft.item.ItemBase;
import net.minecraft.item.ItemInstance;
import net.modificationstation.stationapi.api.event.recipe.RecipeRegisterEvent;
import net.modificationstation.stationapi.api.recipe.CraftingRegistry;

public class RecipeListener {
	 @EventListener
	 public void registerRecipes(RecipeRegisterEvent event) {
		 RecipeRegisterEvent.Vanilla type = RecipeRegisterEvent.Vanilla.fromType(event.recipeId);
		 switch(type) {
		 case CRAFTING_SHAPELESS:
			 registerShapelessRecipes();
		 case CRAFTING_SHAPED:
			 registerShapedRecipes();
		 }
	 }
	 private void registerShapedRecipes() {
		 CraftingRegistry.addShapedRecipe(new ItemInstance(AetherItems.IronRing), " C ", "C C", " C ", 'C', ItemBase.ironIngot);
		 CraftingRegistry.addShapedRecipe(new ItemInstance(AetherItems.GoldRing), " C ", "C C", " C ", 'C', ItemBase.goldIngot);
		 CraftingRegistry.addShapedRecipe(new ItemInstance(AetherItems.Stick,4), "#", "#", '#', AetherBlocks.SKYROOT_PLANKS);
		 CraftingRegistry.addShapedRecipe(new ItemInstance(AetherItems.PickHolystone, 1), new Object[] { "ZZZ", " Y ", " Y ", 'Z', AetherBlocks.HOLYSTONE, 'Y', AetherItems.Stick });
	     CraftingRegistry.addShapedRecipe(new ItemInstance(AetherItems.AxeHolystone, 1), new Object[] { "ZZ", "ZY", " Y", 'Z', AetherBlocks.HOLYSTONE, 'Y', AetherItems.Stick });
	     CraftingRegistry.addShapedRecipe(new ItemInstance(AetherItems.ShovelHolystone, 1), new Object[] { "Z", "Y", "Y", 'Z', AetherBlocks.HOLYSTONE, 'Y', AetherItems.Stick });
	     CraftingRegistry.addShapedRecipe(new ItemInstance(AetherItems.SwordHolystone, 1), new Object[] { "Z", "Z", "Y", 'Z', AetherBlocks.HOLYSTONE, 'Y', AetherItems.Stick });
	     CraftingRegistry.addShapedRecipe(new ItemInstance(AetherItems.PickSkyroot, 1), new Object[] { "ZZZ", " Y ", " Y ", 'Z', AetherBlocks.SKYROOT_PLANKS, 'Y', AetherItems.Stick });
	     CraftingRegistry.addShapedRecipe(new ItemInstance(AetherItems.AxeSkyroot, 1), new Object[] { "ZZ", "ZY", " Y", 'Z', AetherBlocks.SKYROOT_PLANKS, 'Y', AetherItems.Stick });
	     CraftingRegistry.addShapedRecipe(new ItemInstance(AetherItems.ShovelSkyroot, 1), new Object[] { "Z", "Y", "Y", 'Z', AetherBlocks.SKYROOT_PLANKS, 'Y', AetherItems.Stick });
	     CraftingRegistry.addShapedRecipe(new ItemInstance(AetherItems.SwordSkyroot, 1), new Object[] { "Z", "Z", "Y", 'Z', AetherBlocks.SKYROOT_PLANKS, 'Y', AetherItems.Stick });
	     CraftingRegistry.addShapedRecipe(new ItemInstance(AetherItems.Bucket, 1, 0), new Object[] { "Z Z", " Z ", 'Z', AetherBlocks.SKYROOT_PLANKS });
	     CraftingRegistry.addShapedRecipe(new ItemInstance(BlockBase.WORKBENCH, 1), new Object[] { "UU", "UU", 'U', AetherBlocks.SKYROOT_PLANKS });
	     CraftingRegistry.addShapedRecipe(new ItemInstance(AetherBlocks.AMBROSIUM_TORCH, 2), new Object[] { " Z", " Y", 'Z', AetherItems.AmbrosiumShard, 'Y', AetherItems.Stick });
	     CraftingRegistry.addShapedRecipe(new ItemInstance(AetherItems.Dart, 1, 0), new Object[] { "X", "Z", "Y", 'X', AetherItems.GoldenAmber, 'Z', AetherItems.Stick, 'Y', ItemBase.feather });
	     CraftingRegistry.addShapedRecipe(new ItemInstance(AetherItems.Dart, 8, 1), new Object[] { "XXX", "XZX", "XXX", 'X', new ItemInstance(AetherItems.Dart, 1, 0), 'Z', new ItemInstance(AetherItems.Bucket, 1, 2) });
	     CraftingRegistry.addShapedRecipe(new ItemInstance(AetherBlocks.INCUBATOR, 1), new Object[] { "XXX", "XZX", "XXX", 'X', AetherBlocks.HOLYSTONE, 'Z', AetherBlocks.AMBROSIUM_TORCH });
	     CraftingRegistry.addShapedRecipe(new ItemInstance(AetherBlocks.FREEZER, 1), new Object[] { "XXX", "XZX", "YYY", 'X', AetherBlocks.HOLYSTONE, 'Z', AetherBlocks.ICESTONE, 'Y', AetherBlocks.SKYROOT_PLANKS });
	     CraftingRegistry.addShapedRecipe(new ItemInstance(AetherBlocks.ZANITE_BLOCK, 1), new Object[] { "XX", "XX", 'X', AetherItems.Zanite });
	     CraftingRegistry.addShapedRecipe(new ItemInstance(AetherItems.DartShooter, 1), new Object[] { "X", "X", "Y", 'X', AetherBlocks.SKYROOT_PLANKS, 'Y', AetherItems.Zanite });
	     CraftingRegistry.addShapedRecipe(new ItemInstance(ItemBase.saddle, 1), new Object[] { "XXX", "XZX", 'X', ItemBase.leather, 'Z', ItemBase.string });
	     CraftingRegistry.addShapedRecipe(new ItemInstance(BlockBase.CHEST, 1), new Object[] { "PPP", "P P", "PPP", 'P', AetherBlocks.SKYROOT_PLANKS });
	     CraftingRegistry.addShapedRecipe(new ItemInstance(ItemBase.woodDoor), new Object[] { "PP", "PP", "PP", 'P', AetherBlocks.SKYROOT_PLANKS });
	     CraftingRegistry.addShapedRecipe(new ItemInstance(BlockBase.FENCE, 2), new Object[] { "SSS", "SSS", 'S', AetherItems.Stick });
	     CraftingRegistry.addShapedRecipe(new ItemInstance(BlockBase.LADDER, 4), new Object[] { "S S", "SSS", "S S", 'S', AetherItems.Stick });
	     CraftingRegistry.addShapedRecipe(new ItemInstance(BlockBase.JUKEBOX), new Object[] { "PPP", "PGP", "PPP", 'P', AetherBlocks.SKYROOT_PLANKS, 'G', AetherBlocks.ENCHANTED_GRAVITITE });
	     CraftingRegistry.addShapedRecipe(new ItemInstance(AetherBlocks.ENCHANTER), new Object[] { "HHH", "HZH", "HHH", 'H', AetherBlocks.HOLYSTONE, 'Z', AetherItems.Zanite });
	     CraftingRegistry.addShapedRecipe(new ItemInstance(AetherItems.IronPendant), new Object[] { "SSS", "S S", " C ", 'S', ItemBase.string, 'C', ItemBase.ironIngot });
	     CraftingRegistry.addShapedRecipe(new ItemInstance(AetherItems.GoldPendant), new Object[] { "SSS", "S S", " C ", 'S', ItemBase.string, 'C', ItemBase.goldIngot });
	     CraftingRegistry.addShapedRecipe(new ItemInstance(AetherItems.ZanitePendant), new Object[] { "SSS", "S S", " C ", 'S', ItemBase.string, 'C', AetherItems.Zanite });
	     CraftingRegistry.addShapedRecipe(new ItemInstance(AetherItems.ZaniteRing), new Object[] { " C ", "C C", " C ", 'C', AetherItems.Zanite });
	     CraftingRegistry.addShapedRecipe(new ItemInstance(AetherItems.PickZanite, 1), new Object[] { "ZZZ", " Y ", " Y ", 'Z', AetherItems.Zanite, 'Y', AetherItems.Stick });
	     CraftingRegistry.addShapedRecipe(new ItemInstance(AetherItems.AxeZanite, 1), new Object[] { "ZZ", "ZY", " Y", 'Z', AetherItems.Zanite, 'Y', AetherItems.Stick });
	     CraftingRegistry.addShapedRecipe(new ItemInstance(AetherItems.ShovelZanite, 1), new Object[] { "Z", "Y", "Y", 'Z', AetherItems.Zanite, 'Y', AetherItems.Stick });
	     CraftingRegistry.addShapedRecipe(new ItemInstance(AetherItems.SwordZanite, 1), new Object[] { "Z", "Z", "Y", 'Z', AetherItems.Zanite, 'Y', AetherItems.Stick });
	     CraftingRegistry.addShapedRecipe(new ItemInstance(AetherItems.LeatherGlove), new Object[] { "C C", 'C', ItemBase.leather });
	     CraftingRegistry.addShapedRecipe(new ItemInstance(AetherItems.IronGlove), new Object[] { "C C", 'C', ItemBase.ironIngot });
	     CraftingRegistry.addShapedRecipe(new ItemInstance(AetherItems.GoldGlove), new Object[] { "C C", 'C', ItemBase.goldIngot });
	     CraftingRegistry.addShapedRecipe(new ItemInstance(AetherItems.DiamondGlove), new Object[] { "C C", 'C', ItemBase.diamond });
	     CraftingRegistry.addShapedRecipe(new ItemInstance(AetherItems.ZaniteGlove), new Object[] { "C C", 'C', AetherItems.Zanite });
	     CraftingRegistry.addShapedRecipe(new ItemInstance(AetherItems.GravititeGlove), new Object[] { "C C", 'C', AetherBlocks.ENCHANTED_GRAVITITE });
	     CraftingRegistry.addShapedRecipe(new ItemInstance(AetherItems.PickGravitite, 1), new Object[] { "ZZZ", " Y ", " Y ", 'Z', AetherBlocks.ENCHANTED_GRAVITITE, 'Y', AetherItems.Stick });
	     CraftingRegistry.addShapedRecipe(new ItemInstance(AetherItems.AxeGravitite, 1), new Object[] { "ZZ", "ZY", " Y", 'Z', AetherBlocks.ENCHANTED_GRAVITITE, 'Y', AetherItems.Stick });
	     CraftingRegistry.addShapedRecipe(new ItemInstance(AetherItems.ShovelGravitite, 1), new Object[] { "Z", "Y", "Y", 'Z', AetherBlocks.ENCHANTED_GRAVITITE, 'Y', AetherItems.Stick });
	     CraftingRegistry.addShapedRecipe(new ItemInstance(AetherItems.SwordGravitite, 1), new Object[] { "Z", "Z", "Y", 'Z', AetherBlocks.ENCHANTED_GRAVITITE, 'Y', AetherItems.Stick });
	     CraftingRegistry.addShapedRecipe(new ItemInstance(AetherItems.WhiteCape, 1), new Object[] { "XX", "XX", "XX", 'X', new ItemInstance(BlockBase.WOOL, 1, 0) });
	     CraftingRegistry.addShapedRecipe(new ItemInstance(AetherItems.RedCape, 1), new Object[] { "XX", "XX", "XX", 'X', new ItemInstance(BlockBase.WOOL, 1, 14) });
	     CraftingRegistry.addShapedRecipe(new ItemInstance(AetherItems.BlueCape, 1), new Object[] { "XX", "XX", "XX", 'X', new ItemInstance(BlockBase.WOOL, 1, 11) });
	     CraftingRegistry.addShapedRecipe(new ItemInstance(AetherItems.BlueCape, 1), new Object[] { "XX", "XX", "XX", 'X', new ItemInstance(BlockBase.WOOL, 1, 3) });
	     CraftingRegistry.addShapedRecipe(new ItemInstance(AetherItems.BlueCape, 1), new Object[] { "XX", "XX", "XX", 'X', new ItemInstance(BlockBase.WOOL, 1, 9) });
	     CraftingRegistry.addShapedRecipe(new ItemInstance(AetherItems.YellowCape, 1), new Object[] { "XX", "XX", "XX", 'X', new ItemInstance(BlockBase.WOOL, 1, 4) });
	     CraftingRegistry.addShapedRecipe(new ItemInstance(AetherItems.CloudParachute), new Object[] { "CC", "CC", 'C', new ItemInstance(AetherBlocks.AERCLOUD, 1, 0) });
	     CraftingRegistry.addShapedRecipe(new ItemInstance(AetherItems.CloudParachuteGold), new Object[] { "CC", "CC", 'C', new ItemInstance(AetherBlocks.AERCLOUD, 1, 2) });
	     CraftingRegistry.addShapedRecipe(new ItemInstance(AetherItems.DartShooter, 1), new Object[] { "X", "X", "Y", 'X', AetherBlocks.SKYROOT_PLANKS, 'Y', AetherItems.Zanite });
	     CraftingRegistry.addShapedRecipe(new ItemInstance(AetherItems.CloudParachute, 1), new Object[] { "UU", "UU", 'U', new ItemInstance(AetherBlocks.AERCLOUD, 0) });
	     CraftingRegistry.addShapedRecipe(new ItemInstance(AetherItems.CloudParachuteGold, 1), new Object[] { "UU", "UU", 'U', new ItemInstance(AetherBlocks.AERCLOUD, 2) });
	     CraftingRegistry.addShapedRecipe(new ItemInstance(ItemBase.saddle, 1), new Object[] { "XXX", "XZX", 'X', ItemBase.leather, 'Z', ItemBase.string });
	     CraftingRegistry.addShapedRecipe(new ItemInstance(AetherItems.NatureStaff, 1), new Object[] { "Y", "X", 'X', AetherItems.Stick, 'Y', AetherItems.Zanite });
	     CraftingRegistry.addShapedRecipe(new ItemInstance(AetherItems.GravititeHelmet, 1), new Object[] { "XXX", "X X", 'X', AetherBlocks.ENCHANTED_GRAVITITE });
	     CraftingRegistry.addShapedRecipe(new ItemInstance(AetherItems.GravititeBodyplate, 1), new Object[] { "X X", "XXX", "XXX", 'X', AetherBlocks.ENCHANTED_GRAVITITE });
	     CraftingRegistry.addShapedRecipe(new ItemInstance(AetherItems.GravititePlatelegs, 1), new Object[] { "XXX", "X X", "X X", 'X', AetherBlocks.ENCHANTED_GRAVITITE });
	     CraftingRegistry.addShapedRecipe(new ItemInstance(AetherItems.GravititeBoots, 1), new Object[] { "X X", "X X", 'X', AetherBlocks.ENCHANTED_GRAVITITE });
	     CraftingRegistry.addShapedRecipe(new ItemInstance(AetherItems.ZaniteHelmet, 1), new Object[] { "XXX", "X X", 'X', AetherItems.Zanite });
	     CraftingRegistry.addShapedRecipe(new ItemInstance(AetherItems.ZaniteChestplate, 1), new Object[] { "X X", "XXX", "XXX", 'X', AetherItems.Zanite });
	     CraftingRegistry.addShapedRecipe(new ItemInstance(AetherItems.ZaniteLeggings, 1), new Object[] { "XXX", "X X", "X X", 'X', AetherItems.Zanite });
	     CraftingRegistry.addShapedRecipe(new ItemInstance(AetherItems.ZaniteBoots, 1), new Object[] { "X X", "X X", 'X', AetherItems.Zanite });
	     CraftingRegistry.addShapedRecipe(new ItemInstance(AetherItems.CloudParachute), new Object[] { "CC", "CC", 'C', new ItemInstance(AetherBlocks.AERCLOUD, 1, 0) });
	     CraftingRegistry.addShapedRecipe(new ItemInstance(AetherItems.CloudParachuteGold), new Object[] { "CC", "CC", 'C', new ItemInstance(AetherBlocks.AERCLOUD, 1, 2) });
	 }
	 private void registerShapelessRecipes() {
		 CraftingRegistry.addShapelessRecipe(new ItemInstance(AetherBlocks.SKYROOT_PLANKS,4), new ItemInstance(AetherBlocks.SKYROOT_LOG));
		 CraftingRegistry.addShapelessRecipe(new ItemInstance(AetherBlocks.SKYROOT_PLANKS,4), new ItemInstance(AetherBlocks.GOLDEN_OAK_LOG));
		 CraftingRegistry.addShapelessRecipe(new ItemInstance(AetherItems.DartShooter, 1, 1), new Object[] { new ItemInstance(AetherItems.DartShooter, 1, 0), AetherItems.AechorPetal });
	     CraftingRegistry.addShapelessRecipe(new ItemInstance(AetherItems.Zanite, 4), new Object[] { AetherBlocks.ZANITE_BLOCK });
	     CraftingRegistry.addShapelessRecipe(new ItemInstance(ItemBase.dyePowder, 2, 5), new Object[] { AetherBlocks.PURPLE_FLOWER });
	 }
}
