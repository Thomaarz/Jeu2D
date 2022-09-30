package fr.thomas.controller;

import fr.thomas.controller.listeners.KeyListener;
import fr.thomas.controller.managers.GameManager;
import fr.thomas.controller.managers.MenusManager;
import fr.thomas.controller.managers.OptionsManager;
import fr.thomas.controller.tasks.EnemyTask;
import fr.thomas.modele.entity.Player;
import fr.thomas.modele.game.Game;
import fr.thomas.modele.game.GameState;
import fr.thomas.modele.map.save.SaveUtils;
import fr.thomas.modele.map.save.Saves;
import fr.thomas.utils.Utils;
import fr.thomas.exceptions.MovementException;
import fr.thomas.vue.VueElement;
import fr.thomas.vue.VuePlayer;
import fr.thomas.vue.menus.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
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

    private VueMenuGame vueMenuGame;

    private VueMenuMain vueMenuMain;

    private VueMenuOptions vueMenuOptions;

    private VueMenuEndGame vueMenuEndGame;

    private VueMenuPause vueMenuPause;

    private VueMenuHistory vueMenuHistory;

    private Game game;

    private OptionsManager optionsManager;

    private MenusManager menusManager;

    private GameManager gameManager;

    private GameState gameState;

    @FXML
    private Pane gameScreen;

    @FXML
    private Button play;

    @FXML
    private Button saveGame;

    @FXML
    private Button cancelMovement;

    @FXML
    private Button returnMenu;

    @FXML
    private Button options;

    @FXML
    private Button gameNext;

    @FXML
    private Button continueGame;

    @FXML
    private Button history;

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
    private Text endInfos;

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

    private Game test;

    @FXML
    private ListView<Game> historyList;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        // Saves
        test = SaveUtils.loadSave("Game-1");
        if (test != null) {
            System.out.println(test.getMap().getMapEntities().size());
        }

        // Managers
        optionsManager = new OptionsManager(this);
        optionsManager.load();
        gameManager = new GameManager(this);
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

        // Continue
        continueGame.setOnMouseClicked(event -> {
            menusManager.setGameState(GameState.PLAY);
        });

        // Jouer
        play.setOnMouseClicked(event -> {
            // NEW GAME
            gameManager.clearLastGameMap();
            gameManager.createGame();

            gameManager.generateMap();
            menusManager.setGameState(GameState.PLAY);

            /*
            // FROM SAVE
             this.game = test;
            gameManager.createGameFromSave(game);
            gameManager.placeElements();
            menusManager.setGameState(GameState.PLAY);
             */

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

        // Historique
        history.setOnMouseClicked(event -> {
            menusManager.setGameState(GameState.HISTORY);
        });


        gameNext.setOnMouseClicked(event -> {
            menusManager.setGameState(GameState.MENU);
        });

        // Return
        returnMenu.setOnMouseClicked(event -> {
            menusManager.setGameState(GameState.MENU);

            // TODO
            optionsManager.save();
        });

        saveGame.setOnMouseClicked(event -> {
            test = game;

            SaveUtils.save("Game-1", test);
        });
    }

    /**
     * Add a formated message in the chat text area
     */
    public void addChatLine(String message) {
        textChat.appendText(Utils.getTime() + ": " + message + "\n");
    }


    private void initVues() {
        vueMenuGame = new VueMenuGame(gameScreen);
        vueMenuGame.addNode(textChat, powerKey, powerBar, cancelMovement, moveKey, moveValue);
        vueMenuGame.add();

        vueMenuMain = new VueMenuMain(gameScreen);
        vueMenuMain.addNode(play, options, history);
        vueMenuMain.add();

        vueMenuOptions = new VueMenuOptions(gameScreen);
        vueMenuOptions.addNode(returnMenu, topBind, bottomBind, leftBind, rightBind, topKey, bottomKey, rightKey, leftKey);
        vueMenuOptions.add();

        vueMenuEndGame = new VueMenuEndGame(gameScreen);
        vueMenuEndGame.addNode(gameNext, endInfos);
        vueMenuEndGame.add();

        vueMenuPause = new VueMenuPause(gameScreen);
        vueMenuPause.addNode(continueGame, saveGame);
        vueMenuPause.add();

        vueMenuHistory = new VueMenuHistory(gameScreen);
        vueMenuHistory.addNode(returnMenu, historyList);
        vueMenuHistory.add();
    }

}
