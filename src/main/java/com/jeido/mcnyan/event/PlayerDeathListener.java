package com.jeido.mcnyan.event;

import com.jeido.mcnyan.McNyan;
import com.jeido.mcnyan.http.HttpRequestHandler;
import com.jeido.mcnyan.message.VnyanMessage;
import com.jeido.mcnyan.message.payload.DamageSourcePayload;
import com.jeido.mcnyan.message.payload.PlayerDeathPayload;
import com.jeido.mcnyan.message.payload.Vec3Payload;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec3;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;


@EventBusSubscriber(modid = McNyan.MODID, bus = EventBusSubscriber.Bus.GAME)
public class PlayerDeathListener {

    @SubscribeEvent
    public static void onPlayerDeath(LivingDeathEvent event) {
        LocalPlayer lPlayer = Minecraft.getInstance().player;
        if (lPlayer == null) return;

        if (event.getEntity() instanceof Player player && event.getEntity().getUUID().equals(lPlayer.getUUID())) {

            DamageSource source = event.getSource();
            Entity entity = source.getEntity();
            ItemStack weapon = source.getWeaponItem();
            Vec3 pos = source.getSourcePosition() != null ? source.getSourcePosition() : lPlayer.position();
            DamageType type = source.type();

            HttpRequestHandler.getInstance().sendMessage(new VnyanMessage("Death",
                    new PlayerDeathPayload(
                            player.getName().getString(),
                            player.getUUID(),
                            new Vec3Payload(lPlayer.position(), "player"),
                            new DamageSourcePayload(
                                    new Vec3Payload(pos, "source"),
                                    type.msgId(),
                                    entity != null ? entity.getType().toString() : "null",
                                    weapon!= null ? weapon.toString() : "null"
                            ),
                            source.getLocalizedDeathMessage(event.getEntity()).getString()
                    )
            ));
        }
    }
}
