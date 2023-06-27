package com.gildedgames.aether.client.render;

import com.gildedgames.aether.client.render.particle.AetherPortal;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityBase;
import net.minecraft.entity.player.PlayerBase;
import net.minecraft.level.Level;
import net.minecraft.sortme.LevelListener;
import net.minecraft.tileentity.TileEntityBase;

import static com.gildedgames.aether.Aether.of;

//@RequiredArgsConstructor
public class AetherWorldRenderer implements LevelListener {

    private final Minecraft client;
    public Level level;

    public AetherWorldRenderer(Minecraft gameInstance) {
		client = gameInstance;
	}

	public void updateLevel(Level level) {
        if (this.level != null)
            this.level.removeLevelListener(this);
        this.level = level;
        if (level != null)
            level.addLevelListener(this);
    }

    @Override
    public void method_1149(int i, int j, int k) {

    }

    @Override
    public void method_1150(int i, int j, int k, int i1, int j1, int k1) {

    }

    @Override
    public void method_1154(String string, double d, double d1, double d2, float f, float f1) {

    }

    @Override
    public void addParticle(String particleId, double x, double y, double z, double xTo, double yTo, double zTo) {
        if (client != null && client.viewEntity != null && client.particleManager != null) {
            double var14 = client.viewEntity.x - x;
            double var16 = client.viewEntity.y - y;
            double var18 = client.viewEntity.z - z;
            double var20 = 16;
            if (!(var14 * var14 + var16 * var16 + var18 * var18 > var20 * var20)) {
                if (particleId.equals(of("aether_portal").toString())) {
                    client.particleManager.addParticle(new AetherPortal(level, x, y, z, xTo, yTo, zTo));
                }
            }
        }
    }

    @Override
    public void drawCloak(EntityBase arg) {

    }

    @Override
    public void method_1157(EntityBase arg) {

    }

    @Override
    public void method_1148() {

    }

    @Override
    public void method_1155(String string, int i, int j, int k) {

    }

    @Override
    public void method_1151(int i, int j, int k, TileEntityBase arg) {

    }

    @Override
    public void playLevelEvent(PlayerBase arg, int i, int j, int k, int i1, int j1) {

    }
}
