package com.gildedgames.aether.entity.base;

public interface IAetherBoss {
    int getBossHP();
    
    int getBossMaxHP();
    
    boolean isCurrentBoss();
    
    int getBossEntityID();
    
    String getBossTitle();
}

