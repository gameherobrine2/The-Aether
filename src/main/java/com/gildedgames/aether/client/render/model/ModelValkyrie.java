package com.gildedgames.aether.client.render.model;

import net.minecraft.client.model.Cuboid;
import net.minecraft.client.render.entity.model.Biped;
import net.minecraft.util.maths.MathHelper;
import org.lwjgl.opengl.GL11;

public class ModelValkyrie extends Biped
{
    public Cuboid bipedBody2;
    public Cuboid bipedRightArm2;
    public Cuboid bipedLeftArm2;
    public Cuboid wingLeft;
    public Cuboid wingRight;
    public Cuboid[] skirt;
    public Cuboid[] sword;
    public Cuboid[] strand;
    public Cuboid[] halo;
    public static final int swordParts = 5;
    public static final int skirtParts = 6;
    public static final int strandParts = 22;
    public static final int haloParts = 4;
    public float sinage;
    public boolean gonRound;
    public boolean halow;

    public ModelValkyrie()
    {
        this(0.0f);
    }

    public ModelValkyrie(final float f)
    {
        this(f, 0.0f);
    }

    public ModelValkyrie(final float f, final float f1)
    {
        this.field_628 = false;
        this.field_629 = false;
        this.field_630 = false;
        (this.field_619 = new Cuboid(0, 0)).method_1818(-4.0f, -8.0f, -4.0f, 8, 8, 8, f);
        this.field_619.setRotationPoint(0.0f, 0.0f + f1, 0.0f);
        (this.field_621 = new Cuboid(12, 16)).method_1818(-3.0f, 0.0f, -1.5f, 6, 12, 3, f);
        this.field_621.setRotationPoint(0.0f, 0.0f + f1, 0.0f);
        (this.bipedBody2 = new Cuboid(12, 16)).method_1818(-3.0f, 0.5f, -1.25f, 6, 5, 3, f + 0.75f);
        this.bipedBody2.setRotationPoint(0.0f, 0.0f + f1, 0.0f);
        (this.field_622 = new Cuboid(30, 16)).method_1818(-3.0f, -1.5f, -1.5f, 3, 12, 3, f);
        this.field_622.setRotationPoint(-4.0f, 1.5f + f1, 0.0f);
        this.field_623 = new Cuboid(30, 16);
        this.field_623.mirror = true;
        this.field_623.method_1818(-1.0f, -1.5f, -1.5f, 3, 12, 3, f);
        this.field_623.setRotationPoint(5.0f, 1.5f + f1, 0.0f);
        (this.bipedRightArm2 = new Cuboid(30, 16)).method_1818(-3.0f, -1.5f, -1.5f, 3, 3, 3, f + 0.75f);
        this.bipedRightArm2.setRotationPoint(-4.0f, 1.5f + f1, 0.0f);
        this.bipedLeftArm2 = new Cuboid(30, 16);
        this.bipedLeftArm2.mirror = true;
        this.bipedLeftArm2.method_1818(-1.0f, -1.5f, -1.5f, 3, 3, 3, f + 0.75f);
        this.bipedLeftArm2.setRotationPoint(5.0f, 1.5f + f1, 0.0f);
        (this.field_624 = new Cuboid(0, 16)).method_1818(-2.0f, 0.0f, -1.5f, 3, 12, 3, f);
        this.field_624.setRotationPoint(-1.0f, 12.0f + f1, 0.0f);
        this.field_625 = new Cuboid(0, 16);
        this.field_625.mirror = true;
        this.field_625.method_1818(-2.0f, 0.0f, -1.5f, 3, 12, 3, f);
        this.field_625.setRotationPoint(2.0f, 12.0f + f1, 0.0f);
        this.sword = new Cuboid[5];
        (this.sword[0] = new Cuboid(9, 16)).method_1818(-2.5f, 8.0f, 1.5f, 2, 2, 1, f);
        this.sword[0].setRotationPoint(-4.0f, 1.5f + f1, 0.0f);
        (this.sword[1] = new Cuboid(32, 10)).method_1818(-3.0f, 6.5f, -2.75f, 3, 5, 1, f + 0.5f);
        this.sword[1].setRotationPoint(-4.0f, 1.5f + f1, 0.0f);
        (this.sword[2] = new Cuboid(42, 18)).method_1818(-2.0f, 7.5f, -12.5f, 1, 3, 10, f);
        this.sword[2].setRotationPoint(-4.0f, 1.5f + f1, 0.0f);
        (this.sword[3] = new Cuboid(42, 18)).method_1818(-2.0f, 7.5f, -22.5f, 1, 3, 10, f);
        this.sword[3].setRotationPoint(-4.0f, 1.5f + f1, 0.0f);
        (this.sword[4] = new Cuboid(28, 17)).method_1818(-2.0f, 8.5f, -23.5f, 1, 1, 1, f);
        this.sword[4].setRotationPoint(-4.0f, 1.5f + f1, 0.0f);
        (this.wingLeft = new Cuboid(24, 31)).method_1818(0.0f, -4.5f, 0.0f, 19, 8, 1, f);
        this.wingLeft.setRotationPoint(0.5f, 4.5f + f1, 2.625f);
        this.wingRight = new Cuboid(24, 31);
        this.wingRight.mirror = true;
        this.wingRight.method_1818(-19.0f, -4.5f, 0.0f, 19, 8, 1, f);
        this.wingRight.setRotationPoint(-0.5f, 4.5f + f1, 2.625f);
        this.skirt = new Cuboid[6];
        (this.skirt[0] = new Cuboid(0, 0)).method_1818(0.0f, 0.0f, -1.0f, 3, 6, 1, f);
        this.skirt[0].setRotationPoint(-3.0f, 9.0f + f1, -1.5f);
        (this.skirt[1] = new Cuboid(0, 0)).method_1818(0.0f, 0.0f, -1.0f, 3, 6, 1, f);
        this.skirt[1].setRotationPoint(0.0f, 9.0f + f1, -1.5f);
        (this.skirt[2] = new Cuboid(0, 0)).method_1818(0.0f, 0.0f, 0.0f, 3, 6, 1, f);
        this.skirt[2].setRotationPoint(-3.0f, 9.0f + f1, 1.5f);
        (this.skirt[3] = new Cuboid(0, 0)).method_1818(0.0f, 0.0f, 0.0f, 3, 6, 1, f);
        this.skirt[3].setRotationPoint(0.0f, 9.0f + f1, 1.5f);
        (this.skirt[4] = new Cuboid(55, 19)).method_1818(-1.0f, 0.0f, 0.0f, 1, 6, 3, f);
        this.skirt[4].setRotationPoint(-3.0f, 9.0f + f1, -1.5f);
        (this.skirt[5] = new Cuboid(55, 19)).method_1818(0.0f, 0.0f, 0.0f, 1, 6, 3, f);
        this.skirt[5].setRotationPoint(3.0f, 9.0f + f1, -1.5f);
        this.strand = new Cuboid[22];
        for (int i = 0; i < 22; ++i)
        {
            this.strand[i] = new Cuboid(42 + i % 7, 17);
        }
        this.strand[0].method_1818(-5.0f, -7.0f, -4.0f, 1, 3, 1, f);
        this.strand[0].setRotationPoint(0.0f, 0.0f + f1, 0.0f);
        this.strand[1].method_1818(4.0f, -7.0f, -4.0f, 1, 3, 1, f);
        this.strand[1].setRotationPoint(0.0f, 0.0f + f1, 0.0f);
        this.strand[2].method_1818(-5.0f, -7.0f, -3.0f, 1, 4, 1, f);
        this.strand[2].setRotationPoint(0.0f, 0.0f + f1, 0.0f);
        this.strand[3].method_1818(4.0f, -7.0f, -3.0f, 1, 4, 1, f);
        this.strand[3].setRotationPoint(0.0f, 0.0f + f1, 0.0f);
        this.strand[4].method_1818(-5.0f, -7.0f, -2.0f, 1, 4, 1, f);
        this.strand[4].setRotationPoint(0.0f, 0.0f + f1, 0.0f);
        this.strand[5].method_1818(4.0f, -7.0f, -2.0f, 1, 4, 1, f);
        this.strand[5].setRotationPoint(0.0f, 0.0f + f1, 0.0f);
        this.strand[6].method_1818(-5.0f, -7.0f, -1.0f, 1, 5, 1, f);
        this.strand[6].setRotationPoint(0.0f, 0.0f + f1, 0.0f);
        this.strand[7].method_1818(4.0f, -7.0f, -1.0f, 1, 5, 1, f);
        this.strand[7].setRotationPoint(0.0f, 0.0f + f1, 0.0f);
        this.strand[8].method_1818(-5.0f, -7.0f, 0.0f, 1, 5, 1, f);
        this.strand[8].setRotationPoint(0.0f, 0.0f + f1, 0.0f);
        this.strand[9].method_1818(4.0f, -7.0f, 0.0f, 1, 5, 1, f);
        this.strand[9].setRotationPoint(0.0f, 0.0f + f1, 0.0f);
        this.strand[10].method_1818(-5.0f, -7.0f, 1.0f, 1, 6, 1, f);
        this.strand[10].setRotationPoint(0.0f, 0.0f + f1, 0.0f);
        this.strand[11].method_1818(4.0f, -7.0f, 1.0f, 1, 6, 1, f);
        this.strand[11].setRotationPoint(0.0f, 0.0f + f1, 0.0f);
        this.strand[12].method_1818(-5.0f, -7.0f, 2.0f, 1, 7, 1, f);
        this.strand[12].setRotationPoint(0.0f, 0.0f + f1, 0.0f);
        this.strand[13].method_1818(4.0f, -7.0f, 2.0f, 1, 7, 1, f);
        this.strand[13].setRotationPoint(0.0f, 0.0f + f1, 0.0f);
        this.strand[14].method_1818(-5.0f, -7.0f, 3.0f, 1, 8, 1, f);
        this.strand[14].setRotationPoint(0.0f, 0.0f + f1, 0.0f);
        this.strand[15].method_1818(4.0f, -7.0f, 3.0f, 1, 8, 1, f);
        this.strand[15].setRotationPoint(0.0f, 0.0f + f1, 0.0f);
        this.strand[16].method_1818(-4.0f, -7.0f, 4.0f, 1, 9, 1, f);
        this.strand[16].setRotationPoint(0.0f, 0.0f + f1, 0.0f);
        this.strand[17].method_1818(3.0f, -7.0f, 4.0f, 1, 9, 1, f);
        this.strand[17].setRotationPoint(0.0f, 0.0f + f1, 0.0f);
        (this.strand[18] = new Cuboid(42, 17)).method_1818(-3.0f, -7.0f, 4.0f, 3, 10, 1, f);
        this.strand[18].setRotationPoint(0.0f, 0.0f + f1, 0.0f);
        (this.strand[19] = new Cuboid(43, 17)).method_1818(0.0f, -7.0f, 4.0f, 3, 10, 1, f);
        this.strand[19].setRotationPoint(0.0f, 0.0f + f1, 0.0f);
        this.strand[20].method_1818(-1.0f, -7.0f, -5.0f, 1, 2, 1, f);
        this.strand[20].setRotationPoint(0.0f, 0.0f + f1, 0.0f);
        this.strand[21].method_1818(0.0f, -7.0f, -5.0f, 1, 3, 1, f);
        this.strand[21].setRotationPoint(0.0f, 0.0f + f1, 0.0f);
        this.halo = new Cuboid[4];
        (this.halo[0] = new Cuboid(43, 9)).method_1818(-2.5f, -11.0f, -3.5f, 5, 1, 1, f);
        this.halo[0].setRotationPoint(0.0f, 0.0f + f1, 0.0f);
        (this.halo[1] = new Cuboid(43, 9)).method_1818(-2.5f, -11.0f, 2.5f, 5, 1, 1, f);
        this.halo[1].setRotationPoint(0.0f, 0.0f + f1, 0.0f);
        (this.halo[2] = new Cuboid(42, 11)).method_1818(-3.5f, -11.0f, -2.5f, 1, 1, 5, f);
        this.halo[2].setRotationPoint(0.0f, 0.0f + f1, 0.0f);
        (this.halo[3] = new Cuboid(42, 11)).method_1818(2.5f, -11.0f, -2.5f, 1, 1, 5, f);
        this.halo[3].setRotationPoint(0.0f, 0.0f + f1, 0.0f);
    }

    @Override
    public void render(final float f, final float f1, final float f2, final float f3, final float f4, final float f5)
    {
        this.setAngles(f, f1, f2, f3, f4, f5);
        this.field_619.method_1815(f5);
        this.field_621.method_1815(f5);
        this.field_622.method_1815(f5);
        this.field_623.method_1815(f5);
        this.field_624.method_1815(f5);
        this.field_625.method_1815(f5);
        this.bipedBody2.method_1815(f5);
        this.bipedRightArm2.method_1815(f5);
        this.bipedLeftArm2.method_1815(f5);
        this.wingLeft.method_1815(f5);
        this.wingRight.method_1815(f5);
        for (int i = 0; i < 5; ++i)
        {
            this.sword[i].method_1815(f5);
        }
        for (int i = 0; i < 6; ++i)
        {
            this.skirt[i].method_1815(f5);
        }
        for (int i = 0; i < 22; ++i)
        {
            this.strand[i].method_1815(f5);
        }
        if (this.halow)
        {
            GL11.glEnable(2977);
            GL11.glEnable(3042);
            GL11.glDisable(3008);
            GL11.glBlendFunc(770, 771);
            for (int i = 0; i < 4; ++i)
            {
                this.halo[i].method_1815(f5);
            }
            GL11.glEnable(3008);
        }
    }

    @Override
    public void setAngles(final float f, final float f1, final float f2, final float f3, final float f4, final float f5)
    {
        this.field_619.yaw = f3 / 57.29578f;
        this.field_619.pitch = f4 / 57.29578f;
        this.field_622.pitch = MathHelper.cos(f * 0.6662f + 3.141593f) * 2.0f * f1 * 0.5f;
        this.field_623.pitch = MathHelper.cos(f * 0.6662f) * 2.0f * f1 * 0.5f;
        this.field_622.roll = 0.05f;
        this.field_623.roll = -0.05f;
        this.field_624.pitch = MathHelper.cos(f * 0.6662f) * 1.4f * f1;
        this.field_625.pitch = MathHelper.cos(f * 0.6662f + 3.141593f) * 1.4f * f1;
        this.field_624.yaw = 0.0f;
        this.field_625.yaw = 0.0f;
        for (int i = 0; i < 22; ++i)
        {
            this.strand[i].yaw = this.field_619.yaw;
            this.strand[i].pitch = this.field_619.pitch;
        }
        for (int i = 0; i < 4; ++i)
        {
            this.halo[i].yaw = this.field_619.yaw;
            this.halo[i].pitch = this.field_619.pitch;
        }
        if (this.isRiding)
        {
            final Cuboid field_622 = this.field_622;
            field_622.pitch -= 0.6283185f;
            final Cuboid field_623 = this.field_623;
            field_623.pitch -= 0.6283185f;
            this.field_624.pitch = -1.256637f;
            this.field_625.pitch = -1.256637f;
            this.field_624.yaw = 0.3141593f;
            this.field_625.yaw = -0.3141593f;
        }
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
            final Cuboid bipedBody2 = this.bipedBody2;
            final Cuboid field_624 = this.field_621;
            final float n = MathHelper.sin(MathHelper.sqrt(f6) * 3.141593f * 2.0f) * 0.2f;
            field_624.yaw = n;
            bipedBody2.yaw = n;
            final Cuboid field_625 = this.field_622;
            field_625.yaw += this.field_621.yaw;
            final Cuboid field_626 = this.field_623;
            field_626.yaw += this.field_621.yaw;
            final Cuboid field_627 = this.field_623;
            field_627.pitch += this.field_621.yaw;
            f6 = 1.0f - this.handSwingProgress;
            f6 *= f6;
            f6 *= f6;
            f6 = 1.0f - f6;
            final float f7 = MathHelper.sin(f6 * 3.141593f);
            final float f8 = MathHelper.sin(this.handSwingProgress * 3.141593f) * -(this.field_619.pitch - 0.7f) * 0.75f;
            final Cuboid field_628 = this.field_622;
            field_628.pitch -= (float) (f7 * 1.2 + f8);
            final Cuboid field_629 = this.field_622;
            field_629.yaw += this.field_621.yaw * 2.0f;
            this.field_622.roll = MathHelper.sin(this.handSwingProgress * 3.141593f) * -0.4f;
        }
        final Cuboid field_630 = this.field_622;
        field_630.roll += MathHelper.cos(f2 * 0.09f) * 0.05f + 0.05f;
        final Cuboid field_631 = this.field_623;
        field_631.roll -= MathHelper.cos(f2 * 0.09f) * 0.05f + 0.05f;
        final Cuboid field_632 = this.field_622;
        field_632.pitch += MathHelper.sin(f2 * 0.067f) * 0.05f;
        final Cuboid field_633 = this.field_623;
        field_633.pitch -= MathHelper.sin(f2 * 0.067f) * 0.05f;
        for (int i = 0; i < 5; ++i)
        {
            this.sword[i].roll = this.field_622.roll;
            this.sword[i].yaw = this.field_622.yaw;
            this.sword[i].pitch = this.field_622.pitch;
        }
        this.bipedRightArm2.roll = this.field_622.roll;
        this.bipedRightArm2.yaw = this.field_622.yaw;
        this.bipedRightArm2.pitch = this.field_622.pitch;
        this.bipedLeftArm2.roll = this.field_623.roll;
        this.bipedLeftArm2.pitch = this.field_623.pitch;
        this.wingLeft.yaw = -0.2f;
        this.wingRight.yaw = 0.2f;
        this.wingLeft.roll = -0.125f;
        this.wingRight.roll = 0.125f;
        final Cuboid wingLeft = this.wingLeft;
        wingLeft.yaw += (float) (Math.sin((double) this.sinage) / 6.0);
        final Cuboid wingRight = this.wingRight;
        wingRight.yaw -= (float) (Math.sin((double) this.sinage) / 6.0);
        final Cuboid wingLeft2 = this.wingLeft;
        wingLeft2.roll += (float) (Math.cos((double) this.sinage) / (this.gonRound ? 8.0f : 3.0f));
        final Cuboid wingRight2 = this.wingRight;
        wingRight2.roll -= (float) (Math.cos((double) this.sinage) / (this.gonRound ? 8.0f : 3.0f));
        this.skirt[0].pitch = -0.2f;
        this.skirt[1].pitch = -0.2f;
        this.skirt[2].pitch = 0.2f;
        this.skirt[3].pitch = 0.2f;
        this.skirt[4].roll = 0.2f;
        this.skirt[5].roll = -0.2f;
        if (this.field_625.pitch < -0.3f)
        {
            final Cuboid cuboid = this.skirt[1];
            cuboid.pitch += this.field_625.pitch + 0.3f;
            final Cuboid cuboid2 = this.skirt[2];
            cuboid2.pitch -= this.field_625.pitch + 0.3f;
        }
        if (this.field_625.pitch > 0.3f)
        {
            final Cuboid cuboid3 = this.skirt[3];
            cuboid3.pitch += this.field_625.pitch - 0.3f;
            final Cuboid cuboid4 = this.skirt[0];
            cuboid4.pitch -= this.field_625.pitch - 0.3f;
        }
    }
}
