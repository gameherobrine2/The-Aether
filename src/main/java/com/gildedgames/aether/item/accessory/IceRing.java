package com.gildedgames.aether.item.accessory;

import net.modificationstation.stationapi.api.registry.Identifier;

public class IceRing extends IcePendant {
    public IceRing(Identifier identifier, int colour) {
        super(identifier, "", colour);
    }

    @Override
    public Type getType() {
        return Type.ring;
    }
}
