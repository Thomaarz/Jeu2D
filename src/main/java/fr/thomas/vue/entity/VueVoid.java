package fr.thomas.vue.entity;

import fr.thomas.modele.map.Localizable;
import fr.thomas.vue.VueElement;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

public class VueVoid extends VueElement {

    public static final Image house = new Image("tiles/empty_tile.png");

    public VueVoid(Localizable element, Pane gameScreen) {
        super(house, element, gameScreen);
    }
}
