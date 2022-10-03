package fr.thomas.modele.game;

import fr.thomas.modele.entity.Player;
import fr.thomas.modele.map.Map;
import fr.thomas.utils.Utils;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class Game {

    private String name = "Game-" + Utils.random(0, 10000) + ".txt";

    private String lore;

    private Player player;

    private Map map;

    public Game(String name, Player player, Map map) {
        this.name = name;
        this.lore = "Partie - " + Utils.getGameTime();
        this.player = player;
        this.map = map;
    }


    public Game(Player player, Map map) {
        this.lore = "Partie - " + Utils.getGameTime();
        this.player = player;
        this.map = map;
    }

    @Override
    public String toString() {
        return name;
    }
}
