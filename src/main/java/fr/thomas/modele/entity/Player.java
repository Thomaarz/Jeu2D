package fr.thomas.modele.entity;

import fr.thomas.modele.map.MapElement;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Player extends MapElement {

    public static final int DEFAULT_POWER = 100;
    public static final int POWER_LOSS = 10;
    public static final int POWER_WIN = 10;

    private int power = DEFAULT_POWER;
    private int canceledMovements = 0;
    private List<Movement> movementsHistory = new ArrayList<>();

    public Player(int x, int y) {
        super(x, y);
    }

    /**
     * Move the player in the movement and remove the power
     */
    public void move(Movement movement) {
        super.move(movement.getX(), movement.getY());
        removePower(POWER_LOSS);
    }

    /**
     * Remove power
     */
    public void removePower(int power) {
        this.power -= power;
    }

    /**
     * Add Power
     */
    public void addPower(int power) {
        this.power += power;
    }

    /**
     * Add Movement in history
     */
    public void addMovement(Movement movement) {
        movementsHistory.add(movement);
    }

    /**
     * Cancel "amount" movement
     */
    public void cancelMovement(int amount) {
        for (int i = 0; i < amount; i++) {
            cancelMovement();
        }
    }

    /**
     * Cancel one movement, remove from history and give power
     */
    private void cancelMovement() {
        // Last Movement
        Movement last = movementsHistory.get(movementsHistory.size() - 1);

        // Move Opossite Direction
        move(last.getOpposite());

        // Remove Last Movement from History
        movementsHistory.remove(movementsHistory.size() - 1);

        // Give Power
        addPower(POWER_WIN + POWER_LOSS);
    }

    @Override
    public String toString() {
        return "Player{" +
                "power=" + power +
                ", canceledMovements=" + canceledMovements +
                ", movementsHistory=" + movementsHistory +
                '}';
    }
}
