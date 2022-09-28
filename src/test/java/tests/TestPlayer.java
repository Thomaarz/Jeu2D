package tests;

import fr.thomas.exceptions.MovementException;
import fr.thomas.modele.entity.Movement;
import fr.thomas.modele.entity.Player;
import org.junit.Assert;
import org.junit.Test;

public class TestPlayer {

    @Test
    public void movementTests() {
        Player player = new Player(0, 0);

        // X Coord
        Assert.assertEquals("Player X position #1", 0, player.getX());
        Assert.assertEquals("Player X position #2", 1, player.move(Movement.RIGHT).getX());

        // Y Coord
        Assert.assertEquals("Player Y position #1", 0, player.getY());
        Assert.assertEquals("Player Y position #2", 1, player.move(Movement.BOTTOM).getY());
    }

    @Test
    public void powerTests() throws MovementException {
        Player player = new Player(0, 0);

        Assert.assertEquals("Power #1",  100, player.getPower());

        Assert.assertEquals("Power #2",  90, player.removePower(10).getPower());
        Assert.assertEquals("Power #3",  40, player.removePower(50).getPower());
        Assert.assertEquals("Power #4",  0, player.removePower(40).getPower());

        Assert.assertThrows("Power Exception #1",  MovementException.class, () -> player.removePower(10));
    }

    @Test
    public void movementsHisoryTests() {
        Player player = new Player(0, 0);

        player.addMovement(Movement.RIGHT);
        player.addMovement(Movement.TOP);
        player.addMovement(Movement.LEFT);
        player.addMovement(Movement.BOTTOM);

        // History
        Assert.assertEquals("First Movement History #1", Movement.RIGHT, player.getMovementsHistory().get(0));
        Assert.assertEquals("First Movement History #2", Movement.TOP, player.getMovementsHistory().get(1));
        Assert.assertEquals("First Movement History #3", Movement.LEFT, player.getMovementsHistory().get(2));
        Assert.assertEquals("First Movement History #4", Movement.BOTTOM, player.getMovementsHistory().get(3));
    }
}
