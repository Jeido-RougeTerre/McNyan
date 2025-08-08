package com.jeido.mcnyan.event;

import com.jeido.mcnyan.McNyan;
import com.jeido.mcnyan.http.HttpRequestHandler;
import com.jeido.mcnyan.message.VnyanMessage;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;

@EventBusSubscriber(modid = McNyan.MODID, bus = EventBusSubscriber.Bus.GAME)
public class PlayerOnFireListener {

    public static boolean isOnFire = false;

    @SubscribeEvent
    public static void OnPlayerTickPost(PlayerTickEvent.Post event) {
        LocalPlayer lPlayer = Minecraft.getInstance().player;
        if (lPlayer == null) return;

        if (event.getEntity() instanceof Player player && event.getEntity().getUUID().equals(lPlayer.getUUID())) {
            if (player.isOnFire() && !isOnFire) {
                isOnFire = true;
                HttpRequestHandler.getInstance().sendMessage(new VnyanMessage("IsOnFire"));
            }
        }
    }

    @SubscribeEvent
    public static void OnPlayerTickPre(PlayerTickEvent.Pre event) {
        LocalPlayer lPlayer = Minecraft.getInstance().player;
        if (lPlayer == null) return;

        if (event.getEntity() instanceof Player player && event.getEntity().getUUID().equals(lPlayer.getUUID())) {
            if (isOnFire && !player.isOnFire()) {
                isOnFire = false;
                HttpRequestHandler.getInstance().sendMessage(new VnyanMessage("WasOnFire"));
            }
        }
    }
}
