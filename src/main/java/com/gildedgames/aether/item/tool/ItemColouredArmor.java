package com.gildedgames.aether.item.tool;

import net.minecraft.item.armour.Armour;
import net.modificationstation.stationapi.api.client.item.ArmourTextureProvider;
import net.modificationstation.stationapi.api.registry.Identifier;
import net.modificationstation.stationapi.api.template.item.armour.TemplateArmour;

import static com.gildedgames.aether.event.listener.TextureListener.MOD_ID;

public class ItemColouredArmor extends TemplateArmour implements ArmourTextureProvider
{
    private int colour;
    private String name;

    public ItemColouredArmor(final Identifier i, final int j, final String s, final int l, final int col)
    {
        super(i, j, 0, l);
        this.name = s;
        this.colour = col;
    }

    @Override
    public int getColourMultiplier(final int i)
    {
        return this.colour;
    }

    @Override
    public Identifier getTexture(Armour armour)
    {
        return Identifier.of(MOD_ID, name);
    }
}
