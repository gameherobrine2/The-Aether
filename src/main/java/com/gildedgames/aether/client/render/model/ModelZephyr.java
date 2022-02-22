package com.gildedgames.aether.client.render.model;

import net.minecraft.client.model.Cuboid;
import net.minecraft.client.render.entity.model.EntityModelBase;

public class ModelZephyr extends EntityModelBase {
    Cuboid body;
    
    public ModelZephyr() {
        final byte byte0 = -16;
        (this.body = new Cuboid(0, 0)).method_1817(-8.0f, -4.0f, -8.0f, 10, 7, 12);
        final Cuboid body = this.body;
        body.rotationPointY += 24 + byte0;
    }
    
    @Override
    public void render(final float f, final float f1, final float f2, final float f3, final float f4, final float f5) {
        this.setAngles(f, f1, f2, f3, f4, f5);
        this.body.method_1815(f5);
    }
}
