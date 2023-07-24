package com.gildedgames.aether.client.render.model;

import net.minecraft.client.model.Cuboid;
import net.minecraft.client.render.entity.model.EntityModelBase;

public class ModelAerwhale extends EntityModelBase
{
    Cuboid body;
    Cuboid body2;
    Cuboid body3;
    Cuboid fin1;
    Cuboid fin2;
    Cuboid fin3;
    Cuboid fin4;

    public ModelAerwhale()
    {
        (this.body2 = new Cuboid(0, 0)).method_1817(-2.5f, -2.5f, -2.5f, 5, 5, 5);
        (this.body3 = new Cuboid(0, 10)).method_1817(-1.5f, -1.5f, 2.5f, 3, 3, 4);
        (this.fin1 = new Cuboid(0, 17)).method_1817(-7.5f, -0.5f, 2.5f, 8, 1, 4);
        (this.fin2 = new Cuboid(0, 17)).method_1817(-0.5f, -0.5f, 2.5f, 8, 1, 4);
        (this.fin3 = new Cuboid(0, 22)).method_1817(-7.5f, 1.5f, -6.5f, 4, 1, 2);
        (this.fin4 = new Cuboid(0, 22)).method_1817(3.5f, 1.5f, -6.5f, 4, 1, 2);
        (this.body = new Cuboid(20, 0)).method_1817(-3.5f, -3.5f, -12.5f, 7, 6, 10);
    }

    @Override
    public void render(final float f, final float f1, final float f2, final float f3, final float f4, final float f5)
    {
        this.setAngles(f, f1, f2, f3, f4, f5);
        this.body.method_1815(f5);
        this.body2.method_1815(f5);
        this.body3.method_1815(f5);
        this.fin1.method_1815(f5);
        this.fin2.method_1815(f5);
        this.fin3.method_1815(f5);
        this.fin4.method_1815(f5);
    }
}
