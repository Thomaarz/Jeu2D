package fr.thomas.modele.game;

import fr.thomas.modele.map.Map;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class Game {

    private Map map;

    private GameState gameState;

}
