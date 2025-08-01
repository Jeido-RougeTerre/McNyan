package com.jeido.mcnyan.message.payload;


import java.util.Formatter;
import java.util.Locale;
import java.util.UUID;

public record PlayerDamagePayload(String playerName, UUID playerUUID, Vec3Payload playerPos, float damage, DamageSourcePayload source) implements VnyanPayload {

    @Override
    public String serialize() {
        return new Formatter(Locale.US).format("\"playerName\":\"%s\",\"playerUUID\":\"%s\",%s,\"damage\":%f,%s",
        playerName, playerUUID, playerPos.serialize(), damage, source.serialize()
        ).toString();
    }
}
