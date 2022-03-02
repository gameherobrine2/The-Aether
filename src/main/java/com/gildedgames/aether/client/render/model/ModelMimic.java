package com.gildedgames.aether.client.render.model;
import com.gildedgames.aether.entity.mobs.EntityMimic;

import net.minecraft.client.model.Cuboid;
import net.minecraft.client.render.entity.model.EntityModelBase;

public class ModelMimic extends EntityModelBase {
    Cuboid box;
    Cuboid boxLid;
    Cuboid leftLeg;
    Cuboid rightLeg;
    
    public ModelMimic() {
        (this.box = new Cuboid(0, 0)).method_1817(-8.0f, 0.0f, -8.0f, 16, 10, 16);
        this.box.setRotationPoint(0.0f, -24.0f, 0.0f);
        (this.boxLid = new Cuboid(16, 10)).method_1817(0.0f, 0.0f, 0.0f, 16, 6, 16);
        this.boxLid.setRotationPoint(-8.0f, -24.0f, 8.0f);
        (this.leftLeg = new Cuboid(0, 0)).method_1817(-3.0f, 0.0f, -3.0f, 6, 15, 6);
        this.leftLeg.setRotationPoint(-4.0f, -15.0f, 0.0f);
        (this.rightLeg = new Cuboid(0, 0)).method_1817(-3.0f, 0.0f, -3.0f, 6, 15, 6);
        this.rightLeg.setRotationPoint(4.0f, -15.0f, 0.0f);
    }
    
    public void render1(final float f, final float f1, final float f2, final float f3, final float f4, final float f5, final EntityMimic mimic) {
        this.setAngles(f, f1, f2, f3, f4, f5);
        this.boxLid.pitch = 3.1415927f - mimic.mouth;
        this.rightLeg.pitch = mimic.legs;
        this.leftLeg.pitch = -mimic.legs;
        this.box.method_1815(f5);
    }
    
    public void render2(final float f, final float f1, final float f2, final float f3, final float f4, final float f5, final EntityMimic mimic) {
        this.boxLid.method_1815(f5);
        this.leftLeg.method_1815(f5);
        this.rightLeg.method_1815(f5);
    }
}
