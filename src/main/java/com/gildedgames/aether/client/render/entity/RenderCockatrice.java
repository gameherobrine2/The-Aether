package com.gildedgames.aether.client.render.entity;

import org.lwjgl.opengl.GL11;

import com.gildedgames.aether.entity.mobs.EntityCockatrice;

import net.minecraft.entity.EntityBase;
import net.minecraft.util.maths.MathHelper;
import net.minecraft.entity.Living;
import net.minecraft.client.render.entity.model.EntityModelBase;
import net.minecraft.client.render.entity.LivingEntityRenderer;

public class RenderCockatrice extends LivingEntityRenderer {
    public RenderCockatrice(final EntityModelBase modelbase, final float f) {
        super(modelbase, f);
    }
    
    public void renderChicken(final EntityCockatrice entitybadmoa, final double d, final double d1, final double d2, final float f, final float f1) {
        super.render(entitybadmoa, d, d1, d2, f, f1);
    }
    
    protected float getWingRotation(final EntityCockatrice entitybadmoa, final float f) {
        final float f2 = entitybadmoa.field_756_e + (entitybadmoa.field_752_b - entitybadmoa.field_756_e) * f;
        final float f3 = entitybadmoa.field_757_d + (entitybadmoa.destPos - entitybadmoa.field_757_d) * f;
        return (MathHelper.sin(f2) + 1.0f) * f3;
    }
    
    @Override
    protected float method_828(final Living entityliving, final float f) {
        return this.getWingRotation((EntityCockatrice)entityliving, f);
    }
    
    @Override
    public void render(final Living entity, final double x, final double y, final double z, final float f, final float f1) {
        this.renderChicken((EntityCockatrice)entity, x, y, z, f, f1);
    }
    
    @Override
    public void render(final EntityBase entity, final double x, final double y, final double z, final float f, final float f1) {
        this.renderChicken((EntityCockatrice)entity, x, y, z, f, f1);
    }
    
    protected void scalemoa() {
        GL11.glScalef(1.8f, 1.8f, 1.8f);
    }
    
    @Override
    protected void method_823(final Living entityliving, final float f) {
        this.scalemoa();
    }
}
