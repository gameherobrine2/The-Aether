package com.gildedgames.aether.mixin.gui.mainmenu;

import java.util.*;

import com.gildedgames.aether.Aether;
import net.minecraft.client.gui.widgets.Button;
import net.minecraft.client.render.Tessellator;
import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.*;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.ScreenBase;

@Mixin(value = ScreenBase.class, priority = 900)
public class MainMenuMixinAlt
{
    @Shadow
    protected List buttons;

    @Shadow
    protected Minecraft minecraft;

    @Redirect(method = "renderDirtBackground", at = @At(value = "INVOKE", target="Lorg/lwjgl/opengl/GL11;glBindTexture(II)V"))
    public void BindCustomTexture(int bind_to, int texture)
    {
        if (((Button)buttons.get(1)).id == 2 && Aether.themeOption)
            GL11.glBindTexture(3553, minecraft.textureManager.getTextureId("aether:textures/gui/aetherBG.png"));
        else
            GL11.glBindTexture(3553, this.minecraft.textureManager.getTextureId("/gui/background.png")); // vanilla
    }

    @Redirect(method = "renderDirtBackground", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/Tessellator;colour(I)V"))
    public void invoke(Tessellator instance, int i)
    {
        if (((Button)buttons.get(1)).id == 2 && Aether.themeOption) // has singleplayer button
            instance.colour(10066329);
        else
            instance.colour(4210752); // vanilla

    }

    /*
    public void renderBackground(int i) {
        if (this.minecraft.level != null) {
            this.fillGradient(0, 0, this.width, this.height, -1072689136, -804253680);
        } else {
            this.renderDirtBackground(i);
        }

    }*/
}
