package com.gildedgames.aether.block;

import java.util.Random;

import com.gildedgames.aether.event.listener.TextureListener;
import com.gildedgames.aether.registry.AetherItems;

import net.minecraft.block.material.Material;
import net.modificationstation.stationapi.api.registry.Identifier;
import net.modificationstation.stationapi.api.template.block.TemplateBlockBase;

public class BlockZaniteOre extends TemplateBlockBase {
    public BlockZaniteOre(final Identifier id) {
        super(id, Material.STONE);
    }
    public int getTextureForSide(int side, int meta) {
    	return TextureListener.sprZaniteOre;
    }
    @Override
    public int getDropId(final int meta, final Random rand) {
        return AetherItems.Zanite.id;
    }
}
