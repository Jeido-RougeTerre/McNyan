package com.jeido.mcnyan.event;

import com.jeido.mcnyan.McNyan;
import com.jeido.mcnyan.http.HttpRequestHandler;
import com.jeido.mcnyan.message.VnyanMessage;
import com.jeido.mcnyan.message.payload.ProjectilePayload;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.ProjectileImpactEvent;

@EventBusSubscriber(modid = McNyan.MODID, bus = EventBusSubscriber.Bus.GAME)
public class PlayerProjectileImpactListener {

    @SubscribeEvent
    public static void OnProjectileImpact(ProjectileImpactEvent event) {
        LocalPlayer lPlayer = Minecraft.getInstance().player;
        if (lPlayer == null) return;

        if (event.getRayTraceResult().distanceTo(lPlayer) == 0.0) {
            HttpRequestHandler.getInstance().sendMessage(new VnyanMessage(
                    "ProjectileImpact",
                    new ProjectilePayload(
                            event.getProjectile().getName().getString()
            )));
        }
    }
}
