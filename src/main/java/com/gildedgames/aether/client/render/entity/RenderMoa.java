package com.gildedgames.aether.client.render.entity;
import net.minecraft.client.render.entity.ChickenRenderer;
import org.lwjgl.opengl.GL11;

import com.gildedgames.aether.entity.animal.EntityMoa;

import net.minecraft.entity.EntityBase;
import net.minecraft.util.maths.MathHelper;
import net.minecraft.entity.Living;
import net.minecraft.client.render.entity.model.EntityModelBase;
import net.minecraft.client.render.entity.LivingEntityRenderer;

public class RenderMoa extends LivingEntityRenderer {
    public RenderMoa(final EntityModelBase modelbase, final float f) {
        super(modelbase, f);
    }
    
    public void renderChicken(final EntityMoa entitymoa, final double d, final double d1, final double d2, final float f, final float f1) {
        super.method_822(entitymoa, d, d1, d2, f, f1);
    }
    
    protected float getWingRotation(final EntityMoa entitymoa, final float f) {
        final float f2 = entitymoa.field_756_e + (entitymoa.field_752_b - entitymoa.field_756_e) * f;
        final float f3 = entitymoa.field_757_d + (entitymoa.destPos - entitymoa.field_757_d) * f;
        return (MathHelper.sin(f2) + 1.0f) * f3;
    }
    
    @Override
    protected float method_828(final Living entityliving, final float f) {
        return this.getWingRotation((EntityMoa)entityliving, f);
    }
    
    @Override
    public void method_822(final Living entity, final double x, final double y, final double z, final float f, final float f1) {
        this.renderChicken((EntityMoa)entity, x, y, z, f, f1);
    }
    
    @Override
    public void render(final EntityBase entity, final double x, final double y, final double z, final float f, final float f1) {
        this.renderChicken((EntityMoa)entity, x, y, z, f, f1);
    }
    
    protected void scalemoa() {
        GL11.glScalef(1.8f, 1.8f, 1.8f);
    }
    
    @Override
    protected void method_823(final Living entityliving, final float f) {
        if (entityliving instanceof EntityMoa && ((EntityMoa)entityliving).baby) {
            return;
        }
        this.scalemoa();
    }
}
