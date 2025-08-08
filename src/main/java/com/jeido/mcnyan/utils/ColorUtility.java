package com.jeido.mcnyan.utils;

public class ColorUtility {
    public static String intToHexColor(int colorInt) {
        return String.format("#%06X", (0xFFFFFF & colorInt));
    }
}
