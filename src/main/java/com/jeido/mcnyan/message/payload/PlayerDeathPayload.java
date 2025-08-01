package com.jeido.mcnyan.message.payload;

import java.util.UUID;

public record PlayerDeathPayload(String playerName, UUID playerUUID, Vec3Payload playerPos, DamageSourcePayload source, String deathMessage) implements VnyanPayload {
    @Override
    public String serialize() {
        return String.format("\"playerName\":\"%s\",\"playerUUID\":\"%s\",%s,%s,\"deathMessage\":\"%s\"",
                playerName, playerUUID, playerPos.serialize(), source.serialize(), deathMessage);
    }
}
