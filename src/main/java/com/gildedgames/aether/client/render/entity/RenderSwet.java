package com.gildedgames.aether.client.render.entity;

import net.minecraft.entity.Living;
import org.lwjgl.opengl.GL11;

import com.gildedgames.aether.entity.animal.EntitySwet;

import net.minecraft.client.render.entity.model.EntityModelBase;
import net.minecraft.client.render.entity.LivingEntityRenderer;

public class RenderSwet extends LivingEntityRenderer {
    private EntityModelBase field_22001_a;
    
    public RenderSwet(final EntityModelBase modelbase, final EntityModelBase modelbase1, final float f) {
        super(modelbase, f);
        this.field_22001_a = modelbase1;
    }
    
    protected boolean a(final EntitySwet entityswets, final int i, final float f) {
        if (i == 0) {
            this.setModel(this.field_22001_a);
            GL11.glEnable(2977);
            GL11.glEnable(3042);
            GL11.glBlendFunc(770, 771);
            return true;
        }
        if (i == 1) {
            GL11.glDisable(3042);
            GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        }
        return false;
    }
    
    protected void a(final EntitySwet entityswets, final float f) {
        float f3;
        float f2 = f3 = 1.0f;
        float f4 = 1.5f;
        if (!entityswets.onGround) {
            if (entityswets.velocityY > 0.8500000238418579) {
                f3 = 1.425f;
                f2 = 0.575f;
            }
            else if (entityswets.velocityY < -0.8500000238418579) {
                f3 = 0.575f;
                f2 = 1.425f;
            }
            else {
                final float f5 = (float)entityswets.velocityY * 0.5f;
                f3 += f5;
                f2 -= f5;
            }
        }
        if (entityswets.passenger != null) {
            f4 = 1.5f + (entityswets.passenger.width + entityswets.passenger.height) * 0.75f;
        }
        GL11.glScalef(f2 * f4, f3 * f4, f2 * f4);
    }
    
    @Override
    protected void method_823(final Living entityliving, final float f) {
        this.a((EntitySwet)entityliving, f);
    }
    
    @Override
    protected boolean render(final Living entityliving, final int i, final float f) {
        return this.a((EntitySwet)entityliving, i, f);
    }
}
