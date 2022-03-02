package com.gildedgames.aether.block;

import com.gildedgames.aether.event.listener.TextureListener;
import com.gildedgames.aether.level.dimension.AetherTravelAgent;
import com.gildedgames.aether.registry.AetherDimensions;
import net.minecraft.block.BlockBase;
import net.minecraft.class_467;
import net.minecraft.entity.player.PlayerBase;
import net.minecraft.level.Level;
import net.modificationstation.stationapi.api.block.CustomPortal;
import net.modificationstation.stationapi.api.registry.Identifier;
import net.modificationstation.stationapi.api.template.block.TemplatePortal;

import java.util.*;

import static com.gildedgames.aether.Aether.of;

public class AetherPortal extends TemplatePortal implements CustomPortal {

    public AetherPortal(Identifier identifier, int j) {
        super(identifier, j);
    }
    public int getTextureForSide(int side, int meta) {
    	return TextureListener.sprPortal;
    }
    @Override
    public boolean method_736(Level arg, int x, int y, int z) {
        byte var5 = 0;
        byte var6 = 0;
        if (arg.getTileId(x - 1, y, z) == BlockBase.GLOWSTONE.id || arg.getTileId(x + 1, y, z) == BlockBase.GLOWSTONE.id)
            var5 = 1;

        if (arg.getTileId(x, y, z - 1) == BlockBase.GLOWSTONE.id || arg.getTileId(x, y, z + 1) == BlockBase.GLOWSTONE.id)
            var6 = 1;

        if (var5 == var6)
            return false;
        else {
            if (arg.getTileId(x - var5, y, z - var6) == 0) {
                x -= var5;
                z -= var6;
            }

            for(int var7 = -1; var7 <= 2; ++var7)
                for (int var8 = -1; var8 <= 3; ++var8) {
                    boolean var9 = var7 == -1 || var7 == 2 || var8 == -1 || var8 == 3;
                    if (var7 != -1 && var7 != 2 || var8 != -1 && var8 != 3) {
                        int var10 = arg.getTileId(x + var5 * var7, y + var8, z + var6 * var7);
                        if (var9) {
                            if (var10 != BlockBase.GLOWSTONE.id)
                                return false;
                        } else if (var10 != 0 && var10 != BlockBase.STILL_WATER.id && var10 != BlockBase.FLOWING_WATER.id)
                            return false;
                    }
                }

            arg.stopPhysics = true;

            for(int var11 = 0; var11 < 2; ++var11)
                for (int var12 = 0; var12 < 3; ++var12) {
                    arg.setTileWithMetadata(x + var5 * var11, y + var12, z + var6 * var11, id, (var5 + var6 * 2) - 1);
                    arg.method_243(x + var5 * var11, y + var12, z + var6 * var11);
                }

            arg.stopPhysics = false;
            return true;
        }
    }

    @Override
    public void onAdjacentBlockUpdate(Level level, int x, int y, int z, int id) {
        byte var6 = 0;
        byte var7 = 1;
        if (level.getTileId(x - 1, y, z) == this.id || level.getTileId(x + 1, y, z) == this.id) {
            var6 = 1;
            var7 = 0;
        }

        int var8;
        var8 = y;
        while (level.getTileId(x, var8 - 1, z) == this.id)
            --var8;

        if (level.getTileId(x, var8 - 1, z) != BlockBase.GLOWSTONE.id)
            level.setTile(x, y, z, 0);
        else {
            int var9;
            var9 = 1;
            while (var9 < 4 && level.getTileId(x, var8 + var9, z) == this.id)
                ++var9;

            if (var9 == 3 && level.getTileId(x, var8 + var9, z) == BlockBase.GLOWSTONE.id) {
                boolean var10 = level.getTileId(x - 1, y, z) == this.id || level.getTileId(x + 1, y, z) == this.id;
                boolean var11 = level.getTileId(x, y, z - 1) == this.id || level.getTileId(x, y, z + 1) == this.id;
                if (var10 && var11)
                    level.setTile(x, y, z, 0);
                else if ((level.getTileId(x + var6, y, z + var7) != BlockBase.GLOWSTONE.id || level.getTileId(x - var6, y, z - var7) != this.id) && (level.getTileId(x - var6, y, z - var7) != BlockBase.GLOWSTONE.id || level.getTileId(x + var6, y, z + var7) != this.id))
                    level.setTile(x, y, z, 0);
            } else
                level.setTile(x, y, z, 0);
        }
    }

    @Override
    public void randomDisplayTick(Level level, int x, int y, int z, Random rand) {
        if (rand.nextInt(100) == 0)
            level.playSound((double) x + 0.5D, (double) y + 0.5D, (double) z + 0.5D, "portal.portal", 1.0F, rand.nextFloat() * 0.4F + 0.8F);

        for(int var6 = 0; var6 < 4; ++var6) {
            double var7 = (float)x + rand.nextFloat();
            double var9 = (float)y + rand.nextFloat();
            double var11 = (float)z + rand.nextFloat();
            double var13;
            double var15;
            double var17;
            int var19 = rand.nextInt(2) * 2 - 1;
            var13 = ((double)rand.nextFloat() - 0.5D) * 0.5D;
            var15 = ((double)rand.nextFloat() - 0.5D) * 0.5D;
            var17 = ((double)rand.nextFloat() - 0.5D) * 0.5D;
            if (level.getTileId(x - 1, y, z) != this.id && level.getTileId(x + 1, y, z) != this.id) {
                var7 = (double)x + 0.5D + 0.25D * (double)var19;
                var13 = rand.nextFloat() * 2.0F * (float)var19;
            } else {
                var11 = (double)z + 0.5D + 0.25D * (double)var19;
                var17 = rand.nextFloat() * 2.0F * (float)var19;
            }

            level.addParticle(of("aether_portal").toString(), var7, var9, var11, var13, var15, var17);
        }
    }

    @Override
    public Identifier getDimension(PlayerBase player) {
        return AetherDimensions.THE_AETHER;
    }

    @Override
    public class_467 getTravelAgent(PlayerBase player) {
        return new AetherTravelAgent();
    }
}
