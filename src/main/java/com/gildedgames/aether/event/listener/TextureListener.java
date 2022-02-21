package com.gildedgames.aether.event.listener;

import com.gildedgames.aether.block.BlockAetherLeaves;
import com.gildedgames.aether.block.BlockAetherLog;

import net.mine_diver.unsafeevents.listener.EventListener;
import net.modificationstation.stationapi.api.client.event.texture.TextureRegisterEvent;
import net.modificationstation.stationapi.api.client.texture.atlas.Atlases;
import net.modificationstation.stationapi.api.mod.entrypoint.Entrypoint;
import net.modificationstation.stationapi.api.registry.Identifier;
import net.modificationstation.stationapi.api.registry.ModID;
import net.modificationstation.stationapi.api.util.Null;

public class TextureListener {
	@EventListener
    public void registerTextures(TextureRegisterEvent event) {
		sprTop = Atlases.getTerrain().addTexture(Identifier.of(MOD_ID, "block/SkyrootLogTop")).index;
		sprSide = Atlases.getTerrain().addTexture(Identifier.of(MOD_ID, "block/SkyrootLogSide")).index;
		sprGoldenSide = Atlases.getTerrain().addTexture(Identifier.of(MOD_ID, "block/GoldenOak")).index;
		sprSkyroot = Atlases.getTerrain().addTexture(Identifier.of(MOD_ID, "block/SkyrootLeaves")).index;
        sprGoldenOak = Atlases.getTerrain().addTexture(Identifier.of(MOD_ID, "block/GoldenOakLeaves")).index;
        sprAercloud = Atlases.getTerrain().addTexture(Identifier.of(MOD_ID, "block/Aercloud")).index;
	}
	
	
	public static int sprAercloud;
    public static int sprSkyroot;
    public static int sprGoldenOak;
    public static int sprTop;
    public static int sprSide;
    public static int sprGoldenSide;
    @Entrypoint.ModID
    public static final ModID MOD_ID = Null.get();
}
