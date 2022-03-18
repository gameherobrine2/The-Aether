package com.gildedgames.aether.item;
import net.minecraft.item.ItemInstance;
import net.modificationstation.stationapi.api.client.item.ArmorTextureProvider;
import net.modificationstation.stationapi.api.registry.Identifier;
import net.modificationstation.stationapi.api.template.item.armour.TemplateArmour;

public class ItemColouredArmor extends TemplateArmour implements ArmorTextureProvider{
    private int colour;
    private String armorTexture;
    public ItemColouredArmor(final Identifier i, final int j, final String s, final int l, final int col) {
        super(i, j, 0, l);
        this.armorTexture = "/assets/aether/stationapi/textures/armor/"+s+".png";
        this.colour = col;
    }
    
    @Override
    public int getColourMultiplier(final int i) {
        return this.colour;
    }

	@Override
	public String getChestplateModelTexture(ItemInstance itemInstance) {
		System.out.println(armorTexture);
		return armorTexture;
	}

	@Override
	public String getOtherModelTexture(ItemInstance itemInstance) {
		System.out.println(armorTexture);
		return armorTexture;
	}
}
