package com.jeido.mcnyan.event;

import com.jeido.mcnyan.McNyan;
import com.jeido.mcnyan.http.HttpRequestHandler;
import com.jeido.mcnyan.message.VnyanMessage;
import com.jeido.mcnyan.message.payload.EquipPayload;
import com.jeido.mcnyan.message.payload.ItemStackPayload;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingEquipmentChangeEvent;

@EventBusSubscriber(modid = McNyan.MODID, bus = EventBusSubscriber.Bus.GAME)
public class PlayerEquipListener {

    @SubscribeEvent
    public static void OnEquip(LivingEquipmentChangeEvent event) {
        LocalPlayer lPlayer = Minecraft.getInstance().player;
        if (lPlayer == null) return;

        if (event.getEntity() instanceof Player && event.getEntity().getUUID().equals(lPlayer.getUUID())) {

            HttpRequestHandler.getInstance().sendMessage(new VnyanMessage("EquipmentChange", new EquipPayload(
                    event.getSlot().getName(),
                    new ItemStackPayload(event.getTo())
            )));
        }
    }
}
