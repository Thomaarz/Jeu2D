package fr.thomas.controller.managers;

import fr.thomas.controller.Controller;
import fr.thomas.modele.game.GameState;

public class MenusManager {

    private Controller controller;

    public MenusManager(Controller controller) {
        this.controller = controller;
    }

    public void openMainMenu() {
        controller.getVueElements().forEach((s, vueElement) -> {
            vueElement.hide();
        });

        controller.getVuePlayer().hide();
        controller.getVueMenuGame().hide();
        controller.getVueMenuMain().show();
        controller.getVueMenuOptions().hide();

        controller.getTextInfo().setText("Menu");
        controller.getTextInfo().setVisible(true);
    }

    public void openGameMenu() {
        controller.getVueElements().forEach((s, vueElement) -> {
            vueElement.show();
        });

        controller.getVuePlayer().show();
        controller.getVueMenuGame().show();
        controller.getVueMenuMain().hide();
        controller.getVueMenuOptions().hide();

        controller.getTextInfo().setVisible(false);
    }

    public void openOptionsMenu() {
        controller.getVueElements().forEach((s, vueElement) -> {
            vueElement.hide();
        });

        controller.getVuePlayer().hide();
        controller.getVueMenuGame().hide();
        controller.getVueMenuMain().hide();
        controller.getVueMenuOptions().show();

        controller.getTextInfo().setText("Options");
        controller.getTextInfo().setVisible(true);
    }

    public void setGameState(GameState gameState) {
        controller.getGame().setGameState(gameState);

        switch (gameState) {
            case PLAY:
                openGameMenu();
                break;
            case MENU:
                openMainMenu();
                break;
            case OPTIONS:
                openOptionsMenu();
                break;

        }
    }

}
