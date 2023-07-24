package com.gildedgames.aether.gui;

import com.gildedgames.aether.entity.tile.TileEntityIncubator;
import com.gildedgames.aether.gui.container.ContainerIncubator;
import net.minecraft.client.gui.screen.container.ContainerBase;
import net.minecraft.entity.player.PlayerInventory;
import org.lwjgl.opengl.GL11;

public class GuiIncubator extends ContainerBase
{
    private TileEntityIncubator IncubatorInventory;

    public GuiIncubator(final PlayerInventory inventoryplayer, final TileEntityIncubator tileentityIncubator)
    {
        super(new ContainerIncubator(inventoryplayer, tileentityIncubator));
        this.IncubatorInventory = tileentityIncubator;
    }

    @Override
    protected void renderForeground()
    {
        this.textManager.drawText("Incubator", 60, 6, 4210752);
        this.textManager.drawText("Inventory", 8, this.containerHeight - 96 + 2, 4210752);
    }

    @Override
    protected void renderContainerBackground(final float tickDelta)
    {
        final int i = this.minecraft.textureManager.getTextureId("/assets/aether/textures/gui/incubator.png");
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        this.minecraft.textureManager.bindTexture(i);
        final int j = (this.width - this.containerWidth) / 2;
        final int k = (this.height - this.containerHeight) / 2;
        this.blit(j, k, 0, 0, this.containerWidth, this.containerHeight);
        if (this.IncubatorInventory.isBurning())
        {
            final int l = this.IncubatorInventory.getBurnTimeRemainingScaled(12);
            this.blit(j + 74, k + 47 - l, 176, 12 - l, 14, l + 2);
        }
        final int i2 = this.IncubatorInventory.getCookProgressScaled(54);
        this.blit(j + 103, k + 70 - i2, 179, 70 - i2, 10, i2);
    }
}
