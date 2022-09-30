package fr.thomas.modele.map.entity;

import fr.thomas.exceptions.MovementException;
import fr.thomas.modele.entity.MovementResult;
import fr.thomas.modele.entity.Player;
import fr.thomas.vue.VueElement;

public class Bloc extends MapEntity {

    public Bloc(int x, int y) {
        super(x, y);
    }

    @Override
    public MovementResult onPass(Player player, VueElement vueElement) throws MovementException {
        player.removePower(10);
        return MovementResult.DENY;
    }
}
