package com.jeido.mcnyan.message.payload;

import java.util.Formatter;
import java.util.Locale;

public record XpChangePayload(int amount, XpPayload pre) implements VnyanPayload{

    @Override
    public String serialize() {

        XpPayload post = pre.getPost(amount);

        return new Formatter(Locale.US).format(
                "\"amount\":%d,%s,%s", amount, pre.serialize(), post.serialize()
        ).toString();
    }
}
