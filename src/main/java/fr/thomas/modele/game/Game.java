package fr.thomas.modele.game;

import fr.thomas.modele.entity.Player;
import fr.thomas.modele.map.Map;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Game {

    private Player player;

    private Map map;

    private GameState gameState;


    public Game(Player player, Map map, GameState gameState) {
        this.player = player;
        this.map = map;
        this.gameState = gameState;
    }

}
