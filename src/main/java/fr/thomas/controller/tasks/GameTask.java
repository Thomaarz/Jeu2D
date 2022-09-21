package fr.thomas.controller.tasks;

import fr.thomas.controller.Controller;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import lombok.Getter;

import java.util.ArrayList;

@Getter
public abstract class GameTask {

	private Controller controller;
    private Timeline gameLoop;
    private Pane gameScreen;

	private int timer = 0;

    public GameTask(Controller controller) {
		this.controller = controller;
		this.gameScreen = controller.getGameScreen();
    }

	public abstract void onTick();

	private void init() {
		gameLoop = new Timeline();
		gameLoop.setCycleCount(Timeline.INDEFINITE);

		KeyFrame keyFrame = new KeyFrame(

				// FPS
				Duration.seconds(0.017),

				(event -> {
					onTick();
					timer++;
				})
		);
		gameLoop.getKeyFrames().add(keyFrame);
	}

	public void start() {
		init();
		gameLoop.play();
	}
}