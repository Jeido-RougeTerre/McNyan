package com.jeido.mcnyan.message.payload;

public record EquipPayload(String slot, String itemStack) implements VnyanPayload{
    @Override
    public String serialize() {
        return String.format("\"slot\": \"%s\", \"itemStack\": \"%s\"", slot, itemStack);
    }
}
