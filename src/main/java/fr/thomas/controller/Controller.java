package fr.thomas.controller;

import fr.thomas.modele.map.Map;
import fr.thomas.modele.map.MapElement;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import lombok.Getter;

import java.net.URL;
import java.util.ResourceBundle;

@Getter
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
        init();
        Image player = new Image("player.png");
        ImageView imageView = new ImageView(player);
        imageView.xProperty().bind(map.getPlayer().getXProperty());
        imageView.yProperty().bind(map.getPlayer().getYProperty());
        gameScreen.getChildren().add(imageView);

        // Listeners
        gameScreen.setFocusTraversable(true);
        gameScreen.setOnKeyPressed(new KeyListener(this));

        // Design
        //fillBackground();

        // PowerBar
        powerBar.progressProperty().bind(map.getPlayer().getPowerProperty());

        // Replay
        replay.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                map.reset();
                init();
            }
        });

        // Exit
        exit.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                System.exit(0);
            }
        });
    }

    public void init() {
        gameScreen.getChildren().removeIf(node -> node != null && node.getId() != null && node.getId().startsWith("bloc"));

        // TEST
        Image image = new Image("tiles/bloc_tile.png");
        for (MapElement element : map.getMapElements()) {
            ImageView imageView = new ImageView(image);
            imageView.xProperty().bind(element.getXProperty());
            imageView.yProperty().bind(element.getYProperty());
            imageView.setId("bloc" + element.getId());
            gameScreen.getChildren().add(imageView);
        }

    }
}
