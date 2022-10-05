package fr.thomas.controller.tasks;

import fr.thomas.Infos;
import fr.thomas.controller.Controller;
import fr.thomas.vue.entity.VueVisited;

public class VisitedTasks extends GameTask {

    private int i;
    private double brightness;

    private boolean endGame;


    public VisitedTasks(Controller controller, boolean endGame) {
        super(controller);
        this.i = 0;
        this.brightness = 1;
        this.endGame = endGame;

        getController().setTask(this);
    }

    @Override
    public void onTick() {
        if (getTimer() % getController().getAnimateSpeedReview().getValue() == 0) {
            brightness -= 1.0 / getController().getGame().getPlayer().getVisited().size();
            brightness = Math.max(brightness, 0.2);
            try {
                VueVisited vueVisited = getController().placeVisiteds(i++, brightness);
                if (endGame) {
                    vueVisited.getImageView().setTranslateX(-Infos.WIDTH / 5.0);
                    vueVisited.getImageView().setTranslateY(Infos.HEIGHT / 5.0);
                }

                if (vueVisited == null) {
                    stop();
                }
            } catch (Exception e) {
                stop();
            }
        }
    }

    @Override
    public void stop() {
        super.stop();

        getController().setTask(null);
        getGameLoop().stop();
    }
}
