package com.gildedgames.aether.common.item.accessories.gloves;

import com.gildedgames.aether.Aether;
import com.gildedgames.aether.client.renderer.accessory.model.GlovesModel;
import com.gildedgames.aether.common.item.accessories.AccessoryItem;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.entity.model.PlayerModel;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurio;

import java.util.UUID;

public class GlovesItem extends AccessoryItem
{
    protected ResourceLocation GLOVES_TEXTURE;
    protected ResourceLocation GLOVES_SLIM_TEXTURE;
    protected double damage;

    public GlovesItem(double punchDamage, String glovesName, Properties properties) {
        super(properties);
        this.damage = punchDamage;
        this.setRenderTexture(Aether.MODID, glovesName);
    }

    public void setRenderTexture(String modId, String registryName) {
        this.GLOVES_TEXTURE = new ResourceLocation(modId, "textures/models/accessory/gloves/" + registryName + "_accessory.png");
        this.GLOVES_SLIM_TEXTURE = new ResourceLocation(modId, "textures/models/accessory/gloves/" + registryName + "_slim_accessory.png");
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(SlotContext slotContext, UUID uuid, ItemStack stack) {
        Multimap<Attribute, AttributeModifier> attributes = HashMultimap.create();
        attributes.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(uuid, "Gloves Damage Bonus", this.damage, AttributeModifier.Operation.ADDITION));
        return attributes;
    }

    @Override
    public boolean canRender(String identifier, int index, LivingEntity livingEntity, ItemStack stack) {
        return true;
    }

    @Override
    public void renderModel(BipedModel<?> model, String identifier, int index, MatrixStack matrixStack, IRenderTypeBuffer renderTypeBuffer, int light, LivingEntity livingEntity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, ItemStack stack) {
        GlovesModel gloves;
        IVertexBuilder vertexBuilder;

        if (!(model instanceof PlayerModel<?>)) {
            gloves = new GlovesModel(false);
            vertexBuilder = ItemRenderer.getArmorFoilBuffer(renderTypeBuffer, RenderType.armorCutoutNoCull(this.GLOVES_TEXTURE), false, stack.isEnchanted());
        }
        else {
            gloves = new GlovesModel(true);
            vertexBuilder = ItemRenderer.getArmorFoilBuffer(renderTypeBuffer, RenderType.armorCutoutNoCull(this.GLOVES_SLIM_TEXTURE), false, stack.isEnchanted());
        }

        gloves.prepareMobModel(livingEntity, limbSwing, limbSwingAmount, partialTicks);
        gloves.setupAnim(livingEntity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
        ICurio.RenderHelper.followBodyRotations(livingEntity, gloves);
        gloves.renderToBuffer(matrixStack, vertexBuilder, light, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
    }
}