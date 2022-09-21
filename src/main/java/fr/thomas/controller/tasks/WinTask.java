package fr.thomas.controller.tasks;

import fr.thomas.controller.Controller;
import fr.thomas.vue.bloc.VueHouse;

public class WinTask extends GameTask {

    public WinTask(Controller controller) {
        super(controller);
    }

    @Override
    public void onTick() {

        // End
        if (getTimer() == 100) {
            getGameLoop().stop();
        }

        if (getTimer() % 5 == 0) {
            getController().getVueElements().forEach((s, vueElement) -> {
                if (vueElement instanceof VueHouse) {
                    vueElement.getImageView().setScaleX(vueElement.getImageView().getScaleX() + 1);
                    vueElement.getImageView().setScaleY(vueElement.getImageView().getScaleY() + 1);
                    vueElement.getImageView().setRotate(vueElement.getImageView().getRotate() + 10);
                }
            });
        }
    }
}
