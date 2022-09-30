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

        controller.getVueMenuGame().hide();
        controller.getVueMenuOptions().hide();
        controller.getVueMenuEndGame().hide();
        controller.getVueMenuPause().hide();
        controller.getVueMenuHistory().hide();
        controller.getVueMenuMain().show();

        controller.getTextInfo().setText("Menu");
        controller.getTextInfo().setVisible(true);

    }

    public void openGameMenu() {
        controller.getVueElements().forEach((s, vueElement) -> {
            vueElement.show();
        });

        controller.getVueMenuMain().hide();
        controller.getVueMenuOptions().hide();
        controller.getVueMenuEndGame().hide();
        controller.getVueMenuPause().hide();
        controller.getVueMenuHistory().hide();
        controller.getGameManager().getVuePlayer().show();
        controller.getVueMenuGame().show();

        controller.getTextInfo().setVisible(false);
    }

    public void openOptionsMenu() {
        controller.getVueElements().forEach((s, vueElement) -> {
            vueElement.hide();
        });

        controller.getVueMenuGame().hide();
        controller.getVueMenuMain().hide();
        controller.getVueMenuEndGame().hide();
        controller.getVueMenuPause().hide();
        controller.getVueMenuHistory().hide();
        controller.getVueMenuOptions().show();

        controller.getTextInfo().setText("Options");
        controller.getTextInfo().setVisible(true);
    }

    public void openHistoryMenu() {
        controller.getVueElements().forEach((s, vueElement) -> {
            vueElement.hide();
        });

        controller.getVueMenuGame().hide();
        controller.getVueMenuMain().hide();
        controller.getVueMenuEndGame().hide();
        controller.getVueMenuPause().hide();
        controller.getVueMenuOptions().hide();
        controller.getVueMenuHistory().show();

        controller.getTextInfo().setText("Historique");
        controller.getTextInfo().setVisible(true);
    }

    public void openEndGameMenu() {
        controller.getVueElements().forEach((s, vueElement) -> {
            vueElement.hide();
        });

        controller.getVueMenuGame().hide();
        controller.getGameManager().getVuePlayer().hide();
        controller.getVueMenuMain().hide();
        controller.getVueMenuOptions().hide();
        controller.getVueMenuPause().hide();
        controller.getVueMenuHistory().hide();
        controller.getVueMenuEndGame().show();

        controller.getTextInfo().setText("Fin de Partie");
        controller.getEndInfos().setText("WAOUUUU");
        controller.getTextInfo().setVisible(true);
    }

    public void openPauseGameMenu() {
        controller.getVueElements().forEach((s, vueElement) -> {
            vueElement.hide();
        });

        controller.getVueMenuGame().hide();
        controller.getGameManager().getVuePlayer().hide();
        controller.getVueMenuMain().hide();
        controller.getVueMenuOptions().hide();
        controller.getVueMenuEndGame().hide();
        controller.getVueMenuHistory().hide();
        controller.getVueMenuPause().show();

        controller.getTextInfo().setText("Pause");
        controller.getTextInfo().setVisible(true);
    }

    public void setGameState(GameState gameState) {
        controller.setGameState(gameState);

        switch (gameState) {
            case PLAY:
                openGameMenu();
                break;
            case END:
                openEndGameMenu();
                break;
            case PAUSE:
                openPauseGameMenu();
                break;
            case MENU:
                openMainMenu();
                break;
            case OPTIONS:
                openOptionsMenu();
                break;
            case HISTORY:
                openHistoryMenu();
                break;

        }
    }

}
