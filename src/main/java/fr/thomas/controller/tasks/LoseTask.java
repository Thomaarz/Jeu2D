package fr.thomas.controller.tasks;

import fr.thomas.controller.Controller;
import fr.thomas.utils.Utils;
import javafx.scene.layout.Pane;

public class LoseTask extends GameTask {

    public LoseTask(Controller controller) {
        super(controller);
        controller.getTextInfo().setText("Perdu !");
    }

    @Override
    public void onTick() {

        // End
        if (getTimer() == 500) {
            getGameLoop().stop();
            getController().getTextInfo().setText(null);
        }

        if (getTimer() % 5 == 0) {
            /*
            getController().getMap().getMapEntities().forEach(mapEntity -> {

                mapEntity.move(Utils.random(-1, 3), Utils.random(-1, 3));
            });
             */
        }
    }
}
