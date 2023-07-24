package com.gildedgames.aether.entity.tile;

import com.gildedgames.aether.registry.AetherBlocks;
import com.gildedgames.aether.registry.AetherItems;
import com.gildedgames.aether.utils.Enchantment;
import net.minecraft.entity.player.PlayerBase;
import net.minecraft.inventory.InventoryBase;
import net.minecraft.item.ItemBase;
import net.minecraft.item.ItemInstance;
import net.minecraft.tileentity.TileEntityBase;
import net.minecraft.util.io.AbstractTag;
import net.minecraft.util.io.CompoundTag;
import net.minecraft.util.io.ListTag;

import java.util.ArrayList;
import java.util.List;

public class TileEntityEnchanter extends TileEntityBase implements InventoryBase
{
    private static List<Enchantment> enchantments;
    private ItemInstance[] enchanterItemStacks;
    public int enchantProgress;
    public int enchantPowerRemaining;
    public int enchantTimeForItem;
    private Enchantment currentEnchantment;

    public TileEntityEnchanter()
    {
        this.enchanterItemStacks = new ItemInstance[3];
        this.enchantProgress = 0;
        this.enchantPowerRemaining = 0;
        this.enchantTimeForItem = 0;
    }

    @Override
    public int getInventorySize()
    {
        return this.enchanterItemStacks.length;
    }

    @Override
    public ItemInstance getInventoryItem(final int index)
    {
        return this.enchanterItemStacks[index];
    }

    @Override
    public ItemInstance takeInventoryItem(final int index, final int count)
    {
        if (this.enchanterItemStacks[index] == null)
        {
            return null;
        }
        if (this.enchanterItemStacks[index].count <= count)
        {
            final ItemInstance itemstack = this.enchanterItemStacks[index];
            this.enchanterItemStacks[index] = null;
            return itemstack;
        }
        final ItemInstance itemstack2 = this.enchanterItemStacks[index].split(count);
        if (this.enchanterItemStacks[index].count == 0)
        {
            this.enchanterItemStacks[index] = null;
        }
        return itemstack2;
    }

    @Override
    public void setInventoryItem(final int slot, final ItemInstance stack)
    {
        this.enchanterItemStacks[slot] = stack;
        if (stack != null && stack.count > this.getMaxItemCount())
        {
            stack.count = this.getMaxItemCount();
        }
    }

    @Override
    public String getContainerName()
    {
        return "Enchanter";
    }

    @Override
    public void readIdentifyingData(final CompoundTag tag)
    {
        super.readIdentifyingData(tag);
        final ListTag nbttaglist = tag.getListTag("Items");
        this.enchanterItemStacks = new ItemInstance[this.getInventorySize()];
        for (int i = 0; i < nbttaglist.size(); ++i)
        {
            final CompoundTag nbttagcompound1 = (CompoundTag) nbttaglist.get(i);
            final byte byte0 = nbttagcompound1.getByte("Slot");
            if (byte0 >= 0 && byte0 < this.enchanterItemStacks.length)
            {
                this.enchanterItemStacks[byte0] = new ItemInstance(nbttagcompound1);
            }
        }
        this.enchantProgress = tag.getShort("BurnTime");
        this.enchantTimeForItem = tag.getShort("CookTime");
    }

    @Override
    public void writeIdentifyingData(final CompoundTag tag)
    {
        super.writeIdentifyingData(tag);
        tag.put("BurnTime", (short) this.enchantProgress);
        tag.put("CookTime", (short) this.enchantTimeForItem);
        final ListTag nbttaglist = new ListTag();
        for (int i = 0; i < this.enchanterItemStacks.length; ++i)
        {
            if (this.enchanterItemStacks[i] != null)
            {
                final CompoundTag nbttagcompound1 = new CompoundTag();
                nbttagcompound1.put("Slot", (byte) i);
                this.enchanterItemStacks[i].toTag(nbttagcompound1);
                nbttaglist.add(nbttagcompound1);
            }
        }
        tag.put("Items", (AbstractTag) nbttaglist);
    }

    @Override
    public int getMaxItemCount()
    {
        return 64;
    }

    public int getCookProgressScaled(final int i)
    {
        if (this.enchantTimeForItem == 0)
        {
            return 0;
        }
        return this.enchantProgress * i / this.enchantTimeForItem;
    }

    public int getBurnTimeRemainingScaled(final int i)
    {
        return this.enchantPowerRemaining * i / 500;
    }

    public boolean isBurning()
    {
        return this.enchantPowerRemaining > 0;
    }

    @Override
    public void tick()
    {
        if (this.enchantPowerRemaining > 0)
        {
            --this.enchantPowerRemaining;
            if (this.currentEnchantment != null)
            {
                ++this.enchantProgress;
            }
        }
        if (this.currentEnchantment != null && (this.enchanterItemStacks[0] == null || this.enchanterItemStacks[0].itemId != this.currentEnchantment.enchantFrom.itemId))
        {
            this.currentEnchantment = null;
            this.enchantProgress = 0;
        }
        if (this.currentEnchantment != null && this.enchantProgress >= this.currentEnchantment.enchantPowerNeeded)
        {
            if (this.enchanterItemStacks[2] == null)
            {
                this.setInventoryItem(2, new ItemInstance(this.currentEnchantment.enchantTo.getType(), 1, this.currentEnchantment.enchantTo.getDamage()));
            }
            else
            {
                this.setInventoryItem(2, new ItemInstance(this.currentEnchantment.enchantTo.getType(), this.getInventoryItem(2).count + 1, this.currentEnchantment.enchantTo.getDamage()));
            }
            this.takeInventoryItem(0, 1);
            this.enchantProgress = 0;
            this.currentEnchantment = null;
            this.enchantTimeForItem = 0;
        }
        if (this.enchantPowerRemaining <= 0 && this.currentEnchantment != null && this.getInventoryItem(1) != null && this.getInventoryItem(1).itemId == AetherItems.AmbrosiumShard.id)
        {
            this.enchantPowerRemaining += 500;
            this.takeInventoryItem(1, 1);
        }
        if (this.currentEnchantment == null)
        {
            final ItemInstance itemstack = this.getInventoryItem(0);
            for (int i = 0; i < TileEntityEnchanter.enchantments.size(); ++i)
            {
                if (itemstack != null && TileEntityEnchanter.enchantments.get(i) != null && itemstack.itemId == ((Enchantment) TileEntityEnchanter.enchantments.get(i)).enchantFrom.itemId)
                {
                    if (this.enchanterItemStacks[2] == null)
                    {
                        this.currentEnchantment = (Enchantment) TileEntityEnchanter.enchantments.get(i);
                        this.enchantTimeForItem = this.currentEnchantment.enchantPowerNeeded;
                    }
                    else if (this.enchanterItemStacks[2].itemId == ((Enchantment) TileEntityEnchanter.enchantments.get(i)).enchantTo.itemId && ((Enchantment) TileEntityEnchanter.enchantments.get(i)).enchantTo.getType().getMaxStackSize() > this.enchanterItemStacks[2].count)
                    {
                        this.currentEnchantment = (Enchantment) TileEntityEnchanter.enchantments.get(i);
                        this.enchantTimeForItem = this.currentEnchantment.enchantPowerNeeded;
                    }
                }
            }
        }
    }

    @Override
    public boolean canPlayerUse(final PlayerBase player)
    {
        return this.level.getTileEntity(this.x, this.y, this.z) == this && player.squaredDistanceTo(this.x + 0.5, this.y + 0.5, this.z + 0.5) <= 64.0;
    }

    public static void addEnchantment(final ItemInstance from, final ItemInstance to, final int i)
    {
        TileEntityEnchanter.enchantments.add(new Enchantment(from, to, i));
    }

    static
    {
        TileEntityEnchanter.enchantments = new ArrayList<Enchantment>();
        addEnchantment(new ItemInstance(AetherBlocks.GRAVITITE_ORE, 1), new ItemInstance(AetherBlocks.ENCHANTED_GRAVITITE, 1), 1000);
        addEnchantment(new ItemInstance(AetherItems.PickSkyroot, 1), new ItemInstance(AetherItems.PickSkyroot, 1), 250);
        addEnchantment(new ItemInstance(AetherItems.SwordSkyroot, 1), new ItemInstance(AetherItems.SwordSkyroot, 1), 250);
        addEnchantment(new ItemInstance(AetherItems.ShovelSkyroot, 1), new ItemInstance(AetherItems.ShovelSkyroot, 1), 200);
        addEnchantment(new ItemInstance(AetherItems.AxeSkyroot, 1), new ItemInstance(AetherItems.AxeSkyroot, 1), 200);
        addEnchantment(new ItemInstance(AetherItems.PickHolystone, 1), new ItemInstance(AetherItems.PickHolystone, 1), 600);
        addEnchantment(new ItemInstance(AetherItems.SwordHolystone, 1), new ItemInstance(AetherItems.SwordHolystone, 1), 600);
        addEnchantment(new ItemInstance(AetherItems.ShovelHolystone, 1), new ItemInstance(AetherItems.ShovelHolystone, 1), 500);
        addEnchantment(new ItemInstance(AetherItems.AxeHolystone, 1), new ItemInstance(AetherItems.AxeHolystone, 1), 500);
        addEnchantment(new ItemInstance(AetherItems.PickZanite, 1), new ItemInstance(AetherItems.PickZanite, 1), 2500);
        addEnchantment(new ItemInstance(AetherItems.SwordZanite, 1), new ItemInstance(AetherItems.SwordZanite, 1), 2500);
        addEnchantment(new ItemInstance(AetherItems.ShovelZanite, 1), new ItemInstance(AetherItems.ShovelZanite, 1), 2000);
        addEnchantment(new ItemInstance(AetherItems.AxeZanite, 1), new ItemInstance(AetherItems.AxeZanite, 1), 2000);
        addEnchantment(new ItemInstance(AetherItems.PickGravitite, 1), new ItemInstance(AetherItems.PickGravitite, 1), 6000);
        addEnchantment(new ItemInstance(AetherItems.SwordGravitite, 1), new ItemInstance(AetherItems.SwordGravitite, 1), 6000);
        addEnchantment(new ItemInstance(AetherItems.ShovelGravitite, 1), new ItemInstance(AetherItems.ShovelGravitite, 1), 5000);
        addEnchantment(new ItemInstance(AetherItems.AxeGravitite, 1), new ItemInstance(AetherItems.AxeGravitite, 1), 5000);
        addEnchantment(new ItemInstance(AetherItems.Dart, 1, 0), new ItemInstance(AetherItems.Dart, 1, 2), 250);
        addEnchantment(new ItemInstance(AetherItems.Bucket, 1, 2), new ItemInstance(AetherItems.Bucket, 1, 3), 1000);
        //addEnchantment(new ItemInstance(ItemBase.record13, 1), new ItemInstance(AetherItems.BlueMusicDisk, 1), 2500);
        //(new ItemInstance(ItemBase.recordCat, 1), new ItemInstance(AetherItems.BlueMusicDisk, 1), 2500);
        addEnchantment(new ItemInstance(ItemBase.leatherHelmet, 1), new ItemInstance(ItemBase.leatherHelmet, 1), 400);
        addEnchantment(new ItemInstance(ItemBase.leatherChestplate, 1), new ItemInstance(ItemBase.leatherChestplate, 1), 500);
        addEnchantment(new ItemInstance(ItemBase.leatherLeggings, 1), new ItemInstance(ItemBase.leatherLeggings, 1), 500);
        addEnchantment(new ItemInstance(ItemBase.leatherBoots, 1), new ItemInstance(ItemBase.leatherBoots, 1), 400);
        addEnchantment(new ItemInstance(ItemBase.woodPickaxe, 1), new ItemInstance(ItemBase.woodPickaxe, 1), 500);
        addEnchantment(new ItemInstance(ItemBase.woodShovel, 1), new ItemInstance(ItemBase.woodShovel, 1), 400);
        addEnchantment(new ItemInstance(ItemBase.woodSword, 1), new ItemInstance(ItemBase.woodSword, 1), 500);
        addEnchantment(new ItemInstance(ItemBase.woodAxe, 1), new ItemInstance(ItemBase.woodAxe, 1), 400);
        addEnchantment(new ItemInstance(ItemBase.woodHoe, 1), new ItemInstance(ItemBase.woodHoe, 1), 300);
        addEnchantment(new ItemInstance(ItemBase.stonePickaxe, 1), new ItemInstance(ItemBase.stonePickaxe, 1), 1000);
        addEnchantment(new ItemInstance(ItemBase.stoneShovel, 1), new ItemInstance(ItemBase.stoneShovel, 1), 750);
        addEnchantment(new ItemInstance(ItemBase.stoneSword, 1), new ItemInstance(ItemBase.stoneSword, 1), 1000);
        addEnchantment(new ItemInstance(ItemBase.stoneAxe, 1), new ItemInstance(ItemBase.stoneAxe, 1), 750);
        addEnchantment(new ItemInstance(ItemBase.stoneHoe, 1), new ItemInstance(ItemBase.stoneHoe, 1), 750);
        addEnchantment(new ItemInstance(ItemBase.ironHelmet, 1), new ItemInstance(ItemBase.ironHelmet, 1), 1500);
        addEnchantment(new ItemInstance(ItemBase.ironChestplate, 1), new ItemInstance(ItemBase.ironChestplate, 1), 2000);
        addEnchantment(new ItemInstance(ItemBase.ironLeggings, 1), new ItemInstance(ItemBase.ironLeggings, 1), 2000);
        addEnchantment(new ItemInstance(ItemBase.ironBoots, 1), new ItemInstance(ItemBase.ironBoots, 1), 1500);
        addEnchantment(new ItemInstance(ItemBase.ironPickaxe, 1), new ItemInstance(ItemBase.ironPickaxe, 1), 2500);
        addEnchantment(new ItemInstance(ItemBase.ironShovel, 1), new ItemInstance(ItemBase.ironShovel, 1), 2000);
        addEnchantment(new ItemInstance(ItemBase.ironSword, 1), new ItemInstance(ItemBase.ironSword, 1), 2500);
        addEnchantment(new ItemInstance(ItemBase.ironAxe, 1), new ItemInstance(ItemBase.ironAxe, 1), 1500);
        addEnchantment(new ItemInstance(ItemBase.ironHoe, 1), new ItemInstance(ItemBase.ironHoe, 1), 1500);
        addEnchantment(new ItemInstance(ItemBase.goldHelmet, 1), new ItemInstance(ItemBase.goldHelmet, 1), 1000);
        addEnchantment(new ItemInstance(ItemBase.goldChestplate, 1), new ItemInstance(ItemBase.goldChestplate, 1), 1200);
        addEnchantment(new ItemInstance(ItemBase.goldLeggings, 1), new ItemInstance(ItemBase.goldLeggings, 1), 1200);
        addEnchantment(new ItemInstance(ItemBase.goldBoots, 1), new ItemInstance(ItemBase.goldBoots, 1), 1000);
        addEnchantment(new ItemInstance(ItemBase.goldPickaxe, 1), new ItemInstance(ItemBase.goldPickaxe, 1), 1500);
        addEnchantment(new ItemInstance(ItemBase.goldShovel, 1), new ItemInstance(ItemBase.goldShovel, 1), 1000);
        addEnchantment(new ItemInstance(ItemBase.goldSword, 1), new ItemInstance(ItemBase.goldSword, 1), 1500);
        addEnchantment(new ItemInstance(ItemBase.goldAxe, 1), new ItemInstance(ItemBase.goldAxe, 1), 1000);
        addEnchantment(new ItemInstance(ItemBase.goldHoe, 1), new ItemInstance(ItemBase.goldHoe, 1), 1000);
        addEnchantment(new ItemInstance(ItemBase.diamondHelmet, 1), new ItemInstance(ItemBase.diamondHelmet, 1), 5000);
        addEnchantment(new ItemInstance(ItemBase.diamondChestplate, 1), new ItemInstance(ItemBase.diamondChestplate, 1), 6000);
        addEnchantment(new ItemInstance(ItemBase.diamondLeggings, 1), new ItemInstance(ItemBase.diamondLeggings, 1), 6000);
        addEnchantment(new ItemInstance(ItemBase.diamondBoots, 1), new ItemInstance(ItemBase.diamondBoots, 1), 5000);
        addEnchantment(new ItemInstance(ItemBase.diamondPickaxe, 1), new ItemInstance(ItemBase.diamondPickaxe, 1), 7000);
        addEnchantment(new ItemInstance(ItemBase.diamondShovel, 1), new ItemInstance(ItemBase.diamondShovel, 1), 6000);
        addEnchantment(new ItemInstance(ItemBase.diamondSword, 1), new ItemInstance(ItemBase.diamondSword, 1), 6500);
        addEnchantment(new ItemInstance(ItemBase.diamondAxe, 1), new ItemInstance(ItemBase.diamondAxe, 1), 6000);
        addEnchantment(new ItemInstance(ItemBase.diamondHoe, 1), new ItemInstance(ItemBase.diamondHoe, 1), 6000);
        addEnchantment(new ItemInstance(ItemBase.fishingRod, 1), new ItemInstance(ItemBase.fishingRod, 1), 500);
        addEnchantment(new ItemInstance(AetherBlocks.QUICKSOIL, 1), new ItemInstance(AetherBlocks.QUICKSOIL_GLASS, 1), 250);
        addEnchantment(new ItemInstance(AetherBlocks.HOLYSTONE, 1), new ItemInstance(AetherItems.HealingStone, 1), 750);
        addEnchantment(new ItemInstance(AetherItems.GravititeHelmet, 1), new ItemInstance(AetherItems.GravititeHelmet, 1), 12000);
        addEnchantment(new ItemInstance(AetherItems.GravititeBodyplate, 1), new ItemInstance(AetherItems.GravititeBodyplate, 1), 20000);
        addEnchantment(new ItemInstance(AetherItems.GravititePlatelegs, 1), new ItemInstance(AetherItems.GravititePlatelegs, 1), 15000);
        addEnchantment(new ItemInstance(AetherItems.GravititeBoots, 1), new ItemInstance(AetherItems.GravititeBoots, 1), 12000);
        addEnchantment(new ItemInstance(AetherItems.GravititeGlove, 1), new ItemInstance(AetherItems.GravititeGlove, 1), 10000);
        addEnchantment(new ItemInstance(AetherItems.ZaniteHelmet, 1), new ItemInstance(AetherItems.ZaniteHelmet, 1), 6000);
        addEnchantment(new ItemInstance(AetherItems.ZaniteChestplate, 1), new ItemInstance(AetherItems.ZaniteChestplate, 1), 10000);
        addEnchantment(new ItemInstance(AetherItems.ZaniteLeggings, 1), new ItemInstance(AetherItems.ZaniteLeggings, 1), 8000);
        addEnchantment(new ItemInstance(AetherItems.ZaniteBoots, 1), new ItemInstance(AetherItems.ZaniteBoots, 1), 5000);
        addEnchantment(new ItemInstance(AetherItems.ZaniteGlove, 1), new ItemInstance(AetherItems.ZaniteGlove, 1), 4000);
        addEnchantment(new ItemInstance(AetherItems.ZaniteRing, 1), new ItemInstance(AetherItems.ZaniteRing, 1), 2000);
        addEnchantment(new ItemInstance(AetherItems.ZanitePendant, 1), new ItemInstance(AetherItems.ZanitePendant, 1), 2000);
        addEnchantment(new ItemInstance(AetherItems.LeatherGlove, 1), new ItemInstance(AetherItems.LeatherGlove, 1), 300);
        addEnchantment(new ItemInstance(AetherItems.IronGlove, 1), new ItemInstance(AetherItems.IronGlove, 1), 1200);
        addEnchantment(new ItemInstance(AetherItems.GoldGlove, 1), new ItemInstance(AetherItems.GoldGlove, 1), 800);
        addEnchantment(new ItemInstance(AetherItems.DiamondGlove, 1), new ItemInstance(AetherItems.DiamondGlove, 1), 4000);
        addEnchantment(new ItemInstance(AetherItems.DartShooter, 1, 0), new ItemInstance(AetherItems.DartShooter, 1, 2), 2000);
    }
}
