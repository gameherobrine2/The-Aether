package com.gildedgames.aether.item.tool;

import com.gildedgames.aether.AetherMod;
import com.gildedgames.aether.item.misc.ItemAether;
import net.minecraft.block.BlockBase;
import net.minecraft.entity.EntityBase;
import net.minecraft.entity.Living;
import net.minecraft.entity.player.PlayerBase;
import net.minecraft.item.ItemInstance;
import net.minecraft.item.tool.ToolMaterial;
import net.modificationstation.stationapi.api.registry.Identifier;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

public class ItemVampireBlade extends ItemAether
{
    private int weaponDamage;
    private static Random random;

    public ItemVampireBlade(final @NotNull Identifier identifier)
    {
        super(identifier);
        this.maxStackSize = 1;
        this.setDurability(ToolMaterial.field_1691.getDurability()); // (ToolMaterial) field_1691 = emerald
        this.weaponDamage = 4 + ToolMaterial.field_1691.getAttackDamage() * 2;
    }

    @Override
    public float getStrengthOnBlock(final ItemInstance item, final BlockBase tile)
    {
        return 1.5f;
    }

    @Override
    public boolean postHit(final ItemInstance itemstack, final Living damageSource, final Living damageTarget)
    {
        final PlayerBase player = (PlayerBase) damageTarget;
        if (player.health < AetherMod.getPlayerHandler(player).maxHealth)
        {
            final PlayerBase playerBase = player;
            ++playerBase.health;
        }
        itemstack.applyDamage(1, damageTarget);
        return true;
    }

    @Override
    public boolean postMine(final ItemInstance itemstack, final int i, final int j, final int k, final int l, final Living damageTarget)
    {
        itemstack.applyDamage(2, damageTarget);
        return true;
    }

    @Override
    public int getAttack(final EntityBase entity)
    {
        return this.weaponDamage;
    }

    @Override
    public boolean isRendered3d()
    {
        return true;
    }

    static
    {
        ItemVampireBlade.random = new Random();
    }
}
