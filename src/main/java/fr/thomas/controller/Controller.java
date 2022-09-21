package fr.thomas.controller;

import fr.thomas.controller.listeners.KeyListener;
import fr.thomas.modele.game.Game;
import fr.thomas.modele.game.GameState;
import fr.thomas.modele.map.entity.*;
import fr.thomas.modele.map.entity.Void;
import fr.thomas.utils.Utils;
import fr.thomas.exceptions.MovementException;
import fr.thomas.modele.map.Map;
import fr.thomas.modele.map.Localizable;
import fr.thomas.vue.VueElement;
import fr.thomas.vue.VuePlayer;
import fr.thomas.vue.bloc.VueBloc;
import fr.thomas.vue.bloc.VueEnergy;
import fr.thomas.vue.bloc.VueHouse;
import fr.thomas.vue.bloc.VueVoid;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import lombok.Getter;

import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

@Getter
public class Controller implements Initializable {

    private java.util.Map<String, VueElement> vueElements = new HashMap<>();

    private VuePlayer vuePlayer;

    private Game game;

    @FXML
    private Pane gameScreen;

    @FXML
    private Button replay;

    @FXML
    private Button exit;

    @FXML
    private Button cancelMovement;

    @FXML
    private Text powerKey;

    @FXML
    private ProgressBar powerBar;

    @FXML
    private TextArea textChat;

    @FXML
    private Text textInfo;

    @FXML
    private Text moveValue;

    @FXML
    private Text moveKey;

    @FXML
    private Text endInfo;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        // Create Game
        game = new Game(new Map(), GameState.PLAY);
        vuePlayer = new VuePlayer(getGame().getMap().getPlayer(), gameScreen);
        init();

        // Listeners
        gameScreen.setFocusTraversable(true);
        gameScreen.setOnKeyPressed(new KeyListener(this));

        // Chat
        textChat.setFocusTraversable(true);

        // PowerBar
        powerBar.progressProperty().bind(game.getMap().getPlayer().getPowerProperty());

        // Buttons
        replay.setOnMouseClicked(event -> {
            game.getMap().reset();
            hideMenu();
            init();
        });
        cancelMovement.setOnMouseClicked(event -> {
            try {
                game.getMap().getPlayer().cancelMovement(1);
                addChatLine("Retour en arrière !");
            } catch (MovementException e) {
                addChatLine("Impossible de retourner en arrière !");
            }
        });
        exit.setOnMouseClicked(event -> {
            Utils.saveGame(game);
            System.exit(0);
        });
    }

    /**
     * Call this function for init or replay
     */
    public void init() {

        // Remove each bloc, energy, ...
        gameScreen.getChildren().removeIf(node -> node != null && node.getId() != null && node.getId().startsWith("entity"));

        // Place Player
        placePlayer();

        // Place Elements
        placeElements();

        // Clear Texts
        textChat.clear();
        textInfo.setText(null);
        moveValue.setText("0");
        cancelMovement.setDisable(false);

        // Add Info in Chat
        addChatLine("Nouvelle partie !");
    }

    /**
     * Create the player node
     */
    public void placePlayer() {
        this.vuePlayer.add();
    }

    /**
     * Add a formated message in the chat text area
     */
    public void addChatLine(String message) {
        textChat.appendText(Utils.getTime() + ": " + message + "\n");
    }

    /**
     * Place the elements from the mapEntities
     */
    public void placeElements() {

        // Blocs & Energy & House & Void
        for (Localizable element : game.getMap().getMapEntities()) {
            VueElement vueElement = null;
            if (element instanceof Bloc) {
                vueElement = new VueBloc(element, gameScreen);
            } else if (element instanceof Energy) {
                vueElement = new VueEnergy(element, gameScreen);
            } else if (element instanceof House) {
                vueElement = new VueHouse(element, gameScreen);
            } else if (element instanceof Void) {
                vueElement = new VueVoid(element, gameScreen);
            }

            if (vueElement != null) {
                vueElement.add();
                vueElements.put(vueElement.getId(), vueElement);
            }
        }
    }

    public void openMenu() {
        game.setGameState(GameState.WAIT);

        getVueElements().forEach((s, vueElement) -> {
            vueElement.remove();
        });

        // Hide
        getTextChat().setVisible(false);
        getPowerKey().setVisible(false);
        getPowerBar().setVisible(false);
        getCancelMovement().setVisible(false);
        getMoveKey().setVisible(false);
        getMoveValue().setVisible(false);

        // Show
        getExit().setVisible(true);
        getReplay().setVisible(true);

        endInfo.setText("Déplacements: " + getGame().getMap().getPlayer().getMovementsHistory().size() + "\n" +
                "Energie: " + getGame().getMap().getPlayer().getPower() + "%\n" +
                "");

        getEndInfo().setVisible(true);
        vuePlayer.remove();
    }

    public void hideMenu() {
        game.setGameState(GameState.PLAY);

        // Show
        getTextChat().setVisible(true);
        getPowerKey().setVisible(true);
        getPowerBar().setVisible(true);
        getCancelMovement().setVisible(true);
        getMoveKey().setVisible(true);
        getMoveValue().setVisible(true);

        // Hide
        getExit().setVisible(false);
        getReplay().setVisible(false);
        getEndInfo().setVisible(false);

        vuePlayer.add();
    }
}
