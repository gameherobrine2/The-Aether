package com.gildedgames.aether.client.render.particle;

import net.minecraft.client.render.particle.Portal;
import net.minecraft.level.Level;

public class AetherPortal extends Portal {

    public AetherPortal(Level arg, double d, double d1, double d2, double d3, double d4, double d5) {
        super(arg, d, d1, d2, d3, d4, d5);
        field_2642 = field_2643 = field_2644 = rand.nextFloat() * 0.6F + 0.4F;
        field_2642 *= 0.2F;
        field_2643 *= 0.2F;
    }
}
