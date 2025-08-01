package com.jeido.mcnyan.message.payload;

import net.minecraft.world.phys.Vec3;

import java.util.Formatter;
import java.util.Locale;

public record Vec3Payload(double x, double y, double z, String prefix) implements VnyanPayload {

    public Vec3Payload(Vec3 vec3, String prefix) {
        this(vec3.x(), vec3.y(), vec3.z(), prefix);
    }

    public Vec3Payload(double x, double y, double z) {
        this(x,y,z,"");
    }

    public Vec3Payload(Vec3 vec3) {
        this(vec3, "");
    }

    @Override
    public String serialize() {
        String xEntry = prefix.isBlank() ? "x" : prefix + "X";
        String yEntry = prefix.isBlank() ? "y" : prefix + "Y";
        String zEntry = prefix.isBlank() ? "z" : prefix + "Z";

        return new Formatter(Locale.US).format("\"%s\":%f,\"%s\":%f,\"%s\":%f",
                xEntry, x,
                yEntry, y,
                zEntry, z
        ).toString();
    }
}
