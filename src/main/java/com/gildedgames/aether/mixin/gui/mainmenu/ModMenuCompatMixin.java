package com.gildedgames.aether.mixin.gui.mainmenu;

import com.gildedgames.aether.Aether;
import com.gildedgames.aether.mixin.gui.ButtonAccessor;
import com.gildedgames.aether.titlescreen.AetherButton;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.gui.screen.ScreenBase;
import net.minecraft.client.gui.screen.menu.MainMenu;
import net.minecraft.client.gui.widgets.Button;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/*
	@Inject(at = @At("RETURN"), method = "init")
	public void drawMenuButton(CallbackInfo info) {
		Button texturePackButton = minecraft.isApplet ? (Button) this.buttons.get(this.buttons.size() - 2) : (Button) this.buttons.get(2);
		texturePackButton.text = "Texture Packs";
		int newWidth = ((MixinGuiButton) texturePackButton).getWidth() / 2 - 1;
		((MixinGuiButton) texturePackButton).setWidth(newWidth);
		this.buttons.add(new ModMenuButtonWidget(100, this.width / 2 + 2, texturePackButton.y, newWidth, 20,  "Mods (" + ModMenu.getFormattedModCount() + " loaded)"));
	}
 */
@Mixin(value = MainMenu.class, priority = 1010)
public class ModMenuCompatMixin extends ScreenBase
{
    @Inject(at = @At("RETURN"), method = "init")
    public void addMenuButton(CallbackInfo info) {
        this.buttons.add(new Button(101, this.width - 25, 5, 20, 20,  "T"));
    }

    @Inject(method = "buttonClicked", at = @At("HEAD"))
    private void onActionPerformed(Button button, CallbackInfo ci) {
        if (button.id == 101) {
            Aether.themeOption = !Aether.themeOption;
        }
    }
    @Inject(at = @At("RETURN"), method = "init")
    public void replaceMenuButtons(CallbackInfo info)
    {
        if (FabricLoader.getInstance().isModLoaded("modmenu"))
        {
            Button b = (Button) this.buttons.get(4);
            ButtonAccessor ba = (ButtonAccessor) b;
            AetherButton replacement = new AetherButton(b.id,b.x,b.y,ba.getWidth(),ba.getHeight(),b.text);
            this.buttons.remove(4);
            this.buttons.add(replacement);
        }


        // returns quit button BUT ITS REALLY UGLY
        /*
        this.buttons.remove(3);

        TranslationStorage var2 = TranslationStorage.getInstance();
        int var4 = this.height / 4 + 48;
        this.buttons.add(new AetherButton(0, this.width / 2 - 100, var4 + 72 + 12, 98, 20, var2.translate("menu.options")));
        this.buttons.add(new AetherButton(4, this.width / 2 + 2, var4 + 72 + 12, 98, 20, var2.translate("menu.quit")));
        */

        // if we didn't change it on initialization we could replace all of them
        /*
        for (int i = 0; i < buttons.size(); i++)
        {
            Object button = buttons.get(i);
            Button b = (Button) this.buttons.get(i);
            ButtonAccessor ba = (ButtonAccessor) b;
            GuiAetherButton replacement = new GuiAetherButton(b.id,b.x,b.y,ba.getWidth(),ba.getHeight(),b.text);
            this.buttons.remove(i);
            this.buttons.add(replacement);
        }*/
    }
}