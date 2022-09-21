package fr.thomas.modele.map.entity;

import fr.thomas.modele.entity.MovementResult;
import fr.thomas.modele.entity.Player;
import fr.thomas.vue.VueElement;

public class Empty extends MapEntity {

    public Empty(int x, int y) {
        super(x, y);
    }

    @Override
    public MovementResult onPass(Player player, VueElement vueElement) {
        return MovementResult.ALLOW;
    }
}
