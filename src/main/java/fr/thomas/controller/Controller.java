package fr.thomas.controller;

import fr.thomas.Infos;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;

import javax.swing.*;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private Pane gameScreen;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        // Listeners
        gameScreen.setFocusTraversable(true);
        gameScreen.setOnKeyPressed(new KeyListener());

        // Design
        fillBackground();

    }

    private void fillBackground() {
        Image grass = new Image("tiles/grass_tile.png");

        for (int x = 0; x <= Infos.WIDTH; x += Infos.TILE_SIZE) {
            for (int y = 0; y <= Infos.HEIGHT / 2; y += Infos.TILE_SIZE) {
                System.out.println("a");
                ImageView imageView = new ImageView(grass);
                imageView.setX(x);
                imageView.setY(y);
                gameScreen.getChildren().add(imageView);
            }
        }
    }
}
