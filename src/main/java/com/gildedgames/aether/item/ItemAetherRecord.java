package com.gildedgames.aether.item;
import net.minecraft.block.Jukebox;

import org.jetbrains.annotations.NotNull;

import com.gildedgames.aether.mixin.MinecraftClientAccessor;

import net.minecraft.block.BlockBase;
import net.minecraft.level.Level;
import net.modificationstation.stationapi.api.registry.Identifier;
import net.modificationstation.stationapi.api.template.item.TemplateRecord;
import net.minecraft.entity.player.PlayerBase;
import net.minecraft.item.ItemInstance;
import net.minecraft.item.Record;

public class ItemAetherRecord extends TemplateRecord {
    public ItemAetherRecord(final @NotNull Identifier identifier, final String title) {
        super(identifier, title);
    }
    
    @Override
    public boolean useOnTile(final ItemInstance item, final PlayerBase player, final Level level, final int x, final int y, final int z, final int facing) {
        if (level.getTileId(x, y, z) != BlockBase.JUKEBOX.id || level.getTileMeta(x, y, z) != 0) {
            return false;
        }
        if (level.isServerSide) {
            return true;
        }
        ((Jukebox)BlockBase.JUKEBOX).setRecord(level, x, y, z, this.id);
        level.playLevelEvent(null, 1005, x, y, z, this.id);
        MinecraftClientAccessor.getMCinstance().overlay.showJukeboxMessage("Noisestorm - " + this.title);
        --item.count;
        return true;
    }
}
