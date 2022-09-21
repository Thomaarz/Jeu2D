package fr.thomas.controller.tasks;

import fr.thomas.controller.Controller;
import fr.thomas.utils.Utils;
import javafx.scene.layout.Pane;

public class LoseTask extends GameTask {

    public LoseTask(Controller controller) {
        super(controller);
        controller.getTextInfo().setText("Perdu !");

        controller.openMenu();
    }

    @Override
    public void onTick() {

        // End
        if (getTimer() == 500) {
            getGameLoop().stop();
            getController().getTextInfo().setText(null);
        }
    }
}
