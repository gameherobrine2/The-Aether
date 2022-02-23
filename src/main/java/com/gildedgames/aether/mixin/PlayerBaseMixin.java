package com.gildedgames.aether.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.At;

import com.gildedgames.aether.gui.container.ContainerAether;
import com.gildedgames.aether.inventory.InventoryAether;
import com.gildedgames.aether.registry.AetherItems;

import net.minecraft.entity.player.AbstractClientPlayer;//.PlayerBase;

@Mixin(AbstractClientPlayer.class)
public class PlayerBaseMixin {
    private InventoryAether inv;
    private int maxHealth = 20;
    
    //updateDespawnCounter
    @Inject(method = "updateDespawnCounter", at = @At(value = "HEAD"))
	public void onLivingUpdate(CallbackInfo ci) {
		AbstractClientPlayer player = AbstractClientPlayer.class.cast(this);
    	if(inv == null) {
    		this.inv = new InventoryAether(player);
    	}
    	if(!(player.playerContainer instanceof ContainerAether)) {
        	player.playerContainer = new ContainerAether(player.inventory, this.inv, true);
        	player.container = player.playerContainer;
    	}
        //if (MainMenu.mmactive) {
        //    player.setPosition(player.prevRenderX, player.prevRenderY, player.prevRenderZ);
        //}
        if (player.field_1645 % 400 == 0) {
            if (this.inv.slots[0] != null && this.inv.slots[0].itemId == AetherItems.ZanitePendant.id) {
                this.inv.slots[0].applyDamage(1, player);
                if (this.inv.slots[0].count < 1) {
                    this.inv.slots[0] = null;
                }
            }
            if (this.inv.slots[4] != null && this.inv.slots[4].itemId == AetherItems.ZaniteRing.id) {
                this.inv.slots[4].applyDamage(1, player);
                if (this.inv.slots[4].count < 1) {
                    this.inv.slots[4] = null;
                }
            }
            if (this.inv.slots[5] != null && this.inv.slots[5].itemId == AetherItems.ZaniteRing.id) {
                this.inv.slots[5].applyDamage(1, player);
                if (this.inv.slots[5].count < 1) {
                    this.inv.slots[5] = null;
                }
            }
            if (this.inv.slots[0] != null && this.inv.slots[0].itemId == AetherItems.IcePendant.id) {
                this.inv.slots[0].applyDamage(1, player);
                if (this.inv.slots[0].count < 1) {
                    this.inv.slots[0] = null;
                }
            }
            if (this.inv.slots[4] != null && this.inv.slots[4].itemId == AetherItems.IceRing.id) {
                this.inv.slots[4].applyDamage(1, player);
                if (this.inv.slots[4].count < 1) {
                    this.inv.slots[4] = null;
                }
            }
            if (this.inv.slots[5] != null && this.inv.slots[5].itemId == AetherItems.IceRing.id) {
                this.inv.slots[5].applyDamage(1, player);
                if (this.inv.slots[5].count < 1) {
                    this.inv.slots[5] = null;
                }
            }
        }
        if (player.level.difficulty == 0 && player.health >= 20 && player.health < this.maxHealth && player.field_1645 % 20 == 0) {
            player.addHealth(1);
        }
    }
	
}
