package com.gildedgames.aether.block;

import com.gildedgames.aether.entity.tile.TileEntityTreasureChest;
import com.gildedgames.aether.event.listener.TextureListener;
import com.gildedgames.aether.gui.container.ContainerTreasureChest;
import com.gildedgames.aether.registry.AetherBlocks;
import com.gildedgames.aether.registry.AetherItems;
import net.minecraft.block.BlockBase;
import net.minecraft.entity.player.PlayerBase;
import net.minecraft.item.ItemInstance;
import net.minecraft.level.BlockView;
import net.minecraft.level.Level;
import net.minecraft.tileentity.TileEntityBase;
import net.modificationstation.stationapi.api.gui.screen.container.GuiHelper;
import net.modificationstation.stationapi.api.registry.Identifier;
import net.modificationstation.stationapi.api.template.block.TemplateChest;

import java.util.Random;

import static com.gildedgames.aether.AetherMod.of;

public class BlockTreasureChest extends TemplateChest
{
    public BlockTreasureChest(Identifier identifier)
    {
        super(identifier);
    }

    @Override
    public int getTextureForSide(final BlockView tileView, final int x, final int y, final int z, final int meta)
    {
        if (meta == 1)
        {
            return 62;
        }
        if (meta == 0)
        {
            return 62;
        }
        final int i1 = tileView.getTileId(x, y, z - 1);
        final int j1 = tileView.getTileId(x, y, z + 1);
        final int k1 = tileView.getTileId(x - 1, y, z);
        final int l1 = tileView.getTileId(x + 1, y, z);
        byte byte0 = 3;
        if (BlockBase.FULL_OPAQUE[i1] && !BlockBase.FULL_OPAQUE[j1])
        {
            byte0 = 3;
        }
        if (BlockBase.FULL_OPAQUE[j1] && !BlockBase.FULL_OPAQUE[i1])
        {
            byte0 = 2;
        }
        if (BlockBase.FULL_OPAQUE[k1] && !BlockBase.FULL_OPAQUE[l1])
        {
            byte0 = 5;
        }
        if (BlockBase.FULL_OPAQUE[l1] && !BlockBase.FULL_OPAQUE[k1])
        {
            byte0 = 4;
        }
        return (meta != byte0) ? TextureListener.sprChestSide : TextureListener.sprChestFront;
    }

    @Override
    public int getTextureForSide(final int side)
    {
        if (side == 1)
        {
            return 62;
        }
        if (side == 0)
        {
            return 62;
        }
        if (side == 3)
        {
            return TextureListener.sprChestFront;
        }
        return TextureListener.sprChestSide;
    }

    @Override
    public void onBlockPlaced(Level level, int x, int y, int z, int l)
    {
        Random rand = new Random();
        super.onBlockPlaced(level, x, y, z, l);
        TileEntityBase tileEntity = level.getTileEntity(x, y, z);
        if (tileEntity instanceof TileEntityTreasureChest treasureChest)
        {
            if (rand.nextBoolean())
            {
                level.setTileMeta(x, y, z, 5);
            }
            else
            {
                treasureChest.setRarity(rand.nextInt(4));
                if (treasureChest.rarity == 0)
                    level.setTileMeta(x, y, z, 0);
                else
                    level.setTileMeta(x, y, z, 1);
            }
        }
    }

    @Override
    public void onBlockRemoved(Level arg, int i, int j, int k)
    {
        if (arg.getTileMeta(i, j, k) == 0) super.onBlockRemoved(arg, i, j, k);
    }

    private ItemInstance getGoldLoot(final Random random)
    {
        final int item = random.nextInt(8);
        switch (item)
        {
            case 0:
            {
                return new ItemInstance(AetherItems.IronBubble);
            }
            case 1:
            {
                return new ItemInstance(AetherItems.VampireBlade);
            }
            case 2:
            {
                return new ItemInstance(AetherItems.PigSlayer);
            }
            case 3:
            {
                if (random.nextBoolean())
                {
                    return new ItemInstance(AetherItems.PhoenixHelm);
                }
                if (random.nextBoolean())
                {
                    return new ItemInstance(AetherItems.PhoenixLegs);
                }
                if (random.nextBoolean())
                {
                    return new ItemInstance(AetherItems.PhoenixBody);
                }
                break;
            }
            case 4:
            {
                if (random.nextBoolean())
                {
                    return new ItemInstance(AetherItems.PhoenixBoots);
                }
                return new ItemInstance(AetherItems.PhoenixGlove);
            }
            case 5:
            {
                return new ItemInstance(AetherItems.LifeShard);
            }
            case 6:
            {
                if (random.nextBoolean())
                {
                    return new ItemInstance(AetherItems.GravititeHelmet);
                }
                if (random.nextBoolean())
                {
                    return new ItemInstance(AetherItems.GravititePlatelegs);
                }
                if (random.nextBoolean())
                {
                    return new ItemInstance(AetherItems.GravititeBodyplate);
                }
                break;
            }
            case 7:
            {
                if (random.nextBoolean())
                {
                    return new ItemInstance(AetherItems.GravititeBoots);
                }
                return new ItemInstance(AetherItems.GravititeGlove);
            }
        }
        //return null;
        return new ItemInstance(AetherItems.ObsidianBody);
    }

    private ItemInstance getSilverLoot(final Random random)
    {
        final int item = random.nextInt(9);
        switch (item)
        {
            case 0:
            {
                return new ItemInstance(AetherItems.GummieSwet, random.nextInt(16));
            }
            case 1:
            {
                return new ItemInstance(AetherItems.SwordLightning);
            }
            case 2:
            {
                if (random.nextBoolean())
                {
                    return new ItemInstance(AetherItems.AxeValkyrie);
                }
                if (random.nextBoolean())
                {
                    return new ItemInstance(AetherItems.ShovelValkyrie);
                }
                if (random.nextBoolean())
                {
                    return new ItemInstance(AetherItems.PickValkyrie);
                }
                break;
            }
            case 3:
            {
                return new ItemInstance(AetherItems.SwordHoly);
            }
            case 4:
            {
                return new ItemInstance(AetherItems.GoldenFeather);
            }
            case 5:
            {
                return new ItemInstance(AetherItems.RegenerationStone);
            }
            case 6:
            {
                if (random.nextBoolean())
                {
                    return new ItemInstance(AetherItems.NeptuneHelmet);
                }
                if (random.nextBoolean())
                {
                    return new ItemInstance(AetherItems.NeptuneLeggings);
                }
                if (random.nextBoolean())
                {
                    return new ItemInstance(AetherItems.NeptuneChestplate);
                }
                break;
            }
            case 7:
            {
                if (random.nextBoolean())
                {
                    return new ItemInstance(AetherItems.NeptuneBoots);
                }
                return new ItemInstance(AetherItems.NeptuneGlove);
            }
            case 8:
            {
                return new ItemInstance(AetherItems.InvisibilityCloak);
            }
        }
        return new ItemInstance(AetherItems.ZanitePendant);
    }

    private ItemInstance getBronzeLoot(final Random random)
    {
        final int item = random.nextInt(7);
        switch (item)
        {
            case 0:
            {
                return new ItemInstance(AetherItems.GummieSwet, random.nextInt(8), random.nextInt(2));
            }
            case 1:
            {
                return new ItemInstance(AetherItems.PhoenixBow);
            }
            case 2:
            {
                return new ItemInstance(AetherItems.SwordFire);
            }
            case 3:
            {
                return new ItemInstance(AetherItems.HammerNotch);
            }
            case 4:
            {
                return new ItemInstance(AetherItems.LightningKnife, random.nextInt(16));
            }
            case 5:
            {
                return new ItemInstance(AetherItems.Lance);
            }
            case 6:
            {
                return new ItemInstance(AetherItems.AgilityCape);
            }
            default:
            {
                return new ItemInstance(AetherItems.Stick);
            }
        }
    }


    @Override
    public boolean canUse(final Level level, final int x, final int y, final int z, final PlayerBase player)
    {
        final int meta = level.getTileMeta(x, y, z);
        TileEntityBase tileEntity = level.getTileEntity(x, y, z);
        if (tileEntity instanceof TileEntityTreasureChest treasureChest)
        {
            if (meta == 5)
            {
                GuiHelper.openGUI(player, of("treasure_chest"), treasureChest, new ContainerTreasureChest(player.inventory, treasureChest));
                return true;
            }
            if (meta > 1)
            {
                ((TileEntityTreasureChest) tileEntity).setRarity(meta - 1);
                level.setTileMeta(x, y, z, 1);
            }
            if (meta == 0)
            {
                GuiHelper.openGUI(player, of("treasure_chest"), treasureChest, new ContainerTreasureChest(player.inventory, treasureChest));
                return true;
            }
            if (level.isServerSide)
            {
                return true;
            }
            final ItemInstance itemstack = player.inventory.getHeldItem();
            if (itemstack != null && meta == 1)
            {
                if (itemstack.itemId == AetherItems.Key.id && itemstack.getDamage() + 1 == treasureChest.rarity)
                {
                    player.inventory.takeInventoryItem(player.inventory.selectedHotbarSlot, 1);
                    level.setTileMeta(x, y, z, 0);
                    Random rand = new Random();
                    switch (treasureChest.rarity)
                    {
                        case 0:
                            break;
                        case 1:
                            for (int p = 0; p < 3 + rand.nextInt(3); ++p)
                            {
                                final ItemInstance item = this.getBronzeLoot(rand);
                                treasureChest.setInventoryItem(rand.nextInt(treasureChest.getInventorySize()), item);
                            }
                            break;
                        case 2:
                            for (int p = 0; p < 3 + rand.nextInt(3); ++p)
                            {
                                final ItemInstance item = this.getSilverLoot(rand);
                                treasureChest.setInventoryItem(rand.nextInt(treasureChest.getInventorySize()), item);
                            }
                            break;
                        case 3:
                            for (int p = 0; p < 3 + rand.nextInt(3); ++p)
                            {
                                final ItemInstance item = this.getGoldLoot(rand);
                                treasureChest.setInventoryItem(rand.nextInt(treasureChest.getInventorySize()), item);
                            }
                            break;
                    }
                }
            }
        }
        return false;
    }

    public static void PlaceTreasureChest(Level level, int x, int y, int z, int rarity)
    {
        // todo: make sure this works
        level.setTileWithMetadata(x, y, z, AetherBlocks.TREASURE_CHEST.id, 1 + rarity);
    }

    @Override
    protected TileEntityBase createTileEntity()
    {
        return new TileEntityTreasureChest();
    }
}
