package com.gildedgames.aether.item.tool;

import net.minecraft.block.BlockBase;
import net.minecraft.entity.player.PlayerBase;
import net.minecraft.item.ItemInstance;
import net.minecraft.item.tool.ToolMaterial;
import net.minecraft.util.hit.HitType;
import net.modificationstation.stationapi.api.item.CustomReachProvider;
import net.modificationstation.stationapi.api.registry.Identifier;
import net.modificationstation.stationapi.api.template.item.tool.TemplateShovel;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

public class ItemValkyrieSpade extends TemplateShovel implements CustomReachProvider
{
    private static BlockBase[] blocksEffectiveAgainst;
    private static Random random;

    public ItemValkyrieSpade(final @NotNull Identifier identifier, final ToolMaterial enumtoolmaterial)
    {
        super(identifier, enumtoolmaterial);
    }

    @Override
    public double getReach(ItemInstance itemInstance, PlayerBase player, HitType type, double currentReach)
    {
        return 10.f;
    }
}
