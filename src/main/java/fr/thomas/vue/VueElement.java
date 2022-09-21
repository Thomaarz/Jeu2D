package fr.thomas.vue;

import fr.thomas.modele.map.Localizable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import lombok.Getter;

@Getter
public class VueElement {

    private Pane gameScreen;

    private ImageView imageView;

    private String id;

    public VueElement(Image image, Localizable element, Pane gameScreen) {
        this.gameScreen = gameScreen;
        this.id = "entity" + element.getId();

        imageView = new ImageView(image);
        imageView.xProperty().bind(element.getXProperty());
        imageView.yProperty().bind(element.getYProperty());
        imageView.setId(this.id);
    }

    public void add() {
        gameScreen.getChildren().add(imageView);
    }

    public void remove() {
        gameScreen.getChildren().remove(imageView);
    }

}
