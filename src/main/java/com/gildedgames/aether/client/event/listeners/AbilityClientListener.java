package com.gildedgames.aether.client.event.listeners;

import com.gildedgames.aether.common.item.accessories.abilities.IZaniteAccessory;
import com.gildedgames.aether.common.item.tools.abilities.IValkyrieToolItem;
import com.gildedgames.aether.common.registry.AetherItems;
import com.gildedgames.aether.core.network.AetherPacketHandler;
import com.gildedgames.aether.core.network.packet.server.ExtendedAttackPacket;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileHelper;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderHandEvent;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import top.theillusivec4.curios.api.CuriosApi;

@Mod.EventBusSubscriber(Dist.CLIENT)
public class AbilityClientListener
{
    @SubscribeEvent
    public static void onPlayerLeftClick(PlayerInteractEvent.LeftClickEmpty event) {
        PlayerEntity player = event.getPlayer();
        if (event.getItemStack().getItem() instanceof IValkyrieToolItem) {
            handleExtendedReach(player);
        }
    }

    @SubscribeEvent
    public static void onPlayerLeftClickBlock(PlayerInteractEvent.LeftClickBlock event) {
        PlayerEntity player = event.getPlayer();
        if (event.getItemStack().getItem() instanceof IValkyrieToolItem) {
            event.setCanceled(handleExtendedReach(player));
        }
    }

    //TODO: Verify this is up-to-date.
    private static boolean handleExtendedReach(PlayerEntity player) {
        double reach = player.getAttribute(ForgeMod.REACH_DISTANCE.get()).getValue();
        Vector3d eyePos = player.getEyePosition(1.0F);
        Vector3d lookVec = player.getLookAngle();
        Vector3d reachVec = eyePos.add(lookVec.x * reach, lookVec.y * reach, lookVec.z * reach);
        AxisAlignedBB playerBox = player.getBoundingBox().expandTowards(lookVec.scale(reach)).inflate(1.0D, 1.0D, 1.0D);
        EntityRayTraceResult traceResult = ProjectileHelper.getEntityHitResult(player, eyePos, reachVec, playerBox, (target) -> !target.isSpectator() && target.isPickable(), reach * reach);
        if (traceResult != null) {
            Entity target = traceResult.getEntity();
            Vector3d hitVec = traceResult.getLocation();
            double distance = eyePos.distanceToSqr(hitVec);
            if (distance < reach * reach) {
                AetherPacketHandler.sendToServer(new ExtendedAttackPacket(player.getUUID(), target.getId()));
                return true;
            }
        }
        return false;
    }

    @SubscribeEvent
    public static void onRenderPlayer(RenderPlayerEvent.Pre event) {
        CuriosApi.getCuriosHelper().findEquippedCurio(AetherItems.INVISIBILITY_CLOAK.get(), event.getPlayer()).ifPresent((triple) -> event.setCanceled(true));
    }

    @SubscribeEvent
    @SuppressWarnings("resource")
    public static void onRenderHand(RenderHandEvent event) {
        if (Minecraft.getInstance().player != null) {
            CuriosApi.getCuriosHelper().findEquippedCurio(AetherItems.INVISIBILITY_CLOAK.get(), Minecraft.getInstance().player).ifPresent((triple) -> event.setCanceled(true));
        }
    }
}
