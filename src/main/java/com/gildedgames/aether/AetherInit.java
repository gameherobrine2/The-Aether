package com.gildedgames.aether;

import com.gildedgames.aether.entity.SPCAetherEntities;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;

public class AetherInit implements ModInitializer
{
    @Override
    public void onInitialize()
    {
        if (FabricLoader.getInstance().isModLoaded("spc"))
        {
            SPCAetherEntities.insert();
        }
    }
}
