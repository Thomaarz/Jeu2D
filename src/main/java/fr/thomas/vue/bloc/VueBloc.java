package fr.thomas.vue.bloc;

import fr.thomas.modele.map.Localizable;
import fr.thomas.vue.VueElement;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

public class VueBloc extends VueElement {

    public static final Image bloc = new Image("tiles/bloc_tile.png");

    public VueBloc(Localizable element, Pane gameScreen) {
        super(bloc, element, gameScreen);
    }
}
