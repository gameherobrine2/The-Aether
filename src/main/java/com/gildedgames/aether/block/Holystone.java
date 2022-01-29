package com.gildedgames.aether.block;

import net.minecraft.block.material.Material;
import net.modificationstation.stationapi.api.block.HasMetaNamedBlockItem;
import net.modificationstation.stationapi.api.registry.Identifier;
import net.modificationstation.stationapi.api.template.block.TemplateBlockBase;

@HasMetaNamedBlockItem
public class Holystone extends TemplateBlockBase {

    public Holystone(Identifier identifier) {
        super(identifier, Material.STONE);
    }
}
