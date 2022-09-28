package fr.thomas.vue.entity;

import fr.thomas.modele.map.Localizable;
import fr.thomas.vue.VueElement;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

public class VueEnemy extends VueElement {

    public static final Image enemy = new Image("tiles/enemy_tile.png");

    public VueEnemy(Localizable element, Pane gameScreen) {
        super(enemy, element, gameScreen);
    }
}
