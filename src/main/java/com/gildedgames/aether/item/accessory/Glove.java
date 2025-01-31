package com.gildedgames.aether.item.accessory;

import com.matthewperiut.accessoryapi.api.Accessory;
import com.matthewperiut.accessoryapi.api.render.AccessoryRenderer;
import com.matthewperiut.accessoryapi.api.render.HasCustomRenderer;
import com.matthewperiut.accessoryapi.api.render.builtin.GloveRenderer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.item.ItemInstance;
import net.modificationstation.stationapi.api.registry.Identifier;
import net.modificationstation.stationapi.api.template.item.TemplateItemBase;

import java.awt.*;
import java.util.Optional;

public class Glove extends TemplateItemBase implements Accessory, HasCustomRenderer
{
    public final int strength;
    private final int color;
    public String texture;

    public Glove(Identifier identifier, int strength, String texture, int color)
    {
        super(identifier);
        this.texture = texture;
        this.color = color;
        this.strength = strength;
        this.setMaxStackSize(1);
        this.setDurability(500);
    }

    public Glove(Identifier identifier, int strength, String texture)
    {
        this(identifier, strength, texture, 16777215);
    }

    @Override
    public String[] getAccessoryTypes(ItemInstance item)
    {
        return new String[]{"gloves"};
    }

    @Environment(EnvType.CLIENT)
    public int getColourMultiplier(int i)
    {
        return color;
    }

    AccessoryRenderer renderer;

    @Override
    public Optional<AccessoryRenderer> getRenderer()
    {
        return Optional.ofNullable(renderer);
    }

    @Override
    public void constructRenderer()
    {
        renderer = new GloveRenderer(texture).withColor(new Color(color));
    }
}
