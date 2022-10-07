package fr.thomas.modele.map;

import fr.thomas.Infos;
import fr.thomas.modele.map.entity.*;
import fr.thomas.modele.map.entity.Void;
import fr.thomas.utils.Utils;
import lombok.Getter;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Getter
public class Map {

    private List<MapEntity> mapEntities;

    public Map() {
        this.mapEntities = new ArrayList<>();
    }

    public List<MapEntity> getElements(Class<? extends MapEntity> e) {
        return mapEntities.stream().filter(entity -> entity.getClass() == e).collect(Collectors.toList());
    }

    /**
     * Create the map with dimension defined in {@link Infos}
     */
    public void createMap() {

        // Bord
        for (int x = 0; x < Infos.MAP_SIZE; x++) {
            for (int y = 0; y < Infos.MAP_SIZE; y++) {
                if (x == 0 || y == 0 || x == Infos.MAP_SIZE - 1 || y == Infos.MAP_SIZE - 1) {
                    mapEntities.add(new Bloc(x, y));
                }
            }
        }

        // Create a random amount of entity
        createEntities(House.class, 1, 1);
        createBlocs(Bloc.class);
        //createEntities(Enemy.class, 1, 3);
        createEntities(Energy.class, 10, 12);

    }

    /**
     * Check if the current map is winnable with verify the power or not
     * Used for generate a winnable map
     */
    private boolean canGo() {
        Localizable base = new Localizable(Infos.MAP_SIZE / 2, Infos.MAP_SIZE / 2);
        MapSolver solver = new MapSolver(this);
        return solver.canGo(base, solver.getHouse());
    }

    /**
     * Check if the player can go to a location
     * @param to: The location where the player want to go
     * @return true if the player can go to the location
     */
    private boolean canGo(Localizable to) {
        Localizable from = new Localizable(Infos.MAP_SIZE / 2, Infos.MAP_SIZE / 2);
        return canGo(from, to);
    }

    /**
     * Check if a path exists between two locations
     * @param from: the begin position
     * @param to: the end position
     * @return true if a path exists
     */
    private boolean canGo(Localizable from, Localizable to) {
        MapSolver solver = new MapSolver(this);
        return solver.canGo(from, to);
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
     * Function to create random amount of entities
     * @param e: the entity type to create ({@link House}, {@link Bloc}, {@link Void}, {@link Energy})
     * @param min: the min amount of entity to create
     * @param max: the max amount of entity to create
     */
    public void createEntities(Class<? extends MapEntity> e, int min, int max) {
        int current = 0;
        int choose = Utils.random(min, max);

        int trys = 0;

        while (current < choose) {
            trys++;
            if (trys >= choose * 3 && e != House.class) {
                break;
            }

            int x = Utils.random(1, Infos.MAP_SIZE - 1);
            int y = Utils.random(1, Infos.MAP_SIZE - 1);

            Localizable element = getElement(x, y);

            // Element already exist at this position
            if (element != null || (x == Infos.MAP_SIZE / 2 && y == Infos.MAP_SIZE / 2)) {
                continue;
            }

            if (!canGo(new Localizable(x, y))) {
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

    public void createBlocs(Class<? extends MapEntity> e) {

        int trys = 0;

        while (canGo()) {

            trys++;
            if (trys >= 500) {
                break;
            }
            if (getElements(Bloc.class).size() >= 200) {
                break;
            }

            int x = Utils.random(1, Infos.MAP_SIZE - 1);
            int y = Utils.random(1, Infos.MAP_SIZE - 1);

            Localizable element = getElement(x, y);

            // Element already exist at this position
            if (element != null || (x == Infos.MAP_SIZE / 2 && y == Infos.MAP_SIZE / 2)) {
                continue;
            }

            try {
                MapEntity entity = e.getDeclaredConstructor(int.class, int.class).newInstance(x, y);
                mapEntities.add(entity);

                if (!canGo()) {
                    mapEntities.remove(entity);
                    return;
                }
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                     NoSuchMethodException ex) {
                ex.printStackTrace();
            }
        }
    }
}
