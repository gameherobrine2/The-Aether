package com.gildedgames.aether.item.tool;

import com.gildedgames.aether.entity.projectile.EntityAetherLightning;
import net.minecraft.entity.monster.ZombiePigman;
import net.minecraft.entity.monster.Skeleton;
import net.minecraft.entity.monster.Zombie;

import com.gildedgames.aether.utils.EnumElement;

import net.minecraft.entity.EntityBase;
import net.minecraft.entity.Living;
import net.minecraft.block.BlockBase;
import net.minecraft.item.ItemInstance;
import net.minecraft.item.tool.ToolMaterial;
import net.modificationstation.stationapi.api.registry.Identifier;
import net.modificationstation.stationapi.api.template.item.tool.TemplateSword;

import java.util.ArrayList;

import org.jetbrains.annotations.NotNull;

public class ItemSwordElemental extends TemplateSword {
    public static ArrayList<Class<? extends Living>> undead;
    private int weaponDamage;
    private int holyDamage;
    private EnumElement element;
    public static Identifier elementalSwordTexture;
    private int colour;
    
    public ItemSwordElemental(final @NotNull Identifier identifier, final EnumElement element, final int colour) {
        super(identifier, ToolMaterial.field_1691); // (ToolMaterial) field_1691 = EMERALD
        this.maxStackSize = 1;
        this.setDurability((element == EnumElement.Holy) ? 128 : 32);
        this.weaponDamage = 4;
        this.holyDamage = 20;
        this.element = element;
        this.colour = colour;
    }
    
    @Override
    public float getStrengthOnBlock(final ItemInstance item, final BlockBase tile) {
        return 1.5f;
    }
    
    @Override
    public boolean postMine(final ItemInstance itemstack, final int i, final int j, final int k, final int l, final Living damageTarget) {
        itemstack.applyDamage(2, damageTarget);
        return true;
    }
    
    @Override
    public boolean postHit(final ItemInstance itemstack, final Living damageSource, final Living damageTarget) {
        if (this.element == EnumElement.Fire) {
            damageSource.fire = 600;
        }
        else if (this.element == EnumElement.Lightning) {
            damageTarget.level.spawnEntity(new EntityAetherLightning(damageSource.level, damageSource.x, damageSource.y, damageSource.z));
            //todo: aether lightning ModLoader.getMinecraftInstance().level.spawnEntity(new EntityAetherLightning(ModLoader.getMinecraftInstance().level, (int)damageSource.x, (int)damageSource.y, (int)damageSource.z));
        }
        itemstack.applyDamage(1, damageTarget);
        return true;
    }
    
    @Override
    public int getAttack(final EntityBase entity) {
        if (this.element == EnumElement.Holy && entity instanceof Living) {
            final Living living = (Living)entity;
            for (final Class<? extends Living> cls : ItemSwordElemental.undead) {
                if (living.getClass().isAssignableFrom((Class)cls)) {
                    return this.holyDamage;
                }
            }
        }
        return this.weaponDamage;
    }
    
    @Override
    public int getColourMultiplier(final int i) {
        return this.colour;
    }
    
    @Override
    public boolean isRendered3d() {
        return true;
    }
    
    static {
        (ItemSwordElemental.undead = new ArrayList<Class<? extends Living>>()).add(Zombie.class);
        ItemSwordElemental.undead.add(Skeleton.class);
        ItemSwordElemental.undead.add(ZombiePigman.class);
    }
}
