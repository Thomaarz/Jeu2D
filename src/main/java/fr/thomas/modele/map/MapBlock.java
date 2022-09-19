package fr.thomas.modele.map;

import fr.thomas.modele.entity.Player;

public class MapBlock extends MapEntity {

    public MapBlock(int x, int y) {
        super(x, y, false);
    }

    @Override
    public void onHurt(Player player) {

    }
}
