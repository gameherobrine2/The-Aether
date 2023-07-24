package com.gildedgames.aether.client.render.model;

import net.minecraft.client.model.Cuboid;
import net.minecraft.client.render.entity.model.Biped;
import net.minecraft.util.maths.MathHelper;
import org.lwjgl.opengl.GL11;

public class ModelFireMonster extends Biped
{
    public Cuboid bipedBody2;
    public Cuboid bipedBody3;
    public Cuboid bipedBody4;
    public Cuboid bipedRightArm2;
    public Cuboid bipedLeftArm2;
    public Cuboid bipedRightArm3;
    public Cuboid bipedLeftArm3;

    public ModelFireMonster()
    {
        this(0.0f);
    }

    public ModelFireMonster(final float f)
    {
        this(f, 0.0f);
    }

    public ModelFireMonster(final float f, final float f1)
    {
        this.field_628 = false;
        this.field_629 = false;
        this.field_630 = false;
        (this.field_619 = new Cuboid(0, 0)).method_1818(-4.0f, -8.0f, -3.0f, 8, 5, 7, f);
        this.field_619.setRotationPoint(0.0f, 0.0f + f1, 0.0f);
        (this.field_620 = new Cuboid(32, 0)).method_1818(-4.0f, -3.0f, -4.0f, 8, 3, 8, f);
        this.field_620.setRotationPoint(0.0f, 0.0f + f1, 0.0f);
        (this.field_621 = new Cuboid(0, 12)).method_1818(-5.0f, 0.0f, -2.5f, 10, 6, 5, f);
        this.field_621.setRotationPoint(0.0f, 0.0f + f1, 0.0f);
        (this.bipedBody2 = new Cuboid(0, 23)).method_1818(-4.5f, 6.0f, -2.0f, 9, 5, 4, f);
        this.bipedBody2.setRotationPoint(0.0f, 0.0f + f1, 0.0f);
        (this.bipedBody3 = new Cuboid(30, 27)).method_1818(-4.5f, 11.0f, -2.0f, 5, 1, 4, f + 0.5f);
        this.bipedBody3.setRotationPoint(0.0f, 0.0f + f1, 0.0f);
        (this.bipedBody4 = new Cuboid(30, 27)).method_1818(-0.5f, 11.0f, -2.0f, 5, 1, 4, f + 0.5f);
        this.bipedBody4.setRotationPoint(0.0f, 0.0f + f1, 0.0f);
        (this.field_622 = new Cuboid(30, 11)).method_1818(-2.5f, -2.5f, -2.5f, 5, 5, 5, f + 0.5f);
        this.field_622.setRotationPoint(-8.0f, 2.0f + f1, 0.0f);
        (this.bipedRightArm2 = new Cuboid(30, 11)).method_1818(-2.5f, 2.5f, -2.5f, 5, 10, 5, f);
        this.bipedRightArm2.setRotationPoint(-8.0f, 2.0f + f1, 0.0f);
        (this.bipedRightArm3 = new Cuboid(30, 26)).method_1818(-2.5f, 7.5f, -2.5f, 5, 1, 5, f + 0.25f);
        this.bipedRightArm3.setRotationPoint(-8.0f, 2.0f + f1, 0.0f);
        this.field_623 = new Cuboid(30, 11);
        this.field_623.mirror = true;
        this.field_623.method_1818(-2.5f, -2.5f, -2.5f, 5, 5, 5, f + 0.5f);
        this.field_623.setRotationPoint(8.0f, 2.0f + f1, 0.0f);
        this.bipedLeftArm2 = new Cuboid(30, 11);
        this.bipedLeftArm2.mirror = true;
        this.bipedLeftArm2.method_1818(-2.5f, 2.5f, -2.5f, 5, 10, 5, f);
        this.bipedLeftArm2.setRotationPoint(8.0f, 2.0f + f1, 0.0f);
        this.bipedLeftArm3 = new Cuboid(30, 26);
        this.bipedLeftArm3.mirror = true;
        this.bipedLeftArm3.method_1818(-2.5f, 7.5f, -2.5f, 5, 1, 5, f + 0.25f);
        this.bipedLeftArm3.setRotationPoint(8.0f, 2.0f + f1, 0.0f);
    }

    @Override
    public void render(final float f, final float f1, final float f2, final float f3, final float f4, final float f5)
    {
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        GL11.glScalef(2.25f, 2.25f, 2.25f);
        GL11.glTranslatef(0.0f, -0.25f, 0.0f);
        this.setAngles(f, f1, f2, f3, f4, f5);
        this.field_619.method_1815(f5);
        this.field_620.method_1815(f5);
        this.field_621.method_1815(f5);
        this.bipedBody2.method_1815(f5);
        this.bipedBody3.method_1815(f5);
        this.bipedBody4.method_1815(f5);
        this.field_622.method_1815(f5);
        this.bipedRightArm2.method_1815(f5);
        this.bipedRightArm3.method_1815(f5);
        this.field_623.method_1815(f5);
        this.bipedLeftArm2.method_1815(f5);
        this.bipedLeftArm3.method_1815(f5);
    }

    @Override
    public void setAngles(final float f, final float f1, final float f2, final float f3, final float f4, final float f5)
    {
        this.field_619.yaw = f3 / 57.29578f;
        this.field_619.pitch = f4 / 57.29578f;
        this.field_620.yaw = this.field_619.yaw;
        this.field_620.pitch = this.field_619.pitch;
        this.field_622.pitch = 0.0f;
        this.field_623.pitch = 0.0f;
        this.field_622.roll = 0.0f;
        this.field_623.roll = 0.0f;
        if (this.field_628)
        {
            this.field_623.pitch = this.field_623.pitch * 0.5f - 0.3141593f;
        }
        if (this.field_629)
        {
            this.field_622.pitch = this.field_622.pitch * 0.5f - 0.3141593f;
        }
        this.field_622.yaw = 0.0f;
        this.field_623.yaw = 0.0f;
        if (this.handSwingProgress > -9990.0f)
        {
            float f6 = this.handSwingProgress;
            this.field_621.yaw = MathHelper.sin(MathHelper.sqrt(f6) * 3.141593f * 2.0f) * 0.2f;
            final Cuboid field_622 = this.field_622;
            field_622.yaw += this.field_621.yaw;
            final Cuboid field_623 = this.field_623;
            field_623.yaw += this.field_621.yaw;
            final Cuboid field_624 = this.field_623;
            field_624.pitch += this.field_621.yaw;
            f6 = 1.0f - this.handSwingProgress;
            f6 *= f6;
            f6 *= f6;
            f6 = 1.0f - f6;
            final float f7 = MathHelper.sin(f6 * 3.141593f);
            final float f8 = MathHelper.sin(this.handSwingProgress * 3.141593f) * -(this.field_619.pitch - 0.7f) * 0.75f;
            final Cuboid field_625 = this.field_622;
            field_625.pitch -= (float) (f7 * 1.2 + f8);
            final Cuboid field_626 = this.field_622;
            field_626.yaw += this.field_621.yaw * 2.0f;
            this.field_622.roll = MathHelper.sin(this.handSwingProgress * 3.141593f) * -0.4f;
        }
        final Cuboid field_627 = this.field_622;
        field_627.roll += MathHelper.cos(f2 * 0.09f) * 0.05f + 0.05f;
        final Cuboid field_628 = this.field_623;
        field_628.roll -= MathHelper.cos(f2 * 0.09f) * 0.05f + 0.05f;
        final Cuboid field_629 = this.field_622;
        field_629.pitch += MathHelper.sin(f2 * 0.067f) * 0.05f;
        final Cuboid field_630 = this.field_623;
        field_630.pitch -= MathHelper.sin(f2 * 0.067f) * 0.05f;
        final Cuboid bipedBody4 = this.bipedBody4;
        final Cuboid bipedBody5 = this.bipedBody3;
        final Cuboid bipedBody6 = this.bipedBody2;
        final float pitch = this.field_621.pitch;
        bipedBody6.pitch = pitch;
        bipedBody5.pitch = pitch;
        bipedBody4.pitch = pitch;
        final Cuboid bipedBody7 = this.bipedBody4;
        final Cuboid bipedBody8 = this.bipedBody3;
        final Cuboid bipedBody9 = this.bipedBody2;
        final float yaw = this.field_621.yaw;
        bipedBody9.yaw = yaw;
        bipedBody8.yaw = yaw;
        bipedBody7.yaw = yaw;
        final Cuboid bipedLeftArm3 = this.bipedLeftArm3;
        final Cuboid bipedLeftArm4 = this.bipedLeftArm2;
        final float pitch2 = this.field_623.pitch;
        bipedLeftArm4.pitch = pitch2;
        bipedLeftArm3.pitch = pitch2;
        final Cuboid bipedLeftArm5 = this.bipedLeftArm3;
        final Cuboid bipedLeftArm6 = this.bipedLeftArm2;
        final float yaw2 = this.field_623.yaw;
        bipedLeftArm6.yaw = yaw2;
        bipedLeftArm5.yaw = yaw2;
        final Cuboid bipedLeftArm7 = this.bipedLeftArm3;
        final Cuboid bipedLeftArm8 = this.bipedLeftArm2;
        final float roll = this.field_623.roll;
        bipedLeftArm8.roll = roll;
        bipedLeftArm7.roll = roll;
        final Cuboid bipedRightArm3 = this.bipedRightArm3;
        final Cuboid bipedRightArm4 = this.bipedRightArm2;
        final float pitch3 = this.field_622.pitch;
        bipedRightArm4.pitch = pitch3;
        bipedRightArm3.pitch = pitch3;
        final Cuboid bipedRightArm5 = this.bipedRightArm3;
        final Cuboid bipedRightArm6 = this.bipedRightArm2;
        final float yaw3 = this.field_622.yaw;
        bipedRightArm6.yaw = yaw3;
        bipedRightArm5.yaw = yaw3;
        final Cuboid bipedRightArm7 = this.bipedRightArm3;
        final Cuboid bipedRightArm8 = this.bipedRightArm2;
        final float roll2 = this.field_622.roll;
        bipedRightArm8.roll = roll2;
        bipedRightArm7.roll = roll2;
    }
}
