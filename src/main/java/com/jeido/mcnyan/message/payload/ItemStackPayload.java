package com.jeido.mcnyan.message.payload;

import com.jeido.mcnyan.utils.ColorUtility;
import net.minecraft.world.item.ItemStack;

public class ItemStackPayload implements VnyanPayload {

    private final ItemStack itemStack;

    public ItemStackPayload(ItemStack itemStack) {
        this.itemStack = itemStack;
    }

    public ItemStackPayload() {
        this.itemStack = ItemStack.EMPTY;
    }

    @Override
    public String serialize() {
        String s = "\"item\": \"" + itemStack.getItem() + "\"";

        s += ", \"itemCount\": " + itemStack.getCount();

        s += ", \"itemRarity\": \"" + itemStack.getRarity() + "\"";

        s += ", \"itemHasFoil\": " + itemStack.hasFoil();

        if (itemStack.isDamageableItem()) {
            s += ", \"itemMaxDamage\": " + itemStack.getMaxDamage();
            if (itemStack.isDamaged()) {
                s += ", \"itemDamage\": " + itemStack.getDamageValue();
            }
        }

        if (itemStack.isBarVisible()) {
            s += ", \"itemBarSize\": " + itemStack.getBarWidth();


            s += ", \"itemBarColor\": " + ColorUtility.intToHexColor(itemStack.getBarColor());
        }

        s += ", \"itemIsEnchanted\": " + itemStack.isEnchanted();

        return s;
    }
}
