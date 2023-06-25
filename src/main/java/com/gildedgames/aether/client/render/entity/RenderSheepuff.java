package com.gildedgames.aether.client.render.entity;

import net.minecraft.entity.Living;
import org.lwjgl.opengl.GL11;

import com.gildedgames.aether.entity.animal.EntitySheepuff;

import net.minecraft.entity.animal.Sheep;
import net.minecraft.client.render.entity.model.EntityModelBase;
import net.minecraft.client.render.entity.LivingEntityRenderer;

public class RenderSheepuff extends LivingEntityRenderer {
    private EntityModelBase wool;
    private EntityModelBase puffed;
    
    public RenderSheepuff(final EntityModelBase modelbase, final EntityModelBase modelbase1, final EntityModelBase modelbase2, final float f) {
        super(modelbase1, f);
        this.setModel(modelbase);
        this.wool = modelbase;
        this.puffed = modelbase2;
    }
    
    protected boolean setWoolColorAndRender(final EntitySheepuff entitysheep, final int i, final float f) {
        if (i == 0 && !entitysheep.getSheared()) {
            if (entitysheep.getPuffed()) {
                this.setModel(this.puffed);
                this.bindTexture("aether:textures/entity/sheepuff_fur.png");
            }
            else {
                this.setModel(this.wool);
                this.bindTexture("aether:textures/entity/sheepuff_fur.png");
            }
            final float f2 = entitysheep.getBrightnessAtEyes(f);
            final int j = entitysheep.getFleeceColor();
            GL11.glColor3f(f2 * Sheep.field_2698[j][0], f2 * Sheep.field_2698[j][1], f2 * Sheep.field_2698[j][2]);
            return true;
        }
        return false;
    }
    
    @Override
    protected boolean render(final Living entityliving, final int i, final float f) {
        return this.setWoolColorAndRender((EntitySheepuff)entityliving, i, f);
    }
}
