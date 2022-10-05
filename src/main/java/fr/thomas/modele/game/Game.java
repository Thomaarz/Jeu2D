package fr.thomas.modele.game;

import fr.thomas.modele.entity.Player;
import fr.thomas.modele.map.Map;
import fr.thomas.utils.Utils;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class Game {

    private String name = Utils.getGameTime() + ".txt";

    private String lore;

    private Player player;

    private Map map;

    private boolean end;

    public Game(String name, Player player, Map map, boolean end) {
        this.name = name;
        this.lore = "Partie - " + Utils.getGameTime();
        this.player = player;
        this.map = map;
        this.end = end;
    }


    public Game(Player player, Map map) {
        this.lore = "Partie - " + Utils.getGameTime();
        this.player = player;
        this.map = map;
        this.end = false;
    }

    @Override
    public String toString() {
        return name;
    }
}
