package com.jeido.mcnyan.message.payload;

import java.util.Formatter;
import java.util.Locale;

public record XpPayload(
        int totalXp, int xpLevel, int xpNeededForNextLevel, float progress, boolean pre) implements VnyanPayload {

    @Override
    public String serialize() {
        String prefix = (pre)? "pre" : "post";
        return new Formatter(Locale.US).format(
                "\"" + prefix + "TotalXp\":%d," +
                "\"" + prefix + "XpLevel\":%d," +
                "\"" + prefix + "XpNeededForNextLevel\":%d," +
                "\"" + prefix + "Progress\":%f",
                totalXp, xpLevel, xpNeededForNextLevel, progress
            ).toString();
    }

    public XpPayload getPost(int amount) {
        int newXp = totalXp + amount;
        int newLvl;

        if (newXp <= 352) {
            newLvl = (int) Math.floor(Math.sqrt(newXp + 9) - 3);
        } else if (newXp <= 1507) {
            newLvl = (int) Math.floor((81.0 / 10.0) + Math.sqrt((2.0 / 5.0) * (newXp - (7839.0 / 40.0))));
        } else {
            newLvl = (int) Math.floor((325.0 / 18.0) + Math.sqrt((2.0 / 9.0) * (newXp - (54215.0 / 72.0))));
        }

        int newXpNeededForNextLevel;
        if (newLvl <= 15) {
            newXpNeededForNextLevel = 2 * newLvl + 7;
        } else if (newLvl <= 30) {
            newXpNeededForNextLevel = 5 * newLvl - 38;
        } else {
            newXpNeededForNextLevel = 9 * newLvl - 158;
        }

        int expRequiredForCurrent;
        if (newLvl <= 16) {
            expRequiredForCurrent = newLvl * newLvl + 6 * newLvl;
        } else if (newLvl <= 31) {
            expRequiredForCurrent = (int)Math.floor(2.5 * newLvl * newLvl - 40.5 * newLvl + 360);
        } else {
            expRequiredForCurrent = (int)Math.floor(4.5 * newLvl * newLvl - 162.5 * newLvl + 2220);
        }

        int xpSinceCurrentLevel = newXp - expRequiredForCurrent;

        float newProgress =  (float) xpSinceCurrentLevel / newXpNeededForNextLevel;

        return new XpPayload(newXp, newLvl, newXpNeededForNextLevel, newProgress, false);
    }
}
