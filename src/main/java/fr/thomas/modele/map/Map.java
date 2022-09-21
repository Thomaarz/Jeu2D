package fr.thomas.modele.map;

import fr.thomas.Infos;
import fr.thomas.modele.map.entity.House;
import fr.thomas.modele.map.entity.Void;
import fr.thomas.utils.Utils;
import fr.thomas.modele.entity.Player;
import fr.thomas.modele.map.entity.Bloc;
import fr.thomas.modele.map.entity.Energy;
import fr.thomas.modele.map.entity.MapEntity;
import lombok.Getter;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Getter
public class Map {

    private Player player;
    private List<MapEntity> mapEntities;

    public Map() {
        this.player = new Player(1, 4);
        this.mapEntities = new ArrayList<>();

        createMap();
    }

    /**
     * Create the map with dimension defined in {@link Infos}
     */
    private void createMap() {

        // Bord
        for (int x = 0; x < Infos.MAP_SIZE; x++) {
            for (int y = 0; y < Infos.MAP_SIZE; y++) {
                if (x == 0 || y == 0 || x == Infos.MAP_SIZE - 1 || y == Infos.MAP_SIZE - 1) {
                    mapEntities.add(new Bloc(x, y));
                }
            }
        }

        // Create a random amount of entity
        createEntities(Bloc.class, 20, 25);
        createEntities(Energy.class, 5, 7);
        createEntities(House.class, 1, 1);
        createEntities(Void.class, 2, 5);
    }

    /**
     * Check if the current map is winnable with verify the power or not
     * Used for generate a winnable map
     */
    private boolean canWin(boolean usePower) {
        // TODO
        return true;
    }

    /**
     * Get tans element of the mapElements
     * @param x: coord x
     * @param y: coord y
     * @return MapEntity: The entity at the position
     */
    public MapEntity getElement(int x, int y) {
        Optional<MapEntity> optional = mapEntities.stream().filter(mapElement -> mapElement.getX() == x && mapElement.getY() == y).findAny();

        return optional.orElse(null);
    }

    /**
     * Reset the map (entities, create new map, and reset player)
     */
    public void reset() {
        this.mapEntities = new ArrayList<>();
        createMap();

        player.reset();
    }

    /**
     * Function to create random amount of entities
     * @param e: the entity type to create ({@link House}, {@link Bloc}, {@link Void}, {@link Energy})
     * @param min: the min amount of entity to create
     * @param max: the max amount of entity to create
     */
    public void createEntities(Class<? extends MapEntity> e, int min, int max) {
        int current = 0;
        int choose = Utils.random(min, max);

        while (current < choose) {
            int x = Utils.random(2, Infos.MAP_SIZE - 1);
            int y = Utils.random(2, Infos.MAP_SIZE - 1);

            Localizable element = getElement(x, y);

            // Element already exist at this position
            if (element != null) {
                continue;
            }

            try {
                MapEntity entity = e.getDeclaredConstructor(int.class, int.class).newInstance(x, y);
                mapEntities.add(entity);
                current++;
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                     NoSuchMethodException ex) {
                ex.printStackTrace();
            }
        }
    }
}
