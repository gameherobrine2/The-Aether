package com.gildedgames.aether.mixin;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.gen.Accessor;

import com.gildedgames.aether.Aether;
import com.gildedgames.aether.gui.misc.GuiAetherButton;

import net.minecraft.client.Minecraft;
import net.minecraft.client.SinglePlayerClientInteractionManager;
import net.minecraft.client.gui.screen.ScreenBase;
import net.minecraft.client.gui.screen.menu.MainMenu;
import net.minecraft.client.gui.widgets.Button;
import net.minecraft.client.render.Tessellator;
import net.minecraft.client.resource.language.TranslationStorage;
import net.minecraft.util.maths.MathHelper;

@Mixin(MainMenu.class)
public class MainMenuMixin extends ScreenBase{
	private String hoverText = "";
    public void drawAetherDefaultBackground() {
        this.drawAetherWorldBackground(0);
    }
    
    public void drawAetherWorldBackground(final int i) {
        this.drawAetherBackground(i);
    }
    
    public void drawAetherBackground(final int i) {
    	Minecraft minecraft = MinecraftClientAccessor.getMCinstance();
        GL11.glDisable(2896);
        GL11.glDisable(2912);
        final Tessellator tessellator = Tessellator.INSTANCE;
        GL11.glBindTexture(3553, minecraft.textureManager.getTextureId("/assets/aether/stationapi/textures/gui/aetherBG.png"));
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        final float f = 32.0f;
        tessellator.start();
        tessellator.colour(10066329);
        tessellator.vertex(0.0, minecraft.currentScreen.height, 0.0, 0.0, minecraft.currentScreen.height / f + i);
        tessellator.vertex(minecraft.currentScreen.width, minecraft.currentScreen.height, 0.0, minecraft.currentScreen.width / f, minecraft.currentScreen.height / f + i);
        tessellator.vertex(minecraft.currentScreen.width, 0.0, 0.0, minecraft.currentScreen.width / f, 0 + i);
        tessellator.vertex(0.0, 0.0, 0.0, 0.0, 0 + i);
        tessellator.draw();
    }
    public void drawMiniLogo() {
        final Tessellator var4 = Tessellator.INSTANCE;
        final byte var5 = 15;
        final byte var6 = 15;
        GL11.glBindTexture(3553, MinecraftClientAccessor.getMCinstance().textureManager.getTextureId("/title/mclogo.png"));
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        GL11.glPushMatrix();
        GL11.glScalef(0.8f, 0.8f, 0.8f);
        MainMenu.class.cast(this).blit(var5 + 0, var6 + 0, 0, 0, 155, 44);
        MainMenu.class.cast(this).blit(var5 + 155, var6 + 0, 0, 45, 155, 44);
        GL11.glPopMatrix();
        var4.colour(16777215);
    }
    @Overwrite
    public void render(final int mouseX, final int mouseY, final float delta) {
    	Minecraft minecraft = MinecraftClientAccessor.getMCinstance();
        if (Aether.themeOption) {
            if (Aether.renderOption) {
                final Tessellator var4 = Tessellator.INSTANCE;
                final boolean var5 = true;
                final byte var6 = 15;
                final byte var7 = 15;
                GL11.glBindTexture(3553, minecraft.textureManager.getTextureId("/assets/aether/stationapi/textures/title/mclogomod1.png"));
                GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
                (MainMenu.class.cast(this)).blit(var6 + 0, var7 + 0, 0, 0, 155, 44);
                (MainMenu.class.cast(this)).blit(var6 + 155, var7 + 0, 0, 45, 155, 44);
                var4.colour(16777215);
                final String var8 = "Minecraft Beta 1.7.3";
                minecraft.currentScreen.drawTextWithShadow(MinecraftClientAccessor.getMCinstance().textRenderer, var8, minecraft.currentScreen.width - MinecraftClientAccessor.getMCinstance().textRenderer.getTextWidth(var8) - 5, minecraft.currentScreen.height - 20, 16777215);
                final String var9 = "Copyright Mojang AB. Do not distribute.";
                minecraft.currentScreen.drawTextWithShadow(MinecraftClientAccessor.getMCinstance().textRenderer, var9, minecraft.currentScreen.width - MinecraftClientAccessor.getMCinstance().textRenderer.getTextWidth(var9) - 5, minecraft.currentScreen.height - 10, 5263440);
            }
            else {
                this.drawAetherDefaultBackground();
                final Tessellator var4 = Tessellator.INSTANCE;
                final short var10 = 274;
                final int var11 = minecraft.currentScreen.width / 2 - var10 / 2;
                final byte var7 = 30;
                GL11.glBindTexture(3553, minecraft.textureManager.getTextureId("/assets/aether/stationapi/textures/title/mclogomod1.png"));
                GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
                MainMenu.class.cast(this).blit(var11 + 30, var7 + 0, 0, 0, 155, 44);
                MainMenu.class.cast(this).blit(var11 + 185, var7 + 0, 0, 45, 155, 44);
                var4.colour(16777215);
                GL11.glPushMatrix();
                GL11.glTranslatef((float)(minecraft.currentScreen.width / 2 + 90), 70.0f, 0.0f);
                GL11.glRotatef(-20.0f, 0.0f, 0.0f, 1.0f);
                float var12 = 1.8f - MathHelper.abs(MathHelper.sin(System.currentTimeMillis() % 1000L / 1000.0f * 3.1415927f * 2.0f) * 0.1f);
                var12 = var12 * 100.0f / (MinecraftClientAccessor.getMCinstance().textRenderer.getTextWidth(MainMenuAccessor.class.cast(this).getSplashMessage()) + 32);
                GL11.glScalef(var12, var12, var12);
                minecraft.currentScreen.drawTextWithShadowCentred(MinecraftClientAccessor.getMCinstance().textRenderer, MainMenuAccessor.class.cast(this).getSplashMessage(), 0, -8, 16776960);
                GL11.glPopMatrix();
                (MainMenu.class.cast(this)).drawTextWithShadow(MinecraftClientAccessor.getMCinstance().textRenderer, "Minecraft Beta 1.7.3", 2, 2, 5263440);
                final String var9 = "Copyright Mojang AB. Do not distribute.";
                minecraft.currentScreen.drawTextWithShadow(MinecraftClientAccessor.getMCinstance().textRenderer, var9, minecraft.currentScreen.width - MinecraftClientAccessor.getMCinstance().textRenderer.getTextWidth(var9) - 2, minecraft.currentScreen.height - 10, 16777215);
            }
        }
        else if (Aether.renderOption) {
            final Tessellator var4 = Tessellator.INSTANCE;
            final boolean var5 = true;
            final String var13 = "Minecraft Beta 1.7.3";
            minecraft.currentScreen.drawTextWithShadow(MinecraftClientAccessor.getMCinstance().textRenderer, var13, minecraft.currentScreen.width - MinecraftClientAccessor.getMCinstance().textRenderer.getTextWidth(var13) - 5, minecraft.currentScreen.height - 20, 16777215);
            final String var14 = "Copyright Mojang AB. Do not distribute.";
            minecraft.currentScreen.drawTextWithShadow(MinecraftClientAccessor.getMCinstance().textRenderer, var14, minecraft.currentScreen.width - MinecraftClientAccessor.getMCinstance().textRenderer.getTextWidth(var14) - 5, minecraft.currentScreen.height - 10, 5263440);
            this.drawMiniLogo();
        }
        else {
        	MainMenu.class.cast(this).renderBackground();
            final Tessellator var4 = Tessellator.INSTANCE;
            final short var10 = 274;
            final int var11 = minecraft.currentScreen.width / 2 - var10 / 2;
            final byte var7 = 30;
            GL11.glBindTexture(3553, minecraft.textureManager.getTextureId("/title/mclogo.png"));
            GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
            MainMenu.class.cast(this).blit(var11 + 0, var7 + 0, 0, 0, 155, 44);
            MainMenu.class.cast(this).blit(var11 + 155, var7 + 0, 0, 45, 155, 44);
            var4.colour(16777215);
            GL11.glPushMatrix();
            GL11.glTranslatef((float)(minecraft.currentScreen.width / 2 + 90), 70.0f, 0.0f);
            GL11.glRotatef(-20.0f, 0.0f, 0.0f, 1.0f);
            float var12 = 1.8f - MathHelper.abs(MathHelper.sin(System.currentTimeMillis() % 1000L / 1000.0f * 3.1415927f * 2.0f) * 0.1f);
            var12 = var12 * 100.0f / (MinecraftClientAccessor.getMCinstance().textRenderer.getTextWidth(MainMenuAccessor.class.cast(this).getSplashMessage()) + 32);
            GL11.glScalef(var12, var12, var12);
            minecraft.currentScreen.drawTextWithShadowCentred(MinecraftClientAccessor.getMCinstance().textRenderer, MainMenuAccessor.class.cast(this).getSplashMessage(), 0, -8, 16776960);
            GL11.glPopMatrix();
            (MainMenu.class.cast(this)).drawTextWithShadow(MinecraftClientAccessor.getMCinstance().textRenderer, "Minecraft Beta 1.7.3", 2, 2, 5263440);
            final String var9 = "Copyright Mojang AB. Do not distribute.";
            minecraft.currentScreen.drawTextWithShadow(MinecraftClientAccessor.getMCinstance().textRenderer, var9, minecraft.currentScreen.width - MinecraftClientAccessor.getMCinstance().textRenderer.getTextWidth(var9) - 2, minecraft.currentScreen.height - 10, 16777215);
        }
        minecraft.currentScreen.drawTextWithShadow(MinecraftClientAccessor.getMCinstance().textRenderer, this.hoverText, minecraft.currentScreen.width - 72, 28, 16777215);
        //ScreenBaseAccessor.class.cast(this).invokeRender(mouseX, mouseY, delta);
        super.render(mouseX, mouseY, delta);
    }
    
	@Overwrite
    public void init() {
		Minecraft minecraft = MinecraftClientAccessor.getMCinstance();
        Aether.themeOption = true;
        //minecraft.achievement = new GuiAchievementAether(minecraft);
        //minecraft.overlay = new GuiIngameAether(minecraft);
        if (Aether.musicId == -1 && !Aether.loadingWorld) {
            minecraft.soundHelper.playSound("aether:aether.music.menu", 1.0f, 1.0f);
            /*try {
                //Aether.musicId = (int)ModLoader.getPrivateValue((Class)SoundHelper.class, minecraft.soundHelper, "e");
                //ModLoader.setPrivateValue((Class)SoundHelper.class, minecraft.soundHelper, "i", 999999999);
            }
            catch (Exception e) {
                if (e instanceof NoSuchFieldException) {
                    try {
                        Aether.musicId = (int)ModLoader.getPrivateValue((Class)SoundHelper.class, minecraft.soundHelper, "field_587_e");
                        ModLoader.setPrivateValue((Class)SoundHelper.class, minecraft.soundHelper, "ticksBeforeMusic", 999999999);
                    }
                    catch (Exception e2) {
                        e2.printStackTrace();
                    }
                }
                else {
                    e.printStackTrace();
                }
            }*/
        }
        if (Aether.loadingWorld) {
            Aether.loadingWorld = false;
        }
        //MinecraftClientAccessor.getMCinstance().options.hideHud = true;
        //MinecraftClientAccessor.getMCinstance().options.thirdPerson = true;
        final TranslationStorage var1 = TranslationStorage.getInstance();
        if (Aether.renderOption) {
            minecraft.interactionManager = new SinglePlayerClientInteractionManager(minecraft);
            //if (minecraft.level == null) {
             //   this.loadSaves();
             //   final String var2 = this.getSaveFileName(0);
             //   final String var3 = this.getSaveName(0);
             //   if (var3 == null || var2 == null) {
            //        Aether.renderOption = false;
            //    }
            //    else {
            //        minecraft.createOrLoadWorld(var2, var3, 0L);
            //        ((LevelAccessor)minecraft.level).set212(999999999);
           //     }
            //}
        }
        else if (Aether.themeOption) {
            this.drawAetherDefaultBackground();
        }
        else {
        	MainMenu.class.cast(this).renderBackground();
        }
        final Calendar var4 = Calendar.getInstance();
        var4.setTime(new Date());
        if (var4.get(2) + 1 == 11 && var4.get(5) == 9) {
        	((MainMenuAccessor)MainMenu.class.cast(this)).setSplashMessage("Happy birthday, ez!");
        }
        else if (var4.get(2) + 1 == 6 && var4.get(5) == 1) {
            ((MainMenuAccessor)MainMenu.class.cast(this)).setSplashMessage("Happy birthday, Notch!");
        }
        else if (var4.get(2) + 1 == 12 && var4.get(5) == 24) {
            ((MainMenuAccessor)MainMenu.class.cast(this)).setSplashMessage("Merry X-mas!");
        }
        else if (var4.get(2) + 1 == 1 && var4.get(5) == 1) {
            ((MainMenuAccessor)MainMenu.class.cast(this)).setSplashMessage("Happy new year!");
        }
        else if (var4.get(2) + 1 == 8 && var4.get(5) == 3) {
            ((MainMenuAccessor)MainMenu.class.cast(this)).setSplashMessage("We miss you, Ripsand :(");
        }
        if (Aether.renderOption) {
            final int var5 = MainMenu.class.cast(this).height / 4 + 20;
            ((ScreenBaseAccessor)MainMenu.class.cast(this)).getButtons().clear();
            ((ScreenBaseAccessor)MainMenu.class.cast(this)).getButtons().add(new GuiAetherButton(1, minecraft.currentScreen.width / 4 - 100, var5, var1.translate("menu.singleplayer")));
            ((MainMenuAccessor)MainMenu.class.cast(this)).setMultiplayerButton(new GuiAetherButton(2, minecraft.currentScreen.width / 4 - 100, var5 + 24, var1.translate("menu.multiplayer")));
            ((ScreenBaseAccessor)MainMenu.class.cast(this)).getButtons().add(((MainMenuAccessor)MainMenu.class.cast(this)).getMultiplayerButton());
            ((ScreenBaseAccessor)MainMenu.class.cast(this)).getButtons().add(new GuiAetherButton(3, minecraft.currentScreen.width / 4 - 100, var5 + 48, var1.translate("menu.mods")));
            ((ScreenBaseAccessor)MainMenu.class.cast(this)).getButtons().add(new Button(5, minecraft.currentScreen.width - 24, 4, 20, 20, var1.translate("W")));
            ((ScreenBaseAccessor)MainMenu.class.cast(this)).getButtons().add(new Button(6, minecraft.currentScreen.width - 48, 4, 20, 20, var1.translate("T")));
            ((ScreenBaseAccessor)MainMenu.class.cast(this)).getButtons().add(new Button(7, minecraft.currentScreen.width - 72, 4, 20, 20, var1.translate("Q")));
            ((ScreenBaseAccessor)MainMenu.class.cast(this)).getButtons().add(new GuiAetherButton(0, minecraft.currentScreen.width / 4 - 100, var5 + 72, var1.translate("menu.options")));
            ((ScreenBaseAccessor)MainMenu.class.cast(this)).getButtons().add(new GuiAetherButton(4, minecraft.currentScreen.width / 4 - 100, var5 + 96, var1.translate("menu.quit")));
            if (minecraft.session == null) {
                //this.multiplayerButton.active = false;
            }
        }
        else if (!Aether.renderOption) {
            final int var5 = minecraft.currentScreen.height / 4 + 40;
            final int var6 = minecraft.currentScreen.width / 2 + 100;
            ((ScreenBaseAccessor)MainMenu.class.cast(this)).getButtons().clear();
            ((ScreenBaseAccessor)MainMenu.class.cast(this)).getButtons().add(new GuiAetherButton(1, minecraft.currentScreen.width / 2 - 110, var5, var1.translate("menu.singleplayer")));
            ((MainMenuAccessor)MainMenu.class.cast(this)).setMultiplayerButton(new GuiAetherButton(2, minecraft.currentScreen.width / 2 - 110, var5 + 24, var1.translate("menu.multiplayer")));
            ((ScreenBaseAccessor)MainMenu.class.cast(this)).getButtons().add(((MainMenuAccessor)MainMenu.class.cast(this)).getMultiplayerButton());
            ((ScreenBaseAccessor)MainMenu.class.cast(this)).getButtons().add(new GuiAetherButton(3, minecraft.currentScreen.width / 2 - 110, var5 + 48, var1.translate("menu.mods")));
            ((ScreenBaseAccessor)MainMenu.class.cast(this)).getButtons().add(new Button(5, minecraft.currentScreen.width - 24, 4, 20, 20, var1.translate("W")));
            ((ScreenBaseAccessor)MainMenu.class.cast(this)).getButtons().add(new Button(6, minecraft.currentScreen.width - 48, 4, 20, 20, var1.translate("T")));
            ((ScreenBaseAccessor)MainMenu.class.cast(this)).getButtons().add(new GuiAetherButton(0, minecraft.currentScreen.width / 2 - 110, var5 + 72, 98, 20, var1.translate("menu.options")));
            ((ScreenBaseAccessor)MainMenu.class.cast(this)).getButtons().add(new GuiAetherButton(4, minecraft.currentScreen.width / 2 + 2 - 10, var5 + 72, 98, 20, var1.translate("menu.quit")));
            if (minecraft.session == null) {
                //this.multiplayerButton.active = false;
            }
        }
    }
}
