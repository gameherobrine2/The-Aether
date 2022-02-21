package com.gildedgames.aether.client.render.model;
import net.minecraft.client.model.Cuboid;
import net.minecraft.client.render.entity.model.AnimalQuadrupedModelBase;

public class ModelFlyingCow1 extends AnimalQuadrupedModelBase {
    Cuboid udders;
    Cuboid horn1;
    Cuboid horn2;
    
    public ModelFlyingCow1() {
        super(12, 0.0f);
        (this.cuboid1 = new Cuboid(0, 0)).method_1818(-4.0f, -4.0f, -6.0f, 8, 8, 6, 0.0f);
        this.cuboid1.setRotationPoint(0.0f, 4.0f, -8.0f);
        (this.horn1 = new Cuboid(22, 0)).method_1818(-4.0f, -5.0f, -4.0f, 1, 3, 1, 0.0f);
        this.horn1.setRotationPoint(0.0f, 3.0f, -7.0f);
        (this.horn2 = new Cuboid(22, 0)).method_1818(3.0f, -5.0f, -4.0f, 1, 3, 1, 0.0f);
        this.horn2.setRotationPoint(0.0f, 3.0f, -7.0f);
        (this.udders = new Cuboid(52, 0)).method_1818(-2.0f, -3.0f, 0.0f, 4, 6, 2, 0.0f);
        this.udders.setRotationPoint(0.0f, 14.0f, 6.0f);
        this.udders.pitch = 1.570796f;
        (this.cuboid2 = new Cuboid(18, 4)).method_1818(-6.0f, -10.0f, -7.0f, 12, 18, 10, 0.0f);
        this.cuboid2.setRotationPoint(0.0f, 5.0f, 2.0f);
        final Cuboid cuboid3 = this.cuboid3;
        --cuboid3.rotationPointX;
        final Cuboid cuboid4 = this.cuboid4;
        ++cuboid4.rotationPointX;
        final Cuboid cuboid5 = this.cuboid3;
        cuboid5.rotationPointZ += 0.0f;
        final Cuboid cuboid6 = this.cuboid4;
        cuboid6.rotationPointZ += 0.0f;
        final Cuboid cuboid7 = this.cuboid5;
        --cuboid7.rotationPointX;
        final Cuboid cuboid8 = this.cuboid6;
        ++cuboid8.rotationPointX;
        final Cuboid cuboid9 = this.cuboid5;
        --cuboid9.rotationPointZ;
        final Cuboid cuboid10 = this.cuboid6;
        --cuboid10.rotationPointZ;
    }
    
    @Override
    public void render(final float f, final float f1, final float f2, final float f3, final float f4, final float f5) {
        super.render(f, f1, f2, f3, f4, f5);
        this.horn1.method_1815(f5);
        this.horn2.method_1815(f5);
        this.udders.method_1815(f5);
    }
    
    @Override
    public void setAngles(final float f, final float f1, final float f2, final float f3, final float f4, final float f5) {
        super.setAngles(f, f1, f2, f3, f4, f5);
        this.horn1.yaw = this.cuboid1.yaw;
        this.horn1.pitch = this.cuboid1.pitch;
        this.horn2.yaw = this.cuboid1.yaw;
        this.horn2.pitch = this.cuboid1.pitch;
    }
}
