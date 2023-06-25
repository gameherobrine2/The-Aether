package com.gildedgames.aether.titlescreen;

import com.gildedgames.aether.Aether;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.widgets.Button;
import net.minecraft.client.render.TextRenderer;
import org.lwjgl.opengl.GL11;

public class AetherButton extends Button
{
    public AetherButton(int i, int j, int k, String string)
    {
        super(i, j, k, string);
    }

    public AetherButton(int i, int j, int k, int l, int m, String string)
    {
        super(i,j,k,l,m,string);
    }

    @Override
    public void render(Minecraft minecraft, int i, int j) {
        if (!Aether.themeOption)
        {
            super.render(minecraft, i, j);
            return;
        }

        if (this.visible) {
            TextRenderer var4 = minecraft.textRenderer;
            GL11.glBindTexture(3553, minecraft.textureManager.getTextureId("/assets/aether/textures/gui/buttons.png"));
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            boolean var5 = i >= this.x && j >= this.y && i < this.x + this.width && j < this.y + this.height;
            int var6 = this.getYImage(var5);
            this.blit(this.x, this.y, 0, 46 + var6 * 20, this.width / 2, this.height);
            this.blit(this.x + this.width / 2, this.y, 200 - this.width / 2, 46 + var6 * 20, this.width / 2, this.height);
            this.postRender(minecraft, i, j);
            if (!this.active) {
                this.drawTextWithShadow(var4, this.text, this.x + 20, this.y + (this.height - 8) / 2, -6250336);
            } else if (var5) {
                this.drawTextWithShadow(var4, this.text, this.x + 20, this.y + (this.height - 8) / 2, 7851212);
            } else {
                this.drawTextWithShadow(var4, this.text, this.x + 20, this.y + (this.height - 8) / 2, 14737632);
            }

        }
    }
}
