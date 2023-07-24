package com.gildedgames.aether.client.render.entity;

import com.gildedgames.aether.entity.projectile.EntityFloatingBlock;
import net.minecraft.block.BlockBase;
import net.minecraft.client.render.Tessellator;
import net.minecraft.client.render.block.BlockRenderer;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.entity.EntityBase;
import net.minecraft.level.Level;
import net.minecraft.util.maths.MathHelper;
import org.lwjgl.opengl.GL11;

public class RenderFloatingBlock extends EntityRenderer
{
    private static BlockRenderer renderBlocks;

    public RenderFloatingBlock()
    {
        RenderFloatingBlock.renderBlocks = new BlockRenderer();
        this.field_2678 = 0.5f;
    }

    public void func_156_a(final EntityFloatingBlock entityFloatingBlock, final double d, final double d1, final double d2, final float f, final float f1)
    {
        GL11.glPushMatrix();
        GL11.glTranslatef((float) d, (float) d1, (float) d2);
        this.bindTexture("/terrain.png");
        final BlockBase block = BlockBase.BY_ID[entityFloatingBlock.blockID];
        final Level world = entityFloatingBlock.getWorld();
        GL11.glDisable(2896);
        renderBlockFallingSand(block, world, MathHelper.floor(entityFloatingBlock.x), MathHelper.floor(entityFloatingBlock.y), MathHelper.floor(entityFloatingBlock.z), entityFloatingBlock.metadata);
        GL11.glEnable(2896);
        GL11.glPopMatrix();
    }

    public static void renderBlockFallingSand(final BlockBase block, final Level world, final int i, final int j, final int k, final int meta)
    {
        final int l = block.getBaseColour(meta);
        final float red = (l >> 16 & 0xFF) / 255.0f;
        final float green = (l >> 8 & 0xFF) / 255.0f;
        final float blue = (l & 0xFF) / 255.0f;
        final float f = 0.5f;
        final float f2 = 1.0f;
        final float f3 = 0.8f;
        final float f4 = 0.6f;
        final Tessellator tessellator = Tessellator.INSTANCE;
        tessellator.start();
        final float f5 = block.getBrightness(world, i, j, k);
        float f6 = block.getBrightness(world, i, j - 1, k);
        if (f6 < f5)
        {
            f6 = f5;
        }
        tessellator.colour(f * f6 * red, f * f6 * green, f * f6 * blue);
        RenderFloatingBlock.renderBlocks.renderBottomFace(block, -0.5, -0.5, -0.5, block.getTextureForSide(0, meta));
        f6 = block.getBrightness(world, i, j + 1, k);
        if (f6 < f5)
        {
            f6 = f5;
        }
        tessellator.colour(f2 * f6 * red, f2 * f6 * green, f2 * f6 * blue);
        RenderFloatingBlock.renderBlocks.renderTopFace(block, -0.5, -0.5, -0.5, block.getTextureForSide(1, meta));
        f6 = block.getBrightness(world, i, j, k - 1);
        if (f6 < f5)
        {
            f6 = f5;
        }
        tessellator.colour(f3 * f6 * red, f3 * f6 * green, f3 * f6 * blue);
        RenderFloatingBlock.renderBlocks.renderEastFace(block, -0.5, -0.5, -0.5, block.getTextureForSide(2, meta));
        f6 = block.getBrightness(world, i, j, k + 1);
        if (f6 < f5)
        {
            f6 = f5;
        }
        tessellator.colour(f3 * f6 * red, f3 * f6 * green, f3 * f6 * blue);
        RenderFloatingBlock.renderBlocks.renderWestFace(block, -0.5, -0.5, -0.5, block.getTextureForSide(3, meta));
        f6 = block.getBrightness(world, i - 1, j, k);
        if (f6 < f5)
        {
            f6 = f5;
        }
        tessellator.colour(f4 * f6 * red, f4 * f6 * green, f4 * f6 * blue);
        RenderFloatingBlock.renderBlocks.renderNorthFace(block, -0.5, -0.5, -0.5, block.getTextureForSide(4, meta));
        f6 = block.getBrightness(world, i + 1, j, k);
        if (f6 < f5)
        {
            f6 = f5;
        }
        tessellator.colour(f4 * f6 * red, f4 * f6 * green, f4 * f6 * blue);
        RenderFloatingBlock.renderBlocks.renderSouthFace(block, -0.5, -0.5, -0.5, block.getTextureForSide(5, meta));
        tessellator.draw();
    }

    @Override
    public void render(final EntityBase entity, final double x, final double y, final double z, final float f, final float f1)
    {
        this.func_156_a((EntityFloatingBlock) entity, x, y, z, f, f1);
    }
}
