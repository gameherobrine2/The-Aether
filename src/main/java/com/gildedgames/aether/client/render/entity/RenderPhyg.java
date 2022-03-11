package com.gildedgames.aether.client.render.entity;

import net.minecraft.entity.Living;
import net.minecraft.client.render.entity.model.EntityModelBase;

import com.gildedgames.aether.client.render.model.ModelFlyingPig2;
import com.gildedgames.aether.entity.animal.EntityPhyg;

import net.minecraft.client.render.entity.LivingEntityRenderer;

public class RenderPhyg extends LivingEntityRenderer {
    private EntityModelBase wingmodel;
    
    public RenderPhyg(final EntityModelBase modelbase, final EntityModelBase modelbase1, final float f) {
        super(modelbase, f);
        this.setModel(modelbase1);
        this.wingmodel = modelbase1;
    }
    
    protected boolean setWoolColorAndRender(final EntityPhyg pig, final int i, final float f) {
        if (i == 0) {
            this.bindTexture("/assets/aether/stationapi/textures/entity/Mob_FlyingPigWings.png");
            ModelFlyingPig2.pig = pig;
            return true;
        }
        return false;
    }
    
    @Override
    protected boolean render(final Living entityliving, final int i, final float f) {
        return this.setWoolColorAndRender((EntityPhyg)entityliving, i, f);
    }
}
