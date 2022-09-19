package fr.thomas.modele.map;

import fr.thomas.modele.entity.Player;
import lombok.Getter;

@Getter
public abstract class MapEntity extends MapElement {

    private boolean canPlayerPass;
    public MapEntity(int x, int y, boolean canPlayerPass) {
        super(x, y);
        this.canPlayerPass = canPlayerPass;
    }

    /**
     * Call when the player try to go on the MapEntity position
     */
    public abstract void onHurt(Player player);
}
