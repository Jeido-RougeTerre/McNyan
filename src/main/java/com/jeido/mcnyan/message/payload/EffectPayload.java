package com.jeido.mcnyan.message.payload;

public record EffectPayload(String name, int amplifier, int duration, String color) implements VnyanPayload {

    @Override
    public String serialize() {
        return String.format(
            "\"effectName\": \"%s\", " + "\"effectAmplifier\": %d, \"effectDuration\": %d, \"effectColor\": \"%s\"",
            name, amplifier, duration, color
        );
    }
}
