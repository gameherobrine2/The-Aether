package com.gildedgames.aether.gui.misc;
import net.minecraft.client.render.TextRenderer;
import org.lwjgl.opengl.GL11;

import com.gildedgames.aether.Aether;
import com.gildedgames.aether.mixin.MainMenuMixin;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.menu.MainMenu;
import net.minecraft.client.gui.widgets.Button;

public class GuiAetherButton extends Button {
    public int scrollMax;
    public int scrollHeight;
    public int scrollMin;
    public int scrollCrop;
    public int scrollCropMax;
    public boolean retracting;
    
    public GuiAetherButton(final int id, final int x, final int y, final String text) {
        super(id, x, y, text);
        this.scrollMax = 100;
        this.scrollHeight = this.scrollMax;
        this.scrollMin = 115;
        this.scrollCrop = 20;
        this.scrollCropMax = 90;
        this.retracting = false;
    }
    
    public GuiAetherButton(final int id, final int x, final int y, final int width, final int height, final String text) {
        super(id, x, y, width, height, text);
        this.scrollMax = 100;
        this.scrollHeight = this.scrollMax;
        this.scrollMin = 115;
        this.scrollCrop = 20;
        this.scrollCropMax = 90;
        this.retracting = false;
        this.active = true;
        this.visible = true;
    }
    
    @Override
    protected int getYImage(final boolean hovered) {
        byte byte0 = 1;
        if (Aether.themeOption) {
            if (!this.active) {
                byte0 = 0;
            }
            else if (hovered) {
                if (byte0 < 2) {
                    ++byte0;
                }
                if (this.scrollCrop < this.scrollCropMax) {
                    ++this.scrollCrop;
                }
                if (this.scrollHeight < this.scrollMin) {
                    ++this.scrollHeight;
                }
            }
            else {
                if (this.scrollCrop > this.scrollCropMax) {
                    --this.scrollCrop;
                }
                if (this.scrollHeight > this.scrollMax) {
                    --this.scrollHeight;
                }
                if (this.scrollHeight == this.scrollMax) {
                    this.retracting = false;
                }
            }
        }
        else if (!Aether.themeOption) {
            if (!this.active) {
                byte0 = 0;
            }
            else if (hovered) {
                byte0 = 2;
            }
        }
        return byte0;
    }
    
    @Override
    public void render(final Minecraft minecraft, final int mouseX, final int mouseY) {
        if (!this.visible) {
            return;
        }
        if (Aether.themeOption) {
            final TextRenderer fontrenderer = minecraft.textRenderer;
            GL11.glBindTexture(3553, minecraft.textureManager.getTextureId("/assets/aether/stationapi/textures/gui/buttons.png"));
            GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
            final boolean flag = mouseX >= this.x && mouseY >= this.y && mouseX < this.x + this.width && mouseY < this.y + this.height;
            final int k = this.getYImage(flag);
            this.blit(this.x + this.scrollHeight - 90, this.y, 0, 46 + k * 20, this.width / 2, this.height);
            this.blit(this.x + this.scrollHeight + this.width / 2 - 90, this.y, 200 - this.width / 2, 46 + k * 20, this.width / 2, this.height);
            this.postRender(minecraft, mouseX, mouseY);
            if (!this.active) {
                this.drawTextWithShadow(fontrenderer, this.text, this.x + this.width / 10 + this.scrollHeight - 80, this.y + (this.height - 8) / 2, -6250336);
            }
            else if (flag) {
                this.drawTextWithShadow(fontrenderer, this.text, this.x + this.width / 10 + this.scrollHeight - 80, this.y + (this.height - 8) / 2, 7851212);
            }
            else {
                this.drawTextWithShadow(fontrenderer, this.text, this.x + this.width / 10 + this.scrollHeight - 80, this.y + (this.height - 8) / 2, 14737632);
            }
        }
        if (!Aether.themeOption) {
            final TextRenderer fontrenderer = minecraft.textRenderer;
            GL11.glBindTexture(3553, minecraft.textureManager.getTextureId("/gui/gui.png"));
            GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
            final boolean flag = mouseX >= this.x && mouseY >= this.y && mouseX < this.x + this.width && mouseY < this.y + this.height;
            final int k = this.getYImage(flag);
            this.blit(this.x, this.y, 0, 46 + k * 20, this.width / 2, this.height);
            this.blit(this.x + this.width / 2, this.y, 200 - this.width / 2, 46 + k * 20, this.width / 2, this.height);
            this.postRender(minecraft, mouseX, mouseY);
            if (!this.active) {
                this.drawTextWithShadowCentred(fontrenderer, this.text, this.x + this.width / 2, this.y + (this.height - 8) / 2, -6250336);
            }
            else if (flag) {
                this.drawTextWithShadowCentred(fontrenderer, this.text, this.x + this.width / 2, this.y + (this.height - 8) / 2, 16777120);
            }
            else {
                this.drawTextWithShadowCentred(fontrenderer, this.text, this.x + this.width / 2, this.y + (this.height - 8) / 2, 14737632);
            }
        }
    }
    
    @Override
    protected void postRender(final Minecraft minecraft, final int mouseX, final int mouseY) {
    }
    
    @Override
    public void mouseReleased(final int mouseX, final int mouseY) {
    }
    
    @Override
    public boolean isMouseOver(final Minecraft minecraft, final int mouseX, final int mouseY) {
        return this.active && mouseX >= this.x && mouseY >= this.y && mouseX < this.x + this.width && mouseY < this.y + this.height;
    }
}
