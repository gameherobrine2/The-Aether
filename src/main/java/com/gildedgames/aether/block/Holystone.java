package com.gildedgames.aether.block;

import com.gildedgames.aether.client.texture.AetherTextures;
import net.minecraft.block.material.Material;
import net.minecraft.level.BlockView;
import net.modificationstation.stationapi.api.block.HasMetaNamedBlockItem;
import net.modificationstation.stationapi.api.client.model.Model;
import net.modificationstation.stationapi.api.client.model.block.BlockInventoryModelProvider;
import net.modificationstation.stationapi.api.client.model.block.BlockWorldModelProvider;
import net.modificationstation.stationapi.api.registry.Identifier;
import net.modificationstation.stationapi.api.template.block.TemplateBlockBase;

@HasMetaNamedBlockItem
public class Holystone extends TemplateBlockBase implements BlockInventoryModelProvider, BlockWorldModelProvider {

    public Holystone(Identifier identifier) {
        super(identifier, Material.STONE);
    }

    @Override
    public Model getInventoryModel(int meta) {
        return meta > 1 ? AetherTextures.MOSSY_HOLYSTONE : AetherTextures.HOLYSTONE;
    }

    @Override
    public Model getCustomWorldModel(BlockView blockView, int x, int y, int z) {
        return blockView.getTileMeta(x, y, z) > 1 ? AetherTextures.MOSSY_HOLYSTONE : AetherTextures.HOLYSTONE;
    }
}
