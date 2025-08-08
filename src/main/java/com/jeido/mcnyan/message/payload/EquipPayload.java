package com.jeido.mcnyan.message.payload;

public record EquipPayload(String slot, ItemStackPayload itemStack) implements VnyanPayload{
    @Override
    public String serialize() {
        return String.format("\"slot\": \"%s\", %s", slot, itemStack.serialize());
    }
}
