package fr.thomas.modele.map;

import fr.thomas.modele.entity.Player;

import java.util.List;
import java.util.Optional;

public class Map {

    private Player player;
    private List<MapElement> mapElements;

    private void createMap() {

    }

    public MapElement getElement(int x, int y) {
        Optional<MapElement> optional = mapElements.stream().filter(mapElement -> mapElement.getX() == x && mapElement.getY() == y).findAny();

        return optional.orElse(null);
    }

}
