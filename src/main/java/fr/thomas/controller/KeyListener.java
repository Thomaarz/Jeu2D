package fr.thomas.controller;

import fr.thomas.controller.tasks.LoseTask;
import fr.thomas.controller.tasks.WinTask;
import fr.thomas.exceptions.MovementException;
import fr.thomas.modele.entity.Movement;
import fr.thomas.modele.entity.MovementResult;
import fr.thomas.modele.entity.Player;
import fr.thomas.modele.map.Map;
import fr.thomas.modele.map.entity.MapEntity;
import fr.thomas.vue.VueElement;
import fr.thomas.vue.bloc.VueEnergy;
import javafx.event.EventHandler;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyEvent;

public class KeyListener implements EventHandler<KeyEvent> {

    private Controller controller;
    private Map map;
    private Player player;
    private TextArea textChat;

    public KeyListener(Controller controller) {
        this.controller = controller;
        this.map = controller.getMap();
        this.player = map.getPlayer();
        this.textChat = controller.getTextChat();
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

        MapEntity element = map.getElement(player.getX() + movement.getX(), player.getY() + movement.getY());

        MovementResult result = MovementResult.ALLOW;
        if (element != null) {
            VueElement vueElement = controller.getVueElements().get("entity" + element.getId());
            result = element.onPass(player, vueElement);
            if (vueElement instanceof VueEnergy) {
                controller.getVueElements().remove(vueElement.getId());
                controller.getMap().getMapEntities().remove(element);
            }
        }

        if (result == MovementResult.ALLOW) {
            try {
                player.removePower(Player.POWER_LOSS);
                player.addMovement(movement);
                player.move(movement);
            } catch (MovementException e) {
                controller.addChatLine(e.getMessage());
            }
        } else if (result == MovementResult.WIN) {
            controller.addChatLine("GAGNE !");
            new WinTask(controller).start();
        } else if (result == MovementResult.VOID) {
            controller.addChatLine("TOMBE DANS LE PUIS !");
            new LoseTask(controller).start();
        }
    }
}
