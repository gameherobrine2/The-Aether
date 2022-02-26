package com.gildedgames.aether.block;

import com.gildedgames.aether.event.listener.TextureListener;

import net.minecraft.block.material.Material;
import net.modificationstation.stationapi.api.block.HasMetaNamedBlockItem;
import net.modificationstation.stationapi.api.registry.Identifier;
import net.modificationstation.stationapi.api.template.block.TemplateBlockBase;

@HasMetaNamedBlockItem
public class Holystone extends TemplateBlockBase {

    public Holystone(Identifier identifier) {
        super(identifier, Material.STONE);
    }
    
    public int getTextureForSide(int side, int meta) {
    	switch(meta) {
    	case 0:
    		return TextureListener.sprHolystone;
    	default:
    		return TextureListener.sprMossyHolystone;
    	}
    }
}
