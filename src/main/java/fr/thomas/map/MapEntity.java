package fr.thomas.map;

import fr.thomas.entity.Player;
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
