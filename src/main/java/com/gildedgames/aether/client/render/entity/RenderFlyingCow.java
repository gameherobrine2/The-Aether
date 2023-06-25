package com.gildedgames.aether.client.render.entity;

import com.gildedgames.aether.client.render.model.ModelFlyingCow2;
import com.gildedgames.aether.entity.animal.EntityFlyingCow;

import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModelBase;
import net.minecraft.entity.Living;

public class RenderFlyingCow extends LivingEntityRenderer {
    private EntityModelBase wingmodel;
    
    public RenderFlyingCow(EntityModelBase entityModelBase2, EntityModelBase entityModelBase3, float float4) {
        super(entityModelBase2, float4);
        this.setModel(entityModelBase3);
        this.wingmodel = entityModelBase3;
    }
    
    protected boolean setWoolColorAndRender(EntityFlyingCow entityFlyingCow, int integer, float float4) {
        if (integer == 0) {
            this.bindTexture("aether:textures/entity/FlyingPigWings.png");
            ModelFlyingCow2.flyingcow = entityFlyingCow;
            return true;
        }
        return false;
    }
    
    @Override
    protected boolean render(Living living, int integer, float float4) {
        return this.setWoolColorAndRender((EntityFlyingCow)living, integer, float4);
    }
}
