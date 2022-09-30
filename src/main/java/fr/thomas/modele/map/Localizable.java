package fr.thomas.modele.map;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.JsonAdapter;
import fr.thomas.Infos;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import lombok.Getter;

@Getter
public class Localizable {

    public static final int START_X = (Infos.WIDTH / 2) - ((Infos.MAP_SIZE * Infos.TILE_SIZE) / 2);
    public static final int START_Y = 150 - ((Infos.MAP_SIZE * Infos.TILE_SIZE) / 2);

    private static int idAll = 0;

    private int id;

    private int x;
    private int y;


    private SimpleDoubleProperty xProperty;

    private SimpleDoubleProperty yProperty;


    public Localizable(int x, int y) {
        this.id = idAll++;

        this.x = x;
        this.y = y;

        this.xProperty = new SimpleDoubleProperty(x * Infos.TILE_SIZE + START_X);
        this.yProperty = new SimpleDoubleProperty(y * Infos.TILE_SIZE + START_Y);
    }

    public void move(int x, int y) {
        this.x += x;
        this.y += y;

        this.xProperty.set(this.x * Infos.TILE_SIZE + START_X);
        this.yProperty.set(this.y * Infos.TILE_SIZE + START_Y);
    }

    public void set(int x, int y) {
        this.x = x;
        this.y = y;

        this.xProperty.set(this.x * Infos.TILE_SIZE + START_X);
        this.yProperty.set(this.y * Infos.TILE_SIZE + START_Y);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Localizable) {
            Localizable other = (Localizable) obj;
            return x == other.getX() && y == other.getY();
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return "Localizable{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
