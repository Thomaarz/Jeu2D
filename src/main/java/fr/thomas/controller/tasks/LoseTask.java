package fr.thomas.controller.tasks;

import fr.thomas.controller.Controller;
import fr.thomas.modele.game.GameState;
import fr.thomas.modele.map.save.SaveUtils;

public class LoseTask extends GameTask {

    public LoseTask(Controller controller) {
        super(controller);

        controller.getMenusManager().setGameState(GameState.END);
        controller.getTextInfo().setText("Perdu !");
        controller.getEndInfos().setText("AHAHAHAHHAHAHA NUL !\n" +
                "Déplacements effectués: " + controller.getGame().getPlayer().getMovementsHistory().size() + "\n" +
                "Energies collectées: " + controller.getGame().getPlayer().getPowerUsed() + "\n" +
                "Retours effectués: " + controller.getGame().getPlayer().getCanceledMovements() + "\n" +
                "");

        controller.getGame().setEnd(true);
        SaveUtils.save(controller.getGame());
        controller.refreshHistory();
    }

    @Override
    public void onTick() {

        // End
        if (getTimer() == 50) {
            getGameLoop().stop();
        }
    }
}
