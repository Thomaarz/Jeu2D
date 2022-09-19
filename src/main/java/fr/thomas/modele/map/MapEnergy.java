package fr.thomas.modele.map;

import fr.thomas.modele.entity.Player;

public class MapEnergy extends MapEntity {

    public MapEnergy(int x, int y) {
        super(x, y, true);
    }

    @Override
    public void onHurt(Player player) {

    }
}
