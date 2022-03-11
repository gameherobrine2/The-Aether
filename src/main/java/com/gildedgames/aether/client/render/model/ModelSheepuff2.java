package com.gildedgames.aether.client.render.model;

import net.minecraft.client.model.Cuboid;
import net.minecraft.client.render.entity.model.AnimalQuadrupedModelBase;

public class ModelSheepuff2 extends AnimalQuadrupedModelBase {
    public ModelSheepuff2() {
        super(12, 0.0f);
        (this.cuboid1 = new Cuboid(0, 0)).method_1818(-3.0f, -4.0f, -6.0f, 6, 6, 8, 0.0f);
        this.cuboid1.setRotationPoint(0.0f, 6.0f, -8.0f);
        (this.cuboid2 = new Cuboid(28, 8)).method_1818(-4.0f, -10.0f, -7.0f, 8, 16, 6, 0.0f);
        this.cuboid2.setRotationPoint(0.0f, 5.0f, 2.0f);
    }
}
