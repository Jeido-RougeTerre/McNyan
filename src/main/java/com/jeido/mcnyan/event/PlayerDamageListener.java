package com.jeido.mcnyan.event;

import com.jeido.mcnyan.McNyan;
import com.jeido.mcnyan.http.HttpRequestHandler;
import com.jeido.mcnyan.message.VnyanMessage;
import com.jeido.mcnyan.message.payload.*;
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
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;

@EventBusSubscriber(modid = McNyan.MODID, bus = EventBusSubscriber.Bus.GAME)
public class PlayerDamageListener {


    @SubscribeEvent
    public static void onPlayerDamage(LivingDamageEvent.Pre event) {

        LocalPlayer lPlayer = Minecraft.getInstance().player;
        if (lPlayer == null) return;

        DamageSource source = event.getSource();
        Entity entity = source.getEntity();
        ItemStack weapon = source.getWeaponItem();
        Vec3 pos = source.getSourcePosition() != null ? source.getSourcePosition() : lPlayer.position();
        DamageType type = source.type();

        if (event.getEntity() instanceof Player && event.getEntity().getUUID().equals(lPlayer.getUUID())) {

            HttpRequestHandler.getInstance().sendMessage(new VnyanMessage("Damage",
                    new PlayerDamagePayload(
                            event.getEntity().getName().getString(),
                            event.getEntity().getUUID(),
                            new Vec3Payload(lPlayer.position(), "player"),
                            event.getOriginalDamage(),
                            new DamageSourcePayload(
                                    new Vec3Payload(pos, "source"),
                                    type.msgId(),
                                    entity != null ? entity.getType().toString() : "null",
                                    weapon != null ? new ItemStackPayload(weapon) : new ItemStackPayload()
                            )
                    )
            ));
        }
    }
}
