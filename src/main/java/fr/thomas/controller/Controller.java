package fr.thomas.controller;

import fr.thomas.controller.listeners.KeyListener;
import fr.thomas.controller.managers.GameManager;
import fr.thomas.controller.managers.MenusManager;
import fr.thomas.controller.managers.OptionsManager;
import fr.thomas.modele.game.Game;
import fr.thomas.modele.game.GameState;
import fr.thomas.utils.Utils;
import fr.thomas.exceptions.MovementException;
import fr.thomas.vue.VueElement;
import fr.thomas.vue.VuePlayer;
import fr.thomas.vue.menus.VueMenuGame;
import fr.thomas.vue.menus.VueMenuMain;
import fr.thomas.vue.menus.VueMenuOptions;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import lombok.Getter;
import lombok.Setter;

import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

@Getter
@Setter
public class Controller implements Initializable {

    private java.util.Map<String, VueElement> vueElements = new HashMap<>();

    private VuePlayer vuePlayer;

    private VueMenuGame vueMenuGame;

    private VueMenuMain vueMenuMain;

    private VueMenuOptions vueMenuOptions;

    private Game game;

    private OptionsManager optionsManager;

    private MenusManager menusManager;

    private GameManager gameManager;

    @FXML
    private Pane gameScreen;

    @FXML
    private Button play;

    @FXML
    private Button cancelMovement;

    @FXML
    private Button returnMenu;

    @FXML
    private Button options;

    @FXML
    private Button continueGame;

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
    private TextField topBind;

    @FXML
    private TextField bottomBind;

    @FXML
    private TextField rightBind;

    @FXML
    private TextField leftBind;

    @FXML
    private Text topKey;

    @FXML
    private Text bottomKey;

    @FXML
    private Text rightKey;

    @FXML
    private Text leftKey;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        // Managers
        optionsManager = new OptionsManager(this);
        optionsManager.load();
        gameManager = new GameManager(this);
        gameManager.createGame();
        menusManager = new MenusManager(this);

        // Vues
        initVues();

        // Listeners
        gameScreen.setFocusTraversable(true);
        gameScreen.setOnKeyPressed(new KeyListener(this));

        // Default State
        menusManager.setGameState(GameState.MENU);

        // Chat
        textChat.setFocusTraversable(true);

        // PowerBar
        powerBar.progressProperty().bind(game.getPlayer().getPowerProperty());

        // Jouer
        play.setOnMouseClicked(event -> {
            gameManager.clearLastGameMap();
            game.getPlayer().reset();
            gameManager.generateMap();
            menusManager.setGameState(GameState.PLAY);
        });

        // Cancel Movement
        cancelMovement.setOnMouseClicked(event -> {
            try {
                game.getPlayer().cancelMovement(1);
                addChatLine("Retour en arrière !");
            } catch (MovementException e) {
                addChatLine("Impossible de retourner en arrière !");
            }
        });

        // Options
        options.setOnMouseClicked(event -> {
            menusManager.setGameState(GameState.OPTIONS);
        });

        // Return
        returnMenu.setOnMouseClicked(event -> {
            menusManager.setGameState(GameState.MENU);

            // TODO
            optionsManager.save();
        });
    }

    /**
     * Add a formated message in the chat text area
     */
    public void addChatLine(String message) {
        textChat.appendText(Utils.getTime() + ": " + message + "\n");
    }


    private void initVues() {
        vuePlayer = new VuePlayer(getGame().getPlayer(), gameScreen);
        vuePlayer.add();

        vueMenuGame = new VueMenuGame(gameScreen);
        vueMenuGame.addNode(textChat, powerKey, powerBar, cancelMovement, moveKey, moveValue);
        vueMenuGame.add();

        vueMenuMain = new VueMenuMain(gameScreen);
        vueMenuMain.addNode(play, continueGame, options);
        vueMenuMain.add();

        vueMenuOptions = new VueMenuOptions(getGameScreen());
        vueMenuOptions.addNode(returnMenu, topBind, bottomBind, leftBind, rightBind, topKey, bottomKey, rightKey, leftKey);
        vueMenuOptions.add();
    }

}
