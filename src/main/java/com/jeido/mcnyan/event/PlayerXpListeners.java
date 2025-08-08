package com.jeido.mcnyan.event;

import com.jeido.mcnyan.McNyan;
import com.jeido.mcnyan.http.HttpRequestHandler;
import com.jeido.mcnyan.message.VnyanMessage;
import com.jeido.mcnyan.message.payload.XpChangePayload;
import com.jeido.mcnyan.message.payload.XpPayload;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerXpEvent;

@EventBusSubscriber(modid = McNyan.MODID, bus = EventBusSubscriber.Bus.GAME)
public class PlayerXpListeners {

    @SubscribeEvent
    public static void OnXpChange(PlayerXpEvent.XpChange event) {
        LocalPlayer lPlayer = Minecraft.getInstance().player;
        if (lPlayer == null) return;

        if (event.getEntity() instanceof Player player && event.getEntity().getUUID().equals(lPlayer.getUUID())) {



            HttpRequestHandler.getInstance().sendMessage(new VnyanMessage("XpChange",
                    new XpChangePayload(
                            event.getAmount(),
                            new XpPayload(
                                    player.totalExperience,
                                    player.experienceLevel,
                                    player.getXpNeededForNextLevel(),
                                    player.experienceProgress,
                                    true
                            )
                    )
            ));
        }
    }
}
