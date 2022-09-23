package fr.thomas.controller.tasks;

import fr.thomas.controller.Controller;
import fr.thomas.modele.game.GameState;
import fr.thomas.vue.bloc.VueHouse;

public class WinTask extends GameTask {

    public WinTask(Controller controller) {
        super(controller);
        controller.getTextInfo().setText("Gagné !");

        controller.getMenusManager().setGameState(GameState.MENU);
    }

    @Override
    public void onTick() {

        /*
        if (getTimer() % 3 == 0) {
            getController().getVueElements().forEach((s, vueElement) -> {
                if (vueElement instanceof VueHouse) {
                    vueElement.getImageView().setScaleX(vueElement.getImageView().getScaleX() + 1);
                    vueElement.getImageView().setScaleY(vueElement.getImageView().getScaleY() + 1);
                    vueElement.getImageView().setRotate(vueElement.getImageView().getRotate() + 10);
                }
            });
        }
         */

        // End
        if (getTimer() == 50) {
            getGameLoop().stop();
        }
    }
}
