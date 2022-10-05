package fr.thomas.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class Utils {

    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm:ss");
    private static SimpleDateFormat gameFormat = new SimpleDateFormat("dd MMM à hh:mm_ss");

    public static int random(int min, int max) {
        return new Random().nextInt(max - min + 1) + min;
    }

    public static String getTime() {
        Date date = new Date();
        return simpleDateFormat.format(date);
    }

    public static String getGameTime() {
        Date date = new Date();
        return gameFormat.format(date).replaceAll(":", "h").replaceAll("_", "min") + "sec";
    }
}
