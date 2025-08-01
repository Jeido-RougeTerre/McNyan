package com.jeido.mcnyan.message.payload;

public record DamageSourcePayload(Vec3Payload pos, String type, String entity, String weapon) implements VnyanPayload {
    @Override
    public String serialize() {
        return String.format("%s,\"type\":\"%s\",\"entity\":\"%s\",\"weapon\":\"%s\"",
                pos.serialize(),
                type,
                entity,
                weapon
        );
    }
}
