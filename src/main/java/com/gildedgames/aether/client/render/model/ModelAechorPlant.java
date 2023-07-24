package com.gildedgames.aether.client.render.model;

import net.minecraft.client.model.Cuboid;
import net.minecraft.client.render.entity.model.EntityModelBase;
import org.lwjgl.opengl.GL11;

public class ModelAechorPlant extends EntityModelBase
{
    private Cuboid[] petal;
    private Cuboid[] leaf;
    private Cuboid[] stamen;
    private Cuboid[] stamen2;
    private Cuboid[] thorn;
    private Cuboid stem;
    private Cuboid head;
    private static int petals;
    private static int thorns;
    private static int stamens;
    public float sinage;
    public float sinage2;
    public float size;
    private float pie;

    public ModelAechorPlant()
    {
        this(0.0f);
    }

    public ModelAechorPlant(final float f)
    {
        this(f, 0.0f);
    }

    public ModelAechorPlant(final float f, final float f1)
    {
        this.pie = 6.283186f;
        this.size = 1.0f;
        this.petal = new Cuboid[ModelAechorPlant.petals];
        this.leaf = new Cuboid[ModelAechorPlant.petals];
        for (int i = 0; i < ModelAechorPlant.petals; ++i)
        {
            this.petal[i] = new Cuboid(0, 0);
            if (i % 2 == 0)
            {
                (this.petal[i] = new Cuboid(29, 3)).method_1818(-4.0f, -1.0f, -12.0f, 8, 1, 9, f - 0.25f);
                this.petal[i].setRotationPoint(0.0f, 1.0f + f1, 0.0f);
            }
            else
            {
                this.petal[i].method_1818(-4.0f, -1.0f, -13.0f, 8, 1, 10, f - 0.125f);
                this.petal[i].setRotationPoint(0.0f, 1.0f + f1, 0.0f);
            }
            (this.leaf[i] = new Cuboid(38, 13)).method_1818(-2.0f, -1.0f, -9.5f, 4, 1, 8, f - 0.15f);
            this.leaf[i].setRotationPoint(0.0f, 1.0f + f1, 0.0f);
        }
        this.stamen = new Cuboid[ModelAechorPlant.stamens];
        this.stamen2 = new Cuboid[ModelAechorPlant.stamens];
        for (int i = 0; i < ModelAechorPlant.stamens; ++i)
        {
            (this.stamen[i] = new Cuboid(36, 13)).method_1818(0.0f, -9.0f, -1.5f, 1, 6, 1, f - 0.25f);
            this.stamen[i].setRotationPoint(0.0f, 1.0f + f1, 0.0f);
        }
        for (int i = 0; i < ModelAechorPlant.stamens; ++i)
        {
            (this.stamen2[i] = new Cuboid(32, 15)).method_1818(0.0f, -10.0f, -1.5f, 1, 1, 1, f + 0.125f);
            this.stamen2[i].setRotationPoint(0.0f, 1.0f + f1, 0.0f);
        }
        (this.head = new Cuboid(0, 12)).method_1818(-3.0f, -3.0f, -3.0f, 6, 2, 6, f + 0.75f);
        this.head.setRotationPoint(0.0f, 1.0f + f1, 0.0f);
        (this.stem = new Cuboid(24, 13)).method_1818(-1.0f, 0.0f, -1.0f, 2, 6, 2, f);
        this.stem.setRotationPoint(0.0f, 1.0f + f1, 0.0f);
        this.thorn = new Cuboid[ModelAechorPlant.thorns];
        for (int i = 0; i < ModelAechorPlant.thorns; ++i)
        {
            (this.thorn[i] = new Cuboid(32, 13)).setRotationPoint(0.0f, 1.0f + f1, 0.0f);
        }
        this.thorn[0].method_1818(-1.75f, 1.25f, -1.0f, 1, 1, 1, f - 0.25f);
        this.thorn[1].method_1818(-1.0f, 2.25f, 0.75f, 1, 1, 1, f - 0.25f);
        this.thorn[2].method_1818(0.75f, 1.25f, 0.0f, 1, 1, 1, f - 0.25f);
        this.thorn[3].method_1818(0.0f, 2.25f, -1.75f, 1, 1, 1, f - 0.25f);
    }

    @Override
    public void render(final float f, final float f1, final float f2, final float f3, final float f4, final float f5)
    {
        this.setAngles(f, f1, f2, f3, f4, f5);
        GL11.glPushMatrix();
        GL11.glTranslatef(0.0f, 1.2f, 0.0f);
        GL11.glScalef(this.size, this.size, this.size);
        for (int i = 0; i < ModelAechorPlant.petals; ++i)
        {
            this.petal[i].method_1815(f5);
            this.leaf[i].method_1815(f5);
        }
        for (int i = 0; i < ModelAechorPlant.stamens; ++i)
        {
            this.stamen[i].method_1815(f5);
            this.stamen2[i].method_1815(f5);
        }
        this.head.method_1815(f5);
        this.stem.method_1815(f5);
        for (int i = 0; i < ModelAechorPlant.thorns; ++i)
        {
            this.thorn[i].method_1815(f5);
        }
        GL11.glPopMatrix();
    }

    @Override
    public void setAngles(final float f, final float f1, final float f2, final float f3, final float f4, final float f5)
    {
        this.head.pitch = 0.0f;
        this.head.yaw = f4 / 57.29578f;
        final float boff = this.sinage2;
        this.stem.yaw = this.head.yaw;
        this.stem.rotationPointY = boff * 0.5f;
        for (int i = 0; i < ModelAechorPlant.thorns; ++i)
        {
            this.thorn[i].yaw = this.head.yaw;
            this.thorn[i].rotationPointY = boff * 0.5f;
        }
        for (int i = 0; i < ModelAechorPlant.petals; ++i)
        {
            this.petal[i].pitch = ((i % 2 == 0) ? -0.25f : -0.4125f);
            final Cuboid cuboid = this.petal[i];
            cuboid.pitch += this.sinage;
            this.petal[i].yaw = this.head.yaw;
            final Cuboid cuboid2 = this.petal[i];
            cuboid2.yaw += this.pie / ModelAechorPlant.petals * i;
            this.leaf[i].pitch = ((i % 2 == 0) ? 0.1f : 0.2f);
            final Cuboid cuboid3 = this.leaf[i];
            cuboid3.pitch += this.sinage * 0.75f;
            this.leaf[i].yaw = this.head.yaw + this.pie / ModelAechorPlant.petals / 2.0f;
            final Cuboid cuboid4 = this.leaf[i];
            cuboid4.yaw += this.pie / ModelAechorPlant.petals * i;
            this.petal[i].rotationPointY = boff;
            this.leaf[i].rotationPointY = boff;
        }
        for (int i = 0; i < ModelAechorPlant.stamens; ++i)
        {
            this.stamen[i].pitch = 0.2f + i / 15.0f;
            this.stamen[i].yaw = this.head.yaw + 0.1f;
            final Cuboid cuboid5 = this.stamen[i];
            cuboid5.yaw += this.pie / ModelAechorPlant.stamens * i;
            final Cuboid cuboid6 = this.stamen[i];
            cuboid6.pitch += this.sinage * 0.4f;
            this.stamen2[i].pitch = 0.2f + i / 15.0f;
            this.stamen2[i].yaw = this.head.yaw + 0.1f;
            final Cuboid cuboid7 = this.stamen2[i];
            cuboid7.yaw += this.pie / ModelAechorPlant.stamens * i;
            final Cuboid cuboid8 = this.stamen2[i];
            cuboid8.pitch += this.sinage * 0.4f;
            this.stamen[i].rotationPointY = boff + this.sinage * 2.0f;
            this.stamen2[i].rotationPointY = boff + this.sinage * 2.0f;
        }
        this.head.rotationPointY = boff + this.sinage * 2.0f;
    }

    static
    {
        ModelAechorPlant.petals = 10;
        ModelAechorPlant.thorns = 4;
        ModelAechorPlant.stamens = 3;
    }
}
