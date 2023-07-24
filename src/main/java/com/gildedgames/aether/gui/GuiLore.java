package com.gildedgames.aether.gui;

import com.gildedgames.aether.AetherMod;
import com.gildedgames.aether.gui.container.ContainerLore;
import com.gildedgames.aether.registry.AetherAchievements;
import com.gildedgames.aether.registry.AetherBlocks;
import com.gildedgames.aether.registry.AetherItems;
import com.gildedgames.aether.utils.Lore;
import net.minecraft.block.BlockBase;
import net.minecraft.client.gui.screen.container.ContainerBase;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemBase;
import net.minecraft.item.ItemInstance;
import org.lwjgl.opengl.GL11;

import java.util.ArrayList;

public class GuiLore extends ContainerBase
{

    public GuiLore(net.minecraft.container.ContainerBase container2)
    {
        super(container2);
    }

    public static ArrayList<Lore> lores;
    private int type;

    public GuiLore(final PlayerInventory inventoryplayer, final int i)
    {
        super(new ContainerLore(inventoryplayer));
        this.type = i;
    }

    @Override
    protected void renderForeground()
    {
        this.textManager.drawText("Book Of Lore", 37, 18, 4210752);
        this.textManager.drawText(new StringBuilder().append("Volume ").append(this.type + 1).toString(), 47, 28, 4210752);
        switch (this.type)
        {
            case 0:
            {
                this.textManager.drawText("The Surface", 37, 38, 4210752);
                break;
            }
            case 1:
            {
                this.textManager.drawText("The Nether", 37, 38, 4210752);
                break;
            }
            case 2:
            {
                this.textManager.drawText("The Aether", 37, 38, 4210752);
                break;
            }
        }
        this.textManager.drawText("Item : ", 46, 72, 4210752);
        final ItemInstance item = ((ContainerLore) this.container).loreSlot.getInventoryItem(0);
        if (item != null)
        {
            for (final Lore lore : GuiLore.lores)
            {
                if (lore.equals(item) && lore.type == this.type)
                {
                    this.textManager.drawText(lore.name, 134, 14, 4210752);
                    this.textManager.drawText(lore.line1, 134, 28, 4210752);
                    this.textManager.drawText(lore.line2, 134, 38, 4210752);
                    this.textManager.drawText(lore.line3, 134, 48, 4210752);
                    this.textManager.drawText(lore.line4, 134, 58, 4210752);
                    this.textManager.drawText(lore.line5, 134, 68, 4210752);
                    this.textManager.drawText(lore.line6, 134, 78, 4210752);
                    AetherMod.giveAchievement(AetherAchievements.lore);
                    if (item.itemId == AetherItems.LoreBook.id)
                    {
                        AetherMod.giveAchievement(AetherAchievements.loreception);
                        break;
                    }
                    break;
                }
            }
        }
    }

    @Override
    public void onClose()
    {
        super.onClose();
        this.container.onClosed(this.minecraft.player);
    }

    @Override
    protected void renderContainerBackground(final float tickDelta)
    {
        this.containerWidth = 256;
        this.containerHeight = 195;
        final int i = this.minecraft.textureManager.getTextureId("/assets/aether/textures/gui/lore.png");
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        this.minecraft.textureManager.bindTexture(i);
        final int j = (this.width - this.containerWidth) / 2;
        final int k = (this.height - this.containerHeight) / 2;
        this.blit(j, k, 0, 0, this.containerWidth, this.containerHeight);
    }

    /* todo: Add these to lore book:
	(pulled from lang file)
    item.IronGlove.name=Iron Glove
    item.GoldGlove.name=Gold Glove
    item.DiamondGlove.name=Diamond Glove
    item.WhiteCape.name=White Cape
    item.AetherCape.name=Aether Cape
	item.LeatherGlove.name=Leather Glove
    item.IronGlove.name=Iron Glove
    item.GoldGlove.name=Gold Glove
    item.DiamondGlove.name=Diamond Glove
	item.IronPendant.name=Iron Pendant
    item.GoldPendant.name=Gold Pendant
	item.IronRing.name=Iron Ring
    item.GoldRing.name=Gold Ring
    tile.SkyrootLeaves.name=Skyroot Leaves
    tile.GoldenOakLog.name=Golden Oak Log
    tile.GoldenOakLeaves.name=Golden Oak Leaves
*/

    static
    {
        (GuiLore.lores = new ArrayList<Lore>()).add(new Lore(BlockBase.STONE, "Stone", "Found everywhere.", "Makes steps", "", "", "", "", 0));
        GuiLore.lores.add(new Lore((BlockBase) BlockBase.GRASS, "Grass", "Found in light.", "Spreads to dirt.", "Flowers and trees", "will grow on it.", "Click with a hoe", "to make farmland", 0));
        GuiLore.lores.add(new Lore(BlockBase.DIRT, "Dirt", "Found everywhere.", "Grass, trees and", "flowers will grow", "on it.", "Click with a hoe", "to make farmland", 0));
        GuiLore.lores.add(new Lore(BlockBase.COBBLESTONE, "Cobblestone", "Found when mining", "stone and when", "water meets lava.", "Makes stone tools,", "cobble steps and", "furnaces", 0));
        GuiLore.lores.add(new Lore(BlockBase.WOOD, "Wooden Planks", "Crafted from wood.", "Useful building", "material.", "Makes sticks, tools,", "boats, doors, chests", "and crafting tables", 0));
        GuiLore.lores.add(new Lore(BlockBase.SAPLING, "Sapling", "Dropped by leaves.", "Grows a tree.", "", "", "", "", 0));
        GuiLore.lores.add(new Lore(BlockBase.BEDROCK, "Bedrock / Adminium", "Not obtainable...", "The hardest known", "material; completely", "indestructible.", "", "", 0));
        GuiLore.lores.add(new Lore(BlockBase.SAND, "Sand", "Found by water.", "Falls when ", "unsupported.", "Smeltable into glass", "", "", 0));
        GuiLore.lores.add(new Lore(BlockBase.GRAVEL, "Gravel", "Found in rock.", "Falls when ", "unsupported.", "Chance to drop flint", "", "", 0));
        GuiLore.lores.add(new Lore(BlockBase.GOLD_ORE, "Gold Ore", "Found in rock.", "Smeltable into", "gold ingots.", "Medium rarity", "", "", 0));
        GuiLore.lores.add(new Lore(BlockBase.IRON_ORE, "Iron Ore", "Found in rock.", "Smeltable into", "iron ingots.", "Common", "", "", 0));
        GuiLore.lores.add(new Lore(BlockBase.LOG, "Wood", "Found in trees.", "Craftable into", "planks.", "", "", "", 0));
        GuiLore.lores.add(new Lore(BlockBase.SPONGE, "Sponge", "Not obtainable...", "Has no purpose", "", "", "", "", 0));
        GuiLore.lores.add(new Lore(BlockBase.GLASS, "Glass", "Smelted from sand.", "Decorative block", "", "", "", "", 0));
        GuiLore.lores.add(new Lore(BlockBase.LAPIS_LAZULI_BLOCK, "Lapis Lazuli", "Made from 9 lapis", "lazuli.", "Decorative block", "", "", "", 0));
        GuiLore.lores.add(new Lore(BlockBase.DISPENSER, "Dispenser", "Ejects items when ", "powered. Also", "shoots arrows", "", "", "", 0));
        GuiLore.lores.add(new Lore(BlockBase.SANDSTONE, "Sandstone", "Made from 4 sand.", "Also found below", "sand naturally.", "Decorative block", "", "", 0));
        GuiLore.lores.add(new Lore(BlockBase.NOTEBLOCK, "Note Block", "Plays a note when", "powered. The", "block underneath", "defines the sound.", "Right click to", "change the note", 0));
        GuiLore.lores.add(new Lore(BlockBase.GOLDEN_RAIL, "Powered Rail", "Quickens minecarts", "when powered.", "Brakes minecarts", "when unpowered", "", "", 0));
        GuiLore.lores.add(new Lore(BlockBase.DETECTOR_RAIL, "Detector Rail", "Gives out power", "when a minecart is", "on it.", "", "", "", 0));
        GuiLore.lores.add(new Lore(BlockBase.COBWEB, "Web", "Slows down", "anything that", "enters it", "", "", "", 0));
        GuiLore.lores.add(new Lore(BlockBase.WOOL, "Wool", "Found on sheep.", "Can be dyed.", "Decorative Block", "", "", "", 0));
        GuiLore.lores.add(new Lore((BlockBase) BlockBase.DANDELION, "Dandelion", "Found on grass.", "Can make", "dandelion yellow", "dye", "", "", 0));
        GuiLore.lores.add(new Lore((BlockBase) BlockBase.ROSE, "Rose", "Found on grass.", "Can make", "rose red dye", "", "", "", 0));
        GuiLore.lores.add(new Lore((BlockBase) BlockBase.BROWN_MUSHROOM, "Mushroom", "Found on grass", "and in caves.", "Used to make", "mushroom soup", "", "", 0));
        GuiLore.lores.add(new Lore((BlockBase) BlockBase.RED_MUSHROOM, "Mushroom", "Found on grass", "and in caves.", "Used to make", "mushroom soup", "", "", 0));
        GuiLore.lores.add(new Lore(BlockBase.GOLD_BLOCK, "Gold", "Crafted from 9", "gold ingots.", "Decorative block", "", "", "", 0));
        GuiLore.lores.add(new Lore(BlockBase.IRON_BLOCK, "Iron", "Crafted from 9", "iron ingots.", "Decorative block", "", "", "", 0));
        GuiLore.lores.add(new Lore(BlockBase.STONE_SLAB, "Half-Step", "Used for making", "stairs and such.", "Decorative block", "", "", "", 0));
        GuiLore.lores.add(new Lore(BlockBase.BRICKS, "Bricks", "Decorative block", "", "", "", "", "", 0));
        GuiLore.lores.add(new Lore(BlockBase.TNT, "TNT", "Made from sand", "and gunpowder.", "Will detonate when", "hit or powered.", "Handle with care", "", 0));
        GuiLore.lores.add(new Lore(BlockBase.BOOKSHELF, "Bookshelf", "A pleasant array", "of books.", "Decorative Block", "", "", "", 0));
        GuiLore.lores.add(new Lore(BlockBase.MOSSY_COBBLESTONE, "Mossy Cobblestone", "Found in dungeons.", "Deorative Block", "", "", "", "", 0));
        GuiLore.lores.add(new Lore(BlockBase.OBSIDIAN, "Obsidian", "Formed from water", "and lava.", "Very hard block", "that is useful in", "making fortifications", "and nether portals", 0));
        GuiLore.lores.add(new Lore(BlockBase.TORCH, "Torch", "Made from coal and", "sticks.", "Most common light", "source used for", "mining and lighting", "homes.", 0));
        GuiLore.lores.add(new Lore(BlockBase.WOOD_STAIRS, "Wooden Stairs", "Made from wood.", "Useful for making", "staircases, but ", "more compact than", "half steps.", "", 0));
        GuiLore.lores.add(new Lore(BlockBase.CHEST, "Chest", "Made from planks.", "Holds 27 slots.", "Can be joined to", "another chest to", "make a double", "chest.", 0));
        GuiLore.lores.add(new Lore(BlockBase.DIAMOND_BLOCK, "Diamond", "Crafted from 9", "diamonds.", "Decorative block", "", "", "", 0));
        GuiLore.lores.add(new Lore(BlockBase.WORKBENCH, "Workbench", "Used to create all", "complex items.", "", "", "", "", 0));
        GuiLore.lores.add(new Lore(BlockBase.FURNACE, "Furnace", "Takes coal, wood", "or lava as fuel and", "smelts sand, cobble", "iron, gold, clay", "and lots more", "", 0));
        GuiLore.lores.add(new Lore(BlockBase.LADDER, "Ladder", "Used to climb", "vertically", "upwards or", "downwards", "", "", 0));
        GuiLore.lores.add(new Lore(BlockBase.RAIL, "Rail", "Allows minecarts", "to be placed and", "to move.", "You will need a lot", "of rails to make", "a minecart track", 0));
        GuiLore.lores.add(new Lore(BlockBase.COBBLESTONE_STAIRS, "Cobblestone Stairs", "Made from cobble.", "Useful for making", "staircases, but ", "more compact than", "half steps.", "", 0));
        GuiLore.lores.add(new Lore(BlockBase.LEVER, "Lever", "Gives a redstone", "current when on.", "Used as an input", "device for", "redstone circuits", "", 0));
        GuiLore.lores.add(new Lore(BlockBase.WOODEN_PRESSURE_PLATE, "Pressure Plate", "Gives a redstone", "current when", "stepped on by", "a living thing.", "", "", 0));
        GuiLore.lores.add(new Lore(BlockBase.STONE_PRESSURE_PLATE, "Pressure Plate", "Gives a redstone", "current when", "anything is on it", "", "", "", 0));
        GuiLore.lores.add(new Lore(BlockBase.REDSTONE_TORCH_LIT, "Redstone Torch", "Gives a redstone", "current when", "unpowered, but", "does not when", "powered (NOT gate)", "", 0));
        GuiLore.lores.add(new Lore(BlockBase.BUTTON, "Button", "Gives a redstone", "pulse for about", "a second when", "pushed", "", "", 0));
        GuiLore.lores.add(new Lore(BlockBase.SNOW_BLOCK, "Snow", "Melts when near", "light.", "Could be used as", "camouflage in a", "snowy biome", "", 0));
        GuiLore.lores.add(new Lore(BlockBase.CACTUS, "Cactus", "Found in deserts.", "Hurts living things", "that touch it.", "Can be used as", "defenses and can", "be farmed", 0));
        GuiLore.lores.add(new Lore(BlockBase.CLAY, "Clay", "Found in sand.", "Can be split into", "clay lumps and then", "smelted to make", "bricks", "", 0));
        GuiLore.lores.add(new Lore(BlockBase.JUKEBOX, "Jukebox", "Plays records", "found in dungeons.", "", "", "", "", 0));
        GuiLore.lores.add(new Lore(BlockBase.FENCE, "Fence", "Stops anything", "from jumping over.", "Also used for poles.", "Start from the top", "and work down", "", 0));
        GuiLore.lores.add(new Lore(BlockBase.PUMPKIN, "Pumpkin", "Found in small", "patches.", "Can be made into", "Jack-o-Lanterns", "", "", 0));
        GuiLore.lores.add(new Lore(BlockBase.JACK_O_LANTERN, "Jack-o-Lantern", "Made from pumpkins", "and torches.", "Useful lightsource", "and rather scary", "", "", 0));
        GuiLore.lores.add(new Lore(BlockBase.TRAPDOOR, "Trapdoor", "Opens upwards to", "allow access to", "space below", "", "", "", 0));
        GuiLore.lores.add(new Lore(BlockBase.PISTON, "Piston", "Extends when", "powered. Useful", "for traps, doors", "and machines.", "", "", 0));
        GuiLore.lores.add(new Lore(BlockBase.STICKY_PISTON, "Sticky Piston", "Extends when", "powered and pulls", "blocks when", "retracted. Useful", "in making doors", "and hidden blocks", 0));
        GuiLore.lores.add(new Lore((BlockBase) BlockBase.LEAVES, "Leaves", "Grows on trees.", "Obtainable by", "using shears.", "Decorative block", "", "", 0));
        GuiLore.lores.add(new Lore(ItemBase.ironShovel, "Iron Shovel", "Digs grass, dirt,", "sand and gravel.", "Normal Speed", "Many Uses", "", "", 0));
        GuiLore.lores.add(new Lore(ItemBase.ironPickaxe, "Iron Pickaxe", "Digs stone, cobble,", "and other rocks.", "Normal Speed", "Many Uses", "", "", 0));
        GuiLore.lores.add(new Lore(ItemBase.ironAxe, "Iron Axe", "Chops wood and ", "planks.", "Normal Speed", "Many Uses", "", "", 0));
        GuiLore.lores.add(new Lore(ItemBase.ironSword, "Iron Sword", "For attacking", "mobs and animals.", "Normal Damage", "Many Uses", "", "", 0));
        GuiLore.lores.add(new Lore(ItemBase.ironHoe, "Iron Hoe", "Turns grass or", "dirt into farmland.", "Normal Speed", "Many Uses", "", "", 0));
        GuiLore.lores.add(new Lore(ItemBase.woodShovel, "Wooden Shovel", "Digs grass, dirt,", "sand and gravel.", "Very Slow Speed", "Few Uses", "", "", 0));
        GuiLore.lores.add(new Lore(ItemBase.woodPickaxe, "Wooden Pickaxe", "Digs stone, cobble,", "and other rocks.", "Very Slow Speed", "Few Uses", "", "", 0));
        GuiLore.lores.add(new Lore(ItemBase.woodAxe, "Wooden Axe", "Chops wood and ", "planks.", "Very Slow Speed", "Few Uses", "", "", 0));
        GuiLore.lores.add(new Lore(ItemBase.woodSword, "Wooden Sword", "For attacking", "mobs and animals.", "Very Low Damage", "Few Uses", "", "", 0));
        GuiLore.lores.add(new Lore(ItemBase.woodHoe, "Wooden Hoe", "Turns grass or", "dirt into farmland.", "Very Slow Speed", "Few Uses", "", "", 0));
        GuiLore.lores.add(new Lore(ItemBase.stoneShovel, "Stone Shovel", "Digs grass, dirt,", "sand and gravel.", "Slow Speed", "Average Uses", "", "", 0));
        GuiLore.lores.add(new Lore(ItemBase.stonePickaxe, "Stone Pickaxe", "Digs stone, cobble,", "and other rocks.", "Slow Speed", "Average Uses", "", "", 0));
        GuiLore.lores.add(new Lore(ItemBase.stoneAxe, "Stone Axe", "Chops wood and ", "planks.", "Slow Speed", "Average Uses", "", "", 0));
        GuiLore.lores.add(new Lore(ItemBase.stoneSword, "Stone Sword", "For attacking", "mobs and animals.", "Low Damage", "Average Uses", "", "", 0));
        GuiLore.lores.add(new Lore(ItemBase.stoneHoe, "Stone Hoe", "Turns grass or", "dirt into farmland.", "Slow Speed", "Average Uses", "", "", 0));
        GuiLore.lores.add(new Lore(ItemBase.goldShovel, "Gold Shovel", "Digs grass, dirt,", "sand and gravel.", "Very Fast Speed", "Very Few Uses", "", "", 0));
        GuiLore.lores.add(new Lore(ItemBase.goldPickaxe, "Gold Pickaxe", "Digs stone, cobble,", "and other rocks.", "Very Fast Speed", "Very Few Uses", "", "", 0));
        GuiLore.lores.add(new Lore(ItemBase.goldAxe, "Gold Axe", "Chops wood and ", "planks.", "Very Fast Speed", "Very Few Uses", "", "", 0));
        GuiLore.lores.add(new Lore(ItemBase.goldSword, "Gold Sword", "For attacking", "mobs and animals.", "Very High Damage", "Very Few Uses", "", "", 0));
        GuiLore.lores.add(new Lore(ItemBase.goldHoe, "Gold Hoe", "Turns grass or", "dirt into farmland.", "Very Fast Speed", "Very Few Uses", "", "", 0));
        GuiLore.lores.add(new Lore(ItemBase.diamondShovel, "Diamond Shovel", "Digs grass, dirt,", "sand and gravel.", "Fast Speed", "Very Many Uses", "", "", 0));
        GuiLore.lores.add(new Lore(ItemBase.diamondPickaxe, "Diamond Pickaxe", "Digs stone, cobble,", "and other rocks.", "Fast Speed", "Very Many Uses", "", "", 0));
        GuiLore.lores.add(new Lore(ItemBase.diamondAxe, "Diamond Axe", "Chops wood and ", "planks.", "Fast Speed", "Very Many Uses", "", "", 0));
        GuiLore.lores.add(new Lore(ItemBase.diamondSword, "Diamond Sword", "For attacking", "mobs and animals.", "High Damage", "Very Many Uses", "", "", 0));
        GuiLore.lores.add(new Lore(ItemBase.diamondHoe, "Diamond Hoe", "Turns grass or", "dirt into farmland.", "Fast Speed", "Very Many Uses", "", "", 0));
        GuiLore.lores.add(new Lore(ItemBase.flintAndSteel, "Iron and Flint", "Makes fires", "and activates", "Nether portals.", "", "", "", 0));
        GuiLore.lores.add(new Lore(ItemBase.apple, "Red Apple", "Heals the user", "a small amount.", "Found in dungeons", "and from Notch", "", "", 0));
        GuiLore.lores.add(new Lore(ItemBase.bow, "Bow", "Shoots arrows", "and can be used", "to make dispensers.", "Skeletons use", "bows too", "", 0));
        GuiLore.lores.add(new Lore(ItemBase.arrow, "Arrow", "Ammo for the bow.", "Made from feathers,", "sticks and flints", "", "", "", 0));
        GuiLore.lores.add(new Lore(ItemBase.coal, "Coal", "Found in rock.", "Very common.", "Makes torches", "and is used as", "fuel in the furnace", "", 0));
        GuiLore.lores.add(new Lore(ItemBase.diamond, "Diamond", "Found deep under-", "ground. Very rare.", "Used for diamond", "tools, armour and", "jukeboxes", "", 0));
        GuiLore.lores.add(new Lore(ItemBase.ironIngot, "Iron Ingot", "Refined Iron.", "Makes iron tools,", "armour, rails,", "minecarts, doors,", "buckets and", "compasses", 0));
        GuiLore.lores.add(new Lore(ItemBase.goldIngot, "Gold Ingot", "Refined Gold.", "Makes gold tools,", "armour and ", "watches", "", "", 0));
        GuiLore.lores.add(new Lore(ItemBase.stick, "Stick", "Made from 2 planks.", "Very important", "crafting material", "", "", "", 0));
        GuiLore.lores.add(new Lore(ItemBase.bowl, "Bowl", "For holding soup", "", "", "", "", "", 0));
        GuiLore.lores.add(new Lore(ItemBase.mushroomStew, "Mushroom Soup", "Tasty soup that", "heals a few hearts", "", "", "", "", 0));
        GuiLore.lores.add(new Lore(ItemBase.string, "String", "Dropped by", "spiders.", "Used for bows and", "fishing rods", "", "", 0));
        GuiLore.lores.add(new Lore(ItemBase.feather, "Feather", "Dropped by", "chickens", "and zombies.", "Makes arrows", "", "", 0));
        GuiLore.lores.add(new Lore(ItemBase.gunpowder, "Gunpowder", "Dropped by ghasts", "and creepers.", "Makes TNT", "", "", "", 0));
        GuiLore.lores.add(new Lore(ItemBase.seeds, "Seeds", "Gained by cutting", "wild grass.", "Placeable in", "farmland to grow", "crops", "", 0));
        GuiLore.lores.add(new Lore(ItemBase.wheat, "Wheat", "Produced when", "harvesting crops.", "Used to make bread", "and cake", "", "", 0));
        GuiLore.lores.add(new Lore(ItemBase.bread, "Bread", "Delicious,", "nutritious", "bread.", "Heals a few hearts", "", "", 0));
        GuiLore.lores.add(new Lore(ItemBase.leatherHelmet, "Leather Helmet", "Wear it on your", "head.", "Awful Protection", "", "", "", 0));
        GuiLore.lores.add(new Lore(ItemBase.leatherChestplate, "Leather Chestplate", "Wear it on your", "chest.", "Awful Protection", "", "", "", 0));
        GuiLore.lores.add(new Lore(ItemBase.leatherLeggings, "Leather Greaves", "Wear it on your", "legs.", "Awful Protection", "", "", "", 0));
        GuiLore.lores.add(new Lore(ItemBase.leatherBoots, "Leather Boots", "Wear it on your", "feet.", "Awful Protection", "", "", "", 0));
        GuiLore.lores.add(new Lore(ItemBase.goldHelmet, "Gold Helmet", "Wear it on your", "head.", "Bad Protection", "", "", "", 0));
        GuiLore.lores.add(new Lore(ItemBase.goldChestplate, "Gold Chestplate", "Wear it on your", "chest.", "Bad Protection", "", "", "", 0));
        GuiLore.lores.add(new Lore(ItemBase.goldLeggings, "Gold Greaves", "Wear it on your", "legs.", "Bad Protection", "", "", "", 0));
        GuiLore.lores.add(new Lore(ItemBase.goldBoots, "Gold Boots", "Wear it on your", "feet.", "Bad Protection", "", "", "", 0));
        GuiLore.lores.add(new Lore(ItemBase.chainHelmet, "Chain Helmet", "Wear it on your", "head.", "Bad Protection", "", "", "", 0));
        GuiLore.lores.add(new Lore(ItemBase.chainChestplate, "Chain Chestplate", "Wear it on your", "chest.", "Bad Protection", "", "", "", 0));
        GuiLore.lores.add(new Lore(ItemBase.chainLeggings, "Chain Greaves", "Wear it on your", "legs.", "Bad Protection", "", "", "", 0));
        GuiLore.lores.add(new Lore(ItemBase.chainBoots, "Chain Boots", "Wear it on your", "feet.", "Bad Protection", "", "", "", 0));
        GuiLore.lores.add(new Lore(ItemBase.ironHelmet, "Iron Helmet", "Wear it on your", "head.", "Good Protection", "", "", "", 0));
        GuiLore.lores.add(new Lore(ItemBase.ironChestplate, "Iron Chestplate", "Wear it on your", "chest.", "Good Protection", "", "", "", 0));
        GuiLore.lores.add(new Lore(ItemBase.ironLeggings, "Iron Greaves", "Wear it on your", "legs.", "Good Protection", "", "", "", 0));
        GuiLore.lores.add(new Lore(ItemBase.ironBoots, "Iron Boots", "Wear it on your", "feet.", "Good Protection", "", "", "", 0));
        GuiLore.lores.add(new Lore(ItemBase.diamondHelmet, "Diamond Helmet", "Wear it on your", "head.", "Great Protection", "", "", "", 0));
        GuiLore.lores.add(new Lore(ItemBase.diamondChestplate, "Diamond Chestplate", "Wear it on your", "chest.", "Great Protection", "", "", "", 0));
        GuiLore.lores.add(new Lore(ItemBase.diamondLeggings, "Diamond Greaves", "Wear it on your", "legs.", "Great Protection", "", "", "", 0));
        GuiLore.lores.add(new Lore(ItemBase.diamondBoots, "Diamond Boots", "Wear it on your", "feet.", "Great Protection", "", "", "", 0));
        GuiLore.lores.add(new Lore(ItemBase.flint, "Flint", "Found in gravel.", "Used in arrows", "and firelighters", "", "", "", 0));
        GuiLore.lores.add(new Lore(ItemBase.rawPorkchop, "Raw Pork", "Dropped by pigs.", "Can be cooked", "or eaten raw", "", "", "", 0));
        GuiLore.lores.add(new Lore(ItemBase.cookedPorkchop, "Cooked Pork", "Dropped by pig", "zombies. Also", "obtainable from", "cooking pork.", "Heals a few hearts", "", 0));
        GuiLore.lores.add(new Lore(ItemBase.painting, "Painting", "Made from sticks", "and cloth.", "Puts a random", "painting where", "you click", "", 0));
        GuiLore.lores.add(new Lore(ItemBase.goldenApple, "Golden Apple", "A ridiculously", "expensive apple", "which, despite being", "coated in gold,", "heals all hearts", "", 0));
        GuiLore.lores.add(new Lore(ItemBase.sign, "Sign", "Made from sticks", "and planks.", "Can be placed on", "walls or the floor", "with your message", "", 0));
        GuiLore.lores.add(new Lore(ItemBase.woodDoor, "Wooden Door", "Made from planks.", "Allows you to shut", "out the creepers", "before they boom", "in your house", "", 0));
        GuiLore.lores.add(new Lore(ItemBase.bucket, "Bucket", "Made from iron.", "Can pick up water", "and lava.", "If used on a cow,", "milk may be", "obtained", 0));
        GuiLore.lores.add(new Lore(ItemBase.waterBucket, "Water Bucket", "Can be used to", "place a water", "source", "", "", "", 0));
        GuiLore.lores.add(new Lore(ItemBase.lavaBucket, "Lava Bucket", "Can be used to", "place a lava", "source", "", "", "", 0));
        GuiLore.lores.add(new Lore(ItemBase.minecart, "Minecart", "Can be ridden in,", "but make sure the", "animals can't get", "to your cart", "", "", 0));
        GuiLore.lores.add(new Lore(ItemBase.saddle, "Saddle", "Found in dungeons.", "Can be used to", "saddle a pig", "", "", "", 0));
        GuiLore.lores.add(new Lore(ItemBase.ironDoor, "Iron Door", "Made from iron.", "Behaves like a door", "but can only be", "opened by redstone", "", "", 0));
        GuiLore.lores.add(new Lore(ItemBase.redstoneDust, "Redstone", "Used to carry", "redstone currents", "in redstone circuits", "", "", "", 0));
        GuiLore.lores.add(new Lore(ItemBase.snowball, "Snowball", "Found by digging", "snow with a spade.", "Can be thrown", "", "", "", 0));
        GuiLore.lores.add(new Lore(ItemBase.boat, "Boat", "Can be ridden in", "to cross lakes", "and oceans", "", "", "", 0));
        GuiLore.lores.add(new Lore(ItemBase.leather, "Leather", "Dropped by cows.", "Used in making", "leather armour", "", "", "", 0));
        GuiLore.lores.add(new Lore(ItemBase.milk, "Milk Bucket", "Gained by using", "a bucket on a cow.", "Used in making cake.", "Heals a few hearts", "", "", 0));
        GuiLore.lores.add(new Lore(ItemBase.brick, "Brick", "Smelted from clay.", "Used to make brick", "blocks", "", "", "", 0));
        GuiLore.lores.add(new Lore(ItemBase.clay, "Clay", "Found in clay", "blocks.", "Can be smelted", "into bricks", "", "", 0));
        GuiLore.lores.add(new Lore(ItemBase.sugarCanes, "Sugarcanes", "Found on dirt or", "grass by water.", "Makes paper for", "books and sugar ", "for cakes.", "Can be farmed", 0));
        GuiLore.lores.add(new Lore(ItemBase.paper, "Paper", "Made from", "sugarcane.", "Used in books and", "maps", "", "", 0));
        GuiLore.lores.add(new Lore(ItemBase.book, "Book", "Made from paper.", "Used to make", "bookcases", "", "", "", 0));
        GuiLore.lores.add(new Lore(ItemBase.slimeball, "Slime Ball", "Dropped by slimes.", "Used to make", "sticky pistons", "", "", "", 0));
        GuiLore.lores.add(new Lore(ItemBase.minecartChest, "Storage Minecart", "A minecart that", "carries a chest", "", "", "", "", 0));
        GuiLore.lores.add(new Lore(ItemBase.minecartFurnace, "Powered Minecart", "A minecart that", "pushes other carts", "when given coal", "", "", "", 0));
        GuiLore.lores.add(new Lore(ItemBase.egg, "Egg", "Laid by chickens.", "Throw it to hatch", "a new chicken.", "Also used in", "making cake.", "", 0));
        GuiLore.lores.add(new Lore(ItemBase.compass, "Compass", "Made from iron", "and redstone.", "Points to your", "spawnpoint", "", "", 0));
        GuiLore.lores.add(new Lore(ItemBase.fishingRod, "Fishing Rod", "Made from sticks", "and string.", "Can be used for", "fishing or ", "pulling mobs around", "", 0));
        GuiLore.lores.add(new Lore(ItemBase.clock, "Watch", "Made from gold", "and redstone.", "Tells the time", "", "", "", 0));
        GuiLore.lores.add(new Lore(ItemBase.glowstoneDust, "Lightstone Dust", "Dropped by", "lightstone.", "Can be crafted", "into lightstone", "", "", 0));
        GuiLore.lores.add(new Lore(ItemBase.rawFish, "Raw Fish", "Gained by fishing.", "Can be cooked or", "eaten raw", "", "", "", 0));
        GuiLore.lores.add(new Lore(ItemBase.cookedFish, "Cooked Fish", "Gained by cooking", "raw fish.", "Heals a few hearts", "", "", "", 0));
        GuiLore.lores.add(new Lore(ItemBase.dyePowder, "Dye", "Obtained from many", "places.", "Dyes can be mixed,", "added to wool", "and used on sheep", "", 0));
        GuiLore.lores.add(new Lore(ItemBase.bone, "Bone", "Dropped by", "skeletons.", "Used to make", "bonemeal and to", "tame wolves", "", 0));
        GuiLore.lores.add(new Lore(ItemBase.sugar, "Sugar", "Made from", "sugarcane.", "Used to make cake", "", "", "", 0));
        GuiLore.lores.add(new Lore(ItemBase.cake, "Cake", "The cake is a lie.", "", "", "", "", "", 0));
        GuiLore.lores.add(new Lore(ItemBase.bed, "Bed", "Made from planks", "and wool.", "Allows you to", "sleep until", "morning and set", "your spawnpoint", 0));
        GuiLore.lores.add(new Lore(ItemBase.redstoneRepeater, "Repeater", "Made from stone", "and redstone.", "Repeats a signal", "with a delay, set", "by the toggle", "", 0));
        GuiLore.lores.add(new Lore(ItemBase.cookie, "Cookie", "Made from cocoa", "beans and wheat.", "Heals a few hearts", "", "", "", 0));
        GuiLore.lores.add(new Lore((ItemBase) ItemBase.map, "Map", "Made from paper", "and a compass.", "Makes a map of the", "area you are in", "", "", 0));
        GuiLore.lores.add(new Lore(ItemBase.record13, "13", "Found in dungeons.", "Playable in jukebox.", "A rather odd tune", "", "", "", 0));
        GuiLore.lores.add(new Lore(ItemBase.recordCat, "Cat", "Found in dungeons.", "Playable in jukebox.", "A very jolly tune", "", "", "", 0));
        GuiLore.lores.add(new Lore((ItemBase) ItemBase.shears, "Shears", "Made from iron.", "Used to shear", "sheep and to get", "leaf blocks from", "trees.", "", 0));
        GuiLore.lores.add(new Lore(AetherItems.IronRing, "Iron Ring", "Made from iron.", "Wear it in your", "ring slot.", "Purely decorative", "item", "", 0));
        GuiLore.lores.add(new Lore(AetherItems.GoldRing, "Gold Ring", "Made from gold.", "Wear it in your", "ring slot.", "Purely decorative", "item", "", 0));
        GuiLore.lores.add(new Lore(AetherItems.IronPendant, "Iron Pendant", "Made from iron.", "Wear it in your", "pendant slot.", "Purely decorative", "item", "", 0));
        GuiLore.lores.add(new Lore(AetherItems.GoldPendant, "Gold Pendant", "Made from gold.", "Wear it in your", "pendant slot.", "Purely decorative", "item", "", 0));
        GuiLore.lores.add(new Lore(AetherItems.LeatherGlove, "Leather Glove", "Wear them on your", "hands.", "Awful Protection", "", "", "", 0));
        GuiLore.lores.add(new Lore(AetherItems.IronGlove, "Iron Glove", "Wear them on your", "hands.", "Good Protection", "", "", "", 0));
        GuiLore.lores.add(new Lore(AetherItems.GoldGlove, "Gold Glove", "Wear them on your", "hands.", "Bad Protection", "", "", "", 0));
        GuiLore.lores.add(new Lore(AetherItems.DiamondGlove, "Diamond Glove", "Wear them on your", "hands.", "Great Protection", "", "", "", 0));
        GuiLore.lores.add(new Lore(new ItemInstance(AetherItems.LoreBook, 1, 0), "Lore Book : Vol 1", "Contains information", "about blocks and", "items from the", "surface world", "", "", 0));
        GuiLore.lores.add(new Lore(BlockBase.NETHERRACK, "Netherrack", "Main nether", " material.", "Burns forever", "", "", "", 1));
        GuiLore.lores.add(new Lore(BlockBase.SOUL_SAND, "Slow Sand", "Found in patches", "Slows anything on it", "", "", "", "", 1));
        GuiLore.lores.add(new Lore(BlockBase.GLOWSTONE, "Glowstone", "Found on the roof", "of the Nether.", "Drops 4 Glowstone", "dust.", "Used in Aether ", "portals", 1));
        GuiLore.lores.add(new Lore(ItemBase.glowstoneDust, "Glowstone Dust", "Obtained when mining", "a block of Glowstone.", "", "", "", "", 1));
        GuiLore.lores.add(new Lore(new ItemInstance(AetherItems.LoreBook, 1, 1), "Lore Book : Vol 2", "Contains information", "about blocks and", "items from the", "Nether", "", "", 1));
        GuiLore.lores.add(new Lore(AetherBlocks.AETHER_DIRT, "Aether Dirt", "A paler dirt.", "Aether grass", "and skyroot trees", "will grow on it", "", "", 2));
        GuiLore.lores.add(new Lore(AetherBlocks.AETHER_GRASS_BLOCK, "Aether Grass", "A paler grass.", "Skyroot trees will", "grow on it.", "Allows Aether mobs", "to spawn", "", 2));
        GuiLore.lores.add(new Lore(new ItemInstance(AetherBlocks.HOLYSTONE, 1, 0), "Holystone", "Main material of", "the Aether.", "Makes holystone", "tools and", "enchanters", "", 2));
        GuiLore.lores.add(new Lore(AetherBlocks.SKYROOT_PLANKS, "Skyroot Plank", "Made from skyroot.", "Used to make", "skyroot sticks and", "tools", "", "", 2));
        GuiLore.lores.add(new Lore(AetherBlocks.SKYROOT_SAPLING, "Skyroot Sapling", "Dropped by ", "skyroot leaves.", "Plants a skyroot", "tree", "", "", 2));
        GuiLore.lores.add(new Lore(AetherBlocks.GOLDEN_OAK_SAPLING, "Golden Oak Sapling", "Dropped by golden", "oak leaves.", "Plants a golden", "oak", "", "", 2));
        GuiLore.lores.add(new Lore(AetherBlocks.QUICKSOIL, "Quicksoil", "Found at the edge", "of islands.", "Speeds up anything", "on it.", "Use it with blue", "clouds for epicness", 2));
        GuiLore.lores.add(new Lore(AetherBlocks.SKYROOT_LOG, "Skyroot Log", "Wood from skyroot", "trees.", "Makes skyroot", "planks", "", "", 2));
        GuiLore.lores.add(new Lore(AetherBlocks.ICESTONE, "Icestone", "Found in Holystone.", "Freezes water", "around it on", "placement", "", "", 2));
        GuiLore.lores.add(new Lore(AetherBlocks.GRAVITITE_ORE, "Gravitite Ore", "Found under big", "islands.", "Floats upwards,", "and can be made", "into tools.", "Can be enchanted", 2));
        GuiLore.lores.add(new Lore(AetherBlocks.ENCHANTED_GRAVITITE, "Enchanted Gravitite", "Floats upwards", "when powered by", "redstone", "", "", "", 2));
        GuiLore.lores.add(new Lore(new ItemInstance(AetherBlocks.MOSSY_HOLYSTONE, 1), "Mossy Holystone", "Found in dungeons.", "Decorative block", "", "", "", "", 2));
        GuiLore.lores.add(new Lore(new ItemInstance(AetherBlocks.AERCLOUD, 1, 1), "Blue Aercloud", "Found in clouds.", "When landed on,", "it will bounce you", "sky-high", "", "", 2));
        GuiLore.lores.add(new Lore(new ItemInstance(AetherBlocks.AERCLOUD, 1, 0), "Cold Aercloud", "Found in clouds.", "Stops fall damage", "when landed on", "", "", "", 2));
        GuiLore.lores.add(new Lore(new ItemInstance(AetherBlocks.AERCLOUD, 1, 2), "Gold Aercloud", "Found in clouds.", "Stops fall damage", "when landed on.", "Quite rare", "", "", 2));
        GuiLore.lores.add(new Lore(AetherBlocks.AMBROSIUM_TORCH, "Ambrosium Torch", "Made from skyroot", "sticks and", "ambrosium.", "Can be placed in", "the Aether", "", 2));
        GuiLore.lores.add(new Lore(AetherBlocks.DUNGEON_STONE, "Dungeon Stone", "Found in dungeons.", "Decorative block", "", "", "", "", 2));
        GuiLore.lores.add(new Lore(AetherBlocks.LIGHT_DUNGEON_STONE, "Lit Dungeon Stone", "Found in dungeons.", "Emits a faint light.", "Decorative block", "", "", "", 2));
        GuiLore.lores.add(new Lore(AetherBlocks.PILLAR, "Pillar", "Found in silver", "dungeons.", "Decorative block", "", "", "", 2));
        GuiLore.lores.add(new Lore(AetherBlocks.ENCHANTER, "Enchanter", "Made from Zanite", "and Holystone.", "Enchants items", "and repairs tools", "", "", 2));
        GuiLore.lores.add(new Lore(AetherBlocks.INCUBATOR, "Incubator", "Made from skyroot", "planks and", "Holystone.", "Incubates Moa", "eggs until they ", "hatch", 2));
        GuiLore.lores.add(new Lore(AetherBlocks.ZANITE_BLOCK, "Zanite Block", "Crafted with four", "Zanite Gemstones.", "Decorative block", "", "", "", 2));
        GuiLore.lores.add(new Lore(AetherBlocks.PURPLE_FLOWER, "Purple Flower", "Common plant in", "the Aether.", "Can be crafted", "into dye.", "", "", 2));
        GuiLore.lores.add(new Lore(AetherBlocks.WHITE_FLOWER, "White Flower", "Common plant in", "the Aether.", "Can be crafted", "into dye.", "", "", 2));
        GuiLore.lores.add(new Lore(AetherBlocks.FREEZER, "Freezer", "Allows you to", "freeze certain items.", "Uses Icestone as", "a fuel source.", "", "", 2));
        GuiLore.lores.add(new Lore(AetherBlocks.QUICKSOIL_GLASS, "Quicksoil Glass", "Gained by enchanting", "Quicksoil blocks.", "Translucent, gives", "off small amount", "of light.", "", 2));
        GuiLore.lores.add(new Lore(AetherBlocks.AEROGEL, "Aerogel", "Found in dungeons.", "Incredibly high", "TNT resistance", "", "", "", 2));
        GuiLore.lores.add(new Lore(AetherItems.PhoenixBow, "Phoenix Bow", "Found in dungeons.", "Shoots flaming", "arrows that", "burn mobs", "", "", 2));
        GuiLore.lores.add(new Lore(AetherItems.GummieSwet, "Gummy Swet", "Found in dungeons.", "Tasty swet", "flavoured", "gummy swets", "(May contain swet)", "", 2));
        GuiLore.lores.add(new Lore(AetherItems.SwordFire, "Fire Sword", "Found in dungeons.", "A sword imbued", "with the power of", "fire", "", "", 2));
        GuiLore.lores.add(new Lore(AetherItems.SwordLightning, "Lightning Sword", "Found in dungeons.", "A sword imbued", "with the power of", "lightning", "", "", 2));
        GuiLore.lores.add(new Lore(AetherItems.SwordHoly, "Holy Sword", "Found in dungeons.", "A holy sword that", "will deal extra", "damage to undead", "", "", 2));
        GuiLore.lores.add(new Lore(AetherItems.HammerNotch, "Hammer of Notch", "Found in dungeons.", "A hammer that", "has a special", "attack which hits", "lots of mobs", "", 2));
        GuiLore.lores.add(new Lore(AetherItems.LightningKnife, "Lightning Knife", "Found in dungeons.", "Throwable.", "Creates lightning", "on hit.", "", "", 2));
        GuiLore.lores.add(new Lore(AetherItems.PigSlayer, "Pig Slayer", "Found in Dungeons.", "Very good dagger.", "Kills pigs and", "pig zombies in", "1 hit", "", 2));
        GuiLore.lores.add(new Lore(AetherItems.VictoryMedal, "Medallion", "Dropped by ", "Valkyries.", "A sign of victory", "from the Valkyries", "that you need to ", "fight the boss", 2));
        GuiLore.lores.add(new Lore(AetherItems.PickHolystone, "Holystone Pickaxe", "Digs holystone", "and Aether ores.", "Randomly gives", "extra ambrosium.", "Slow Speed", "Average Uses", 2));
        GuiLore.lores.add(new Lore(AetherItems.AxeHolystone, "Holystone Axe", "Chops Skyroot", "and Gilded Oak.", "Randomly gives", "extra ambrosium.", "Slow Speed", "Average Uses", 2));
        GuiLore.lores.add(new Lore(AetherItems.ShovelHolystone, "Holystone Shovel", "Digs Aether dirt,", "and quicksoil.", "Randomly gives", "extra ambrosium.", "Slow Speed", "Average Uses", 2));
        GuiLore.lores.add(new Lore(AetherItems.SwordHolystone, "Holystone Sword", "For attacking", "mobs and animals.", "Randomly gives", "extra ambrosium.", "Slow Speed", "Average Uses", 2));
        GuiLore.lores.add(new Lore(AetherItems.PickSkyroot, "Skyroot Pickaxe", "Digs Holystone", "and Aether ores.", "Randomly gives", "double drops.", "Very Slow Speed", "Few Uses", 2));
        GuiLore.lores.add(new Lore(AetherItems.AxeSkyroot, "Skyroot Axe", "Chops Skyroot", "and Gilded Oak.", "Randomly gives", "double drops.", "Very Slow Speed", "Few Uses", 2));
        GuiLore.lores.add(new Lore(AetherItems.ShovelSkyroot, "Skyroot Shovel", "Digs Aether dirt,", "and quicksoil.", "Randomly gives", "double drops.", "Very Slow Speed", "Few Uses", 2));
        GuiLore.lores.add(new Lore(AetherItems.SwordSkyroot, "Skyroot Sword", "For attacking", "mobs and animals.", "Randomly gives", "double drops.", "Very Slow Speed", "Few Uses", 2));
        GuiLore.lores.add(new Lore(AetherItems.PickZanite, "Zanite Pickaxe", "Digs Holystone", "and Aether ores.", "Power increases", "with damage.", "Normal Speed", "Many Uses", 2));
        GuiLore.lores.add(new Lore(AetherItems.AxeZanite, "Zanite Axe", "Chops Skyroot", "and Gilded Oak.", "Power increases", "with damage.", "Normal Speed", "Many Uses", 2));
        GuiLore.lores.add(new Lore(AetherItems.ShovelZanite, "Zanite Shovel", "Digs Aether dirt,", "and quicksoil.", "Power increases", "with damage.", "Normal Speed", "Many Uses", 2));
        GuiLore.lores.add(new Lore(AetherItems.SwordZanite, "Zanite Sword", "For attacking", "mobs and animals.", "Power increases", "with damage.", "Normal Speed", "Many Uses", 2));
        GuiLore.lores.add(new Lore(AetherItems.PickGravitite, "Gravitite Pickaxe", "Digs Holystone", "and Aether ores.", "Right click will", "lift mobs.", "Fast Speed", "Very Many Uses", 2));
        GuiLore.lores.add(new Lore(AetherItems.AxeGravitite, "Gravitite Axe", "Chops Skyroot", "and Gilded Oak.", "Right click will", "lift mobs.", "Fast Speed", "Very Many Uses", 2));
        GuiLore.lores.add(new Lore(AetherItems.ShovelGravitite, "Gravitite Shovel", "Digs Aether dirt,", "and quicksoil.", "Right click will", "lift mobs.", "Fast Speed", "Very Many Uses", 2));
        GuiLore.lores.add(new Lore(AetherItems.SwordGravitite, "Gravitite Sword", "For attacking", "mobs and animals.", "Right click will", "lift mobs.", "Fast Speed", "Very Many Uses", 2));
        GuiLore.lores.add(new Lore(AetherItems.AmbrosiumShard, "Ambrosium Shard", "Found in Holystone.", "Makes Ambrosium", "Torches and is", "the fuel of the", "enchanter", "", 2));
        GuiLore.lores.add(new Lore(AetherItems.Zanite, "Zanite Gem", "Found in Holystone.", "Makes Zanite tools", "and enchanters", "", "", "", 2));
        GuiLore.lores.add(new Lore(AetherItems.Stick, "Skyroot Stick", "Made from skyroot", "planks.", "Vital crafting", "item for Aether", "tools", "", 2));
        GuiLore.lores.add(new Lore(new ItemInstance(AetherItems.Bucket, 1, 0), "Skyroot Bucket", "Made from skyroot.", "Can pick up water,", "milk and poison", "", "", "", 2));
        GuiLore.lores.add(new Lore(new ItemInstance(AetherItems.Bucket, 1, BlockBase.FLOWING_WATER.id), "Skyroot Water Bucket", "A skyroot bucket", "full of water", "", "", "", "", 2));
        GuiLore.lores.add(new Lore(new ItemInstance(AetherItems.Bucket, 1, 1), "Skyroot Milk Bucket", "A skyroot bucket", "full of milk", "", "", "", "", 2));
        GuiLore.lores.add(new Lore(AetherItems.GoldenAmber, "Golden Amber", "Dropped by golden", "oaks.", "Used to make", "golden darts", "", "", 2));
        GuiLore.lores.add(new Lore(AetherItems.MoaEgg, "Moa Egg", "Laid by Moas.", "Place in an", "incubator to", "hatch  it", "", "", 2));
        GuiLore.lores.add(new Lore(new ItemInstance(AetherItems.Key, 1, 0), "Bronze Key", "Dropped by the", "Slider.", "Use it to gain", "access to the", "bronze treasure", "chest", 2));
        GuiLore.lores.add(new Lore(new ItemInstance(AetherItems.Key, 1, 1), "Silver Key", "Dropped by the", "Grand Valkyrie.", "Use it to gain", "access to the silver", "treasure chest", "", 2));
        GuiLore.lores.add(new Lore(new ItemInstance(AetherItems.Key, 1, 2), "Gold Key", "Dropped by the", "Sun Spririt", "Use it to gain", "access to the gold", "treasure chest", "", 2));
        GuiLore.lores.add(new Lore(AetherItems.AechorPetal, "Aechor Petal", "Dropped by Aechor", "Plants.", "Used to tame and", "feed Moas", "", "", 2));
        GuiLore.lores.add(new Lore(new ItemInstance(AetherItems.DartShooter, 1, 0), "Dart Shooter", "Found in Dungeons.", "Shoots gold darts", "", "", "", "", 2));
        GuiLore.lores.add(new Lore(new ItemInstance(AetherItems.DartShooter, 1, 2), "Enchanted Shooter", "Shoots enchanted", "darts", "", "", "", "", 2));
        GuiLore.lores.add(new Lore(new ItemInstance(AetherItems.DartShooter, 1, 1), "Poison Shooter", "Shoots poison", "darts", "", "", "", "", 2));
        GuiLore.lores.add(new Lore(new ItemInstance(AetherItems.Dart, 1, 0), "Golden Dart", "Found in Dungeons", "and crafted from", "golden orbs and", "skyroot sticks.", "Simplest ammo", "for the dart shooter", 2));
        GuiLore.lores.add(new Lore(new ItemInstance(AetherItems.Dart, 1, 1), "Enchanted Dart", "Found in Dungeons.", "Enchantable from", "Golden Darts.", "Has more attack", "than a golden dart", "", 2));
        GuiLore.lores.add(new Lore(new ItemInstance(AetherItems.Dart, 1, 2), "Poison Dart", "Found in Dungeons.", "Craftable from", "Golden Darts and", "poison buckets.", "Ammo for the dart", "shooter that poisons", 2));
        GuiLore.lores.add(new Lore(AetherItems.BlueMusicDisk, "Blue Music Disk", "Found in Dungeons.", "Can be played", "in jukeboxes.", "Plays the Aether", "tune", "", 2));
        GuiLore.lores.add(new Lore(new ItemInstance(AetherItems.Bucket, 1, 3), "Bucket of Remedy", "Enchantable from", "bucket of poison.", "Cures poison", "", "", "", 2));
        GuiLore.lores.add(new Lore(new ItemInstance(AetherItems.Bucket, 1, 2), "Bucket of Posion", "Found in Dungeons.", "Obtainable from", "Aechor Plants.", "Used to make poison", "darts", "", 2));
        GuiLore.lores.add(new Lore(AetherItems.CloudParachute, "Cloud Parachute", "Made from clouds.", "Will float the player", "gently down.", "Activates on click", "or when falling", "from the Aether", 2));
        GuiLore.lores.add(new Lore(AetherItems.CloudParachuteGold, "Gold Cloud Parachute", "Made from gold", "clouds.", "Similar to Cloud", "Parachute, but", "has 4 uses.", "", 2));
        GuiLore.lores.add(new Lore(AetherItems.IronBubble, "Iron Bubble", "Found in dungeons.", "Allows you to", "breathe", "underwater forever", "", "", 2));
        GuiLore.lores.add(new Lore(AetherItems.VampireBlade, "Vampire Blade", "Found in dungeons.", "Powerful sword", "that drains the", "health of anything", "hit", "", 2));
        GuiLore.lores.add(new Lore(AetherItems.NatureStaff, "Nature Staff", "Made from sticks", "and Zanite.", "Allows you to", "control your moas", "by making them", "follow and halt", 2));
        GuiLore.lores.add(new Lore(AetherItems.CloudStaff, "Cloud Staff", "Found in Dungeons.", "Use this staff to", "summon two mini", "zephyrs which", "will shoot ice", "balls", 2));
        GuiLore.lores.add(new Lore(AetherItems.LifeShard, "Life Shard", "Found in Dungeons.", "Increases your", "maximum health by", "one heart", "", "", 2));
        GuiLore.lores.add(new Lore(AetherItems.GoldenFeather, "Golden Feather", "Found in Dungeons.", "While holding this", "you will float", "gently to the ground", "and take no fall", "damage", 2));
        GuiLore.lores.add(new Lore(AetherItems.RepShield, "Shield of Repulsion", "Found in Dungeons.", "Place it in shield", "slot.", "While standing still", "all projectiles", "will bounce off you", 2));
        GuiLore.lores.add(new Lore(AetherItems.Lance, "Lance", "Found in Dungeons.", "Powerful weapon", "with extended", "reach", "", "", 2));
        GuiLore.lores.add(new Lore(AetherItems.AetherCape, "Swet Cape", "Found in Dungeons.", "Wear it as a cape.", "Purely asthetic", "item", "", "s", 2));
        GuiLore.lores.add(new Lore(AetherItems.ZanitePendant, "Zanite Pendant", "Made from zanite.", "Wear it in your", "pendant slot.", "As it wears away", "it increases your", "mining speed", 2));
        GuiLore.lores.add(new Lore(AetherItems.ZaniteRing, "Zanite Ring", "Made from zanite.", "Wear it in your", "ring slot.", "As it wears away", "it increases your", "mining speed", 2));
        GuiLore.lores.add(new Lore(AetherItems.PhoenixHelm, "Phoenix Helmet", "Found in dungeons.", "Protects the ", "wearer from any", "fire or lava damage.", "Weak to water, but", "it holds a secret", 2));
        GuiLore.lores.add(new Lore(AetherItems.PhoenixBody, "Phoenix Chestplate", "Found in dungeons.", "Protects the ", "wearer from any", "fire or lava damage.", "Weak to water, but", "it holds a secret", 2));
        GuiLore.lores.add(new Lore(AetherItems.PhoenixLegs, "Phoenix Greaves", "Found in dungeons.", "Protects the ", "wearer from any", "fire or lava damage.", "Weak to water, but", "it holds a secret", 2));
        GuiLore.lores.add(new Lore(AetherItems.PhoenixBoots, "Phoenix Boots", "Found in dungeons.", "Protects the ", "wearer from any", "fire or lava damage.", "Weak to water, but", "it holds a secret", 2));
        GuiLore.lores.add(new Lore(AetherItems.HealingStone, "Healing Stone", "Gained by enchanting", "Holystone blocks.", "Heals four hearts", "of health.", "", "", 2));
        GuiLore.lores.add(new Lore(AetherItems.RedCape, "Red Cape", "Worn in cape", "slot.", "Purely decorative.", "", "", "", 2));
        GuiLore.lores.add(new Lore(AetherItems.BlueCape, "Blue Cape", "Worn in cape", "slot.", "Purely decorative.", "", "", "", 2));
        GuiLore.lores.add(new Lore(AetherItems.WhiteCape, "White Cape", "Worn in cape", "slot.", "Purely decorative.", "", "", "", 2));
        GuiLore.lores.add(new Lore(AetherItems.YellowCape, "Yellow Cape", "Worn in cape", "slot.", "Purely decorative.", "", "", "", 2));
        GuiLore.lores.add(new Lore(AetherItems.IcePendant, "Ice Pendant", "Worn in pendant", "slot.", "Freezes all water", "and lava sources", "around the player.", "", 2));
        GuiLore.lores.add(new Lore(AetherItems.IceRing, "Ice Ring", "Worn in ring", "slots.", "Freezes all water", "and lava sources", "around the player.", "", 2));
        GuiLore.lores.add(new Lore(AetherItems.AgilityCape, "Agility Cape", "Worn in cape", "slot.", "Gives the player", "the ability to", "walk up blocks", "without jumping.", 2));
        GuiLore.lores.add(new Lore(AetherItems.PickValkyrie, "Valkyrie Pickaxe", "A powerful pickaxe", "which once belonged", "to a Valkyrie.", "Has extended reach.", "", "", 2));
        GuiLore.lores.add(new Lore(AetherItems.AxeValkyrie, "Valkyrie Axe", "A powerful axe", "which once belonged", "to a Valkyrie.", "Has extended reach.", "", "", 2));
        GuiLore.lores.add(new Lore(AetherItems.ShovelValkyrie, "Valkyrie Shovel", "A powerful shovel", "which once belonged", "to a Valkyrie.", "Has extended reach.", "", "", 2));
        GuiLore.lores.add(new Lore(AetherItems.ObsidianHelm, "Obsidian Helmet", "A cooled version of", "the Phoenix", "Helmet.", "Incredibly strong", "head armour", "", 2));
        GuiLore.lores.add(new Lore(AetherItems.ObsidianBody, "Obsidian Chestplate", "A cooled version of", "the Phoenix", "Chestplate.", "Incredibly strong", "chest armour", "", 2));
        GuiLore.lores.add(new Lore(AetherItems.ObsidianLegs, "Obsidian Greaves", "A cooled version of", "the Phoenix", "Leggings.", "Incredibly strong", "leg armour", "", 2));
        GuiLore.lores.add(new Lore(AetherItems.ObsidianBoots, "Obsidian Boots", "A cooled version of", "the Phoenix", "Boots.", "Incredibly strong", "foot armour", "", 2));
        GuiLore.lores.add(new Lore(AetherItems.ZaniteHelmet, "Zanite Helmet", "Wear it on your", "head.", "Good Protection.", "Provides better", "protection when", "more damaged", 2));
        GuiLore.lores.add(new Lore(AetherItems.ZaniteChestplate, "Zanite Chestplate", "Wear it on your", "chest.", "Good Protection.", "Provides better", "protection when", "more damaged", 2));
        GuiLore.lores.add(new Lore(AetherItems.ZaniteLeggings, "Zanite Greaves", "Wear it on your", "legs.", "Good Protection.", "Provides better", "protection when", "more damaged", 2));
        GuiLore.lores.add(new Lore(AetherItems.ZaniteBoots, "Zanite Boots", "Wear it on your", "feet.", "Good Protection.", "Provides better", "protection when", "more damaged", 2));
        GuiLore.lores.add(new Lore(AetherItems.GravititeHelmet, "Gravitite Helmet", "Wear it on your", "head.", "Great Protection.", "Full set stops", "fall damage and", "jumps higher", 2));
        GuiLore.lores.add(new Lore(AetherItems.GravititeBodyplate, "Gravitite Chestplate", "Wear it on your", "chest.", "Great Protection.", "Full set stops", "fall damage and", "jumps higher", 2));
        GuiLore.lores.add(new Lore(AetherItems.GravititePlatelegs, "Gravitite Greaves", "Wear it on your", "legs.", "Great Protection.", "Full set stops", "fall damage and", "jumps higher", 2));
        GuiLore.lores.add(new Lore(AetherItems.GravititeBoots, "Gravitite Boots", "Wear it on your", "feet.", "Great Protection.", "Full set stops", "fall damage and", "jumps higher", 2));
        GuiLore.lores.add(new Lore(AetherItems.NeptuneHelmet, "Neptune Helmet", "Found in dungeons.", "Wear it on your", "head.", "Great Protection.", "Full set speeds", "up water movement", 2));
        GuiLore.lores.add(new Lore(AetherItems.NeptuneChestplate, "Neptune Chestplate", "Found in dungeons.", "Wear it on your", "chest.", "Great Protection.", "Full set speeds", "up water movement", 2));
        GuiLore.lores.add(new Lore(AetherItems.NeptuneLeggings, "Neptune Greaves", "Found in dungeons.", "Wear it on your", "legs.", "Great Protection.", "Full set speeds", "up water movement", 2));
        GuiLore.lores.add(new Lore(AetherItems.NeptuneBoots, "Neptune Boots", "Found in dungeons.", "Wear it on your", "feet.", "Great Protection.", "Full set speeds", "up water movement", 2));
        GuiLore.lores.add(new Lore(AetherItems.ZaniteGlove, "Zanite Glove", "Wear them on your", "hands.", "Good Protection", "", "", "", 2));
        GuiLore.lores.add(new Lore(AetherItems.GravititeGlove, "Gravitite Glove", "Wear them on your", "hands.", "Great Protection.", "Full set stops", "fall damage and", "jumps higher", 2));
        GuiLore.lores.add(new Lore(AetherItems.ObsidianGlove, "Obsidian Glove", "A cooled version of", "the Phoenix", "Glove.", "Incredibly strong", "hand armour", "", 2));
        GuiLore.lores.add(new Lore(AetherItems.PhoenixGlove, "Phoenix Glove", "Found in dungeons.", "Protects the ", "wearer from any", "fire or lava damage.", "Weak to water, but", "it holds a secret", 2));
        GuiLore.lores.add(new Lore(AetherItems.NeptuneGlove, "Neptune Glove", "Found in dungeons.", "Wear them on your", "hands.", "Great Protection", "", "", 2));
        GuiLore.lores.add(new Lore(AetherItems.RegenerationStone, "Regeneration Stone", "Use it in your", "accessory slots.", "Regenerates health", "over time", "", "", 2));
        GuiLore.lores.add(new Lore(AetherItems.InvisibilityCloak, "Invisibility Cloak", "Use it in your", "cloak slot.", "Makes you invisible", "", "", "", 2));
        GuiLore.lores.add(new Lore(new ItemInstance(AetherItems.LoreBook, 1, 2), "Lore Book : Vol 3", "Contains information", "about blocks and", "items from the", "Aether", "", "", 2));
    } //TODO: add lore
}
