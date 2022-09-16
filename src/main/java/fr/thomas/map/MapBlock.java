package fr.thomas.map;

import fr.thomas.entity.Player;

public class MapBlock extends MapEntity {

    public MapBlock(int x, int y) {
        super(x, y, false);
    }

    @Override
    public void onHurt(Player player) {

    }
}
