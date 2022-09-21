package fr.thomas.modele.map.entity;

import fr.thomas.modele.entity.MovementResult;
import fr.thomas.modele.entity.Player;
import fr.thomas.vue.VueElement;

public class Energy extends MapEntity {

    public Energy(int x, int y) {
        super(x, y);
    }

    @Override
    public MovementResult onPass(Player player, VueElement vueElement) {
        player.addPower(50);
        vueElement.remove();
        return MovementResult.ALLOW;
    }
}
