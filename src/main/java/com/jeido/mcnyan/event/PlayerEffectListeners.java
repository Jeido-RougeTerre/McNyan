package com.jeido.mcnyan.event;


import com.jeido.mcnyan.McNyan;
import com.jeido.mcnyan.http.HttpRequestHandler;
import com.jeido.mcnyan.message.VnyanMessage;
import com.jeido.mcnyan.message.payload.EffectPayload;
import com.jeido.mcnyan.utils.ColorUtility;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.MobEffectEvent;

@EventBusSubscriber(modid = McNyan.MODID, bus = EventBusSubscriber.Bus.GAME)
public class PlayerEffectListeners {

    @SubscribeEvent
    public static void OnEffectAdded(MobEffectEvent.Added event) {
        LocalPlayer lPlayer = Minecraft.getInstance().player;
        if (lPlayer == null) return;

        if (event.getEntity() instanceof Player && event.getEntity().getUUID().equals(lPlayer.getUUID())) {


            HttpRequestHandler.getInstance().sendMessage(new VnyanMessage("AddedEffect", new EffectPayload(
                    event.getEffectInstance().getEffect().getRegisteredName(),
                    event.getEffectInstance().getAmplifier(),
                    event.getEffectInstance().getDuration(),
                    ColorUtility.intToHexColor(event.getEffectInstance().getEffect().value().getColor())
            )));
        }
    }

    @SubscribeEvent
    public static void OnEffectExpire(MobEffectEvent.Expired event) {
        LocalPlayer lPlayer = Minecraft.getInstance().player;
        if (lPlayer == null) return;

        if (
                event.getEntity() instanceof Player &&
                event.getEntity().getUUID().equals(lPlayer.getUUID()) &&
                event.getEffectInstance() != null
        ) {
            HttpRequestHandler.getInstance().sendMessage(new VnyanMessage("ExpiredEffect", new EffectPayload(
                    event.getEffectInstance().getEffect().getRegisteredName(),
                    event.getEffectInstance().getAmplifier(),
                    0,
                    ""
            )));
        }
    }

    @SubscribeEvent
    public static void OnRemovedEffect(MobEffectEvent.Remove event) {
        LocalPlayer lPlayer = Minecraft.getInstance().player;
        if (lPlayer == null) return;

        if (
                event.getEntity() instanceof Player &&
                        event.getEntity().getUUID().equals(lPlayer.getUUID()) &&
                        event.getEffectInstance() != null
        ) {

            HttpRequestHandler.getInstance().sendMessage(new VnyanMessage("RemovedEffect", new EffectPayload(
                    event.getEffectInstance().getEffect().getRegisteredName(),
                    event.getEffectInstance().getAmplifier(),
                    0,
                    ""
            )));
        }
    }

}
