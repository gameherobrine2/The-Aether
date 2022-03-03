package com.gildedgames.aether.client.render.model;

import net.minecraft.util.maths.MathHelper;
import org.lwjgl.opengl.GL11;
import net.minecraft.client.model.Cuboid;
import net.minecraft.client.render.entity.model.EntityModelBase;

public class ModelAerbunny extends EntityModelBase {
    public Cuboid a;
    public Cuboid b;
    public Cuboid b2;
    public Cuboid b3;
    public Cuboid e1;
    public Cuboid e2;
    public Cuboid ff1;
    public Cuboid ff2;
    public Cuboid g;
    public Cuboid g2;
    public Cuboid h;
    public Cuboid h2;
    public float puffiness;
    
    public ModelAerbunny() {
        final byte byte0 = 16;
        (this.a = new Cuboid(0, 0)).method_1818(-2.0f, -1.0f, -4.0f, 4, 4, 6, 0.0f);
        this.a.setRotationPoint(0.0f, (float)(-1 + byte0), -4.0f);
        (this.g = new Cuboid(14, 0)).method_1818(-2.0f, -5.0f, -3.0f, 1, 4, 2, 0.0f);
        this.g.setRotationPoint(0.0f, (float)(-1 + byte0), -4.0f);
        (this.g2 = new Cuboid(14, 0)).method_1818(1.0f, -5.0f, -3.0f, 1, 4, 2, 0.0f);
        this.g2.setRotationPoint(0.0f, (float)(-1 + byte0), -4.0f);
        (this.h = new Cuboid(20, 0)).method_1818(-4.0f, 0.0f, -3.0f, 2, 3, 2, 0.0f);
        this.h.setRotationPoint(0.0f, (float)(-1 + byte0), -4.0f);
        (this.h2 = new Cuboid(20, 0)).method_1818(2.0f, 0.0f, -3.0f, 2, 3, 2, 0.0f);
        this.h2.setRotationPoint(0.0f, (float)(-1 + byte0), -4.0f);
        (this.b = new Cuboid(0, 10)).method_1818(-3.0f, -4.0f, -3.0f, 6, 8, 6, 0.0f);
        this.b.setRotationPoint(0.0f, (float)(0 + byte0), 0.0f);
        (this.b2 = new Cuboid(0, 24)).method_1818(-2.0f, 4.0f, -2.0f, 4, 3, 4, 0.0f);
        this.b2.setRotationPoint(0.0f, (float)(0 + byte0), 0.0f);
        (this.b3 = new Cuboid(29, 0)).method_1818(-3.5f, -3.5f, -3.5f, 7, 7, 7, 0.0f);
        this.b3.setRotationPoint(0.0f, 0.0f, 0.0f);
        (this.e1 = new Cuboid(24, 16)).method_1817(-2.0f, 0.0f, -1.0f, 2, 2, 2);
        this.e1.setRotationPoint(3.0f, (float)(3 + byte0), -3.0f);
        (this.e2 = new Cuboid(24, 16)).method_1817(0.0f, 0.0f, -1.0f, 2, 2, 2);
        this.e2.setRotationPoint(-3.0f, (float)(3 + byte0), -3.0f);
        (this.ff1 = new Cuboid(16, 24)).method_1817(-2.0f, 0.0f, -4.0f, 2, 2, 4);
        this.ff1.setRotationPoint(3.0f, (float)(3 + byte0), 4.0f);
        (this.ff2 = new Cuboid(16, 24)).method_1817(0.0f, 0.0f, -4.0f, 2, 2, 4);
        this.ff2.setRotationPoint(-3.0f, (float)(3 + byte0), 4.0f);
    }
    
    @Override
    public void render(final float f, final float f1, final float f2, final float f3, final float f4, final float f5) {
        this.setAngles(f, f1, f2, f3, f4, f5);
        this.a.method_1815(f5);
        this.g.method_1815(f5);
        this.g2.method_1815(f5);
        this.h.method_1815(f5);
        this.h2.method_1815(f5);
        this.b.method_1815(f5);
        this.b2.method_1815(f5);
        GL11.glPushMatrix();
        final float a = 1.0f + this.puffiness * 0.5f;
        GL11.glTranslatef(0.0f, 1.0f, 0.0f);
        GL11.glScalef(a, a, a);
        this.b3.method_1815(f5);
        GL11.glPopMatrix();
        this.e1.method_1815(f5);
        this.e2.method_1815(f5);
        this.ff1.method_1815(f5);
        this.ff2.method_1815(f5);
    }
    
    @Override
    public void setAngles(final float f, final float f1, final float f2, final float f3, final float f4, final float f5) {
        this.a.pitch = -(f4 / 57.29578f);
        this.a.yaw = f3 / 57.29578f;
        this.g.pitch = this.a.pitch;
        this.g.yaw = this.a.yaw;
        this.g2.pitch = this.a.pitch;
        this.g2.yaw = this.a.yaw;
        this.h.pitch = this.a.pitch;
        this.h.yaw = this.a.yaw;
        this.h2.pitch = this.a.pitch;
        this.h2.yaw = this.a.yaw;
        this.b.pitch = 1.570796f;
        this.b2.pitch = 1.570796f;
        this.b3.pitch = 1.570796f;
        this.e1.pitch = MathHelper.cos(f * 0.6662f) * 1.0f * f1;
        this.ff1.pitch = MathHelper.cos(f * 0.6662f + 3.141593f) * 1.2f * f1;
        this.e2.pitch = MathHelper.cos(f * 0.6662f) * 1.0f * f1;
        this.ff2.pitch = MathHelper.cos(f * 0.6662f + 3.141593f) * 1.2f * f1;
    }
}
