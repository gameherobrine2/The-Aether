package com.gildedgames.aether.mixin;

import com.gildedgames.aether.Aether;
import com.gildedgames.aether.effect.AetherPoison;
import com.gildedgames.aether.registry.AetherAchievements;
import com.gildedgames.aether.registry.AetherItems;
import net.minecraft.client.Minecraft;
import net.minecraft.client.render.Tessellator;
import net.minecraft.entity.EntityBase;
import net.minecraft.entity.player.PlayerBase;
import net.minecraft.item.ItemInstance;
import net.minecraft.sortme.GameRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GameRenderer.class)
public class GameRendererMixin {
	@Shadow
	private Minecraft minecraft;
	@Inject(method = "method_1844", at = @At("TAIL"))
	private void onRenderTick(float delta, CallbackInfo ci) { //skidded from BetaLoader
		if(minecraft.level != null) {
			final PlayerBase player = minecraft.player;
			if (Aether.getCurrentDimension() == 2) {
                final boolean enteredAether = minecraft.statFileWriter.isAchievementUnlocked(AetherAchievements.enterAether);
                if (!enteredAether) {
                    Aether.giveAchievement(AetherAchievements.enterAether, player);
                    player.inventory.addStack(new ItemInstance(AetherItems.LoreBook, 1, 2));
                    player.inventory.addStack(new ItemInstance(AetherItems.CloudParachute, 1));
                    minecraft.level.playSound((EntityBase)player, "random.pop", 0.2f, 1.0f);
                }
            }
			AetherItems.tick(minecraft);
			AetherPoison.tickRender(minecraft);
		}
	}

	private float zLevel = -90;
	public void drawTexturedModalRect(final int i, final int j, final int k, final int l, final int i1, final int j1) {
        final float f = 0.00390625f;
        final float f2 = 0.00390625f;
        final Tessellator tessellator = Tessellator.INSTANCE;
        tessellator.start();
        tessellator.vertex(i + 0, j + j1, this.zLevel, (k + 0) * f, (l + j1) * f2);
        tessellator.vertex(i + i1, j + j1, this.zLevel, (k + i1) * f, (l + j1) * f2);
        tessellator.vertex(i + i1, j + 0, this.zLevel, (k + i1) * f, (l + 0) * f2);
        tessellator.vertex(i + 0, j + 0, this.zLevel, (k + 0) * f, (l + 0) * f2);
        tessellator.draw();
    }
    
}
