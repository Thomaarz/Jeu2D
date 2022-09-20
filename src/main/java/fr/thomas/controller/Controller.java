package fr.thomas.controller;

import fr.thomas.Infos;
import fr.thomas.modele.map.Map;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    private Map map;

    @FXML
    private Pane gameScreen;

    @FXML
    private Button replay;

    @FXML
    private Button exit;

    @FXML
    private ProgressBar powerBar;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        map = new Map();

        // Listeners
        gameScreen.setFocusTraversable(true);
        gameScreen.setOnKeyPressed(new KeyListener());

        // Design
        //fillBackground();

        replay.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                map.getPlayer().removePower(1);
            }
        });

        // PowerBar

        powerBar.progressProperty().bind(map.getPlayer().getPowerProperty());
    }

    private void fillBackground() {
        Image grass = new Image("tiles/grass_tile.png");

        for (int x = 0; x <= Infos.WIDTH; x += Infos.TILE_SIZE) {
            for (int y = 0; y <= Infos.HEIGHT; y += Infos.TILE_SIZE) {
                ImageView imageView = new ImageView(grass);
                imageView.setX(x);
                imageView.setY(y);
                gameScreen.getChildren().add(imageView);
            }
        }
    }
}
