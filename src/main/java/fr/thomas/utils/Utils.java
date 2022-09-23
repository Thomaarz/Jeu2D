package fr.thomas.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class Utils {

    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm:ss");

    public static int random(int min, int max) {
        return new Random().nextInt(max - min + 1) + min;
    }

    public static String getTime() {
        Date date = new Date();
        return simpleDateFormat.format(date);
    }
}
