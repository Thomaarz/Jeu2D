package fr.thomas.modele.map.entity;

import fr.thomas.Infos;
import fr.thomas.exceptions.MovementException;
import fr.thomas.modele.entity.MovementResult;
import fr.thomas.modele.entity.Player;
import fr.thomas.modele.map.Localizable;
import fr.thomas.vue.VueElement;
import javafx.beans.property.SimpleDoubleProperty;
import lombok.Getter;

@Getter
public abstract class MapEntity extends Localizable {

    public MapEntity() {
        super(0, 0);
    }

    public MapEntity(int x, int y) {
        super(x, y);

    }

    /**
     * Call when the player try to go on the MapEntity position
     */
    public abstract MovementResult onPass(Player player, VueElement vueElement) throws MovementException;
}
