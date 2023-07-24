package com.gildedgames.aether.item.tool;

import com.gildedgames.aether.entity.projectile.EntityFloatingBlock;
import net.minecraft.block.BlockBase;
import net.minecraft.entity.player.PlayerBase;
import net.minecraft.item.ItemInstance;
import net.minecraft.item.tool.ToolMaterial;
import net.minecraft.level.Level;
import net.modificationstation.stationapi.api.block.BlockState;
import net.modificationstation.stationapi.api.registry.Identifier;
import net.modificationstation.stationapi.api.tag.TagKey;
import net.modificationstation.stationapi.api.template.item.tool.TemplatePickaxe;
import org.jetbrains.annotations.NotNull;

public class ItemGravititePickaxe extends TemplatePickaxe
{
    public ItemGravititePickaxe(final @NotNull Identifier identifier, final ToolMaterial enumtoolmaterial)
    {
        super(identifier, enumtoolmaterial);
    }

    @Override
    public boolean useOnTile(ItemInstance item, PlayerBase player, Level level, int x, int y, int z, int idk)
    {
        if (!level.isServerSide)
        {
            BlockState b = level.getBlockState(x, y, z);

            TagKey<BlockBase> tag = this.getEffectiveBlocks(item);
            if (b.isIn(tag))
            {
                final int blockID = level.getTileId(x, y, z);
                final int metadata = level.getTileMeta(x, y, z);
                final EntityFloatingBlock floating = new EntityFloatingBlock(level, x + 0.5f, y + 0.5f, z + 0.5f, blockID, metadata);
                level.spawnEntity(floating);
                item.applyDamage(1, player);
            }

            return super.useOnTile(item, player, level, x, y, z, idk);
        }
        return false;
    }
}
