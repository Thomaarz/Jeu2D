package fr.thomas.controller.managers;

import fr.thomas.Infos;
import fr.thomas.controller.Controller;
import fr.thomas.modele.entity.Player;
import fr.thomas.modele.game.Game;
import fr.thomas.modele.game.GameState;
import fr.thomas.modele.map.Localizable;
import fr.thomas.modele.map.Map;
import fr.thomas.modele.map.entity.Bloc;
import fr.thomas.modele.map.entity.Energy;
import fr.thomas.modele.map.entity.House;
import fr.thomas.modele.map.entity.Void;
import fr.thomas.vue.VueElement;
import fr.thomas.vue.bloc.VueBloc;
import fr.thomas.vue.bloc.VueEnergy;
import fr.thomas.vue.bloc.VueHouse;
import fr.thomas.vue.bloc.VueVoid;

public class GameManager {

    private Controller controller;

    public GameManager(Controller controller) {
        this.controller = controller;
    }

    /**
     * Call this function for init or replay
     */
    public void createGame() {

        // Create Player, Map
        Player player = new Player(Infos.MAP_SIZE / 2, Infos.MAP_SIZE / 2);
        Map map = new Map();

        // Create Game
        controller.setGame(new Game(player, map, GameState.MENU));

        // Clear Texts
        controller.getTextChat().clear();
        controller.getTextInfo().setText(null);
        controller.getMoveValue().setText("0");

        // Add Info in Chat
        controller.addChatLine("Nouvelle partie !");
    }

    public void generateMap() {

        // Create The Map Objects
        controller.getGame().getMap().createMap();

        // Place The Elements
        placeElements();
    }

    public void clearLastGameMap() {
        controller.getVueElements().forEach((s, vueElement) -> controller.getGameScreen().getChildren().remove(vueElement.getImageView()));
        controller.getVueElements().clear();

        controller.getGame().getMap().reset();
    }

    /**
     * Place the elements from the mapEntities
     */
    public void placeElements() {

        // Blocs & Energy & House & Void
        for (Localizable element : controller.getGame().getMap().getMapEntities()) {
            VueElement vueElement = null;
            if (element instanceof Bloc) {
                vueElement = new VueBloc(element, controller.getGameScreen());
            } else if (element instanceof Energy) {
                vueElement = new VueEnergy(element, controller.getGameScreen());
            } else if (element instanceof House) {
                vueElement = new VueHouse(element, controller.getGameScreen());
            } else if (element instanceof Void) {
                vueElement = new VueVoid(element, controller.getGameScreen());
            }

            if (vueElement != null) {
                vueElement.add();
                controller.getVueElements().put(vueElement.getId(), vueElement);
            }
        }
    }

}
