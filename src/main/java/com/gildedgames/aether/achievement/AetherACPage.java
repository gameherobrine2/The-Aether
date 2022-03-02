package com.gildedgames.aether.achievement;
import net.minecraft.block.BlockBase;
import net.modificationstation.stationapi.api.client.gui.screen.menu.AchievementPage;
import net.modificationstation.stationapi.api.util.Null;

import java.util.Random;

import com.gildedgames.aether.Aether;
import com.gildedgames.aether.block.Holystone;
import com.gildedgames.aether.event.listener.TextureListener;
import com.gildedgames.aether.registry.AetherBlocks;

public class AetherACPage extends AchievementPage {
    public AetherACPage() {
        super(Aether.MODID, "Aether");
    }
    /*@Override
    public int getBackgroundTexture(final Random random, final int i, final int j, int randomizedRow, int currentTexture) {
        /*int k = BlockBase.SAND.texture;
        final int l = randomizedRow;
        if (l > 37 || j == 35) {
        	k = TextureListener.sprAercloud;
            //k = AetherBlocks.AERCLOUD.texture;
        }
        else if (l == 22) {
            k = ((random.nextInt(2) != 0) ? TextureListener.sprMossyHolystone : TextureListener.sprGravititeOre);
        }
        else if (l == 10) {
            k = TextureListener.sprZaniteOre;
        }
        else if (l == 8) {
            k = TextureListener.sprAmbrosiumOre;
        }
        else if (l > 4) {
            k = TextureListener.sprHolystone;
        }
        else if (l > 0) {
            k = TextureListener.sprDirt;
        }
        return k; TODO: texture
    	//currentTexture = BlockBase.WOOL.texture;
    	//return BlockBase.WOOL.getTextureForSide(0);
    }*/
}
