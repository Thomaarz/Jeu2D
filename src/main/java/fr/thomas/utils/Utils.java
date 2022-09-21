package fr.thomas.utils;

import com.google.gson.Gson;
import fr.thomas.modele.game.Game;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
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

    public static Game loadGame() {
        String path = "data/games.json";
        Gson gson = new Gson();

        try {

            File file = new File(path);
            if (!file.exists()) {
                file.createNewFile();
            }

            Game game = gson.fromJson(new FileReader(path), Game.class);

            return game;
        } catch (IOException ignored) {

        }
        return null;
    }

    public static void saveGame(Game game) {
        Gson gson = new Gson();
        try {
            File file = new File("data/games.json");
            FileUtils.write(file, gson.toJson(game));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
