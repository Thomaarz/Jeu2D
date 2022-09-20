package fr.thomas.controller;

import fr.thomas.modele.entity.Movement;
import fr.thomas.modele.entity.Player;
import fr.thomas.modele.map.Map;
import fr.thomas.modele.map.MapElement;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

public class KeyListener implements EventHandler<KeyEvent> {

    private Map map;
    private Player player;

    public KeyListener(Controller controller) {
        this.map = controller.getMap();
        this.player = map.getPlayer();
    }

    @Override
    public void handle(KeyEvent event) {
        Movement movement = null;
        switch (event.getCode()) {
            case Z:
                movement = Movement.TOP;
                break;
            case S:
                movement = Movement.BOTTOM;
                break;
            case Q:
                movement = Movement.LEFT;
                break;
            case D:
                movement = Movement.RIGHT;
                break;
        }

        if (movement == null) {
            return;
        }

        MapElement element = map.getElement(player.getX() + movement.getX(), player.getY() + movement.getY());

        if (element == null) {
            if (player.getPower() <= 0) {
                return;
            }
            player.move(movement);
            player.removePower(Player.POWER_LOSS);
        }
    }
}
