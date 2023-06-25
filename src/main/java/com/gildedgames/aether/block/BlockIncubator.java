package com.gildedgames.aether.block;

import net.minecraft.entity.Item;
import net.minecraft.item.ItemInstance;
import net.minecraft.util.maths.MathHelper;
import net.modificationstation.stationapi.api.registry.Identifier;
import net.modificationstation.stationapi.api.template.block.TemplateBlockWithEntity;
import net.minecraft.entity.Living;
import net.minecraft.tileentity.TileEntityBase;
import net.minecraft.entity.player.PlayerBase;
import net.minecraft.block.BlockBase;
import net.minecraft.level.Level;
import net.minecraft.block.material.Material;
import java.util.Random;

import com.gildedgames.aether.entity.tile.TileEntityIncubator;
import com.gildedgames.aether.event.listener.TextureListener;
import com.gildedgames.aether.gui.GuiIncubator;
import com.gildedgames.aether.mixin.access.MinecraftClientAccessor;

public class BlockIncubator extends TemplateBlockWithEntity {
    private Random IncubatorRand;
    
    
    public BlockIncubator(final Identifier blockID) {
        super(blockID, Material.STONE);
        
        this.IncubatorRand = new Random();
    }
    
    @Override
    public void onBlockPlaced(final Level level, final int x, final int y, final int z) {
        super.onBlockPlaced(level, x, y, z);
        this.setDefaultDirection(level, x, y, z);
    }
    
    private void setDefaultDirection(final Level world, final int i, final int j, final int k) {
        if (world.isServerSide) {
            return;
        }
        final int l = world.getTileId(i, j, k - 1);
        final int i2 = world.getTileId(i, j, k + 1);
        final int j2 = world.getTileId(i - 1, j, k);
        final int k2 = world.getTileId(i + 1, j, k);
        byte byte0 = 3;
        if (BlockBase.FULL_OPAQUE[l] && !BlockBase.FULL_OPAQUE[i2]) {
            byte0 = 3;
        }
        if (BlockBase.FULL_OPAQUE[i2] && !BlockBase.FULL_OPAQUE[l]) {
            byte0 = 2;
        }
        if (BlockBase.FULL_OPAQUE[j2] && !BlockBase.FULL_OPAQUE[k2]) {
            byte0 = 5;
        }
        if (BlockBase.FULL_OPAQUE[k2] && !BlockBase.FULL_OPAQUE[j2]) {
            byte0 = 4;
        }
        world.setTileMeta(i, j, k, byte0);
    }
    
    @Override
    public int getTextureForSide(final int side) {
        if (side == 1) {
            return TextureListener.sprIncubatorTop;
        }
        if (side == 0) {
            return TextureListener.sprIncubatorTop;
        }
        return TextureListener.sprIncubatorSide;
    }
    
    @Override
    public boolean canUse(final Level level, final int x, final int y, final int z, final PlayerBase player) {
        if (level.isServerSide) {
            return true;
        }
        final TileEntityIncubator tileentityIncubator = (TileEntityIncubator)level.getTileEntity(x, y, z);
        MinecraftClientAccessor.getMCinstance().openScreen(new GuiIncubator(player.inventory, tileentityIncubator));
        //TODO: ModLoader.OpenGUI(player, (ScreenBase)new GuiIncubator(player.inventory, tileentityIncubator));
        return true;
    }
    
    public static void updateIncubatorBlockState(final boolean flag, final Level world, final int i, final int j, final int k) {
        final int l = world.getTileMeta(i, j, k);
        final TileEntityBase tileentity = world.getTileEntity(i, j, k);
        world.setTileMeta(i, j, k, l);
        world.setTileEntity(i, j, k, tileentity);
    }
    
    @Override
    protected TileEntityBase createTileEntity() {
        return new TileEntityIncubator();
    }
    
    @Override
    public void afterPlaced(final Level level, final int x, final int y, final int z, final Living living) {
        final int l = MathHelper.floor(living.yaw * 4.0f / 360.0f + 0.5) & 0x3;
        if (l == 0) {
            level.setTileMeta(x, y, z, 2);
        }
        if (l == 1) {
            level.setTileMeta(x, y, z, 5);
        }
        if (l == 2) {
            level.setTileMeta(x, y, z, 3);
        }
        if (l == 3) {
            level.setTileMeta(x, y, z, 4);
        }
    }
    
    @Override
    public void onBlockRemoved(final Level level, final int x, final int y, final int z) {
        final TileEntityIncubator tileentityIncubator = (TileEntityIncubator)level.getTileEntity(x, y, z);
        for (int l = 0; l < tileentityIncubator.getInventorySize(); ++l) {
            final ItemInstance itemstack = tileentityIncubator.getInventoryItem(l);
            if (itemstack != null) {
                final float f = this.IncubatorRand.nextFloat() * 0.8f + 0.1f;
                final float f2 = this.IncubatorRand.nextFloat() * 0.8f + 0.1f;
                final float f3 = this.IncubatorRand.nextFloat() * 0.8f + 0.1f;
                while (itemstack.count > 0) {
                    int i1 = this.IncubatorRand.nextInt(21) + 10;
                    if (i1 > itemstack.count) {
                        i1 = itemstack.count;
                    }
                    final ItemInstance itemInstance = itemstack;
                    itemInstance.count -= i1;
                    final Item entityitem = new Item(level, x + f, y + f2, z + f3, new ItemInstance(itemstack.itemId, i1, itemstack.getDamage()));
                    final float f4 = 0.05f;
                    entityitem.velocityX = (float)this.IncubatorRand.nextGaussian() * f4;
                    entityitem.velocityY = (float)this.IncubatorRand.nextGaussian() * f4 + 0.2f;
                    entityitem.velocityZ = (float)this.IncubatorRand.nextGaussian() * f4;
                    level.spawnEntity(entityitem);
                }
            }
        }
        super.onBlockRemoved(level, x, y, z);
    }
}
