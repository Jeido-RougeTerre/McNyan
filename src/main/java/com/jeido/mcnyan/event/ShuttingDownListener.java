package com.jeido.mcnyan.event;

import com.jeido.mcnyan.McNyan;
import com.jeido.mcnyan.http.HttpRequestHandler;
import com.jeido.mcnyan.message.VnyanMessage;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.GameShuttingDownEvent;

@EventBusSubscriber(modid = McNyan.MODID, bus = EventBusSubscriber.Bus.GAME)
public class ShuttingDownListener {

    @SubscribeEvent
    public static void OnShuttingDown(GameShuttingDownEvent event) {
        HttpRequestHandler.getInstance().sendMessage(new VnyanMessage("ShuttingDown"));
    }
}
