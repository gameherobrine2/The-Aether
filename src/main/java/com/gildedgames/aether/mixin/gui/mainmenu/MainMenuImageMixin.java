package com.gildedgames.aether.mixin.gui.mainmenu;

import com.gildedgames.aether.Aether;
import com.gildedgames.aether.mixin.gui.ButtonAccessor;
import com.gildedgames.aether.mixin.sound.SoundHelperAccessor;
import com.gildedgames.aether.titlescreen.AetherButton;
import net.minecraft.client.gui.screen.ScreenBase;
import net.minecraft.client.gui.screen.menu.MainMenu;
import net.minecraft.client.gui.widgets.Button;
import net.minecraft.client.render.TextRenderer;
import net.modificationstation.stationapi.api.util.math.ColorHelper;
import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.List;

@Mixin(value = MainMenu.class, priority = 1011)
public class MainMenuImageMixin extends ScreenBase
{
    @Redirect(method = "init", at = @At(value = "INVOKE", target = "Ljava/util/List;add(Ljava/lang/Object;)Z"))
    public boolean initializeMainMenu(List instance, Object e)
    {
        if (Aether.musicId == -1 && !Aether.loadingWorld) {
            minecraft.soundHelper.playSound("aether:aether.music.menu", 1.0f, 1.0f);
            Aether.musicId = ((SoundHelperAccessor) minecraft.soundHelper).getSoundUID();
        }

        Button button = ((Button) e);
        int id = button.id;
        ButtonAccessor b = (ButtonAccessor) button;

        return instance.add(new AetherButton(id, button.x, button.y, button.text));
    }

    @Redirect(method = "render", at=@At(value = "INVOKE", target="Lorg/lwjgl/opengl/GL11;glBindTexture(II)V"))
    public void BindCustomTexture(int target, int texture)
    {
        if (Aether.themeOption)
            GL11.glBindTexture(3553, this.minecraft.textureManager.getTextureId("/assets/aether/textures/title/mclogomod2.png"));
        else
            GL11.glBindTexture(3553, this.minecraft.textureManager.getTextureId("/title/mclogo.png"));
    }

    @Redirect(method = "render", at=@At(value = "INVOKE", target="Lnet/minecraft/client/gui/screen/menu/MainMenu;drawTextWithShadow(Lnet/minecraft/client/render/TextRenderer;Ljava/lang/String;III)V"))
    public void drawTextWithShadow(MainMenu instance, TextRenderer textRenderer, String text, int i, int j, int color)
    {
        textRenderer.drawTextWithShadow(text, i, j, ColorHelper.Abgr.getAbgr(255,255,255, 255));
    }
}
