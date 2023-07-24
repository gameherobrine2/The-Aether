package com.gildedgames.aether.item.tool;

import com.gildedgames.aether.item.misc.ItemAether;
import net.minecraft.block.BlockBase;
import net.minecraft.entity.EntityBase;
import net.minecraft.entity.Living;
import net.minecraft.entity.player.PlayerBase;
import net.minecraft.item.ItemInstance;
import net.minecraft.item.tool.ToolMaterial;
import net.minecraft.level.Level;
import net.modificationstation.stationapi.api.registry.Identifier;
import org.jetbrains.annotations.NotNull;

public class ItemNotchHammer extends ItemAether
{
    private int weaponDamage;

    public ItemNotchHammer(final @NotNull Identifier identifier)
    {
        super(identifier);
        this.maxStackSize = 1;
        // field_1690 is IRON
        this.setDurability(ToolMaterial.field_1690.getDurability());
        this.weaponDamage = 4 + ToolMaterial.field_1690.getAttackDamage() * 2;
    }

    @Override
    public float getStrengthOnBlock(final ItemInstance item, final BlockBase tile)
    {
        return 1.5f;
    }

    @Override
    public boolean postHit(final ItemInstance itemstack, final Living damageSource, final Living damageTarget)
    {
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
    public ItemInstance use(final ItemInstance item, final Level level, final PlayerBase player)
    {
        item.applyDamage(1, player);
        level.playSound((EntityBase) player, "mob.ghast.fireball", 1.0f, 1.0f / (ItemNotchHammer.rand.nextFloat() * 0.4f + 0.8f));
        if (!level.isServerSide)
        {
            /*TODO: NotchWave final EntityNotchWave notchwave = new EntityNotchWave(level, player);
            level.spawnEntity(notchwave);*/
        }
        return item;
    }

    @Override
    public boolean isRendered3d()
    {
        return true;
    }
}
