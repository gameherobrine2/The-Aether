package com.gildedgames.aether.item;
import org.jetbrains.annotations.NotNull;

import com.gildedgames.aether.event.listener.TextureListener;

import net.minecraft.entity.player.PlayerBase;
import net.minecraft.level.Level;
import net.modificationstation.stationapi.api.registry.Identifier;
import net.modificationstation.stationapi.api.template.item.TemplateItemBase;
import net.minecraft.item.ItemInstance;
import net.minecraft.item.ItemBase;

public class ItemAether extends TemplateItemBase {
    
    public ItemAether(final @NotNull Identifier identifier) {
        super(identifier);
    }
}
