package com.gildedgames.aether.client.render.model;
import com.gildedgames.aether.entity.animal.EntityFlyingCow;

import net.minecraft.client.model.Cuboid;
import net.minecraft.client.render.entity.model.EntityModelBase;

public class ModelFlyingCow2 extends EntityModelBase {
    private Cuboid leftWingInner;
    private Cuboid leftWingOuter;
    private Cuboid rightWingInner;
    private Cuboid rightWingOuter;
    public static EntityFlyingCow flyingcow;
    
    public ModelFlyingCow2() {
        this.leftWingInner = new Cuboid(0, 0);
        this.leftWingOuter = new Cuboid(20, 0);
        this.rightWingInner = new Cuboid(0, 0);
        this.rightWingOuter = new Cuboid(40, 0);
        this.leftWingInner.method_1818(-1.0f, -8.0f, -4.0f, 2, 16, 8, 0.0f);
        this.leftWingOuter.method_1818(-1.0f, -8.0f, -4.0f, 2, 16, 8, 0.0f);
        this.rightWingInner.method_1818(-1.0f, -8.0f, -4.0f, 2, 16, 8, 0.0f);
        this.rightWingOuter.method_1818(-1.0f, -8.0f, -4.0f, 2, 16, 8, 0.0f);
        this.rightWingOuter.yaw = 3.1415927f;
    }
    
    @Override
    public void render(final float f, final float f1, final float f2, final float f3, final float f4, final float f5) {
        final float wingBend = -(float)Math.acos((double)ModelFlyingCow2.flyingcow.wingFold);
        float x = 32.0f * ModelFlyingCow2.flyingcow.wingFold / 4.0f;
        final float y = -32.0f * (float)Math.sqrt((double)(1.0f - ModelFlyingCow2.flyingcow.wingFold * ModelFlyingCow2.flyingcow.wingFold)) / 4.0f;
        final float z = 0.0f;
        float x2 = x * (float)Math.cos((double)ModelFlyingCow2.flyingcow.wingAngle) - y * (float)Math.sin((double)ModelFlyingCow2.flyingcow.wingAngle);
        float y2 = x * (float)Math.sin((double)ModelFlyingCow2.flyingcow.wingAngle) + y * (float)Math.cos((double)ModelFlyingCow2.flyingcow.wingAngle);
        this.leftWingInner.setRotationPoint(4.0f + x2, y2 + 6.0f, z);
        this.rightWingInner.setRotationPoint(-4.0f - x2, y2 + 6.0f, z);
        x *= 3.0f;
        x2 = x * (float)Math.cos((double)ModelFlyingCow2.flyingcow.wingAngle) - y * (float)Math.sin((double)ModelFlyingCow2.flyingcow.wingAngle);
        y2 = x * (float)Math.sin((double)ModelFlyingCow2.flyingcow.wingAngle) + y * (float)Math.cos((double)ModelFlyingCow2.flyingcow.wingAngle);
        this.leftWingOuter.setRotationPoint(4.0f + x2, y2 + 6.0f, z);
        this.rightWingOuter.setRotationPoint(-4.0f - x2, y2 + 6.0f, z);
        this.leftWingInner.roll = ModelFlyingCow2.flyingcow.wingAngle + wingBend + 1.5707964f;
        this.leftWingOuter.roll = ModelFlyingCow2.flyingcow.wingAngle - wingBend + 1.5707964f;
        this.rightWingInner.roll = -(ModelFlyingCow2.flyingcow.wingAngle + wingBend - 1.5707964f);
        this.rightWingOuter.roll = -(ModelFlyingCow2.flyingcow.wingAngle - wingBend + 1.5707964f);
        this.leftWingOuter.method_1815(f5);
        this.leftWingInner.method_1815(f5);
        this.rightWingOuter.method_1815(f5);
        this.rightWingInner.method_1815(f5);
    }
    
    @Override
    public void setAngles(final float f, final float f1, final float f2, final float f3, final float f4, final float f5) {
    }
}
