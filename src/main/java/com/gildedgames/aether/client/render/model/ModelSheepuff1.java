package com.gildedgames.aether.client.render.model;

import net.minecraft.client.model.Cuboid;
import net.minecraft.client.render.entity.model.AnimalQuadrupedModelBase;

public class ModelSheepuff1 extends AnimalQuadrupedModelBase
{
    public ModelSheepuff1()
    {
        super(12, 0.0f);
        (this.cuboid1 = new Cuboid(0, 0)).method_1818(-3.0f, -4.0f, -4.0f, 6, 6, 6, 0.6f);
        this.cuboid1.setRotationPoint(0.0f, 6.0f, -8.0f);
        (this.cuboid2 = new Cuboid(28, 8)).method_1818(-4.0f, -10.0f, -7.0f, 8, 16, 6, 1.75f);
        this.cuboid2.setRotationPoint(0.0f, 5.0f, 2.0f);
        final float f = 0.5f;
        (this.cuboid3 = new Cuboid(0, 16)).method_1818(-2.0f, 0.0f, -2.0f, 4, 6, 4, f);
        this.cuboid3.setRotationPoint(-3.0f, 12.0f, 7.0f);
        (this.cuboid4 = new Cuboid(0, 16)).method_1818(-2.0f, 0.0f, -2.0f, 4, 6, 4, f);
        this.cuboid4.setRotationPoint(3.0f, 12.0f, 7.0f);
        (this.cuboid5 = new Cuboid(0, 16)).method_1818(-2.0f, 0.0f, -2.0f, 4, 6, 4, f);
        this.cuboid5.setRotationPoint(-3.0f, 12.0f, -5.0f);
        (this.cuboid6 = new Cuboid(0, 16)).method_1818(-2.0f, 0.0f, -2.0f, 4, 6, 4, f);
        this.cuboid6.setRotationPoint(3.0f, 12.0f, -5.0f);
    }
}
