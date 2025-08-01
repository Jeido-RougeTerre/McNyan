package com.jeido.mcnyan.message.payload;

public class EmptyPayload implements VnyanPayload {

    @Override
    public String serialize() {
        return "";
    }
}
