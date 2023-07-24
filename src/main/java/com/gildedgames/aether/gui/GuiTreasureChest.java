package com.gildedgames.aether.gui;

import com.gildedgames.aether.entity.tile.TileEntityTreasureChest;
import net.minecraft.client.gui.screen.container.ContainerBase;
import net.minecraft.container.Chest;
import net.minecraft.inventory.InventoryBase;
import org.lwjgl.opengl.GL11;

public class GuiTreasureChest extends ContainerBase
{
    private InventoryBase upperChestInventory;
    private InventoryBase lowerChestInventory;
    private int inventoryRows;
    private String name;

    public GuiTreasureChest(final InventoryBase iinventory, final TileEntityTreasureChest iinventory1)
    {
        super(new Chest(iinventory, iinventory1));
        this.inventoryRows = 0;
        this.upperChestInventory = iinventory;
        this.lowerChestInventory = iinventory1;
        this.passEvents = false;
        final char c = '\u00de';
        final int i = c - 'l';
        this.inventoryRows = iinventory1.getInventorySize() / 9;
        this.containerHeight = i + this.inventoryRows * 18;

        switch (iinventory1.rarity)
        {
            case 1:
            {
                this.name = "Bronze Treasure Chest";
                break;
            }
            case 2:
            {
                this.name = "Silver Treasure Chest";
                break;
            }
            case 3:
            {
                this.name = "Gold Treasure Chest";
                break;
            }
            default:
            {
                this.name = "Treasure Chest";
            }
        }
    }

    @Override
    protected void renderForeground()
    {
        this.textManager.drawText(this.name, 8, 6, 4210752);
        this.textManager.drawText(this.upperChestInventory.getContainerName(), 8, this.containerHeight - 96 + 2, 4210752);
    }

    @Override
    protected void renderContainerBackground(final float tickDelta)
    {
        final int i = this.minecraft.textureManager.getTextureId("/gui/container.png");
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        this.minecraft.textureManager.bindTexture(i);
        final int j = (this.width - this.containerWidth) / 2;
        final int k = (this.height - this.containerHeight) / 2;
        this.blit(j, k, 0, 0, this.containerWidth, this.inventoryRows * 18 + 17);
        this.blit(j, k + this.inventoryRows * 18 + 17, 0, 126, this.containerWidth, 96);
    }
}