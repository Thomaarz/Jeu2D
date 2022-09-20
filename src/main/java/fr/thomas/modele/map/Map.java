package fr.thomas.modele.map;

import fr.thomas.Infos;
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

        createMap();
    }

    private void createMap() {
        // Bord
        for (int x = 0; x < Infos.MAP_SIZE; x++) {
            for (int y = 0; y < Infos.MAP_SIZE; y++) {
                if (x == 0 || y == 0 || x == Infos.MAP_SIZE - 1 || y == Infos.MAP_SIZE - 1) {
                    mapElements.add(new MapBlock(x, y));
                }
            }
        }
        while (mapElements.size() < Infos.MAP_SIZE * 4 + 25) {
            int x = Utils.random(2, Infos.MAP_SIZE - 1);
            int y = Utils.random(2, Infos.MAP_SIZE - 1);

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

    public void reset() {
        this.mapElements = new ArrayList<>();
        createMap();

        player.set(1, 4);
        player.setPower(Player.DEFAULT_POWER);
    }

}
