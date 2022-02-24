package com.gildedgames.aether.event.listener;

import com.gildedgames.aether.registry.AetherBlocks;
import com.gildedgames.aether.registry.AetherItems;

import net.mine_diver.unsafeevents.listener.EventListener;
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
	 }
	 private void registerShapelessRecipes() {
		 CraftingRegistry.addShapelessRecipe(new ItemInstance(AetherBlocks.SKYROOT_PLANKS), new ItemInstance(AetherBlocks.LOG));
	 }
}
