package fr.thomas.modele.map;

import fr.thomas.Infos;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import lombok.Getter;

@Getter
public abstract class MapElement {

    private int x;
    private int y;

    private DoubleProperty xProperty;
    private DoubleProperty yProperty;

    protected MapElement(int x, int y) {
        this.x = x;
        this.y = y;

        this.xProperty = new SimpleDoubleProperty(x * Infos.TILE_SIZE);
        this.yProperty = new SimpleDoubleProperty(y * Infos.TILE_SIZE);
    }

    public void move(int x, int y) {
        this.x += x;
        this.y += y;

        this.xProperty.set(this.x * Infos.TILE_SIZE);
        this.yProperty.set(this.y * Infos.TILE_SIZE);
    }
}
