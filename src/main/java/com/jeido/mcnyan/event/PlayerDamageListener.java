package com.jeido.mcnyan.event;

import com.jeido.mcnyan.McNyan;
import com.jeido.mcnyan.http.HttpRequestHandler;
import com.jeido.mcnyan.message.VnyanMessage;
import com.jeido.mcnyan.message.payload.PlayerDamagePayload;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;

@EventBusSubscriber(modid = McNyan.MODID, bus = EventBusSubscriber.Bus.GAME)
public class PlayerDamageListener {


    @SubscribeEvent
    public static void onPlayerDamage(LivingDamageEvent.Pre event) {

        LocalPlayer lPlayer = Minecraft.getInstance().player;
        if (lPlayer == null) return;


        if (event.getEntity() instanceof Player && event.getEntity().getUUID().equals(lPlayer.getUUID())) {

            HttpRequestHandler.getInstance().sendMessage(new VnyanMessage("Damage",
                    new PlayerDamagePayload(
                            event.getEntity().getName().getString(),
                            event.getEntity().getUUID(),
                            event.getOriginalDamage()
                    )
            ));
        }
    }
}
