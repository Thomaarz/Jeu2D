package fr.thomas.modele.game;

import fr.thomas.modele.map.Map;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Game {

    private Map map;

    @Setter
    private GameState gameState;

    public Game(Map map, GameState gameState) {
        this.map = map;
        this.gameState = gameState;
    }

}
