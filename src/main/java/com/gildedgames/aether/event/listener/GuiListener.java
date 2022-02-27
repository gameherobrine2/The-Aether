package com.gildedgames.aether.event.listener;

import com.gildedgames.aether.Aether;
import com.gildedgames.aether.gui.GuiTreasureChest;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.minecraft.client.gui.screen.ScreenBase;
import net.minecraft.entity.player.PlayerBase;
import net.minecraft.inventory.InventoryBase;
import net.minecraft.tileentity.TileEntityChest;
import net.modificationstation.stationapi.api.event.registry.GuiHandlerRegistryEvent;
import net.modificationstation.stationapi.api.registry.GuiHandlerRegistry;
import net.modificationstation.stationapi.api.registry.Identifier;
import uk.co.benjiweber.expressions.tuple.BiTuple;

public class GuiListener {

    @Environment(EnvType.CLIENT)
    @EventListener
    public void registerGuiHandlers(GuiHandlerRegistryEvent event) {
        GuiHandlerRegistry registry = event.registry;
        registry.registerValueNoMessage(Identifier.of(Aether.MODID, "treasurechest"), BiTuple.of(this::openTreasureChest, TileEntityChest::new));
    }

    @Environment(EnvType.CLIENT)
    public ScreenBase openTreasureChest(PlayerBase player, InventoryBase inventoryBase) {
        return new GuiTreasureChest(player.inventory, (TileEntityChest) inventoryBase, 0);
    }
}