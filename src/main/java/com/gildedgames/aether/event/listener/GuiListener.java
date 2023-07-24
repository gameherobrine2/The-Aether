package com.gildedgames.aether.event.listener;

import com.gildedgames.aether.AetherMod;
import com.gildedgames.aether.entity.tile.TileEntityEnchanter;
import com.gildedgames.aether.entity.tile.TileEntityFreezer;
import com.gildedgames.aether.entity.tile.TileEntityIncubator;
import com.gildedgames.aether.entity.tile.TileEntityTreasureChest;
import com.gildedgames.aether.gui.GuiEnchanter;
import com.gildedgames.aether.gui.GuiFreezer;
import com.gildedgames.aether.gui.GuiIncubator;
import com.gildedgames.aether.gui.GuiTreasureChest;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.minecraft.client.gui.screen.ScreenBase;
import net.minecraft.entity.player.PlayerBase;
import net.minecraft.inventory.InventoryBase;
import net.modificationstation.stationapi.api.event.registry.GuiHandlerRegistryEvent;
import net.modificationstation.stationapi.api.registry.GuiHandlerRegistry;
import net.modificationstation.stationapi.api.registry.Identifier;
import uk.co.benjiweber.expressions.tuple.BiTuple;

public class GuiListener
{

    @Environment(EnvType.CLIENT)
    @EventListener
    public void registerGuiHandlers(GuiHandlerRegistryEvent event)
    {
        GuiHandlerRegistry registry = event.registry;
        registry.registerValueNoMessage(Identifier.of(AetherMod.MODID, "treasure_chest"), BiTuple.of(this::openTreasureChest, TileEntityTreasureChest::new));
        registry.registerValueNoMessage(Identifier.of(AetherMod.MODID, "freezer"), BiTuple.of(this::openFreezer, TileEntityFreezer::new));
        registry.registerValueNoMessage(Identifier.of(AetherMod.MODID, "enchanter"), BiTuple.of(this::openEnchanter, TileEntityEnchanter::new));
        registry.registerValueNoMessage(Identifier.of(AetherMod.MODID, "incubator"), BiTuple.of(this::openIncubator, TileEntityIncubator::new));

    }

    @Environment(EnvType.CLIENT)
    public ScreenBase openTreasureChest(PlayerBase player, InventoryBase inventoryBase)
    {
        return new GuiTreasureChest(player.inventory, (TileEntityTreasureChest) inventoryBase);
    }

    @Environment(EnvType.CLIENT)
    public ScreenBase openFreezer(PlayerBase player, InventoryBase inventoryBase)
    {
        return new GuiFreezer(player.inventory, (TileEntityFreezer) inventoryBase);
    }

    @Environment(EnvType.CLIENT)
    public ScreenBase openEnchanter(PlayerBase player, InventoryBase inventoryBase)
    {
        return new GuiEnchanter(player.inventory, (TileEntityEnchanter) inventoryBase);
    }

    @Environment(EnvType.CLIENT)
    public ScreenBase openIncubator(PlayerBase player, InventoryBase inventoryBase)
    {
        return new GuiIncubator(player.inventory, (TileEntityIncubator) inventoryBase);
    }
}