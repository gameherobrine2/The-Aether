package com.gildedgames.aether.block;

import com.gildedgames.aether.registry.AetherBlocks;
import com.gildedgames.aether.registry.AetherItems;
import net.minecraft.entity.player.PlayerBase;
import net.minecraft.item.ItemBase;
import net.minecraft.item.ItemInstance;
import net.minecraft.level.Level;
import net.minecraft.tileentity.TileEntityChest;
import net.modificationstation.stationapi.api.registry.Identifier;
import net.modificationstation.stationapi.api.template.block.TemplateChest;

import java.util.Random;

public class AetherChest extends TemplateChest
{
    Random rand;

    public AetherChest(Identifier identifier)
    {
        super(identifier);
        rand = new Random();
    }

    private ItemInstance getNormalLoot(final Random random)
    {
        final int item = random.nextInt(15);
        switch (item)
        {
            case 0:
            {
                return new ItemInstance(AetherItems.PickZanite);
            }
            case 1:
            {
                return new ItemInstance(AetherItems.Bucket, 1, 2);
            }
            case 2:
            {
                return new ItemInstance(AetherItems.DartShooter);
            }
            case 3:
            {
                return new ItemInstance(AetherItems.MoaEgg, 1, 0);
            }
            case 4:
            {
                return new ItemInstance(AetherItems.AmbrosiumShard, random.nextInt(10) + 1);
            }
            case 5:
            {
                return new ItemInstance(AetherItems.Dart, random.nextInt(5) + 1, 0);
            }
            case 6:
            {
                return new ItemInstance(AetherItems.Dart, random.nextInt(3) + 1, 1);
            }
            case 7:
            {
                return new ItemInstance(AetherItems.Dart, random.nextInt(3) + 1, 2);
            }
            case 8:
            {
                if (random.nextInt(20) == 0)
                {
                    return new ItemInstance(AetherItems.BlueMusicDisk);
                }
                break;
            }
            case 9:
            {
                return new ItemInstance(AetherItems.Bucket);
            }
            case 10:
            {
                if (random.nextInt(10) == 0)
                {
                    return new ItemInstance(ItemBase.byId[ItemBase.record13.id + random.nextInt(2)]);
                }
                break;
            }
            case 11:
            {
                if (random.nextInt(2) == 0)
                {
                    return new ItemInstance(AetherItems.ZaniteBoots);
                }
                if (random.nextInt(2) == 0)
                {
                    return new ItemInstance(AetherItems.ZaniteHelmet);
                }
                if (random.nextInt(2) == 0)
                {
                    return new ItemInstance(AetherItems.ZaniteLeggings);
                }
                if (random.nextInt(2) == 0)
                {
                    return new ItemInstance(AetherItems.ZaniteChestplate);
                }
                break;
            }
            case 12:
            {
                if (random.nextInt(4) == 0)
                {
                    return new ItemInstance(AetherItems.IronPendant);
                }
            }
            case 13:
            {
                if (random.nextInt(10) == 0)
                {
                    return new ItemInstance(AetherItems.GoldPendant);
                }
            }
            case 14:
            {
                if (random.nextInt(15) == 0)
                {
                    return new ItemInstance(AetherItems.ZaniteRing);
                }
                break;
            }
        }
        return new ItemInstance(AetherBlocks.AMBROSIUM_TORCH, random.nextInt(5));
    }

    @Override
    public boolean canUse(Level level, int x, int y, int z, PlayerBase player)
    {
        level.setTile(x,y,z, CHEST.id);

        final TileEntityChest chest = (TileEntityChest) level.getTileEntity(x,y,z);
        for (int i = 0; i < 3 + rand.nextInt(3); ++i)
        {
            final ItemInstance item = this.getNormalLoot(rand);
            chest.setInventoryItem(rand.nextInt(chest.getInventorySize()), item);
        }

        return CHEST.canUse(level, x, y, z, player);
    }

    @Override
    public int getDropId(int i, Random random)
    {
        return CHEST.id;
    }

    @Override
    public void onBlockPlaced(Level arg, int i, int j, int k)
    {
        
    }
}
