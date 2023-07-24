package com.gildedgames.aether.item.tool;

import net.minecraft.entity.player.PlayerBase;
import net.minecraft.item.ItemInstance;
import net.minecraft.level.Level;
import net.modificationstation.stationapi.api.registry.Identifier;
import net.modificationstation.stationapi.api.template.item.TemplateItemBase;
import org.jetbrains.annotations.NotNull;

public class ItemLoreBook extends TemplateItemBase
{
    public ItemLoreBook(final @NotNull Identifier identifier)
    {
        super(identifier);
        this.maxStackSize = 1;
        this.setHasSubItems(true);
        this.setDurability(0);
    }

    @Override
    public int getColourMultiplier(final int i)
    {
        if (i == 0)
        {
            return 8388479;
        }
        if (i == 1)
        {
            return 16744319;
        }
        return 8355839;
    }

    @Override
    public ItemInstance use(final ItemInstance item, final Level level, final PlayerBase player)
    {
        /*
        if (FabricLoader.getInstance().getGameInstance() instanceof Minecraft mc)
        {
            mc.openScreen(new GuiLore(player.inventory, item.getDamage()));
        }*/
        // todo: open gui lore
        //TODO ModLoader.OpenGUI(player, (ScreenBase)new GuiLore(player.inventory, item.getDamage()));
        return item;
    }

    @Override
    public String getTranslationKey(final ItemInstance item)
    {
        int i = item.getDamage();
        if (i > 2)
        {
            i = 2;
        }
        return super.getTranslationKey() + "." + i;
    }
}
