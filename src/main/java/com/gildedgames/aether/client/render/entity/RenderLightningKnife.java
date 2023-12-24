package com.gildedgames.aether.client.render.entity;

import org.lwjgl.opengl.GL11;

import com.gildedgames.aether.entity.projectile.EntityLightningKnife;
import net.minecraft.client.render.Tessellator;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.entity.EntityBase;

public class RenderLightningKnife extends EntityRenderer{

	@Override
	public void render(EntityBase entity, double d, double d1, double d2, float f, float f1) {
		this.doRenderKnife((EntityLightningKnife)entity, d, d1, d2, f, f1);
	}

	public void doRenderKnife(EntityLightningKnife entity, double d, double d1, double d2, float f, float f1)
    {
        float f2 = 0;
        float f3 = 1;
        float f4 = 0;
        float f5 = 1;
        GL11.glPushMatrix();
        GL11.glTranslatef((float)d, (float)d1, (float)d2);
        GL11.glRotatef(f, 0.0F, 1.0F, 0.0F);
        GL11.glRotatef(-(entity.prevPitch + (entity.pitch - entity.prevPitch) * f1), 1.0F, 0.0F, 0.0F);
        GL11.glRotatef(45F, 0.0F, 1.0F, 0.0F);
        
        this.bindTexture("aether:stationapi/textures/items/LightningKnife.png");
        
        Tessellator tessellator = Tessellator.INSTANCE;
        float f6 = 1.0F;
        GL11.glEnable(32826 /*GL_RESCALE_NORMAL_EXT*/);
        float f7 = 0.0625F;
        GL11.glTranslatef(-0.5F, 0.0F, -0.5F);
        tessellator.start();
        tessellator.setNormal(0.0F, 0.0F, 1.0F);
        tessellator.vertex(0.0D, 0.0D, 0.0D, f3, f5);
        tessellator.vertex(f6, 0.0D, 0.0D, f2, f5);
        tessellator.vertex(f6, 0.0D, 1.0D, f2, f4);
        tessellator.vertex(0.0D, 0.0D, 1.0D, f3, f4);
        tessellator.draw();
        tessellator.start();
        tessellator.setNormal(0.0F, 0.0F, -1F);
        tessellator.vertex(0.0D, 0.0F - f7, 1.0D, f3, f4);
        tessellator.vertex(f6, 0.0F - f7, 1.0D, f2, f4);
        tessellator.vertex(f6, 0.0F - f7, 0.0D, f2, f5);
        tessellator.vertex(0.0D, 0.0F - f7, 0.0D, f3, f5);
        tessellator.draw();
        tessellator.start();
        tessellator.setNormal(-1F, 0.0F, 0.0F);
        for(int j = 0; j < 16; j++)
        {
            float f8 = (float)j / 16F;
            float f12 = (f3 + (f2 - f3) * f8) - 0.001953125F;
            float f16 = f6 * f8;
            tessellator.vertex(f16, 0.0F - f7, 0.0D, f12, f5);
            tessellator.vertex(f16, 0.0D, 0.0D, f12, f5);
            tessellator.vertex(f16, 0.0D, 1.0D, f12, f4);
            tessellator.vertex(f16, 0.0F - f7, 1.0D, f12, f4);
        }

        tessellator.draw();
        tessellator.start();
        tessellator.setNormal(1.0F, 0.0F, 0.0F);
        for(int k = 0; k < 16; k++)
        {
            float f9 = (float)k / 16F;
            float f13 = (f3 + (f2 - f3) * f9) - 0.001953125F;
            float f17 = f6 * f9 + 0.0625F;
            tessellator.vertex(f17, 0.0F - f7, 1.0D, f13, f4);
            tessellator.vertex(f17, 0.0D, 1.0D, f13, f4);
            tessellator.vertex(f17, 0.0D, 0.0D, f13, f5);
            tessellator.vertex(f17, 0.0F - f7, 0.0D, f13, f5);
        }

        tessellator.draw();
        tessellator.start();
        tessellator.setNormal(0.0F, 1.0F, 0.0F);
        for(int l = 0; l < 16; l++)
        {
            float f10 = (float)l / 16F;
            float f14 = (f5 + (f4 - f5) * f10) - 0.001953125F;
            float f18 = f6 * f10 + 0.0625F;
            tessellator.vertex(0.0D, 0.0D, f18, f3, f14);
            tessellator.vertex(f6, 0.0D, f18, f2, f14);
            tessellator.vertex(f6, 0.0F - f7, f18, f2, f14);
            tessellator.vertex(0.0D, 0.0F - f7, f18, f3, f14);
        }

        tessellator.draw();
        tessellator.start();
        tessellator.setNormal(0.0F, -1F, 0.0F);
        for(int i1 = 0; i1 < 16; i1++)
        {
            float f11 = (float)i1 / 16F;
            float f15 = (f5 + (f4 - f5) * f11) - 0.001953125F;
            float f19 = f6 * f11;
            tessellator.vertex(f6, 0.0D, f19, f2, f15);
            tessellator.vertex(0.0D, 0.0D, f19, f3, f15);
            tessellator.vertex(0.0D, 0.0F - f7, f19, f3, f15);
            tessellator.vertex(f6, 0.0F - f7, f19, f2, f15);
        }

        tessellator.draw();
        GL11.glDisable(32826 /*GL_RESCALE_NORMAL_EXT*/);
        GL11.glPopMatrix();
    }
}
