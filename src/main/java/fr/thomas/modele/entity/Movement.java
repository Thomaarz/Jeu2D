package fr.thomas.modele.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Movement {

    TOP(0, 0, -1),
    BOTTOM(1, 0, 1),
    LEFT(2, -1, 0),
    RIGHT(3, 1, 0);

    private final int id;
    private final int x;
    private final int y;

    /**
     * Get the opposite direction from the current
     */
    public Movement getOpposite() {
        return this == TOP ? BOTTOM : this == BOTTOM ? TOP : this == RIGHT ? LEFT : RIGHT;
    }

    public static Movement get(int id) {
        for (Movement movement : values()) {
            if (movement.getId() == id) {
                return movement;
            }
        }
        return null;
    }

}
