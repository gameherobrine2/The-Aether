package com.gildedgames.aether.client.render.entity;

import com.gildedgames.aether.client.render.model.ModelValkyrie;
import com.gildedgames.aether.entity.boss.EntityValkyrie;
import net.minecraft.client.render.entity.BipedEntityRenderer;
import net.minecraft.client.render.entity.model.Biped;
import net.minecraft.entity.Living;

public class RenderValkyrie extends BipedEntityRenderer
{
    public ModelValkyrie mv1;

    public RenderValkyrie(final Biped model, final float f)
    {
        super(model, f);
        this.mv1 = (ModelValkyrie) model;
    }

    @Override
    protected void method_823(final Living entityliving, final float f)
    {
        final EntityValkyrie v1 = (EntityValkyrie) entityliving;
        this.mv1.sinage = v1.sinage;
        this.mv1.gonRound = v1.onGround;
        this.mv1.halow = !v1.otherDimension();
    }
}
