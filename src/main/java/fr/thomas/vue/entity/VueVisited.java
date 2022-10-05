package fr.thomas.vue.entity;

import fr.thomas.modele.map.Localizable;
import fr.thomas.vue.VueElement;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

public class VueVisited extends VueElement {

    public static final Image empty = new Image("tiles/visited_tile.png");

    public VueVisited(Localizable element, Pane gameScreen) {
        super(empty, element, gameScreen);
    }
}
