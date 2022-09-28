package fr.thomas.controller.tasks;

import fr.thomas.controller.Controller;
import fr.thomas.modele.game.Game;
import fr.thomas.modele.map.entity.Enemy;
import fr.thomas.modele.map.entity.MapEntity;

public class EnemyTask extends GameTask {

    private Game game;

    public EnemyTask(Controller controller) {
        super(controller);
        this.game = controller.getGame();
    }

    @Override
    public void onTick() {
        if (getTimer() % 100 == 0) {
            for (MapEntity enemy : game.getMap().getElements(Enemy.class)) {
                enemy.move(1, 0);
            }
        }
    }
}
