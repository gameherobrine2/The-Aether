package com.gildedgames.aether.block;

import com.gildedgames.aether.event.listener.TextureListener;
import com.gildedgames.aether.registry.AetherItems;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerBase;
import net.minecraft.level.Level;
import net.modificationstation.stationapi.api.registry.Identifier;
import net.modificationstation.stationapi.api.template.block.TemplateBlockBase;

public class BlockQuicksoil extends TemplateBlockBase
{
    public BlockQuicksoil(final Identifier id)
    {
        super(id, Material.SAND);
        this.slipperiness = 1.1f;
    }

    public int getTextureForSide(int side, int meta)
    {
        return TextureListener.sprQuicksoil;
    }

    @Override
    public void afterBreak(final Level level, final PlayerBase playerBase, final int x, final int y, final int z, final int meta)
    {
        super.afterBreak(level, playerBase, x, y, z, meta);
        if (playerBase.getHeldItem() != null)
            if (meta == 0 && playerBase.getHeldItem().getType() == AetherItems.ShovelSkyroot)
            {
                this.drop(level, x, y, z, meta);
            }
    }
}
