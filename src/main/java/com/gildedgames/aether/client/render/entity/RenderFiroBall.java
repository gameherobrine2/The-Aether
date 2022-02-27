package com.gildedgames.aether.client.render.entity;
import net.minecraft.entity.Living;
import net.minecraft.client.render.entity.model.EntityModelBase;

import com.gildedgames.aether.client.render.model.ModelHomeShot;
import com.gildedgames.aether.entity.projectile.EntityFiroBall;

import net.minecraft.client.render.entity.LivingEntityRenderer;

public class RenderFiroBall extends LivingEntityRenderer {
    private ModelHomeShot shotty;
    
    public RenderFiroBall(final EntityModelBase ms, final float f) {
        super(ms, f);
        this.shotty = (ModelHomeShot)ms;
    }
    
    public void method_823(final Living el, final float f) {
        final EntityFiroBall hs = (EntityFiroBall)el;
        for (int i = 0; i < 3; ++i) {
            this.shotty.sinage[i] = hs.sinage[i];
        }
    }
}
