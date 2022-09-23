package fr.thomas.controller.tasks;

import fr.thomas.controller.Controller;
import fr.thomas.modele.game.GameState;

public class LoseTask extends GameTask {

    public LoseTask(Controller controller) {
        super(controller);
        controller.getTextInfo().setText("Perdu !");

        controller.getMenusManager().setGameState(GameState.MENU);
    }

    @Override
    public void onTick() {

        // End
        if (getTimer() == 50) {
            getGameLoop().stop();
        }
    }
}
