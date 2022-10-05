package fr.thomas.controller.managers;

import fr.thomas.Infos;
import fr.thomas.controller.Controller;
import fr.thomas.modele.entity.Player;
import fr.thomas.modele.game.Game;
import fr.thomas.modele.map.Localizable;
import fr.thomas.modele.map.Map;
import fr.thomas.modele.map.entity.*;
import fr.thomas.modele.map.entity.Void;
import fr.thomas.modele.map.save.SaveUtils;
import fr.thomas.vue.VueElement;
import fr.thomas.vue.VuePlayer;
import fr.thomas.vue.entity.*;
import lombok.Getter;

public class GameManager {

    private Controller controller;

    @Getter
    private VuePlayer vuePlayer;

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

        // Vues
        vuePlayer = new VuePlayer(player, controller.getGameScreen());
        vuePlayer.add();

        // Create Game
        controller.setGame(new Game(player, map));

        // Clear Texts
        controller.getTextChat().clear();
        controller.getTextInfo().setText(null);
        controller.getMoveValue().setText("0");

        // Add Info in Chat
        controller.getTextChat().clear();
        controller.addChatLine("Nouvelle partie !");

        // Bind
        controller.getPowerBar().progressProperty().bind(player.getPowerProperty());
    }

    public void createGameFromSave(Game game) {
        // Vues
        vuePlayer = new VuePlayer(game.getPlayer(), controller.getGameScreen());
        vuePlayer.add();

        // Clear Texts
        controller.getTextChat().clear();
        controller.getTextInfo().setText(null);
        controller.getMoveValue().setText(String.valueOf(game.getPlayer().getMovementsHistory().size()));

        // Add Info in Chat
        controller.getTextChat().clear();
        controller.addChatLine("Chargement de la partie (" + game.getName() + ") !");
        if (game.isEnd()) {
            controller.addChatLine("Partie terminé, affichage du récapitulatif");
            controller.addChatLine("Appuyez sur ECHAP pour revenir en arrière");
        }

        // Bind
        controller.getPowerBar().progressProperty().bind(game.getPlayer().getPowerProperty());
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
    }

    public void save(Game game) {
        SaveUtils.save(game);
        controller.refreshHistory();
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
            } else if (element instanceof Visited) {
                vueElement = new VueVisited(element, controller.getGameScreen());
            } else if (element instanceof Enemy) {
                vueElement = new VueEnemy(element, controller.getGameScreen());
            }

            if (vueElement != null) {
                vueElement.add();
                controller.getVueElements().put(vueElement.getId(), vueElement);
            }
        }
    }
}
