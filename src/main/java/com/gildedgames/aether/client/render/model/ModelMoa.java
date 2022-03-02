package com.gildedgames.aether.client.render.model;
import net.minecraft.util.maths.MathHelper;
import java.util.Random;
import net.minecraft.client.model.Cuboid;
import net.minecraft.client.render.entity.model.EntityModelBase;

public class ModelMoa extends EntityModelBase {
    public Cuboid head;
    public Cuboid body;
    public Cuboid legs;
    public Cuboid legs2;
    public Cuboid wings;
    public Cuboid wings2;
    public Cuboid jaw;
    public Cuboid neck;
    public Cuboid feather1;
    public Cuboid feather2;
    public Cuboid feather3;
    public Random random;
    
    public ModelMoa() {
        final byte byte0 = 16;
        this.random = new Random();
        (this.head = new Cuboid(0, 13)).method_1818(-2.0f, -4.0f, -6.0f, 4, 4, 8, 0.0f);
        this.head.setRotationPoint(0.0f, (float)(-8 + byte0), -4.0f);
        (this.jaw = new Cuboid(24, 13)).method_1818(-2.0f, -1.0f, -6.0f, 4, 1, 8, -0.1f);
        this.jaw.setRotationPoint(0.0f, (float)(-8 + byte0), -4.0f);
        (this.body = new Cuboid(0, 0)).method_1818(-3.0f, -3.0f, 0.0f, 6, 8, 5, 0.0f);
        this.body.setRotationPoint(0.0f, (float)(0 + byte0), 0.0f);
        (this.legs = new Cuboid(22, 0)).method_1817(-1.0f, -1.0f, -1.0f, 2, 9, 2);
        this.legs.setRotationPoint(-2.0f, (float)(0 + byte0), 1.0f);
        (this.legs2 = new Cuboid(22, 0)).method_1817(-1.0f, -1.0f, -1.0f, 2, 9, 2);
        this.legs2.setRotationPoint(2.0f, (float)(0 + byte0), 1.0f);
        (this.wings = new Cuboid(52, 0)).method_1817(-1.0f, -0.0f, -1.0f, 1, 8, 4);
        this.wings.setRotationPoint(-3.0f, (float)(-4 + byte0), 0.0f);
        (this.wings2 = new Cuboid(52, 0)).method_1817(0.0f, -0.0f, -1.0f, 1, 8, 4);
        this.wings2.setRotationPoint(3.0f, (float)(-4 + byte0), 0.0f);
        (this.neck = new Cuboid(44, 0)).method_1817(-1.0f, -6.0f, -1.0f, 2, 6, 2);
        this.neck.setRotationPoint(0.0f, (float)(-2 + byte0), -4.0f);
        (this.feather1 = new Cuboid(30, 0)).method_1818(-1.0f, -5.0f, 5.0f, 2, 1, 5, -0.3f);
        this.feather1.setRotationPoint(0.0f, (float)(1 + byte0), 1.0f);
        (this.feather2 = new Cuboid(30, 0)).method_1818(-1.0f, -5.0f, 5.0f, 2, 1, 5, -0.3f);
        this.feather2.setRotationPoint(0.0f, (float)(1 + byte0), 1.0f);
        (this.feather3 = new Cuboid(30, 0)).method_1818(-1.0f, -5.0f, 5.0f, 2, 1, 5, -0.3f);
        this.feather3.setRotationPoint(0.0f, (float)(1 + byte0), 1.0f);
        final Cuboid feather1 = this.feather1;
        feather1.rotationPointY += 0.5f;
        final Cuboid feather2 = this.feather2;
        feather2.rotationPointY += 0.5f;
        final Cuboid feather3 = this.feather3;
        feather3.rotationPointY += 0.5f;
    }
    
    @Override
    public void render(final float f, final float f1, final float f2, final float f3, final float f4, final float f5) {
        this.setAngles(f, f1, f2, f3, f4, f5);
        this.head.method_1815(f5);
        this.jaw.method_1815(f5);
        this.body.method_1815(f5);
        this.legs.method_1815(f5);
        this.legs2.method_1815(f5);
        this.wings.method_1815(f5);
        this.wings2.method_1815(f5);
        this.neck.method_1815(f5);
        this.feather1.method_1815(f5);
        this.feather2.method_1815(f5);
        this.feather3.method_1815(f5);
    }
    
    @Override
    public void setAngles(final float f, final float f1, final float f2, final float f3, final float f4, final float f5) {
        final float f6 = 3.141593f;
        this.head.pitch = f4 / 57.29578f;
        this.head.yaw = f3 / 57.29578f;
        this.jaw.pitch = this.head.pitch;
        this.jaw.yaw = this.head.yaw;
        this.body.pitch = 1.570796f;
        this.legs.pitch = MathHelper.cos(f * 0.6662f) * 1.4f * f1;
        this.legs2.pitch = MathHelper.cos(f * 0.6662f + 3.141593f) * 1.4f * f1;
        if (f2 > 0.001f) {
            this.wings.rotationPointZ = -1.0f;
            this.wings2.rotationPointZ = -1.0f;
            this.wings.rotationPointY = 12.0f;
            this.wings2.rotationPointY = 12.0f;
            this.wings.pitch = 0.0f;
            this.wings2.pitch = 0.0f;
            this.wings.roll = f2;
            this.wings2.roll = -f2;
            this.legs.pitch = 0.6f;
            this.legs2.pitch = 0.6f;
        }
        else {
            this.wings.rotationPointZ = -3.0f;
            this.wings2.rotationPointZ = -3.0f;
            this.wings.rotationPointY = 14.0f;
            this.wings2.rotationPointY = 14.0f;
            this.wings.pitch = f6 / 2.0f;
            this.wings2.pitch = f6 / 2.0f;
            this.wings.roll = 0.0f;
            this.wings2.roll = 0.0f;
        }
        this.feather1.yaw = -0.375f;
        this.feather2.yaw = 0.0f;
        this.feather3.yaw = 0.375f;
        this.feather1.pitch = 0.25f;
        this.feather2.pitch = 0.25f;
        this.feather3.pitch = 0.25f;
        this.neck.pitch = 0.0f;
        this.neck.yaw = this.head.yaw;
        final Cuboid jaw = this.jaw;
        jaw.pitch += 0.35f;
    }
}
