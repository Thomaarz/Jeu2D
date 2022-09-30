package fr.thomas.modele.map.save;

import fr.thomas.modele.game.Game;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Saves {

    private List<Game> games = new ArrayList<>();

}
