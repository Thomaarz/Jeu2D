package fr.thomas.controller.listeners;

import fr.thomas.controller.Controller;
import fr.thomas.controller.tasks.LoseTask;
import fr.thomas.controller.tasks.WinTask;
import fr.thomas.exceptions.MovementException;
import fr.thomas.modele.entity.Movement;
import fr.thomas.modele.entity.MovementResult;
import fr.thomas.modele.entity.Player;
import fr.thomas.modele.game.GameState;
import fr.thomas.modele.map.Map;
import fr.thomas.modele.map.entity.MapEntity;
import fr.thomas.utils.options.Options;
import fr.thomas.utils.options.OptionsUtils;
import fr.thomas.vue.VueElement;
import fr.thomas.vue.entity.VueEnergy;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class KeyListener implements EventHandler<KeyEvent> {

    private Controller controller;

    public KeyListener(Controller controller) {
        this.controller = controller;
    }

    @Override
    public void handle(KeyEvent event) {
        Movement movement = null;
        String key = event.getText();
        if (key.equalsIgnoreCase(OptionsUtils.optionValues.get(Options.MOVE_TOP))) {
            movement = Movement.TOP;
        } else if (key.equalsIgnoreCase(OptionsUtils.optionValues.get(Options.MOVE_BOTTOM))) {
            movement = Movement.BOTTOM;
        } else if (key.equalsIgnoreCase(OptionsUtils.optionValues.get(Options.MOVE_LEFT))) {
            movement = Movement.LEFT;
        } else if (key.equalsIgnoreCase(OptionsUtils.optionValues.get(Options.MOVE_RIGHT))) {
            movement = Movement.RIGHT;
        } else if (event.getCode() == KeyCode.ESCAPE && controller.getGameState() == GameState.PLAY) {
            controller.getMenusManager().setGameState(GameState.PAUSE);
        }

        if (movement == null) {
            return;
        }

        if (controller.getGameState() != GameState.PLAY) {
            return;
        }

        if (controller.getGame() == null) {
            return;
        }

        if (controller.getGame().isEnd()) {
            return;
        }

        Player player = controller.getGame().getPlayer();
        Map map = controller.getGame().getMap();

        MapEntity element = map.getElement(player.getX() + movement.getX(), player.getY() + movement.getY());

        MovementResult result = MovementResult.ALLOW;
        if (element != null) {
            VueElement vueElement = controller.getVueElements().get("entity" + element.getId());
            try {
                result = element.onPass(player, vueElement);
            } catch (MovementException e) {
                controller.addChatLine(e.getMessage());
                new LoseTask(controller).start();
            }
            if (vueElement instanceof VueEnergy) {
                controller.getVueElements().remove(vueElement.getId());
                controller.getGame().getMap().getMapEntities().remove(element);
            }
        }

        if (result == MovementResult.ALLOW) {
            try {
                if (player.hasVisited(player.getX() + movement.getX(), player.getY() + movement.getY())) {
                    controller.addChatLine("Case déjà visité !");
                }
                player.removePower(Player.POWER_LOSS);
                player.addMovement(movement);
                player.move(movement);
                controller.getMoveValue().setText(String.valueOf(player.getMovementsHistory().size()));

                if (controller.getAutoSave().isSelected()) {
                    controller.getGameManager().save(controller.getGame());
                }
            } catch (MovementException e) {
                controller.addChatLine(e.getMessage());
                new LoseTask(controller).start();
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
