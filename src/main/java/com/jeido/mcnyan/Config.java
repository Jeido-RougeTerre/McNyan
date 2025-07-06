package com.jeido.mcnyan;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.config.ModConfigEvent;
import net.neoforged.neoforge.common.ModConfigSpec;


// An example config class. This is not required, but it's a good idea to have one to keep your config organized.
// Demonstrates how to use Neo's config APIs
@EventBusSubscriber(modid = McNyan.MODID, bus = EventBusSubscriber.Bus.MOD)
public class Config {
    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();


    private static final ModConfigSpec.ConfigValue<String> HOST = BUILDER.comment("The Host address to POST/GET. Vnyan listen to http://localhost by default").define("host", "http://localhost");
    private static final ModConfigSpec.IntValue PORT = BUILDER.comment("The Port to POST/GET. Vnyan listen to port 8069 by default").defineInRange("port", 8069, 0, 65535);

    private static final ModConfigSpec.BooleanValue DEBUG_MODE = BUILDER.comment("Log all the message sent to VNyan").define("debugMode", false);

    // a list of strings that are treated as resource locations for items
    static final ModConfigSpec SPEC = BUILDER.build();


    public static String host;
    public static int port;
    public static boolean debugMode;

    @SubscribeEvent
    static void onLoad(final ModConfigEvent event) {
        port = PORT.get();
        host = HOST.get();
        debugMode = DEBUG_MODE.get();
    }
}
