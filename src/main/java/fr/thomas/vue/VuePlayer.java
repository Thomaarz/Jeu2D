package fr.thomas.vue;

import fr.thomas.modele.map.Localizable;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

public class VuePlayer extends VueElement {

    public static final Image player = new Image("player.png");

    public VuePlayer(Localizable element, Pane gameScreen) {
        super(player, element, gameScreen);
    }
}
