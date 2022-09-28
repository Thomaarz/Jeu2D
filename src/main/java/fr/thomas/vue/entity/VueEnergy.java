package fr.thomas.vue.entity;

import fr.thomas.modele.map.Localizable;
import fr.thomas.vue.VueElement;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

public class VueEnergy extends VueElement {

    public static final Image energy = new Image("tiles/energy_tile.png");

    public VueEnergy(Localizable element, Pane gameScreen) {
        super(energy, element, gameScreen);
    }
}
