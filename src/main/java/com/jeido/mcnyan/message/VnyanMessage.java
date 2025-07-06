package com.jeido.mcnyan.message;

import com.jeido.mcnyan.message.payload.EmptyPayload;
import com.jeido.mcnyan.message.payload.VnyanPayload;

public class VnyanMessage {
    public String action;
    public VnyanPayload payload;

    public VnyanMessage(String action, VnyanPayload payload) {
        this.action = action;
        this.payload = payload;
    }

    public VnyanMessage(String action) {
        this.action = action;
        this.payload = new EmptyPayload();
    }
}
