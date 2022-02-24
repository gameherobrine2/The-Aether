package com.gildedgames.aether.block;
import net.minecraft.item.ItemInstance;
import net.minecraft.tileentity.TileEntityBase;
import net.minecraft.client.gui.screen.ScreenBase;
import net.minecraft.inventory.InventoryBase;
import net.minecraft.tileentity.TileEntityChest;
import net.modificationstation.stationapi.api.registry.Identifier;
import net.modificationstation.stationapi.api.template.block.TemplateBlockWithEntity;
import net.minecraft.entity.player.PlayerBase;
import net.minecraft.level.Level;
import net.minecraft.block.BlockBase;
import net.minecraft.level.BlockView;
import net.minecraft.block.material.Material;
import java.util.Random;

import com.gildedgames.aether.event.listener.TextureListener;

import net.minecraft.block.BlockWithEntity;

public class BlockTreasureChest extends TemplateBlockWithEntity {
    private Random random;
    
    public BlockTreasureChest(final Identifier id) {
        super(id, Material.STONE);
        this.random = new Random();
    }
    
    @Override
    public int getDropCount(final Random rand) {
        return 0;
    }
    
    @Override
    public int getTextureForSide(final BlockView tileView, final int x, final int y, final int z, final int meta) {
        if (meta == 1) {
            return 62;
        }
        if (meta == 0) {
            return 62;
        }
        final int i1 = tileView.getTileId(x, y, z - 1);
        final int j1 = tileView.getTileId(x, y, z + 1);
        final int k1 = tileView.getTileId(x - 1, y, z);
        final int l1 = tileView.getTileId(x + 1, y, z);
        byte byte0 = 3;
        if (BlockBase.FULL_OPAQUE[i1] && !BlockBase.FULL_OPAQUE[j1]) {
            byte0 = 3;
        }
        if (BlockBase.FULL_OPAQUE[j1] && !BlockBase.FULL_OPAQUE[i1]) {
            byte0 = 2;
        }
        if (BlockBase.FULL_OPAQUE[k1] && !BlockBase.FULL_OPAQUE[l1]) {
            byte0 = 5;
        }
        if (BlockBase.FULL_OPAQUE[l1] && !BlockBase.FULL_OPAQUE[k1]) {
            byte0 = 4;
        }
        return (meta != byte0) ? TextureListener.sprChestSide : TextureListener.sprChestFront;
    }
    
    @Override
    public int getTextureForSide(final int side) {
        if (side == 1) {
            return 62;
        }
        if (side == 0) {
            return 62;
        }
        if (side == 3) {
            return TextureListener.sprChestFront;
        }
        return TextureListener.sprChestSide;
    }
    
    @Override
    public boolean canPlaceAt(final Level level, final int x, final int y, final int z) {
        return false;
    }
    
    @Override
    public boolean canUse(final Level level, final int x, final int y, final int z, final PlayerBase player) {
        /*//if (level.isServerSide) {
        //    return true;
        //}
        final int meta = level.getTileMeta(x, y, z);
        final TileEntityChest chest = (TileEntityChest)level.getTileEntity(x, y, z);
        if (meta % 2 == 1) {
            ModLoader.OpenGUI(player, (ScreenBase)new GuiTreasureChest(player.inventory, chest, meta));
            return true;
        }
        final ItemInstance itemstack = player.inventory.getHeldItem();
        if (itemstack != null && itemstack.itemId == AetherItems.Key.id && itemstack.getDamage() == 0 && meta == 0) {
            player.inventory.takeInventoryItem(player.inventory.selectedHotbarSlot, 1);
            level.method_223(x, y, z, meta + 1);
            level.setTileEntity(x, y, z, chest);
            return true;
        }
        if (itemstack != null && itemstack.itemId == AetherItems.Key.id && itemstack.getDamage() == 1 && meta == 2) {
            player.inventory.takeInventoryItem(player.inventory.selectedHotbarSlot, 1);
            level.method_223(x, y, z, meta + 1);
            level.setTileEntity(x, y, z, chest);
            return true;
        }
        if (itemstack != null && itemstack.itemId == AetherItems.Key.id && itemstack.getDamage() == 2 && meta == 4) {
            player.inventory.takeInventoryItem(player.inventory.selectedHotbarSlot, 1);
            level.method_223(x, y, z, meta + 1);
            level.setTileEntity(x, y, z, chest);
            return true;
        }
        return false;*/ //TODO: fix all stuff here(too lazy to do it now)
    	return true;
    }
    
    @Override
    protected TileEntityBase createTileEntity() {
        return new TileEntityChest();
    }
}
