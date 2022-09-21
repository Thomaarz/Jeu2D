package fr.thomas.modele.map.entity;

import fr.thomas.modele.entity.MovementResult;
import fr.thomas.modele.entity.Player;
import fr.thomas.vue.VueElement;

public class Void extends MapEntity {

    public Void(int x, int y) {
        super(x, y);
    }

    @Override
    public MovementResult onPass(Player player, VueElement vueElement) {
        return MovementResult.VOID;
    }
}
