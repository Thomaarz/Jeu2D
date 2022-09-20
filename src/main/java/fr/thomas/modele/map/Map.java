package fr.thomas.modele.map;

import fr.thomas.modele.entity.Player;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Getter
public class Map {

    private Player player;
    private List<MapElement> mapElements;

    public Map() {
        this.player = new Player(0, 0);
        this.mapElements = new ArrayList<>();
    }

    private void createMap() {

    }

    public MapElement getElement(int x, int y) {
        Optional<MapElement> optional = mapElements.stream().filter(mapElement -> mapElement.getX() == x && mapElement.getY() == y).findAny();

        return optional.orElse(null);
    }

}
