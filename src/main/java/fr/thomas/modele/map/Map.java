package fr.thomas.modele.map;

import fr.thomas.Utils;
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
        this.player = new Player(1, 4);
        this.mapElements = new ArrayList<>();
    }

    public void createMap() {
        // Bord
        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 9; y++) {
                if (x == 0 || y == 0 || x == 8 || y == 8) {
                    mapElements.add(new MapBlock(x, y));
                }
            }
        }
        while (mapElements.size() < 40) {
            int x = Utils.random(2, 6);
            int y = Utils.random(2, 6);

            MapElement element = getElement(x, y);

            // Element already exist at this position
            if (element != null) {
                continue;
            }

            MapBlock block = new MapBlock(x, y);
            mapElements.add(block);
        }
    }

    private boolean canWin(boolean usePower) {
        // TODO
        return true;
    }

    public MapElement getElement(int x, int y) {
        Optional<MapElement> optional = mapElements.stream().filter(mapElement -> mapElement.getX() == x && mapElement.getY() == y).findAny();

        return optional.orElse(null);
    }

}
