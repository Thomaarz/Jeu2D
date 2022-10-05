package fr.thomas.modele.map.save;

import fr.thomas.modele.entity.Movement;
import fr.thomas.modele.entity.Player;
import fr.thomas.modele.game.Game;
import fr.thomas.modele.map.Localizable;
import fr.thomas.modele.map.Map;
import fr.thomas.modele.map.entity.Bloc;
import fr.thomas.modele.map.entity.Energy;
import fr.thomas.modele.map.entity.House;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class SaveUtils {

    public static boolean saveExist(String name) {
        return new File("data/game/" + name + ".txt").exists();
    }

    public static List<String> getSavedGames() {
        File folder = new File("data/game/");

        folder.mkdirs();

        return Arrays.asList(folder.listFiles()).stream().map(file -> file.getName()).collect(Collectors.toList());
    }

    public static Game loadSave(String name) {
        String path = "data/game/" + name;

        Player player = null;
        Map map = new Map();
        boolean end = false;

        try {
            FileInputStream fileInputStream = new FileInputStream(path);
            Scanner scanner = new Scanner(fileInputStream);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();

                String key = line.split("=")[0];
                String[] values = line.split("=")[1].split(";");

                if (key.equalsIgnoreCase("player")) {
                    player = new Player(Integer.parseInt(values[0]), Integer.parseInt(values[1]));
                    player.setPower(Integer.parseInt(values[2]));
                    for (String movement : values[3].split("-")) {
                        player.addMovement(Movement.get(Integer.parseInt(movement)));
                    }
                    for (String visited : values[4].split("-")) {
                        try {
                            int x = Integer.parseInt(visited.split(":")[0]);
                            int y = Integer.parseInt(visited.split(":")[1]);

                            player.getVisited().add(new Localizable(x, y));
                        } catch (NumberFormatException e) {

                        }
                    }
                } else if (key.equalsIgnoreCase("house")) {
                    map.getMapEntities().add(new House(Integer.parseInt(values[0]), Integer.parseInt(values[1])));
                } else if (key.equalsIgnoreCase("energy")) {
                    map.getMapEntities().add(new Energy(Integer.parseInt(values[0]), Integer.parseInt(values[1])));
                } else if (key.equalsIgnoreCase("bloc")) {
                    map.getMapEntities().add(new Bloc(Integer.parseInt(values[0]), Integer.parseInt(values[1])));
                } else if (key.equalsIgnoreCase("end")) {
                    end = values[0].equalsIgnoreCase("1");
                }
            }

            scanner.close();
        } catch (Exception e) {

        }

        if (player == null) {
            return null;
        }

        return new Game(name, player, map, end);
    }

    public static void save(Game game) {
        String path = "data/game/" + game.getName().replaceAll(".txt", "").replaceAll(" ", "_") + ".txt";

        File directory = new File("data/game/");
        directory.mkdirs();

        File file = new File(path);
        if (!file.exists()) {
            try {

                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            PrintWriter writer = new PrintWriter(path, "UTF-8");
            StringBuilder movements = new StringBuilder();
            for (int i = 0; i < game.getPlayer().getMovementsHistory().size(); i++) {
                Movement movement = game.getPlayer().getMovementsHistory().get(i);
                movements.append(movement.getId()).append(i == game.getPlayer().getMovementsHistory().size() ? "" : "-");
            }
            StringBuilder visiteds = new StringBuilder();
            for (int i = 0; i < game.getPlayer().getVisited().size(); i++) {

                Localizable localizable = game.getPlayer().getVisited().get(i);
                visiteds.append(localizable.getX()).append(":").append(localizable.getY()).append(i == game.getPlayer().getMovementsHistory().size() ? "" : "-");
            }
            writer.println("Player=" + game.getPlayer().getX() + ";" + game.getPlayer().getY() + ";" + game.getPlayer().getPower() + ";" + movements + ";" + visiteds);
            writer.println("End=" + (game.isEnd() ? "1" : "0"));
            game.getMap().getMapEntities().forEach(entity -> {
                writer.println(entity.getClass().getName().substring(entity.getClass().getName().lastIndexOf('.') + 1) + "=" + entity.getX() + ";" + entity.getY());
            });
            writer.close();
        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    public static void delete(String game) {
        File file = new File("data/game/" + game);

        file.delete();
    }
}
