package com.jeido.mcnyan.message.payload;

import java.util.UUID;

public record PlayerDamagePayload(String playerName, UUID playerUUID, float damage) implements VnyanPayload {

}
