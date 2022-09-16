package fr.thomas.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Movement {

    TOP(0, -1),
    BOTTOM(0, 1),
    LEFT(-1, 0),
    RIGHT(1, 0);

    private final int x;
    private final int y;

    /**
     * Get the opposite direction from the current
     */
    public Movement getOpposite() {
        return this == TOP ? BOTTOM : this == BOTTOM ? TOP : this == RIGHT ? LEFT : RIGHT;
    }

}
