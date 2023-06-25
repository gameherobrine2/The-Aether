package com.gildedgames.aether.mixin.gui.mainmenu;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import net.minecraft.client.gui.screen.menu.MainMenu;
import net.minecraft.client.gui.widgets.Button;
@Mixin(MainMenu.class)
public interface MainMenuAccessor {
	@Accessor("splashMessage")
	public void setSplashMessage(String s);
	@Accessor("multiplayerButton")
	public void setMultiplayerButton(Button b);
	@Accessor("multiplayerButton")
	public Button getMultiplayerButton();
	@Accessor("splashMessage")
	public String getSplashMessage();
}
