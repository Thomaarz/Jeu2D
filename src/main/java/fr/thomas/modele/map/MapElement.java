package fr.thomas.modele.map;

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

        this.xProperty = new SimpleDoubleProperty(x);
        this.yProperty = new SimpleDoubleProperty(y);
    }

    public void move(int x, int y) {
        this.x += x;
        this.y += y;

        xProperty.set(this.x);
        yProperty.set(this.y);
    }
}
