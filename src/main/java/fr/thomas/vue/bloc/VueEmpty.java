package fr.thomas.vue.bloc;

import fr.thomas.modele.map.Localizable;
import fr.thomas.vue.VueElement;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

public class VueEmpty extends VueElement {

    public static final Image empty = new Image("tiles/empty_tile.png");

    public VueEmpty(Localizable element, Pane gameScreen) {
        super(empty, element, gameScreen);
    }
}
