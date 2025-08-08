package com.jeido.mcnyan.message.payload;

public record ProjectilePayload(String name) implements VnyanPayload {

    @Override
    public String serialize() {
        return String.format("\"projectileName\": \"%s\"", name);
    }
}
