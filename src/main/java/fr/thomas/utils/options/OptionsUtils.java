package fr.thomas.utils.options;

import lombok.Getter;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

@Getter
public class OptionsUtils {

    private static String PATH = "options.txt";

    public static Map<Options, String> optionValues = new HashMap<>();

    /**
     * Check is a key is already bind
     * @param key: the key to verify
     * @return true if the key is not assigned to any option
     */
    public static boolean isBind(String key) {
        for (Map.Entry<Options, String> option : optionValues.entrySet()) {
            if (option.getValue().equalsIgnoreCase(key)) {
                return true;
            }
        }
        return false;
    }

    public static void createDefaultIfNotExist() {
        File file = new File(PATH);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        optionValues.put(Options.MOVE_TOP, "Z");
        optionValues.put(Options.MOVE_BOTTOM, "S");
        optionValues.put(Options.MOVE_LEFT, "Q");
        optionValues.put(Options.MOVE_RIGHT, "D");
    }

    public static void loadOptions() {
        try {
            FileInputStream fileInputStream = new FileInputStream(PATH);
            Scanner scanner = new Scanner(fileInputStream);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();

                Options options = Options.getFromLine(line);
                String value = line.split("=")[1];

                optionValues.put(options, value);
            }

            scanner.close();
        } catch (Exception e) {

        }
    }

    public static void saveOptions() {
        try {
            PrintWriter writer = new PrintWriter(PATH, "UTF-8");
            optionValues.forEach((options, optionValue) -> {
                writer.println(options.getKey() + "=" + optionValue);
            });
            writer.close();
        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }
}
