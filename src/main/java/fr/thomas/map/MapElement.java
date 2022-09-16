package fr.thomas.map;

import lombok.Getter;

@Getter
public abstract class MapElement {

    private int x;
    private int y;

    protected MapElement(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void move(int x, int y) {
        this.x += x;
        this.y += y;
    }
}
